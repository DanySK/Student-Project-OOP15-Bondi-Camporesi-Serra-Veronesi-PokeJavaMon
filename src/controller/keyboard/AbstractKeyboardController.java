package controller.keyboard;

import model.map.Drawable.Direction;

/**
 * Abstract class that others {@link KeyboardController}s can extend
 */
public abstract class AbstractKeyboardController implements KeyboardController {

    protected AbstractKeyboardController() {
        // EMPTY CONSTRUCTOR
    }
    
    @Override
    public boolean keyDown(final int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(final int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(final char character) {
        return false;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
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