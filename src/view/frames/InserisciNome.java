package view.frames;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import controller.MainController;
import controller.ViewController;
import controller.parameters.FilePath;
import controller.parameters.State;  
  
public class InserisciNome {  
	
    private JFrame f = new JFrame("PokeJavaMon");
    private static JTextField nickname = new JTextField();
    private JButton inizia = new JButton("Inizia");
    private static String s;
    
    public static String getPlayerName() {
        s = nickname.getText();
        return s;
    }
    
    public InserisciNome() {
		nickname.requestFocusInWindow();
		f.setFocusable(true);
		f.setResizable(false);
		try {
		    f.setIconImage(Toolkit.getDefaultToolkit().getImage(FilePath.PALLA.getAbsolutePath()));
		} catch (Exception e) {
		    e.printStackTrace();
		    f.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(FilePath.PALLA.getResourcePath()).getPath()));
		}
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 400, 300);
		f.getContentPane().setLayout(null);

		JLabel inserisciNome = new JLabel();
    	inserisciNome.setBackground(SystemColor.control);
    	inserisciNome.setFont(new Font("Elephant", Font.PLAIN, 16));
    	inserisciNome.setText("Nome (4 - 20 [blazeit])");
    	inserisciNome.setBounds(102, 41, 190, 35);
    	f.getContentPane().add(inserisciNome);
    	nickname.setBounds(102, 111, 190, 22);
    	f.getContentPane().add(nickname);
    	nickname.setColumns(40);    	
    	inizia.setBounds(144, 158, 110, 25);
    	f.getContentPane().add(inizia);
		
    	inizia.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			s = nickname.getText();
    	        if (s.length() < 4 || s.length() > 20) {
    	        	JOptionPane.showMessageDialog(inizia, "You Naive Idiot");
    	           }
    	        else {
    	        ViewController.getController().setName(nickname.getText());
                ViewController.getController().map(true);
                MainController.getController().updateStatus(State.WALKING);
                f.dispose();
    	        }
    		}});
		f.setVisible(true);
		}
	}