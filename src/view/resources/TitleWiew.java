package view.resources;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.MainController;
import controller.ViewController;
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
            e.printStackTrace();
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(FilePath.PALLA.getResourcePath()).getPath()));
        }
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
                MainController.getController().updateStatus(State.SECOND_MENU);
                ViewController.getController().secondMenu();
            }
        });
        
        JButton continua = new JButton("CONTINUA");
        pane.add(continua);
        continua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewController.getController().map(false);
                MainController.getController().updateStatus(State.WALKING);
                frame.dispose();
            }
        });
    }
}
