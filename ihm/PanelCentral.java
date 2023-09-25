package ihm;
 
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Controleur;

import java.awt.Color;

public class PanelCentral extends JPanel implements ActionListener{
    private Controleur ctrl;
    private JTextArea textArea;

    public PanelCentral(Controleur ctrl){

        this.ctrl = ctrl;
        this.textArea = new JTextArea();

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.add(this.textArea);
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println("2");
    }
    
}
