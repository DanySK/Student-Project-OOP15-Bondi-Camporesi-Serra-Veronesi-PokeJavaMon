package view.resources;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import controller.parameters.FilePath;
import controller.parameters.State;

public class TitleWiew {
    
    private JFrame frame;
    
    public TitleWiew() {
        frame = new JFrame("PokeJavaMon");
    }
    
    public void title() {
        
        frame.setResizable(false);
        frame.setLocation(100, 100);
        try {
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FilePath.PALLA.getAbsolutePath()));
        } catch (Exception e) {
        	//TODO: Fare catch di una semplice Exception e' sbagliato
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(FilePath.PALLA.getResourcePath()).getPath()));
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
                Controller.getController().updateStatus(State.SECOND_MENU);
                Controller.getController().getViewController().secondMenu();
            }
        });
        pane.add(nuova);
        JButton continua = new JButton("CONTINUA");
        continua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Controller.getController().getViewController().map(false);
                Controller.getController().updateStatus(State.WALKING);
                frame.dispose();
            }
        });
        if (!Controller.getController().saveExists()) {
            continua.setEnabled(false);
        }
        pane.add(continua);
        frame.add(pane);
        frame.setVisible(true);
    }
}
