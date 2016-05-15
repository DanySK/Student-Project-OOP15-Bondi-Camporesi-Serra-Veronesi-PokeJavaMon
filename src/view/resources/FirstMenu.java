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
    private JPanel pane;
    private JLabel text;
    private JButton newGame;
    private	JButton continueGame;
    
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
        pane = new JPanel();
        text = new JLabel("Benvenuto in PokeJavaMon!!!");
        pane.add(text);
        pane.setPreferredSize(new Dimension(400,200));
        text.setOpaque(false);
        frame.setMinimumSize(new Dimension(500,300));
        frame.setFocusable(true);
        newGame = new JButton("NUOVA PARTITA");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainController.getController().updateStatus(State.SECOND_MENU);
                MainController.getController().getViewController().secondMenu();
            }
        });
        pane.add(newGame);
        continueGame = new JButton("CONTINUA");
        continueGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainController.getController().getViewController().map(false);
                frame.dispose();
            }
        });
        if (!MainController.getController().saveExists()) {
        	continueGame.setEnabled(false);
        }
        pane.add(continueGame);
        frame.add(pane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}