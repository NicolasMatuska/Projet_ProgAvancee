package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import controleur.ControleurEditeur;

public class Metier{

    //attributs
    private Fichier fichier;

    private ControleurEditeur ctrl;

    private ArrayList<Utilisateur> alUtilisateur;
    private GestionnairePseudos gp;

    public Metier(ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        this.fichier = new Fichier();
        this.alUtilisateur = new ArrayList<Utilisateur>();
        this.gp = new GestionnairePseudos();
    }

    public Metier(ControleurEditeur ctrl, File file) {
        this.ctrl = ctrl;
        setFichier(file);
        this.alUtilisateur = new ArrayList<Utilisateur>();
        this.gp = new GestionnairePseudos();
    }

    /*------------------------------------*/
    /*méthodes pour gérer les utilisateurs*/
    /*------------------------------------*/
    public void addUtilisateur(String ip, int port) {
        String pseudo = this.gp.assignerPseudo();
        this.alUtilisateur.add(new Utilisateur(pseudo, ip, port));
    }

    public void removeUtilisateur(String pseudo) {
        for (Utilisateur utilisateur : this.alUtilisateur) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                this.alUtilisateur.remove(utilisateur);
                return;
            }
        }
    }

    public ArrayList<Utilisateur> getAlUtilisateur() {
        return this.alUtilisateur;
    }

    /*------------------------------*/
    /*méthodes pour gérer le fichier*/
    /*------------------------------*/
    public void   setContenuFichier(String contenu) {this.fichier.setContenu(contenu);}
    public String getContenuFichier()               {return this.fichier.getContenu();}

    public void   setNomFichier(String nomFichier) {this.fichier.setNomFichier(nomFichier);}
    public String getNomFichier()                  {return this.fichier.getNomFichier();}

    public void    setFichier(Fichier fichier) {this.fichier = fichier;}
    public Fichier getFichier()                {return this.fichier;}

    public void enregistrerFichier(File file) {
    }

    public void setFichier(File file) {

        String nomFichier = file.getName();

        //on récupère le contenu du fichier
        String contenu = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String ligne;
            while ((ligne = br.readLine()) != null) {
                contenu += ligne + "\n";
            }
            br.close();
        } catch (IOException e) {e.printStackTrace();}

        //on met à jour le fichier
        this.fichier = new Fichier(nomFichier, contenu);
    }


}
