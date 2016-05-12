package view.resources;

import java.awt.*;  
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.MainController;
import controller.parameters.Img;
import controller.parameters.FrontSpriteImage;
import exceptions.SquadFullException;
import model.player.PlayerImpl;
import model.pokemon.Pokedex;
import model.pokemon.StaticPokemonFactory;

public class SecondMenu {  
	
    private JFrame f = new JFrame("PokeJavaMon");
    private static JTextField nickname = new JTextField();
    private static String s;
	        
    public static String getPlayerName() {
        s = nickname.getText();
	return s;      
    }
	    
    public SecondMenu() {
	nickname.requestFocusInWindow();
	f.setFocusable(true);
	f.setResizable(false);
	try {
	    f.setIconImage(Toolkit.getDefaultToolkit().getImage(Img.PALLA.getAbsolutePath()));
	} catch (Exception e) {
	    //TODO: Fare catch di una semplice Exception e' sbagliato
	    f.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(Img.PALLA.getResourcePath()).getPath()));
	}
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setBounds(100, 100, 400, 300);
	f.getContentPane().setLayout(null);
        PlayerImpl.getPlayer().getBox().putCapturedPokemon(StaticPokemonFactory.createPokemon(Pokedex.RAYQUAZA, 50));
        JTextArea inserisciNome = new JTextArea();
	inserisciNome.setHighlighter(null);
	inserisciNome.setEditable(false);
	inserisciNome.setBackground(SystemColor.control);
	inserisciNome.setFont(new Font("Elephant", Font.PLAIN, 16));
	inserisciNome.setText("INSERT NAME");
	inserisciNome.setBounds(130, 41, 190, 35);
	f.getContentPane().add(inserisciNome);
	nickname.setBounds(102, 106, 190, 22);
	f.setLocationRelativeTo(null);
	f.getContentPane().add(nickname);
	nickname.setColumns(40);    	
	JButton uno = new JButton("Bulbasaur");
	uno.setBounds(21, 160, 105, 25);
	f.getContentPane().add(uno);
	JButton due = new JButton("Charmander");
	due.setBounds(146, 160, 105, 25);
	f.getContentPane().add(due);
	JButton tre = new JButton("Squirtle");
	tre.setBounds(272, 160, 105, 25);
	f.getContentPane().add(tre);
	StarterPanel panel_1 = new StarterPanel();
	panel_1.setBounds(20, 196, 352, 50);
	f.getContentPane().add(panel_1);
	panel_1.setLayout(null);
	uno.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        s = nickname.getText();
	    	if (s.length() < 4 || s.length() > 20) {
	    	    JOptionPane.showMessageDialog(uno, "Insert a valid NAME");
	    	} else {
	    	    try {
	    	        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.VENUSAUR, 50));
		        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.CHARMANDER, 5));
		        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 5));
		        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.RATTATA,5));
		        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.ZUBAT, 5));
		        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.TENTACOOL, 5));	                     
	    	    } catch (SquadFullException ex) {
		        ex.printStackTrace();
		    }
	    	    MainController.getController().getViewController().setName(nickname.getText());
	            MainController.getController().getViewController().map(true);
	            f.dispose();
	    	}
	    }
	});
	due.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        s = nickname.getText();
	    	if (s.length() < 4 || s.length() > 20) {
	    	    JOptionPane.showMessageDialog(due, "Insert a valid NAME");
	    	} else {
	    	    try {
	    	        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.CHARMANDER, 44));
		    } catch (SquadFullException ex) {
		        ex.printStackTrace();
		    }
	    	    MainController.getController().getViewController().setName(nickname.getText());
	            MainController.getController().getViewController().map(true);
	            f.dispose();
	    	}
	    }
	});
	tre.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        s = nickname.getText();
	        if (s.length() < 4 || s.length() > 20) {
	            JOptionPane.showMessageDialog(tre, "Insert a valid NAME");
	    	} else {
	    	    try {
	    	        PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.BLASTOISE, 45));
		    } catch (SquadFullException ex) {
		        ex.printStackTrace();
		    }
	    	    MainController.getController().getViewController().setName(nickname.getText());
	            MainController.getController().getViewController().map(true);
	            f.dispose();
	    	}
	    }
	});
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
            image = ImageIO.read(new File(FrontSpriteImage.BULBASAUR.getAbsolutePath()));
    	} catch (Exception ex) {
    	    try {
    	        image = ImageIO.read(new File(FrontSpriteImage.BULBASAUR.getResourcePath()));
            } catch (Exception e) {
                System.out.println("CANNOT LOAD SPRITE");
            }
    	}
    	try {                
    	    image2 = ImageIO.read(new File(FrontSpriteImage.CHARMANDER.getAbsolutePath()));
    	} catch (Exception ex) {
    	    try {
    	        image2 = ImageIO.read(new File(FrontSpriteImage.CHARMANDER.getResourcePath()));
            } catch (Exception e) {
                System.out.println("CANNOT LOAD SPRITE");
            }
        }
    	try {                
    	    image3 = ImageIO.read(new File(FrontSpriteImage.SQUIRTLE.getAbsolutePath()));
    	} catch (Exception ex) {
    	    try {
    	        image3 = ImageIO.read(new File(FrontSpriteImage.SQUIRTLE.getResourcePath()));
            } catch (Exception e) {
                System.out.println("CANNOT LOAD SPRITE");
            }
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