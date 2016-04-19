package model.map.tile;

import model.map.Position;

public class Water extends AbstractTile {

   public Water(final int x, final int y) {
		super(TileType.WATER, Direction.SOUTH, x, y);
	}
	
	public Water(final Position p) {
		super(TileType.WATER, Direction.SOUTH, p);
	}

}
