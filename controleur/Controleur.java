package controleur;

import ihm.FramePrincipale;

public class Controleur{

    private FramePrincipale frmPrincipale;

    public Controleur() {
        this.frmPrincipale = new FramePrincipale(this);
    }

    public FramePrincipale getFramePrincipale() {
        return this.frmPrincipale;
    }
    public static void main(String[] args){
        new Controleur();
    }
}