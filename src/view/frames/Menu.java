package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import view.resources.TitleWiew;
import view.resources.ViewController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
/*import com.badlogic.gdx.Input.Keys;*/

public class Menu  {

	public Menu() {
		final JFrame f = new JFrame();
		
		f.setResizable(false);
		f.setAlwaysOnTop(true);

		f.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/POKEPALLA.png"));
	
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setSize(179,310);
		f.getContentPane().setLayout(null);
	
		JButton squadra = new JButton  ("Squadra");
		squadra.setBounds(30, 75, 100, 25);
		f.getContentPane().add(squadra);
		squadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new Squadra();
			}});
		
		JButton salva = new JButton("Salva");
		salva.setBounds(30, 165, 100, 25);
		f.getContentPane().add(salva);
		salva.addMouseListener(new MouseAdapter() {
			@Override
        	public void mouseClicked(MouseEvent e) {
			ViewController.save();
        		JOptionPane.showMessageDialog(salva, "Salvataggio riuscito!");
        	}
        });
		
		JButton box = new JButton("Box");
		box.setBounds(30, 30, 103, 25);
		f.getContentPane().add(box);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new Box();
			}});
		
		JButton zaino = new JButton("Zaino");
		zaino.setBounds(30, 120, 100, 25);
		f.getContentPane().add(zaino);	
		zaino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new Zaino();
			}});
		
		JButton esci = new JButton("Riprendi");
		esci.setBounds(30, 210, 100, 25);
		f.getContentPane().add(esci);
		esci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				ViewController.resume();
			}});
		
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new TitleWiew();
	}
}