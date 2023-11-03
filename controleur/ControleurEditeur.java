package controleur;

import java.io.File;
import java.util.ArrayList;

import ihm.application.FramePrincipale;
import metier.Fichier;
import metier.Metier;
import metier.Utilisateur;
import reseau.Multicast;

public class ControleurEditeur{

    private FramePrincipale ihm;
    private Multicast       reseau;
    private Metier          metier;

    public ControleurEditeur()
    {
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
        this.reseau = new Multicast(this);
    }

    public void setFichier(Fichier receivedFichier) {this.metier.setFichier(receivedFichier);}

    /*---------------------*/
    /* méthodes pour l'ihm */
    /*---------------------*/

    public FramePrincipale getIhm() {return this.ihm;}
    public String getContenuTextArea() {return this.ihm.getContenuTextArea();}

    //méthodes ihm pour le menu
    public void nouveauFichier()
    {
        this.ihm.dispose();

        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
    }

    public void ouvrirFichier(File file) {

        this.ihm.dispose();
        this.metier = new Metier(this, file);
        this.ihm = new FramePrincipale(this);
        
    }

    public void enregistrerFichier(File file) {this.metier.enregistrerFichier(file);}
    public void fermerFichier     ()          {this.ihm.dispose();}

    /*------------------------*/
    /* méthodes pour le métier*/
    /*------------------------*/

    public ArrayList<Utilisateur> getAlUtilisateur() {return this.metier.getAlUtilisateur();}
    public String                getContenuFichier() {return this.metier.getContenuFichier();}

    public static void main(String[] args){
        new ControleurEditeur();
    }

    

    

    

    

    
}