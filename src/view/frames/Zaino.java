package view.frames;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.ViewController;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Potion;
import model.items.Potion.PotionType;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;

public class Zaino {

	private static JFrame frame;
	
public Zaino() {
		
	
    frame = new JFrame("Squadra");
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setAlwaysOnTop(true);
    frame.setSize(500,600);
    frame.setUndecorated(true);
    
    JPanel contiene = new JPanel();
    frame.setContentPane(contiene);
    contiene.setLayout(new GridLayout(1,1));

    final ArrayList<String>Name1 = new ArrayList<String>();
    final ArrayList<String>Name2 = new ArrayList<String>();
    final ArrayList<String>Prz = new ArrayList<String>();
    final ArrayList<String>Qnt = new ArrayList<String>();
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).keySet()) { 
    	Name1.add(i.getType().name()); 
    	Name2.add(i.getType().name()); 
    	Prz.add("" + i.getPrice());
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).get(i));
    	    }

    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).keySet()) { 
    	Name1.add(i.getType().name());
    	Name2.add(i.getType().name()); 
    	Prz.add("" + i.getPrice());
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).get(i));
    }
    
    for (Item i : PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).keySet()) { 
    	Name1.add(i.getType().name()); 
    	Name2.add(i.getType().name()); 
    	Prz.add("" + i.getPrice());
    	Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).get(i));
    }
   
    contiene.add(new Panel2(Name1, Name2, Prz, Qnt, 1));
    
    frame.setVisible(true);
}
    
public static void dispose() {
    frame.dispose();
}
}

class Panel extends JPanel
{

	private static final long serialVersionUID = 3332840867083025623L;
	ArrayList<String> Name1 = new ArrayList<String>();
    ArrayList<String> Name2 = new ArrayList<String>();
    ArrayList<String> Prz = new ArrayList<String>();
    ArrayList<String> Qnt = new ArrayList<String>();
    int col;

public Panel(ArrayList<String> a, ArrayList<String> b, ArrayList<String> d,ArrayList<String> e, int c) 
{
    this.Name1 = a;
    this.Name2 = b;
    this.Prz = d;
    this.Qnt = e;
    this.col = c;

    setLayout(new GridLayout(Name1.size(),col));

    for(int j = 0; j<Name1.size();j++)
    {
        add(new JLabel(Name1.get(j)));
        add(new JTextField(Name2.get(j)));
        add(new JTextField(Prz.get(j)));
        add(new JTextField(Qnt.get(j)));
        JButton usa = new JButton("Usa");
        add(usa);
       }
	}
}