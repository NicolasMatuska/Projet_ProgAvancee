package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import controleur.ControleurEditeur;

public class PeerToPeerServer {
    private int port;
    private List<Socket> clientSockets = new ArrayList<>();
    private ControleurEditeur ctrl;

    public PeerToPeerServer(int port, ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente sur le port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);

                // Créez un thread pour gérer la communication avec ce client
                Thread clientThread = new Thread(new ClientHandler(clientSocket, clientSockets, this.ctrl));
                clientThread.start();
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
