package ihm.application;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import controleur.ControleurEditeur;
import metier.Fichier;

public class FramePrincipale extends JFrame
{
    private ControleurEditeur ctrl;
    private PanelCentral panelCentral;
    private PanelGauche panelGauche;
    private PanelReseau panelDroite;
    private PanelHaut panelHaut;
    private PanelBas panelBas;

    private MenuBarre menuBarre;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    private String nomFichier = "";


    public FramePrincipale(ControleurEditeur ctrl){

        this.ctrl = ctrl;

        //Paramètres de la frame
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setTitle("Application de document partagé");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());  
        this.setMinimumSize(new Dimension((int) (tailleEcran.getWidth() * 0.9), (int) (tailleEcran.getHeight() * 0.9)));

		this.menuBarre = new MenuBarre(this.ctrl);
        this.setJMenuBar(this.menuBarre);

        //Création des panels
        //this.panelCentral = new PanelCentral(this.ctrl);
        this.textArea = new JTextArea();
        this.textArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.panelGauche = new PanelGauche(this.ctrl);
        this.panelDroite = new PanelReseau(this.ctrl);
        this.panelHaut = new PanelHaut(this.ctrl);
        this.panelBas = new PanelBas(this.ctrl);

        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(this.panelGauche, BorderLayout.WEST);
        this.add(this.panelDroite, BorderLayout.EAST);
        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelBas, BorderLayout.SOUTH);
        this.setVisible(true);
    }

	public void enregistrer() 
	{

		// Ouvrir fenetre enregistrement
		//if (nomFichier.isBlank())
		{
			JFileChooser choose = new JFileChooser(".");
			choose.setDialogTitle("Enregistrer un fichier");
			choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			if (choose.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
				return;
			nomFichier = choose.getSelectedFile().getAbsolutePath();
		}

		// Enregistrement du fichier
		this.ctrl.ecrireFichier(new Fichier(this.nomFichier, this.textArea.getText()));
    }

    public void setContenu(String contenu){
        System.out.println(contenu);
        this.textArea.setText(contenu);
    }

    public void majIHM() {
        this.panelDroite.repaint();
        this.textArea.repaint();
    }
}