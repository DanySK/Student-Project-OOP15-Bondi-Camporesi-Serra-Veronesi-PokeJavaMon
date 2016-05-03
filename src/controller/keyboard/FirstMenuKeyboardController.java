package controller.keyboard;

import com.badlogic.gdx.Input.Keys;

import controller.parameters.State;
import controller.status.StatusController;
import controller.view.ViewController;
import model.map.Drawable.Direction;

/**
 * The {@link KeyboardController} active for the first menu.
 * This class implements the singleton programmation pattern
 */
public final class FirstMenuKeyboardController implements KeyboardController {

    private static FirstMenuKeyboardController singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private FirstMenuKeyboardController() {}
    
    /** 
     * @return the curent {@link FirstMenuKeyboardController}, or a new {@link FirstMenuKeyboardController}
     * if this is the first time this method is invoked
     */
    public static FirstMenuKeyboardController getController() {
        if (singleton == null) {
            synchronized (FirstMenuKeyboardController.class) {
                if (singleton == null) {
                    singleton = new FirstMenuKeyboardController();
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
        switch(keycode) {
        case Keys.N:
            ViewController.getController().secondMenu();
            StatusController.getController().updateStatus(State.SECOND_MENU);
            break;
        case Keys.C:
            ViewController.getController().map(false);
            StatusController.getController().updateStatus(State.WALKING);
            break;
        default:
            break;
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