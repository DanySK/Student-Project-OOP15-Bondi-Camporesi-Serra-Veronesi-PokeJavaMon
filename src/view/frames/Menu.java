package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Box;
import controller.Controller;
import controller.parameters.State;
import model.player.PlayerImpl;
import view.resources.MessageFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class Menu  {
	
public Menu() {
		final JWindow f = new JWindow();
		
		f.setAlwaysOnTop(true);	

		f.setBounds(100, 100, 180, 310);

		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		f.add(Box.createVerticalGlue());

		JButton box = new JButton("Box");
		box.setAlignmentX(Component.CENTER_ALIGNMENT);
		f.getContentPane().add(box);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				/*f.dispose();*/
 				if (PlayerImpl.getPlayer().getBox().getBoxSize() < 1) {
 				    new MessageFrame(null, "NO PKMN IN BOX");
 				} else {
 				   Controller.getController().getViewController().box();
 				}
			}});
		
		f.add(Box.createVerticalGlue());
		
		JButton team = new JButton  ("Team");
		team.setAlignmentX(Component.CENTER_ALIGNMENT);
		f.getContentPane().add(team);
		team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
 				Controller.getController().getViewController().team(true, false);
			}});
		
		f.add(Box.createVerticalGlue());
			
		JButton bag = new JButton("Bag");
		bag.setAlignmentX(Component.CENTER_ALIGNMENT);
		f.getContentPane().add(bag);	
		bag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
 				Controller.getController().getViewController().bag();
			}});
		
		f.add(Box.createVerticalGlue());
		
		JButton save = new JButton("Save");
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		f.getContentPane().add(save);
		save.addMouseListener(new MouseAdapter() {
			@Override
        	public void mouseClicked(MouseEvent e) {
 			Controller.getController().getViewController().save();
 				new MessageFrame(null, "Salvataggio riuscito!");
        	}
        });
		
		f.add(Box.createVerticalGlue());
		
		JButton resume = new JButton("Resume");
		resume.setAlignmentX(Component.CENTER_ALIGNMENT);
		f.getContentPane().add(resume);
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
 				Controller.getController().updateStatus(State.WALKING);
			}});
		
		f.add(Box.createVerticalGlue());
		f.setVisible(true);
	}
}
