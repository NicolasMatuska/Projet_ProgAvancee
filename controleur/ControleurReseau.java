package controleur;

import reseau.PeerToPeerServer;

public class ControleurReseau {
    
    private PeerToPeerServer serveur;

    public ControleurReseau() {
        this.serveur = new PeerToPeerServer(12345);
        this.serveur.start();
    }

    public void start() {
        this.serveur.start();
    }
}
