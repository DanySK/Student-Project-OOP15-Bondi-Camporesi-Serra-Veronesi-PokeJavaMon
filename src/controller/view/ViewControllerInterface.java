package controller.view;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Fight;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;

/**
 * This interface shows the methods that can be called on {@link ViewController}
 */
public interface ViewControllerInterface {

    /**
     * Shows the pokemon market menu
     */
    public void market();

    /**
     * Shows the main menu
     */
    public void showMenu();

    /**
     * Shows the first menu
     */
    public void firstMenu();

    /**
     * Shows the second menu
     */
    public void secondMenu();

    /**
     * Shows the game map
     * @param b if it is a new game or not
     */
    public void map(boolean b);

    /**
     * Save the game
     */
    public void save();

    /**
     * Shows the box menu
     */
    public void box();
    
    /**
     * Shows the team menu
     * @param bl if player can close the menu or not
     * @param bl2 if player can change pokemon to that pokemon or select that pokemon 
     * to use an item on
     */
    public void team(final boolean bl, final boolean bl2);
    
    /**
     * Shows the bag menu
     */
    public void bag();

    /**
     * Shows the stat menu
     * @param iD the {@link Pokemon} to show stats of
     */
    public void stats(Pokemon iD);

    /**
     * Set player's name
     * @param text the selected name
     */
    public void setName(String text);

    /**
     * Use the {@link Move} of a player's {@link Pokemon} during a fight
     * @param move the selected {@link Move}
     */
    public void attack(Move move);

    /**
     * Change player's {@link Pokemon} during a fight
     * @param poke the selected {@link Pokemon}
     * @throws PokemonIsExhaustedException if the selected {@link Pokemon} is exausted
     * @throws PokemonIsFightingException if the selected {@link Pokemon} is fighting
     */
    public void changePokemon(Pokemon poke) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Use an {@link Item} during a fight
     * @param it the selected {@link Item}
     * @param pk the {@link Pokemon} to use {@link Item} on
     * @throws PokemonIsExhaustedException if selected {@link Pokemon} is exausted
     * @throws PokemonNotFoundException if player is trying to use an item on a {@link Pokemon} not found
     * @throws CannotCaughtTrainerPkmException if player is trying to catch a {@link Trainer}'s {@link Pokemon}
     * @throws IllegalStateException if player doesn't have the {@link Item} he is trying to use
     */
    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException,
                CannotCaughtTrainerPkmException, IllegalStateException;

    /**
     * Try to run away from a fight
     * @throws CannotEscapeFromTrainerException if player is trying to run from a {@link Trainer}
     */
    public void run() throws CannotEscapeFromTrainerException;

    /**
     * Search for {@link Pokemon}s that have to evolve
     * @return the {@link List}<{@link Pokedex}> that have to evolve 
     */
    public List<Pokedex> getEvolutions();

    /**
     * Select the next {@link Pokemon} to use in the {@link Fight}
     * @param pk the {@link Pokemon} selected
     * @throws PokemonIsExhaustedException if selected {@link Pokemon} is exausted
     * @throws PokemonIsFightingException if selected {@link Pokemon} is fighting 
     */
    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Shows the fight menu
     * @param pk the enemy {@link Pokemon}
     */
    public void fightScreen(Pokemon pk);
}