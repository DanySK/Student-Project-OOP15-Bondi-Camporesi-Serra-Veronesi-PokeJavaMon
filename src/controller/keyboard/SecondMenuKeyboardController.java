package controller.keyboard;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Input.Keys;

import controller.MainController;
import controller.parameters.State;
import view.resources.SecondMenu;

/**
 * The {@link KeyboardController} active for the second menu.
 */
public class SecondMenuKeyboardController extends AbstractKeyboardController {
    
    private static final int MIN = 4;
    private static final int MAX = 20;
    private final String name = "SecondMenuKeyboardController";

    public SecondMenuKeyboardController() {
        // EMPTY CONSTRUCTOR
    }
    
    @Override
    public boolean keyUp(final int keycode) {
        if (keycode == Keys.ENTER) {
            final String name = SecondMenu.getPlayerName();
            if (name.length() < MIN || name.length() > MAX) {
                JOptionPane.showMessageDialog(null,"You Naive Idiot");
            }
            else {
                MainController.getController().getViewController().setName(name);
                MainController.getController().getViewController().map(true);
                MainController.getController().updateStatus(State.WALKING);
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}