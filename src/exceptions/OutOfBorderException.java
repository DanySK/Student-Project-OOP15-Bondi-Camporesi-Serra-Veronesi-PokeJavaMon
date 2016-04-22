package exceptions;

public class OutOfBorderException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3951035879097802025L;

    public OutOfBorderException() {
        super("This position of this object is out of border");
    }
}
