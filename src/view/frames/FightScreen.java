package view.frames;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.MainController;
import view.View;

public class FightScreen extends JWindow implements MyFrame {

    private static final long serialVersionUID = -3997502312610503237L;	 
    
    private FightPanel mainPanel;
    
    	public FightScreen() {
            this.setAlwaysOnTop(true);
            this.setFocusable(true);
            this.setAlwaysOnTop(true);
            this.setMinimumSize(new Dimension(450, 300));
            this.setLocationRelativeTo(null);
            this.getContentPane().setLayout(null);
            this.mainPanel = new FightPanel(MainController.getController().getEnemyPokemonInFight(), MainController.getController().getSquad().getPokemonList().get(0));
            this.mainPanel.setBounds(0,0, 450, 300);
            this.mainPanel.setBorder(new LineBorder(Color.GRAY, 4));
            this.getContentPane().add(mainPanel);
    	}
    
        public void repaintFrame() {       	
            this.mainPanel.refresh();       	
        }
        
        public void showMessage(String... message) {
            View.getView().hideCurrent();
            if (MainController.getController().getSquad().getPokemonList().get(0).getCurrentHP() == 0) {
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
        }

        @Override
        public void resumeFrame() {
            this.repaintFrame();
            this.setVisible(true);
        }
}