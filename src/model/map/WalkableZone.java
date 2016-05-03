package model.map;

import java.awt.Rectangle;

public class WalkableZone extends Rectangle implements Zone{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5731057178007223542L;
	
	final String name;
	final String musicPath;
	
	public WalkableZone(final String name, final int x, final int y, final int widht, final int height, final String musicPath) {
		this.setBounds(x, y, width, height);
		this.name = name;
		this.musicPath = musicPath;
	}
	
	public String getMusicPath() {
		return this.musicPath;
	}

	@Override
	public boolean isInsideZone(final int x, final int y) {
		return this.contains(x,y);
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
	
	@Override
	public String toString() { 
		return this.name + " " + this.musicPath + new Position(this.x,this.y) + ", width = " + this.width + ", height = " + this.height;
	}

}
