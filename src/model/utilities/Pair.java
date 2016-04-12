package model.utilities;

public class Pair<X, Y> {
    private X x;
    private Y y;
    
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }
    
    public X getX() {
        return this.x;
    }
    
    public Y getY() {
        return this.y;
    }
    
    public String toString() {
        return "X: " + x + " Y: " + y;
    }
}
