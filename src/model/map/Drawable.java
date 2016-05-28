package model.map;

/**
 * Simple interface to represent a simple object which can be drawn in the {@link PokeMap}
 * In order to be drawable this object needs to have certain coordinates and eventually a {@link Direction}
 */
public interface Drawable {
    
  public static enum Direction {
        NORTH, EAST, SOUTH, WEST, NONE;
    }
    
  /**
   * @return x-axis coordinate in tile-units
   */
  int getTileX();
    
  /**
   * @return y-axis coordinate in tile-units
   */
  int getTileY();
    
  /**
   * @return a complete {@link Position} in the {@link PokeMap}
   */
  Position getPosition();
    
  /**
   * @return {@link Direction} that this {@link Drawable} is facing, if it does not matter it is by default {@link Direction#SOUTH}
   */
  Direction getDirection();
        
}
