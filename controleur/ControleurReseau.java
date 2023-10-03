package controleur;

import reseau.PeerToPeerServer;

public class ControleurReseau {
    
    private PeerToPeerServer serveur;
    private ControleurEditeur ctrlEdit;

    public ControleurReseau() {
        this.serveur = new PeerToPeerServer(12345);
        this.serveur.start();
        }

    public void start() {
        this.serveur.start();
    }

    public static void main(String[] args){
        new ControleurReseau();
    }
}
