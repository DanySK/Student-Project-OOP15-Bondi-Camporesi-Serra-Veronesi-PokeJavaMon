package model.utilities;

/**
 * Utility class to represent an unmodifialble pair of two different elements 
 *
 * @param <X> Type1
 * @param <Y> Type2
 */
public class Pair<X, Y> {
    private final X x;
    private final Y y;
    
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * @return the first object in the pair
     */
    public X getX() {
        return this.x;
    }
    

    /**
     * 
     * @return the second object in the pair
     */
    public Y getY() {
        return this.y;
    }
    
    public String toString() {
        return "X: " + x + " Y: " + y;
    }
}
