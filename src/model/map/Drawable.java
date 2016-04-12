package model.map;

public interface Drawable {
    
  public static enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }
    
    public int getTileX();
    
    public int getTileY();
    
    public Direction getDirection();
        
}
