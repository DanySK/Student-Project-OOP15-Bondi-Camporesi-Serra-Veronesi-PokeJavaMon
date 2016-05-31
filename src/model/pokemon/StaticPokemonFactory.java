package model.pokemon;

import model.squad.Squad;

/**
 * A static class that implements the Static Factory Pattern for the creation of {@link Pokemon}. 
 */
public final class StaticPokemonFactory {

    private StaticPokemonFactory() {}
	
	/**
	 * Creates a Pokemon from the ID (the position in the {@link Pokedex}), level, current HP, current EXP, and the current moveset.
	 * Basically it's method that allows the loading of the {@link Squad} from the save file
	 * @param pkmnID	
	 * 			{@link Pokedex}.ordinal()
	 * @param lvl		
	 * 			the level
	 * @param hp		
	 * 			the current HP
	 * @param exp		
	 * 			the current EXP
	 * @param moves		
	 * 			the current moveset
	 * @return	the newly created {@link Pokemon} 
	 * @throws IllegalArgumentException if 1) pkmnID does not match any available {@link Pokedex} entry, 
	 * 									   2) if one of the {@link Move} is not present in its learnable moveset
	 */
    public static PokemonInBattle createPokemon(final String pkmnID, final int lvl, final int hp, final int exp, final String[] moves) throws IllegalArgumentException {
        Pokedex pokemonID = null;
        Move[] moveset = new Move[PokemonInBattle.MAX_MOVES];
		
        for (final Pokedex p : Pokedex.values()) {
            if (pkmnID.toUpperCase().equals(p.name())) {
                pokemonID = p;
            }
        }
        if (pokemonID == null) {
            throw new IllegalArgumentException("Pokemon name not found");
        }
        for (int i = 0; i < moves.length; i++) {
            for (final Move m : Move.values()) {
                if (moves[i].toUpperCase().equals(m.name())) {
                    moveset[i] = m;
                }
            }
            if (moveset[i] == null) {
                throw new IllegalArgumentException("Move not found");
            }
        }
        for (int i = moves.length; i < AbstractPokemon.MAX_MOVES; i++) {
            moveset[i] = Move.NULLMOVE;
        }
        final PokemonInBattle retPkmn = new PokemonInBattle(pokemonID, lvl);
        retPkmn.currentMoves = moveset;

        if (hp < retPkmn.getStat(Stat.MAX_HP) && hp >= 0) {
            retPkmn.damage(retPkmn.getStat(Stat.MAX_HP) - hp);
        }
        retPkmn.changeStat(Stat.EXP, exp);
        return retPkmn;
    }

	/**
	 * Creates a {@link Pokemon} from its index in the {@link Pokedex} with max HP, 0 EXP and the last 4 learnable moves.
	 * @param pkmnID	
	 * 			{@link Pokedex} index
	 * @param lvl		
	 * 			level
	 * @return	a newly created {@link Pokemon}
	 */
    public static PokemonInBattle createPokemon(final Pokedex pkmnID, final int lvl) {
        return new PokemonInBattle(pkmnID, lvl);
    }

	/**
	 * Creates a {@link Pokemon} from its index int the {@link Pokedex} with fixed hp, 0 EXP and the last 4 learnable moves.
	 * @param pkmnID	
	 * 			{@link Pokedex} index
	 * @param lvl
	 * 			level
	 * @param hp		
	 * 			current HP
	 * @return	a newly created {@link Pokemon}
	 */
    public static PokemonInBattle createPokemon(final Pokedex pkmnID, final int lvl, final int hp) {
        final PokemonInBattle retPkmn = new PokemonInBattle(pkmnID, lvl);
        if (hp < retPkmn.getStat(Stat.MAX_HP) && hp > 0) {
            retPkmn.damage(retPkmn.getStat(Stat.MAX_HP) - hp);
        }
        return retPkmn;
    }
}
