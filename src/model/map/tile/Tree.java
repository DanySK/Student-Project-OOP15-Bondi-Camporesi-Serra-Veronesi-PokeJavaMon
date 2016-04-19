package model.map.tile;

import model.map.Position;

public class Tree extends AbstractTile {

	public Tree(final int x, final int y) {
		super(TileType.TREE, Direction.SOUTH, x, y);
	}
	
	public Tree(final Position p) {
		super(TileType.TREE, Direction.SOUTH, p);
	}
}
