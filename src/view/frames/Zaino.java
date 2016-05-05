package view.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import controller.Controller;
import controller.parameters.State;
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

	private static JWindow window;
	private static Item itemToUse;
	
public Zaino() {
		
	
	//window = new JFrame("Bag");
	//window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	window = new JWindow ();
	window.setAlwaysOnTop(true);
	//window.setUndecorated(true);
    
    JPanel contiene = new JPanel();
    window.setContentPane(contiene);
    contiene.setLayout(new GridLayout(1,1));

    final ArrayList<String>Name1 = new ArrayList<String>();
    final ArrayList<String>Name2 = new ArrayList<String>();
    final ArrayList<String>Qnt = new ArrayList<String>();
    final ArrayList<Item> it = new ArrayList<Item>();

    Name1.add("TYPE");
    Name2.add("NAME");
    Qnt.add("QUANTITY");
    it.add(null);
    
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).keySet()) { 
    	if (PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).get(i) != 0) {
    	Name1.add(i.getType().name()); 
    	Name2.add(i.toString()); 
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).get(i));
    	it.add(i);
        System.out.println(""+ PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).get(i));
}
    	    }
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).keySet()) { 
    	if (PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).get(i) != 0) {
    	Name1.add(i.getType().name());
    	Name2.add(i.toString()); 
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).get(i));
    	it.add(i);
    	}
    }
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).keySet()) { 
    	if (PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).get(i) != 0) {
    	Name1.add(i.getType().name()); 
    	Name2.add(i.toString()); 
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).get(i));
    	it.add(i);
    	}
    }
   
    contiene.add(new Panel(Name1, Name2, Qnt, it, 1));
    window.setSize(600,60 * Name1.size());
    window.setVisible(true);
}
    
public static void dispose() {
	window.dispose();
}

public static void selectItem(Item it) {
    itemToUse = it;
}

public static void useItem(Pokemon p) {
    if (itemToUse != null) {
        if (Controller.getController().getStatusController().getState() == State.FIGHTING) {
            try {
                Controller.getController().getFightController().useItem(itemToUse, p);
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (PokemonIsExhaustedException e1) {
                new MessageFrame(null, "POKEMON IS EXAUSTED");
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (PokemonNotFoundException e1) {
                new MessageFrame(null, "POKEMON NOT FOUND");
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (CannotCaughtTrainerPkmException e1) {
                new MessageFrame(null, "CANNOT CATCH TRAINER POKEMON");
                Zaino.selectItem(null);
                Zaino.dispose();
            } catch (IllegalStateException e1) {
                new MessageFrame(null, "YOU HAVE NO MORE THIS ITEM");
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
                    new MessageFrame(null, "POKEMON NOT FOUND");
                } catch (IllegalStateException ex) {
                    new MessageFrame(null, "YOU HAVE NO MORE THIS ITEM");
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
    ArrayList<String> Qnt = new ArrayList<String>();
    ArrayList<Item> it = new ArrayList<Item>();
    int col;

public Panel(ArrayList<String> a, ArrayList<String> b, ArrayList<String> d,ArrayList<Item> f, int c) 
{
    this.Name1 = a;
    this.Name2 = b;
    this.Qnt = d;
    this.col = c;
    this.it = f;

    setLayout(new GridLayout(Name1.size(),col));

    for(int j = 0; j<Name1.size();j++)
    {	if (j==0) {
    	add(new JLabel(Name1.get(j)));
        add(new JLabel(Name2.get(j)));
        add(new JLabel(Qnt.get(j)));
        JButton esci2 = new JButton("Exit");
        esci2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Zaino.dispose();
            }
        });
        add(esci2);
    	j++;
    }
        final Item itm = it.get(j);
        add(new JLabel(Name1.get(j)));
        add(new JLabel(Name2.get(j)));
        add(new JLabel(Qnt.get(j)));
        JButton usa = new JButton("Use");
        usa.addActionListener(new ActionListener() {     
            Item i = itm;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i.getType() != ItemType.POKEBALL) {
                	Zaino.selectItem(i);
                	Zaino.dispose();
                        Controller.getController().getViewController().team(true, true);
                } else {
                	Zaino.selectItem(i);
                    if (Controller.getController().getStatusController().getState() == State.FIGHTING) {
                    	Zaino.useItem(Controller.getController().getEnemyPokemonInFight());
                    } else {
                    	new MessageFrame(null, "NON PUOI CATTURARE FUORI DALLA BATTAGLIA");
                    	Zaino.useItem(null);
                    }
                }
                Zaino.dispose();
            }
        });
    /*    if (itm.getType() != ItemType.POTION && Controller.getController().getStatusController().getState() != State.FIGHTING) {
            usa.setEnabled(false);
        }*/ 
        add(usa);
        
       }
	}
}