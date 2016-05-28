package model.map;

import controller.MainController;

/**
 * Abstract class to factorize some code among various subclasses
 * such as {@link NPC}, {@link Trainer}, etc..
 */
public abstract class AbstractCharacter implements Character {
    
	/**
	 * current x-axis coordinate in tile-units, made protected to be easily accessible by subclasses
	 */
    protected int tileX;

	/**
	 * current y-axis coordinate in tile-units, made protected to be easily accessible by subclasses
	 */
    protected int tileY;
    
    /**
     * current {@link Direction} he is facing
     */
    protected Direction direction;
    
    /**
     * Basic constructor to initialize the 3 basic components of a {@link Drawable}
     * @param x
     * 			x-axis coordinate in tile-units
     * @param y
     * 			y-axis coordinate in tile-units
     * @param d
     * 			{@link Direction} to face
     */
    protected AbstractCharacter(final int x, final int y, final Direction d) {
        this.tileX = x;
        this.tileY = y;
        this.direction = d;
    }
    
    /**
     * Abstract method that each implementation will define on its own
     * @param d 
     * 			{@link Direction} to move towards, based on his current {@link Position}
     * @param pm
     * 			{@link PokeMap} to apply the movement in the map
     */
    public abstract void move(final Direction d, final PokeMap pm);
    
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getTileX() {
        return this.tileX;
    }

    @Override
    public int getTileY() {
        return this.tileY;
    }
    
    @Override
    public Position getPosition() {
    	return new Position(this.tileX, this.tileY);
    }
    
	@Override
	public void turn(final Direction d) {
		if (this.direction == d) {
			return;
		}
	    PokeMap pm =  MainController.getController().getPokeMap().get();
	    pm.turnCharacter(this, d);
	    this.direction = d;
	}
	
}
