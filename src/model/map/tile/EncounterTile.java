package model.map.tile;

import model.map.NPC;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;

public class EncounterTile extends NPC {

	private final Pokemon pokemon;
	private boolean isEncounterable;
	
	public EncounterTile(final Pokedex pkmn, final int lvl, final int x, final int y, final Direction d, final String message) {
		super(pkmn.getName(), x, y, d, message);
		this.pokemon = StaticPokemonFactory.createPokemon(pkmn, lvl);
		this.isEncounterable = true;
	}
	
	public Pokemon getPokemon() {
		return this.pokemon;
	}
	
	public void setNotEncounterable() {
		this.isEncounterable = false;
	}
	
	public boolean isEncounterable() {
		return this.isEncounterable;
	}
	
	@Override
	public String toString() {
		return "Pokemon Encounter Tile of " + this.pokemon.getPokedexEntry();
	}

}
