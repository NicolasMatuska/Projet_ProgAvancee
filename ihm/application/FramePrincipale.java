package ihm.application;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import controleur.ControleurEditeur;
import ihm.reseau.PanelReseau;

public class FramePrincipale extends JFrame
{
    private ControleurEditeur ctrl;
    private PanelCentral panelCentral;
    private PanelGauche panelGauche;
    private PanelReseau panelDroite;
    private MenuBarre menuBarre;
    private JTextArea textArea;
        private JScrollPane scrollPane;


    public FramePrincipale(ControleurEditeur ctrl){

        this.ctrl = ctrl;

        //Paramètres de la frame
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setTitle("Application de document partagé");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());  
        this.setSize(new Dimension((int)tailleEcran.getWidth(),(int)tailleEcran.getHeight()));

		this.menuBarre = new MenuBarre(this.ctrl);
        this.setJMenuBar(this.menuBarre);

        //Création des panels
        //this.panelCentral = new PanelCentral(this.ctrl);
        this.textArea = new JTextArea();
        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.panelGauche = new PanelGauche(this.ctrl);
        this.panelDroite = new PanelReseau();
        JPanel panelHaut, panelBas;
        panelHaut = new JPanel();
        panelBas = new JPanel();

        panelHaut.setPreferredSize(new Dimension((int)tailleEcran.getWidth(), (int)tailleEcran.getHeight()/20));
        panelBas.setPreferredSize(new Dimension((int)tailleEcran.getWidth(), (int)tailleEcran.getHeight()/20));

        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(this.panelGauche, BorderLayout.WEST);
        this.add(this.panelDroite, BorderLayout.EAST);
        this.add(panelHaut, BorderLayout.NORTH);
        this.add(panelBas, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}