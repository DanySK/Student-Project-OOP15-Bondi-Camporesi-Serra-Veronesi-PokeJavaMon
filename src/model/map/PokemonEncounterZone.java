package model.map;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import com.google.common.collect.Range;

import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.PokemonRarity;
import model.pokemon.StaticPokemonFactory;

/**
 * Implementation in a rectangular form of {@link Zone}.
 * Each {@link Tile} of type {@link TileType#ENCOUNTER} yields a chance
 * to find a wild {@link Pokemon} if walking on it. Once triggered the encounter
 * the probability of finding a {@link Pokemon} instead of another is based on its {@link PokemonRarity} :
 * the more rare the lower the chance of finding it.
 * In order not to create objects of {@link TileType#ENCOUNTER} with all the methods and structures I decided to 
 * group all of these in a {@link Zone}, in order to reduce the ammount of Objects creted as there are many more
 * {@link TileType#ENCOUNTER} than {@link PokemonEncounterZone}
 * @see Tile
 * @see Pokeball
 */
public class PokemonEncounterZone extends AbstractRectangularZone {
	
	//Properties of each zone
	private final int id;
	private final Set<Pokedex> pokemonList;
	private final int avgLvl;
	
	//Chance that each Tile of Type ENCOUNTER has to find a 
    private final static double ENCOUNTER_CHANCE = 0.08;
   //Variation of level given a base level value
    private final static int LEVEL_VARIATION = 2;

    //To adjust PokemonRarity coefficients
    private final static double COMMON_COEFF_ADJUSTMENT = 25.5;
    private final static double UNCOMMON_COEFF_ADJUSTMENT = 17.0;
    private final static double RARE_COEFF_ADJUSTMENT = 9.1;
    private final static double VERY_RARE_COEFF_ADJUSTMENT = 8.0;
    
    private boolean encountered = false;
	
	/**
	 * Constructor that creates a new {@link PokemonEncounterZone}
	 * 
	 * @param id
	 * 			identifier of the zone
	 * @param pokemonSet
	 * 			set of all findables
	 * @param averageLevel
	 * 			average level of each {@link Pokemon} findable in this zone, there's a random variation applied to the final result
	 * @param tileX
	 * 			x-axis coordinate of top-left corner of the {@link Rectangle}
	 * @param tileY
	 * 			y-axis coordinate of top-left corner of the {@link Rectangle}
	 * @param width
	 * 			of the {@link Rectangle} 
	 * @param height 
	 * 			of the {@link Rectangle}
	 * @throws IllegalArgumentException if pokemonSet is empty
	 */
	public PokemonEncounterZone(final int id, final Set<Pokedex> pokemonSet, final int averageLevel, final int tileX, final int tileY, final int width, final int height) {
		super("EncounterZone_" + id, tileX, tileY, width, height);
		this.id = id;
		this.pokemonList = new HashSet<>(pokemonSet);

		if (this.pokemonList.isEmpty()) {
			throw new IllegalArgumentException("Pokemon Encounter Zone has no valid pokemon found in PokemonDB");
		}
		this.avgLvl = averageLevel;
	}

	/**
	 * If called, randomly (according to a set chance) tells you if you triggered an encounter
	 * @return true if you triggered an encounter, false otherwise
	 */
    public boolean isEncounterNow() {
        final Random r = new Random();
        final double chance = r.nextDouble();
        
        if (chance < ENCOUNTER_CHANCE) {
            encountered = true;
            return true;
        }
        return false;
    }

    /**
     * Method that returns a fully operating and randomly generated{@link PokemonInBattle} as encounter.
     * Each {@link Pokemon} has a different chance of getting found as it depends on its {@link PokemonRarity}
     * and every one's probability is determined by its actual chance divided by the sum of all other chances
     * Also in the end this methods resets {@link PokemonEncounterZone#isEncounterNow()}
     * @return the randomly choosen {@link PokemonInBattle}
     * @throws IllegalStateException if this method is called before {@link PokemonEncounterZone#isEncounterNow()} has returned true 
     */
    public PokemonInBattle getPokemonEncounter() {
        if (!encountered) {
            throw new IllegalStateException("Cannot encounter Pokemon if the value is false");
        }
        
        final Map<Pokedex, Range<java.lang.Double>> chanceMap = new HashMap<>();
        double probabilitySum = 0;
        final Random r = new Random();
        for (final Pokedex pkmn : this.pokemonList) {
            probabilitySum += getPokemonChance(pkmn);
        }
        double tmpSum = 0;
        for (final Pokedex pkmn : this.pokemonList) {
            double pkmnProbability = getPokemonChance(pkmn) / probabilitySum;
            final Range<java.lang.Double> range = Range.closedOpen(tmpSum, pkmnProbability + tmpSum);
            chanceMap.put(pkmn, range);
            tmpSum += pkmnProbability;
        }
        double chance = r.nextDouble();
        for (final Entry<Pokedex, Range<java.lang.Double>> e : chanceMap.entrySet()) {
            if (e.getValue().contains(chance)) {
                final int levelVariation = r.nextInt(LEVEL_VARIATION * 2);
                final int level = this.avgLvl + levelVariation - LEVEL_VARIATION;
                final PokemonInBattle encounterPokemon = StaticPokemonFactory.createPokemon(e.getKey(), level);
                this.encountered = false;
                return encounterPokemon;
            }
        }
        this.encountered = false;
        throw new IllegalStateException("random chance not in range of chanceMap");
    }
	
    //Gives each PokemonRarity a base chance, except for Starters, Unfindable and Legendary Pokemons which cannot be found in a simple encounterTile
    private double getPokemonChance(final Pokedex pkmn) {
        switch (pkmn.getRarity()) {
        case COMMON :
            return (double) PokemonRarity.COMMON.getCoeff() / COMMON_COEFF_ADJUSTMENT;
        case UNCOMMON :
            return (double) PokemonRarity.UNCOMMON.getCoeff() / UNCOMMON_COEFF_ADJUSTMENT;
        case RARE :
            return (double) PokemonRarity.RARE.getCoeff() / RARE_COEFF_ADJUSTMENT;
        case VERY_RARE :
            return (double) PokemonRarity.VERY_RARE.getCoeff() / VERY_RARE_COEFF_ADJUSTMENT;
        case LEGENDARY :
        case UNFINDABLE :
        case STARTER :
        default :
        	throw new IllegalArgumentException("Pokemon is unfindable");
        }
    }

    /**
     * @return an unmodifiable {@link Set} of current {@link Pokedex} entries available
     */
	public Set<Pokedex> getAvailablePokemon() {
		return Collections.unmodifiableSet(this.pokemonList);
	}

	/**
	 * @return average level of all the findable {@link Pokemon}
	 */
	public int getAverageLevel() {
		return this.avgLvl;
	}
	
	/**
	 * @return zone's identifier
	 */
	public int getID() {
		return this.id;
	}


	public String toString() {
		return getZoneName() + new Position(super.rect.x, super.rect.y) + ", width = " + super.rect.width + ", height = " + super.rect.height;
	}

	

}

