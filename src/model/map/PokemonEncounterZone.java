package model.map;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.player.Player;
import model.pokemon.PokemonDB;

public class PokemonEncounterZone extends Rectangle implements Zone {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4501693972739348662L;
	
	private final int id;
	private final List<PokemonDB> pokemonList;
	private final int avgLvl;
	
	public PokemonEncounterZone(final int id, final String pokemonList, final int averageLevel, final int tileX, final int tileY, final int width, final int height) {
		this.setBounds(tileX, tileY, width, height);
		
		this.pokemonList = new ArrayList<>();
		
		for (final String pkmn : pokemonList.split(" ")) {
			for (final PokemonDB pkmnID : PokemonDB.values()) {
				if (pkmn.equals(pkmnID.toString())) {
					this.pokemonList.add(pkmnID);
				}
			}
		}
		if (this.pokemonList.isEmpty()) {
			throw new IllegalArgumentException("Pokemon Encounter Zone has no valid pokemon found in PokemonDB");
		}
		
		this.id = id;
		this.avgLvl = averageLevel;
	}

	@Override
	public boolean isPlayerInZone(Player pl) {
		return this.contains(pl.getTileX(), pl.getTileY());
	}

	public List<PokemonDB> getAvailablePokemon() {
		return Collections.unmodifiableList(this.pokemonList);
	}

	public int getAverageLevel() {
		return this.avgLvl;
	}

	@Override
	public int getTileX() {
		return this.x;
	}

	@Override
	public int getTileY() {
		return this.y;
	}

	@Override
	public int getZoneWidth() {
		return this.width;
	}

	@Override
	public int getZoneHeight() {
		return this.height;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this);
	}

	@Override
	public String getZoneName() {
		return "EncounterZone_" + this.id;
	}

	

}
