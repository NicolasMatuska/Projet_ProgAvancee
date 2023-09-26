package ihm.application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PanelHaut extends JPanel implements ActionListener {
    
    public PanelHaut (){
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 

        this.setPreferredSize(new Dimension((int)tailleEcran.getWidth(), (int)tailleEcran.getHeight()/20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
