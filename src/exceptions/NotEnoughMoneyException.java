package exceptions;

public class NotEnoughMoneyException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5517402259546436926L;

    public NotEnoughMoneyException() {
        super("You don't have enough money to purchase this item");
    }
}
