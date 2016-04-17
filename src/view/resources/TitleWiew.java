package view.resources;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.MainController;
import controller.keyboard.FirstMenuKeyboardController;
import controller.parameters.State;

public class TitleWiew {
    
    private JFrame frame;
    private FirstMenuKeyboardController k;
    
    public TitleWiew(KeyListener k) {
        frame = new JFrame("PokeJavaMon");
        this.k = (FirstMenuKeyboardController) k;
        frame.addKeyListener(k);
        this.k.setFrame(frame);
    }
    
    public void title() {
        
        frame.setResizable(false);
        frame.setLocation(100, 100);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(TitleWiew.class.getResource("/POKEPALLA.png").getPath()));
        JPanel pane = new JPanel();
        JTextArea text = new JTextArea("Benvenuto in PokeJavaMon!!!");
        pane.add(text);
        pane.setPreferredSize(new Dimension(400,200));
        text.setOpaque(false);
        text.setEditable(false);
        text.setHighlighter(null);	
	frame.add(pane);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500,300));
        frame.setFocusable(true);
        JButton nuova = new JButton("NUOVA PARTITA");
        pane.add(nuova);
        nuova.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainController.updateStatus(State.SECOND_MENU);
                ViewController.secondMenu((KeyListener) MainController.getController());
            }
        });
        
        JButton continua = new JButton("CONTINUA");
        pane.add(continua);
        continua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewController.map(false);
                MainController.updateStatus(State.WALKING);
                frame.dispose();
            }
        });
    }
}
