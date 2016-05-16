package model.map.tile;

import model.map.NPC;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;

public class EncounterTile extends NPC {

	private final Pokemon pokemon;
	
	public EncounterTile(final Pokedex pkmn, final int lvl, final int x, final int y, final Direction d, final String message) {
		super(pkmn.getName(), x, y, d, message);
		this.pokemon = StaticPokemonFactory.createPokemon(pkmn, lvl);
	}
	
	public Pokemon getPokemon() {
		return this.pokemon;
	}
	
	@Override
	public String toString() {
		return "Pokemon Encounter Tile of " + this.pokemon.getPokedexEntry();
	}

}
