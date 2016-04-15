package model.map;

import java.awt.Rectangle;
import model.player.Player;

public class WalkableZone extends Rectangle implements Zone{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5731057178007223542L;
	
	final String name;
	final String musicPath;
	
	public WalkableZone(final String name, final int x, final int y, final int height, final int width, final String musicPath) {
		this.setBounds(x, y, width, height);
		this.name = name;
		this.musicPath = musicPath;
	}
	
	public String getMusicPath() {
		return this.musicPath;
	}

	@Override
	public boolean isPlayerInZone(Player pl) {
		return this.contains(pl.getTileX(), pl.getTileY());
	}


	public int getTileX() {
		return this.x;
	}

	@Override
	public int getTileY() {
		return this.y;
	}

	@Override
	public int getZoneWidth() {
		return this.width;
	}

	@Override
	public int getZoneHeight() {
		return this.height;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this);
	}

	@Override
	public String getZoneName() {
		return this.name;
	}

}
