package view.frames;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

import controller.MainController;
import controller.parameters.State;
import view.resources.ViewController;  
  
public class InserisciNome {  
	
    public InserisciNome() {
		
		final JFrame f = new JFrame("PokeJavaMon");
		f.setResizable(false);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/POKEPALLA.png"));
         
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 400, 300);
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
    	        	JOptionPane.showMessageDialog(inizia, "You Naive Idiot");
    	           }
    	        else {
    	        
    	        ViewController.setName(nickname.getText());
                ViewController.map(true);
                MainController.updateStatus(State.WALKING);
                f.dispose();
    	        }
    		}});
		f.setVisible(true);
		}
	}