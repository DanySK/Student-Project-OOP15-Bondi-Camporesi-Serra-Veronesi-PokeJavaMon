package model.map;

public interface Drawable {
    
  public static enum Direction {
        NORTH, EAST, SOUTH, WEST, NONE;
    }
    
    int getTileX();
    
    int getTileY();
    
    Position getPosition();
    
    Direction getDirection();
        
}
