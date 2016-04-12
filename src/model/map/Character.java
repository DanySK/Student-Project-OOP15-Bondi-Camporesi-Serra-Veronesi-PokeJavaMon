package model.map;

public interface Character extends Drawable {

    public void move(final Direction d) throws OutOfBorderException;
    
    public Direction getDirection();
    
}
