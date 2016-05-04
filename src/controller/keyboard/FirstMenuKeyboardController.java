package controller.keyboard;

import com.badlogic.gdx.Input.Keys;

import controller.Controller;
import controller.parameters.State;
import model.map.Drawable.Direction;

/**
 * The {@link KeyboardController} active for the first menu.
 */
public class FirstMenuKeyboardController implements KeyboardController {
    
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
            Controller.getController().getViewController().secondMenu();
            Controller.getController().updateStatus(State.SECOND_MENU);
            break;
        case Keys.C:
            Controller.getController().getViewController().map(false);
            Controller.getController().updateStatus(State.WALKING);
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