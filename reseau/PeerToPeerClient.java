package reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

public class PeerToPeerClient {
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private List<Socket> clientSockets;

    public PeerToPeerClient(String serverAddress, int serverPort, List<Socket> clientSockets) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.clientSockets = clientSockets;
    }

    public void connect() {
        try {
            socket = new Socket(serverAddress, serverPort);
            System.out.println("Connecté au serveur " + serverAddress + ":" + serverPort);
            clientSockets.add(socket);

            // Créez des flux d'entrée et de sortie pour communiquer avec le serveur
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Lisez et envoyez des données depuis ici
            // writer.write("Données à envoyer\n");
            // writer.flush();

            // Fermez la connexion lorsque vous avez terminé
            // socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

