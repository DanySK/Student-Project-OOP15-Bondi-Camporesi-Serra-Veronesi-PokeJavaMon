package view.windows;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MainController;
import controller.parameters.State;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Potion;
import model.pokemon.Pokemon;
import view.View;
/**
 * BagMenuClass
 * 
 * 
 */
public class BagMenu extends JWindow implements MyFrame {
    private static final long serialVersionUID = 4403659276705962715L;
    private Item itemToUse;
    private JPanel panel;
    private final ArrayList<String> name1;
    private final ArrayList<String> name2;
    private final ArrayList<String> qnt;
    private final ArrayList<Item> it;
    private int cols;
    private JButton exit;
    private JButton use;
	/**
	 * BagMenu
	 */
    public BagMenu() {
        this.name1 = new ArrayList<String>();
        this.name2 = new ArrayList<String>();
        this.qnt = new ArrayList<String>();
        this.it = new ArrayList<Item>();
        this.cols = 1;
    }
	/**
	 * selectItem
	 */
    public void selectItem(Item it) {
        this.itemToUse = it;
    }
	/**
	 * useItem
	 */
    public void useItem(Pokemon p) {
        if (this.itemToUse != null) {
            if (MainController.getController().getStatusController().getState() == State.FIGHTING) {
                try {
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    MainController.getController().getFightController().useItem(itemToUse, p);
                    if (!View.getView().isEmpty()) {
                        View.getView().resumeCurrent();
                    }
                    selectItem(null);
                } catch (PokemonIsExhaustedException e1) {
                    selectItem(null);
                    View.getView().addNew(new MessageFrame(null, "POKEMON IS EXAUSTED"));
                    View.getView().showCurrent();
                } catch (PokemonNotFoundException e1) {
                    selectItem(null);
                    View.getView().addNew(new MessageFrame(null, "POKEMON NOT FOUND"));
                    View.getView().showCurrent();
                } catch (CannotCaughtTrainerPkmException e1) {
                    selectItem(null);
                    View.getView().addNew(new MessageFrame(null, "CANNOT CATCH TRAINER POKEMON"));
                    View.getView().showCurrent();
                } catch (IllegalStateException e1) {
                    selectItem(null);
                    View.getView().addNew(new MessageFrame(null, "YOU HAVE NO MORE THIS ITEM"));
                    View.getView().showCurrent();
                }
            } else {
                if (this.itemToUse instanceof Potion) {
                    try {
                        MainController.getController().effectItem(this.itemToUse, p);
                        disposeFrame();
                    } catch (PokemonNotFoundException e) {
                        new MessageFrame(null, "POKEMON NOT FOUND");
                    } catch (IllegalStateException ex) {
                        new MessageFrame(null, "YOU HAVE NO MORE THIS ITEM");
                        selectItem(null);
                        disposeFrame();
                    }
                }
            }
        }
    }

    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        this.panel = new JPanel();
        this.setContentPane(panel);  
        this.panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.panel.setLayout(new GridLayout(1,1));
        this.name1.add("TYPE");
        this.name2.add("NAME");
        this.qnt.add("QUANTITY");
        this.it.add(null);   
        this.forCycles(ItemType.POTION);
        this.forCycles(ItemType.POKEBALL); 
        this.forCycles(ItemType.BOOST);
        if (this.it.size() > 1) {
            for(int j = 0; j<this.name1.size();j++) {   
                if (j==0) {
                    this.panel.add(new JLabel(this.name1.get(j)));
                    this.panel.add(new JLabel(this.name2.get(j)));
                    this.panel.add(new JLabel(this.qnt.get(j)));
                    this.exit = new JButton("Exit");
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
                    j++;
                }
                final Item itm = this.it.get(j);
                this.panel.add(new JLabel(this.name1.get(j)));
                this.panel.add(new JLabel(this.name2.get(j)));
                this.panel.add(new JLabel(this.qnt.get(j)));
                this.use = new JButton("Use");
                this.use.setBorderPainted(false);
                this.use.addActionListener(new ActionListener() {     
                    Item i = itm;
                    @Override
                    public void actionPerformed(ActionEvent e) {            
                        if (this.i.getType() == ItemType.POTION) {
                            selectItem(this.i);
                            TeamMenu sq = new TeamMenu(true, true);
                            View.getView().hideCurrent();
                            View.getView().addNew(sq);
                            View.getView().showCurrent();
                        } else {
                            selectItem(this.i);
                            if (MainController.getController().getStatusController().getState() == State.FIGHTING) {
                                if (this.i.getType() == ItemType.POKEBALL) {
                                    useItem(MainController.getController().getEnemyPokemonInFight().get());
                                } else if (this.i.getType() == ItemType.BOOST) {
                                    useItem(MainController.getController().getPlayer().get().getSquad().getPokemonList().get(0));
                                } else {
                                    selectItem(this.i);
                                    TeamMenu sq = new TeamMenu(true, true);
                                    View.getView().hideCurrent();
                                    View.getView().addNew(sq);
                                    View.getView().showCurrent();
                                }
                            } else {
                                new MessageFrame(null, "NON PUOI USARE QUESTO STRUMENTO FUORI DALLA BATTAGLIA");
                                useItem(null);
                            }
                        }
                    }
                });
                if (itm.getType() != ItemType.POTION && MainController.getController().getStatusController().getState() != State.FIGHTING) {
                    this.use.setEnabled(false);
                }
                this.panel.add(this.use);
            }       
            this.panel.setLayout(new GridLayout(name1.size(), cols));
            this.setSize(600,60 * name1.size());
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } else {
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
            View.getView().addNew(new MessageFrame(null, "NO MORE ITEM IN BAG"));
            View.getView().showCurrent();
        }
    }
	/**
	 * forCycles
	 */
    private void forCycles(final ItemType it) {
        for (Item i : MainController.getController().getInventory().get().getSubInventory(it).keySet()) { 
            if (MainController.getController().getInventory().get().getSubInventory(it).get(i) != 0) {
                this.name1.add(i.getType().name()); 
                this.name2.add(i.toString()); 
                this.qnt.add("" + MainController.getController().getInventory().get().getSubInventory(it).get(i));
                this.it.add(i);
            }
        }  
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