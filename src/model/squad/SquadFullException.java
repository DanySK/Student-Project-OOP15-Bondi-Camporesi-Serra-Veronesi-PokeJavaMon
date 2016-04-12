package model.squad;

public class SquadFullException extends Exception {

    
    /**
     * 
     */
    private static final long serialVersionUID = 4196233501354359888L;

    public SquadFullException() {
        super("You cannot retrieve because your squad is full");
    }
    
    
}
