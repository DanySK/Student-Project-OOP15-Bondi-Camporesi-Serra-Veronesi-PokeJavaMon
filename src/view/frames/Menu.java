package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.parameters.State;
import controller.status.StatusController;
import controller.view.ViewController;
import model.player.PlayerImpl;
import view.resources.MessageFrame;

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
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		JButton squadra = new JButton  ("Squadra");
		squadra.setBounds(30, 84, 120, 30);
		f.getContentPane().add(squadra);
		squadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewController.getController();
                /*f.dispose();*/
 				ViewController.getController().team(true, false);
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
 				if (PlayerImpl.getPlayer().getBox().getBoxSize() < 1) {
 				    new MessageFrame("NO PKMN IN BOX", null);
 				} else {
 				   ViewController.getController().box();
 				}
			}});
		
		JButton zaino = new JButton("Zaino");
		zaino.setBounds(30, 140, 120, 30);
		f.getContentPane().add(zaino);	
		zaino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewController.getController();
                /*f.dispose();*/
 				ViewController.getController().bag();
			}});
		
		JButton esci = new JButton("Riprendi");
		esci.setBounds(30, 252, 120, 30);
		f.getContentPane().add(esci);
		esci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
 				StatusController.getController().updateStatus(State.WALKING);
			}});
		
		f.setVisible(true);
	}
}
