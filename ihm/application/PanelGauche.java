package ihm.application;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.ControleurEditeur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;


public class PanelGauche extends JPanel implements ActionListener{
    private ControleurEditeur ctrl;
    private JButton btnValider, btnAnnuler, btnCopier;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    
    public PanelGauche(ControleurEditeur ctrl){
        this.ctrl = ctrl;
        this.setBackground(new Color(230, 228, 225));
        this.setLayout(new BorderLayout());
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setPreferredSize(new Dimension((int) tailleEcran.getWidth() / 5, (int) tailleEcran.getHeight()));

        this.textArea = new JTextArea();
        // this.textArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout(1, 2));
        this.btnCopier = new JButton("Copier le texte");
        this.btnValider = new JButton("Envoyer nouvelle version");
        this.btnAnnuler = new JButton("Recevoir version actuelle");

        panelBtn.add(this.btnValider);
        panelBtn.add(this.btnAnnuler);
        this.add(panelBtn, BorderLayout.NORTH);
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(this.btnCopier, BorderLayout.SOUTH);

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
