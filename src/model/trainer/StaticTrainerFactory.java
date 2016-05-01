package model.trainer;

import java.util.ArrayList;
import java.util.Map;

import model.map.Drawable.Direction;
import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.StaticPokemonFactory;
import model.squad.Squad;
import model.squad.SquadImpl;


public final class StaticTrainerFactory {
	
	private StaticTrainerFactory() {}
	
	private static Squad extractSquad(final Map<String, Integer> pkmnList) {
		final ArrayList<PokemonInBattle> tmpList = new ArrayList<>();
		for (final String pkmn : pkmnList.keySet()) {
			for (final Pokedex p : Pokedex.values()) {
				if (p.toString().equals(pkmn)) {
					tmpList.add(StaticPokemonFactory.createPokemon(p, pkmnList.get(pkmn)));
				}
			}
		}
		final PokemonInBattle[] retList = new PokemonInBattle[tmpList.size()];
		return new SquadImpl(tmpList.toArray(retList));
	}
	
	private static Squad extractSquad (final ArrayList<String> pkmns_lvl) {
		final ArrayList<PokemonInBattle> tmpList = new ArrayList<>();
		
		for (final String pkmn_lvl : pkmns_lvl) {
			final String pkmn = pkmn_lvl.split("=")[0].toUpperCase();
			final int lvl = Integer.parseInt(pkmn_lvl.split("=")[1]);
			for (final Pokedex p : Pokedex.values()) {
				if (p.toString().equals(pkmn)) {
					tmpList.add(StaticPokemonFactory.createPokemon(p, lvl));
				}
			}
		}
		final PokemonInBattle[] retList = new PokemonInBattle[tmpList.size()];
		return new SquadImpl(tmpList.toArray(retList));
		
	}
	
	public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final Map<String, Integer> pkmnList,
										final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {

		return new Trainer(trainerName, x, y, d, isDefeated, extractSquad(pkmnList), 
						   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);
		

	}

	
	public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final ArrayList<String> pkmns_lvl,
										final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {
		return new Trainer(trainerName, x, y, d, isDefeated, extractSquad(pkmns_lvl), 
				   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);

	}
	
	public static GymLeader createGymLeader(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final ArrayList<String> pkmns_lvl,
			final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id, final int badge) {

		return new GymLeader(trainerName, x, y, d, isDefeated, extractSquad(pkmns_lvl), 
				   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id, badge);


	}
	
	public static GymLeader createGymLeader(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final Map<String, Integer> pkmnList,
										final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id, final int badge) {

		return new GymLeader(trainerName, x, y, d, isDefeated, extractSquad(pkmnList), 
				   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id, badge);

	}
}
