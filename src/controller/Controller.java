package controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;

import controller.fight.FightController;
import controller.load.LoadController;
import controller.music.MusicController;
import controller.parameters.MusicPath;
import controller.parameters.State;
import controller.save.SaveController;
import controller.status.StatusController;
import controller.view.ViewController;
import exceptions.NotEnoughMoneyException;
import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.Model;
import model.fight.Fight;
import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Potion;
import model.map.PokeMap;
import model.map.Position;
import model.map.tile.SpecialEncounterTile;
import model.player.Box;
import model.player.Inventory;
import model.player.Player;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.squad.Squad;

/**
 * This interface explains all the methods that can be called on {@link MainController}
 */
public interface Controller {

    /**
     * @return the current {@link Fight}
     */
    Optional<Fight> getFight();

    /**
     * @return the current enemy {@link Pokemon} in fight
     */
    Optional<Pokemon> getEnemyPokemonInFight();

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
     * Plays the selected {@link MusicPath}
     * @param m the selected {@link MusicPath}
     */
    void playMusic(MusicPath m);

    /**
     * Stops playing {@link MusicPath}
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
     * @return the {@link MusicPath} playing
     */
    Optional<MusicPath> playing();
    
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
    Optional<TiledMap> getMap();
    
    /**
     * @return the current {@link PokeMap} 
     */
    Optional<PokeMap> getPokeMap();
    
    /**
     * @return the current {@link Player}
     */
    Optional<Player> getPlayer();
    
    /**
     * Initializes the {@link Model}
     * @param map the current {@link TiledMap}
     */
    void initializeModel(TiledMap map);

    /**
     * @return player's {@link Inventory}
     */
    Optional<Inventory> getInventory();
    
    /**
     * @return player's {@link Box}
     */
    Optional<Box> getBox();
    
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
    Optional<Squad> getSquad();

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
    Position getInitialPosition();

    /**
     * @return the default initial player's position
     */
    Position getDefaultInitialPosition();
    
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

    /**
     * If the tile in front of the player is a legendary pokemon, deletes the tile
     */
    void checkLegendaryAndDelete();

    /**
     * Loads old save
     * @param money the old moneys
     * @param name the old player's name
     * @param badges old badges
     * @param position old player's {@link Position}
     * @param team old player's team
     * @param trainers beaten trainers
     * @param box old player's pokemon box
     * @param pokeballs the old number of items of {@link Pokeball} type
     * @param boosts the old number of items of {@link Boost} type
     * @param potions the old number of items of {@link Potion} type
     * @param defeatedEncounterTiles the defeated {@link SpecialEncounterTile}s
     * @throws SquadFullException if squad is full
     */
    void loadSave(int money, String name, int badges, Position position, List<Pokemon> team,
            Map<Integer, Boolean> trainers, List<Pokemon> box, Map<String, Integer> pokeballs,
            Map<String, Integer> boosts, Map<String, Integer> potions, Set<String> defeatedEncounterTiles) 
            throws SquadFullException;
}