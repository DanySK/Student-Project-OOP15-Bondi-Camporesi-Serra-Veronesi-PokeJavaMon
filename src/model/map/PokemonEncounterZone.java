package model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.google.common.collect.Range;

import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.PokemonRarity;
import model.pokemon.StaticPokemonFactory;

public class PokemonEncounterZone extends AbstractZone {
	
	private final int id;
	private final List<Pokedex> pokemonList;
	private final int avgLvl;
    private final static double ENCOUNTER_CHANCE = 20 / 187.5;
    private final static int LEVEL_VARIATION = 2;
    private boolean encountered = false;
	
	
	public PokemonEncounterZone(final int id, final String pokemonList, final int averageLevel, final int tileX, final int tileY, final int width, final int height) {
		super("EncounterZone_" + id, tileX, tileY, width, height);
		this.id = id;
		this.pokemonList = new ArrayList<>();
		
		for (final String pkmn : pokemonList.split(" ")) {
			for (final Pokedex pkmnID : Pokedex.values()) {
				if (pkmn.equals(pkmnID.toString())) {
					this.pokemonList.add(pkmnID);
				}
			}
		}
		if (this.pokemonList.isEmpty()) {
			throw new IllegalArgumentException("Pokemon Encounter Zone has no valid pokemon found in PokemonDB");
		}
		this.avgLvl = averageLevel;
	}

    public boolean isEncounterNow() {
        final Random r = new Random();
        final double chance = r.nextDouble();
        
        if (chance < ENCOUNTER_CHANCE) {
            encountered = true;
            return true;
        }
        return false;
    }
    
    //TODO: Need testing
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
	
    private double getPokemonChance(final Pokedex pkmn) {
        switch (pkmn.getRarity()) {
        case COMMON :
            return (double) PokemonRarity.COMMON.getCoeff() / 25.5;
        case UNCOMMON :
            return (double) PokemonRarity.UNCOMMON.getCoeff() / 17;
        case RARE :
            return (double) PokemonRarity.RARE.getCoeff() / 9.1;
        case VERY_RARE :
            return (double) PokemonRarity.VERY_RARE.getCoeff() / 8;
        case LEGENDARY :
        	return (double) PokemonRarity.LEGENDARY.getCoeff() / 7;
        case UNFINDABLE :
        	return (double) PokemonRarity.UNFINDABLE.getCoeff() / 6;
        case STARTER :
        
        default :
        	throw new IllegalArgumentException("PokemonRarity not listed or is Starter so cannot be found");
        }
    }

	public List<Pokedex> getAvailablePokemon() {
		return Collections.unmodifiableList(this.pokemonList);
	}

	public int getAverageLevel() {
		return this.avgLvl;
	}
	
	public int getID() {
		return this.id;
	}


	public String toString() {
		return getZoneName() + new Position(super.rect.x, super.rect.y) + ", width = " + super.rect.width + ", height = " + super.rect.height;
	}

	

}

