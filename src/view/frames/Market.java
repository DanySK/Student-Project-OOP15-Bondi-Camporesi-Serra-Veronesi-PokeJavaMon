package view.frames;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.parameters.State;
import exceptions.NotEnoughMoneyException;
import model.items.Item;
import model.items.Item.ItemType;
import model.player.PlayerImpl;
import view.View;

public class Market extends JWindow implements MyFrame {
        
    private static final long serialVersionUID = 8636920096874072291L;
    private JPanel panel;
    private final ArrayList<String>Name1 = new ArrayList<String>();
    private final ArrayList<String>Name2 = new ArrayList<String>();
    private final ArrayList<String>Prz = new ArrayList<String>();
    private final ArrayList<String>Qnt = new ArrayList<String>();
    private final ArrayList<Item> it = new ArrayList<Item>();
    private int cols = 1;
    private JButton use;
    private JButton exit;

    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        this.setSize(600,600); 
        panel = new JPanel();
        this.setContentPane(panel);
        Name1.add("TYPE");
        Name2.add("NAME");
        Prz.add("PRICE");
        Qnt.add("QUANTITY");
        it.add(null); 
        for (Item i : Controller.getController().getPokeMap().getPokeMarket().getAvailableItems()) {         
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
        for(int j = 0; j<Name1.size();j++) {           
            if (j==0) {
            	panel.add(new JLabel("Money: "+ PlayerImpl.getPlayer().getMoney()));
            	panel.add(new JLabel(Name2.get(j)));
            	panel.add(new JLabel(Prz.get(j)));
            	panel.add(new JLabel(Qnt.get(j)));
                exit = new JButton("Exit");
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        Controller.getController().updateStatus(State.WALKING);
                    }
                });
                panel.add(exit);
                j++;
            }
            final Item itm = it.get(j);
            panel.add(new JLabel(Name1.get(j)));
            panel.add(new JLabel(Name2.get(j)));
            panel.add(new JLabel(Prz.get(j)));
            panel.add(new JLabel(Qnt.get(j)));
            use = new JButton("Buy");
            use.addActionListener(new ActionListener() {     
                Item i = itm;
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        PlayerImpl.getPlayer().buyItem(i);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        Market mk = new Market();
                        View.getView().addNew(mk);
                        View.getView().showCurrent();
                    } catch (NotEnoughMoneyException e1) {
                        View.getView().hideCurrent();
                        View.getView().addNew(new MessageFrame(null, "NOT ENOUGH MONEY"));
                        View.getView().showCurrent();
                    }
                }
            });
            panel.add(use);
        }
        panel.setLayout(new GridLayout(Name1.size(), cols));
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.setSize(100 * Name1.size(), 600);
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