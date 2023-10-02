package ihm.reseau;

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

public class PanelReseau extends JPanel
{
    private JList<String> lstUtilisateurs;
    private DefaultListModel<String> utilisateurModel;

    public PanelReseau()
    {

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(230, 228, 225));
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth()/6, (int)tailleEcran.getHeight()));

        this.utilisateurModel = new DefaultListModel<String>();
        this.lstUtilisateurs = new JList<String>(this.utilisateurModel);
        this.lstUtilisateurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.lstUtilisateurs.setPreferredSize(new Dimension(200, 400));

        JLabel lblUtilisateurs = new JLabel("Utilisateurs connectés");
        JScrollPane scrollPane = new JScrollPane(this.lstUtilisateurs);

        this.add(lblUtilisateurs, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        
    }

    public void majListeUtilisateurs(List<String> utilisateurs)
    {
        SwingUtilities.invokeLater(() -> {
            utilisateurModel.clear();
            for (String utilisateur : utilisateurs) {
                utilisateurModel.addElement(utilisateur);
            }
        });
    }

    public void ajouterUtilisateur(String utilisateur)
    {
        SwingUtilities.invokeLater(() -> {
            utilisateurModel.addElement(utilisateur);
        });
    }


}
