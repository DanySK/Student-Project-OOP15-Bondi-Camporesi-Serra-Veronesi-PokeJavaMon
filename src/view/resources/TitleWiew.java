package view.resources;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.MainController;
import controller.ViewController;
import controller.parameters.FilePath;
import controller.parameters.State;
import exceptions.SquadFullException;
import model.inventory.InventoryImpl;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.player.PlayerImpl;
import model.pokemon.PokemonDB;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;

public class TitleWiew {
    
    private JFrame frame;
    
    public TitleWiew() {
        frame = new JFrame("PokeJavaMon");
    }
    
    public void title() {
        
        frame.setResizable(false);
        frame.setLocation(100, 100);
        try {
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FilePath.PALLA.getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(FilePath.PALLA.getResourcePath()).getPath()));
        }
        JPanel pane = new JPanel();
        JLabel text = new JLabel("Benvenuto in PokeJavaMon!!!");
        pane.add(text);
        pane.setPreferredSize(new Dimension(400,200));
        text.setOpaque(false);	
        frame.setMinimumSize(new Dimension(500,300));
        frame.setFocusable(true);
        JButton nuova = new JButton("NUOVA PARTITA");
        nuova.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // Inizializza un po di roba
                try {
//                    PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(PokemonDB.RAICHU, 50));
                    PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(PokemonDB.CHARMANDER, 5));
                } catch (SquadFullException ex) {
                    ex.printStackTrace();
                }
                Map<String, Integer> potionList = new HashMap<>();
                Map<String, Integer> boostList = new HashMap<>();
                Map<String, Integer> ballList = new HashMap<>();
                potionList.put(PotionType.POTION.name(), 10);
                potionList.put(PotionType.SUPERPOTION.name(), 20);
                potionList.put(PotionType.HYPERPOTION.name(), 30);
                boostList.put(Stat.SPD.name() + "X", 35);
                boostList.put(Stat.DEF.name() + "X", 25);
                boostList.put(Stat.ATK.name() + "X", 15);
                ballList.put(PokeballType.Greatball.name(), 20);
                ballList.put(PokeballType.Ultraball.name(), 30);
                ballList.put(PokeballType.Pokeball.name(), 10);
                InventoryImpl.initializeInventory(potionList, boostList, ballList);
                MainController.getController().updateStatus(State.SECOND_MENU);
                ViewController.getController().secondMenu();
            }
        });
        pane.add(nuova);
        JButton continua = new JButton("CONTINUA");
        continua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewController.getController().map(false);
                MainController.getController().updateStatus(State.WALKING);
                frame.dispose();
            }
        });
        pane.add(continua);
        frame.add(pane);
        frame.setVisible(true);
    }
}
