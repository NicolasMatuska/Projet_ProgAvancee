package ihm.application;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.ControleurEditeur;

import java.awt.Color;
import java.awt.Dimension;


public class PanelGauche extends JPanel implements ActionListener{
    private ControleurEditeur ctrl;
    private JButton btnValider, btnAnnuler;
    
    public PanelGauche(ControleurEditeur ctrl){
        this.ctrl = ctrl;
        this.setBackground(new Color(230, 228, 225));
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth()/6, (int)tailleEcran.getHeight()));
        this.btnValider = new JButton("Valider");        this.btnValider = new JButton("Valider");
        this.btnAnnuler = new JButton("Annuler");
        this.add(this.btnValider);
        this.add(this.btnAnnuler);
        this.btnValider.addActionListener(this);
        this.btnAnnuler.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnValider){

        }

        if (e.getSource() == this.btnAnnuler){
            
        }
    }

}
