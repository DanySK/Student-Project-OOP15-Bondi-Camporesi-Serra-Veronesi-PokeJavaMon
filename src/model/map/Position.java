package model.map;

public class Position {
    private int x;
    private int y;
    
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public Position(final Position p) {
        this.x = p.x;
        this.y = p.y;
    }
     
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y; 
    }
}
