package model.map.tile;

import model.map.Position;
import model.player.PlayerImpl;

public class Sign extends AbstractTile {

	private String message;
    
    public Sign(final int x, final int y, final String message) {
        super(Tile.TileType.SIGN, Direction.SOUTH, x, y);

        this.message = message;
    	if (this.message != null) {
    		if (this.message.indexOf("%%PLAYER%%") != -1) {
    			this.message = this.message.replaceAll("%%PLAYER%%", PlayerImpl.getPlayer().getName());
    		}
    	} else {
    		this.message = "VOID_SIGN";
    	}
    	
    }
    
    public Sign(final Position p, final String message) {
    	super(Tile.TileType.SIGN, Direction.SOUTH, p);
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String toString() {
    	return "Sign " + new Position(this.tileX, this.tileY) + ", Message: " + this.message;
    }

}
