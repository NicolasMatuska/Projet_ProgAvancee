package ihm.application;
 
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.ControleurEditeur;

import java.awt.Color;


public class PanelCentral extends JPanel implements ActionListener{
    private ControleurEditeur ctrl;
    private JTextArea textArea;

    public PanelCentral(ControleurEditeur ctrl){

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        this.ctrl = ctrl;
        this.textArea = new JTextArea();
        this.textArea.setPreferredSize(this.getSize());

        this.add(this.textArea);
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println("2");
    }
    
}
