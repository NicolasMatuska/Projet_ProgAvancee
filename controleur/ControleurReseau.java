package controleur;

import java.net.ServerSocket;
import java.net.Socket;

import reseau.PeerToPeerServer;

public class ControleurReseau {
    
    private PeerToPeerServer serveur;
    private ControleurEditeur ctrl;

    public ControleurReseau(ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        // System.out.println(this.addressIsAlreadyUsed());
        if (!this.addressIsAlreadyUsed()) {
            this.serveur = new PeerToPeerServer(12345, this.ctrl);
            this.serveur.start();
        }
        else {
            this.ctrl.setResetCtrlReseau();
        }
    }

    public void start() {
        this.serveur.start();
    }

    public boolean addressIsAlreadyUsed() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            serverSocket.close();
            System.out.println("The address is available.");
            return false;
        } catch (Exception e) {
            System.err.println("The address is already in use or inaccessible.");
            return true;
        }
    }
}
