package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import model.pokemon.InitializeMoves;
import model.pokemon.PokemonType;
import model.pokemon.WeaknessTable;

public class TestWeakness {
	
	private Map<PokemonType, List<PokemonType>> mapWeaknesses = new HashMap<>();
	private WeaknessTable t = WeaknessTable.getWeaknessTable();
	
	@Test
	public void testSingleWeaknesses() {
		InitializeMoves.getAllMoves();
		for (final PokemonType pt : PokemonType.values()) {
			for (final PokemonType pt2 : PokemonType.values()) {
				if (mapWeaknesses.get(pt2) == null) {
					mapWeaknesses.put(pt2, new ArrayList<>());
				}
				if (t.getMultiplierAttack(pt, pt2, PokemonType.NONE) == 2) {
					mapWeaknesses.get(pt2).add(pt);
				}
			}
		}
		System.out.println(mapWeaknesses.get(PokemonType.NORMAL));
		assertTrue(mapWeaknesses.get(PokemonType.NORMAL).containsAll(Arrays.asList(PokemonType.FIGHT)));
		assertTrue(mapWeaknesses.get(PokemonType.FIGHT).containsAll(Arrays.asList(PokemonType.FLYING, PokemonType.PSYCHIC)));
		assertTrue(mapWeaknesses.get(PokemonType.FLYING).containsAll(Arrays.asList(PokemonType.ROCK, PokemonType.ELECTR, PokemonType.ICE)));
		assertTrue(mapWeaknesses.get(PokemonType.POISON).containsAll(Arrays.asList(PokemonType.PSYCHIC, PokemonType.GROUND)));
		assertTrue(mapWeaknesses.get(PokemonType.GROUND).containsAll(Arrays.asList(PokemonType.WATER, PokemonType.GRASS, PokemonType.ICE)));
		assertTrue(mapWeaknesses.get(PokemonType.ROCK).containsAll(Arrays.asList(PokemonType.FIGHT, PokemonType.GROUND, PokemonType.STEEL, PokemonType.WATER, PokemonType.GRASS)));
		assertTrue(mapWeaknesses.get(PokemonType.BUG).containsAll(Arrays.asList(PokemonType.FLYING, PokemonType.ROCK, PokemonType.FIRE)));
		assertTrue(mapWeaknesses.get(PokemonType.GHOST).containsAll(Arrays.asList(PokemonType.GHOST, PokemonType.DARK)));
		assertTrue(mapWeaknesses.get(PokemonType.STEEL).containsAll(Arrays.asList(PokemonType.FIGHT, PokemonType.GROUND, PokemonType.FIRE)));
		assertTrue(mapWeaknesses.get(PokemonType.FIRE).containsAll(Arrays.asList(PokemonType.GROUND, PokemonType.ROCK, PokemonType.WATER)));
		assertTrue(mapWeaknesses.get(PokemonType.WATER).containsAll(Arrays.asList(PokemonType.GRASS, PokemonType.ELECTR)));
		assertTrue(mapWeaknesses.get(PokemonType.GRASS).containsAll(Arrays.asList(PokemonType.FIRE, PokemonType.POISON, PokemonType.FLYING, PokemonType.BUG, PokemonType.ICE)));
		assertTrue(mapWeaknesses.get(PokemonType.ELECTR).containsAll(Arrays.asList(PokemonType.GROUND)));
		assertTrue(mapWeaknesses.get(PokemonType.PSYCHIC).containsAll(Arrays.asList(PokemonType.BUG, PokemonType.GHOST, PokemonType.DARK)));
		assertTrue(mapWeaknesses.get(PokemonType.ICE).containsAll(Arrays.asList(PokemonType.FIGHT, PokemonType.ROCK, PokemonType.FIRE, PokemonType.STEEL)));
		assertTrue(mapWeaknesses.get(PokemonType.DRAGON).containsAll(Arrays.asList(PokemonType.ICE, PokemonType.DRAGON)));
		assertTrue(mapWeaknesses.get(PokemonType.DARK).containsAll(Arrays.asList(PokemonType.FIGHT, PokemonType.BUG)));
	
	}

}
