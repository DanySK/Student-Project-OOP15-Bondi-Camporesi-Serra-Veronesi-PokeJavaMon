package model.map.tile;

import model.map.Position;

public class Water extends AbstractTile {

	public Water(int x, int y) {
		super(TileType.WATER, Direction.SOUTH, x, y);
	}
	
	public Water(Position p) {
		super(TileType.WATER, Direction.SOUTH, p);
	}

}