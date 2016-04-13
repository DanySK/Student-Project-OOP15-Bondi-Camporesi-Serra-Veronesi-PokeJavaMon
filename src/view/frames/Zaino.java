package view.frames;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class Zaino {

public Zaino() {
		
		final JFrame f = new JFrame("Zaino");
	
		f.setResizable(true);
		f.setAlwaysOnTop(true);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/POKEPALLA.png"));
         
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(350,600);
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
    			
		JButton uscita = new JButton("Uscita");
    	f.getContentPane().add(uscita);
    	uscita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new Menu();
			}
		});
				
    	JButton fight = new JButton("Fight");
    	f.getContentPane().add(fight);
    	fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new FightScreen();
			}
		});
    	f.setVisible(true);
    	}
}