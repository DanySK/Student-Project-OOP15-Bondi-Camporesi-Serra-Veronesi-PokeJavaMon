package model.map;

public interface Tile extends Drawable{
    public static enum TileType {
        POKEMON_ENCOUNTER(true, true), TERRAIN(false, true), WATER(true, false), TREE(false, false), WALL(false, false), TELEPORT(false, true), SIGN(false, false);
    
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
    }
    
    public int getTileWidth();
    
    public int getTileHeight();

    public TileType getType();
    
    public boolean canPokemonAppear();
    
    public boolean isWalkable();
}
