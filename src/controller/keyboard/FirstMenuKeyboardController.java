package controller.keyboard;

import com.badlogic.gdx.Input.Keys;

import controller.main.MainController;
import controller.parameters.Directions;
import controller.parameters.State;
import controller.view.ViewController;

public class FirstMenuKeyboardController implements KeyboardController {

    private static FirstMenuKeyboardController SINGLETON;
    
    private FirstMenuKeyboardController() {}
    
    public static FirstMenuKeyboardController getController() {
        if (SINGLETON == null) {
            synchronized (FirstMenuKeyboardController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new FirstMenuKeyboardController();
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
        switch(keycode) {
        case Keys.N:
            ViewController.getController().secondMenu();
            MainController.getController().updateStatus(State.SECOND_MENU);
            break;
        case Keys.C:
            ViewController.getController().map(false);
            MainController.getController().updateStatus(State.WALKING);
            break;
        }
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