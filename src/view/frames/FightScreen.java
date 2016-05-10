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
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controller.Controller;
import exceptions.CannotEscapeFromTrainerException;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Stat;
import view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FightScreen extends JFrame implements MyFrame {
//TODO: campi in minuscolo con camelCase, Quando usi i campi nella classe devono essere preceduti da this. Ripetizione di codice da eliminare, final ai campi/var che non vengono più inizializzate

    private static final long serialVersionUID = 1L;
    private JTextArea JTextArea;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private MyPanel panel;
    private JButton Move1;
    private JButton Move2;
    private JButton Move3;
    private JButton Move4;
    private JButton Fight;
    private JButton Squadra;
    private JButton Zaino;
    private JButton Fuga;
        
        public void repaintFrame() {
            panel.repaint();
            panel_3 = new JPanel();
            panel_3.setVisible(false);
            panel_3.setBounds(0, 213, 226, 60);
            this.getContentPane().add(panel_3);
            panel_3.setLayout(null);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
            	Move1 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0).name());
            } else {
            	Move1 = new JButton("----");
            }
            Move1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
                                Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0));
                            } else {
                                new MessageFrame(null, "MOVE NOT FOUND");
                            }
                    }
            });
            Move1.setBounds(0, 0, 113, 30);
            panel_3.add(Move1);            
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
            	Move2 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1).name());
            } else {
            	Move2 = new JButton("----");
            }               
            Move2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
                            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            Move2.setBounds(113, 0, 113, 30);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) == Move.NULLMOVE) Move2.setEnabled(false);
            panel_3.add(Move2);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
            	Move3 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2).name());
            } else {
            	Move3 = new JButton("----");
            }
            Move3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
                            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            Move3.setBounds(0, 30, 113, 30);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) == Move.NULLMOVE) Move3.setEnabled(false);
            panel_3.add(Move3);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
            	Move4 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3).name());
            } else {
            	Move4 = new JButton("----");
            }
            Move4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
                            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            Move4.setBounds(113, 30, 113, 30);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) == Move.NULLMOVE) Move4.setEnabled(false);
            panel_3.add(Move4);
        }
        
        public void showMessage(String... message) {
            panel_2 = new JPanel();
            panel_2.setVisible(false);
            panel_2.setBounds(0, 213, 450, 60);
            this.getContentPane().add(panel_2);
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
                                        if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent() && PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
                                            View.getView().hideCurrent();
                                            TeamMenu tm = new TeamMenu(false, false);
                                            View.getView().addNew(tm);
                                            View.getView().showCurrent();
                                        }
                                        repaintFrame();
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
            panel_2.requestFocus();
            panel_2.setVisible(true);
            panel_3.setVisible(false);
        }

        @Override
        public void showFrame() {
            panel_1 = new JPanel();
            panel_2 = new JPanel();
            panel_3 = new JPanel();
            this.setAlwaysOnTop(true);
            this.setUndecorated(true);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.setFocusable(true);
            this.setAlwaysOnTop(true);
            this.setBounds(100, 100, 450, 275);
            this.getContentPane().setLayout(null);
            panel = new MyPanel();
            panel.setBounds(0, 0, 450, 212);
            this.getContentPane().add(panel);
            panel.setLayout(null);
            panel_1.setBounds(224, 213, 226, 60);
            this.getContentPane().add(panel_1);
            panel_1.setLayout(null);          
            panel_2.setVisible(false);
            panel_2.setBounds(0, 213, 450, 60);
            this.getContentPane().add(panel_2);
            panel_2.setLayout(null);           
            panel_3.setVisible(false);
            panel_3.setBounds(0, 213, 226, 60);
            this.getContentPane().add(panel_3);
            panel_3.setLayout(null);           
            Fight = new JButton("Fight");
            Fight.setBounds(0, 0, 113, 30);
            Fight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panel_1.setVisible(false);
                    panel_2.setVisible(false);
                    panel_3.setVisible(true);
                }
            });
            panel_1.add(Fight);            
            Squadra = new JButton("TeamMenu");
            Squadra.setBounds(113, 0, 113, 30);
            Squadra.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
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
            panel_1.add(Squadra);
            Zaino = new JButton("BagMenu");
            Zaino.setBounds(0, 30, 113, 30);
            panel_1.add(Zaino);
            Zaino.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    View.getView().hideCurrent();
                    BagMenu za = new BagMenu();
                    View.getView().addNew(za);
                    View.getView().showCurrent();               
                }
            });
            
            Fuga = new JButton("Fuga");
            Fuga.setBounds(113, 30, 113, 30);
            panel_1.add(Fuga);
            Fuga.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            try {
                    Controller.getController().getFightController().run();
                } catch (CannotEscapeFromTrainerException e1) {
                    View.getView().hideCurrent();
                    View.getView().addNew(new MessageFrame(null, "CANNOT ESCAPE FROM TRAINER"));
                    View.getView().showCurrent();
                }
                    }
            });
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
            	Move1 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0).name());
            } else {
            	Move1 = new JButton("NULL");
            }
            Move1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0) != Move.NULLMOVE) {
                                Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0));
                            } else {
                                new MessageFrame(null, "MOVE NOT FOUND");
                            }
                    }
            });
            Move1.setBounds(0, 0, 113, 30);
            panel_3.add(Move1);            
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
            	Move2 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1).name());
            } else {
            	Move2 = new JButton("NULL");
            }               
            Move2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) != Move.NULLMOVE) {
                            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            Move2.setBounds(113, 0, 113, 30);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1) == Move.NULLMOVE) Move2.setEnabled(false);
            panel_3.add(Move2);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
            	Move3 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2).name());
            } else {
            	Move3 = new JButton("NULL");
            }
            Move3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) != Move.NULLMOVE) {
                            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            Move3.setBounds(0, 30, 113, 30);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(2) == Move.NULLMOVE) Move3.setEnabled(false);
            panel_3.add(Move3);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
            	Move4 = new JButton(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3).name());
            } else {
            	Move4 = new JButton("NULL");
            }
            Move4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            panel_1.setVisible(false);
                            panel_3.setVisible(false);
                        if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) != Move.NULLMOVE) {
                            Controller.getController().getFightController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3));
                        } else {
                            new MessageFrame(null, "MOVE NOT FOUND");
                        }
                    }
            });
            Move4.setBounds(113, 30, 113, 30);
            if (PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(3) == Move.NULLMOVE) Move4.setEnabled(false);
            panel_3.add(Move4);
            
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
                System.out.println("CANNOT LOAD FRONTSPRITEFOLDER SPRITE");
            }
        }
        try {                
            image2 = ImageIO.read(new File(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().getBackSprite().getAbsolutePath()));
        } catch (Exception ex) {
            try {
                image2 = ImageIO.read(new File(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().getBackSprite().getResourcePath()));
            } catch (IOException e) {
                System.out.println("CANNOT LOAD BACKSPRITEFOLDER SPRITE");
            }
        }
        if (image != null) {
            int width = 150;
            double maxHP2 = PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getStat(Stat.HP); /*get maxHealth*/
            double HP2 = PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP(); /*get currentHealth*/
            double Scale2 = HP2 / maxHP2;
            double maxHP = Controller.getController().getEnemyPokemonInFight().getStat(Stat.HP); /*get maxHealth*/
            double HP = Controller.getController().getEnemyPokemonInFight().getCurrentHP(); /*get currentHealth*/
            double Scale = HP / maxHP;
            g.drawRect(20, 20, width, 7);
            g.drawRect(272, 130, width, 7);
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