package model.player;

import java.util.List;

import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.pokemon.Pokemon;
import model.squad.Squad;

/**
 * Interface that describes all the basic methods of a Box which is basically
 * a storage that contains all the captured {@link Pokemon} that are not in the {@link Squad}
 * 
 * @see Squad
 * @see BoxImpl
 * @see Player
 */
public interface Box {

    /**
     * Deposits a {@link Pokemon} from the current {@link Squad},
     * @param pkmn
     * 			{@link Pokemon} to be deposited
     * @param squad
     * 			{@link Squad} where the {@link Pokemon} comes from
     * @throws PokemonNotFoundException if the pokemon is not in the squad
     * @throws OnlyOnePokemonInSquadException if you try to deposit last pokemon in your squad
     */
    public void depositPokemon(final Pokemon pkmn, final Squad squad) throws PokemonNotFoundException, OnlyOnePokemonInSquadException;
    
    /**
     * Withdraws a {@link Pokemon}
     * @param pkmn
     * 			{@link Pokemon} to be withdrawn
     * @param squad
     * 			{@link Squad} where the {@link Pokemon} comes from
     * @throws PokemonNotFoundException if the {@link Pokemon} cannot be found in the {@link Box}
     * @throws SquadFullException if the {@link Squad} is already full
     */
    public void withdrawPokemon(final Pokemon pkmn, final Squad squad) throws PokemonNotFoundException, SquadFullException;
    
    /**
     * Puts a captured {@link Pokemon} in the {@link Box}
     * @param pkmn
     * 			the {@link Pokemon} that has just been captured
     */
    public void putCapturedPokemon(final Pokemon pkmn);
    
    /**
     * @return the number of {@link Pokemon} present in the {@link Box}
     */
    public int getBoxSize();
    
    /**
     * @return an unmodifiable {@link List} of {@link Pokemon}
     */
    public List<Pokemon> getPokemonList();
    
    /**
     * 
     * @param pkmn
     * 			{@link Pokemon} to be checked
     * @return	true if the {@link Pokemon} is present in the {@link Box}, false otherwise
     */
    public boolean contains(final Pokemon pkmn);
    
    /**
     * (Can be only initialized once)
     * A method used to initialize the {@link Box} with a {@link List} of {@link Pokemon}
     * @param pokemons
     * 			{@link List}({@link Pokemon}) that will go inside
     * @throws IllegalStateException if it has already been initialized
     */
    public void initializePokemons(List<Pokemon> pokemons) throws IllegalStateException;
    
    public String toString();
}
