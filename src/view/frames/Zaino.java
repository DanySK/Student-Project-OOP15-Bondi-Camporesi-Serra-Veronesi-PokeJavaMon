package view.frames;

import java.awt.event.*;  
import javax.swing.*;

import controller.ViewController;

public class Zaino {

public Zaino() {
		
		final JFrame f = new JFrame("Zaino");
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setResizable(false);
		f.setAlwaysOnTop(true);         
		f.setUndecorated(true);
		f.setBounds(100, 100, 350, 600);
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
    			
		JButton uscita = new JButton("Uscita");
    	f.getContentPane().add(uscita);
    	uscita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				/*ViewController.getController().showMenu();*/
			}
		});
				
    	JButton fight = new JButton("Fight");
    	f.getContentPane().add(fight);
    	fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				ViewController.getController().fightScreen(null);
			}
		});
    	f.setVisible(true);
    	}
}