package controller.keyboard;

import controller.parameters.Directions;

/**
 * The {@link KeyboardController} active during a fight.
 * This class implements the singleton programmation pattern
 */
public final class FightingKeyboardController implements KeyboardController {

    private static FightingKeyboardController singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private FightingKeyboardController() {}
    
    /** 
     * @return the curent {@link FightingKeyboardController}, or a new {@link FightingKeyboardController}
     * if this is the first time this method is invoked
     */
    public static FightingKeyboardController getController() {
        if (singleton == null) {
            synchronized (FightingKeyboardController.class) {
                if (singleton == null) {
                    singleton = new FightingKeyboardController();
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
    public Directions getDirection() {
        return Directions.STILL;
    }

    @Override
    public void checkEncounter() {
        // EMPTY METHOD
    }
}