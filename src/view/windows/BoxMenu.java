package view.windows;

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
/**
 * 
 * BoxMenuClass
 * 
 * @author Daniel Veronesi
 *
 */
public class BoxMenu extends JWindow implements MyFrame {
	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 6312860320430410019L;
    /**
	 * panel
	 */
    private JPanel panel;
    /**
	 * pn
	 */
    private JScrollPane pn;
    /**
	 * An array filled with the names of the pok�mon in the box.
	 */
    private final ArrayList<String> names;
    /**
	 * An array filled with the levels of the pok�mon in the box.
	 */
    private final ArrayList<String> lvl;
    /**
	 * An array filled with the current health points of the pok�mon in the box.
	 */
    private final ArrayList<String> cHP;
    /**
	 * An array filled with the max health points of the pok�mon in the box.
	 */
    private final ArrayList<String> mHP;
    /**
	 * pk
	 */
    private final ArrayList<Pokemon> pk;
    /**
	 * The number of columns.
	 */
    private int cols = 1;
    /**
	 * A button that opens {@link Statistics}.
	 */
    private JButton info;
    /**
	 * A button that allows to withdraw the pok�mon and put it in the party.
	 */
    private JButton withdraw;
    /**
	 * A button that closes the Box.
	 */
    private JButton exit;
	/**
	 * BoxMenu
	 */
    public BoxMenu() {
        this.names = new ArrayList<String>();
        this.lvl = new ArrayList<String>();
        this.cHP = new ArrayList<String>();
        this.mHP = new ArrayList<String>();
        this.pk = new ArrayList<Pokemon>();
    }
    
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        this.panel = new JPanel();
        this.pn = new JScrollPane(this.panel);
        this.setContentPane(this.pn);     
        this.panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.panel.setLayout(new GridLayout(1,1));      
        this.names.add("NAME");
        this.lvl.add("LEVEL");
        this.cHP.add("HEALTH POINTS");
        this.mHP.add("");
        this.pk.add(null);     
        for (Pokemon p : MainController.getController().getBox().get().getPokemonList()) {
            this.names.add(p.getPokedexEntry().name()); // Nome Pkmn
            this.lvl.add("" + p.getStat(Stat.LVL)); // Livello
            this.mHP.add("" + p.getCurrentHP() + "/" + p.getStat(Stat.MAX_HP));
            this.cHP.add("" + p.getCurrentHP());
            this.pk.add(p);
        }  
        for(int i = 0; i<this.names.size();i++) {
            if (i == 0) {
                this.panel.add(new JLabel(this.names.get(i)));
            	this.panel.add(new JLabel(this.lvl.get(i)));
            	this.panel.add(new JLabel(this.cHP.get(i)));
            	this.panel.add(new JLabel(""));
            	this.exit = new JButton("EXIT");
            	this.exit.setBorderPainted(false);
            	this.exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    }
                });
            	this.panel.add(this.exit);
            } else {
                final Pokemon pokmn = this.pk.get(i);
                this.panel.add(new JLabel(this.names.get(i)));
                this.panel.add(new JLabel(this.lvl.get(i)));
                this.panel.add(new JLabel(this.mHP.get(i)));
                this.info = new JButton("INFO");
                this.info.setBorderPainted(false);
                this.info.addActionListener(new ActionListener() {
                private final Pokemon ID = pokmn;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statistics st = new Statistics(ID);
                        View.getView().addNew(st);
                        View.getView().hideParent();
                        View.getView().showCurrent();
                    }
                });
                this.panel.add(info);
                this.withdraw = new JButton("WITHDRAW");
                this.withdraw.setBorderPainted(false);
                this.withdraw.addActionListener(new ActionListener() {
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
                this.panel.add(this.withdraw);
            }               
        }
        this.panel.setLayout(new GridLayout(this.names.size(), this.cols));
        this.setSize(900,100 * this.names.size());
        if (this.names.size() > 6) {
            this.setSize(800,600);
        } else {
            this.setSize(800,100 * this.names.size());
        }
        this.setLocationRelativeTo(null);
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