package model.map;

public abstract class AbstractCharacter implements Drawable {
    
    protected int tileX;
    protected int tileY;
    protected Direction direction;
    
    
    public AbstractCharacter(final int x, final int y, final Direction d) {
        this.tileX = x;
        this.tileY = y;
        this.direction = d;
    }
    
    public abstract void move(final Direction d, final PokeMap pm);
    
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
    
}
