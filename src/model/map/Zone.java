package model.map;


import java.awt.Rectangle;

import model.player.Player;

public interface Zone {

	public boolean isPlayerInZone(final Player pl);
	public int getTileX();
	public int getTileY();
	public int getZoneWidth();
	public int getZoneHeight();
	
	public Rectangle getRectangle();
	public String getZoneName();
}
