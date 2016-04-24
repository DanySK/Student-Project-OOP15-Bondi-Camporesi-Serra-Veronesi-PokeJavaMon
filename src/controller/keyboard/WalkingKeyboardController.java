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
    private Directions direction = Directions.DOWN;
    private PokeMapImpl pm;
    private TileType t;
    private boolean left, right, up, down;
    
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                addKey();
                up = true;
                direction = Directions.UP;
                break;
            case Keys.A:
            case Keys.LEFT:
                addKey();
                left = true;
                direction = Directions.LEFT;
                break;
            case Keys.D:
            case Keys.RIGHT:
                addKey();
                right = true;
                direction = Directions.RIGHT;
                break;
            case Keys.S:
            case Keys.DOWN:
                addKey();
                down = true;
                direction = Directions.DOWN;
                break; 
            case Keys.ESCAPE:
                if (keys == 0) {
                    ViewController.getController().showMenu();
                }
                break;
            case Keys.ENTER:
                PokeMapImpl pm = Play.getMapImpl();
                TileType t = null;
                int x = 0, y = 0;
                switch (direction) {
                case UP:
                    x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
                    y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) - 1;
                    t = pm.getTileType(x, y);
                    break;
                case DOWN:
                    x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
                    y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) + 1;
                    t = pm.getTileType(x, y);
                    break;
                case LEFT:
                    x = (PlayerSprite.getSprite().getPosition().getX().intValue() / 16) - 1;
                    y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
                    t = pm.getTileType(x, y);
                    break;
                case RIGHT:
                    x = (PlayerSprite.getSprite().getPosition().getX().intValue() / 16) + 1;
                    y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
                    t = pm.getTileType(x, y);
                    break;
                case STILL:
                    break;
                }
                if (t == TileType.SIGN) {
                    MainController.getController().updateStatus(State.READING);
                    JFrame fr = new JFrame();
                    JPanel pa = new JPanel();
                    JTextArea tx;
                    if (pm.getSign(x, y).isPresent()) {
                        tx = new JTextArea(pm.getSign(x, y).get().getMessage());
                    } else {
                        tx = new JTextArea("SIGN_MESSAGE");
                    }
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
        return false;
    }

    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                removeKey();
                up = false;
                break;
            case Keys.A:
            case Keys.LEFT:
                removeKey();
                left = false;
                break;
            case Keys.D:
            case Keys.RIGHT:
                removeKey();
                right = false;
                break;
            case Keys.S:
            case Keys.DOWN:
                removeKey();
                down = false;
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

    @Override
    public void updateSpeed() {
        pm = Play.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
        if (t == TileType.TELEPORT) {
            int x, y;
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
            if (pm.getTeleport(x, y).isPresent()) {
                PlayerSprite.getSprite().setPlayerPosition(pm.getTeleport(x, y).get().getDestinationX(), pm.getTeleport(x, y).get().getDestinationY());
            } else {
                System.out.println("TELEPORT NOT ACTIVE");
            }
        }
        if (t == TileType.POKEMON_ENCOUNTER && (up == true || down == true || left == true || right == true)) {
            int x, y;
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
            if (pm.getEncounterZone(x, y).isPresent()) {
                if (pm.getEncounterZone(x, y).get().isInsideZone(x, y)) {
                    System.out.println("POKEMON ENCOUNTER ZONE");
                }
            }
        }
        if (up == true) {
            direction = Directions.UP;
            pm = Play.getMapImpl();
            t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) - 1);
            if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
                PlayerSprite.getSprite().setVelocity(0, 2);
            } else {
                PlayerSprite.getSprite().setVelocity(0, 0);
            }
        } else if (down == true) {
            direction = Directions.DOWN;
            pm = Play.getMapImpl();
            t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) + 1);
            if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
                PlayerSprite.getSprite().setVelocity(0, -2);
            } else {
                PlayerSprite.getSprite().setVelocity(0, 0);
            }
        } else if (left == true) {
            direction = Directions.LEFT;
            pm = Play.getMapImpl();
            t = pm.getTileType((PlayerSprite.getSprite().getPosition().getX().intValue() / 16) - 1, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
            if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
                PlayerSprite.getSprite().setVelocity(-2, 0);
            } else {
                PlayerSprite.getSprite().setVelocity(0, 0);
            }
        } else if (right == true) {
            direction = Directions.RIGHT;
            pm = Play.getMapImpl();
            t = pm.getTileType((PlayerSprite.getSprite().getPosition().getX().intValue() / 16) + 1, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
            if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
                PlayerSprite.getSprite().setVelocity(2, 0);
            } else {
                PlayerSprite.getSprite().setVelocity(0, 0);
            }
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }

    @Override
    public Directions getDirection() {
        return direction;
    }
}