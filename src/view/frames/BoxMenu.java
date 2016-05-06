package view.frames;

import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.View;
import view.resources.MessageFrame;  
  
public class BoxMenu extends JWindow implements MyFrame {

    private static final long serialVersionUID = 6312860320430410019L;
    private JPanel contain;
    private JScrollPane pn;
    private final ArrayList<String> names = new ArrayList<String>();
    private final ArrayList<String> lvl = new ArrayList<String>();
    private final ArrayList<String> cHP = new ArrayList<String>();
    private final ArrayList<String> mHP = new ArrayList<String>();
    private final ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
    private int cols = 1;
    private JButton but4;
    private JButton but;
    private JButton but3;

    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        contain = new JPanel();
        pn = new JScrollPane(contain);
        this.setContentPane(pn);     
        contain.setLayout(new GridLayout(1,1));    
        names.add("NAME");
        lvl.add("LEVEL");
        cHP.add("HEALTH POINTS");
        mHP.add("");
        pk.add(null);     
        for (Pokemon p : PlayerImpl.getPlayer().getBox().getPokemonList()) {
            names.add(p.getPokemon().name()); // Nome Pkmn
            lvl.add("" + p.getStat(Stat.LVL)); // Livello
            mHP.add("" + p.getStat(Stat.HP) + "/" + p.getCurrentHP());
            cHP.add("" + p.getCurrentHP());
            pk.add(p);
        }  
        for(int i = 0; i<names.size();i++) {
            if (i == 0) {
                contain.add(new JLabel(names.get(i)));
                contain.add(new JLabel(lvl.get(i)));
                contain.add(new JLabel(cHP.get(i)));
                contain.add(new JLabel(""));
                but4 = new JButton("EXIT");
                but4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    }
                });
                contain.add(but4);
            } else {
                final Pokemon pokmn = pk.get(i);
                contain.add(new JLabel(names.get(i)));
                contain.add(new JLabel(lvl.get(i)));
                contain.add(new JLabel(mHP.get(i)));
                but = new JButton("INFO");
                but.addActionListener(new ActionListener() {
                private final Pokemon ID = pokmn;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statistics st = new Statistics(ID);
                        View.getView().addNew(st);
                        View.getView().hideParent();
                        View.getView().showCurrent();
                    }
                });
                contain.add(but);
                but3 = new JButton("WITHDRAW");
                but3.addActionListener(new ActionListener() {
                    final Pokemon selected = pokmn;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            PlayerImpl.getPlayer().getBox().withdrawPokemon(selected, PlayerImpl.getPlayer().getSquad());
                            View.getView().disposeCurrent();
                            View.getView().removeCurrent();
                            BoxMenu bx = new BoxMenu();
                            View.getView().addNew(bx);
                            View.getView().showCurrent();
                        } catch (PokemonNotFoundException e1) {
                            new MessageFrame(null, "POKEMON NOT FOUND");
                        } catch (SquadFullException e1) {
                            new MessageFrame(null, "SQUAD IS FULL");
                        }
                    }
                });
                contain.add(but3);
            }               
        }
        contain.setLayout(new GridLayout(names.size(), cols));
        this.setSize(900,100 * names.size());
        if (names.size() > 6) {
            this.setSize(800,600);
        } else {
            this.setSize(800,100 * names.size());
        }
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