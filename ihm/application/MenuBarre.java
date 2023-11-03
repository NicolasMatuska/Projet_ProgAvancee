package ihm.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.ControleurEditeur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private ControleurEditeur ctrl;

	private JMenu menuFichier;

    private JMenuItem menuiFichierNouveau;
    private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierEnregistrer;
	private JMenuItem menuiFichierFermer;

	public MenuBarre(ControleurEditeur ctrl) 
	{
		this.ctrl = ctrl;

		/*=========================*/
		/* Création des composants */
		/*=========================*/
		this.menuFichier = new JMenu("Fichier");
		this.menuFichier.setMnemonic('F');

        this.menuiFichierNouveau 	 = new JMenuItem ("Nouveau "	);
        this.menuiFichierOuvrir 	 = new JMenuItem ("Ouvrir "	 );
        this.menuiFichierEnregistrer = new JMenuItem ("Enregistrer ");
		this.menuiFichierFermer 	 = new JMenuItem ("Fermer "	 );

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
		this.menuiFichierNouveau	.addActionListener(this);
		this.menuiFichierOuvrir		.addActionListener(this);
		this.menuiFichierEnregistrer.addActionListener(this);
		this.menuiFichierFermer		.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			//Nouveau fichier
			if (e.getSource() == this.menuiFichierNouveau){
				int choix = JOptionPane.showConfirmDialog(this.ctrl.getIhm(), "Les modifications non-enregistrées seront perdu, êtes-vous sûr de votre choix ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
				if (choix == JOptionPane.YES_OPTION)
					this.ctrl.nouveauFichier();
			}

			//Ouvrir un fichier
			if (e.getSource() == this.menuiFichierOuvrir){
				JFileChooser chooser = new JFileChooser(".");
				chooser.setFileFilter(new FileNameExtensionFilter("Fichier txt", "txt"));

				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					this.ctrl.ouvrirFichier(file);
				}
			}

			//Enregistrer un fichier
			if (e.getSource() == this.menuiFichierEnregistrer)
			{
				JFileChooser chooser = new JFileChooser(".");
				chooser.setDialogTitle("Enregistrer un fichier");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileNameExtensionFilter("Fichier txt", "txt"));

				int res = chooser.showSaveDialog(this);

				if (res == JFileChooser.APPROVE_OPTION) 
				{
					File file = chooser.getSelectedFile();

					//si l'utilisateur n'a pas mis l'extension .txt, on la rajoute
					if (!file.getName().endsWith(".txt"))
						file = new File(file.getAbsolutePath() + ".txt");
					
					//si le fichier existe déjà, on demande à l'utilisateur s'il veut l'écraser ou non
					if (file.exists()) 
					{
						int choix = JOptionPane.showConfirmDialog(this.ctrl.getIhm(), "Le fichier existe déjà, êtes-vous sûr de votre choix ?", "Confirmation", JOptionPane.YES_NO_OPTION);
		
						if (choix == JOptionPane.YES_OPTION)
							this.ctrl.enregistrerFichier(file);
					}

					try {
						FileWriter fw = new FileWriter(file);

						//si le métier n'a pas de contenu sauvegardé mais que l'utilisateur a écrit dans le textArea
						//on demande à l'utilisateur s'il veut sauvegarder le contenu du textArea ou non
						//rmq : une sauvegarde envoie le contenu aux autres utilisateurs donc il faut demander à l'utilisateur
						String contenuTextArea = this.ctrl.getContenuTextArea();
						String contenuFichier = this.ctrl.getContenuFichier();
						String contenuASauvegarder = null;

						//si le métier n'est pas la même chose que le textArea, on demande à l'utilisateur s'il veut quand même sauvegarder cette version non synchronisée avec les autres utilisateurs	
						if(!contenuTextArea.equals(contenuFichier))
						{
							int choix = JOptionPane.showConfirmDialog(this.ctrl.getIhm(), "Le contenu présent actuellement n'a pas été envoyé aux autres utilisateurs. Souhaitez-vous quand même sauvegarder cette version ?", "Confirmation", JOptionPane.YES_NO_OPTION);

							if (choix == JOptionPane.YES_OPTION)
								contenuASauvegarder = contenuTextArea;			
						}
						else
							contenuASauvegarder = contenuTextArea;
							

						fw.write(contenuASauvegarder);
						fw.flush();
						fw.close();
						
					} catch (Exception ex) {System.out.println("Erreur lors de l'écriture du fichier");}
					
				}	
			}

			//Fermer l'application
			if (e.getSource() == this.menuiFichierFermer){
				this.ctrl.fermerFichier();
			}
		}
	}
}