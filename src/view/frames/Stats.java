package view.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
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
		
		JLabel NomePokemon = new JLabel(iD.getPokemon().name());
		NomePokemon.setBounds(78, 27, 275, 30);
		frame.getContentPane().add(NomePokemon);
		
		JLabel Lvl = new JLabel("Lvl:");
		Lvl.setBounds(163, 82, 37, 22);
		frame.getContentPane().add(Lvl);
		
		JLabel LvlValue = new JLabel("" + iD.getStat(Stat.LVL));
		LvlValue.setBounds(232, 82, 37, 22);
		frame.getContentPane().add(LvlValue);
		
		JLabel Move1 = new JLabel("Move 1");
		Move1.setBounds(21, 131, 70, 22);
		frame.getContentPane().add(Move1);
		
		JLabel Move2 = new JLabel("Move 2");
		Move2.setBounds(21, 164, 70, 22);
		frame.getContentPane().add(Move2);
		
		JLabel Move3 = new JLabel("Move 3");
		Move3.setBounds(21, 197, 70, 22);
		frame.getContentPane().add(Move3);
		
		JLabel Move4 = new JLabel("Move 4");
		Move4.setBounds(21, 230, 70, 22);
		frame.getContentPane().add(Move4);
		
		JLabel Move1Name;
		if (iD.getCurrentMoves().get(0) != null) {
			Move1Name = new JLabel(iD.getCurrentMoves().get(0).name());
		} else {
			Move1Name = new JLabel("NONE");
		}
		Move1Name.setBounds(121, 130, 98, 22);
		frame.getContentPane().add(Move1Name);
		
		JLabel Move2Name;
		if (iD.getCurrentMoves().get(1) != null) {
			Move2Name = new JLabel(iD.getCurrentMoves().get(1).name());
                } else {
                	Move2Name = new JLabel("");
                	Move2.setEnabled(false);
                }
		Move2Name.setBounds(121, 163, 98, 22);
		frame.getContentPane().add(Move2Name);
		
		JLabel Move3Name;
		if (iD.getCurrentMoves().get(2) != null) {
			Move3Name = new JLabel(iD.getCurrentMoves().get(2).name());
                } else {
                	Move3Name = new JLabel("");
                	Move3.setEnabled(false);
                }
		Move3Name.setBounds(121, 229, 98, 22);
		frame.getContentPane().add(Move3Name);
		
		JLabel Move4Name;
		if (iD.getCurrentMoves().get(3) != null) {
			Move4Name = new JLabel(iD.getCurrentMoves().get(3).name());
                } else {
                	Move4Name = new JLabel("");
                	Move4.setEnabled(false);
                }
		Move4Name.setBounds(121, 196, 98, 22);
		frame.getContentPane().add(Move4Name);
		
		JLabel Hp = new JLabel("HP:");
		Hp.setBounds(292, 130, 37, 22);
		frame.getContentPane().add(Hp);
		
		JLabel Atk = new JLabel("Atk:");
		Atk.setBounds(292, 163, 37, 22);
		frame.getContentPane().add(Atk);
		
		JLabel Def = new JLabel("Def:");
		Def.setBounds(292, 196, 37, 22);
		frame.getContentPane().add(Def);
		
		JLabel Spd = new JLabel("Spd:");
		Spd.setBounds(292, 229, 37, 22);
		frame.getContentPane().add(Spd);
		
		JLabel getHP = new JLabel("" + iD.getCurrentHP() + "/" + iD.getStat(Stat.HP));
		getHP.setBounds(393, 130, 31, 22);
		frame.getContentPane().add(getHP);
		
		JLabel getAtk = new JLabel("" + iD.getStat(Stat.ATK));
		getAtk.setBounds(393, 163, 31, 22);
		frame.getContentPane().add(getAtk);
		
		JLabel getDef = new JLabel("" + iD.getStat(Stat.DEF));
		getDef.setBounds(393, 196, 31, 22);
		frame.getContentPane().add(getDef);
		
		JLabel getSpd = new JLabel("" + iD.getStat(Stat.SPD));
		getSpd.setBounds(393, 229, 31, 22);
		frame.getContentPane().add(getSpd);
				
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
	//Hp.setOpaque(false);
	//cento.setFont(new Font("Arial Black", Font.BOLD, 13));
	//.setEnabled(false);