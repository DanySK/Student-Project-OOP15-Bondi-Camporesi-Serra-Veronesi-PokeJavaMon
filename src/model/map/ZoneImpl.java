package model.map;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;



import model.player.Player;
import model.pokemon.PokemonDB;

public class ZoneImpl implements Zone {

	final Rectangle r;
	final ZoneType type;
	final int averageLevel;
	final List<PokemonDB> availablePokemon;
	
	public ZoneImpl(final ZoneType zt, final int x, final int y, final int height, final int width, final List<PokemonDB> availablePkmn, final int averageLvl) {
		r = new Rectangle(x,y,width,height);
		type = zt;
		this.averageLevel = averageLvl;
		this.availablePokemon = availablePkmn;
	}
	
	@Override
	public String getMusicPath() {
		return this.type.getPath();
	}

	@Override
	public boolean isPlayerInZone(Player pl) {
		return r.contains(pl.getTileX(), pl.getTileY());
	}

	@Override
	public List<PokemonDB> getAvailablePokemon() {
		return Collections.unmodifiableList(availablePokemon);
	}

	@Override
	public int getAverageLevel() {
		return this.averageLevel;
	}

}
