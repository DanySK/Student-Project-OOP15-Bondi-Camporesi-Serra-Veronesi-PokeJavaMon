package controller.keyboard;

import com.badlogic.gdx.InputProcessor;

import controller.parameters.Directions;
import model.pokemon.Pokemon;
import view.sprite.PlayerSprite;

/**
 * This interface explains the methods each {@link KeyboardController} has to implement
 * to operate on user imput
 */
public interface KeyboardController extends InputProcessor {
    
    /**
     * @return true if at least one key is pressed, false otherwise 
     */
    public boolean isKeyPressed();
    
    /**
     * Updates the speed of the {@link PlayerSprite}
     */
    public void updateSpeed();
    
    /**
     * @return the {@link Directions} selected by the last key pressed
     */
    public Directions getDirection();

    /**
     * Checks if player has encountered a wild {@link Pokemon}
     */
    public void checkEncounter();
}
