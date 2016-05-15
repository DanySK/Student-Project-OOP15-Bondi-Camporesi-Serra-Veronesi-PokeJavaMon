package view.frames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MainController;
import controller.parameters.State;
import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.View;

public class TeamMenu extends JWindow implements MyFrame {
        
    private static final long serialVersionUID = 4848482754813638374L;
    private JPanel panel;
    private final ArrayList<String>names = new ArrayList<String>();
    private final ArrayList<String>lvl = new ArrayList<String>();
    private final ArrayList<String>cHP = new ArrayList<String>();
    private final ArrayList<String>mHP = new ArrayList<String>();
    private final ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
    private int cols = 1;
    private JButton info;
    private JButton set;
    private JButton deposit;
    private JButton select;
    private JButton exit;
    private boolean canExit, isChangingPoke;
    
    public TeamMenu(final boolean b1, final boolean b2) {
        this.canExit = b1;
        this.isChangingPoke = b2;
    }
    
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        panel = new JPanel();
        this.setContentPane(panel);
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        names.add("NAME");
        lvl.add("LEVEL");
        cHP.add("HEALTH POINTS");
        mHP.add("");
        pk.add(null);
        for (Pokemon p : MainController.getController().getSquad().getPokemonList()) {
            names.add(p.getPokemon().name()); // Nome Pkmn
            lvl.add("" + p.getStat(Stat.LVL)); // Livello
            mHP.add("" + p.getStat(Stat.HP));
            cHP.add("" + p.getCurrentHP());
            pk.add(p);
        }
        for(int i = 0; i<names.size();i++) {
            if (i == 0) {
            	panel.add(new JLabel(names.get(i)));
            	panel.add(new JLabel(lvl.get(i)));
            	panel.add(new JLabel(cHP.get(i)));
            	panel.add(new JLabel(""));
            	panel.add(new JLabel(""));
            	panel.add(new JLabel(""));
                exit = new JButton("EXIT");
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    }
                });
                if (!canExit) {
                	exit.setEnabled(false);
                }
                panel.add(exit);
                i++;
            }
            final int index = i - 1;
            final Pokemon pkmn = pk.get(i);
            panel.add(new JLabel(names.get(i)));
            panel.add(new JLabel(lvl.get(i)));
            panel.add(new JLabel(cHP.get(i) + " / " + mHP.get(i)));
            info = new JButton("INFO");
            info.addActionListener(new ActionListener() {
                private final Pokemon ID = pkmn;
                @Override
                public void actionPerformed(ActionEvent e) {
                    Statistics st = new Statistics(ID);
                    View.getView().addNew(st);
                    View.getView().hideParent();
                    View.getView().showCurrent();
                }
            });
            panel.add(info);
            set = new JButton("SET FIRST");
            set.addActionListener(new ActionListener() {
                private final int ID = index;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (MainController.getController().getStatusController().getState() == State.MENU) {
                        if (MainController.getController().getSquad().getPokemonList().get(index).getCurrentHP() > 0) {
                            MainController.getController().switchPokemon(0, ID);
                            View.getView().disposeCurrent();
                            View.getView().removeCurrent();
                            TeamMenu sc = new TeamMenu(true, false);
                            View.getView().addNew(sc);
                            View.getView().showCurrent();
                        } else {
                            View.getView().hideCurrent();
                            View.getView().addNew(new MessageFrame(null, "CANNOT SELECT THAT POKEMON"));
                            View.getView().showCurrent();
                        }
                    } else {
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
                            try {
                                View.getView().disposeCurrent();
                                View.getView().removeCurrent();
                                MainController.getController().getFightController().selectPokemon(MainController.getController().getSquad().getPokemonList().get(index));
                                MyFrame fr = View.getView().getCurrent();
                                ((FightScreen) fr).repaintFrame();
                                View.getView().resumeCurrent();
                            } catch (PokemonIsExhaustedException e1) {
                                TeamMenu tm = new TeamMenu(false, false);
                                View.getView().addNew(tm);
                                View.getView().showCurrent();
                                View.getView().hideCurrent();
                                View.getView().addNew(new MessageFrame(null, "CANNOT SELECT THAT POKEMON"));
                                View.getView().showCurrent();
                            } catch (PokemonIsFightingException e1) {
                                TeamMenu tm = new TeamMenu(false, false);
                                View.getView().addNew(tm);
                                View.getView().showCurrent();
                                View.getView().hideCurrent();
                                View.getView().addNew(new MessageFrame(null, "CANNOT SELECT THAT POKEMON"));
                                View.getView().showCurrent();
                            }
                        } else {
                            try {
                                View.getView().disposeCurrent();
                                View.getView().removeCurrent();
                                MainController.getController().getFightController().changePokemon(MainController.getController().getSquad().getPokemonList().get(index));
                                View.getView().resumeCurrent();
                            } catch (PokemonIsExhaustedException e1) {
                                View.getView().addNew(new MessageFrame(null, "CANNOT SELECT THAT POKEMON"));
                                View.getView().showCurrent();
                            } catch (PokemonIsFightingException e1) {
                                View.getView().addNew(new MessageFrame(null, "CANNOT SELECT THAT POKEMON"));
                                View.getView().showCurrent();
                            }
                        }
                    }
                }
            });
            if (isChangingPoke) {
            	set.setEnabled(false);
            }
            panel.add(set);
            deposit = new JButton("DEPOSIT");
            deposit.addActionListener(new ActionListener() {
                final Pokemon p = pkmn;
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainController.getController().depositPokemon(p);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        TeamMenu sc = new TeamMenu(canExit, isChangingPoke);
                        View.getView().addNew(sc);
                        View.getView().showCurrent();
                    } catch (PokemonNotFoundException e1) {
                        View.getView().hideCurrent();
                        View.getView().addNew(new MessageFrame(null, "POKEMON NOT FOUND"));
                        View.getView().showCurrent();
                    } catch (OnlyOnePokemonInSquadException e1) {
                        View.getView().hideCurrent();
                        View.getView().addNew(new MessageFrame(null, "CANNOT DEPOSIT LAST POKEMON"));
                        View.getView().showCurrent();
                    }
                }
            });
            if (MainController.getController().getStatusController().getState() != State.MENU || isChangingPoke) {
            	deposit.setEnabled(false);
            }
            panel.add(deposit);
            select = new JButton("SELECT");
            select.addActionListener(new ActionListener() {
                final Pokemon p = pkmn;
                public void actionPerformed(ActionEvent e) {
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    MyFrame fr = View.getView().getCurrent();
                    ((BagMenu) fr).useItem(p);
                    if (MainController.getController().getStatusController().getState() == State.MENU) {
                        View.getView().removeCurrent();
                        BagMenu z = new BagMenu();
                        View.getView().addNew(z);
                        View.getView().showCurrent();
                    } 
                }
            });
            if (!isChangingPoke) {
            	select.setEnabled(false);
            }
            panel.add(select);
        }      
        panel.setLayout(new GridLayout(names.size(), cols));
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
    public void resumeFrame() {
        this.setVisible(true);
    }
}      