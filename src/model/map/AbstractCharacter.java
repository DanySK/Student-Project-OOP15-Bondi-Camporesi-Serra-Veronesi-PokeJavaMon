package model.map;


public abstract class AbstractCharacter implements Drawable {
    
    protected int tileX;
    protected int tileY;
    protected Direction direction;
    
    
    public AbstractCharacter(final int x, final int y, final Direction d) {
        this.tileX = y;
        this.tileY = y;
        this.direction = d;
    }
    
    public void move(final Direction d) throws OutOfBorderException {

       this.direction = d;
       switch (d) {
       case NORTH :
           if (PokeMap.isOutOfBorder(this.tileX, this.tileY+1)) {
               throw new OutOfBorderException();
           }
           this.tileY++;
           break;
       case EAST :
           if (PokeMap.isOutOfBorder(this.tileX+1, this.tileY)) {
               throw new OutOfBorderException();
           }
           this.tileX ++;
           break;
       case SOUTH :
           if (PokeMap.isOutOfBorder(this.tileX, this.tileY-1)) {
               throw new OutOfBorderException();
           }
           this.tileY --;
       case WEST :
           if (PokeMap.isOutOfBorder(this.tileX-1, this.tileY)) {
               throw new OutOfBorderException();
           }
           this.tileX --;
       }
    }
    
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
    

	@SuppressWarnings("null")
	public Tile nextTile(Direction d) {
		final PokeMap pmMap = null; //TODO import map, not null
		switch (d) {
		case NORTH :
			return pmMap.getTile(this.tileX, this.tileY + 1);
		case EAST :
			return pmMap.getTile(this.tileX + 1, this.tileY);
		case WEST :
			return pmMap.getTile(this.tileX - 1, this.tileY);
		case SOUTH :
			return pmMap.getTile(this.tileX, this.tileY -1);
		default :
			return null;
		}
	}


}
