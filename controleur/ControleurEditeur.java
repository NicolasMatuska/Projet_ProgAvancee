package controleur;

import ihm.application.FramePrincipale;
import reseau.PeerToPeerClient;
import metier.Fichier;
import metier.Metier;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class ControleurEditeur{

    private FramePrincipale ihm;
    private ArrayList<Socket> clientSockets = new ArrayList<>();
    private ControleurReseau ctrlReseau;
    private Metier metier;
    private PeerToPeerClient client;

    public ControleurEditeur()
    {
        //this.ctrlReseau = ctrlReseau;
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
    }

    public void setResetCtrlReseau() {
        this.ctrlReseau = null;
        System.out.println("Réseau deja utilisé");
    }

    public void openServer() {
        this.ctrlReseau = new ControleurReseau(this);
    }

    public void joinServer() {
        this.client = new PeerToPeerClient(null, 12345, this);
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

	public void ecrireFichier(Fichier fichier)
	{
		this.metier.ecrireFichier(fichier);
	}

    public void setContenu(String contenu){
        this.ihm.setContenu(contenu);
    }

    public Metier getMetier(){
        return this.metier;
    }
    
    public FramePrincipale getIHM() {
        return this.ihm;
    }

    public void majIHM() {
        this.ihm.majIHM();
    }

    public static void main(String[] args){
        new ControleurEditeur();
    }
}