package model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.google.common.collect.Range;

import model.pokemon.PokemonDB;
import model.pokemon.PokemonInBattle;
import model.pokemon.PokemonRarity;
import model.pokemon.StaticPokemonFactory;
import java.awt.Rectangle;

public class PokemonEncounterZone extends Rectangle implements Zone {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4501693972739348662L;
	
	private final int id;
	private final List<PokemonDB> pokemonList;
	private final int avgLvl;
    private final static double ENCOUNTER_CHANCE = 30 / 187.5;
    private final static int LEVEL_VARIATION = 2;
    private boolean encountered = false;
	
	
	public PokemonEncounterZone(final int id, final String pokemonList, final int averageLevel, final int tileX, final int tileY, final int width, final int height) {
		this.setBounds(tileX, tileY, width, height);
		
		this.pokemonList = new ArrayList<>();
		
		for (final String pkmn : pokemonList.split(" ")) {
			for (final PokemonDB pkmnID : PokemonDB.values()) {
				if (pkmn.equals(pkmnID.toString())) {
					this.pokemonList.add(pkmnID);
				}
			}
		}
		if (this.pokemonList.isEmpty()) {
			throw new IllegalArgumentException("Pokemon Encounter Zone has no valid pokemon found in PokemonDB");
		}
		
		this.id = id;
		this.avgLvl = averageLevel;
	}

    protected boolean isEncounterNow() {
        final Random r = new Random();
        final double chance = r.nextDouble();
        
        if (chance < ENCOUNTER_CHANCE) {
            encountered = true;
            return true;
        }
        return false;
    }
    
    //TODO: Need testing
    protected PokemonInBattle getPokemonEncounter() {
        if (!encountered) {
            throw new IllegalStateException("Cannot encounter Pokemon if the value is false");
        }
        
        final Map<PokemonDB, Range<java.lang.Double>> chanceMap = new HashMap<>();
        double probabilitySum = 0;
        final Random r = new Random();
        
        for (final PokemonDB pkmn : this.pokemonList) {
            probabilitySum += getPokemonChance(pkmn);
        }
        
        double tmpSum = 0;
        for (final PokemonDB pkmn : this.pokemonList) {
            double pkmnProbability = getPokemonChance(pkmn) / probabilitySum;
            final Range<java.lang.Double> range = Range.closedOpen(tmpSum, pkmnProbability + tmpSum);
            chanceMap.put(pkmn, range);
            tmpSum += pkmnProbability;
        }
        
        double chance = r.nextDouble();
        
        for (final Entry<PokemonDB, Range<java.lang.Double>> e : chanceMap.entrySet()) {
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
	
    private double getPokemonChance(final PokemonDB pkmn) {
        switch (pkmn.getRarity()) {
        case COMMON :
            return (double) PokemonRarity.COMMON.getCoeff() / 25.5;
        case UNCOMMON :
            return (double) PokemonRarity.UNCOMMON.getCoeff() / 17;
        case RARE :
            return (double) PokemonRarity.RARE.getCoeff() / 9.1;
        case LEGENDARY :
        	return (double) PokemonRarity.LEGENDARY.getCoeff() / 7;
        case STARTER :
        default :
        	throw new IllegalArgumentException("PokemonRarity not listed or is Starter so cannot be found");
        }
    }
    
    
	@Override
	public boolean isInsideZone(final int x, final int y) {
		return this.contains(x, y);
	}

	public List<PokemonDB> getAvailablePokemon() {
		return Collections.unmodifiableList(this.pokemonList);
	}

	public int getAverageLevel() {
		return this.avgLvl;
	}

	@Override
	public int getTileX() {
		return this.x;
	}

	@Override
	public int getTileY() {
		return this.y;
	}

	@Override
	public int getZoneWidth() {
		return this.width;
	}

	@Override
	public int getZoneHeight() {
		return this.height;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this);
	}

	@Override
	public String getZoneName() {
		return "EncounterZone_" + this.id;
	}
	
	public String toString() {
		return getZoneName() + new Position(this.x, this.y) + ", width = " + this.width + ", height = " + this.height;
	}

	

}
