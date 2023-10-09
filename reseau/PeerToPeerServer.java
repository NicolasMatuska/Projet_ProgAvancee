package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import controleur.ControleurEditeur;

public class PeerToPeerServer {
    private int port;
    private ServerSocket serverSocket;
    private ArrayList<ServerClientHandler> clients;
    private ArrayList<Socket> clientSockets;
    private ControleurEditeur ctrl;

    public PeerToPeerServer(int port, ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        this.clients = new ArrayList<ServerClientHandler>();
        this.port = port;
    }

    public void start() {

        /*
         * try {
         * this.serverSocket = new ServerSocket(port);
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         * if (serverSocket == null)
         * {
         * System.out.println("Socket null");
         * return;
         * }else{
         * System.out.println("Socket not null");
         * 
         * }
         * 
         * try {
         * Socket client = serverSocket.accept();
         * ServerClientHandler sch = new ServerClientHandler(ctrl, client);
         * clients.add(sch);
         * sch.run();
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */

        try {
            this.serverSocket = new ServerSocket(port);
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

    public void majMetier()
    {
        for (ServerClientHandler sch : clients)
        {
            sch.majMetier(this.ctrl.getMetier());
        }
    }

    public void Stop()
    {
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RemoveClient(ServerClientHandler sch)
    {
        sch.Disconnect();
        this.clients.remove(sch);
        this.majMetier();
        this.ctrl.majIHM();
    }
}
