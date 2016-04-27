package exceptions;

import java.lang.Exception;

public class CannotCaughtTrainerPkmException extends Exception{

	private final static long serialVersionUID = -5352765567595123862L;
	
	public CannotCaughtTrainerPkmException(){
		super("You can't catch a pokemon of a trainer!");
	}
}
