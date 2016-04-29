package view.frames;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import javax.swing.border.LineBorder;  
  
public class Box {

public Box() {
	final JFrame f = new JFrame("Box");
	
		f.setAlwaysOnTop(true);
		f.setResizable(false);
		f.setBounds(100, 100, 450, 300);
		f.setUndecorated(true);
		f.getContentPane().setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton deposita = new JButton("Deposita");
		deposita.setBounds(170, 88, 89, 23);
		f.getContentPane().add(deposita);
		
		JButton ritira = new JButton("Ritira");
		ritira.setBounds(170, 122, 89, 23);
		f.getContentPane().add(ritira);
		
		JButton esci = new JButton("Esci");
		esci.setBounds(170, 228, 89, 23);
		f.getContentPane().add(esci);
		
		JInternalFrame box = new JInternalFrame("Box");
		box.setVisible(true);
		box.setBounds(281, 11, 153, 250);
		f.getContentPane().add(box);
		
		JPanel squadra = new JPanel();
		squadra.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		squadra.setBounds(22, 21, 124, 240);
		f.getContentPane().add(squadra);
		squadra.setLayout(null);
		
		esci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				/*ViewController.getController().showMenu();*/
				}
		});
		f.setVisible(true);
	}
}