package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDialog;


import controleur.Controleur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;
	private JMenu menuFichier;
    private JMenuItem menuiFichierNouveau;
    private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierEnregistrer;

	//private JDialog dialogAideMenu;

	public MenuBarre(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		//this.dialogAideMenu = null;

		/*=========================*/
		/* Création des composants */
		/*=========================*/
		this.menuFichier = new JMenu("Fichier");
		this.menuFichier.setMnemonic('F');
        this.menuiFichierNouveau = new JMenuItem ("Nouveau ");
        this.menuiFichierOuvrir = new JMenuItem ("Ouvrir ");
        this.menuiFichierEnregistrer = new JMenuItem ("Enregistrer ");

		/*=======================*/
		/* Ajouts des composants */
		/*=======================*/
        this.menuFichier.add(this.menuiFichierNouveau);
        this.menuFichier.add(this.menuiFichierOuvrir);
        this.menuFichier.add(this.menuiFichierEnregistrer);
		this.add(menuFichier);

		/*============================*/
		/* Activations des composants */
		/*============================*/
		this.menuiFichierEnregistrer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			if (e.getSource() == this.menuiFichierEnregistrer)
				System.out.println("Hello");
                //this.ctrl.faire un truc
		}
	}
}