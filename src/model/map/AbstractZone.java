package model.map;

import java.awt.Rectangle;

public class AbstractZone implements Zone {

	protected final Rectangle rect;
	protected final String name;
	
	protected AbstractZone(final String name, final int x, final int y, final int width, final int height) {
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

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(this.rect);
	}

	@Override
	public String getZoneName() {
		return this.name;
	}

}
