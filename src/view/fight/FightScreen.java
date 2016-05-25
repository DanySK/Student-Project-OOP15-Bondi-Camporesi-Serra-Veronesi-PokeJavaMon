package view.fight;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.MainController;
import view.View;
import view.windows.MessageFrame;
import view.windows.MyFrame;
import view.windows.TeamMenu;
/**
 * This {@link JWindow} handles the fighting part of the games.
 * 
 * @author Daniel Veronesi
 */
public class FightScreen extends JWindow implements MyFrame {

    private static final long serialVersionUID = -3997502312610503237L;	 
    
    private FightPanel mainPanel;
	/**
	 * It creates the window where pokémon and choices are put.
	 */
    	public FightScreen() {
            this.setAlwaysOnTop(true);
            this.setFocusable(true);
            this.setAlwaysOnTop(true);
            this.setMinimumSize(new Dimension(450, 300));
            this.setLocationRelativeTo(null);
            this.getContentPane().setLayout(null);
            this.mainPanel = new FightPanel(MainController.getController().getEnemyPokemonInFight().get(), MainController.getController().getSquad().get().getPokemonList().get(0));
            this.mainPanel.setBounds(0,0, 450, 300);
            this.mainPanel.setBorder(new LineBorder(Color.GRAY, 4));
            this.getContentPane().add(mainPanel);
    	}
    	/**
    	 * It updates the informations of the frame.
    	 */
        public void repaintFrame() {       	
            this.mainPanel.refresh();       	
        }
    	/**
    	 * It is the function that shows the input.
    	 * 
    	 * @param message It is the message it will be displayed.
    	 */
        public void showMessage(String... message) {
            View.getView().hideCurrent();
            if (MainController.getController().getSquad().get().getPokemonList().get(0).getCurrentHP() == 0) {
                View.getView().addNew(new TeamMenu(false, false));
                View.getView().showCurrent();
                View.getView().hideCurrent();
            }
            View.getView().addNew(new MessageFrame(null, message));
            View.getView().showCurrent();
        }

        @Override
        public void showFrame() {
            this.setVisible(true);
        }

        @Override
        public void disposeFrame() {
            this.dispose();
        }

        @Override
        public void hideFrame() {
            this.setVisible(false);
            this.setEnabled(false);
        }

        @Override
        public void resumeFrame() {
            this.repaintFrame();
            this.setEnabled(true);
            this.setVisible(true);
        }
}