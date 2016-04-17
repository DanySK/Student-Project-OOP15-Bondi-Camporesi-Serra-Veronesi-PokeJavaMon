package view.frames;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

import controller.MainController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.parameters.State;
import view.resources.TitleWiew;
import view.resources.ViewController;  
  
public class InserisciNome {  
	
    private JFrame f = new JFrame("PokeJavaMon");
    private JTextField nickname = new JTextField();
    private SecondMenuKeyboardController k;
    private JButton inizia = new JButton("Inizia");
    
    public InserisciNome(KeyListener k) {
		this.k = (SecondMenuKeyboardController) k;
		f.addKeyListener(k);
		nickname.addKeyListener(k);
		this.k.setFrame(f);
		this.k.setText(nickname);
		this.k.setButton(inizia);
		nickname.requestFocusInWindow();
		f.setFocusable(true);
		f.setResizable(false);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(TitleWiew.class.getResource("/POKEPALLA.png").getPath()));
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
    	nickname.setBounds(102, 111, 190, 22);
    	f.getContentPane().add(nickname);
    	nickname.setColumns(40);    	
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