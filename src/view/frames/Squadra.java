package view.frames;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import controller.MainController;
import controller.ViewController;
import controller.parameters.State;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;

public class Squadra {

        private static JFrame frame;
	
        public Squadra() {
		
        frame = new JFrame("Squadra");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setSize(500,600);
        frame.setUndecorated(true);
        
        JPanel contain = new JPanel();
        frame.setContentPane(contain);
        contain.setLayout(new GridLayout(1,1));

        final ArrayList<String>names = new ArrayList<String>();
        final ArrayList<String>lvl = new ArrayList<String>();
        final ArrayList<String>cHP = new ArrayList<String>();
        final ArrayList<String>mHP = new ArrayList<String>();
        
        for (Pokemon p : PlayerImpl.getPlayer().getSquad().getPokemonList()) {
        	names.add(p.getPokemon().name()); // Nome Pkmn
        	lvl.add("" + p.getStat(Stat.LVL)); // Livello
        	cHP.add("" + p.getCurrentHP());
        	mHP.add("" + p.getStat(Stat.HP));
        }
        
        contain.add(new Panel2(names, lvl, cHP, mHP, 1));
        
        frame.setVisible(true);
    }
        
    public static void dispose() {
        frame.dispose();
    }
}

class Panel2 extends JPanel
{
    private static final long serialVersionUID = 222368330456306439L;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> lvl = new ArrayList<String>();
    ArrayList<String> cHP = new ArrayList<String>();
    ArrayList<String> mHP = new ArrayList<String>();
    int cols;

    public Panel2(ArrayList<String> nam,ArrayList<String> lv,ArrayList<String> current, ArrayList<String> max, int c) 
    {
        this.names = nam;
        this.lvl =lv;
        this.cHP = current;
        this.mHP = max;
        this.cols = c;

        setLayout(new GridLayout(names.size(),cols));

        for(int i = 0; i<names.size();i++)
        {
            final int index = i;
            add(new JLabel(names.get(i)));
            add(new JTextField(lvl.get(i)));
            add(new JTextField(cHP.get(i)));
            add(new JTextField(mHP.get(i)));
            JButton but = new JButton("INFO");
            but.addActionListener(new ActionListener() {
                private final int ID = index;
                @Override
                public void actionPerformed(ActionEvent e) {
                    ViewController.getController().stats(ID);
                }
            });
            add(but);
            JButton but2 = new JButton("SET FIRST");
            but2.addActionListener(new ActionListener() {
                private final int ID = index;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (MainController.getController().getState() == State.MENU) {
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(index).getCurrentHP() > 0) {
                            PlayerImpl.getPlayer().getSquad().switchPokemon(0, ID);
                        } else {
                            System.out.println("CANNOT SELECT THAT POKEMON");
                        }
                    } else {
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
                            try {
                                ViewController.getController().selectPokemon(PlayerImpl.getPlayer().getSquad().getPokemonList().get(index));
                                Squadra.dispose();
                            } catch (PokemonIsExhaustedException e1) {
                                System.out.println("CANNOT SELECT THAT POKEMON");
                            } catch (PokemonIsFightingException e1) {
                                System.out.println("CANNOT SELECT THAT POKEMON");
                            }
                        } else {
                            try {
                                ViewController.getController().changePokemon(PlayerImpl.getPlayer().getSquad().getPokemonList().get(index));
                                Squadra.dispose();
                            } catch (PokemonIsExhaustedException e1) {
                                System.out.println("CANNOT SELECT THAT POKEMON");
                            } catch (PokemonIsFightingException e1) {
                                System.out.println("CANNOT SELECT THAT POKEMON");
                            }
                        }
                    }
                }
            });
            add(but2);
            JButton but3 = new JButton("EXIT");
            but3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (MainController.getController().getState() == State.MENU) {
                        Squadra.dispose();
                    }
                }
            });
            add(but3);
           }

    }}

