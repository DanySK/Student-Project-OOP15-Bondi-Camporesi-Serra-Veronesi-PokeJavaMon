package controller.keyboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.badlogic.gdx.Input.Keys;
import controller.MainController;
import controller.ViewController;
import controller.parameters.*;
import model.map.PokeMapImpl;
import model.map.tile.Tile.TileType;
import model.resources.Player;
import view.resources.Play;

public class WalkingKeyboardController implements KeyboardController {
    
    private static WalkingKeyboardController SINGLETON;
    private int keys = 0;
    private Directions d = Directions.DOWN;
    
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                addKey();
                Player.move(Directions.UP);
                d = Directions.UP;
                break;
            case Keys.A:
            case Keys.LEFT:
                addKey();
                Player.move(Directions.LEFT);
                d = Directions.LEFT;
                break;
            case Keys.D:
            case Keys.RIGHT:
                addKey();
                Player.move(Directions.RIGHT);
                d = Directions.RIGHT;
                break;
            case Keys.S:
            case Keys.DOWN:
                addKey();
                Player.move(Directions.DOWN);
                d = Directions.DOWN;
                break; 
            case Keys.ESCAPE:
                if (!Player.isMoving() && keys == 0) {
                    ViewController.getController().showMenu();
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
            case Keys.ENTER:
                PokeMapImpl pm = Play.getMapImpl();
                TileType t = null;
                switch (d) {
                case UP:
                    t = pm.getTileType(Player.getPosition().getX().intValue() / 16, (299 - (Player.getPosition().getY().intValue() / 16)) - 1);
                    break;
                case DOWN:
                    t = pm.getTileType(Player.getPosition().getX().intValue() / 16, (299 - (Player.getPosition().getY().intValue() / 16)) + 1);
                    break;
                case LEFT:
                    t = pm.getTileType((Player.getPosition().getX().intValue() / 16) - 1, (299 - (Player.getPosition().getY().intValue() / 16)));
                    break;
                case RIGHT:
                    t = pm.getTileType((Player.getPosition().getX().intValue() / 16) + 1, (299 - (Player.getPosition().getY().intValue() / 16)));
                    break;
                }
                if (t == TileType.SIGN) {
                    MainController.getController().updateStatus(State.READING);
                    JFrame fr = new JFrame();
                    JPanel pa = new JPanel();
                    JTextArea tx = new JTextArea("SIGN_MESSAGE");
                    JButton button = new JButton("OK");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainController.getController().updateStatus(State.WALKING);
                            fr.dispose();
                        }
                    });
                    tx.setEditable(false);
                    pa.add(tx);
                    pa.add(button);
                    fr.add(pa);
                    fr.setAlwaysOnTop(true);
                    fr.setBounds(100, 100, 450, 300);
                    fr.setUndecorated(true);
                    fr.setVisible(true);
                } else if (t == TileType.TRAINER) {
                    MainController.getController().updateStatus(State.FIGHTING);
                    ViewController.getController().fightScreen();
                }
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
    
    public static WalkingKeyboardController getController() {
        if (SINGLETON == null) {
            synchronized (WalkingKeyboardController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new WalkingKeyboardController();
                }
            }
        }
        return SINGLETON;
    }
}