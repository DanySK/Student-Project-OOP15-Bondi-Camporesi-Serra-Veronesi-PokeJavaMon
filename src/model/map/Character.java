package model.map;

public interface Character extends Drawable {

    void move(final Direction d, final PokeMap pm);
    
    Direction getDirection();
    
    void turn(final Direction d);
}
