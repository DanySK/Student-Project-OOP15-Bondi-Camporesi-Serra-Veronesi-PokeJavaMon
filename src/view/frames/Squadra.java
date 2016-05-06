package view.frames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import controller.Controller;
import controller.parameters.State;
import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.resources.MessageFrame;

public class Squadra {

        private static JWindow window;
	
        public Squadra(boolean bl, boolean bl2) {
		
        	window = new JWindow ();
        	window.setAlwaysOnTop(true);
        
        JPanel contain = new JPanel();
        window.setContentPane(contain);
        
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
        
        for (Pokemon p : PlayerImpl.getPlayer().getSquad().getPokemonList()) {
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
                contain.add(new JLabel(""));
        		i++;
        	}
      
            final int index = i - 1;
            final Pokemon pkmn = pk.get(i);
            contain.add(new JLabel(names.get(i)));
            contain.add(new JLabel(lvl.get(i)));
            contain.add(new JLabel(cHP.get(i) + " / " + mHP.get(i)));
            JButton but = new JButton("INFO");
            but.addActionListener(new ActionListener() {
                private final Pokemon ID = pkmn;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(ID);
                    new Statistics (ID);
                }
            });
   
            contain.add(but);
            JButton but2 = new JButton("SET FIRST");
            but2.addActionListener(new ActionListener() {
                private final int ID = index;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Controller.getController().getStatusController().getState() == State.MENU) {
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(index).getCurrentHP() > 0) {
                            PlayerImpl.getPlayer().getSquad().switchPokemon(0, ID);
                            Squadra.dispose();
                            new Squadra(true, false);
                        } else {
                            //Squadra.dispose();
                            new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                        }
                    } else {
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
                            try {
                                Controller.getController().getFightController().selectPokemon(PlayerImpl.getPlayer().getSquad().getPokemonList().get(index));
                                Squadra.dispose();
                          
                            } catch (PokemonIsExhaustedException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            } catch (PokemonIsFightingException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            }
                        } else {
                            try {
                                Squadra.dispose();
                                Controller.getController().getFightController().changePokemon(PlayerImpl.getPlayer().getSquad().getPokemonList().get(index));
                            } catch (PokemonIsExhaustedException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            } catch (PokemonIsFightingException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            }
                        }
                    }
                }
            });
            if (bl2) {
                but2.setEnabled(false);
            }
            contain.add(but2);
            JButton but3 = new JButton("DEPOSIT");
            but3.addActionListener(new ActionListener() {
                final Pokemon p = pkmn;
                public void actionPerformed(ActionEvent e) {
                    Squadra.dispose();
                    try {
                        PlayerImpl.getPlayer().getBox().depositPokemon(p, PlayerImpl.getPlayer().getSquad());
                    } catch (PokemonNotFoundException e1) {
                        new MessageFrame(null, "POKEMON NOT FOUND");
                    } catch (OnlyOnePokemonInSquadException e1) {
                        new MessageFrame(null, "CANNOT DEPOSIT LAST POKEMON");
                    }
                }
            });
            if (Controller.getController().getStatusController().getState() != State.MENU || bl2) {
                but3.setEnabled(false);
            }
            contain.add(but3);
            JButton but4 = new JButton("SELECT");
            but4.addActionListener(new ActionListener() {
                final Pokemon p = pkmn;
                public void actionPerformed(ActionEvent e) {
                    Squadra.dispose();
                    Zaino.useItem(p);
                }
            });
            if (!bl2) {
                but4.setEnabled(false);
            }
            contain.add(but4);
            JButton but5 = new JButton("EXIT");
            but5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Squadra.dispose();
                }
            });
            if (!bl) {
                but5.setEnabled(false);
            }
            contain.add(but5);
           }      
        contain.setLayout(new GridLayout(names.size(), cols));
        window.setSize(900,100 * names.size());
        window.setVisible(true);
        }
    
        
    public static void dispose() {
    	window.dispose();
    }
}


       