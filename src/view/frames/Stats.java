package view.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Stats {
	
	public Stats(Pokemon iD) { {
		
		final JFrame frame = new JFrame("Statistiche");		
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		JLabel Ruotadifuo;
		if (iD.getCurrentMoves().get(0) != null) {
		    Ruotadifuo = new JLabel(iD.getCurrentMoves().get(0).name());
		} else {
		    Ruotadifuo = new JLabel("NONE");
		}
		Ruotadifuo.setFont(new Font("Arial Black", Font.BOLD, 13));
		Ruotadifuo.setBounds(121, 130, 98, 22);
		frame.getContentPane().add(Ruotadifuo);
		
		JLabel Destinnob;
		if (iD.getCurrentMoves().get(1) != null) {
		    Destinnob = new JLabel(iD.getCurrentMoves().get(1).name());
                } else {
                    Destinnob = new JLabel("NONE");
                }
		Destinnob.setFont(new Font("Arial Black", Font.BOLD, 13));
		Destinnob.setBounds(121, 163, 98, 22);
		frame.getContentPane().add(Destinnob);
		
		JLabel Spichico;
		if (iD.getCurrentMoves().get(2) != null) {
		    Spichico = new JLabel(iD.getCurrentMoves().get(2).name());
                } else {
                    Spichico = new JLabel("NONE");
                }
		Spichico.setFont(new Font("Arial Black", Font.BOLD, 13));
		Spichico.setBounds(121, 229, 98, 22);
		frame.getContentPane().add(Spichico);
		
		JLabel Splash;
		if (iD.getCurrentMoves().get(3) != null) {
		    Splash = new JLabel(iD.getCurrentMoves().get(3).name());
                } else {
                    Splash = new JLabel("NONE");
                }
		Splash.setFont(new Font("Arial Black", Font.BOLD, 13));
		Splash.setBounds(121, 196, 98, 22);
		frame.getContentPane().add(Splash);
		
		JLabel Hp = new JLabel("HP:");
		Hp.setOpaque(false);
		Hp.setFont(new Font("Arial Black", Font.BOLD, 13));
		Hp.setBounds(292, 130, 37, 22);
		frame.getContentPane().add(Hp);
		
		JLabel Atk = new JLabel("Atk:");
		Atk.setOpaque(false);
		Atk.setFont(new Font("Arial Black", Font.BOLD, 13));
		Atk.setBounds(292, 163, 37, 22);
		frame.getContentPane().add(Atk);
		
		JLabel Def = new JLabel("DEf:");
		Def.setOpaque(false);
		Def.setFont(new Font("Arial Black", Font.BOLD, 13));
		Def.setBounds(292, 196, 37, 22);
		frame.getContentPane().add(Def);
		
		JLabel Spd = new JLabel("Spd:");
		Spd.setOpaque(false);
		Spd.setFont(new Font("Arial Black", Font.BOLD, 13));
		Spd.setBounds(292, 229, 37, 22);
		frame.getContentPane().add(Spd);
		
		JLabel getHP = new JLabel("" + iD.getCurrentHP() + "/" + iD.getStat(Stat.HP));
		getHP.setFont(new Font("Arial Black", Font.BOLD, 13));
		getHP.setBounds(393, 130, 31, 22);
		frame.getContentPane().add(getHP);
		
		JLabel getAtk = new JLabel("" + iD.getStat(Stat.ATK));
		getAtk.setFont(new Font("Arial Black", Font.BOLD, 13));
		getAtk.setBounds(393, 163, 31, 22);
		frame.getContentPane().add(getAtk);
		
		JLabel getDef = new JLabel("" + iD.getStat(Stat.DEF));
		getDef.setFont(new Font("Arial Black", Font.BOLD, 13));
		getDef.setBounds(393, 196, 31, 22);
		frame.getContentPane().add(getDef);
		
		JLabel getSpd = new JLabel("" + iD.getStat(Stat.SPD));
		getSpd.setFont(new Font("Arial Black", Font.BOLD, 13));
		getSpd.setBounds(393, 229, 31, 22);
		frame.getContentPane().add(getSpd);
		
		JLabel Mossa1 = new JLabel("Mossa 1");
		Mossa1.setOpaque(false);
		Mossa1.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa1.setBounds(21, 131, 70, 22);
		frame.getContentPane().add(Mossa1);
		
		JLabel Mossa2 = new JLabel("Mossa 2");
		Mossa2.setOpaque(false);
		Mossa2.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa2.setBounds(21, 164, 70, 22);
		frame.getContentPane().add(Mossa2);
		
		JLabel Mossa3 = new JLabel("Mossa 3");
		Mossa3.setOpaque(false);
		Mossa3.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa3.setBounds(21, 197, 70, 22);
		frame.getContentPane().add(Mossa3);
		
		JLabel Mossa4 = new JLabel("Mossa 4");
		Mossa4.setOpaque(false);
		Mossa4.setFont(new Font("Arial Black", Font.BOLD, 13));
		Mossa4.setBounds(21, 230, 70, 22);
		frame.getContentPane().add(Mossa4);
		
		JLabel NomePokemon = new JLabel(iD.getPokemon().name());
		NomePokemon.setOpaque(false);
		NomePokemon.setFont(new Font("Arial Black", Font.BOLD, 18));
		NomePokemon.setBounds(78, 27, 275, 30);
		frame.getContentPane().add(NomePokemon);
		
		JLabel Lvl = new JLabel("Lvl:");
		Lvl.setOpaque(false);
		Lvl.setFont(new Font("Arial Black", Font.BOLD, 13));
		Lvl.setBounds(163, 82, 37, 22);
		frame.getContentPane().add(Lvl);
		
		JLabel cento = new JLabel("" + iD.getStat(Stat.LVL));
		cento.setFont(new Font("Arial Black", Font.BOLD, 13));
		cento.setBounds(232, 82, 37, 22);
		frame.getContentPane().add(cento);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(352, 11, 82, 22);
		frame.getContentPane().add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
			}	
		});
		frame.setVisible(true);
	}}
	}
	