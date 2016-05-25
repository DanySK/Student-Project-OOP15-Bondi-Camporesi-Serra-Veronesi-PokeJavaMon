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
 * The interface that declares operation to manage a fight.
 *
 */
public interface Fight {

    /**
     * Control if a pokemon trainer can continue to battle.
     * 
     * @param pkmSquad  The {@link model.pokemon.Squad} which must be controlled.
     * @return          True if the pkmSquad does not contain {@link model.pokemon.Pokemon} which can fight.
     */
    public boolean checkLose(final Squad pkmSquad);

    /**
     * Resolve the turn in case user choose the run option.
     * 
     * @throws  CannotEscapeFromTrainerException if the fight is an instance of {@link model.fight.FightVsTrainer}.
     */
    public void runTurn() throws CannotEscapeFromTrainerException;

    /**
     * Resolve the case in which the ally pokemon is exhausted and user must choose another
     * pokemon to send in battle.
     * 
     * @param pkm                               The {@link model.pokemon.Pokemon} to send in battle.
     * @throws PokemonIsExhaustedException      If pkm can't fight.
     * @throws PokemonIsFightingException       If pkm is already in battle.
     */
    public void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Resolve the turn in case user choose the change option.
     * 
     * @param pkm                               The {@link model.pokemon.Pokemon} to send in battle.
     * @throws PokemonIsExhaustedException      If {@link model.pokemon.Pokemon} can't fight.
     * @throws PokemonIsFightingException       If {@link model.pokemon.Pokemon} is already in battle.
     */
    public void changeTurn(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    /**
     * Resolve the turn in case user choose the item option.
     * 
     * @param itemToUse                         The {@link model.items.Item} to use.
     * @param pkm                               The target {@link model.pokemon.Pokemon}(if it is present).
     * @throws PokemonIsExhaustedException      If target is exhausted.
     * @throws PokemonNotFoundException         If target was not found.
     * @throws CannotCaughtTrainerPkmException  If itemToUse is a {@link model.items.Pokeball} and the fight instance is {@link model.fight.FightVsTrainer}.
     */
    public void itemTurn(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException;

    /**
     * Return the list of pokemon to evolve.
     * 
     * @return          The list of {@link model.pokemon.Pokemon} which must be evolved after this battle.
     */
    public List<PokemonInBattle> getPkmsThatMustEvolve();

    /**
     * Evolve the list of pokemon ({@link model.fight.AbstractFight#pkmsThatMustEvolve}.
     */
    public void evolvePkms();

    /**
     * Get the enemy pokemon in fight.
     * 
     * @return  The current enemy {@link model.pokemon.Pokemon} in fight.
     */
    public Pokemon getCurrentEnemyPokemon();

    /**
     * Resolve the turn in case user choose to use a move.
     * 
     * @param move      The {@link model.pokemon.Move} chosen.
     */
    public void moveTurn(final Move move);

}