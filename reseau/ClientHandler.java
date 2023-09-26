package reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Socket> clientSockets;

    public ClientHandler(Socket clientSocket, List<Socket> clientSockets) {
        this.clientSocket = clientSocket;
        this.clientSockets = clientSockets;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String message;
            while ((message = reader.readLine()) != null) {
                // Traitez le message reçu du client ici
                System.out.println("Message reçu du client : " + message);

                // Diffusez le message à tous les autres clients connectés
                broadcastMessage(message);

                // Vous pouvez également implémenter la logique de gestion des fichiers partagés ici
            }

            // Si le client se déconnecte, retirez la socket de la liste des clients
            clientSockets.remove(clientSocket);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message) {
        // Diffusez le message à tous les autres clients connectés
        for (Socket socket : clientSockets) {
            if (socket != clientSocket) {
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write(message + "\n");
                    System.out.println("COnnecte");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

