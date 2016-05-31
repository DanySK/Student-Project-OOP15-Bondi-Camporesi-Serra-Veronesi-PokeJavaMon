package model.map.tile;

import model.map.Position;
import model.player.Player;
import model.player.PlayerImpl;

/**
 * Special kind of {@link Tile} that simply contains a message to show
 */
public class Sign extends AbstractTile {

	//message to be shown
	private String message;
    
	/**
	 * Creates an instance with its current position in coordinates and the message to show
	 * @param x
	 * 			x-axis coordinate in tile-units of its current {@link Position}
	 * @param y
	 * 			y-axis coordinate in tile-units of its current {@link Position}
	 * @param message
	 * 			message to show when the {@link Player} interacts with it
	 */
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
    
	/**
	 * Creates an instance with its current {@link Position} and the message to show
	 * @param p
	 * 			current {@link Position} in tile-units 
	 * @param message
	 * 			message to show when the {@link Player} interacts with it
	 */
    public Sign(final Position p, final String message) {
    	super(Tile.TileType.SIGN, Direction.SOUTH, p);
        this.message = message;
    }
    
    /**
     * @return message to show when interacted
     */
    public String getMessage() {
        return this.message;
    }
    
    public String toString() {
    	return "Sign " + new Position(this.tileX, this.tileY) + ", Message: " + this.message;
    }

}
