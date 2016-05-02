package controller.fight;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Effectiveness;
import model.fight.Fight;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.trainer.Trainer;

/**
 * This interface explains the methods {@link FightController} implements to control
 * the fight against a wild pokemon or a trainer
 */
public interface FightControllerInterface {

    /**
     * Initializes a new {@link Fight} against a {@link Trainer}
     * @param tr the {@link Trainer} player is fighting with
     */
    public void newFightWithTrainer(Trainer tr);

    /**
     * Initializes a new {@link Fight} against a wild {@link Pokemon}
     * @param pm the wild {@link Pokemon} player is fighting with
     */
    public void newFightWithPokemon(Pokemon pm);

    /**
     * @return the current {@link Fight}
     */
    public Fight getFight();

    /**
     * Resolve the {@link Fight}'s turn in case of a {@link Move} of the player
     * @param myMove the {@link Move} of the player's {@link Pokemon} or null if 
     * player's {@link Pokemon} is exausted
     * @param myMoveEffectiveness the {@link Effectiveness} of player's {@link Pokemon} {@link Move}
     * @param enemyMove enemy {@link Pokemon}'s {@link Move} or null if enemy {@link Pokemon}
     * is exausted
     * @param enemyMoveEffectiveness the {@link Effectiveness} of enemy {@link Pokemon}'s
     * {@link Move}
     * @param myMoveFirst true if player's {@link Pokemon} moves first, false otherwise
     * @param lastPokemonKills true if the last {@link Pokemon} that moves defeat 
     * the opponent, false otehrwise
     * @param nextEnemyPokemon the next enemy's {@link Pokemon} if it changes, null 
     * otherwise 
     * @param optionalMessage an optional message, null if there is no message to show
     */
    public void resolveAttack(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
                Pokemon nextEnemyPokemon, String optionalMessage);

    /**
     * Resolve the {@link Fight}'s turn in case of run choice
     * @param success true if run succeed, false otherwise
     * @param enemyMove the enemy's {@link Move} if run fails, null otherwise
     * @param isMyPokemonDead true if player's {@link Pokemon} is exausted, false otherwise
     */
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);

    /**
     * Resolve the {@link Fight}'s turn in case player wants to use an item
     * @param item the {@link Item} player wants to use
     * @param pk the {@link Pokemon} to use {@link Item} on
     * @param enemyMove the {@link Move} enemy's {@link Pokemon} does after player has
     * used the {@link Item}
     * @param isMyPokemonDead true if player's {@link Pokemon} is exausted after enemy's
     * {@link Move}, false otherwise
     */
    public void resolveItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead);

    /**
     * Resolve the {@link Fight}'s turn in case player wants to change {@link Pokemon}
     * @param myPokemon new player's {@link Pokemon}
     * @param enemyMove the {@link Move} enemy's {@link Pokemon} does after player has
     * changed {@link Pokemon}
     * @param isMyPokemonDead true if player's {@link Pokemon} is exausted after enemy's
     * {@link Move}, false otherwise
     */
    public void resolvePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead);

    /**
     * Starts the {@link Fight}'s turn in case player has chosen to attack
     * @param mv player's {@link Pokemon} {@link Move}
     */
    public void attack(Move mv);

    /**
     * Starts the {@link Fight}'s turn in case player has chosen to run away
     * @throws CannotEscapeFromTrainerException in case player is trying to escape
     * from a {@link Trainer}
     */
    public void run() throws CannotEscapeFromTrainerException;

    /**
     * Starts the {@link Fight}'s turn in case player has chosen to change {@link Pokemon}
     * @param pk the new {@link Pokemon}
     * @throws PokemonIsExhaustedException in case player is trying to select an exausted {@link Pokemon}
     * @throws PokemonIsFightingException in case player is trying to select the fighting {@link Pokemon}
     */
    public void changePokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Starts the {@link Fight}'s turn in case player has chosen to use an {@link Item}
     * @param it the {@link Item} to use
     * @param pk the {@link Pokemon} to use item on
     * @throws PokemonIsExhaustedException if player is trying to use an {@link Item}
     * on an exausted {@link Pokemon}
     * @throws PokemonNotFoundException if player is trying to use an {@link Item}
     * on a {@link Pokemon} that has not been found
     * @throws CannotCaughtTrainerPkmException if player is trying to catch a {@link Trainer}'s
     * {@link Pokemon}
     * @throws IllegalStateException if player is trying to use an {@link Item}
     * that he doesn't have
     */
    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException,
                CannotCaughtTrainerPkmException, IllegalStateException;

    /**
     * Search for {@link Pokemon}s that have to evolve
     * @return the {@link List}<{@link Pokedex}> that have to evolve 
     */
    public List<Pokedex> resolveEvolution();

    /**
     * Select the next {@link Pokemon} to use in the {@link Fight}
     * @param pk the {@link Pokemon} selected
     * @throws PokemonIsExhaustedException if selected {@link Pokemon} is exausted
     * @throws PokemonIsFightingException if selected {@link Pokemon} is fighting 
     */
    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * @return the enemy's fighting {@link Pokemon}
     */
    public Pokemon getEnemyPokemon();
}