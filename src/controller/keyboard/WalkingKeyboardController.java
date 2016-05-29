package controller.keyboard;

import java.util.Optional;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

import controller.MainController;
import controller.parameters.*;
import model.map.Drawable.Direction;
import model.map.PokeMap;
import model.map.tile.BadgeTeleport;
import model.map.tile.Teleport;
import model.map.tile.Tile.TileType;
import model.player.Player;
import model.pokemon.Pokemon;
import view.View;
import view.sprite.PlayerSprite;
import view.windows.MessageFrame;

/**
 * The {@link KeyboardController} active when the {@link State} is WALKING.
 * This class implements the SINGLETON programmation pattern
 */
public class WalkingKeyboardController extends AbstractKeyboardController {
    
    private static final int INCREMENT = 1;
    private static final int SPEED = 2;
    private static final int NULL_SPEED = 0;
    private final String name;
    private WalkingKeyboardControllerResolver resolver;
    private int keys;
    private int x;
    private int y;
    private PokeMap pm;
    private TileType t;
    private Player player;
    private Direction direction;
    private Direction oppositeDirection;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    public WalkingKeyboardController() {
        this.name = "WalkingKeyboardController";
        this.resolver = new WalkingKeyboardControllerResolver();
        this.player = MainController.getController().getPlayer().get();
        this.direction = this.player.getDirection();
        this.oppositeDirection = resolver.changeOppositeDirection();
        this.pm = MainController.getController().getPokeMap().get();
    }
    
    @Override
    public boolean keyDown(final int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                addKey();
                this.up = true;
                this.direction = Direction.NORTH;
                this.oppositeDirection = resolver.changeOppositeDirection();
                break;
            case Keys.A:
            case Keys.LEFT:
                addKey();
                this.left = true;
                this.direction = Direction.WEST;
                this.oppositeDirection = resolver.changeOppositeDirection();
                break;
            case Keys.D:
            case Keys.RIGHT:
                addKey();
                this.right = true;
                this.direction = Direction.EAST;
                this.oppositeDirection = resolver.changeOppositeDirection();
                break;
            case Keys.S:
            case Keys.DOWN:
                addKey();
                this.down = true;
                this.direction = Direction.SOUTH;
                this.oppositeDirection = resolver.changeOppositeDirection();
                break; 
            case Keys.ESCAPE:
                if (!PlayerSprite.getSprite().isMoving()) {
                    MainController.getController().getViewController().showMenu();
                }
                break;
            case Keys.ENTER:
                if (!PlayerSprite.getSprite().isMoving()) {
                    final TileType t = this.pm.getTileNextToPlayer(direction);
                    switch (direction) {
                    case EAST:
                        this.x = this.player.getTileX() + INCREMENT;
                        this.y = this.player.getTileY();
                        break;
                    case NONE:
                        break;
                    case NORTH:
                        this.x = this.player.getTileX();
                        this.y = this.player.getTileY() - INCREMENT;
                        break;
                    case SOUTH:
                        this.x = this.player.getTileX();
                        this.y = this.player.getTileY() + INCREMENT;
                        break;
                    case WEST:
                        this.x = this.player.getTileX() - INCREMENT;
                        this.y = this.player.getTileY();
                        break;
                    default:
                        break;
                    }
                    if (t == TileType.CENTER) {
                        this.resolver.resolvePokemonCenter();
                    } else if (t == TileType.MARKET) {
                        MainController.getController().getViewController().market();
                    } else if (t == TileType.SIGN) {
                        this.resolver.resolveSign();
                    } else if (t == TileType.NPC) {
                        this.resolver.resolveNPC();
                    } else if (t == TileType.ENCOUNTER) {
                    	this.resolver.resolveNPC();
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
                this.up = false;
                break;
            case Keys.A:
            case Keys.LEFT:
                removeKey();
                this.left = false;
                break;
            case Keys.D:
            case Keys.RIGHT:
                removeKey();
                this.right = false;
                break;
            case Keys.S:
            case Keys.DOWN:
                removeKey();
                this.down = false;
                break;
            default:
                break;
        }
        return true;
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
        PlayerSprite.getSprite().updatePosition();
        this.t = pm.getTileType(player.getTileX(), player.getTileY());
        if (this.t == TileType.TELEPORT && this.pm.getTeleport(this.player.getTileX(), this.player.getTileY()).isPresent() 
                && !(this.pm.getTeleport(this.player.getTileX(), this.player.getTileY()).get() instanceof BadgeTeleport)) {
            this.resolver.resolveTeleport();
            MainController.getController().getStatusController().updateMusic();
            return;
        } else if (this.t == TileType.TELEPORT && this.pm.getTeleport(this.player.getTileX(), this.player.getTileY()).isPresent() 
                && this.pm.getTeleport(this.player.getTileX(), this.player.getTileY()).get() instanceof BadgeTeleport 
                && ((BadgeTeleport) this.pm.getTeleport(this.player.getTileX(), this.player.getTileY()).get()).canTeleport()) {
            this.resolver.resolveTeleport();
            MainController.getController().getStatusController().updateMusic();
            return;
        }
        if (this.up) {
            this.direction = Direction.NORTH;
        } else if (this.down) {
            this.direction = Direction.SOUTH;
        } else if (this.left) {
            this.direction = Direction.WEST;
        } else if (this.right) {
            this.direction = Direction.EAST;
        } 
        if (this.up || this.down || this.left || this.right) {
            this.oppositeDirection = resolver.changeOppositeDirection();
            this.resolver.resolveMove(this.direction);
            this.player.move(this.direction, this.pm);
        } else {
            PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
        }
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }
    
    @Override
    public void checkEncounter() {
        PlayerSprite.getSprite().updatePosition();
        this.t = this.pm.getTileType(this.player.getTileX(), this.player.getTileY());
        if (this.t == TileType.POKEMON_ENCOUNTER) {
            int x; 
            int y;
            x = this.player.getTileX();
            y = this.player.getTileY();
            if (this.pm.getEncounterZone(x, y).isPresent() && this.pm.getEncounterZone(x, y).get().contains(x, y) && this.pm.getEncounterZone(x, y).get().isEncounterNow()) {
                final Pokemon poke = this.pm.getEncounterZone(x, y).get().getPokemonEncounter();
                MainController.getController().getFightController().newFightWithPokemon(poke);
                MainController.getController().getViewController().fightScreen();
                this.up = false;
                this.down = false;
                this.left = false;
                this.right = false;
                PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
            }
        }
    }
    
    @Override
    public String toString() {
        return this.name;
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
            MainController.getController().updateStatus(State.READING);
            View.getView().addNew(new MessageFrame(State.WALKING, "POKEMON'S HEALTH FULLY RESTORED"));
            View.getView().showCurrent();
            player.getSquad().healAllPokemon(pm);
        }
        
        /**
         * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
         * a sign
         */
        private void resolveSign() {
            MainController.getController().updateStatus(State.READING);
            if (pm.getSign(x, y).isPresent()) {
                View.getView().addNew(new MessageFrame(State.WALKING, pm.getSign(x, y).get().getMessage()));
                View.getView().showCurrent();
            } else {
                View.getView().addNew(new MessageFrame(State.WALKING, "SIGN_MESSAGE"));
                View.getView().showCurrent();
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
                    MainController.getController().updateStatus(State.READING);
                    View.getView().addNew(new MessageFrame(State.WALKING, pm.getTrainer(x, y).get().getName() + ": " + pm.getTrainer(x, y).get().getTtrainerLostMessage()));
                    View.getView().showCurrent();
                } else {
                    MainController.getController().updateStatus(State.FIGHTING);
                    MainController.getController().getFightController().newFightWithTrainer(pm.getTrainer(x, y).get());
                    MainController.getController().getViewController().fightScreen();
                    View.getView().hideCurrent();
                    final String[] aray = new String[] {
                            pm.getTrainer(x, y).get().getName() + " wants to fight!",
                            pm.getTrainer(x, y).get().getName() + ": " + pm.getTrainer(x, y).get().getInitialMessage()
                    };
                    View.getView().addNew(new MessageFrame(null, aray));
                    View.getView().showCurrent();
                }
            } else if (pm.getNPC(x, y).isPresent()) {
                if (direction != Direction.NONE) {
                    pm.getNPC(x, y).get().turn(oppositeDirection);
                }
                MainController.getController().updateStatus(State.READING);
                View.getView().addNew(new MessageFrame(State.WALKING, pm.getNPC(x, y).get().getMessage()));
                View.getView().showCurrent();
            } else if (pm.getGymLeader(x, y).isPresent()) {
                if (direction != Direction.NONE) {
                    pm.getGymLeader(x, y).get().turn(oppositeDirection);
                }
                if (pm.getGymLeader(x, y).get().isDefeated()) {
                    MainController.getController().updateStatus(State.READING);
                    View.getView().addNew(new MessageFrame(State.WALKING, pm.getTrainer(x, y).get().getName() + ": " + pm.getTrainer(x, y).get().getTtrainerLostMessage()));
                    View.getView().showCurrent();
                } else {
                    MainController.getController().updateStatus(State.FIGHTING);
                    MainController.getController().getFightController().newFightWithTrainer(pm.getGymLeader(x, y).get());
                    MainController.getController().getViewController().fightScreen();
                    View.getView().hideCurrent();
                    final String[] aray = new String[] {
                            pm.getTrainer(x, y).get().getName() + " wants to fight!",
                            pm.getTrainer(x, y).get().getName() + ": " + pm.getTrainer(x, y).get().getInitialMessage()
                    };
                    View.getView().addNew(new MessageFrame(null, aray));
                    View.getView().showCurrent();
                }        
            } else if (pm.getEncounterTile(x, y).isPresent()) { 
            	MainController.getController().updateStatus(State.FIGHTING);
                MainController.getController().getFightController().newFightWithPokemon(pm.getEncounterTile(x, y).get().getPokemon());
                MainController.getController().getViewController().fightScreen();
                View.getView().hideCurrent();
                final String str = pm.getEncounterTile(x, y).get().getMessage();
                View.getView().addNew(new MessageFrame(null, str));
                View.getView().showCurrent();
            }
        }
        
        /**
         * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
         * a teleport
         */
        private void resolveTeleport() {
            final int x = player.getTileX();
            final int y = player.getTileY();
            final Optional<Teleport> t = pm.getTeleport(x, y);
            if (t.isPresent()) {
                PlayerSprite.getSprite().setPlayerPosition(t.get().getDestinationX(), t.get().getDestinationY());
                PlayerSprite.getSprite().setVelocity(NULL_SPEED, NULL_SPEED);
                player.setPosition(t.get().getDestinationX(), t.get().getDestinationY());
            }    
        }
        
        /**
         * Resolve the case player selects to move
         */
        private void resolveMove(final Direction direction) {
            t = pm.getTileNextToPlayer(direction);
            if (pm.isWalkableNextToPlayer(direction)) {
            	switch (direction) {
                case EAST:
                    PlayerSprite.getSprite().setVelocity(SPEED, NULL_SPEED);
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
        
        /**
         * @return the opposite {@link Direction} of current player's {@link Direction}
         */
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