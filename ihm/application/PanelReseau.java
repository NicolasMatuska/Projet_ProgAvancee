package ihm.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import controleur.ControleurEditeur;
import metier.Utilisateur;

public class PanelReseau extends JPanel 
{
    private JList<String> lstUtilisateurs;
    private DefaultListModel<String> utilisateurModel;
    private List<Utilisateur> utilisateurs;
    
    private ControleurEditeur ctrl;


    public PanelReseau(ControleurEditeur ctrl) 
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Création de la liste des utilisateurs
        this.initListeUtilisateurs();

        JLabel lblUtilisateurs = new JLabel("Utilisateurs connectés");

        //Ajout des composants
        this.add(lblUtilisateurs, BorderLayout.NORTH);


    }

    private void initListeUtilisateurs() {

        // Création de la liste des utilisateurs
        this.utilisateurModel = new DefaultListModel<String>();
        this.lstUtilisateurs = new JList<String>(this.utilisateurModel);

        // Paramétrage de la liste des utilisateurs
        this.lstUtilisateurs.setLayoutOrientation(JList.VERTICAL);
        this.lstUtilisateurs.setVisibleRowCount(-1); // -1 pour afficher tous les éléments
        this.lstUtilisateurs.setFixedCellWidth(200);
        this.lstUtilisateurs.setFixedCellHeight(20);

        // Paramétrage du scrollPane
        JScrollPane scrollPane = new JScrollPane(this.lstUtilisateurs);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(200, 200));


    }

    public void majIHM(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                utilisateurModel.clear();
                for (Utilisateur u : ctrl.getUtilisateurs()) {
                    utilisateurModel.addElement(u.getPseudo());
                }
            }
        });
    }
}