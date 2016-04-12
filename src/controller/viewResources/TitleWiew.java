package controller.viewResources;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class TitleWiew {
    
    static LwjglApplication app;
    
    public static void title() {
        
        final JFrame frame = new JFrame("PokeJavaMon");
        JPanel pane = new JPanel();
        JButton button1 = new JButton("NUOVA PARTITA");
        JButton button2 = new JButton("CONTINUA");
        JTextArea text = new JTextArea("Benvenuto in PokeJavaMon!!!");
        pane.add(text);
        pane.setPreferredSize(new Dimension(400,200));
        text.setOpaque(true);
        text.setEditable(false);
        pane.add(button1);
        pane.add(button2);
        frame.add(pane);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500,300));
        
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
                cfg.title = "PokeJavaMon";
                cfg.useGL20 = true;
                cfg.width = 1280;
                cfg.height = 720;
                
                TiledMapGame tl = new TiledMapGame(true);
                
                app = new LwjglApplication(tl, cfg);
                
                tl.setApp(app);
                frame.dispose();
            }
        });
        
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
                cfg.title = "PokeJavaMon";
                cfg.useGL20 = true;
                cfg.width = 1280;
                cfg.height = 720;
                
                TiledMapGame tl = new TiledMapGame(false);
                
                app = new LwjglApplication(tl, cfg);
                
                tl.setApp(app);
                frame.dispose();
            }
        });
    }
}
