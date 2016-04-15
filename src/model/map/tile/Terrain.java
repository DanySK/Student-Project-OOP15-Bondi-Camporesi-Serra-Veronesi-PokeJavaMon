package model.map.tile;

import model.map.Position;

public class Terrain extends AbstractTile {

	public final static String TILE_NAME = "TERRAIN";
	
	public Terrain(TileType t, int x, int y) {
		super(t, Direction.SOUTH, x, y);
	}
	
	public Terrain(TileType t, Position p) {
		super(t, Direction.SOUTH, p);
	}

}
