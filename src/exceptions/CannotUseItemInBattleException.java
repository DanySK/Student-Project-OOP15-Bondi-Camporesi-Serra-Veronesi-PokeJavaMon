package exceptions;

public class CannotUseItemInBattleException extends Exception {

    /**
     * 
     */
    private final static long serialVersionUID = 8520413951015754841L;

    public CannotUseItemInBattleException(){
        super("You can't use this item during battle!");
    }
}