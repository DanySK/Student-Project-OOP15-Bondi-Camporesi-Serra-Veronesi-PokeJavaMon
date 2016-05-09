package controller;

import java.util.Optional;

import com.badlogic.gdx.maps.tiled.TiledMap;

import controller.fight.FightController;
import controller.load.LoadController;
import controller.music.MusicController;
import controller.parameters.Music;
import controller.parameters.State;
import controller.save.SaveController;
import controller.status.StatusController;
import controller.view.ViewController;
import model.Model;
import model.fight.Fight;
import model.map.PokeMap;
import model.player.Player;
import model.pokemon.Pokemon;

/**
 * This interface explains all the methods that can be called on {@link Controller}
 */
public interface ControllerInterface {

    /**
     * @return the current {@link Fight}
     */
    Fight getFight();

    /**
     * @return the current enemy {@link Pokemon} in fight
     */
    Pokemon getEnemyPokemonInFight();

    /**
     * Loads the game
     */
    void load();

    /**
     * @return true if a save file exists, false otherwise
     */
    boolean saveExists();
    
    /**
     * Initializes the {@link MusicController}
     */
    void initializeMusicController();

    /**
     * Plays the selected {@link Music}
     * @param m the selected {@link Music}
     */
    void playMusic(Music m);

    /**
     * Stops playing {@link Music}
     */
    void stopMusic();

    /**
     * @return the {@link Music} playing
     */
    Optional<Music> playing();

    /**
     * Saves the game
     */
    void save();

    /**
     * Updates the game {@link State}
     * @param s the new {@link State}
     */
    void updateStatus(State s);

    /**
     * @return the {@link FightController}
     */
    FightController getFightController();

    /**
     * @return the {@link LoadController}
     */
    LoadController getLoadController();

    /**
     * @return the {@link MusicController}
     */
    MusicController getMusicController();

    /**
     * @return the {@link SaveController}
     */
    SaveController getSaveController();

    /**
     * @return the {@link StatusController}
     */
    StatusController getStatusController();

    /**
     * @return the {@link ViewController}
     */
    ViewController getViewController();
    
    /**
     * @return the {@link TiledMap} used in the game
     */
    TiledMap getMap();
    
    /**
     * @return the current {@link PokeMap} 
     */
    PokeMap getPokeMap();
    
    /**
     * @return the current {@link Player}
     */
    Player getPlayer();
    
    /**
     * @return the current {@link Model}
     */
    Model getModel();

    /**
     * Initializes the {@link Model}
     * @param map the current {@link TiledMap}
     */
    void initializeModel(TiledMap map);
}