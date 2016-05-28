package model.map;

/**
 * Simple class used to represent an unmodifiable position in the map.
 * Uses two integer to represent the coordinates of the position inside the map
 */
public class Position {
	//Coordinates
    private int x;
    private int y;
    
    /**
     * @param x
     * 			x-axis coordinate of the point
     * @param y
     * 			y-axis coordinate of the point
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Creates an unmodifiable copy of the position passed as argument
     * @param p
     * 			Position to be copied
     */
    public Position(final Position p) {
        this.x = p.x;
        this.y = p.y;
    }
     
    /**
     * @return x-axis coordinate
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * @return y-axis coordinate
     */
    public int getY() {
        return this.y;
    }
    
    public String toString() {
    	return " [x=" + x + ", y=" + y + "] ";
    }
}
