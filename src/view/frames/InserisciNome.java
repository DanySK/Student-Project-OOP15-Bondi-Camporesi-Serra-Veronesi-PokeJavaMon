package view.frames;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import view.resources.TiledMapGame;
import view.resources.ViewController;  
  
public class InserisciNome {  
	
	static LwjglApplication app;
	
	public InserisciNome() {
		
		final JFrame f = new JFrame("PokeJavaMon");
		f.setResizable(false);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Pictures\\POKEPALLA.png"));
         
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		f.getContentPane().setLayout(null);

		JTextArea inserisciNome = new JTextArea();
		inserisciNome.setHighlighter(null);
		inserisciNome.setEditable(false);
    	inserisciNome.setBackground(SystemColor.control);
    	inserisciNome.setFont(new Font("Elephant", Font.PLAIN, 16));
    	inserisciNome.setText("Nome (4 - 20 [blazeit])");
    	inserisciNome.setBounds(102, 41, 190, 35);
    	f.getContentPane().add(inserisciNome);
    	
    	JTextField nickname = new JTextField();
    	nickname.setBounds(102, 111, 190, 22);
    	f.getContentPane().add(nickname);
    	nickname.setColumns(40);    	

    	JButton inizia = new JButton("Inizia");
    	inizia.setBounds(144, 158, 110, 25);
    	f.getContentPane().add(inizia);
		
    	inizia.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String s = nickname.getText();
    	        if (s.length() < 4 || s.length() > 20) {
    	        	JOptionPane.showMessageDialog(null, "You Naive Idiot");
    	           }
    	        else {
    	        
    	        ViewController.setName(nickname.getText());
                LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
                
                /*cfg.addIcon(  ,  );*/
                
                cfg.title = "PokeJavaMon";
                cfg.useGL20 = true;
                cfg.width = 1280;
                cfg.height = 720;
                
    		/*public void actionPerformed(ActionEvent ae) {
				/*f.dispose();
				new Mappa();*/
				TiledMapGame tl = new TiledMapGame(true);
                
                app = new LwjglApplication(tl, cfg);
                
                tl.setApp(app);
                f.dispose();
    	        }
    		}});
		f.setVisible(true);
		}
	}