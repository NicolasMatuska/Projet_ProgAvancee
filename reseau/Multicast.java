package reseau;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

import controleur.ControleurEditeur;
import metier.Fichier;
import metier.Metier;


public class Multicast {
    private static final String MULTICAST_GROUP = "239.0.0.1";
    private static final int PORT = 12345;

    private MulticastSocket multicastSocket;
    private InetAddress groupAddress;

    private ControleurEditeur ctrl;

    public Multicast(ControleurEditeur ctrl) {
        try {

            this.ctrl = ctrl;

            this.multicastSocket = new MulticastSocket(PORT);
            this.groupAddress = InetAddress.getByName(MULTICAST_GROUP);
            this.multicastSocket.joinGroup(groupAddress);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //On envoie le fichier au groupe et on met le metier à jour
    public void sendFileUpdate(Fichier instanceFich) {
        try {
            // Convertir l'objet en tableau d'octets
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(instanceFich);
            objectStream.flush(); //on vide le buffer pour être sûr que tout a été envoyé

            // Envoyer le tableau d'octets au groupe multicast
            byte[] buffer = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, PORT);
            multicastSocket.send(packet);

            // Fermer les flux
            objectStream.close();
            byteStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveFileUpdate() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            //Recevoir le tableau d'octets du groupe multicast
            multicastSocket.receive(packet);

            // Convertir le tableau d'octets en objet Fichier
            byte[] receivedData = packet.getData();
            ObjectInputStream objectStream = new ObjectInputStream(new ByteArrayInputStream(receivedData));
            Object receivedObject = objectStream.readObject();

            if (receivedObject instanceof Fichier) {
                Fichier receivedFichier = (Fichier) receivedObject;
                // System.out.println("Fichier received: " + receivedFichier.toString());

                this.ctrl.setFichier(receivedFichier);
            }
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (multicastSocket != null && !multicastSocket.isClosed()) {
            try {
                multicastSocket.leaveGroup(groupAddress);
                multicastSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
