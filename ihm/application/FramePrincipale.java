package ihm.application;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JFrame;

import controleur.ControleurEditeur;
import ihm.reseau.PanelReseau;

public class FramePrincipale extends JFrame
{
    private ControleurEditeur ctrl;
    private PanelCentral panelCentral;
    private PanelGauche panelGauche;
    private PanelReseau panelDroite;
    private MenuBarre menuBarre;

    public FramePrincipale(ControleurEditeur ctrl){

        this.ctrl = ctrl;

        //Paramètres de la frame
        this.setTitle("Application de document partagé");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvre la fenêtre en pleine écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());  

		this.menuBarre = new MenuBarre(this.ctrl);
        this.setJMenuBar(this.menuBarre);

        //Création des panels
        this.panelCentral = new PanelCentral(this.ctrl);
        this.panelGauche = new PanelGauche(this.ctrl);
        this.panelDroite = new PanelReseau();

        this.add(this.panelCentral, BorderLayout.CENTER);
        this.add(this.panelGauche, BorderLayout.WEST);
        this.add(this.panelDroite, BorderLayout.EAST);

        this.setVisible(true);
    }
}