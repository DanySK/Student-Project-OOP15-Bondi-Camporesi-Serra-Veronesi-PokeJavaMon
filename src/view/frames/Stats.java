package view.frames;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import controller.ViewController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Stats {
	
	public Stats() { {
		
		final JFrame frame = new JFrame("Statistiche");		
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		
		frame.setBounds(100, 100, 450, 300);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JTextArea Ruotadifuo = new JTextArea("RuotaDiFuo");
		Ruotadifuo.setFont(new Font("Arial Black", Font.BOLD, 13));
		Ruotadifuo.setBounds(121, 130, 98, 22);
		frame.getContentPane().add(Ruotadifuo);
		
		JTextArea Destinnob = new JTextArea("Destinnob.");
		Destinnob.setFont(new Font("Arial Black", Font.BOLD, 13));
		Destinnob.setBounds(121, 163, 98, 22);
		frame.getContentPane().add(Destinnob);
		
		JTextArea Spichico = new JTextArea("Spichico");
		Spichico.setFont(new Font("Arial Black", Font.BOLD, 13));
		Spichico.setBounds(121, 229, 98, 22);
		frame.getContentPane().add(Spichico);
		
		JTextArea Splash = new JTextArea("Splash");
		Splash.setFont(new Font("Arial Black", Font.BOLD, 13));
		Splash.setBounds(121, 196, 98, 22);
		frame.getContentPane().add(Splash);
		
		JTextArea Hp = new JTextArea("HP");
		Hp.setOpaque(false);
		Hp.setFont(new Font("Arial Black", Font.BOLD, 13));
		Hp.setBounds(292, 130, 37, 22);
		frame.getContentPane().add(Hp);
		
		JTextArea Atk = new JTextArea("Atk");
		Atk.setOpaque(false);
		Atk.setFont(new Font("Arial Black", Font.BOLD, 13));
		Atk.setBounds(292, 163, 37, 22);
		frame.getContentPane().add(Atk);
		
		JTextArea Def = new JTextArea("DEf");
		Def.setOpaque(false);
		Def.setFont(new Font("Arial Black", Font.BOLD, 13));
		Def.setBounds(292, 196, 37, 22);
		frame.getContentPane().add(Def);
		
		JTextArea Spd = new JTextArea("Spd");
		Spd.setOpaque(false);
		Spd.setFont(new Font("Arial Black", Font.BOLD, 13));
		Spd.setBounds(292, 229, 37, 22);
		frame.getContentPane().add(Spd);
		
		JTextArea getHP = new JTextArea("236");
		getHP.setFont(new Font("Arial Black", Font.BOLD, 13));
		getHP.setBounds(393, 130, 31, 22);
		frame.getContentPane().add(getHP);
		
		JTextArea getAtk = new JTextArea("236");
		getAtk.setFont(new Font("Arial Black", Font.BOLD, 13));
		getAtk.setBounds(393, 163, 31, 22);
		frame.getContentPane().add(getAtk);
		
		JTextArea getDef = new JTextArea("236");
		getDef.setFont(new Font("Arial Black", Font.BOLD, 13));
		getDef.setBounds(393, 196, 31, 22);
		frame.getContentPane().add(getDef);
		
		JTextArea getSpd = new JTextArea("236");
		getSpd.setFont(new Font("Arial Black", Font.BOLD, 13));
		getSpd.setBounds(393, 229, 31, 22);
		frame.getContentPane().add(getSpd);
		
		JTextArea Mossa1 = new JTextArea("Mossa 1");
		Mossa1.setOpaque(false);
		Mossa1.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa1.setBounds(21, 131, 70, 22);
		frame.getContentPane().add(Mossa1);
		
		JTextArea Mossa2 = new JTextArea("Mossa 2");
		Mossa2.setOpaque(false);
		Mossa2.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa2.setBounds(21, 164, 70, 22);
		frame.getContentPane().add(Mossa2);
		
		JTextArea Mossa3 = new JTextArea("Mossa 3");
		Mossa3.setOpaque(false);
		Mossa3.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa3.setBounds(21, 197, 70, 22);
		frame.getContentPane().add(Mossa3);
		
		JTextArea Mossa4 = new JTextArea("Mossa 4");
		Mossa4.setOpaque(false);
		Mossa4.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa4.setBounds(21, 230, 70, 22);
		frame.getContentPane().add(Mossa4);
		
		JTextArea NomePokemon = new JTextArea("NomePokemon");
		NomePokemon.setHighlighter(null);
		NomePokemon.setEditable(false);
		NomePokemon.setOpaque(false);
		NomePokemon.setFont(new Font("Arial Black", Font.BOLD, 18));
		NomePokemon.setBounds(78, 27, 275, 30);
		frame.getContentPane().add(NomePokemon);
		
		JTextArea Lvl = new JTextArea("Lvl:");
		Lvl.setOpaque(false);
		Lvl.setHighlighter(null);
		Lvl.setEditable(false);
		Lvl.setFont(new Font("Arial Black", Font.BOLD, 13));
		Lvl.setBounds(163, 82, 37, 22);
		frame.getContentPane().add(Lvl);
		
		JTextArea cento = new JTextArea("100");
		cento.setFont(new Font("Arial Black", Font.BOLD, 13));
		cento.setBounds(232, 82, 37, 22);
		frame.getContentPane().add(cento);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(352, 11, 82, 22);
		frame.getContentPane().add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				ViewController.getController();
                ViewController.team();
			}	
		});
		frame.setVisible(true);
	}}
	}
	