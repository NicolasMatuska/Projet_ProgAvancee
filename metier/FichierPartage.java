package metier;

import java.util.ArrayList;
import java.util.List;

public class FichierPartage 
{
    private String nom;
    private StringBuilder contenu;
    private List<Utilisateur> utilisateursPartage;

    public FichierPartage(String nom) 
    {
        this.nom = nom;
        this.contenu = new StringBuilder();
        this.utilisateursPartage = new ArrayList<>();
    }

    public String getNom() 
    {
        return nom;
    }

    public String getContenu() 
    {
        return contenu.toString();
    }

    public void setContenu(String contenu) 
    {
        this.contenu = new StringBuilder(contenu);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) 
    {
        utilisateursPartage.add(utilisateur);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) 
    {
        utilisateursPartage.remove(utilisateur);
    }

    public List<Utilisateur> getUtilisateursPartage() 
    {
        return utilisateursPartage;
    }

    public void ajouterContenu(String texte) 
    {
        contenu.append(texte);
    }

    public void supprimerContenu(int debut, int fin) 
    {
        contenu.delete(debut, fin);
    }
}

