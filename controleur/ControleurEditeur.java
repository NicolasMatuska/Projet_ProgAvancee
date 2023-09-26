package controleur;

import ihm.application.FramePrincipale;
import reseau.PeerToPeerClient;

import java.awt.List;
import java.net.Socket;
import java.util.ArrayList;


public class ControleurEditeur{

    private FramePrincipale frmPrincipale;
    private ArrayList<Socket> clientSockets = new ArrayList<>();
    private ControleurReseau ctrlReseau;

    public ControleurEditeur(ControleurReseau ctrlReseau)
    {
        this.ctrlReseau = ctrlReseau;
        this.frmPrincipale = new FramePrincipale(this);
    }

    public void connexionUtilisateur(String ip, int port)
    {
        PeerToPeerClient client = new PeerToPeerClient(ip, port, this.clientSockets);
        client.connect();
    }

    
    public FramePrincipale getFramePrincipale() {
        return this.frmPrincipale;
    }
    public static void main(String[] args){
        new ControleurEditeur(new ControleurReseau());
    }
}