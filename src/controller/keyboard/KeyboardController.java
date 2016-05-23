package controller.keyboard;

import com.badlogic.gdx.InputProcessor;
import model.map.Drawable.Direction;
import model.pokemon.Pokemon;
import view.sprite.PlayerSprite;

/**
 * This interface explains the methods each {@link KeyboardController} has to implement
 * to operate on user input.
 * This interface extends {@link InputProcessor}
 */
public interface KeyboardController extends InputProcessor {
    
    /**
     * @return true if at least one key is pressed, false otherwise 
     */
    boolean isKeyPressed();
    
    /**
     * Updates the speed of the {@link PlayerSprite}
     */
    void updateSpeed();
    
    /**
     * @return the {@link Direction} selected by the last key pressed
     */
    Direction getDirection();

    /**
     * Checks if player has encountered a wild {@link Pokemon}
     */
    void checkEncounter();
}