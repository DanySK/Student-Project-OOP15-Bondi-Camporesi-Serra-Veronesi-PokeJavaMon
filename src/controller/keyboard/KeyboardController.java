package controller.keyboard;

import com.badlogic.gdx.InputProcessor;

import controller.parameters.Directions;

public interface KeyboardController extends InputProcessor {
    
    public boolean isKeyPressed();
    
    public void updateSpeed();
    
    public Directions getDirection();

    public void checkEncounter();
}
