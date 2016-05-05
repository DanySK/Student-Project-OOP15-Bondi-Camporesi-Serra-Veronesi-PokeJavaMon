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
import view.resources.MessageFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FightScreen extends JPanel{

    private static final long serialVersionUID = 1L;
    static JFrame frame;
    static JTextArea JTextArea;
    static JPanel panel_1;
    static JPanel panel_2;
    static JPanel panel_3;
    static MyPanel panel;
    static JButton Mossa1;
    static JButton Mossa2;
    static JButton Mossa3;
    static JButton Mossa4;
    public FightScreen() {
		frame = new JFrame("Fight");
		panel_1 = new JPanel();
		panel_2 = new JPanel();
		panel_3 = new JPanel();
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 275);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panel = new MyPanel();
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
			    Controller.getController().getViewController().bag();	        
			}
		});
		
		JButton Fuga = new JButton("Fuga");
		Fuga.setBounds(113, 30, 113, 30);
		panel_1.add(Fuga);
		Fuga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        try {
                        Controller.getController().getFightController().run();
                    } catch (CannotEscapeFromTrainerException e1) {
                        new MessageFrame(null, "CANNOT ESCAPE FROM TRAINER");
                    }
			}
		});
		if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != null) {
		    Mossa1 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0).name());
		} else {
		    Mossa1 = new JButton("NULL");
		}
		Mossa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
                if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != null) {
                    Mossa2 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1).name());
                } else {
                    Mossa2 = new JButton("NULL");
                }		
                Mossa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
                if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != null) {
                    Mossa3 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2).name());
                } else {
                    Mossa3 = new JButton("NULL");
                }
		Mossa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
                if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != null) {
                    Mossa4 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3).name());
                } else {
                    Mossa4 = new JButton("NULL");
                }
		Mossa4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
        
        public static void repaintFrame() {
            panel.repaint();
            panel_3 = new JPanel();
            panel_3.setVisible(false);
            panel_3.setBounds(0, 213, 226, 60);
            frame.getContentPane().add(panel_3);
            panel_3.setLayout(null);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != null) {
                Mossa1 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0).name());
            } else {
                Mossa1 = new JButton("NULL");
            }
            Mossa1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != null) {
                Mossa2 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1).name());
            } else {
                Mossa2 = new JButton("NULL");
            }               
            Mossa2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != null) {
                Mossa3 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2).name());
            } else {
                Mossa3 = new JButton("NULL");
            }
            Mossa3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != null) {
                Mossa4 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3).name());
            } else {
                Mossa4 = new JButton("NULL");
            }
            Mossa4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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
        }
        
        public static void showMessage(String... message) {
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
                                        FightScreen.repaintFrame();
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
		
    	@Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            try {
                image = ImageIO.read(new File(Controller.getController().getEnemyPokemonInFight().getPokemon().getFrontSprite().getAbsolutePath()));
            } catch (Exception e) {
                try {
                    image = ImageIO.read(new File(Controller.getController().getEnemyPokemonInFight().getPokemon().getFrontSprite().getResourcePath()));
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