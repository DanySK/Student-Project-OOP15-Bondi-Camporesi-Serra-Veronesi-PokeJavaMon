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
    boolean isKeyPressed();
    
    /**
     * Updates the speed of the {@link PlayerSprite}
     */
    void updateSpeed();
    
    /**
     * @return the {@link Directions} selected by the last key pressed
     */
    Directions getDirection();

    /**
     * Checks if player has encountered a wild {@link Pokemon}
     */
    void checkEncounter();
}