package model.map;


import java.awt.Rectangle;

public interface Zone {

	public boolean contains(final int x, final int y);
	public int getTileX();
	public int getTileY();
	public int getZoneWidth();
	public int getZoneHeight();
	
	public Rectangle getRectangle();
	public String getZoneName();
}
