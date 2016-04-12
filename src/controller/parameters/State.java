package controller.parameters;

import controller.keyboard.*;

public enum State {

    TITLE(null),
    WALKING(new WalkingController()),  
    FIGHTING(null),
    MENU(null);
    
    private State(KeyboardControllerInterface c) {
        this.controller = c;
    }
    
    private KeyboardControllerInterface controller;
    
    public KeyboardControllerInterface getController() {
        return controller;
    }
}
