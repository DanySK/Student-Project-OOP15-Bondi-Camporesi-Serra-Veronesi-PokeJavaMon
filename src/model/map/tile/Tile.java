package model.map.tile;

import model.map.Drawable;

/**
 * The basic space unit of the {@link PokeMap}.
 * Every single of them has an unmodifiable height and width which are the same for every of them
 * Each Tile has a {@link TileType} specified which gives base properties.  
 */
public interface Tile extends Drawable{
    
	/**
	 * Enumeration that describes whether or not a {@link }
	 */
	public enum TileType {
		
        POKEMON_ENCOUNTER(true, true), 
        TERRAIN(false, true), 
        WATER(false, false), //May be implemented later
        TREE(false, false), 
        WALL(false, false), 
        TELEPORT(false, true), 
        BADGETELEPORT(false, true), 
        SIGN(false, false), 
        NPC(false, false), 
        MARKET(false, false), 
        CENTER(false, false), 
        START(false, true),
        DEFEAT(false, true),
        ENCOUNTER(false, false);
        
        ;
    
        private TileType(final boolean wildEncounter, final boolean walkable) {
            this.wildEncounter = wildEncounter;
            this.walkable = walkable;
        }
        
        /*
         * Two properties that each Tile shares with others
         */
        private final boolean wildEncounter;
        private final boolean walkable;
        
        /**
         * @return true if it is a {@link Tile} can trigger an encounter, false otherwise
         */
        public boolean canPokemonAppear() {
            return this.wildEncounter;
        }
        
        /**
         * @return true if you can walk on this {@link Tile}
         */
        public boolean isWalkable() {
            return this.walkable;
        }
        
        @Override
        public String toString() {
        	return this.name().toUpperCase();
        }
    }
    
	/**
	 * @return the type of {@link Tile}
	 */
    public TileType getType();
    
    /**
     * @return true if you can trigger an encounter on this {@link Tile}, false otherwise
     */
    public boolean canPokemonAppear();
    
    /**
     * @return true if you can walk on it, false otherwise
     */
    public boolean isWalkable();
}
