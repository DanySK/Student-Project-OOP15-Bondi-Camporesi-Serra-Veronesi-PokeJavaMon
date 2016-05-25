package view.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.MainController;
import controller.parameters.State;
import exceptions.NotEnoughMoneyException;
import model.items.Item;
import model.items.Item.ItemType;
import view.View;
/**
 * MarketClass
 * 
 * @author Daniel Veronesi
 */
public class Market extends JWindow implements MyFrame {
	/**
	 * serialVersionUID
	 */   
    private static final long serialVersionUID = 8636920096874072291L;
    /**
     * panel
     */
    private JPanel panel;
    /**
     * An array filled with the types of the items.
     */
    private final ArrayList<String>Name1;
    /**
     * An array filled with the names of the items.
     */
    private final ArrayList<String>Name2;
    /**
     * An array filled with the prizes of the items.
     */
    private final ArrayList<String>Prz;
    /**
     * An array filled with the quantity of the item that the character owns.
     */
    private final ArrayList<String>Qnt;
    /**
     * it
     */
    private final ArrayList<Item> it;
    /**
     * The number of columns.
     */
    private int cols;
    /**
     * A button that allows the player to buy the item.
     */
    private JButton buy;
    /**
     * A button that allows to exit the market.
     */
    private JButton exit;
	/**
	 * Market
	 */
    public Market() {
        this.Name1 = new ArrayList<String>();
        this.Name2 = new ArrayList<String>();
        this.Prz = new ArrayList<String>();
        this.Qnt = new ArrayList<String>();
        this.it = new ArrayList<Item>();
        this.cols = 1;    
    }

    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        this.setMinimumSize(new Dimension(700,600)); 
        this.panel = new JPanel();
        this.setContentPane(this.panel);
        this.Name1.add("TYPE");
        this.Name2.add("NAME");
        this.Prz.add("PRICE");
        this.Qnt.add("QUANTITY");
        this.it.add(null); 
        for (Item i : MainController.getController().getPokeMap().get().getPokeMarket().getAvailableItems()) {         
        	this.Name1.add(i.getType().name()); 
        	this.Name2.add(i.toString()); 
        	this.Prz.add("" + i.getPrice());
            if (i.getType() == ItemType.POTION) {
            	this.Qnt.add("" + MainController.getController().getInventory().get().getSubInventory(ItemType.POTION).get(i));
            } else if (i.getType() == ItemType.POKEBALL) {
            	this.Qnt.add("" + MainController.getController().getInventory().get().getSubInventory(ItemType.POKEBALL).get(i));
            } else {
            	this.Qnt.add("" + MainController.getController().getInventory().get().getSubInventory(ItemType.BOOST).get(i));
            }
            it.add(i);
        }
        for(int j = 0; j<Name1.size();j++) {           
            if (j==0) {
            	this.panel.add(new JLabel("Money: "+ MainController.getController().getPlayer().get().getMoney()));
            	this.panel.add(new JLabel(this.Name2.get(j)));
            	this.panel.add(new JLabel(this.Prz.get(j)));
            	this.panel.add(new JLabel(this.Qnt.get(j)));
            	this.exit = new JButton("Exit");
            	this.exit.setBorderPainted(false);
            	this.exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        MainController.getController().updateStatus(State.WALKING);
                    }
                });
            	this.panel.add(this.exit);
                j++;
            }
            final Item itm = it.get(j);
            this.panel.add(new JLabel(this.Name1.get(j)));
            this.panel.add(new JLabel(this.Name2.get(j)));
            this.panel.add(new JLabel(this.Prz.get(j)));
            this.panel.add(new JLabel(this.Qnt.get(j)));
            this.buy = new JButton("Buy");
            this.buy.setBorderPainted(false);
            this.buy.addActionListener(new ActionListener() {     
                Item i = itm;
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainController.getController().buyItem(i);
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
            this.panel.add(this.buy);
        }
        this.panel.setLayout(new GridLayout(this.Name1.size(), cols));
        this.panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.setSize(100 * Name1.size(), 600);
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