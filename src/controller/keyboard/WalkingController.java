package controller.keyboard;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import controller.load.LoadController;
import controller.modelResources.General;
import controller.modelResources.Player;
import controller.save.SaveController;

public class WalkingController implements KeyboardControllerInterface {

    private boolean active = false;
    private Vector2 velocity = new Vector2();
    private Player pl;
    
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                if(active) {
                    velocity.y = pl.getSpeed();
                    velocity.x = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.A:
            case Keys.LEFT:
                if(active) {
                    velocity.x = -pl.getSpeed();
                    velocity.y = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.D:
            case Keys.RIGHT:
                if(active) {
                    velocity.x = pl.getSpeed();
                    velocity.y = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.S:
            case Keys.DOWN:
                if(active) {
                    velocity.y = -pl.getSpeed();
                    velocity.x = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
        }
        return true;
    }

    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                if(active) {
                    pl.stopY();
                    break;
                }
                break;
            case Keys.A:
            case Keys.LEFT:
                if(active) {
                    pl.stopX();
                    break;
                }
                break;
            case Keys.D:
            case Keys.RIGHT:
                if(active) {
                    pl.stopX();
                    break;
                }
                break;
            case Keys.S:
            case Keys.DOWN:
                if(active) {
                    pl.stopY();
                    break;
                }
                break;
            case Keys.ENTER:
                if(active) {
                    active = false;
                    pl.stop();
                    break;
                } else {
                    active = true;
                }
                break;
            case Keys.SPACE:
                if(active) {
                    float x = pl.getX();
                    float y = pl.getY();
                    General g = new LoadController().load();
                    g.xPos = x;
                    g.yPos = y;
                    new SaveController().save(g);
                }
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
    
    public void start() {
        this.active = true;
    }
    
    public void stop() {
        this.active = false;
    }
    
    public void setPlayer(Player p) {
        this.pl = p;
    }
    
    public String toString() {
        return "Active: " + active;
    }
}