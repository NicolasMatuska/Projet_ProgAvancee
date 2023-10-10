package ihm.application;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurEditeur;

public class PanelBas extends JPanel implements ActionListener {
    private JTextField txtIP, txtUserName;
    private JButton btnJoinServer;
    private ControleurEditeur ctrl;

    public PanelBas(ControleurEditeur ctrl) {

        this.ctrl = ctrl;

        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth(), (int)tailleEcran.getHeight()/20));

        JLabel lblUserName, lblIP, lblEspace;
        lblUserName = new JLabel("Nom d'utilisateur : ");
        lblIP = new JLabel("      IP du salon : ");
        lblEspace = new JLabel("");

        this.txtUserName = new JTextField();
        this.txtIP = new JTextField();
        this.btnJoinServer = new JButton("Rejoindre le serveur");

        this.txtUserName.setPreferredSize(new Dimension((int)tailleEcran.getWidth()/10, (int)tailleEcran.getHeight()/30));
        this.txtIP.setPreferredSize(new Dimension((int)tailleEcran.getWidth()/10, (int)tailleEcran.getHeight()/30));

        this.add(lblUserName);
        this.add(this.txtUserName);
        this.add(lblIP);
        this.add(this.txtIP);
        this.add(new JLabel("   "));
        this.add(this.btnJoinServer);

        this.btnJoinServer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnJoinServer) {
            this.ctrl.joinServer(this.txtUserName.getText(), "224.0.0.1");
        }

    }
}
