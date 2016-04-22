package model.map.tile;

import model.map.PokeMap;
import model.map.Position;

public abstract class AbstractTile implements Tile {

    protected final int tileX;
    protected final int tileY;
    protected final TileType type;
    protected Direction direction;
    
    public AbstractTile(final TileType t, final Direction d, final int x, final int y) {
        this.type = t;
        this.tileX = x;
        this.tileY = y;
        this.direction = d;
        
    }
    
    public AbstractTile(final TileType t, final Direction d, final Position p) {
        this.type = t;
        this.tileX = p.getX();
        this.tileY = p.getY();
    }


    @Override
    public TileType getType() {
        return this.type;
    }


    @Override
    public boolean canPokemonAppear() {
        return this.type.canPokemonAppear();
    }

    @Override
    public boolean isWalkable() {
        return this.type.isWalkable();
    }


    @Override
    public int getTileX() {
        return this.tileX;
    }


    @Override
    public int getTileY() {
        return this.tileY;
    }
    
    @Override
    public Direction getDirection() {
        return this.direction;
    }

}
