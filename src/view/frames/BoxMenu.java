package view.frames;

import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MainController;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.View;  
  
public class BoxMenu extends JWindow implements MyFrame {

    private static final long serialVersionUID = 6312860320430410019L;
    private JPanel panel;
    private JScrollPane pn;
    private final ArrayList<String> names = new ArrayList<String>();
    private final ArrayList<String> lvl = new ArrayList<String>();
    private final ArrayList<String> cHP = new ArrayList<String>();
    private final ArrayList<String> mHP = new ArrayList<String>();
    private final ArrayList<Pokemon> pk = new ArrayList<Pokemon>();
    private int cols = 1;
    private JButton info;
    private JButton withdraw;
    private JButton exit;
    
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        panel = new JPanel();
        pn = new JScrollPane(panel);
        this.setContentPane(pn);     
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        panel.setLayout(new GridLayout(1,1));      
        names.add("NAME");
        lvl.add("LEVEL");
        cHP.add("HEALTH POINTS");
        mHP.add("");
        pk.add(null);     
        for (Pokemon p : MainController.getController().getBox().getPokemonList()) {
            names.add(p.getPokemon().name()); // Nome Pkmn
            lvl.add("" + p.getStat(Stat.LVL)); // Livello
            mHP.add("" + p.getCurrentHP() + "/" + p.getStat(Stat.HP));
            cHP.add("" + p.getCurrentHP());
            pk.add(p);
        }  
        for(int i = 0; i<names.size();i++) {
            if (i == 0) {
            	panel.add(new JLabel(names.get(i)));
            	panel.add(new JLabel(lvl.get(i)));
            	panel.add(new JLabel(cHP.get(i)));
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
                panel.add(exit);
            } else {
                final Pokemon pokmn = pk.get(i);
                panel.add(new JLabel(names.get(i)));
                panel.add(new JLabel(lvl.get(i)));
                panel.add(new JLabel(mHP.get(i)));
                info = new JButton("INFO");
                info.addActionListener(new ActionListener() {
                private final Pokemon ID = pokmn;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statistics st = new Statistics(ID);
                        View.getView().addNew(st);
                        View.getView().hideParent();
                        View.getView().showCurrent();
                    }
                });
                panel.add(info);
                withdraw = new JButton("WITHDRAW");
                withdraw.addActionListener(new ActionListener() {
                    final Pokemon selected = pokmn;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            MainController.getController().withdrawPokemon(selected);
                            View.getView().disposeCurrent();
                            View.getView().removeCurrent();
                            BoxMenu bx = new BoxMenu();
                            View.getView().addNew(bx);
                            View.getView().showCurrent();
                        } catch (PokemonNotFoundException e1) {
                            View.getView().hideCurrent();
                            View.getView().addNew(new MessageFrame(null, "POKEMON NOT FOUND"));
                            View.getView().showCurrent();
                        } catch (SquadFullException e1) {
                            View.getView().hideCurrent();
                            View.getView().addNew(new MessageFrame(null, "SQUAD IS FULL"));
                            View.getView().showCurrent();
                        }
                    }
                });
                panel.add(withdraw);
            }               
        }
        panel.setLayout(new GridLayout(names.size(), cols));
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
    public void resumeFrame() {
        this.setVisible(true);
    }
}