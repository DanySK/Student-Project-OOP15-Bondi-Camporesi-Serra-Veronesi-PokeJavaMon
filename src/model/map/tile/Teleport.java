package model.map.tile;

import model.map.Position;

public class Teleport extends AbstractTile {
    
    private int toX;
    private int toY;
    
    public Teleport(final int x, final int y, final int toX, final int toY) {
        super(TileType.TELEPORT, Direction.SOUTH, x, y);
        this.toX = toX;
        this.toY = toY;
    }
    
    public Teleport(final Position from, final Position to) {
        super(TileType.TELEPORT, Direction.SOUTH, to);
        this.toX = to.getX();
        this.toY = to.getY();
    }

    public int getFromX() {
        return super.tileX;
    }

    public int getFromY() {
        return super.tileY;
    }
    
    public Position getFrom() {
        return new Position(super.tileX, super.tileY);
    }

    public int getDestinationX() {
        return toX;
    }

    public int getDestinationY() {
        return toY;
    }
    
    public Position getDestination() {
        return new Position(this.toX, this.toY);
    }
    
}
