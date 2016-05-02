package controller.keyboard;

import controller.parameters.Directions;

/**
 * The {@link KeyboardController} active for the menu.
 * This class implements the SINGLETON programmation pattern
 */
public class MenuKeyboardController implements KeyboardController {

    private static MenuKeyboardController SINGLETON;
    
    /**
     * Private constructor, used by the method getController
     */
    private MenuKeyboardController() {}
    
    /** 
     * @return the curent {@link MenuKeyboardController}, or a new {@link MenuKeyboardController}
     * if this is the first time this method is invoked
     */
    public static MenuKeyboardController getController() {
        if (SINGLETON == null) {
            synchronized (MenuKeyboardController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new MenuKeyboardController();
                }
            }
        }
        return SINGLETON;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean isKeyPressed() {
        return false;
    }

    @Override
    public void updateSpeed() {
        return;
    }

    @Override
    public Directions getDirection() {
        return Directions.STILL;
    }

    @Override
    public void checkEncounter() {
        return;
    }
}