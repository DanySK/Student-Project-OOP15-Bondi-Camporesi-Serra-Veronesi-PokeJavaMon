package view.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

import controller.MainController;
import exceptions.CannotEscapeFromTrainerException;
import model.pokemon.Move;
import model.pokemon.Stat;
import view.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FightScreen extends JWindow implements MyFrame {
/**
	 * 
	 */
//TODO: campi in minuscolo con camelCase, Quando usi i campi nella classe devono essere preceduti da this. Ripetizione di codice da eliminare, final ai campi/var che non vengono più inizializzate

	private static final long serialVersionUID = -3997502312610503237L;	
    private JTextArea dialog;
    private JPanel decisionsPanel;
    private JPanel dialogPanel;
    private JPanel movesPanel;
    private MyPanel mainPanel;
    private JButton move1;
    private JButton move2;
    private JButton move3;
    private JButton move4;
    private JButton fight;
    private JButton team;
    private JButton bag;
    private JButton run;
    private JPanel namePanel;
    private JLabel enemyName_Lvl;
    private JLabel allyName_Lvl;
    
        public void repaintFrame() {
        	mainPanel.repaint();  
        	namePanel.setVisible(false);
        	namePanel = new JPanel();           
            namePanel.setLayout(new GridLayout(1, 0, 0, 0));
        	namePanel.setBounds(50, 5, 450, 10);
            allyName_Lvl = new JLabel (MainController.getController().getSquad().getPokemonList().get(0).getPokemon().name() +   " Lv "  + MainController.getController().getSquad().getPokemonList().get(0).getStat(Stat.LVL));
            namePanel.add(allyName_Lvl);
            enemyName_Lvl = new JLabel (MainController.getController().getEnemyPokemonInFight().getPokemon().getName() + " Lv " + MainController.getController().getEnemyPokemonInFight().getStat(Stat.LVL));
            namePanel.add(enemyName_Lvl);
            this.add(namePanel);
            movesPanel = new JPanel();
            movesPanel.setVisible(false);
    		movesPanel.setBounds(0, 225, 225, 75);
    		this.add(movesPanel);
    		movesPanel.setLayout(new GridLayout(0, 2, 0, 0));
           
    		if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
            	move1 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0).name());
            } else {
            	move1 = new JButton("----");
            }
            move1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
                                MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0));
                            } else {
                                new MessageFrame(null, "MOVE NOT FOUND");
                            }
                    }
            });
            movesPanel.add(move1);            
           
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
            	move2 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1).name());
            } else {
            	move2 = new JButton("----");
            }               
            move2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
                            MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) == Move.NULLMOVE) move2.setEnabled(false);
            movesPanel.add(move2);
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
            	move3 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2).name());
            } else {
            	move3 = new JButton("----");
            }
            move3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
                            MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) == Move.NULLMOVE) move3.setEnabled(false);
            movesPanel.add(move3);
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
            	move4 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3).name());
            } else {
            	move4 = new JButton("----");
            }
            move4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
                            MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) == Move.NULLMOVE) move4.setEnabled(false);
            movesPanel.add(move4);
        }
        
        public void showMessage(String... message) {
            repaintFrame();
            dialogPanel = new JPanel();
            dialogPanel.setVisible(false);
            dialogPanel.setBounds(0, 225, 450, 75);
            this.getContentPane().add(dialogPanel);
            dialogPanel.setLayout(new GridLayout(0, 1, 0, 0));
            List<String> msgs = Arrays.asList(message);
            Iterator<String> it = msgs.iterator();
            dialog = new JTextArea(it.next());
            dialog.setEditable(false);
            dialog.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				if (it.hasNext()) {    
    				    dialog.setText(it.next());
                                } else {
                                    if (MainController.getController().getSquad().getNextAlivePokemon().isPresent() && MainController.getController().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
                                        View.getView().hideCurrent();
                                        TeamMenu tm = new TeamMenu(false, false);
                                        View.getView().addNew(tm);
                                        View.getView().showCurrent();
                                    }
                                    repaintFrame();
                                    decisionsPanel.setVisible(true);
                                    dialogPanel.setVisible(false);
                                    movesPanel.setVisible(false);
                                }
                        }
                    });
//            dialog.setWrapStyleWord(true);
//            dialog.setLineWrap(true);
            dialog.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
            dialogPanel.add(dialog);
            decisionsPanel.setVisible(false);
            dialogPanel.requestFocus();
            dialogPanel.setVisible(true);
            movesPanel.setVisible(false);   
        }

        @Override
        public void showFrame() {
        	
            decisionsPanel = new JPanel();
            dialogPanel = new JPanel();
            movesPanel = new JPanel();
            this.setAlwaysOnTop(true);
            this.setFocusable(true);
            this.setAlwaysOnTop(true);
            this.setBounds(0, 0, 450, 300);
            this.getContentPane().setLayout(null);
            namePanel = new JPanel();
            namePanel.setLayout(new GridLayout(1, 0, 0, 0));
            namePanel.setBounds(50, 5, 450, 10);
            allyName_Lvl = new JLabel (""+ MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getPokemon().name() + " Lv " + MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getStat(Stat.LVL));
            namePanel.add(allyName_Lvl);
            enemyName_Lvl = new JLabel (""+ MainController.getController().getEnemyPokemonInFight().getPokemon().getName() + " Lv " + MainController.getController().getEnemyPokemonInFight().getStat(Stat.LVL));
            namePanel.add(enemyName_Lvl);
            this.add(namePanel);
            mainPanel = new MyPanel();
            mainPanel.setBounds(0, 15, 450, 210);
            this.getContentPane().add(mainPanel);
            mainPanel.setLayout(null);
            decisionsPanel.setBounds(225, 225, 225, 75);
    		this.add(decisionsPanel);
    		decisionsPanel.setLayout(new GridLayout(0, 2, 0, 0));
            dialogPanel.setVisible(false);
            dialogPanel.setBounds(0, 225, 450, 75);
            this.getContentPane().add(dialogPanel);
            dialogPanel.setLayout(new GridLayout(0, 1, 0, 0));           
            movesPanel.setVisible(false);
    		movesPanel.setBounds(0, 225, 225, 75);
    		this.add(movesPanel);
    		movesPanel.setLayout(new GridLayout(0, 2, 0, 0));
    		fight = new JButton("Fight");
            fight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    decisionsPanel.setVisible(false);
                    dialogPanel.setVisible(false);
                    movesPanel.setVisible(true);
                }
            });
            decisionsPanel.add(fight);            
            team = new JButton("Team");
            team.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                        View.getView().hideCurrent();
                        TeamMenu tm = new TeamMenu(true, false);
                        View.getView().addNew(tm);
                        View.getView().showCurrent();
                    } else {
                        View.getView().hideCurrent();
                        View.getView().addNew(new MessageFrame(null, "NO POKEMON ALIVE"));
                        View.getView().showCurrent();
                    }
                }
            });
            decisionsPanel.add(team);
            bag = new JButton("Bag");
            decisionsPanel.add(bag);
            bag.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    View.getView().hideCurrent();
                    BagMenu za = new BagMenu();
                    View.getView().addNew(za);
                    View.getView().showCurrent();               
                }
            });
            run = new JButton("Run");
            decisionsPanel.add(run);
            run.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            try {
                    MainController.getController().getFightController().run();
                } catch (CannotEscapeFromTrainerException e1) {
                    View.getView().hideCurrent();
                    View.getView().addNew(new MessageFrame(null, "CANNOT ESCAPE FROM TRAINER"));
                    View.getView().showCurrent();
                }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
            	move1 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0).name());
            } else {
            	move1 = new JButton("----");
            }
            move1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
                                MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(0));
                            } else {
                                new MessageFrame(null, "MOVE NOT FOUND");
                            }
                    }
            });
            movesPanel.add(move1);            
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
            	move2 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1).name());
            } else {
            	move2 = new JButton("----");
            }               
            move2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
                            MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) == Move.NULLMOVE) move2.setEnabled(false);
            movesPanel.add(move2);
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
            	move3 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2).name());
            } else {
            	move3 = new JButton("----");
            }
            move3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
                            MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) == Move.NULLMOVE) move3.setEnabled(false);
            movesPanel.add(move3);
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
            	move4 = new JButton(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3).name());
            } else {
            	move4 = new JButton("----");
            }
            move4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            decisionsPanel.setVisible(false);
                            movesPanel.setVisible(false);
                        if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
                            MainController.getController().getFightController().attack(MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) == Move.NULLMOVE) move4.setEnabled(false);
            movesPanel.add(move4);  
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
            this.repaintFrame();
            this.setVisible(true);
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
            image = ImageIO.read(new File(MainController.getController().getEnemyPokemonInFight().getPokemon().getFrontSprite().getAbsolutePath()));
        } catch (Exception e) {
            try {
                image = ImageIO.read(new File(MainController.getController().getEnemyPokemonInFight().getPokemon().getFrontSprite().getResourcePath()));
            } catch (IOException e1) {
                System.out.println("CANNOT LOAD FRONTSPRITEFOLDER SPRITE");
            }
        }
        try {                
            image2 = ImageIO.read(new File(MainController.getController().getSquad().getPokemonList().get(0).getPokemon().getBackSprite().getAbsolutePath()));
        } catch (Exception ex) {
            try {
                image2 = ImageIO.read(new File(MainController.getController().getSquad().getPokemonList().get(0).getPokemon().getBackSprite().getResourcePath()));
            } catch (IOException e) {
                System.out.println("CANNOT LOAD BACKSPRITEFOLDER SPRITE");
            }
        }
        if (image != null) {
            int width = 150;
            double maxHP2 = MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getStat(Stat.HP); /*get maxHealth*/
            double HP2 = MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getCurrentHP(); /*get currentHealth*/
            double Scale2 = HP2 / maxHP2;
            double currentExp = MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getStat(Stat.EXP);
            double maxExp = MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getStat(Stat.EXP) + MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getNecessaryExp();
            double scale3 = currentExp / maxExp;
            double maxHP = MainController.getController().getEnemyPokemonInFight().getStat(Stat.HP); /*get maxHealth*/
            double HP = MainController.getController().getEnemyPokemonInFight().getCurrentHP(); /*get currentHealth*/
            double Scale = HP / maxHP;
            g.drawRect(20, 20, width, 7);
            g.drawRect(272, 130, width, 7);
            g.drawRect(272,137,width,5);
            if (HP <= maxHP / 5) {
                g.setColor(Color.RED);
            } else if (HP <= maxHP / 2) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(21, 21, (int) (width * Scale)-1, 6);
            if (HP2 <= maxHP2 / 5) {
                g.setColor(Color.RED);
            } else if (HP2 <= maxHP2 / 2) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(273, 131, (int) (width * Scale2)-1, 6);
            g.setColor(Color.BLUE);
            g.fillRect(273,138,(int) (width * scale3)-1, 4);
            repaint();
            g.drawImage(image, 300, 20, this);
            g.drawImage(image2, 50, 130, this);
        }
        g.dispose();
    }
}