package reseau;

import java.io.*;
import java.net.*;

import controleur.ControleurEditeur;
import metier.Metier;


public class Multicast {

    private Metier metier;
    private final InetAddress multicastGroup;
    private int port;
    private MulticastSocket socket;
    private ControleurEditeur ctrl;

    public Multicast(String ip) throws IOException {

        port = 12345;
        multicastGroup = InetAddress.getByName(ip); // Adresse IP du groupe multicast
        int port = 12345; // Port sur lequel écouter les messages multicast

        MulticastSocket socket = new MulticastSocket(port);

        NetworkInterface networkInterface = NetworkInterface.getByName("localhost"); // Remplacez "eth0" par le nom de l'interface réseau souhaitée
        socket.joinGroup(new InetSocketAddress(multicastGroup, port), networkInterface);
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

                            if (receivedObject instanceof Metier) {
                                Metier receiveMetier = (Metier) receivedObject;
                                // System.out.println("Metier received: " + receiveMetier.toString());

                                //this.ctrl.mergeMetier(receiveMetier);

                            // Merge the received Metier object with the local Metier object

                            }
                            if (receivedObject instanceof String){
                                String textReceive = (String) receivedObject;
                                System.out.println(textReceive);
                                // System.out.println("Mouse received:" + mouseReceive.toString());
                                // System.out.println("Mouse Set Updated:" + this.ctrl.getMetier().getSetMouse());
                            
                                //this.ctrl.updateText(textReceive);
                                //this.ctrl.majIHM();
                            }
                            // Print the updated value of the Metier object
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error MulticastSender receive");
                    }
                    /*                     finally {
                        System.out.println("Multicast Sender close");
                        socket.close();
                    }*/ 

                }).start();
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
            socket.send(packet);
            // System.out.println("Metier sent: " + this.metier.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendText() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            //oos.writeObject(this.ctrl.getMouse());
            byte[] buffer = baos.toByteArray();
            oos.reset();
            baos.reset();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.multicastGroup, port);
            socket.send(packet);
            // System.out.println("Mouse sent: " + this.ctrl.getMouse().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCtrl(ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        this.metier = ctrl.getMetier();
    }
}