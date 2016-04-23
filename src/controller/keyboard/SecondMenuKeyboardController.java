package controller.keyboard;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Input.Keys;

import controller.MainController;
import controller.ViewController;
import controller.parameters.Directions;
import controller.parameters.State;
import view.frames.InserisciNome;

public class SecondMenuKeyboardController implements KeyboardController {

    private static SecondMenuKeyboardController SINGLETON;
    
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
        switch (keycode) {
        case Keys.ENTER:
            String name = InserisciNome.getPlayerName();
            if (name.length() < 4 || name.length() > 20) {
                JOptionPane.showMessageDialog(null,"You Naive Idiot");
            }
            else {
                // Chiudere il vecchio frame
                ViewController.getController().setName(name);
                ViewController.getController().map(true);
                MainController.getController().updateStatus(State.WALKING);
            }
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
    
    public static SecondMenuKeyboardController getController() {
        if (SINGLETON == null) {
            synchronized (SecondMenuKeyboardController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new SecondMenuKeyboardController();
                }
            }
        }
        return SINGLETON;
    }

    @Override
    public void updateSpeed() {
        return;
    }

    @Override
    public Directions getDirection() {
        return Directions.STILL;
    }
}