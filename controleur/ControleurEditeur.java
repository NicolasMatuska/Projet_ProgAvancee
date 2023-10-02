package controleur;

import ihm.application.FramePrincipale;
import reseau.PeerToPeerClient;
import metier.Metier;

import java.awt.List;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;


public class ControleurEditeur{

    private FramePrincipale ihm;
    private ArrayList<Socket> clientSockets = new ArrayList<>();
    private ControleurReseau ctrlReseau;
    private Metier metier;

    public ControleurEditeur(ControleurReseau ctrlReseau)
    {
        this.ctrlReseau = ctrlReseau;
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
    }

	// Methodes
    public void nouveau() 
    {

        //new Metier
		if (this.ihm != null)
			this.ihm.dispose();

		this.ihm = new FramePrincipale(this);
	}

	public void ouvrir() 
	{
        if (this.ihm != null)
            this.ihm.dispose();
        this.ihm = new FramePrincipale(this);
        this.metier = new Metier(this);
    }

	public void ouvrir(File fichier) 
    {
        this.ihm.dispose();
        this.ihm = new FramePrincipale(this);
        this.metier = new Metier(this, fichier);
    }

    public void enregistrer    ()                   { this.ihm.enregistrer (); }
	public void enregistrerSous()                   { this.ihm.enregistrer (); }
    public void frameDispose   ()                   { this.ihm.dispose     (); }

	public void ecrireFichier(String nomFichier, String texte)
	{
		this.metier.ecrireFichier(nomFichier, texte);
	}

    public void connexionUtilisateur(String ip, int port)
    {
        PeerToPeerClient client = new PeerToPeerClient(ip, port, this.clientSockets);
        client.connect();
    }

    public void setContenu(String contenu){
        this.ihm.setContenu(contenu);
    }
    
    public FramePrincipale getIHM() {
        return this.ihm;
    }
    public static void main(String[] args){
        new ControleurEditeur(new ControleurReseau());
    }
}