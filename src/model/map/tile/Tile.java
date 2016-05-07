package model.map.tile;

import model.map.Drawable;

public interface Tile extends Drawable{
    public static enum TileType {
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
        
        ;
    
        private TileType(final boolean wildEncounter, final boolean walkable) {
            this.wildEncounter = wildEncounter;
            this.walkable = walkable;
        }
    
        private final boolean wildEncounter;
        private final boolean walkable;
        
        public boolean canPokemonAppear() {
            return this.wildEncounter;
        }
        
        public boolean isWalkable() {
            return this.walkable;
        }
        
        @Override
        public String toString() {
        	return this.name().toUpperCase();
        }
    }
    
    public TileType getType();
    
    public boolean canPokemonAppear();
    
    public boolean isWalkable();
}
