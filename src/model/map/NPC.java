package model.map;

import model.map.Drawable.Direction;
import model.trainer.Trainer;

/**
 * Non-Playable Character, that simply tells a message if you interact with him
 * Contains also the basic funtionality of an AbstractCharacter like turning when talking to him/her.
 */
public class NPC extends AbstractCharacter {

	private final String message;
	private final String name;
	
	/**
	 * Creates an NPC with a single message.
	 * @param name
	 * 			NPC's name
	 * @param x
	 * 			x-axis coordinate in tile-units
	 * @param y
	 * 			y-axis coordinate in tile-units
	 * @param d
	 * 			{@link Direction} NPC is facing
	 * @param message
	 * 			line NPC says when interacted
	 */
	public NPC(final String name, final int x, final int y, final Direction d, final String message) {
		super(x, y, d);
		this.message = message;
		this.name = name;
	}

    /**
     * Overriden to do nothing as a {@link Trainer} cannot move
     * @param d
     * @param pm
     */
    @Override
    public void move(final Direction d, final PokeMap pm) {
    	/*
    	 * Empty method
    	 */
    }
    
	/**
	 * @return the message he says when interacted
	 */
	public String getMessage() {
		return this.name.toUpperCase() + ": " + this.message;
	}

}
