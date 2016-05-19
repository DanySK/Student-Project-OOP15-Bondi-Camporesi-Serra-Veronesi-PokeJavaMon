package model.trainer;

import java.util.ArrayList;
import java.util.Map;

import model.map.Drawable.Direction;
import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.StaticPokemonFactory;
import model.squad.Squad;
import model.squad.SquadImpl;

/**
 * A static class that is used to create instances of {@link Trainer} and {@link GymLeader}
 * Implements the Static Factory creation pattern
 * @see Trainer
 * @see GymLeader
 */
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
    
	/**
	 * Creates a {@link Trainer} with all these parameters.
	 * @param trainerName	name of the {@link Trainer}
	 * @param d 	{@link Direction} that the Trainer is facing
	 * @param isDefeated 	true if it is already defeated false otherwise				
	 * @param x		xPosition in the {@link PokeMap}						
	 * @param y		yPosition in the {@link PokeMap}						
	 * @param pkmnList	a {@link Map}<{@link String}, {@link Integer}> where the {@link String} is the name of the {@link Pokemon} in the {@link Pokedex} and the {@link Integer} is the level			
	 * @param initMessage	the first message he says when you fight him				
	 * @param trainerDefeatedMessage	the message he says when you defeat him	
	 * @param trainerWonMessage	the message he says when he defeats you
	 * @param money	the money you get when you defeat him
	 * @param id	trainerID
	 * @return	a newly created {@link Trainer} with such parameters
	 */
    public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final Map<String, Integer> pkmnList,
                                        final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {
        return new Trainer(trainerName, x, y, d, isDefeated, extractSquad(pkmnList), 
                           initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);
    }
    
	/**
	 * Creates a {@link Trainer} with all these parameters.
	 * @param trainerName	name of the {@link Trainer}
	 * @param d 	{@link Direction} that the Trainer is facing
	 * @param isDefeated 	true if it is already defeated false otherwise				
	 * @param x		xPosition in the {@link PokeMap}						
	 * @param y		yPosition in the {@link PokeMap}						
	 * @param pkmns_lvl {@link List}<{@link String}> where each {@link String} is formed as "NAME=LVL" where NAME is the {@link Pokedex} value and LVL is the level of the {@link Pokemon}, E.g.: "CHARIZARD=50" 			
	 * @param initMessage	the first message he says when you fight him				
	 * @param trainerDefeatedMessage	the message he says when you defeat him	
	 * @param trainerWonMessage	the message he says when he defeats you
	 * @param money	the money you get when you defeat him
	 * @param id	trainerID
	 * @return	a newly created {@link Trainer} with such parameters
	 */
    public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final ArrayList<String> pkmns_lvl,
                                        final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {
        return new Trainer(trainerName, x, y, d, isDefeated, extractSquad(pkmns_lvl), 
                           initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);
    }
	
	/**
	 * Creates a {@link Trainer} with all these parameters.
	 * @param trainerName	name of the {@link Trainer}
	 * @param d 	{@link Direction} that the Trainer is facing
	 * @param isDefeated 	true if it is already defeated false otherwise				
	 * @param x		xPosition in the {@link PokeMap}						
	 * @param y		yPosition in the {@link PokeMap}						
	 * @param pkmnList {@link List}<{@link Pokemon}> : all the {@link Pokemon} in the {@link Squad} 			
	 * @param initMessage	the first message he says when you fight him				
	 * @param trainerDefeatedMessage	the message he says when you defeat him	
	 * @param trainerWonMessage	the message he says when he defeats you
	 * @param money	the money you get when you defeat him
	 * @param id	trainerID
	 * @return	a newly created {@link Trainer} with such parameters
	 */
    public static Trainer createTrainer(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final PokemonInBattle[] pkmnList,
            final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id) {
    	return new Trainer(trainerName, x, y, d, isDefeated, new SquadImpl(pkmnList), 
    					   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id);
    }
    
	/**
	 * Creates a {@link GymLeader} with all these parameters.
	 * @param trainerName	name of the {@link GymLeader}
	 * @param d 	{@link Direction} that the GymLeader is facing
	 * @param isDefeated 	true if it is already defeated false otherwise				
	 * @param x		xPosition in the {@link PokeMap}						
	 * @param y		yPosition in the {@link PokeMap}						
	 * @param pkmns_lvl {@link List}<{@link String}> where each {@link String} is formed as "NAME=LVL" where NAME is the {@link Pokedex} value and LVL is the level of the {@link Pokemon}, E.g.: "CHARIZARD=50" 			
	 * @param initMessage	the first message he says when you fight him				
	 * @param trainerDefeatedMessage	the message he says when you defeat him	
	 * @param trainerWonMessage	the message he says when he defeats you
	 * @param money	the money you get when you defeat him
	 * @param id	trainerID
	 * @return	a newly created {@link GymLeader} with such parameters
	 */
    public static GymLeader createGymLeader(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final ArrayList<String> pkmns_lvl,
                                            final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id, final int badge) {
        return new GymLeader(trainerName, x, y, d, isDefeated, extractSquad(pkmns_lvl), 
                             initMessage, trainerWonMessage, trainerDefeatedMessage, money, id, badge);
    }
	
    /**
	 * Creates a {@link GymLeader} with all these parameters.
	 * @param trainerName	name of the {@link GymLeader}
	 * @param d 	{@link Direction} that the GymLeader is facing
	 * @param isDefeated 	true if it is already defeated false otherwise				
	 * @param x		xPosition in the {@link PokeMap}						
	 * @param y		yPosition in the {@link PokeMap}						
	 * @param pkmnList	a {@link Map}<{@link String}, {@link Integer}> where the {@link String} is the name of the {@link Pokemon} in the {@link Pokedex} and the {@link Integer} is the level			
	 * @param initMessage	the first message he says when you fight him				
	 * @param trainerDefeatedMessage	the message he says when you defeat him	
	 * @param trainerWonMessage	the message he says when he defeats you
	 * @param money	the money you get when you defeat him
	 * @param id	trainerID
	 * @return	a newly created {@link GymLeader} with such parameters
	 */
    public static GymLeader createGymLeader(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final Map<String, Integer> pkmnList,
					    final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id, final int badge) {
        return new GymLeader(trainerName, x, y, d, isDefeated, extractSquad(pkmnList), 
                initMessage, trainerWonMessage, trainerDefeatedMessage, money, id, badge);
    }
    
	/**
	 * Creates a {@link GymLeader} with all these parameters.
	 * @param trainerName	name of the {@link GymLeader}
	 * @param d 	{@link Direction} that the GymLeader is facing
	 * @param isDefeated 	true if it is already defeated false otherwise				
	 * @param x		xPosition in the {@link PokeMap}						
	 * @param y		yPosition in the {@link PokeMap}						
	 * @param pkmnList {@link List}<{@link Pokemon}> : all the {@link Pokemon} in the {@link Squad} 			
	 * @param initMessage	the first message he says when you fight him				
	 * @param trainerDefeatedMessage	the message he says when you defeat him	
	 * @param trainerWonMessage	the message he says when he defeats you
	 * @param money	the money you get when you defeat him
	 * @param id	trainerID
	 * @return	a newly created {@link GymLeader} with such parameters
	 */
    public static GymLeader createGymLeader(final String trainerName, final Direction d, final boolean isDefeated, final int x, final int y, final PokemonInBattle[] pkmnList,
            final String initMessage, final String trainerDefeatedMessage, final String trainerWonMessage, final int money, final int id, final int badge) {
    	return new GymLeader(trainerName, x, y, d, isDefeated, new SquadImpl(pkmnList), 
    					   initMessage, trainerWonMessage, trainerDefeatedMessage, money, id, badge);
    }
}
