package ihm.application;
 
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.ControleurEditeur;

import java.awt.Color;
import java.awt.Dimension;


public class PanelCentral extends JPanel implements ActionListener{

    private ControleurEditeur ctrl;
    private JTextPane textArea;
    private JScrollPane scrollPane;

    public PanelCentral(ControleurEditeur ctrl){

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 

        this.ctrl = ctrl;
        this.textArea = new JTextPane();
        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);        
        this.textArea.setPreferredSize(new Dimension((int)(tailleEcran.getWidth()*0.5), (int)(tailleEcran.getHeight()*0.7)));
        System.out.println(this.getWidth() + " " + this.getHeight());

        this.add(this.scrollPane);
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println("2");
    }
    
}
