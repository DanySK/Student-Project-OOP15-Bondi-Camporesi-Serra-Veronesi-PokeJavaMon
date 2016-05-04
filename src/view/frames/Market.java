package view.frames;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import exceptions.NotEnoughMoneyException;
import model.items.Item;
import model.items.Item.ItemType;
import model.player.PlayerImpl;
import view.resources.MessageFrame;
import view.resources.Play;

public class Market {

        private static JFrame frame;
        
public Market() {
                
    frame = new JFrame("Market");
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setAlwaysOnTop(true);
    frame.setSize(600,600);
    frame.setUndecorated(true);
    
    JPanel contiene = new JPanel();
    frame.setContentPane(contiene);
    contiene.setLayout(new GridLayout(1,1));
    contiene.setBorder(new LineBorder(Color.GRAY, 4));
    
    final ArrayList<String>Name1 = new ArrayList<String>();
    final ArrayList<String>Name2 = new ArrayList<String>();
    final ArrayList<String>Prz = new ArrayList<String>();
    final ArrayList<String>Qnt = new ArrayList<String>();
    final ArrayList<Item> it = new ArrayList<Item>();
    
    Name1.add("TIPO");
    Name2.add("NOME");
    Prz.add("PREZZO");
    Qnt.add("QUANTITÀ");
    it.add(null);
    
    for (Item i : Play.getMapImpl().getPokeMarket().getAvailableItems()) { 
        Name1.add(i.getType().name()); 
        Name2.add(i.toString()); 
        Prz.add("" + i.getPrice());
        if (i.getType() == ItemType.POTION) {
            Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POTION).get(i));
        } else if (i.getType() == ItemType.POKEBALL) {
            Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.POKEBALL).get(i));
        } else {
            Qnt.add("" + PlayerImpl.getPlayer().getInventory().getSubInventory(ItemType.BOOST).get(i));
        }
        it.add(i);
            }
   
    contiene.add(new MarketPanel(Name1, Name2, Prz, Qnt, it, 1));
    
    frame.setVisible(true);
}
    
public static void dispose() {
    frame.dispose();
}
}

class MarketPanel extends JPanel
{

        private static final long serialVersionUID = 3332840867083025623L;
        ArrayList<String> Name1 = new ArrayList<String>();
    ArrayList<String> Name2 = new ArrayList<String>();
    ArrayList<String> Prz = new ArrayList<String>();
    ArrayList<String> Qnt = new ArrayList<String>();
    ArrayList<Item> it = new ArrayList<Item>();
    int col;

public MarketPanel(ArrayList<String> a, ArrayList<String> b, ArrayList<String> d,ArrayList<String> e, ArrayList<Item> f, int c) 
{
    this.Name1 = a;
    this.Name2 = b;
    this.Prz = d;
    this.Qnt = e;
    this.col = c;
    this.it = f;

    setLayout(new GridLayout(Name1.size(),col));

    for(int j = 0; j<Name1.size();j++)
    {	if (j==0) {
    	add(new JLabel("Money: "+ PlayerImpl.getPlayer().getMoney()));
        add(new JLabel(Name2.get(j)));
        add(new JLabel(Prz.get(j)));
        add(new JLabel(Qnt.get(j)));
        JButton esci2 = new JButton("Exit");
        esci2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Market.dispose();
            }
        });
        add(esci2);
    	j++;
    }
        final Item itm = it.get(j);
        add(new JLabel(Name1.get(j)));
        add(new JLabel(Name2.get(j)));
        add(new JLabel(Prz.get(j)));
        add(new JLabel(Qnt.get(j)));
        JButton usa = new JButton("Buy");
        usa.addActionListener(new ActionListener() {     
            Item i = itm;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlayerImpl.getPlayer().buyItem(i);
                } catch (NotEnoughMoneyException e1) {
                    new MessageFrame("NOT ENOUGH MONEY", null);
                }
            }
        });
        add(usa);
        
        
       }
        }
}
