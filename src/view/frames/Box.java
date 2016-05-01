package view.frames;

import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.view.ViewController;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.resources.MessageFrame;  
  
public class Box {

    private static JFrame frame;
    
    public Box() {
            
    frame = new JFrame("Box");
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setAlwaysOnTop(true);
    frame.setUndecorated(true);
    JPanel contain = new JPanel();
    JScrollPane pn = new JScrollPane(contain);
    frame.setContentPane(pn);
    contain.setLayout(new GridLayout(1,1));

    final ArrayList<String>names = new ArrayList<String>();
    final ArrayList<String>lvl = new ArrayList<String>();
    final ArrayList<String>cHP = new ArrayList<String>();
    final ArrayList<String>mHP = new ArrayList<String>();
    final ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
    
    for (Pokemon p : PlayerImpl.getPlayer().getBox().getPokemonList()) {
            names.add(p.getPokemon().name()); // Nome Pkmn
            lvl.add("" + p.getStat(Stat.LVL)); // Livello
            mHP.add("" + p.getStat(Stat.HP));
            cHP.add("" + p.getCurrentHP());
            pk.add(p);
    }
    
    contain.add(new BoxPanel(names, lvl, cHP, mHP, pk, 1));
    if (names.size() > 6) {
        frame.setSize(800,600);
    } else {
        frame.setSize(800,100 * names.size());
    }
    frame.setVisible(true);
}
    
public static void dispose() {
    frame.dispose();
}
}

class BoxPanel extends JPanel
{
private static final long serialVersionUID = 222368330456306439L;
ArrayList<String> names = new ArrayList<String>();
ArrayList<String> lvl = new ArrayList<String>();
ArrayList<String> cHP = new ArrayList<String>();
ArrayList<String> mHP = new ArrayList<String>();
ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
int cols;

public BoxPanel(ArrayList<String> nam,ArrayList<String> lv,ArrayList<String> current, ArrayList<String> max, ArrayList<Pokemon> pkm, int c) 
{
    this.names = nam;
    this.lvl =lv;
    this.cHP = current;
    this.mHP = max;
    this.cols = c;
    this.pk = pkm;

    setLayout(new GridLayout(names.size(),cols));

    for(int i = 0; i<names.size();i++)
    {
        final Pokemon pokmn = pk.get(i);
        add(new JLabel(names.get(i)));
        add(new JLabel(lvl.get(i)));
        add(new JLabel(cHP.get(i)));
        add(new JLabel(mHP.get(i)));
        JButton but = new JButton("INFO");
        but.addActionListener(new ActionListener() {
            private final Pokemon ID = pokmn;
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewController.getController().stats(ID);
            }
        });
        add(but);
        JButton but3 = new JButton("WITHDRAW");
        but3.addActionListener(new ActionListener() {
            final Pokemon selected = pokmn;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlayerImpl.getPlayer().getBox().withdrawPokemon(selected, PlayerImpl.getPlayer().getSquad());
                    Box.dispose();
                } catch (PokemonNotFoundException e1) {
                    new MessageFrame("POKEMON NOT FOUND", null);
                } catch (SquadFullException e1) {
                    new MessageFrame("SQUAD IS FULL", null);
                }
            }
        });
        add(but3);
        JButton but4 = new JButton("EXIT");
        but4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Box.dispose();
            }
        });
        add(but4);
       }

}}