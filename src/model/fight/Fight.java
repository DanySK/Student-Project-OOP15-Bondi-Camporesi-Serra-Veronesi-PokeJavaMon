package model.fight;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;

/**
 * The interface which declare operation for manage a fight.
 *
 */
public interface Fight {

    /**
     * Control if a pokemon trainer can continue to battle.
     * 
     * @param pkmSquad  The pokemon squad which must be controlled.
     * @return          True if in the squad, there aren't pokemon which can fight.
     */
    public boolean checkLose(final Squad pkmSquad);

    /**
     * Resolve a turn where the user choose the run option.
     * 
     * @throws CannotEscapeFromTrainerException If the user fights against a trainer.
     */
    public void runTurn() throws CannotEscapeFromTrainerException;

    /**
     * Resolve the case where the ally pokemon is exhausted and user must choose another
     * pokemon to send in battle.
     * 
     * @param pkm                               The pokemon to send in battle.
     * @throws PokemonIsExhaustedException      If pokemon can't fight.
     * @throws PokemonIsFightingException       If pokemon is already in battle.
     */
    public void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Resolve a turn where the user choose the change option.
     * 
     * @param pkm                               The pokemon to send in battle.
     * @throws PokemonIsExhaustedException      If pokemon can't fight.
     * @throws PokemonIsFightingException       If pokemon is already in battle.
     */
    public void changeTurn(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Resolve a turn where the user choose the item option.
     * 
     * @param itemToUse                         The item to use.
     * @param pkm                               The target pokemon(if it is present).
     * @throws PokemonIsExhaustedException      If target is exhausted.
     * @throws PokemonNotFoundException         If target was not found.
     * @throws CannotCaughtTrainerPkmException  If itemToUse is a pokeball and the user is fight against trainer.
     */
    public void itemTurn(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException;

    /**
     * Resolve a turn where the user choose to use a move.
     * 
     * @param move      The move choosen.
     */
    public void moveTurn(final Move move);

    /**
     * Set and return the list of pokemon to evolve.
     * 
     * @return          The list of pokemons which must be evolved after this battle.
     */
    public List<PokemonInBattle> getPkmsThatMustEvolve();

    /**
     * Evolve the list of pokemon set by {@link #getPkmsThatMustEvolve()}
     */
    public void evolvePkms();

    public Pokemon getCurrentEnemyPokemon();

}