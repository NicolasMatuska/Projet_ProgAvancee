package ihm.application;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.ControleurEditeur;

public class PanelBas extends JPanel implements ActionListener {

    private JButton btnCreerServer;
    private JButton btnJoinServer;
    private ControleurEditeur ctrl;

    public PanelBas(ControleurEditeur ctrl) {

        this.ctrl = ctrl;

        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth(), (int)tailleEcran.getHeight()/20));

        this.btnCreerServer = new JButton("Démarrer le serveur");
        this.btnJoinServer = new JButton("Rejoindre le serveur");

        this.add(this.btnCreerServer);
        this.add(this.btnJoinServer);

        this.btnCreerServer.addActionListener(this);
        this.btnJoinServer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnJoinServer) {
            this.ctrl.joinServer("local", "df");
        }
    }
    
}
