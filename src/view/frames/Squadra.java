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
import view.View;
import view.resources.MessageFrame;

public class Squadra extends JWindow implements MyFrame {
        
    private static final long serialVersionUID = 4848482754813638374L;
    private JPanel contain;
    private final ArrayList<String>names = new ArrayList<String>();
    private final ArrayList<String>lvl = new ArrayList<String>();
    private final ArrayList<String>cHP = new ArrayList<String>();
    private final ArrayList<String>mHP = new ArrayList<String>();
    private final ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
    private int cols = 1;
    private JButton but;
    private JButton but2;
    private JButton but3;
    private JButton but4;
    private boolean canExit, isChangingPoke;
    
    public Squadra(final boolean b1, final boolean b2) {
        this.canExit = b1;
        this.isChangingPoke = b2;
    }
    
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        contain = new JPanel();
        this.setContentPane(contain);
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
        for(int i = 0; i<names.size();i++) {
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
            but = new JButton("INFO");
            but.addActionListener(new ActionListener() {
                private final Pokemon ID = pkmn;
                @Override
                public void actionPerformed(ActionEvent e) {
                    Statistics st = new Statistics(ID);
                    View.getView().addNew(st);
                    View.getView().hideParent();
                    View.getView().showCurrent();
                }
            });
            contain.add(but);
            but2 = new JButton("SET FIRST");
            but2.addActionListener(new ActionListener() {
                private final int ID = index;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Controller.getController().getStatusController().getState() == State.MENU) {
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(index).getCurrentHP() > 0) {
                            PlayerImpl.getPlayer().getSquad().switchPokemon(0, ID);
                            View.getView().disposeCurrent();
                            View.getView().removeCurrent();
                            Squadra sc = new Squadra(true, false);
                            View.getView().addNew(sc);
                            View.getView().showCurrent();
                        } else {
                            new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                        }
                    } else {
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
                            try {
                                Controller.getController().getFightController().selectPokemon(PlayerImpl.getPlayer().getSquad().getPokemonList().get(index));
                                View.getView().disposeCurrent();
                                View.getView().removeCurrent();
                                View.getView().resumeCurrent();
                            } catch (PokemonIsExhaustedException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            } catch (PokemonIsFightingException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            }
                        } else {
                            try {
                                Controller.getController().getFightController().changePokemon(PlayerImpl.getPlayer().getSquad().getPokemonList().get(index));
                                View.getView().disposeCurrent();
                                View.getView().removeCurrent();
                                View.getView().resumeCurrent();
                            } catch (PokemonIsExhaustedException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            } catch (PokemonIsFightingException e1) {
                                new MessageFrame(null, "CANNOT SELECT THAT POKEMON");
                            }
                        }
                    }
                }
            });
            if (isChangingPoke) {
                but2.setEnabled(false);
            }
            contain.add(but2);
            but3 = new JButton("DEPOSIT");
            but3.addActionListener(new ActionListener() {
                final Pokemon p = pkmn;
                public void actionPerformed(ActionEvent e) {
                    try {
                        PlayerImpl.getPlayer().getBox().depositPokemon(p, PlayerImpl.getPlayer().getSquad());
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        Squadra sc = new Squadra(canExit, isChangingPoke);
                        View.getView().addNew(sc);
                        View.getView().showCurrent();
                    } catch (PokemonNotFoundException e1) {
                        new MessageFrame(null, "POKEMON NOT FOUND");
                    } catch (OnlyOnePokemonInSquadException e1) {
                        new MessageFrame(null, "CANNOT DEPOSIT LAST POKEMON");
                    }
                }
            });
            if (Controller.getController().getStatusController().getState() != State.MENU || isChangingPoke) {
                but3.setEnabled(false);
            }
            contain.add(but3);
            but4 = new JButton("SELECT");
            but4.addActionListener(new ActionListener() {
                final Pokemon p = pkmn;
                public void actionPerformed(ActionEvent e) {
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    MyFrame fr = View.getView().getCurrent();
                    ((Zaino) fr).useItem(p);
                    if (Controller.getController().getStatusController().getState() == State.MENU) {
                        View.getView().removeCurrent();
                        Zaino z = new Zaino();
                        View.getView().addNew(z);
                        View.getView().showCurrent();
                    } else {
                        View.getView().removeCurrent();
                    }
                }
            });
            if (!isChangingPoke) {
                but4.setEnabled(false);
            }
            contain.add(but4);
            JButton but5 = new JButton("EXIT");
            but5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    View.getView().resumeCurrent();
                }
            });
            if (!canExit) {
                but5.setEnabled(false);
            }
            contain.add(but5);
        }      
        contain.setLayout(new GridLayout(names.size(), cols));
        this.setSize(900,100 * names.size());
        this.setVisible(true);
    }

    @Override
    public void disposeFrame() {
        this.dispose();
    }

    @Override
    public void hideFrame() {
        this.setVisible(false);
    }
    
    @Override
    public void resume() {
        this.setVisible(true);
    }
}      