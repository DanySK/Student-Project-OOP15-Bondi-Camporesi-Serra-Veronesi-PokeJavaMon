package model.squad;

import java.util.List;
import java.util.Optional;

import exceptions.OnlyOnePokemonInSquadException;
import exceptions.SquadFullException;
import model.map.PokeMap;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;

/**
 * Class that contains all the {@link Pokemon} a {@link Trainer} or the {@link Player} can carry
 * It is limited to a max ammount of {@link Pokemon}
 * @see Pokemon
 * @see SquadImpl
 * @see Trainer
 * @see Player
 */
public interface Squad {
    
	/**
	 * @return the number of {@link Pokemon} present in the squad
	 */
	public int getSquadSize();
    
	/**
	 * A method to get a {@link List}<{@link Pokemon}> which is unmodifiable
	 * @return an unmodifiable List of Pokemon
	 */
    public List<PokemonInBattle> getPokemonList();
    
    /**
     * Removes a certain {@link Pokemon} from the {@link Squad}, if it is not present it does nothing
     * @param pkmn 
     * 			the {@link Pokemon} to be removed
     * @throws OnlyOnePokemonInSquadException 
     * 			if it is the only {@link Pokemon} left
     */
    public void remove(final Pokemon pkmn) throws OnlyOnePokemonInSquadException;
    
    /**
     * Adds a {@link Pokemon} to the squad
     * @param pkmn 
     * 			{@link Pokemon} to be added
     * @throws SquadFullException 
     * 			if the {@link Squad} is already full
     */
    public void add(final Pokemon pkmn) throws SquadFullException;
    
    /**
     * Checks if a certain {@link Pokemon} is present in the {@link Squad}
     * @param pkmn 
     * 			{@link Pokemon} to be checked
     * @return true if there is, false otherwise
     */
    public boolean contains(final Pokemon pkmn);

    /**
     * Switches the order of two {@link Pokemon} given the positions of those two.
     * @param index1	
     * 			index of the first {@link Pokemon} to be swapped
     * @param index2	
     * 			index of the second {@link Pokemon} to be swapped
     * @throws IndexOutOfBoundsException 
     * 			if an index not in the range of [0-squadSize-1]
     */
    public void switchPokemon(final int index1, final int index2) throws IndexOutOfBoundsException;

    /**
     * Heals all the {@link Pokemon} in {@link Squad} to max HP.
     * Takes a {@link PokeMap} as argument to verify that the player is in a PokemonCenter Zone
     * @param pm 
     * 			{@link PokeMap} 
     */
    public void healAllPokemon();
    
    /**
     * Gives the next alive Pokemon based on position in the {@link List}<{@link Pokemon}>.
     * Returns {@link Optional}.empty() if all {@link Pokemon} are exhausted
     * @return an {@link Optional}<{@link PokemonInBattle}> that may or may not be there
     */
    public Optional<PokemonInBattle> getNextAlivePokemon();
    
}
