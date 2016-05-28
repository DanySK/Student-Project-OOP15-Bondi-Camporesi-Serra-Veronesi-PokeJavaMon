package model.map;

/**
 * Extends {@link AbstractRectangularZone}, this is basically a simple rectangular zone
 * where, if you walk in you listen to its song
 */
public class WalkableZone extends AbstractRectangularZone {

	//music to be reproduced when walking in it
	private final String musicPath;
	
	/**
	 * Simple constructor that creates a WalkableZone
	 * @param name
	 * 			zone's name
	 * @param x
	 * 			zone's top left corner x-axis
	 * @param y
	 * 			zone's top left corner x-axis
	 * @param width
	 * 			zone's width
	 * @param height
	 * 			zone's height
	 * @param musicPath
	 * 			music path
	 */
	public WalkableZone(final String name, final int x, final int y, final int width, final int height, final String musicPath) {
		super(name, x, y, width, height);
		this.musicPath = musicPath;

	}
	
	/**
	 * @return musicPath in form of string
	 */
	public String getMusicPath() {
		return this.musicPath;
	}

	@Override
	public String toString() { 
		return this.name + " " + this.musicPath + new Position(super.rect.x, super.rect.y) + ", width = " + this.rect.width + ", height = " + this.rect.height;
	}

}
