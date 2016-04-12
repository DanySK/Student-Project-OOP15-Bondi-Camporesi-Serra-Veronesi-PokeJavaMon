package model.map;


import java.util.List;

import model.pokemon.PokemonDB;

public interface Zone {
    
    
    public String getMusic();
    public boolean isInThisZone(final float x, final float y);
    public List<PokemonDB> getAvailablePokemon();
    public int getAverageLevel();
}
