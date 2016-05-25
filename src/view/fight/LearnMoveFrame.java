package view.fight;

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

import controller.MainController;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.View;
import view.windows.MyFrame;
/**
 * LearnMoveFrameClass
 * 
 * @author Daniel Veronesi
 */
public class LearnMoveFrame extends JWindow implements MyFrame {        
		/**
		 * serialVersionUID
		 */
        private static final long serialVersionUID = -4826245459412294421L;
        /**
		 * panel
		 */
        private JPanel panel;
        /**
		 * A button that display the move number 1 of the pokémon.
		 */
        private JButton move1;
        /**
		 * A button that display the move number 2 of the pokémon.
		 */
        private JButton move2;
        /**
		 * A button that display the move number 3 of the pokémon.
		 */
        private JButton move3;
        /**
		 * A button that display the move number 4 of the pokémon.
		 */
        private JButton move4;
        /**
		 * A button that display the move the pokémon is trying to learn.
		 */
        private JButton move5;
        /**
		 * tooltip
		 */
        private JLabel tooltip;
        /**
		 * A label that say "forget". It is connected with the first move.
		 */
        private JLabel forget1;
        /**
		 * A label that say "forget". It is connected with the second move.
		 */
        private JLabel forget2;
        /**
		 * A label that say "forget". It is connected with the third move.
		 */
        private JLabel forget3;
        /**
		 * A label that say "forget". It is connected with the fourth move.
		 */
        private JLabel forget4;
        /**
		 * A label that say "forget". It is connected with the move the pokémon is trying to learn.
		 */
        private JLabel forget5;
        /**
		 * newMove
		 */
        private Move newMove;
        /**
		 * pk
		 */
        private Pokemon pk;
    	/**
    	 * LearnMoveFrame
    	 * @param mv
    	 */
        public LearnMoveFrame(final Move mv) {
            this.newMove = mv;
            this.pk = MainController.getController().getPlayer().get().getSquad().getPokemonList().get(0);
        }
        
        @Override
        public void showFrame() {
                this.setAlwaysOnTop(true); 
                this.setBounds(100, 100, 220, 300);
                this.panel = new JPanel();
                this.setContentPane(this.panel);   
                this.panel.setBorder(new LineBorder(Color.GRAY, 4));
                this.panel.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));   
                this.getContentPane().add(Box.createVerticalGlue());
                this.tooltip = new JLabel("Pokemon already knows 4 moves!");
                this.tooltip.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(this.tooltip);
                this.getContentPane().add(Box.createVerticalGlue());
                this.forget5 = new JLabel("Do not learn:");
                this.forget5.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(this.forget5);
                this.move5 = new JButton(this.newMove.name());
                this.move5.setBorderPainted(false);
                this.move5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        View.getView().resumeCurrent();
                    } 
                });
                this.move5.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.getContentPane().add(move5);
                this.getContentPane().add(Box.createVerticalGlue());
                this.setButton(this.forget1, this.move1, 0);
                this.setButton(this.forget2, this.move2, 1);
                this.setButton(this.forget3, this.move3, 2);
                this.setButton(this.forget4, this.move4, 3);
                this.setVisible(true);
        }
    	/**
    	 * setButton
    	 * @param l
    	 * @param b
    	 * @param x
    	 */
        private void setButton(JLabel l, JButton b, int x) {
            l = new JLabel("Forget:");
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.getContentPane().add(l);
            b = new JButton(this.pk.getCurrentMoves().get(x).name());
            b.setBorderPainted(false);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.getController().learnMove(pk, pk.getCurrentMoves().get(x), newMove);
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    View.getView().resumeCurrent();
                } 
            });
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.getContentPane().add(b);
            this.getContentPane().add(Box.createVerticalGlue());
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