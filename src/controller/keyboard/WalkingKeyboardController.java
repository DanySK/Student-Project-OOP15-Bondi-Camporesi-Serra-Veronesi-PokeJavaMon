package controller.keyboard;

import com.badlogic.gdx.Input.Keys;
import controller.parameters.Direction;
import model.resources.Player;
import view.resources.ViewController;

public class WalkingKeyboardController implements KeyboardController {

    private int keys = 0;
    
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                addKey();
                Player.move(Direction.UP);
                break;
            case Keys.A:
            case Keys.LEFT:
                addKey();
                Player.move(Direction.LEFT);
                break;
            case Keys.D:
            case Keys.RIGHT:
                addKey();
                Player.move(Direction.RIGHT);
                break;
            case Keys.S:
            case Keys.DOWN:
                addKey();
                Player.move(Direction.DOWN);
                break; 
            case Keys.ENTER:
                if (!Player.isMoving() && keys == 0) {
                    ViewController.showMenu();
                }
                break; 
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                removeKey();
                Player.stop();
                break;
            case Keys.A:
            case Keys.LEFT:
                removeKey();
                Player.stop();
                break;
            case Keys.D:
            case Keys.RIGHT:
                removeKey();
                Player.stop();
                break;
            case Keys.S:
            case Keys.DOWN:
                removeKey();
                Player.stop();
                break;
        }
        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }
    
    private void addKey() {
        this.keys ++;
    }
    
    private void removeKey() {
        this.keys --;
    }
    
    public boolean isKeyPressed() {
        return (keys > 0);
    }
}