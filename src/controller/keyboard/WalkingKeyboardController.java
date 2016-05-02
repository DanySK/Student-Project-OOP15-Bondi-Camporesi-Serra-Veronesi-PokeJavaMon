package controller.keyboard;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

import controller.fight.FightController;
import controller.main.MainController;
import controller.parameters.*;
import controller.view.ViewController;
import model.map.Drawable.Direction;
import model.map.PokeMapImpl;
import model.map.tile.Tile.TileType;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import view.resources.MessageFrame;
import view.resources.Play;
import view.sprite.PlayerSprite;

/**
 * The {@link KeyboardController} active when the {@link State} is WALKING.
 * This class implements the singleton programmation pattern
 */
public final class WalkingKeyboardController implements KeyboardController {
    
    private static WalkingKeyboardController singleton;
    private int keys, x, y;
    private Directions direction = Directions.DOWN;
    private PokeMapImpl pm;
    private TileType t;
    private boolean left, right, up, down;
    
    /**
     * Private constructor, used by the method getController
     */
    private WalkingKeyboardController() {}
    
    /** 
     * @return the curent {@link WalkingKeyboardController}, or a new {@link WalkingKeyboardController}
     * if this is the first time this method is invoked
     */
    public static WalkingKeyboardController getController() {
        if (singleton == null) {
            synchronized (WalkingKeyboardController.class) {
                if (singleton == null) {
                    singleton = new WalkingKeyboardController();
                }
            }
        }
        return singleton;
    }
    
    @Override
    public boolean keyDown(final int keycode) {
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
                if (!PlayerSprite.getSprite().isMoving()) {
                    ViewController.getController().showMenu();
                }
                break;
            case Keys.ENTER:
                if (!PlayerSprite.getSprite().isMoving()) {
                    Play.getMapImpl();
                    final TileType t = getTileType();                  
                    if (t == TileType.CENTER) {
                        resolvePokemonCenter();
                    } else if (t == TileType.MARKET) {
                        ViewController.getController().market();
                    } else if (t == TileType.SIGN) {
                        resolveSign();
                    } else if (t == TileType.NPC) {
                    	resolveNPC();
                    }
                }
                break;
            default:
                break;
        }
        return false;
    }
    
    /**
     * @return the {@link TileType} of the {@link TiledMapTile} next to the player 
     * depending on the current {@link Directions}
     */
    private TileType getTileType() {
        switch (direction) {
        case UP:
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = 299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16) - 1;
            return pm.getTileType(x, y);
        case DOWN:
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = 299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16) + 1;
            return pm.getTileType(x, y);
        case LEFT:
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16 - 1;
            y = 299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16);
            return pm.getTileType(x, y);
        case RIGHT:
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16 + 1;
            y = 299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16);
            return pm.getTileType(x, y);
        case STILL:
            return null;
        default:
            break;
        }
        return null;
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * a pokemon center
     */
    private void resolvePokemonCenter() {
        MainController.getController().updateStatus(State.READING);
        new MessageFrame("POKEMON'S HEALTH FULLY RESTORED", State.WALKING);
        PlayerImpl.getPlayer().getSquad().healAllPokemon(pm);
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * a sign
     */
    private void resolveSign() {
        MainController.getController().updateStatus(State.READING);
        if (pm.getSign(x, y).isPresent()) {
            new MessageFrame(pm.getSign(x, y).get().getMessage(), State.WALKING);
        } else {
            new MessageFrame("SIGN_MESSAGE", State.WALKING);
        }
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * an npc
     */
    private void resolveNPC() {
        if (pm.getTrainer(x, y).isPresent()) {
            switch(direction) {
            case LEFT:
                pm.getTrainer(x, y).get().turn(Direction.EAST);
                break;
            case RIGHT:
                pm.getTrainer(x, y).get().turn(Direction.WEST);
                break;
            case UP:
                pm.getTrainer(x, y).get().turn(Direction.SOUTH);
                break;
            case DOWN:
                pm.getTrainer(x, y).get().turn(Direction.NORTH);
                break;
            case STILL:
                break;
            default:
                break;
            }
            MainController.getController().updateStatus(State.FIGHTING);
            FightController.getController().newFightWithTrainer(pm.getTrainer(x, y).get());
            ViewController.getController().fightScreen(pm.getTrainer(x, y).get().getSquad().getPokemonList().get(0));
        } else if (pm.getNPC(x, y).isPresent()) {
            switch(direction) {
            case LEFT:
                pm.getNPC(x, y).get().turn(Direction.EAST);
                break;
            case RIGHT:
                pm.getNPC(x, y).get().turn(Direction.WEST);
                break;
            case UP:
                pm.getNPC(x, y).get().turn(Direction.SOUTH);
                break;
            case DOWN:
                pm.getNPC(x, y).get().turn(Direction.NORTH);
                break;
            case STILL:
                break;
            default:
                break;
            }
            MainController.getController().updateStatus(State.READING);
            new MessageFrame(pm.getNPC(x, y).get().getMessage(), State.WALKING);
        } else if (pm.getGymLeader(x, y).isPresent()) {
            switch(direction) {
            case LEFT:
                pm.getGymLeader(x, y).get().turn(Direction.EAST);
                break;
            case RIGHT:
                pm.getGymLeader(x, y).get().turn(Direction.WEST);
                break;
            case UP:
                pm.getGymLeader(x, y).get().turn(Direction.SOUTH);
                break;
            case DOWN:
                pm.getGymLeader(x, y).get().turn(Direction.NORTH);
                break;
            case STILL:
                break;
            default:
                break;
            }
            MainController.getController().updateStatus(State.FIGHTING);
            FightController.getController().newFightWithTrainer(pm.getGymLeader(x, y).get());
            ViewController.getController().fightScreen(pm.getGymLeader(x, y).get().getSquad().getPokemonList().get(0));
        }
    }

    @Override
    public boolean keyUp(final int keycode) {
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
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(final char character) {
        return false;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
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
    
    /**
     * Add 1 to the keys that are currently pressed
     */
    private void addKey() {
        this.keys ++;
    }
    
    /**
     * Remove 1 to the keys that are currently pressed
     */
    private void removeKey() {
        this.keys --;
    }
    
    @Override
    public boolean isKeyPressed() {
        return keys > 0;
    }

    @Override
    public void updateSpeed() {
        pm = Play.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
        if (t == TileType.TELEPORT) {
            resolveTeleport();
            return;
        }
        if (up) {
            resolveUP();
        } else if (down) {
            resolveDOWN();
        } else if (left) {
            resolveLEFT();
        } else if (right) {
            resolveRIGHT();
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} where the player
     * is on is a teleport
     */
    private void resolveTeleport() {
        int x; 
        int y;
        x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
        y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
        if (pm.getTeleport(x, y).isPresent()) {
            PlayerSprite.getSprite().setPlayerPosition(pm.getTeleport(x, y).get().getDestinationX(), pm.getTeleport(x, y).get().getDestinationY());
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }
    
    /**
     * Resolve the case player selects to move up
     */
    private void resolveUP() {
        direction = Directions.UP;
        pm = Play.getMapImpl();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, 299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16) - 1);
        if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
            PlayerSprite.getSprite().setVelocity(0, 2);
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }
    
    /**
     * Resolve the case player selects to move down
     */
    private void resolveDOWN() {
        direction = Directions.DOWN;
        pm = Play.getMapImpl();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, 299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16) + 1);
        if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
            PlayerSprite.getSprite().setVelocity(0, -2);
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }
    
    /**
     * Resolve the case player selects to move left
     */
    private void resolveLEFT() {
        direction = Directions.LEFT;
        pm = Play.getMapImpl();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16 - 1, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
        if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
            PlayerSprite.getSprite().setVelocity(-2, 0);
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }
    
    /**
     * Resolve the case player selects to move right
     */
    private void resolveRIGHT() {
        direction = Directions.RIGHT;
        pm = Play.getMapImpl();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16 + 1, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
        if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
            PlayerSprite.getSprite().setVelocity(2, 0);
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }

    @Override
    public Directions getDirection() {
        return direction;
    }
    
    @Override
    public void checkEncounter() {
        pm = Play.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
        if (t == TileType.POKEMON_ENCOUNTER && (up || down || left || right)) {
            int x;
            int y;
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
            if (pm.getEncounterZone(x, y).isPresent() && pm.getEncounterZone(x, y).get().isInsideZone(x, y) && pm.getEncounterZone(x, y).get().isEncounterNow()) {
                final Pokemon poke = pm.getEncounterZone(x, y).get().getPokemonEncounter();
                FightController.getController().newFightWithPokemon(poke);
                ViewController.getController().fightScreen(poke);
                up = false;
                down = false;
                left = false;
                right = false;
                PlayerSprite.getSprite().setVelocity(0, 0);
            }
        }
    }
}