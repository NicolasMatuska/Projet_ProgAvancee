package ihm.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.ControleurEditeur;

public class PanelHaut extends JPanel implements ActionListener {

    private ControleurEditeur ctrl;
    private JLabel lblInformationsModifs;

    public PanelHaut(ControleurEditeur ctrl) {
        this.ctrl = ctrl;
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth(), (int)tailleEcran.getHeight()/20));

        this.lblInformationsModifs = new JLabel("Modification de ....");
        this.add(this.lblInformationsModifs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
