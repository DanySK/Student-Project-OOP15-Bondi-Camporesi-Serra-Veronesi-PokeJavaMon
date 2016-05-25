package exceptions;

import java.lang.Exception;

public class PokemonIsFightingException extends Exception{

    /**
     * 
     */
    private final static long serialVersionUID = 8140955350197745549L;

    public PokemonIsFightingException(){
        super("The selected pokemon is already fighting!");
    }
}