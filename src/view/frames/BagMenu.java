package view.frames;

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

public class BagMenu extends JWindow implements MyFrame {

    private static final long serialVersionUID = 4403659276705962715L;
    private Item itemToUse;
    private JPanel panel;
    private final ArrayList<String> Name1 = new ArrayList<String>();
    private final ArrayList<String> Name2 = new ArrayList<String>();
    private final ArrayList<String> Qnt = new ArrayList<String>();
    private final ArrayList<Item> it = new ArrayList<Item>();
    private int cols = 1;
    private JButton exit;
    private JButton use;

    public void selectItem(Item it) {
        itemToUse = it;
    }

    public void useItem(Pokemon p) {
        if (itemToUse != null) {
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
                if (itemToUse instanceof Potion) {
                    try {
                        MainController.getController().effectItem(itemToUse, p);
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
        panel = new JPanel();
        this.setContentPane(panel);  
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        panel.setLayout(new GridLayout(1,1));
        Name1.add("TYPE");
        Name2.add("NAME");
        Qnt.add("QUANTITY");
        it.add(null);   
        for (Item i : MainController.getController().getInventory().getSubInventory(ItemType.POTION).keySet()) { 
            if (MainController.getController().getInventory().getSubInventory(ItemType.POTION).get(i) != 0) {
                Name1.add(i.getType().name()); 
                Name2.add(i.toString()); 
                Qnt.add("" + MainController.getController().getInventory().getSubInventory(ItemType.POTION).get(i));
                it.add(i);
            }
        }  
        for (Item i : MainController.getController().getInventory().getSubInventory(ItemType.POKEBALL).keySet()) { 
            if (MainController.getController().getInventory().getSubInventory(ItemType.POKEBALL).get(i) != 0) {
                Name1.add(i.getType().name());
                Name2.add(i.toString()); 
                Qnt.add("" + MainController.getController().getInventory().getSubInventory(ItemType.POKEBALL).get(i));
                it.add(i);
            }
        }  
        for (Item i : MainController.getController().getInventory().getSubInventory(ItemType.BOOST).keySet()) { 
            if (MainController.getController().getInventory().getSubInventory(ItemType.BOOST).get(i) != 0) {
                Name1.add(i.getType().name()); 
                Name2.add(i.toString()); 
                Qnt.add("" + MainController.getController().getInventory().getSubInventory(ItemType.BOOST).get(i));
                it.add(i);
            }
        } 
        for(int j = 0; j<Name1.size();j++) {   
            if (j==0) {
            	panel.add(new JLabel(Name1.get(j)));
            	panel.add(new JLabel(Name2.get(j)));
                panel.add(new JLabel(Qnt.get(j)));
                exit = new JButton("Exit");
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    }
                });
                panel.add(exit);
                j++;
            }
            final Item itm = it.get(j);
            panel.add(new JLabel(Name1.get(j)));
            panel.add(new JLabel(Name2.get(j)));
            panel.add(new JLabel(Qnt.get(j)));
            use = new JButton("Use");
            use.addActionListener(new ActionListener() {     
                Item i = itm;
                @Override
                public void actionPerformed(ActionEvent e) {            
                    if (i.getType() == ItemType.POTION) {
                        selectItem(i);
                        TeamMenu sq = new TeamMenu(true, true);
                        View.getView().hideCurrent();
                        View.getView().addNew(sq);
                        View.getView().showCurrent();
                    } else {
                        selectItem(i);
                        if (MainController.getController().getStatusController().getState() == State.FIGHTING) {
                            if (i.getType() == ItemType.POKEBALL) {
                                useItem(MainController.getController().getEnemyPokemonInFight());
                            } else if (i.getType() == ItemType.BOOST) {
                                useItem(MainController.getController().getPlayer().getSquad().getPokemonList().get(0));
                            } else {
                                selectItem(i);
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
            	use.setEnabled(false);
            }
            panel.add(use);
        }
        
        panel.setLayout(new GridLayout(Name1.size(), cols));
        this.setSize(600,60 * Name1.size());
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