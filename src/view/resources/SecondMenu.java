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
import model.pokemon.Pokedex;

public class SecondMenu {  
	
    private JFrame f;
    private JTextArea insertName;
    private JButton firstStarter;
    private JButton secondStarter;
    private JButton thirdStarter;
    private StarterPanel starterPanel;    
    private static JTextField nickname = new JTextField();
    private static String s;
	        
    public static String getPlayerName() {
        s = nickname.getText();
	return s;      
    }
	    
    public SecondMenu() {
        this.f = new JFrame("PokeJavaMon");
	nickname.requestFocusInWindow();
	this.f.setFocusable(true);
	this.f.setResizable(false);
	try {
	    this.f.setIconImage(Toolkit.getDefaultToolkit().getImage(Img.PALLA.getAbsolutePath()));
	} catch (Exception e) {
	    //TODO: Fare catch di una semplice Exception e' sbagliato
	    this.f.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(Img.PALLA.getResourcePath()).getPath()));
	}
	this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.f.setBounds(100, 100, 400, 300);
	this.f.getContentPane().setLayout(null);
	this.insertName = new JTextArea();
	this.insertName.setHighlighter(null);
	this.insertName.setEditable(false);
	this.insertName.setBackground(SystemColor.control);
	this.insertName.setFont(new Font("Elephant", Font.PLAIN, 16));
	this.insertName.setText("INSERT NAME");
	this.insertName.setBounds(130, 41, 190, 35);
	this.f.getContentPane().add(this.insertName);
	nickname.setBounds(102, 106, 190, 22);
	this.f.setLocationRelativeTo(null);
	this.f.getContentPane().add(nickname);
	nickname.setColumns(40);    	
	this.firstStarter = new JButton("Bulbasaur");
	this.firstStarter.setBounds(21, 160, 105, 25);
	this.f.getContentPane().add(this.firstStarter);
	this.secondStarter = new JButton("Charmander");
	this.secondStarter.setBounds(146, 160, 105, 25);
	this.f.getContentPane().add(this.secondStarter);
	this.thirdStarter = new JButton("Squirtle");
	this.thirdStarter.setBounds(272, 160, 105, 25);
	this.f.getContentPane().add(this.thirdStarter);
	this.starterPanel = new StarterPanel();
	this.starterPanel.setBounds(20, 196, 352, 50);
	this.f.getContentPane().add(this.starterPanel);
	this.starterPanel.setLayout(null);
	this.addListener(firstStarter, Pokedex.BULBASAUR);
	this.addListener(secondStarter, Pokedex.CHARMANDER);
	this.addListener(thirdStarter, Pokedex.SQUIRTLE);
	this.f.setVisible(true);
    }
    
    private void addListener(final JButton b, final Pokedex p) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s = nickname.getText();
                if (s.length() < 4 || s.length() > 20) {
                    JOptionPane.showMessageDialog(b, "Insert a valid NAME");
                } else {
                    MainController.getController().selectStarter(p);
                    MainController.getController().getViewController().setName(nickname.getText());
                    MainController.getController().getViewController().map(true);
                    f.dispose();
                }
            }
        });
    }
}
class StarterPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    
    public StarterPanel() {
        this.image1 = this.loadImages(FrontSpriteImage.BULBASAUR);
        this.image2 = this.loadImages(FrontSpriteImage.CHARMANDER);
    	this.image3 = this.loadImages(FrontSpriteImage.SQUIRTLE);
    }
    
    private BufferedImage loadImages(final FrontSpriteImage s) {
        BufferedImage b = null;
        try {                
            b = ImageIO.read(new File(s.getAbsolutePath()));
        } catch (Exception ex) {
            try {
                b = ImageIO.read(new File(s.getResourcePath()));
            } catch (Exception e) {
                System.out.println("CANNOT LOAD SPRITE");
            }
        }
        return b;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (this.image1 != null) {
            g.drawImage(this.image1, 10, -10, this);
            g.drawImage(this.image2, 140, -10, this);
            g.drawImage(this.image3, 270, -10, this);
        }
        g.dispose();
    }
}