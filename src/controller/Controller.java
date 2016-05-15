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
import exceptions.NotEnoughMoneyException;
import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.Model;
import model.box.Box;
import model.fight.Fight;
import model.inventory.Inventory;
import model.items.Item;
import model.map.PokeMap;
import model.player.Player;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.squad.Squad;
import model.utilities.Pair;

/**
 * This interface explains all the methods that can be called on {@link MainController}
 */
public interface Controller {

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
     * Pauses the current music
     */
    void pause();
    
    /**
     * Resumes the current music
     */
    void resume();

    /**
     * @return the {@link Music} playing
     */
    Optional<Music> playing();
    
    /**
     * @return true if music is paused, false otherwise
     */
    boolean isPaused();

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

    /**
     * @return player's {@link Inventory}
     */
    Inventory getInventory();
    
    /**
     * @return player's {@link Box}
     */
    Box getBox();
    
    /**
     * @param p the selected {@link Pokemon}
     * @throws PokemonNotFoundException if the selected {@link Pokemon} isn't present
     * @throws SquadFullException if {@link Squad} is full
     */
    void withdrawPokemon(Pokemon p) throws PokemonNotFoundException, SquadFullException;
    
    /**
     * Uses the selected {@link Item} on the selected {@link Pokemon}
     * @param i the selected {@link Item}
     * @param p the selected {@link Pokemon}
     * @throws PokemonNotFoundException if it's impossible to find the selected {@link Pokemon}
     */
    void effectItem(Item i, Pokemon p) throws PokemonNotFoundException;
    
    /**
     * @param p the {@link Pokemon} that wants to learn the {@link Move}
     * @param oldMove the old {@link Move}
     * @param newMove the selected {@link Move}
     */
    void learnMove(Pokemon p, Move oldMove, Move newMove);
    
    /**
     * Buys the selected {@link Item}
     * @param i the selected {@link Item}
     * @throws NotEnoughMoneyException if player has not enough money
     */
    void buyItem(Item i) throws NotEnoughMoneyException;
    
    /**
     * @return current {@link Pokemon}'s {@link Squad}
     */
    Squad getSquad();

    /**
     * Switches two {@link Pokemon}s
     * @param index1 first {@link Pokemon}
     * @param index2 second {@link Pokemon}
     */
    void switchPokemon(int index1, int index2);
    
    /**
     * @param p the {@link Pokemon} to deposit in {@link Box}
     * @throws PokemonNotFoundException if selected {@link Pokemon} has not been found
     * @throws OnlyOnePokemonInSquadException if player is trying to deposit last {@link Pokemon}
     * in {@link Squad}
     */
    void depositPokemon(Pokemon p) throws PokemonNotFoundException, OnlyOnePokemonInSquadException;

    /**
     * Teleports the player to pokemon center and heals all his {@link Pokemon}s
     */
    void teleportToCenter();
    
    /**
     * Initializes player's {@link Inventory}
     */
    void initInventory();
    
    /**
     * @return the initial player's position
     */
    Pair<Integer, Integer> getInitialPosition();

    /**
     * @return the default initial player's position
     */
    Pair<Integer, Integer> getDefaultInitialPosition();
    
    /**
     * @param p the pokemon to add
     * @throws SquadFullException if {@link Squad} is full
     */
    void addPokemonToSquad(Pokedex p) throws SquadFullException;
    
    /**
     * Selects the initial starter pokemon in {@link Pokedex}
     * @param p
     */
    void selectStarter(Pokedex p);
    
    /**
     * Initializes the starter pokemon
     * @throws SquadFullException if the {@link Squad} is full
     */
    void initializeStarter() throws SquadFullException;
}