package controller.keyboard;

import java.util.Optional;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

import controller.fight.FightController;
import controller.main.MainController;
import controller.parameters.*;
import controller.view.ViewController;
import model.map.Drawable.Direction;
import model.map.PokeMapImpl;
import model.map.Position;
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
                    TileType t = getTileType();                  
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
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) - 1;
            return pm.getTileType(x, y);
        case DOWN:
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) + 1;
            return pm.getTileType(x, y);
        case LEFT:
            x = (PlayerSprite.getSprite().getPosition().getX().intValue() / 16) - 1;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
            return pm.getTileType(x, y);
        case RIGHT:
            x = (PlayerSprite.getSprite().getPosition().getX().intValue() / 16) + 1;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
            return pm.getTileType(x, y);
        case STILL:
            return null;
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
            }
            MainController.getController().updateStatus(State.FIGHTING);
            FightController.getController().newFightWithTrainer(pm.getGymLeader(x, y).get());
            ViewController.getController().fightScreen(pm.getGymLeader(x, y).get().getSquad().getPokemonList().get(0));
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
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
        if (t == TileType.TELEPORT) {
            resolveTeleport();
            return;
        }
        if (up == true) {
            resolveUP();
            PlayerImpl.getPlayer().move(Direction.NORTH, pm);
        } else if (down == true) {
            resolveDOWN();
            PlayerImpl.getPlayer().move(Direction.SOUTH, pm);
        } else if (left == true) {
            resolveLEFT();
            PlayerImpl.getPlayer().move(Direction.WEST, pm);
        } else if (right == true) {
            resolveRIGHT();
            PlayerImpl.getPlayer().move(Direction.EAST, pm);
        } else {
            PlayerSprite.getSprite().setVelocity(0, 0);
        }
        
        System.out.println("Player Position:" + new Position(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY()));
    }
    
    /**
     * Resolve the case {@link TileType} of the {@link TiledMapTile} next to the player is
     * a teleport
     */
    private void resolveTeleport() {
        final int x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
        final int y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
        final Optional<Teleport> t = pm.getTeleport(x, y);
        if (t.isPresent()) {
            PlayerSprite.getSprite().setPlayerPosition(t.get().getDestinationX(), t.get().getDestinationY());
            PlayerSprite.getSprite().setVelocity(0, 0);
            PlayerImpl.getPlayer().setPosition(t.get().getDestinationX(), t.get().getDestinationY());
        }
        
    }
    
    //TODO: Refattorizzare il codice, roba ripetuta 4 volte
    /**
     * Resolve the case player selects to move up
     */
    private void resolveUP() {
        direction = Directions.UP;
        pm = Play.getMapImpl();
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) - 1);
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
        t = pm.getTileType(PlayerSprite.getSprite().getPosition().getX().intValue() / 16, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)) + 1);
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
        t = pm.getTileType((PlayerSprite.getSprite().getPosition().getX().intValue() / 16) - 1, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
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
        t = pm.getTileType((PlayerSprite.getSprite().getPosition().getX().intValue() / 16) + 1, (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16)));
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
        if (t == TileType.POKEMON_ENCOUNTER && (up == true || down == true || left == true || right == true)) {
            int x, y;
            x = PlayerSprite.getSprite().getPosition().getX().intValue() / 16;
            y = (299 - (PlayerSprite.getSprite().getPosition().getY().intValue() / 16));
            if (pm.getEncounterZone(x, y).isPresent()) {
                if (pm.getEncounterZone(x, y).get().isInsideZone(x, y)) {
                    if (pm.getEncounterZone(x, y).get().isEncounterNow()) {
                        Pokemon poke = pm.getEncounterZone(x, y).get().getPokemonEncounter();
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
    }
}