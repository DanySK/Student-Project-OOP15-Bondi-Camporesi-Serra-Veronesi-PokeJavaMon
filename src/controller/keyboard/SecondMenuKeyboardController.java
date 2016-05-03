package controller.keyboard;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Input.Keys;

import controller.Controller;
import controller.parameters.State;
import model.map.Drawable.Direction;
import view.frames.InserisciNome;

/**
 * The {@link KeyboardController} active for the second menu.
 * This class implements the singleton programmation pattern
 */
public final class SecondMenuKeyboardController implements KeyboardController {

    private static SecondMenuKeyboardController singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private SecondMenuKeyboardController() {}
    
    /** 
     * @return the curent {@link SecondMenuKeyboardController}, or a new {@link SecondMenuKeyboardController}
     * if this is the first time this method is invoked
     */
    public static SecondMenuKeyboardController getController() {
        if (singleton == null) {
            synchronized (SecondMenuKeyboardController.class) {
                if (singleton == null) {
                    singleton = new SecondMenuKeyboardController();
                }
            }
        }
        return singleton;
    }
    
    @Override
    public boolean keyDown(final int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(final char character) {
        return false;
    }

    @Override
    public boolean keyUp(final int keycode) {
        if (keycode == Keys.ENTER) {
            final String name = InserisciNome.getPlayerName();
            if (name.length() < 4 || name.length() > 20) {
                JOptionPane.showMessageDialog(null,"You Naive Idiot");
            }
            else {
                Controller.getController().getViewController().setName(name);
                Controller.getController().getViewController().map(true);
                Controller.getController().updateStatus(State.WALKING);
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(final int amount) {
        return false;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean isKeyPressed() {
        return false;
    }

    @Override
    public void updateSpeed() {
        // EMPTY METHOD
    }

    @Override
    public Direction getDirection() {
        return Direction.NONE;
    }

    @Override
    public void checkEncounter() {
        // EMPTY METHOD
    }
}