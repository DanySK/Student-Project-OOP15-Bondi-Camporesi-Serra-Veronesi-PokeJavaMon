package model.map;

import java.awt.Rectangle;

/**
 * Interface of a generic portion of {@link PokeMap}, current implementation ({@link AbstractRectangularZone}) is
 * achieved via {@link Rectangle} but it can be implemented with other polygons/shapes, as long as
 * the method {@link Zone#contains(int, int)} works
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
	 * @return Top-left corner x-axis of the zone 
	 */
	int getTileX();
	
	/**
	 * @return Top-left corner y-axis of the zone
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
	
	/**
	 * @return zone's name
	 */
	String getZoneName();
}
