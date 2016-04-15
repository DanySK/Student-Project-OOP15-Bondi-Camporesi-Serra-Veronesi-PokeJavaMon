package model.map.tile;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Range;

import model.map.PokemonEncounterZone;
import model.map.tile.AbstractTile;
import model.pokemon.PokemonDB;
import model.pokemon.PokemonInBattle;
import model.pokemon.PokemonRarity;
import model.pokemon.StaticPokemonFactory;

public class PokemonEncounterTile extends AbstractTile {
    
	public final static String TILE_NAME = "POKEMON_ENCOUNTER";
	
    private final PokemonEncounterZone zone;
    private final static double ENCOUNTER_CHANCE = 30 / 187.5;
    private final static int LEVEL_VARIATION = 2;
    private boolean encountered = false;
    
    public PokemonEncounterTile(final PokemonEncounterZone zone, int x, int y) {
        super(TileType.POKEMON_ENCOUNTER, Direction.SOUTH, x, y);
        this.zone = zone;
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
    
    public PokemonInBattle getPokemonEncounter() {
        if (!encountered) {
            throw new IllegalStateException("Cannot encounter Pokemon if the value is false");
        }
        
        final Map<PokemonDB, Range<Double>> chanceMap = new HashMap<>();
        double probabilitySum = 0;
        final Random r = new Random();
        
        for (final PokemonDB pkmn : zone.getAvailablePokemon()) {
            probabilitySum += getPokemonChance(pkmn);
        }
        
        double tmpSum = 0;
        for (final PokemonDB pkmn : zone.getAvailablePokemon()) {
            double pkmnProbability = getPokemonChance(pkmn) / probabilitySum;
            final Range<Double> range = Range.closedOpen(tmpSum, pkmnProbability + tmpSum);
            chanceMap.put(pkmn, range);
            tmpSum += pkmnProbability;
        }
        
        double chance = r.nextDouble();
        
        for (final Entry<PokemonDB, Range<Double>> e : chanceMap.entrySet()) {
            if (e.getValue().contains(chance)) {
                final int levelVariation = r.nextInt(LEVEL_VARIATION * 2);
                final int level = zone.getAverageLevel() + levelVariation - LEVEL_VARIATION;
                final PokemonInBattle encounterPokemon = StaticPokemonFactory.createPokemon(e.getKey(), level);
                this.encountered = false;
                return encounterPokemon;
            }
        }
        
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
        default :
            throw new IllegalArgumentException();
        }
    }

}
