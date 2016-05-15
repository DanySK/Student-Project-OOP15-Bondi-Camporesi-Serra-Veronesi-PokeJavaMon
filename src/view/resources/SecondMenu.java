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
import model.pokemon.Pokedex;

public class SecondMenu {  
	
    private JFrame f = new JFrame("PokeJavaMon");
    private JTextArea insertName;
    private JButton firstStarter;
    private JButton	secondStarter;
    private JButton thirdStarter;
    private StarterPanel starterPanel;
    
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
	insertName = new JTextArea();
	insertName.setHighlighter(null);
	insertName.setEditable(false);
	insertName.setBackground(SystemColor.control);
	insertName.setFont(new Font("Elephant", Font.PLAIN, 16));
	insertName.setText("INSERT NAME");
	insertName.setBounds(130, 41, 190, 35);
	f.getContentPane().add(insertName);
	nickname.setBounds(102, 106, 190, 22);
	f.setLocationRelativeTo(null);
	f.getContentPane().add(nickname);
	nickname.setColumns(40);    	
	firstStarter = new JButton("Bulbasaur");
	firstStarter.setBounds(21, 160, 105, 25);
	f.getContentPane().add(firstStarter);
	secondStarter = new JButton("Charmander");
	secondStarter.setBounds(146, 160, 105, 25);
	f.getContentPane().add(secondStarter);
	thirdStarter = new JButton("Squirtle");
	thirdStarter.setBounds(272, 160, 105, 25);
	f.getContentPane().add(thirdStarter);
	starterPanel = new StarterPanel();
	starterPanel.setBounds(20, 196, 352, 50);
	f.getContentPane().add(starterPanel);
	starterPanel.setLayout(null);
	firstStarter.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        s = nickname.getText();
	    	if (s.length() < 4 || s.length() > 20) {
	    	    JOptionPane.showMessageDialog(firstStarter, "Insert a valid NAME");
	    	} else {
	    	    try {
	    	        MainController.getController().addPokemonToSquad(Pokedex.BULBASAUR);
	    	    } catch (SquadFullException ex) {
		        ex.printStackTrace();
		    }
	    	    MainController.getController().getViewController().setName(nickname.getText());
	            MainController.getController().getViewController().map(true);
	            f.dispose();
	    	}
	    }
	});
	secondStarter.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        s = nickname.getText();
	    	if (s.length() < 4 || s.length() > 20) {
	    	    JOptionPane.showMessageDialog(secondStarter, "Insert a valid NAME");
	    	} else {
	    	    try {
	                  MainController.getController().addPokemonToSquad(Pokedex.CHARMANDER);
		    } catch (SquadFullException ex) {
		        ex.printStackTrace();
		    }
	    	    MainController.getController().getViewController().setName(nickname.getText());
	            MainController.getController().getViewController().map(true);
	            f.dispose();
	    	}
	    }
	});
	thirdStarter.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        s = nickname.getText();
	        if (s.length() < 4 || s.length() > 20) {
	            JOptionPane.showMessageDialog(thirdStarter, "Insert a valid NAME");
	    	} else {
	    	    try {
	                  MainController.getController().addPokemonToSquad(Pokedex.SQUIRTLE);
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