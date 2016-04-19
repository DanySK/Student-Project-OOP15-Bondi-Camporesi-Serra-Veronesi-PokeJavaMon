package model.trainer;

import java.util.ArrayList;
import java.util.Map;

import model.map.Drawable.Direction;
import model.pokemon.PokemonDB;
import model.pokemon.PokemonInBattle;
import model.pokemon.StaticPokemonFactory;
import model.squad.SquadImpl;


public final class StaticTrainerFactory {
	
	private StaticTrainerFactory() {}
	
	public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final Map<String, Integer> pkmnList,
										final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {
		final ArrayList<PokemonInBattle> tmpList = new ArrayList<>();
		for (final String pkmn : pkmnList.keySet()) {
			for (final PokemonDB p : PokemonDB.values()) {
				if (p.toString().equals(pkmn)) {
					tmpList.add(StaticPokemonFactory.createPokemon(p, pkmnList.get(pkmn)));
				}
			}
		}
		return new Trainer(trainerName, x, y, d, isDefeated, new SquadImpl((PokemonInBattle[]) tmpList.toArray()), 
						   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);
		

	}

	
	public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final ArrayList<String> pkmns_lvl,
										final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {
		final ArrayList<PokemonInBattle> tmpList = new ArrayList<>();
		
		for (final String pkmn_lvl : pkmns_lvl) {
			final String pkmn = pkmn_lvl.split("=")[0].toUpperCase();
			final int lvl = Integer.parseInt(pkmn_lvl.split("=")[1]);
			for (final PokemonDB p : PokemonDB.values()) {
				if (p.toString().equals(pkmn)) {
					tmpList.add(StaticPokemonFactory.createPokemon(p, lvl));
				}
			}
		}
		
		return new Trainer(trainerName, x, y, d, isDefeated, new SquadImpl(tmpList.toArray(new PokemonInBattle[tmpList.size()])), 
				   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);

	}
}
