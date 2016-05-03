package controller.keyboard;

import model.map.Drawable.Direction;

/**
 * The {@link KeyboardController} active for the menu.
 * This class implements the singleton programmation pattern
 */
public final class MenuKeyboardController implements KeyboardController {

    private static MenuKeyboardController singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private MenuKeyboardController() {}
    
    /** 
     * @return the curent {@link MenuKeyboardController}, or a new {@link MenuKeyboardController}
     * if this is the first time this method is invoked
     */
    public static MenuKeyboardController getController() {
        if (singleton == null) {
            synchronized (MenuKeyboardController.class) {
                if (singleton == null) {
                    singleton = new MenuKeyboardController();
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