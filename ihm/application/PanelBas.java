package ihm.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurEditeur;

public class PanelBas extends JPanel implements ActionListener 
{
    private JTextField txtIP;
    private JButton btnJoinServer;

    private ControleurEditeur ctrl;

    public PanelBas(ControleurEditeur ctrl) {

        this.ctrl = ctrl;

        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        
        // Création des composants
        JLabel lblIP = new JLabel("IP du salon : ");
        this.txtIP = new JTextField(15);

        this.btnJoinServer = new JButton("Rejoindre le serveur");

        // Ajout des composants
        this.add(lblIP);
        this.add(this.txtIP);
        this.add(new JLabel("   "));
        this.add(this.btnJoinServer);

        // Activation des composants
        this.btnJoinServer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnJoinServer) {
            System.out.println("Join server");
        }

    }
}
