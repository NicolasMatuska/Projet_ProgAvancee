package controleur;

import reseau.PeerToPeerServer;

public class ControleurReseau {
    
    private PeerToPeerServer serveur;

    public ControleurReseau() {
        this.serveur = new PeerToPeerServer(8080);
        this.serveur.start();
    }

    public void start() {
        this.serveur.start();
    }
}
