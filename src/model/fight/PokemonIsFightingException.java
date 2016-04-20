package model.fight;

import java.lang.Exception;

public class PokemonIsFightingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140955350197745549L;
	
	public PokemonIsFightingException(){
		super("The selected pokemon is already fighting!");
	}
}
