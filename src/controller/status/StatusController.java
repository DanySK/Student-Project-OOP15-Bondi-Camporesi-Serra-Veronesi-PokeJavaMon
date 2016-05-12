package controller.status;

import controller.keyboard.KeyboardController;
import controller.parameters.State;
import model.map.Drawable.Direction;
import model.pokemon.Pokemon;
import view.sprite.PlayerSprite;

/**
 * This interface shows the methods that can be called on {@link MainStatusController}
 */
public interface StatusController {

    /**
     * Updates the {@link State} of the game
     * @param s the new {@link State}
     */
    void updateStatus(State s);

    /**
     * @return the current {@link State}
     */
    State getState();

    /**
     * @return true if at least one key is pressed in the current controller, false otherwise
     */
    boolean isKeyPressed();

    /**
     * @return the current {@link KeyboardController}
     */
    KeyboardController getCurrentController();

    /**
     * Updates the speed of {@link PlayerSprite}
     */
    void updateSpeed();

    /**
     * @return the current {@link Directions} of the player
     */
    Direction getDirection();

    /**
     * Controls if the player has encountered a wild {@link Pokemon}
     */
    void checkEncounter();
    
    /**
     * Updates game's music
     */
    void updateMusic();
}