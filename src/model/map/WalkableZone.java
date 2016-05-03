package model.map;

public class WalkableZone extends AbstractZone {

	private final String musicPath;
	
	public WalkableZone(final String name, final int x, final int y, final int width, final int height, final String musicPath) {
		super(name, x, y, width, height);
		this.musicPath = musicPath;

	}
	
	public String getMusicPath() {
		return this.musicPath;
	}

	@Override
	public String toString() { 
		return this.name + " " + this.musicPath + new Position(super.rect.x, super.rect.y) + ", width = " + this.rect.width + ", height = " + this.rect.height;
	}

}
