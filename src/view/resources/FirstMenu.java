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
    private JButton continueGame;
    
    public FirstMenu() {
        this.frame = new JFrame("PokeJavaMon");
    }
    
    public void title() {
        
        this.frame.setResizable(false);
        try {
            this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Img.PALLA.getAbsolutePath()));
        } catch (Exception e) {
            //TODO: Fare catch di una semplice Exception e' sbagliato
            this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(Img.PALLA.getResourcePath()).getPath()));
        }
        this.pane = new JPanel();
        this.text = new JLabel("Welcome to PokeJavaMon!!!");
        this.pane.add(this.text);
        this.pane.setPreferredSize(new Dimension(400,200));
        this.text.setOpaque(false);
        this.frame.setMinimumSize(new Dimension(500,300));
        this.frame.setFocusable(true);
        this.newGame = new JButton("NEW GAME");
        this.newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainController.getController().updateStatus(State.SECOND_MENU);
                MainController.getController().getViewController().secondMenu();
            }
        });
        this.pane.add(this.newGame);
        this.continueGame = new JButton("CONTINUE");
        this.continueGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainController.getController().getViewController().map(false);
                frame.dispose();
            }
        });
        if (!MainController.getController().saveExists()) {
            this.continueGame.setEnabled(false);
        }
        this.pane.add(this.continueGame);
        this.frame.add(this.pane);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}