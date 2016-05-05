package view.frames;

import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.Controller;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.resources.MessageFrame;  
  
public class Box {

    private static JWindow window;
    
    public Box() {
            
    	//window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    	//window.setUndecorated(true);
    	
    	window = new JWindow ();
    	window.setAlwaysOnTop(true);
    	
    	JPanel contain = new JPanel();
    	JScrollPane pn = new JScrollPane(contain);
    	window.setContentPane(pn);
    	
    	contain.setLayout(new GridLayout(1,1));

    	final ArrayList<String>names = new ArrayList<String>();
    	final ArrayList<String>lvl = new ArrayList<String>();
    	final ArrayList<String>cHP = new ArrayList<String>();
    	final ArrayList<String>mHP = new ArrayList<String>();
    	final ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
    	
        int cols = 1;
        
        names.add("NAME");
        lvl.add("LEVEL");
        cHP.add("HEALTH POINTS");
        mHP.add("");
        pk.add(null);
    	
    	for (Pokemon p : PlayerImpl.getPlayer().getBox().getPokemonList()) {
            names.add(p.getPokemon().name()); // Nome Pkmn
            lvl.add("" + p.getStat(Stat.LVL)); // Livello
            mHP.add("" + p.getStat(Stat.HP));
            cHP.add("" + p.getCurrentHP());
            pk.add(p);
    	}
    
    	for(int i = 0; i<names.size();i++)
		{
    		
    		if (i == 0) {
        		contain.add(new JLabel(names.get(i)));
                contain.add(new JLabel(lvl.get(i)));
                contain.add(new JLabel(cHP.get(i)));
                contain.add(new JLabel(""));
                contain.add(new JLabel(""));
                contain.add(new JLabel(""));
                contain.add(new JLabel(""));
        		i++;
        	}
    		
			final Pokemon pokmn = pk.get(i);
			contain.add(new JLabel(names.get(i)));
			contain.add(new JLabel(lvl.get(i)));
			contain.add(new JLabel(cHP.get(i)));
			contain.add(new JLabel(mHP.get(i)));
			JButton but = new JButton("INFO");
			but.addActionListener(new ActionListener() {
				private final Pokemon ID = pokmn;
				@Override
				public void actionPerformed(ActionEvent e) {
					Controller.getController().getViewController().stats(ID);
				}
			});
			contain.add(but);
			JButton but3 = new JButton("WITHDRAW");
			but3.addActionListener(new ActionListener() {
				final Pokemon selected = pokmn;
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						PlayerImpl.getPlayer().getBox().withdrawPokemon(selected, PlayerImpl.getPlayer().getSquad());
						Box.dispose();
					} catch (PokemonNotFoundException e1) {
						new MessageFrame(null, "POKEMON NOT FOUND");
					} catch (SquadFullException e1) {
						new MessageFrame(null, "SQUAD IS FULL");
					}
				}
			});
			contain.add(but3);
			JButton but4 = new JButton("EXIT");
			but4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Box.dispose();
				}
			});
			contain.add(but4);
		}
    	contain.setLayout(new GridLayout(names.size(), cols));
        window.setSize(900,100 * names.size());
        if (names.size() > 6) {
    		window.setSize(800,600);
    	} else {
    		window.setSize(800,100 * names.size());
    	}
    	window.setVisible(true);
}

    public static void dispose() {
    	window.dispose();
    }
}