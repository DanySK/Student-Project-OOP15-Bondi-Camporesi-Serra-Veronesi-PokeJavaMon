package model.map.tile;

import model.map.Position;

public class Wall extends AbstractTile {

	public Wall(int x, int y) {
		super(TileType.WALL, Direction.SOUTH, x, y);
	}
	
	public Wall(Position p) {
		super(TileType.WALL, Direction.SOUTH, p);
	}

	
}
