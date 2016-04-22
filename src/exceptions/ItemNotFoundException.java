package exceptions;

public class ItemNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3628328773721362296L;

    public ItemNotFoundException() {
        super("Item not found in invetory");
    }
}
