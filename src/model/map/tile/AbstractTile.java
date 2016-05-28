package model.map.tile;

import model.map.Position;

/**
 * Abstract class that factorize most of {@link Tile}'s needed code.
 * It contains the basic fields to represent a specific {@link Tile} in the {@link PokeMap}
 */
public abstract class AbstractTile implements Tile {

	/**
	 * x-axis coordinates in tile-units of its {@link Position}
	 */
	protected final int tileX;

	/**
	 * y-axis coordinates in tile-units of its {@link Position}
	 */
	protected final int tileY;
    
	/**
	 * Type of this {@link Tile}
	 */
    protected final TileType type;
    
    /**
     * {@link Direction} of this {@link Tile}, usually by default it is {@link Direction#SOUTH}
     */
    protected Direction direction;
    
    /**
     * Constructor that instantiate it with base fields
     * @param t
     * 			{@link TileType} of the {@link Tile}
     * @param d
     * 			{@link Direction} of this {@link Tile}
     * @param x
     * 			x-axis coordinates in tile-units of its {@link Position}
     * @param y
     * 			y-axis coordinates in tile-units of its {@link Position}
     */
    protected AbstractTile(final TileType t, final Direction d, final int x, final int y) {
        this.type = t;
        this.tileX = x;
        this.tileY = y;
        this.direction = d;
        
    }
    
    /**
     * Constructor that instantiate it with base fields
     * @param t
     * 			{@link TileType} of the {@link Tile}
     * @param d
     * 			{@link Direction} of this {@link Tile}
     * @param p
     * 			{@link Position} of this {@link Tile}
     */
    protected AbstractTile(final TileType t, final Direction d, final Position p) {
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
    public Position getPosition() {
    	return new Position(this.tileX, this.tileY);
    }
    
    @Override
    public Direction getDirection() {
        return this.direction;
    }

}
