package model.map;

import java.awt.Rectangle;

/**
 * Interface of a generic rectangular portion of {@link PokeMap}
 */
public interface Zone {

	/**
	 * Checks if a certain point is inside this {@link Zone}
	 * @param x
	 * 			tileX-axis coordinate
	 * @param y
	 * 			tileY-axis coordinate
	 * @return	true if the {@link Position} is inside, false otherwise
	 */
	boolean contains(final int x, final int y);
	
	/**
	 * @return Top-left corner x-axis of the rectangle 
	 */
	int getTileX();
	
	/**
	 * @return Top-left corner y-axis of the rectangle
	 */
	int getTileY();
	
	/**
	 * @return zone's width
	 */
	int getZoneWidth();
	
	/**
	 * @return zone's height
	 */
	int getZoneHeight();
	
	Rectangle getRectangle();
	String getZoneName();
}
