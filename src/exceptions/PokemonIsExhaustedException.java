package exceptions;

import  java.lang.Exception;

public class PokemonIsExhaustedException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5711974798274762145L;

	public PokemonIsExhaustedException(){
		super("The selected pokemon is exhausted!");
	}
}
