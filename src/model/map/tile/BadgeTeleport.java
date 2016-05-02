package model.map.tile;
	
import model.player.PlayerImpl;

public class BadgeTeleport extends Teleport {

	private final int numOfBadgesRequired;
	
	public BadgeTeleport(final int x, final int y, final int toX, final int toY, final int numOfBadgesRequired) {
		super(x, y, toX, toY);
		this.numOfBadgesRequired = numOfBadgesRequired;
	}
	
	private boolean canTeleport() {
		return PlayerImpl.getPlayer().getLastBadge() >= this.numOfBadgesRequired;
	}

	@Override
	public int getDestinationX() {
		return canTeleport() ? super.getDestinationX() : PlayerImpl.getPlayer().getTileX();
	}
	
	@Override
	public int getDestinationY() {
		return canTeleport() ? super.getDestinationY() : PlayerImpl.getPlayer().getTileY();
	}

}
