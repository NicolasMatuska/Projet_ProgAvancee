package controleur;

import ihm.application.FramePrincipale;
import reseau.Multicast;
import metier.Fichier;
import metier.Metier;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ControleurEditeur{

    private FramePrincipale ihm;
    private Multicast user;
    private Metier metier;

    public ControleurEditeur()
    {
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
    }

	// Methodes0.
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

    public void joinServer(String nameUser, String ip) {
        try {
            this.metier.addUser(nameUser);
            this.user = new Multicast(ip);
            this.user.setCtrl(this);
            //this.user.set
            //this.user.sendSalutation();
            //this.user.addUsername(nameUser);
            //this.ctrlReseau.sendSalutation();
            System.out.println("OK1");
            //System.out.println(this.getUsers().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMetier() {
        if (this.user != null ) 
            this.user.sendMetier();
    }

    public synchronized void mergeMetier(Metier metier){
        this.metier.mergeMetier(metier);
        this.majIHM();
    }

    public static void main(String[] args){
        new ControleurEditeur();
    }
}