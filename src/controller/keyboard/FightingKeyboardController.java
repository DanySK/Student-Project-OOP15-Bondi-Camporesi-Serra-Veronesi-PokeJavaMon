package controller.keyboard;

import com.badlogic.gdx.Input.Keys;
import controller.ViewController;
import controller.parameters.Directions;
import model.player.PlayerImpl;

public class FightingKeyboardController implements KeyboardController {

    private static FightingKeyboardController SINGLETON;
    
    private FightingKeyboardController() {}
    
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
        case Keys.NUM_1:
            ViewController.getController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(0));
            break;
        case Keys.NUM_2:
            ViewController.getController().attack(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().get(1));
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

    public static FightingKeyboardController getController() {
        if (SINGLETON == null) {
            synchronized (FightingKeyboardController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new FightingKeyboardController();
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