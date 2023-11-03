package metier;

import java.io.Serializable;

public class Fichier implements Serializable{
    private String nomFichier;
    private String contenu;

    public Fichier (){
        this.nomFichier = null;
        this.contenu = null;
    }

    public Fichier (String nomFichier, String contenu){
        this.nomFichier = nomFichier;
        this.contenu = contenu;
    }

    public String getNomFichier (){return this.nomFichier;}
    public String getContenu    (){return this.contenu;   }

    public void setNomFichier (String nomFichier){this.nomFichier = nomFichier;}
    public void setContenu    (String contenu)   {this.contenu    = contenu;   }

    public String toString(){
        return this.nomFichier + " - " + this.contenu;
    }
}
