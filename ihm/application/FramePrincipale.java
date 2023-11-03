package ihm.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import controleur.ControleurEditeur;

public class FramePrincipale extends JFrame
{
    private ControleurEditeur ctrl;

    //Panel
    private PanelGauche panelGauche;
    private PanelReseau panelDroite;
    private PanelHaut panelHaut;
    private PanelBas panelBas;

    //Menu
    private MenuBarre menuBarre;

    //TextArea
    private JTextPane textArea;
    private JScrollPane scrollPane;

    //Attributs pour le textArea
    private StyleContext styleContext;
    private AttributeSet greenAttributeSet;
    private AttributeSet defaultAttributeSet;


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

        /*-------------------- */
        /* Création des panels */
        /*-------------------- */

        //Création du panel central (c'est juste un textArea donc pas besoin de créer une classe)
        this.textArea = new JTextPane();

        //Si on ouvre un fichier, on met son contenu dans le textArea
        if (this.ctrl.getContenuFichier() != null) {
            
            this.textArea.setText(this.ctrl.getContenuFichier());
            System.out.println("contenu fichier : " + this.ctrl.getContenuFichier());
        }

        this.textArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                updateTextColor();
            }
        });

        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Création des autres panels
        this.panelGauche = new PanelGauche  (this.ctrl);
        this.panelDroite = new PanelReseau  (this.ctrl);
        this.panelHaut   = new PanelHaut    (this.ctrl);
        this.panelBas    = new PanelBas     (this.ctrl);

        //Création des attributs pour le textArea
        this.styleContext = new StyleContext();
        this.greenAttributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GREEN);
        this.defaultAttributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);
        this.textArea.setCharacterAttributes(defaultAttributeSet, true);


        //Ajout des panels à la frame
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(this.panelGauche, BorderLayout.WEST);
        this.add(this.panelDroite, BorderLayout.EAST);
        this.add(this.panelHaut, BorderLayout.NORTH);
        this.add(this.panelBas, BorderLayout.SOUTH);


        this.setVisible(true);
    }

	public void enregistrer() 
	{
        JFileChooser choose = new JFileChooser(".");
        choose.setDialogTitle("Enregistrer un fichier");
        choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (choose.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;	
    }

    private void updateTextColor() {
        textArea.setCharacterAttributes(greenAttributeSet, false);
 
    }
    
    public String getContenuTextArea() {return this.textArea.getText();}
    public void setContenu(String contenu){this.textArea.setText(contenu);}

    public void majIHM() {
        this.panelDroite.repaint();
        this.textArea.repaint();
    }

    
}