package controller.keyboard;

import java.util.Optional;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

import controller.Controller;
import controller.parameters.*;
import model.map.Drawable.Direction;
import model.map.PokeMapImpl;
import model.map.tile.BadgeTeleport;
import model.map.tile.Teleport;
import model.map.tile.Tile.TileType;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import view.resources.MessageFrame;
import view.resources.Play;
import view.sprite.PlayerSprite;

/**
 * The {@link KeyboardController} active when the {@link State} is WALKING.
 * This class implements the SINGLETON programmation pattern
 */
public class WalkingKeyboardController implements KeyboardController {
    
    private static WalkingKeyboardController SINGLETON;
    private int keys = 0, x = 0, y = 0;
    private Direction direction = Direction.SOUTH;
    private Direction oppositeDirection = Direction.NORTH;
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
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                addKey();
                up = true;
                direction = Direction.NORTH;
                oppositeDirection = Direction.SOUTH;
                break;
            case Keys.A:
            case Keys.LEFT:
                addKey();
                left = true;
                direction = Direction.WEST;
                oppositeDirection = Direction.EAST;
                break;
            case Keys.D:
            case Keys.RIGHT:
                addKey();
                right = true;
                direction = Direction.EAST;
                oppositeDirection = Direction.WEST;
                break;
            case Keys.S:
            case Keys.DOWN:
                addKey();
                down = true;
                direction = Direction.SOUTH;
                oppositeDirection = Direction.NORTH;
                break; 
            case Keys.ESCAPE:
                if (!PlayerSprite.getSprite().isMoving()) {
                    Controller.getController().getViewController().showMenu();
                }
                break;
            case Keys.ENTER:
                if (!PlayerSprite.getSprite().isMoving()) {
                    Play.getMapImpl();
                    TileType t = Play.getMapImpl().getTileNextToPlayer(direction);
                    switch (direction) {
                    case EAST:
                        x = PlayerImpl.getPlayer().getTileX() + 1;
                        y = PlayerImpl.getPlayer().getTileY();
                        break;
                    case NONE:
                        break;
                    case NORTH:
                        x = PlayerImpl.getPlayer().getTileX();
                        y = PlayerImpl.getPlayer().getTileY() - 1;
                        break;
                    case SOUTH:
                        x = PlayerImpl.getPlayer().getTileX();
                        y = PlayerImpl.getPlayer().getTileY() + 1;
                        break;
                    case WEST:
                        x = PlayerImpl.getPlayer().getTileX() - 1;
                        y = PlayerImpl.getPlayer().getTileY();
                        break;
                    default:
                        break;
                    }
                    if (t == TileType.CENTER) {
                        resolvePokemonCenter();
                    } else if (t == TileType.MARKET) {
                        Controller.getController().getViewController().market();
                    } else if (t == TileType.SIGN) {
                        resolveSign();
                    } else if (t == TileType.NPC) {
                    	resolveNPC();
                    }
                }
        }
        return false;
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * a pokemon center
     */
    private void resolvePokemonCenter() {
        Controller.getController().updateStatus(State.READING);
        new MessageFrame("POKEMON'S HEALTH FULLY RESTORED", State.WALKING);
        PlayerImpl.getPlayer().getSquad().healAllPokemon(pm);
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * a sign
     */
    private void resolveSign() {
        Controller.getController().updateStatus(State.READING);
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
            if (direction != Direction.NONE) {
                pm.getTrainer(x, y).get().turn(oppositeDirection);
            }
            Controller.getController().updateStatus(State.FIGHTING);
            Controller.getController().getFightController().newFightWithTrainer(pm.getTrainer(x, y).get());
            Controller.getController().getViewController().fightScreen(pm.getTrainer(x, y).get().getSquad().getPokemonList().get(0));
        } else if (pm.getNPC(x, y).isPresent()) {
            if (direction != Direction.NONE) {
                pm.getNPC(x, y).get().turn(oppositeDirection);
            }
            Controller.getController().updateStatus(State.READING);
            new MessageFrame(pm.getNPC(x, y).get().getMessage(), State.WALKING);
        } else if (pm.getGymLeader(x, y).isPresent()) {
            if (direction != Direction.NONE) {
                pm.getGymLeader(x, y).get().turn(oppositeDirection);
            }
            Controller.getController().updateStatus(State.FIGHTING);
            Controller.getController().getFightController().newFightWithTrainer(pm.getGymLeader(x, y).get());
            Controller.getController().getViewController().fightScreen(pm.getGymLeader(x, y).get().getSquad().getPokemonList().get(0));
        }
    }

    @Override
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

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
        return (keys > 0);
    }

    @Override
    public void updateSpeed() {
        pm = Play.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY());
        if (t == TileType.TELEPORT && pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).isPresent() 
                && !(pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).get() instanceof BadgeTeleport)) {
            resolveTeleport();
            Controller.getController().getStatusController().updateMusic();
            return;
        } else if (t == TileType.TELEPORT && pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).isPresent() 
                && pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).get() instanceof BadgeTeleport) {
            if (((BadgeTeleport) pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).get()).canTeleport()) {
                System.out.println("BADGE TELEPORT ACTIVE");
                resolveTeleport();
                Controller.getController().getStatusController().updateMusic();
                return;
            }
        }
        if (up == true) {
            resolveMove(Direction.NORTH);
            PlayerImpl.getPlayer().move(Direction.NORTH, pm);
        } else if (down == true) {
            resolveMove(Direction.SOUTH);
            PlayerImpl.getPlayer().move(Direction.SOUTH, pm);
        } else if (left == true) {
            resolveMove(Direction.WEST);
            PlayerImpl.getPlayer().move(Direction.WEST, pm);
        } else if (right == true) {
            resolveMove(Direction.EAST);
            PlayerImpl.getPlayer().move(Direction.EAST, pm);
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * a teleport
     */
    private void resolveTeleport() {
        final int x = PlayerImpl.getPlayer().getTileX();
        final int y = PlayerImpl.getPlayer().getTileY();
        final Optional<Teleport> t = pm.getTeleport(x, y);
        if (t.isPresent()) {
            PlayerSprite.getSprite().setPlayerPosition(t.get().getDestinationX(), t.get().getDestinationY());
            PlayerSprite.getSprite().setVelocity(0, 0);
            PlayerImpl.getPlayer().setPosition(t.get().getDestinationX(), t.get().getDestinationY());
        }    
    }
    
    /**
     * Resolve the case player selects to move
     */
    private void resolveMove(Direction direction) {
        pm = Play.getMapImpl();
        t = pm.getTileNextToPlayer(direction);
        if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
            switch (direction) {
            case EAST:
                PlayerSprite.getSprite().setVelocity(2, 0);
                break;
            case NONE:
                break;
            case NORTH:
                PlayerSprite.getSprite().setVelocity(0, 2);
                break;
            case SOUTH:
                PlayerSprite.getSprite().setVelocity(0, -2);
                break;
            case WEST:
                PlayerSprite.getSprite().setVelocity(-2, 0);
                break;
            default:
                break;
            }
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
    
    @Override
    public void checkEncounter() {
        pm = Play.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY());
        if (t == TileType.POKEMON_ENCOUNTER && (up == true || down == true || left == true || right == true)) {
            int x, y;
            x = PlayerImpl.getPlayer().getTileX();
            y = PlayerImpl.getPlayer().getTileY();
            if (pm.getEncounterZone(x, y).isPresent() && pm.getEncounterZone(x, y).get().contains(x, y) && pm.getEncounterZone(x, y).get().isEncounterNow()) {
                Pokemon poke = pm.getEncounterZone(x, y).get().getPokemonEncounter();
                Controller.getController().getFightController().newFightWithPokemon(poke);
                Controller.getController().getViewController().fightScreen(poke);
                up = false;
                down = false;
                left = false;
                right = false;
                PlayerSprite.getSprite().setVelocity(0, 0);
            }
        }
    }
}