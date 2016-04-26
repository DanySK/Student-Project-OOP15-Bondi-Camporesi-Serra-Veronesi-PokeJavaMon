package model.map;

public interface Character extends Drawable {

    public void move(final Direction d, final PokeMap pm);
    
    public Direction getDirection();
    
    public void turn(final Direction d);
}
