package ihm.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import controleur.ControleurEditeur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private ControleurEditeur ctrl;
	private JMenu menuFichier;
    private JMenuItem menuiFichierNouveau;
    private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierEnregistrer;
	private JMenuItem menuiFichierFermer;

	//private JDialog dialogAideMenu;

	public MenuBarre(ControleurEditeur ctrl) 
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
		this.menuiFichierFermer = new JMenuItem ("Fermer ");

		/*=======================*/
		/* Ajouts des composants */
		/*=======================*/
        this.menuFichier.add(this.menuiFichierNouveau);
        this.menuFichier.add(this.menuiFichierOuvrir);
        this.menuFichier.add(this.menuiFichierEnregistrer);
		this.menuFichier.add(this.menuiFichierFermer);
		this.add(menuFichier);

		/*============================*/
		/* Activations des composants */
		/*============================*/
		this.menuiFichierNouveau.addActionListener(this);
		this.menuiFichierOuvrir.addActionListener(this);
		this.menuiFichierEnregistrer.addActionListener(this);
		this.menuiFichierFermer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			if (e.getSource() == this.menuiFichierNouveau){
				int choix = JOptionPane.showConfirmDialog(this.ctrl.getIHM(), "Les modifications non-enregistrées seront perdu, êtes-vous sûr de votre choix ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
				if (choix == JOptionPane.YES_OPTION)
					this.ctrl.nouveau();
			}
			if (e.getSource() == this.menuiFichierOuvrir){
				JFileChooser chooser = new JFileChooser(".");
				chooser.setFileFilter(new FileNameExtensionFilter("Fichier txt", "txt"));

				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath() != null)
				{
					File fichier = chooser.getSelectedFile();
					String extention = fichier.getName().substring(fichier.getName().lastIndexOf('.') + 1);

					if (extention.equals("txt"))
						this.ctrl.ouvrir(fichier);
					else
						JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format txt", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (e.getSource() == this.menuiFichierEnregistrer){
				this.ctrl.enregistrer();
			}
			if (e.getSource() == this.menuiFichierFermer){
				this.ctrl.frameDispose();
			}
		}
	}
}