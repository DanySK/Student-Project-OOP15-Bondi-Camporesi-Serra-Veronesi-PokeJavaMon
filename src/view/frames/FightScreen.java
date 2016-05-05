package view.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controller.Controller;
import exceptions.CannotEscapeFromTrainerException;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import view.resources.MessageFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FightScreen extends JPanel{

    private static final long serialVersionUID = 1L;
    static JFrame frame;
    static JTextArea JTextArea;
    static JPanel panel_1 = new JPanel();
    static JPanel panel_2 = new JPanel();
    static JPanel panel_3 = new JPanel();
    public FightScreen(Pokemon pk) {
		frame = new JFrame("Fight");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 275);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		MyPanel panel = new MyPanel(pk);
		panel.setBounds(0, 0, 450, 212);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1.setBounds(224, 213, 226, 60);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_2.setVisible(false);
		panel_2.setBounds(0, 213, 450, 60);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		panel_3.setVisible(false);
		panel_3.setBounds(0, 213, 226, 60);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton Fight = new JButton("Fight");
		Fight.setBounds(0, 0, 113, 30);
		Fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("FIGHT");
				panel_1.setVisible(false);
				panel_2.setVisible(false);
                panel_3.setVisible(true);
			}
		});
		panel_1.add(Fight);
		
		JButton Squadra = new JButton("Squadra");
		Squadra.setBounds(113, 0, 113, 30);
		Squadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("TEAM");
//				ViewController.team();
                        if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                            Controller.getController().getViewController().team(true, false);
                        } else {
                            new MessageFrame(null, "NO POKEMON ALIVE");
                        }
			}
		});
		panel_1.add(Squadra);
		
		JButton Zaino = new JButton("Zaino");
		Zaino.setBounds(0, 30, 113, 30);
		panel_1.add(Zaino);
		Zaino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("BAG");
			    Controller.getController().getViewController().bag();	        
			}
		});
		
		JButton Fuga = new JButton("Fuga");
		Fuga.setBounds(113, 30, 113, 30);
		panel_1.add(Fuga);
		Fuga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("RUN");
			        try {
                        Controller.getController().getFightController().run();
                    } catch (CannotEscapeFromTrainerException e1) {
                        new MessageFrame(null, "CANNOT ESCAPE FROM TRAINER");
                    }
			}
		});

		JButton Mossa1 = new JButton("Mossa1");
		Mossa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("1");
				panel_1.setVisible(false);
                                panel_3.setVisible(false);
			        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != null) {
			            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0));
			        } else {
			            new MessageFrame(null, "MOVE NOT FOUND");
			        }
			}
		});
		Mossa1.setBounds(0, 0, 113, 30);
		panel_3.add(Mossa1);
		
		JButton Mossa2 = new JButton("Mossa2");
		Mossa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("2");
				panel_1.setVisible(false);
                                panel_3.setVisible(false);
			    if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != null) {
			        Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1));
			    } else {
			        new MessageFrame(null, "MOVE NOT FOUND");
                            }
			}
		});
		Mossa2.setBounds(113, 0, 113, 30);
		if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) == null) Mossa2.setEnabled(false);
		panel_3.add(Mossa2);
		
		JButton Mossa3 = new JButton("Mossa3");
		Mossa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("3");
				panel_1.setVisible(false);
                                panel_3.setVisible(false);
			    if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != null) {
			        Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2));
			    } else {
			        new MessageFrame(null, "MOVE NOT FOUND");
                            }
			}
		});
		Mossa3.setBounds(0, 30, 113, 30);
		if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) == null) Mossa3.setEnabled(false);
		panel_3.add(Mossa3);
		
		JButton Mossa4 = new JButton("Mossa4");
		Mossa4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("4");
				panel_1.setVisible(false);
                                panel_3.setVisible(false);
			    if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != null) {
			        Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3));
			    } else {
			        new MessageFrame(null, "MOVE NOT FOUND");
                            }
			}
		});
		Mossa4.setBounds(113, 30, 113, 30);
		if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) == null) Mossa4.setEnabled(false);
		panel_3.add(Mossa4);
		
		frame.setVisible(true);
	}
    
        public static void dispose() {
            frame.dispose();
        }
        
        public static void showMessage(String... message) {
            System.out.println("KAPPA");
            panel_2 = new JPanel();
            panel_2.setVisible(false);
            panel_2.setBounds(0, 213, 450, 60);
            frame.getContentPane().add(panel_2);
            panel_2.setLayout(null);
            List<String> msgs = Arrays.asList(message);
            Iterator<String> it = msgs.iterator();
            JTextArea = new JTextArea(it.next());
            JTextArea.setEditable(false);
            JTextArea.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                                    if (it.hasNext()) {
                                        JTextArea.setText(it.next());
                                    } else {
                                        panel_1.setVisible(true);
                                        panel_2.setVisible(false);
                                        panel_3.setVisible(false);
                                    }
                                }
                    }});
            JTextArea.setWrapStyleWord(true);
            JTextArea.setLineWrap(true);
            JTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
            JTextArea.setBounds(0, 0, 444, 60);
            panel_2.add(JTextArea);
            panel_1.setVisible(false);
            panel_2.setVisible(true);
            panel_3.setVisible(false);
        }
}

	class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
		private BufferedImage image2;
		
    	public MyPanel(Pokemon pk) {
    	
    	    try {
                image = ImageIO.read(new File(pk.getPokemon().getFrontSprite().getAbsolutePath()));
            } catch (Exception e) {
                try {
                    image = ImageIO.read(new File(pk.getPokemon().getFrontSprite().getResourcePath()));
                } catch (IOException e1) {
                    System.out.println("CANNOT LOAD FRONT SPRITE");
                }
            }
           try {                
        	image2 = ImageIO.read(new File(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().getBackSprite().getAbsolutePath()));
           } catch (Exception ex) {
               try {
                image2 = ImageIO.read(new File(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().getBackSprite().getResourcePath()));
            } catch (IOException e) {
                System.out.println("CANNOT LOAD BACK SPRITE");
            }
           }
    	}
	
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            if (image != null) {

            	int width = 150;
            	double maxHP = 200; /*get maxHealth*/
        		double HP = 200; /*get currentHealth*/
        		double Scale = HP / maxHP;
        			
        		double maxHP2 = 200; /*get maxHealth*/
        		double HP2 = 75; /*get currentHealth*/
        		double Scale2 = HP2 / maxHP2;
        		
            	g.drawRect(20, 20, width, 7);
                g.drawRect(272, 130, width, 7);
                
               /* g.drawRect (10, 10, width, 5); */
                g.fillRect(20, 20, (int) (width * Scale), 7);
                g.fillRect(272, 130, (int) (width * Scale2), 7);
                repaint();
                repaint();
                
                g.drawImage(image, 300, 20, this);
                g.drawImage(image2, 50, 130, this);
            }

            g.dispose();
        }
    }