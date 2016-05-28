package model.map.tile;

import model.map.NPC;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.PokemonRarity;
import model.pokemon.StaticPokemonFactory;

/**
 * Single {@link Tile} that, when the {@link Player} interacts with it, results in a fight
 * with the specified {@link Pokemon}, which often is of {@link PokemonRarity#LEGENDARY} or at least special (e.g. {@link Pokedex#SNORLAX}
 */
public class SpecialEncounterTile extends NPC {

	private final Pokemon pokemon;
	private boolean isEncounterable;
	
	/**
	 * Creates a new instance with a newly created {@link Pokemon}, its current {@link Position} and {@link Direction}
	 * @param pkmn
	 * 			{@link Pokedex} entry used with level to create the {@link Pokemon}
	 * @param lvl
	 * 			level of the {@link Pokemon}
	 * @param x
	 * 			x-axis coordinate in tile-units of its current {@link Position}
	 * @param y
	 * 			y-axis coordinate in tile-units of its current {@link Position}
	 * @param d
	 * 			{@link Direction} it is facing to
	 * @param message
	 * 			message (or cry) it says when first met
	 */
	public SpecialEncounterTile(final Pokedex pkmn, final int lvl, final int x, final int y, final Direction d, final String message) {
		super(pkmn.getName(), x, y, d, message);
		this.pokemon = StaticPokemonFactory.createPokemon(pkmn, lvl);
		this.isEncounterable = true;
	}
	
	/**
	 * @return {@link Pokemon} that you will fight
	 * @throws IllegalStateException if you cannot encounter it
	 */
	public Pokemon getPokemon() throws IllegalStateException {
		if (!this.isEncounterable) {
			throw new IllegalStateException();
		}
		return this.pokemon;
	}
	
	/**
	 * now you can no more encounter the {@link Pokemon} of this {@link Tile}
	 */
	public void setNotEncounterable() {
		this.isEncounterable = false;
	}
	
	/**
	 * @return true if you can find it, false otherwise
	 */
	public boolean isEncounterable() {
		return this.isEncounterable;
	}
	
	@Override
	public String toString() {
		return "Pokemon Encounter Tile of " + this.pokemon.getPokedexEntry();
	}

}
