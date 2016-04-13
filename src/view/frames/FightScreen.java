package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightScreen {
	
	public FightScreen() {
		
		final JFrame frame = new JFrame("Fight");
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/POKEPALLA.png"));
		
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "Pannello1");
		panel.setLayout(null);
		
		JButton attacca = new JButton("Attacca");
		attacca.setBounds(224, 178, 105, 42);
		panel.add(attacca);
		
		JButton squadra = new JButton("Squadra");
		squadra.setBounds(329, 178, 105, 42);
		panel.add(squadra);
		
		JButton zaino = new JButton("Zaino");
		zaino.setBounds(224, 220, 105, 42);
		panel.add(zaino);
		
		/* rinominare in fuga*/
		JButton fuga = new JButton("Esci");
		fuga.setBounds(329, 220, 105, 42);
		panel.add(fuga);
		fuga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				new Menu();
			}
		});
				
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "Pannello2");
		panel_1.setLayout(null);
		
		JButton mossa1 = new JButton("Mossa 1");
		mossa1.setBounds(0, 178, 102, 42);
		panel_1.add(mossa1);
		
		JButton mossa2 = new JButton("Mossa 2");
		mossa2.setBounds(101, 178, 102, 42);
		panel_1.add(mossa2);
		
		JButton mossa3 = new JButton("Mossa 3");
		mossa3.setBounds(0, 220, 102, 42);
		panel_1.add(mossa3);
		
		JButton mossa4 = new JButton("Mossa 4");
		mossa4.setBounds(101, 220, 102, 42);
		panel_1.add(mossa4);
		
		frame.setVisible(true);
	}
}
