package view.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import controller.fight.FightController;
import controller.main.MainController;
import controller.parameters.State;
import controller.view.ViewController;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Potion;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import view.resources.MessageFrame;

public class Zaino {

	private static JFrame frame;
	private static Item itemToUse;
	
public Zaino() {
		
	
    frame = new JFrame("Zaino");
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setAlwaysOnTop(true);
    frame.setSize(600,600);
    frame.setUndecorated(true);
    
    JPanel contiene = new JPanel();
    frame.setContentPane(contiene);
    contiene.setLayout(new GridLayout(1,1));

    final ArrayList<String>Name1 = new ArrayList<String>();
    final ArrayList<String>Name2 = new ArrayList<String>();
    final ArrayList<String>Prz = new ArrayList<String>();
    final ArrayList<String>Qnt = new ArrayList<String>();
    final ArrayList<Item> it = new ArrayList<Item>();
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).keySet()) { 
    	Name1.add(i.getType().name()); 
    	Name2.add(i.toString()); 
    	Prz.add("" + i.getPrice());
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).get(i));
    	it.add(i);
    	    }

    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).keySet()) { 
    	Name1.add(i.getType().name());
    	Name2.add(i.toString()); 
    	Prz.add("" + i.getPrice());
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).get(i));
    	it.add(i);
    }
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).keySet()) { 
    	Name1.add(i.getType().name()); 
    	Name2.add(i.toString()); 
    	Prz.add("" + i.getPrice());
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).get(i));
    	it.add(i);
    }
   
    contiene.add(new Panel(Name1, Name2, Prz, Qnt, it, 1));
    
    frame.setVisible(true);
}
    
public static void dispose() {
    frame.dispose();
}

public static void selectItem(Item it) {
    itemToUse = it;
}

public static void useItem(Pokemon p) {
    if (itemToUse != null) {
        if (MainController.getController().getState() == State.FIGHTING) {
            try {
                ViewController.getController().useItem(itemToUse, p);
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (PokemonIsExhaustedException e1) {
                new MessageFrame("POKEMON IS EXAUSTED", null);
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (PokemonNotFoundException e1) {
                new MessageFrame("POKEMON NOT FOUND", null);
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (CannotCaughtTrainerPkmException e1) {
                new MessageFrame("CANNOT CATCH TRAINER POKEMON", null);
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (IllegalStateException e1) {
                new MessageFrame("YOU HAVE NO MORE THIS ITEM", null);
                Zaino.selectItem(null);
                Zaino.dispose();
            }
        } else {
            if (itemToUse instanceof Potion) {
                try {
                    ((Potion) itemToUse).effect(PlayerImpl.getPlayer(), (PokemonInBattle) p);
                    PlayerImpl.getPlayer().getInventory().consumeItem(itemToUse);
                    Zaino.dispose();
                } catch (PokemonNotFoundException e) {
                    new MessageFrame("POKEMON NOT FOUND", null);
                } catch (IllegalStateException ex) {
                    new MessageFrame("YOU HAVE NO MORE THIS ITEM", null);
                    Zaino.selectItem(null);
                    Zaino.dispose();
                }
            }
        }
    }
}
}

class Panel extends JPanel
{

	private static final long serialVersionUID = 3332840867083025623L;
	ArrayList<String> Name1 = new ArrayList<String>();
    ArrayList<String> Name2 = new ArrayList<String>();
    ArrayList<String> Prz = new ArrayList<String>();
    ArrayList<String> Qnt = new ArrayList<String>();
    ArrayList<Item> it = new ArrayList<Item>();
    int col;

public Panel(ArrayList<String> a, ArrayList<String> b, ArrayList<String> d,ArrayList<String> e, ArrayList<Item> f, int c) 
{
    this.Name1 = a;
    this.Name2 = b;
    this.Prz = d;
    this.Qnt = e;
    this.col = c;
    this.it = f;

    setLayout(new GridLayout(Name1.size(),col));

    for(int j = 0; j<Name1.size();j++)
    {
        final Item itm = it.get(j);
        add(new JLabel(Name1.get(j)));
        add(new JLabel(Name2.get(j)));
        add(new JLabel(Prz.get(j)));
        add(new JLabel(Qnt.get(j)));
        JButton usa = new JButton("Use");
        usa.addActionListener(new ActionListener() {     
            Item i = itm;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i.getType() != ItemType.POKEBALL) {
                    Zaino.selectItem(i);
                    ViewController.getController();
                    ViewController.getController().team(true, true);
                } else {
                    Zaino.selectItem(i);
                    if (MainController.getController().getState() == State.FIGHTING) {
                        Zaino.useItem(FightController.getController().getEnemyPokemon());
                    } else {
                        Zaino.useItem(null);
                    }
                }
                Zaino.dispose();
            }
        });
        if (itm.getType() != ItemType.POTION && MainController.getController().getState() != State.FIGHTING) {
            usa.setEnabled(false);
        }
        add(usa);
        JButton esci = new JButton("Exit");
        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Zaino.dispose();
            }
        });
        add(esci);
       }
	}
}