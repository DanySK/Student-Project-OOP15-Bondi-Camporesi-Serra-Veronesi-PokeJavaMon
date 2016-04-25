package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controller.MainController;
import controller.ViewController;
import controller.parameters.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FightScreen extends JPanel{

	private static final long serialVersionUID = 1L;

	public FightScreen() {
		final JFrame frame = new JFrame("Fight");
		
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 444, 212);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(224, 213, 226, 60);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setVisible(false);
		panel_2.setBounds(0, 213, 450, 60);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setVisible(false);
		panel_3.setBounds(0, 213, 226, 60);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton Fight = new JButton("Fight");
		Fight.setBounds(0, 0, 113, 30);
		Fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
                panel_3.setVisible(true);
			}
		});
		panel_1.add(Fight);
		
		JButton Squadra = new JButton("Squadra");
		Squadra.setBounds(113, 0, 113, 30);
		Squadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewController.team();
			}
		});
		panel_1.add(Squadra);
		
		JButton Zaino = new JButton("Zaino");
		Zaino.setBounds(0, 30, 113, 30);
		panel_1.add(Zaino);
		Zaino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewController.bag();
			}
		});
		
		JButton Fuga = new JButton("Fuga");
		Fuga.setBounds(113, 30, 113, 30);
		panel_1.add(Fuga);
		Fuga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				MainController.getController().updateStatus(State.WALKING);
			}
		});

		JButton Mossa1 = new JButton("Mossa1");
		Mossa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
                panel_3.setVisible(false);
			}
		});
		Mossa1.setBounds(0, 0, 113, 30);
		panel_3.add(Mossa1);
		
		JButton Mossa2 = new JButton("Mossa2");
		Mossa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
                panel_3.setVisible(false);
			}
		});
		Mossa2.setBounds(113, 0, 113, 30);
		panel_3.add(Mossa2);
		
		JButton Mossa3 = new JButton("Mossa3");
		Mossa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
                panel_3.setVisible(false);
			}
		});
		Mossa3.setBounds(0, 30, 113, 30);
		panel_3.add(Mossa3);
		
		JButton Mossa4 = new JButton("Mossa4");
		Mossa4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
                panel_3.setVisible(false);
			}
		});
		Mossa4.setBounds(113, 30, 113, 30);
		panel_3.add(Mossa4);

		JTextArea JTextArea = new JTextArea("Premio invio per continuare il fight!");
		JTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				    if (e.getKeyCode()==KeyEvent.VK_ENTER){
				    	panel_1.setVisible(true);
						panel_2.setVisible(false);
		                panel_3.setVisible(false);
				    }
			}});
		JTextArea.setWrapStyleWord(true);
		JTextArea.setLineWrap(true);
		JTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		JTextArea.setBounds(0, 0, 444, 60);
		panel_2.add(JTextArea);
		
		frame.setVisible(true);
	}
	}

class MyPanel extends JPanel {		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		int width = 150;
		double maxHP = 200; /*get maxHealth*/
		double HP = 50; /*get currentHealth*/
		double Scale = HP / maxHP;
	    g.drawRect (10, 10, width, 10); 
	    g.fillRect(10, 10, (int) (width * Scale), 10);
	    g.drawRect (10, 10, width, 10);
	    
	    
	    double maxHP2 = 200; /*get maxHealth*/
		double HP2 = 75; /*get currentHealth*/
		double Scale2 = HP2 / maxHP2;
	    g.drawRect (282, 110, width, 10); 
	    g.fillRect(282, 110, (int) (width * Scale2), 10);
	    g.drawRect (282, 110, width, 10);
	  }
}
