package reseau;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import controleur.ControleurEditeur;
import metier.Metier;
import metier.Salut;


public class Multicast {

    private Metier metier;
    private final InetAddress multicastGroup;
    private int port;
    private MulticastSocket socket;
    private ControleurEditeur ctrl;

    public Multicast(String ip) throws IOException {

        port = 12345;
        multicastGroup = InetAddress.getByName(ip); // Adresse IP du groupe multicast
        this.socket = new MulticastSocket(port);
        NetworkInterface networkInterface = NetworkInterface.getByName("eth0"); // Remplacez "eth0" par le nom de l'interface réseau souhaitée
        socket.joinGroup(new InetSocketAddress(multicastGroup, port), networkInterface);
        System.out.println("OK2");

        new Thread(
                () -> {
                    try {
                        while (true) {
                            // Receive a packet from the multicast group
                            byte[] buffer = new byte[500000];
                            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                            socket.receive(packet);

                            // Deserialize the Metier object from the packet data
                            byte[] data = packet.getData();
                            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
                            Object receivedObject = ois.readObject();

                            //Handle message
                            if (receivedObject instanceof Salut) {
                                this.sendMetier();
                                System.out.println("on vient de me salut");
                            }
                            if (receivedObject instanceof Metier) {
                                Metier receiveMetier = (Metier) receivedObject;

                                this.ctrl.mergeMetier(receiveMetier);

                            // Merge the received Metier object with the local Metier object

                            }
                            if (receivedObject instanceof String){
                                String textReceive = (String) receivedObject;
                                System.out.println(textReceive);
                                
                            }
                            // Print the updated value of the Metier object
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error MulticastSender receive");
                    }
                    

                }).start();
                        System.out.println("OK3");

    }

    public void sendMetier() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.metier);
            byte[] buffer = baos.toByteArray();
            oos.reset();
            baos.reset();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.multicastGroup, port);
            this.socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendText() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            byte[] buffer = baos.toByteArray();
            oos.reset();
            baos.reset();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.multicastGroup, port);
            this.socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSalutation(){
        Salut salut = new Salut();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(salut);
            byte[] buffer = baos.toByteArray();
            oos.reset();
            baos.reset();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.multicastGroup, port);
            this.socket.send(packet);
            System.out.println("Salut sent: ");

        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    public void setCtrl(ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        this.metier = ctrl.getMetier();
    }
}