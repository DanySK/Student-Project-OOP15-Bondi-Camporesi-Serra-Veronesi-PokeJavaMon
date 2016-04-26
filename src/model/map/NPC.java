package model.map;

public class NPC extends AbstractCharacter {

	private final String message;
	private final String name;
	
	public NPC(final String name, final int x, final int y, final Direction d, final String message) {
		super(x, y, d);
		this.message = message;
		this.name = name;
	}

	@Override
	public void move(Direction d, PokeMap pm) {}
	
	public String getMessage() {
		return this.name.toUpperCase() + ": " + this.message;
	}

}
