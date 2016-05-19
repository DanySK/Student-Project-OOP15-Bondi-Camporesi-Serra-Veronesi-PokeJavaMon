package test.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.google.common.collect.Range;

import model.items.Pokeball;
import model.items.Pokeball.PokeballType;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;

/**
 * Test class to verify that capture probabilities fall in certain ranges
 * Tested with Pokemon of all kinds of rarity and with Full/Half/1 HP
 */
public class TestCaptureRate {
	private Pokeball ultra = new Pokeball(PokeballType.Ultraball);
	private Pokeball great = new Pokeball(PokeballType.Greatball);
	private Pokeball poke = new Pokeball(PokeballType.Pokeball);
	private Pokemon common = StaticPokemonFactory.createPokemon(Pokedex.PIDGEY, 50);
	private Pokemon uncommon = StaticPokemonFactory.createPokemon(Pokedex.RATICATE, 50);
	private Pokemon rare = StaticPokemonFactory.createPokemon(Pokedex.FEAROW, 50);
	private Pokemon veryRare = StaticPokemonFactory.createPokemon(Pokedex.MAGNETON, 50);
	private Pokemon legendary = StaticPokemonFactory.createPokemon(Pokedex.MEW, 50);
	private Pokemon starter = StaticPokemonFactory.createPokemon(Pokedex.CHARMANDER, 50);
	private Pokemon unfindable = StaticPokemonFactory.createPokemon(Pokedex.RHYDON, 50);

	private Double d = 6.4; //deviation
	private Double d2 = 0.3; //smaller deviation
	private Double d3 = 2.0; //medium deviation		

	private Range<Double> r_common_poke = Range.closed(34 - d, 34 + d);
	private Range<Double> r_common_great = Range.closed(50 - d, 50 + d);
	private Range<Double> r_common_ultra = Range.closed(72 - d, 72 + d);
	private Range<Double> r_uncommon_poke = Range.closed(16 - d, 16 + d);
	private Range<Double> r_uncommon_great = Range.closed(27 - d, 27 + d);
	private Range<Double> r_uncommon_ultra = Range.closed(33 - d, 33 + d);
	private Range<Double> r_rare_poke = Range.closed(12 - d, 12 + d);
	private Range<Double> r_rare_great = Range.closed(19 - d, 19 + d);
	private Range<Double> r_rare_ultra = Range.closed(27.0 - d, 27 + d);
	private Range<Double> r_veryRare_poke = Range.closed(8 - d, 8 + d);
	private Range<Double> r_veryRare_great = Range.closed(12 - d, 12 + d);
	private Range<Double> r_veryRare_ultra = Range.closed(16 - d, 16 + d);
	private Range<Double> r_legendary_poke = Range.closed(0.4 - d2, 0.4 + d2);
	private Range<Double> r_legendary_great = Range.closed(0.4 - d2, 0.7 + d2);
	private Range<Double> r_legendary_ultra = Range.closed(0.8 - d2, 1.1 + d2);
	private Range<Double> r_starter_poke = Range.closed(6 - d3, 6 + d3);
	private Range<Double> r_starter_great = Range.closed(9 - d3, 9 + d3);
	private Range<Double> r_starter_ultra = Range.closed(12 - d3, 12 + d3);
	private Range<Double> r_unfindable_poke = Range.closed(0.0, 0.0);
	private Range<Double> r_unfindable_great = Range.closed(0.0, 0.0);
	private Range<Double> r_unfindable_ultra = Range.closed(0.0, 0.0);
	

	@Test
	public void checkCaptureValueLvl50MaxHP() {
		r_common_poke = Range.closed(34 - d, 34 + d);
		r_common_great = Range.closed(50 - d, 50 + d);
		r_common_ultra = Range.closed(72 - d, 72 + d);
		r_uncommon_poke = Range.closed(16 - d, 16 + d);
		r_uncommon_great = Range.closed(27 - d, 27 + d);
		r_uncommon_ultra = Range.closed(33 - d, 33 + d);
		r_rare_poke = Range.closed(12 - d, 12 + d);
		r_rare_great = Range.closed(19 - d, 19 + d);
		r_rare_ultra = Range.closed(27.0 - d, 27 + d);
		r_veryRare_poke = Range.closed(8 - d, 8 + d);
		r_veryRare_great = Range.closed(12 - d, 12 + d);
		r_veryRare_ultra = Range.closed(16 - d, 16 + d);
		r_legendary_poke = Range.closed(0.4 - d2, 0.4 + d2);
		r_legendary_great = Range.closed(0.4 - d2, 0.7 + d2);
		r_legendary_ultra = Range.closed(0.8 - d2, 1.1 + d2);
		r_starter_poke = Range.closed(6 - d3, 6 + d3);
		r_starter_great = Range.closed(9 - d3, 9 + d3);
		r_starter_ultra = Range.closed(12 - d3, 12 + d3);
		r_unfindable_poke = Range.closed(0.0, 0.0);
		r_unfindable_great = Range.closed(0.0, 0.0);
		r_unfindable_ultra = Range.closed(0.0, 0.0);
		

		assertTrue(poke.calculateProbabilityCatch(common, true) + ", poke_common not in range: " + r_common_poke.toString(), r_common_poke.contains(poke.calculateProbabilityCatch(common, true) * 100));
		assertTrue(great.calculateProbabilityCatch(common, true) + ", great_common not in range: " + r_common_great.toString(), r_common_great.contains(great.calculateProbabilityCatch(common, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(common, true) + ", ultra_common not in range: " + r_common_ultra.toString(), r_common_ultra.contains(ultra.calculateProbabilityCatch(common, true) * 100));
		assertTrue(poke.calculateProbabilityCatch(uncommon, true) + ", poke_uncommon not in range: " + r_uncommon_poke.toString(), r_uncommon_poke.contains(poke.calculateProbabilityCatch(uncommon, true) * 100));
		assertTrue(great.calculateProbabilityCatch(uncommon, true) + ", great_uncommon not in range: " + r_uncommon_great.toString(), r_uncommon_great.contains(great.calculateProbabilityCatch(uncommon, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(uncommon, true) + ", ultra_uncommon not in range: " + r_uncommon_ultra.toString(), r_uncommon_ultra.contains(ultra.calculateProbabilityCatch(uncommon, true) * 100));
		assertTrue(poke.calculateProbabilityCatch(rare, true) + ", poke_rare not in range: " + r_rare_poke.toString(), r_rare_poke.contains(poke.calculateProbabilityCatch(rare, true) * 100));
		assertTrue(great.calculateProbabilityCatch(rare, true) + ", great_rare not in range: " + r_rare_great.toString(), r_rare_great.contains(great.calculateProbabilityCatch(rare, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(rare, true) + ", ultra_rare not in range: " + r_rare_ultra.toString(), r_rare_ultra.contains(ultra.calculateProbabilityCatch(rare, true) * 100));
		assertTrue(poke.calculateProbabilityCatch(veryRare, true) + ", poke_veryRare not in range: " + r_veryRare_poke.toString(), r_veryRare_poke.contains(poke.calculateProbabilityCatch(veryRare, true) * 100));
		assertTrue(great.calculateProbabilityCatch(veryRare, true) + ", great_veryRare not in range: " + r_veryRare_great.toString(), r_veryRare_great.contains(great.calculateProbabilityCatch(veryRare, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(veryRare, true) + ", ultra_veryRare not in range: " + r_veryRare_ultra.toString(), r_veryRare_ultra.contains(ultra.calculateProbabilityCatch(veryRare, true) * 100));
		assertTrue(poke.calculateProbabilityCatch(legendary, true) + ", poke_legendary not in range: " + r_legendary_poke.toString(), r_legendary_poke.contains(poke.calculateProbabilityCatch(legendary, true) * 100));
		assertTrue(great.calculateProbabilityCatch(legendary, true) + ", great_legendary not in range: " + r_legendary_great.toString(), r_legendary_great.contains(great.calculateProbabilityCatch(legendary, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(legendary, true) + ", ultra_legendary not in range: " + r_legendary_ultra.toString(), r_legendary_ultra.contains(ultra.calculateProbabilityCatch(legendary, true) * 100));
		assertTrue(poke.calculateProbabilityCatch(starter, true) + ", poke_starter not in range: " + r_starter_poke.toString(), r_starter_poke.contains(poke.calculateProbabilityCatch(starter, true) * 100));
		assertTrue(great.calculateProbabilityCatch(starter, true) + ", great_starter not in range: " + r_starter_great.toString(), r_starter_great.contains(great.calculateProbabilityCatch(starter, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(starter, true) + ", ultra_starter not in range: " + r_starter_ultra.toString(), r_starter_ultra.contains(ultra.calculateProbabilityCatch(starter, true) * 100));
		assertTrue(poke.calculateProbabilityCatch(unfindable, true) + ", poke_unfindable not in range: " + r_unfindable_poke.toString(), r_unfindable_poke.contains(poke.calculateProbabilityCatch(unfindable, true) * 100));
		assertTrue(great.calculateProbabilityCatch(unfindable, true) + ", great_unfindable not in range: " + r_unfindable_great.toString(), r_unfindable_great.contains(great.calculateProbabilityCatch(unfindable, true) * 100));
		assertTrue(ultra.calculateProbabilityCatch(unfindable, true) + ", ultra_unfindable not in range: " + r_unfindable_ultra.toString(), r_unfindable_ultra.contains(ultra.calculateProbabilityCatch(unfindable, true) * 100));
		
	}
	
	@Test
	public void checkCaptureValueLvl50HalfHP() {
		
		common.damage(common.getStat(Stat.MAX_HP)/2);
		uncommon.damage(uncommon.getStat(Stat.MAX_HP)/2);
		rare.damage(rare.getStat(Stat.MAX_HP)/2);
		veryRare.damage(veryRare.getStat(Stat.MAX_HP)/2);
		legendary.damage(legendary.getStat(Stat.MAX_HP)/2);
		starter.damage(starter.getStat(Stat.MAX_HP)/2);
		unfindable.damage(unfindable.getStat(Stat.MAX_HP)/2);
		
		r_common_poke = Range.closed(66 - d, 66 + d);
		r_common_great = Range.closed(99 - d, 99 + d);
		r_common_ultra = Range.closed(99 - d, 99 + d);
		r_uncommon_poke = Range.closed(33 - d, 33 + d);
		r_uncommon_great = Range.closed(50 - d, 53 + d);
		r_uncommon_ultra = Range.closed(78 - d, 78 + d);
		r_rare_poke = Range.closed(25 - d, 25 + d);
		r_rare_great = Range.closed(40 - d, 40 + d);
		r_rare_ultra = Range.closed(50 - d, 50 + d);
		r_veryRare_poke = Range.closed(16 - d, 16 + d);
		r_veryRare_great = Range.closed(25 - d, 25 + d);
		r_veryRare_ultra = Range.closed(33 - d, 33 + d);
		r_legendary_poke = Range.closed(0.6 - d2, 0.6 + d2);
		r_legendary_great = Range.closed(0.8 - d2, 0.9 + d2);
		r_legendary_ultra = Range.closed(1.4 - d2, 1.4 + d2);
		r_starter_poke = Range.closed(12 - d3, 12 + d3);
		r_starter_great = Range.closed(19 - d3, 19 + d3);
		r_starter_ultra = Range.closed(25 - d3, 25 + d3);
		r_unfindable_poke = Range.closed(0.0, 0.1);
		r_unfindable_great = Range.closed(0.0, 0.1);
		r_unfindable_ultra = Range.closed(0.0, 0.1);
		
		assertTrue(poke.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", poke_common not in range: " + r_common_poke.toString(), r_common_poke.contains(poke.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", great_common not in range: " + r_common_great.toString(), r_common_great.contains(great.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", ultra_common not in range: " + r_common_ultra.toString(), r_common_ultra.contains(ultra.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", poke_uncommon not in range: " + r_uncommon_poke.toString(), r_uncommon_poke.contains(poke.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", great_uncommon not in range: " + r_uncommon_great.toString(), r_uncommon_great.contains(great.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", ultra_uncommon not in range: " + r_uncommon_ultra.toString(), r_uncommon_ultra.contains(ultra.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) + ", poke_rare not in range: " + r_rare_poke.toString(), r_rare_poke.contains(poke.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) + ", great_rare not in range: " + r_rare_great.toString(), r_rare_great.contains(great.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) + ", ultra_rare not in range: " + r_rare_ultra.toString(), r_rare_ultra.contains(ultra.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) + ", poke_veryRare not in range: " + r_veryRare_poke.toString(), r_veryRare_poke.contains(poke.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) + ", great_veryRare not in range: " + r_veryRare_great.toString(), r_veryRare_great.contains(great.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) + ", ultra_veryRare not in range: " + r_veryRare_ultra.toString(), r_veryRare_ultra.contains(ultra.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) + ", poke_legendary not in range: " + r_legendary_poke.toString(), r_legendary_poke.contains(poke.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) + ", great_legendary not in range: " + r_legendary_great.toString(), r_legendary_great.contains(great.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) + ", ultra_legendary not in range: " + r_legendary_ultra.toString(), r_legendary_ultra.contains(ultra.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) + ", poke_starter not in range: " + r_starter_poke.toString(), r_starter_poke.contains(poke.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) + ", great_starter not in range: " + r_starter_great.toString(), r_starter_great.contains(great.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) + ", ultra_starter not in range: " + r_starter_ultra.toString(), r_starter_ultra.contains(ultra.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) + ", poke_unfindable not in range: " + r_unfindable_poke.toString(), r_unfindable_poke.contains(poke.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) + ", great_unfindable not in range: " + r_unfindable_great.toString(), r_unfindable_great.contains(great.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) + ", ultra_unfindable not in range: " + r_unfindable_ultra.toString(), r_unfindable_ultra.contains(ultra.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) * 100));
	
		
		
	}
	
	@Test
	public void checkCaptureValueLvl50OneHP() {

		common.heal(common.getStat(Stat.MAX_HP));
		uncommon.heal(common.getStat(Stat.MAX_HP));
		rare.heal(common.getStat(Stat.MAX_HP));
		veryRare.heal(common.getStat(Stat.MAX_HP));
		legendary.heal(common.getStat(Stat.MAX_HP));
		starter.heal(common.getStat(Stat.MAX_HP));
		unfindable.heal(common.getStat(Stat.MAX_HP));
		
		common.damage(common.getStat(Stat.MAX_HP) - 1);
		uncommon.damage(uncommon.getStat(Stat.MAX_HP) - 1);
		rare.damage(rare.getStat(Stat.MAX_HP) - 1);
		veryRare.damage(veryRare.getStat(Stat.MAX_HP) - 1);
		legendary.damage(legendary.getStat(Stat.MAX_HP) - 1);
		starter.damage(starter.getStat(Stat.MAX_HP) - 1);
		
		
		r_common_poke = Range.closed(99 - d, 99 + d);
		r_common_great = Range.closed(99 - d, 99 + d);
		r_common_ultra = Range.closed(99 - d, 99 + d);
		r_uncommon_poke = Range.closed(50 - d, 52.3 + d);
		r_uncommon_great = Range.closed(78 - d, 82 + d);
		r_uncommon_ultra = Range.closed(99 - d, 99 + d);
		r_rare_poke = Range.closed(25 - d, 29 + d);
		r_rare_great = Range.closed(40 - d, 47 + d);
		r_rare_ultra = Range.closed(50 - d, 64 + d);
		r_veryRare_poke = Range.closed(16 - d, 18 + d);
		r_veryRare_great = Range.closed(25 - d, 29 + d);
		r_veryRare_ultra = Range.closed(33 - d, 41 + d);
		r_legendary_poke = Range.closed(0.6 - d2, 1 + d2);
		r_legendary_great = Range.closed(0.8 - d2, 1.5 + d2);
		r_legendary_ultra = Range.closed(1.4 - d2, 2.1 + d2);
		r_starter_poke = Range.closed(12 - d3, 16 + d3);
		r_starter_great = Range.closed(19 - d3, 25 + d3);
		r_starter_ultra = Range.closed(25 - d3, 34 + d3);
		r_unfindable_poke = Range.closed(0.0, 0.1);
		r_unfindable_great = Range.closed(0.0, 0.1);
		r_unfindable_ultra = Range.closed(0.0, 0.1);
		
		assertTrue(poke.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", poke_common not in range: " + r_common_poke.toString(), r_common_poke.contains(poke.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", great_common not in range: " + r_common_great.toString(), r_common_great.contains(great.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) + ", ultra_common not in range: " + r_common_ultra.toString(), r_common_ultra.contains(ultra.calculateProbabilityCatch(common, common.getStat(Stat.MAX_HP) == common.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == uncommon.getCurrentHP()) + ", poke_uncommon not in range: " + r_uncommon_poke.toString(), r_uncommon_poke.contains(poke.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == uncommon.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == uncommon.getCurrentHP()) + ", great_uncommon not in range: " + r_uncommon_great.toString(), r_uncommon_great.contains(great.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == uncommon.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == uncommon.getCurrentHP()) + ", ultra_uncommon not in range: " + r_uncommon_ultra.toString(), r_uncommon_ultra.contains(ultra.calculateProbabilityCatch(uncommon, uncommon.getStat(Stat.MAX_HP) == uncommon.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) + ", poke_rare not in range: " + r_rare_poke.toString(), r_rare_poke.contains(poke.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) + ", great_rare not in range: " + r_rare_great.toString(), r_rare_great.contains(great.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) + ", ultra_rare not in range: " + r_rare_ultra.toString(), r_rare_ultra.contains(ultra.calculateProbabilityCatch(rare, rare.getStat(Stat.MAX_HP) == rare.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) + ", poke_veryRare not in range: " + r_veryRare_poke.toString(), r_veryRare_poke.contains(poke.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) + ", great_veryRare not in range: " + r_veryRare_great.toString(), r_veryRare_great.contains(great.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) + ", ultra_veryRare not in range: " + r_veryRare_ultra.toString(), r_veryRare_ultra.contains(ultra.calculateProbabilityCatch(veryRare, veryRare.getStat(Stat.MAX_HP) == veryRare.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) + ", poke_legendary not in range: " + r_legendary_poke.toString(), r_legendary_poke.contains(poke.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) + ", great_legendary not in range: " + r_legendary_great.toString(), r_legendary_great.contains(great.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) + ", ultra_legendary not in range: " + r_legendary_ultra.toString(), r_legendary_ultra.contains(ultra.calculateProbabilityCatch(legendary, legendary.getStat(Stat.MAX_HP) == legendary.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) + ", poke_starter not in range: " + r_starter_poke.toString(), r_starter_poke.contains(poke.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) + ", great_starter not in range: " + r_starter_great.toString(), r_starter_great.contains(great.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) + ", ultra_starter not in range: " + r_starter_ultra.toString(), r_starter_ultra.contains(ultra.calculateProbabilityCatch(starter, starter.getStat(Stat.MAX_HP) == starter.getCurrentHP()) * 100));
		assertTrue(poke.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) + ", poke_unfindable not in range: " + r_unfindable_poke.toString(), r_unfindable_poke.contains(poke.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) * 100));
		assertTrue(great.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) + ", great_unfindable not in range: " + r_unfindable_great.toString(), r_unfindable_great.contains(great.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) * 100));
		assertTrue(ultra.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) + ", ultra_unfindable not in range: " + r_unfindable_ultra.toString(), r_unfindable_ultra.contains(ultra.calculateProbabilityCatch(unfindable, unfindable.getStat(Stat.MAX_HP) == unfindable.getCurrentHP()) * 100));
	
	
	}
}
