package model.map.tile;
	
import model.map.Position;
import model.player.Player;
import model.player.PlayerImpl;

/**
 * A further extension of {@link Teleport}, returns the actual destination {@link Position}
 * only if the {@link Player} has a sufficient amount of badges
 */
public class BadgeTeleport extends Teleport {

	//badges that a player is required to have to show the actual 
	private final int numOfBadgesRequired;
	
	/**
	 * Creates an instance similarly to {@link Teleport} constructors but specifying a number of badges required
	 * @param x
	 * 			x-axis coordinate in tile units of its current {@link Position}
	 * @param y
	 * 			y-axis coordinate in tile units of its current {@link Position}
	 * @param toX
	 * 			x-axis coordinate in tile units of its arrival's {@link Position}
	 * @param toY
	 * 			y-axis coordinate in tile units of its arrival's {@link Position}
	 * @param numOfBadgesRequired
	 * 			number of badges required to have it work
	 */
	public BadgeTeleport(final int x, final int y, final int toX, final int toY, final int numOfBadgesRequired) {
		super(x, y, toX, toY);
		this.numOfBadgesRequired = numOfBadgesRequired;
	}
	
	/**
	 * @return true if {@link Player}'s badges are at least equal or higher
	 */
	public boolean canTeleport() {
		return PlayerImpl.getPlayer().getLastBadge() >= this.numOfBadgesRequired;
	}

	/**
	 * Overriden as if the {@link Player} does not have enough badges 
	 * @return x-axis coordinate in tile-units of its arrival point, if you have enough badges
	 */
	@Override
	public int getDestinationX() {
		return canTeleport() ? super.getDestinationX() : PlayerImpl.getPlayer().getTileX();
	}

	/**
	 * Overriden as if the {@link Player} does not have enough badges 
	 * @return y-axis coordinate in tile-units of its arrival point, if you have enough badges
	 */
	@Override
	public int getDestinationY() {
		return canTeleport() ? super.getDestinationY() : PlayerImpl.getPlayer().getTileY();
	}

}
