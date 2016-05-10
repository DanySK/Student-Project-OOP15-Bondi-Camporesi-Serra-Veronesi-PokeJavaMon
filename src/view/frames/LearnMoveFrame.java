package view.frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.View;

public class LearnMoveFrame extends JWindow implements MyFrame {        
        
        private static final long serialVersionUID = -4826245459412294421L;
        private JPanel panel;
        private JButton move1;
        private JButton move2;
        private JButton move3;
        private JButton move4;
        private JButton move5;
        private JLabel tooltip;
        private JLabel forget1;
        private JLabel forget2;
        private JLabel forget3;
        private JLabel forget4;
        private JLabel forget5;
        private Move newMove;
        private Pokemon pk;
        
        public LearnMoveFrame(final Move mv) {
            this.newMove = mv;
            this.pk = Controller.getController().getPlayer().getSquad().getPokemonList().get(0);
        }
        @Override
        public void showFrame() {
                this.setAlwaysOnTop(true); 
                this.setBounds(100, 100, 220, 300);
                panel = new JPanel();
                this.setContentPane(panel);   
                panel.setBorder(new LineBorder(Color.GRAY, 4));
                panel.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));   
                this.getContentPane().add(Box.createVerticalGlue());
                tooltip = new JLabel("Pokemon already knows 4 moves!");
                tooltip.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(tooltip);
                this.getContentPane().add(Box.createVerticalGlue());
                forget5 = new JLabel("Do not learn:");
                forget5.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(forget5);
                move5 = new JButton(newMove.name());
                move5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    } 
                });
                move5.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(move5);
                this.getContentPane().add(Box.createVerticalGlue());
                forget1 = new JLabel("Forget:");
                forget1.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(forget1);
                move1 = new JButton(pk.getCurrentMoves().get(0).name());
                move1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.learnMove(pk.getCurrentMoves().get(0), newMove);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    } 
                });
                move1.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(move1);
                this.getContentPane().add(Box.createVerticalGlue());
                forget2 = new JLabel("Forget:");
                forget2.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(forget2);
                move2 = new JButton(pk.getCurrentMoves().get(1).name());
                move2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.learnMove(pk.getCurrentMoves().get(1), newMove);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    } 
                });
                move2.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(move2);
                this.getContentPane().add(Box.createVerticalGlue());
                forget3 = new JLabel("Forget:");
                forget3.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(forget3);
                move3 = new JButton(pk.getCurrentMoves().get(2).name());
                move3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.learnMove(pk.getCurrentMoves().get(2), newMove);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    } 
                });
                move3.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(move3);
                this.getContentPane().add(Box.createVerticalGlue());
                forget4 = new JLabel("Forget:");
                forget4.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(forget4);
                move4 = new JButton(pk.getCurrentMoves().get(3).name());
                move4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pk.learnMove(pk.getCurrentMoves().get(3), newMove);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    } 
                });
                move4.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(move4);
                this.getContentPane().add(Box.createVerticalGlue());
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