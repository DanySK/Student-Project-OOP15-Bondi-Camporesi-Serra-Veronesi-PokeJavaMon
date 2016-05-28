package model.map.tile;

import model.map.Position;

/**
 * A special kind of Tile that has a {@link Position} where you can teleport to
 */
public class Teleport extends AbstractTile {
    
	//arriving point
    private int toX;
    private int toY;
    
    /**
     * Creates a new Teleport with its current {@link Position} in coordinates and its arriving {@link Position} in coordinates
     * @param x
     * 			x-axis coordinate in tile-units of its current {@link Position}
     * @param y
     * 			y-axis coordinate in tile-units of its current {@link Position}
     * @param toX
     * 			x-axis coordinate in tile-units of its arrival's {@link Position}
     * @param toY
     * 			y-axis coordinate in tile-units of its arrival's {@link Position}
     */
    public Teleport(final int x, final int y, final int toX, final int toY) {
        super(TileType.TELEPORT, Direction.SOUTH, x, y);
        this.toX = toX;
        this.toY = toY;
    }
    
    /**
     * Creates a new Teleport with its current {@link Position} in coordinates and its arriving {@link Position} in coordinates
     * @param from
     * 			current {@link Position} in tile-units
     * @param to
     * 			arrival {@link Position} in tile-units
     */
    public Teleport(final Position from, final Position to) {
        super(TileType.TELEPORT, Direction.SOUTH, to);
        this.toX = to.getX();
        this.toY = to.getY();
    }

    /**
     * @return x-axis coordinate in tile-units of its current {@link Position}
     */
    public int getFromX() {
        return super.tileX;
    }
    
    /**
     * @return y-axis coordinate in tile-units of its current {@link Position}
     */
    public int getFromY() {
        return super.tileY;
    }
    
    /**
     * @return its current {@link Position} in tile-units
     */
    public Position getFrom() {
        return new Position(super.tileX, super.tileY);
    }
    
    /**
     * @return x-axis coordinate in tile-units of its arrival's {@link Position}
     */
    public int getDestinationX() {
        return toX;
    }

    /**
     * @return y-axis coordinate in tile-units of its arrival's {@link Position}
     */
    public int getDestinationY() {
        return toY;
    }
    
    /**
     * @return its arrival's {@link Position} in tile-units
     */
    public Position getDestination() {
        return new Position(this.toX, this.toY);
    }
    
    public String toString() {
    	return "Teleport, from: " + new Position(this.tileX,this.tileY) + ", to: " + new Position(this.toX, this.toY)  ;
    }
}
