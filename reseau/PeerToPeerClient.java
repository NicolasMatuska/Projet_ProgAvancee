package reseau;

import metier.Fichier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.tools.FileObject;

import controleur.ControleurEditeur;

public class PeerToPeerClient {
    private String serverAddress;
    private int serverPort;
    private String nomClient;
    private Socket socket;
    private boolean connecte;
    private String nameUser;

    private ControleurEditeur ctrl;
    private ClientHandler clh;
    private Thread t;

    public PeerToPeerClient(String serverAddress, int serverPort, ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public String getNomClient(){
        return this.nomClient;
    }

    public void setCtrl(ControleurEditeur ctrl){
        this.ctrl = ctrl;
    }

    public void Disconnect()
    {
        //this.csh.Disconnect();
        // stop thread
        t.interrupt();
        // close socket
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.ctrl.Deconnecter();
    }

    public boolean connect() {
        try {
            // this.socket = new Socket(this.serverAddress, this.serverPort);
            // this.connecte = true;
            //this.csh = new ClientServerHandler(this.ctrl, this.socket, password);
            //this.t = new Thread(csh);
            //t.start();

            BufferedReader reader =
             new BufferedReader(new InputStreamReader(System.in));
            // Demander à l'utilisateur son nom et l'envoyer au serveur
            System.out.println("Entrez votre nom d'utilisateur : ");
            this.nameUser = reader.readLine();

            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connecté au serveur " + serverAddress + ":" + serverPort);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            new Thread(() -> {
                try {
                    BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = serverReader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Send messages to the server
            String message;
            while ((message = reader.readLine()) != null) {
                writer.println(this.nameUser + " : " + message);
            }
            System.out.println("prout");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.connecte;
    }
}