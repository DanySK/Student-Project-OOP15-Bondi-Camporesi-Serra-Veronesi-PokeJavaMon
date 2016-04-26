package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.MainController;
import controller.ViewController;
import controller.parameters.State;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu  {
	
public Menu() {
		final JFrame f = new JFrame();
		
		f.setResizable(false);
		f.setAlwaysOnTop(true);	
		f.setUndecorated(true);
		f.setBounds(100, 100, 180, 310);
		f.getContentPane().setLayout(null);
	
		JButton squadra = new JButton  ("Squadra");
		squadra.setBounds(30, 84, 120, 30);
		f.getContentPane().add(squadra);
		squadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewController.getController();
                /*f.dispose();*/
 				ViewController.team();
			}});
		
		JButton salva = new JButton("Salva");
		salva.setBounds(30, 196, 120, 30);
		f.getContentPane().add(salva);
		salva.addMouseListener(new MouseAdapter() {
			@Override
        	public void mouseClicked(MouseEvent e) {
 			ViewController.getController().save();
        		JOptionPane.showMessageDialog(salva, "Salvataggio riuscito!");
        	}
        });
		
		JButton box = new JButton("Box");
		box.setBounds(30, 28, 120, 30);
		f.getContentPane().add(box);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				/*f.dispose();*/
 				ViewController.getController().box();
			}});
		
		JButton zaino = new JButton("Zaino");
		zaino.setBounds(30, 140, 120, 30);
		f.getContentPane().add(zaino);	
		zaino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewController.getController();
                /*f.dispose();*/
 				ViewController.bag();
			}});
		
		JButton esci = new JButton("Riprendi");
		esci.setBounds(30, 252, 120, 30);
		f.getContentPane().add(esci);
		esci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
 				MainController.getController().updateStatus(State.WALKING);
			}});
		
		f.setVisible(true);
	}
}
