package view.frames;

import java.awt.*;  
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import controller.MainController;
import controller.ViewController;
import controller.parameters.FilePath;
import controller.parameters.State;
import exceptions.SquadFullException;
import model.inventory.InventoryImpl;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Pokedex;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;  
import java.util.HashMap;
import java.util.Map;

public class InserisciNome {  
	
	    private JFrame f = new JFrame("PokeJavaMon");
	    private static JTextField nickname = new JTextField();
	    private static String s;
	    
	    public static String getPlayerName() {
	        s = nickname.getText();
	        return s;
	    }
	    
	    public InserisciNome() {
			nickname.requestFocusInWindow();
			f.setFocusable(true);
			f.setResizable(false);
		
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setBounds(100, 100, 400, 300);
			f.getContentPane().setLayout(null);
			
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
            
           	JTextArea inserisciNome = new JTextArea();
			inserisciNome.setHighlighter(null);
			inserisciNome.setEditable(false);
	    	inserisciNome.setBackground(SystemColor.control);
	    	inserisciNome.setFont(new Font("Elephant", Font.PLAIN, 16));
	    	inserisciNome.setText("Nome (4 - 20 [blazeit])");
	    	inserisciNome.setBounds(102, 41, 190, 35);
	    	f.getContentPane().add(inserisciNome);
	    	nickname.setBounds(102, 111, 190, 22);
	    	f.getContentPane().add(nickname);
	    	nickname.setColumns(40);    	
	    	
	    	JButton uno = new JButton("uno");
	    	uno.setBounds(21, 160, 100, 25);
	    	f.getContentPane().add(uno);
	    	
	    	JButton due = new JButton("due");
	    	due.setBounds(146, 160, 100, 25);
	    	f.getContentPane().add(due);
	    	
	    	JButton tre = new JButton("tre");
	    	tre.setBounds(272, 160, 100, 25);
	    	f.getContentPane().add(tre);
	    	
	    	StarterPanel panel_1 = new StarterPanel();
	    	panel_1.setBounds(20, 196, 352, 50);
	    	f.getContentPane().add(panel_1);
	    	panel_1.setLayout(null);
			
	    	uno.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			s = nickname.getText();
	    			if (s.length() < 4 || s.length() > 20) {
	    	        	JOptionPane.showMessageDialog(uno, "You Naive Idiot");
	    	           }
	    	        else {
	    	        	try {
		                     PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.BULBASAUR, 5));
		                 } catch (SquadFullException ex) {
		                     ex.printStackTrace();
		                 }
	    	        ViewController.getController().setName(nickname.getText());
	                ViewController.getController().map(true);
	                MainController.getController().updateStatus(State.WALKING);
	                f.dispose();
	    	        }
	    		}});
	    	    
	    	due.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			s = nickname.getText();
	    			if (s.length() < 4 || s.length() > 20) {
	    	        	JOptionPane.showMessageDialog(due, "You Naive Idiot");
	    	           }
	    	        else {
	    	        	try {
		                     PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.CHARMANDER, 5));
		                 } catch (SquadFullException ex) {
		                     ex.printStackTrace();
		                 }
	    	        ViewController.getController().setName(nickname.getText());
	                ViewController.getController().map(true);
	                MainController.getController().updateStatus(State.WALKING);
	                f.dispose();
	    	        }
	    		}});
	    	 
	    	tre.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			s = nickname.getText();
	    			if (s.length() < 4 || s.length() > 20) {
	    	        	JOptionPane.showMessageDialog(tre, "You Naive Idiot");
	    	           }
	    	        else {
	    	        	try {
		                    PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 5));
		                } catch (SquadFullException ex) {
		                    ex.printStackTrace();
		                }
	    	        ViewController.getController().setName(nickname.getText());
	                ViewController.getController().map(true);
	                MainController.getController().updateStatus(State.WALKING);
	                f.dispose();
	    	        }
	    		}});
			f.setVisible(true);
			}
		}
class StarterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
		private BufferedImage image;
		private BufferedImage image2;
		private BufferedImage image3;
		
    	public StarterPanel() {
    	
    		try {                
    	          	image = ImageIO.read(new File("F:/Users/User/workspace/PokeJavaMonTrial/resources/sprites/front/F001.png"));
    	       	} catch (IOException ex) {
    	       		ex.printStackTrace();
    	       }
    	    
    		try {                
    				image2 = ImageIO.read(new File("F:/Users/User/workspace/PokeJavaMonTrial/resources/sprites/front/F004.png"));
    			} catch (IOException ex) {
    				ex.printStackTrace();
         	}
      
    		try {                
    				image3 = ImageIO.read(new File("F:/Users/User/workspace/PokeJavaMonTrial/resources/sprites/front/F007.png"));
    			} catch (IOException ex) {
    				ex.printStackTrace();
    		}
    	}
	
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            if (image != null) {

                g.drawImage(image, 10, -10, this);
                g.drawImage(image2, 140, -10, this);
                g.drawImage(image3, 270, -10, this);
            }

            g.dispose();
        }
}
