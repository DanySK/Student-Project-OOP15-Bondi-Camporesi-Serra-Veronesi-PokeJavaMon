package model.pokemon;

import java.util.List;
import java.util.Map;

/**
 * This class is used to represent an instance of a Pokemon with its own {@link Stat}s,
 * moveset, etc...
 * It has all the methods to interact with it such as heal, level up, set exp...
 * It is implemented firstly through an Abstrac Class {@link AbstractPokemon},
 * then through {@link PokemonInBattle}.
 * However every Pokemon is instantiated by {@link StaticPokemonFactory} which follows the pattern Static Factory
 * @see StaticPokemonFactory
 */
public interface Pokemon {

	/**
	 * Returns the type of {@link Pokedex} from the "Pokedex"
	 * Example: If called on an object of {@link Pokemon} type Charizard,
	 * it will return the enum type of Charizard in the {@link Pokedex}
	 * @return		the Pokedex type of the Pokemon
	 * @see			Pokedex
	 */
    public Pokedex getPokedexEntry();
    
    /**
     * A method to get the currentHP of the {@link Pokemon}
     * @return		the HealthPoints(HP) left of a {@link Pokemon}
     */
    public int getCurrentHP();
    
    /**
     * A method that calcolates the experience left to level up a {@link Pokemon}
     * Not to be confused with {@link getStat(Stat.EXP)} which returns the total exp needed in the current level.
     * @return 		the experience necessary to level up
     */
    public int getNecessaryExp();
    
    /**
     * The method to call to get the various stats of a {@link Pokemon}
     * which includes {@link Stat.HP}, {@link Stat.ATK}, {@link Stat.DEF}, {@link Stat.SPD}, {@link Stat.LVL}, {@link Stat.EXP}
     * @param s 	{@link Stat} of the Pokemon
     * @return		the value of the selected {@link Stat}
     * @see 		Stat
     */
    public int getStat(Stat s);
    
    /**
     * A method that returns an unmodifiable copy of the {@link Map}<{@link Stat}, {@link Integer}>
     * of the {@link Pokemon}
     * @return		a Map with all the current {@link Stat}
     * @see 		Stat
     */
    public Map<Stat, Integer> getAllStats();
    
    /**
     * A method to get the current {@link Move}s that the {@link Pokemon} has now available to use
     * @return		a {@link List}<{@link Move}> of the current moveset.
     * @see 		Move
     */
    public List<Move> getCurrentMoves();
    
    /**
     * Method that updates {@link Stat}s accordingly to the level
     */
    public void updateStats();
    
    /**
     * A method to change the current {@link Stat.EXP} of the {@link Pokemon}
     * @param exp	the new ammount of exp
     */
    public void setExp(final int exp);
    
    /**
     * A method to call to level up the {@link Pokemon} which also updates all the {@link Stat}s
     * Does not check if the current exp is enough to level up 
     */
    public void levelUp();
    
    /**
     * A method to call when to learn a new {@link Move}, replacing an existing one
     * @param oldMove	one of the {@link Move} in the current moveset to be replaced
     * @param newMove	a new {@link Move} that	replaces the oldMove
     * @throws IllegalArgumentException if oldMove is not present in the current moveset
     */
    public void learnMove(Move oldMove, Move newMove) throws IllegalArgumentException ;
    
    /**
     * A method that checks if there is a free spot in the current moveset
     * @return true if there's no room for another Move
     */
    public boolean isCurrentMovesetFull();
    
    /**
     * A method that increase the currentHP of the {@link Pokemon}
     * @param value		the ammount of HP replenished
     * @throws IllegalArgumentException if the input value is negative
     */
    public void heal(int value);
    
    /**
     * A method which checks if the {@link Pokemon}'s level is enough to evolve
     * as described in the {@link Pokedex}.
     * If it is the {@link Pokemon} changes its {@link Pokedex} type and updates all the {@link Stat} accordingly.
     */
    public void evolve();
    
    /**
     * A method to summarize the current state of a Pokemon in a String form
     * It includes the {@link Pokemon}'s name, the index value in the {@link Pokedex}, the {@link Stat}Map, current HP, and current moveset.
     * @return a summary of the actual state of the {@link Pokemon}
     */
    public String toString();
    
    /**
     * A method to apply damage from an enemy {@link Move}
     * @param dmg	the value of the Move
     * 
     */
    public void damage(final int dmg);
}
