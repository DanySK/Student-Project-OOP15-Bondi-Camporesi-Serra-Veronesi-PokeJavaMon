package model.fight;

public class CannotUseItemInBattleException extends Exception {

	private static final long serialVersionUID = 8520413951015754841L;

	public CannotUseItemInBattleException(){
		super("You can't use this item during battle!");
	}
}
