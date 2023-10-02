package ihm.application;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.ControleurEditeur;

import java.awt.Color;
import java.awt.Dimension;


public class PanelGauche extends JPanel implements ActionListener{
    private ControleurEditeur ctrl;
    
    public PanelGauche(ControleurEditeur ctrl){
        this.ctrl = ctrl;
        this.setBackground(new Color(230, 228, 225));
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth()/6, (int)tailleEcran.getHeight()));

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("1");
    }

}
