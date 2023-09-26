package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PeerToPeerServer {
    private int port;
    private List<Socket> clientSockets = new ArrayList<>();

    public PeerToPeerServer(int port) {
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
                Thread clientThread = new Thread(new ClientHandler(clientSocket, clientSockets));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
