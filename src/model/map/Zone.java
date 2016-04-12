package model.map;


import java.util.List;

import model.player.Player;
import model.pokemon.PokemonDB;

public interface Zone {
	
	public static enum ZoneType {
	    HOME("music/home.mp3"), 
	    OPENING("music/opening.mp3"),
	    LAB("music/lab.mp3"),
	    CENTER("music/center.mp3"),
	    MART("music/mart.mp3"),
	    CAVE("music/cave.mp3"),
	    TOWN("music/town.mp3"),
	    ROUTE("music/route.mp3");
	    
	    private String path;
	    
	    private ZoneType(String s) {
	        this.path = s;
	    }
	    
	    public String getPath() {
	        return this.path;
	    } 
	}
	    
	
    public String getMusicPath();
    public boolean isPlayerInZone(final Player pl);
    public List<PokemonDB> getAvailablePokemon();
    public int getAverageLevel();
}
