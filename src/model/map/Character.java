package model.map;

import model.map.tile.Tile;

/**
 * Interface that adds new basic methods to approach a Character from {@link Drawable} interface 
 */
public interface Character extends Drawable {

	/**
	 * Moves the Character from its current {@link Position} to 1 {@link Tile} next to him
	 * in the specified {@link Direction}
	 * @param d
	 * 			{@link Direction} to move to
	 * @param pm
	 * 			{@link PokeMap} to apply changes
	 */
    void move(final Direction d, final PokeMap pm);
    
    /**
     * @return {@link Direction} he is facing
     */
    Direction getDirection();
    
    /**
     * Turns to the given {@link Direction}
     * @param d
     * 			{@link Direction} to face to
     */
    void turn(final Direction d);
}
