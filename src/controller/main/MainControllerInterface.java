package controller.main;

import controller.keyboard.KeyboardController;
import controller.parameters.Directions;
import controller.parameters.State;

public interface MainControllerInterface {

    public void updateStatus(State s);

    public State getState();

    public boolean isKeyPressed();

    public KeyboardController getCurrentController();

    public void updateSpeed();

    public Directions getDirection();

    public void checkEncounter();
}