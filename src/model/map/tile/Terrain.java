package model.map.tile;

import model.map.Position;

public class Terrain extends AbstractTile {

	public Terrain(final int x, final int y) {
		super(TileType.TERRAIN, Direction.SOUTH, x, y);
	}
	
	public Terrain(final Position p) {
		super(TileType.TERRAIN, Direction.SOUTH, p);
	}

}
