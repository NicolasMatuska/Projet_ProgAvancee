package ihm;

import controleur.Controleur;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

public class FramePrincipale extends JFrame{
    private Controleur ctrl;
    private PanelCentral panelCentral;
    private PanelGauche panelGauche;
    private PanelDroite panelDroite;
    private MenuBarre menuBarre;

    public FramePrincipale(Controleur ctrl){

        this.ctrl = ctrl;

        //Paramètres de la frame
        this.setTitle("Application de document partagé");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvre la fenêtre en pleine écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("./ressources/ahbatrdtucompiles.png");    
        this.setIconImage(icon);   

		this.menuBarre = new MenuBarre(this.ctrl);
        this.setJMenuBar(this.menuBarre);

        //Création des panels
        this.panelCentral = new PanelCentral(this.ctrl);
        this.panelGauche = new PanelGauche(this.ctrl);
        this.panelDroite = new PanelDroite(this.ctrl);

        this.add(this.panelCentral, BorderLayout.CENTER);
        this.add(this.panelGauche, BorderLayout.WEST);
        this.add(this.panelDroite, BorderLayout.EAST);

        this.setVisible(true);
    }
}