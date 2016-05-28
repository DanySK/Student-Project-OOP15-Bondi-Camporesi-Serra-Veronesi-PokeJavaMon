package model.map;

import java.awt.Rectangle;

/**
 * Abstract class that implements {@link Zone} with a rectangular based shape (via {@link Rectangle})
 * It was made to factorize code from {@link WalkableZone} and {@link PokemonEncounterZone}
 */
public class AbstractRectangularZone implements Zone {

	/**
	 * A geometrical shape to easily group a zone
	 */
	protected final Rectangle rect;
	
	/**
	 * Its name
	 */
	protected final String name;
	
	/**
	 * Base constructor to initialize all needed fields to represent a rectangular-shaped {@link Zone}
	 * @param name
	 * 			Name given to the {@link Zone}
	 * @param x
	 * 			x-axis coordinate in tile-units of its top-left corner of the {@link Rectangle}
	 * @param y
	 * 			y-axis coordinate in tile-units of its top-left corner of the {@link Rectangle}
	 * @param width
	 * 			width in tile-units of the {@link Rectangle}
	 * @param height
	 * 			height in tile-units of the {@link Rectangle}
	 * 			
	 */
	protected AbstractRectangularZone(final String name, final int x, final int y, final int width, final int height) {
		this.rect = new Rectangle(x, y, width, height);
		this.name = name;
	}
	
	@Override
	public boolean contains(final int x, final int y) {
		return this.rect.contains(x, y);
	}

	@Override
	public int getTileX() {	
		return this.rect.x;
	}

	@Override
	public int getTileY() {
		return this.rect.y;
	}

	@Override
	public int getZoneWidth() {
		return this.rect.width;
	}

	@Override
	public int getZoneHeight() {
		return this.rect.height;
	}

	/**
	 * @return zone's {@link Rectangle}
	 */
	public Rectangle getRectangle() {
		return new Rectangle(this.rect);
	}

	@Override
	public String getZoneName() {
		return this.name;
	}

}
