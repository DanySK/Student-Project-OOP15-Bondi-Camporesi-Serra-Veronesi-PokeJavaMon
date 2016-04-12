package model.pokemon;

public final class StaticPokemonFactory {

	private StaticPokemonFactory() {}
	
	public static PokemonInBattle createPokemon(final String pkmnID, final int lvl, final int hp, final int exp, final String[] moves) {
		PokemonDB pokemonID = null;
		Move[] moveset = new Move[PokemonInBattle.MAX_MOVES];
		
		for (final PokemonDB p : PokemonDB.values()) {
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
		
		final PokemonInBattle retPkmn = new PokemonInBattle(pokemonID, lvl);
		retPkmn.currentMoves = moveset;
		retPkmn.changeStat(Stat.HP, hp);
		retPkmn.changeStat(Stat.EXP, exp);
		
		return retPkmn;
	}

	public static PokemonInBattle createPokemon(final PokemonDB pkmnID, final int lvl) {
		return new PokemonInBattle(pkmnID, lvl);
	}

	public static PokemonInBattle createPokemon(final PokemonDB pkmnID, final int lvl, final int hp) {
		final PokemonInBattle retPkmn = new PokemonInBattle(pkmnID, lvl);
		retPkmn.changeStat(Stat.HP, hp);
		return retPkmn;
	}
}
