package model.map.tile;

import model.map.Position;

public class Sign extends AbstractTile {

	private String message;
    
    public Sign(final int x, final int y, final String message) {
        super(Tile.TileType.SIGN, Direction.SOUTH, x, y);
        this.message = message;
    }
    
    public Sign(final Position p, final String message) {
    	super(Tile.TileType.SIGN, Direction.SOUTH, p);
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }

}
