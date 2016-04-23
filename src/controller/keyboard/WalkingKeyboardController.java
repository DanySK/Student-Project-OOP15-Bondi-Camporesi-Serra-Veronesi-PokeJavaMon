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
import view.PlayerSprite;
import view.resources.Play;

public class WalkingKeyboardController implements KeyboardController {
    
    private static WalkingKeyboardController SINGLETON;
    private int keys = 0;
    private Directions d = Directions.DOWN;
    private PokeMapImpl pm;
    private TileType t;
    
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                addKey();
                pm = Play.getMapImpl();
                t = null;
                t = pm.getTileType(PlayerSprite.getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)) - 1);
                if (t == TileType.TERRAIN || t == TileType.POKEMON_ENCOUNTER) {
                    PlayerSprite.getSprite().setDirection(Directions.UP);
                } else {
                    PlayerSprite.getSprite().stop();
                }
                d = Directions.UP;
                break;
            case Keys.A:
            case Keys.LEFT:
                addKey();
                pm = Play.getMapImpl();
                t = null;
                t = pm.getTileType((PlayerSprite.getPosition().getX().intValue() / 16) - 1, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)));
                if (t == TileType.TERRAIN || t == TileType.POKEMON_ENCOUNTER) {
                    PlayerSprite.getSprite().setDirection(Directions.LEFT);
                } else {
                    PlayerSprite.getSprite().stop();
                }
                d = Directions.LEFT;
                break;
            case Keys.D:
            case Keys.RIGHT:
                addKey();
                pm = Play.getMapImpl();
                t = null;
                t = pm.getTileType((PlayerSprite.getPosition().getX().intValue() / 16) + 1, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)));
                if (t == TileType.TERRAIN || t == TileType.POKEMON_ENCOUNTER) {
                    PlayerSprite.getSprite().setDirection(Directions.RIGHT);
                } else {
                    PlayerSprite.getSprite().stop();
                }
                d = Directions.RIGHT;
                break;
            case Keys.S:
            case Keys.DOWN:
                addKey();
                pm = Play.getMapImpl();
                t = null;
                t = pm.getTileType(PlayerSprite.getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)) + 1);
                if (t == TileType.TERRAIN || t == TileType.POKEMON_ENCOUNTER) {
                    PlayerSprite.getSprite().setDirection(Directions.DOWN);
                } else {
                    PlayerSprite.getSprite().stop();
                }
                d = Directions.DOWN;
                break; 
            case Keys.ESCAPE:
                if (keys == 0) {
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
                break;
            case Keys.A:
            case Keys.LEFT:
                removeKey();
                break;
            case Keys.D:
            case Keys.RIGHT:
                removeKey();
                break;
            case Keys.S:
            case Keys.DOWN:
                removeKey();
                break;
            case Keys.ENTER:
                PokeMapImpl pm = Play.getMapImpl();
                TileType t = null;
                switch (d) {
                case UP:
                    t = pm.getTileType(PlayerSprite.getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)) - 1);
                    break;
                case DOWN:
                    t = pm.getTileType(PlayerSprite.getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)) + 1);
                    break;
                case LEFT:
                    t = pm.getTileType((PlayerSprite.getPosition().getX().intValue() / 16) - 1, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)));
                    break;
                case RIGHT:
                    t = pm.getTileType((PlayerSprite.getPosition().getX().intValue() / 16) + 1, (299 - (PlayerSprite.getPosition().getY().intValue() / 16)));
                    break;
                case STILL:
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