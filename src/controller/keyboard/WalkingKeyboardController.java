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
import view.frames.MessageFrame;
import view.resources.MainGameView;
import view.sprite.PlayerSprite;

/**
 * The {@link KeyboardController} active when the {@link State} is WALKING.
 * This class implements the SINGLETON programmation pattern
 */
public class WalkingKeyboardController implements KeyboardController {
    
    private final WalkingKeyboardControllerResolver resolver = new WalkingKeyboardControllerResolver();
    private static final int INCREMENT = 1;
    private static final int SPEED = 2;
    private static final int NULL_SPEED = 0;
    private int keys, x, y;
    private Direction direction = PlayerImpl.getPlayer().getDirection();
    private Direction oppositeDirection = resolver.changeOppositeDirection();
    private PokeMapImpl pm;
    private TileType t;
    private boolean left, right, up, down;
    
    @Override
    public boolean keyDown(final int keycode) {
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
                    MainGameView.getMapImpl();
                    final TileType t = MainGameView.getMapImpl().getTileNextToPlayer(direction);
                    switch (direction) {
                    case EAST:
                        x = PlayerImpl.getPlayer().getTileX() + INCREMENT;
                        y = PlayerImpl.getPlayer().getTileY();
                        break;
                    case NONE:
                        break;
                    case NORTH:
                        x = PlayerImpl.getPlayer().getTileX();
                        y = PlayerImpl.getPlayer().getTileY() - INCREMENT;
                        break;
                    case SOUTH:
                        x = PlayerImpl.getPlayer().getTileX();
                        y = PlayerImpl.getPlayer().getTileY() + INCREMENT;
                        break;
                    case WEST:
                        x = PlayerImpl.getPlayer().getTileX() - INCREMENT;
                        y = PlayerImpl.getPlayer().getTileY();
                        break;
                    default:
                        break;
                    }
                    if (t == TileType.CENTER) {
                        resolver.resolvePokemonCenter();
                    } else if (t == TileType.MARKET) {
                        Controller.getController().getViewController().market();
                    } else if (t == TileType.SIGN) {
                        resolver.resolveSign();
                    } else if (t == TileType.NPC) {
                    	resolver.resolveNPC();
                    }
                }
                break;
            default:
                break;
        }
        return false;
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
        pm = MainGameView.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY());
        if (t == TileType.TELEPORT && pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).isPresent() 
                && !(pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).get() instanceof BadgeTeleport)) {
            resolver.resolveTeleport();
            Controller.getController().getStatusController().updateMusic();
            return;
        } else if (t == TileType.TELEPORT && pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).isPresent() 
                && pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).get() instanceof BadgeTeleport 
                && ((BadgeTeleport) pm.getTeleport(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()).get()).canTeleport()) {
            System.out.println("BADGE TELEPORT ACTIVE");
            resolver.resolveTeleport();
            Controller.getController().getStatusController().updateMusic();
            return;
        }
        if (up) {
            resolver.resolveMove(Direction.NORTH);
            PlayerImpl.getPlayer().move(Direction.NORTH, pm);
        } else if (down) {
            resolver.resolveMove(Direction.SOUTH);
            PlayerImpl.getPlayer().move(Direction.SOUTH, pm);
        } else if (left) {
            resolver.resolveMove(Direction.WEST);
            PlayerImpl.getPlayer().move(Direction.WEST, pm);
        } else if (right) {
            resolver.resolveMove(Direction.EAST);
            PlayerImpl.getPlayer().move(Direction.EAST, pm);
        } else {
            PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
        }
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
    
    @Override
    public void checkEncounter() {
        pm = MainGameView.getMapImpl();
        PlayerSprite.getSprite().updatePosition();
        t = pm.getTileType(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY());
        if (t == TileType.POKEMON_ENCOUNTER && (up || down || left || right )) {
            int x; 
            int y;
            x = PlayerImpl.getPlayer().getTileX();
            y = PlayerImpl.getPlayer().getTileY();
            if (pm.getEncounterZone(x, y).isPresent() && pm.getEncounterZone(x, y).get().contains(x, y) && pm.getEncounterZone(x, y).get().isEncounterNow()) {
                final Pokemon poke = pm.getEncounterZone(x, y).get().getPokemonEncounter();
                Controller.getController().getFightController().newFightWithPokemon(poke);
                Controller.getController().getViewController().fightScreen();
                up = false;
                down = false;
                left = false;
                right = false;
                PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
            }
        }
    }
    
    /**
     * Private nested class that contains several methods needed by {@link WalkingKeyboardController}
     */
    private class WalkingKeyboardControllerResolver {
        
        /**
         * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
         * a pokemon center
         */
        private void resolvePokemonCenter() {
            Controller.getController().updateStatus(State.READING);
            new MessageFrame(State.WALKING, "POKEMON'S HEALTH FULLY RESTORED");
            PlayerImpl.getPlayer().getSquad().healAllPokemon(pm);
        }
        
        /**
         * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
         * a sign
         */
        private void resolveSign() {
            Controller.getController().updateStatus(State.READING);
            if (pm.getSign(x, y).isPresent()) {
                new MessageFrame(State.WALKING, pm.getSign(x, y).get().getMessage());
            } else {
                new MessageFrame(State.WALKING, "SIGN_MESSAGE");
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
                if (pm.getTrainer(x, y).get().isDefeated()) {
                    Controller.getController().updateStatus(State.READING);
                    new MessageFrame(State.WALKING, "TRAINER ALREADY DEFEATED");
                } else {
                    Controller.getController().updateStatus(State.FIGHTING);
                    Controller.getController().getFightController().newFightWithTrainer(pm.getTrainer(x, y).get());
                    Controller.getController().getViewController().fightScreen();
                }
            } else if (pm.getNPC(x, y).isPresent()) {
                if (direction != Direction.NONE) {
                    pm.getNPC(x, y).get().turn(oppositeDirection);
                }
                Controller.getController().updateStatus(State.READING);
                new MessageFrame(State.WALKING, pm.getNPC(x, y).get().getMessage());
            } else if (pm.getGymLeader(x, y).isPresent()) {
                if (direction != Direction.NONE) {
                    pm.getGymLeader(x, y).get().turn(oppositeDirection);
                }
                if (pm.getGymLeader(x, y).get().isDefeated()) {
                    Controller.getController().updateStatus(State.READING);
                    new MessageFrame(State.WALKING, "GYM LEADER ALREADY DEFEATED");
                } else {
                    Controller.getController().updateStatus(State.FIGHTING);
                    Controller.getController().getFightController().newFightWithTrainer(pm.getGymLeader(x, y).get());
                    Controller.getController().getViewController().fightScreen();
                }        
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
                PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
                PlayerImpl.getPlayer().setPosition(t.get().getDestinationX(), t.get().getDestinationY());
            }    
        }
        
        /**
         * Resolve the case player selects to move
         */
        private void resolveMove(final Direction direction) {
            pm = MainGameView.getMapImpl();
            t = pm.getTileNextToPlayer(direction);
            System.out.println("Player Pos: " + PlayerImpl.getPlayer().getPosition() + "Tile to direction " + direction + " = " + t.toString());
            if (t == TileType.POKEMON_ENCOUNTER || t == TileType.TELEPORT || t == TileType.TERRAIN) {
                switch (direction) {
                case EAST:
                    PlayerSprite.getSprite().setVelocity(SPEED, NULL_SPEED);
                    break;
                case NONE:
                    break;
                case NORTH:
                    PlayerSprite.getSprite().setVelocity(NULL_SPEED, SPEED);
                    break;
                case SOUTH:
                    PlayerSprite.getSprite().setVelocity(NULL_SPEED, -SPEED);
                    break;
                case WEST:
                    PlayerSprite.getSprite().setVelocity(-SPEED, NULL_SPEED);
                    break;
                default:
                    break;
                }
            } else {
                PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
            }

        }
        
        private Direction changeOppositeDirection() {
            switch (direction) {
            case EAST:
                return Direction.WEST;
            case NONE:
                break;
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
            case WEST:
                return Direction.EAST;
            default:
                break;
            }
            return Direction.NONE;
        }
    }
}