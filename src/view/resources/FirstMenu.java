package view.resources;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainController;
import controller.parameters.Img;
import controller.parameters.State;

public class FirstMenu {
    
    private JFrame frame;
    
    public FirstMenu() {
        frame = new JFrame("PokeJavaMon");
    }
    
    public void title() {
        
        frame.setResizable(false);
        //frame.setLocation(100, 100);
        try {
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Img.PALLA.getAbsolutePath()));
        } catch (Exception e) {
            //TODO: Fare catch di una semplice Exception e' sbagliato
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(Img.PALLA.getResourcePath()).getPath()));
        }
        JPanel pane = new JPanel();
        JLabel text = new JLabel("Benvenuto in PokeJavaMon!!!");
        pane.add(text);
        pane.setPreferredSize(new Dimension(400,200));
        text.setOpaque(false);
        frame.setMinimumSize(new Dimension(500,300));
        frame.setFocusable(true);
        JButton nuova = new JButton("NUOVA PARTITA");
        nuova.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainController.getController().updateStatus(State.SECOND_MENU);
                MainController.getController().getViewController().secondMenu();
            }
        });
        pane.add(nuova);
        JButton continua = new JButton("CONTINUA");
        continua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainController.getController().getViewController().map(false);
                frame.dispose();
            }
        });
        if (!MainController.getController().saveExists()) {
            continua.setEnabled(false);
        }
        pane.add(continua);
        frame.add(pane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}