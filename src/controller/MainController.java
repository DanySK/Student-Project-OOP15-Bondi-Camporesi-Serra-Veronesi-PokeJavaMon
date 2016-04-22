package controller;

import controller.keyboard.FightingKeyboardController;
import controller.keyboard.FirstMenuKeyboardController;
import controller.keyboard.KeyboardController;
import controller.keyboard.MenuKeyboardController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.parameters.State;
import model.pokemon.InitializeMoves;
import model.resources.Player;
import view.resources.Play;

public class MainController {
    
    private State state;
    private KeyboardController keyboardController;
    private static MainController SINGLETON; 
    
    public static MainController getController() {
        if (SINGLETON == null) {
            synchronized (MainController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new MainController();
                }
            }
        }
        return SINGLETON;
    }

    public void updateStatus(State s) {
        state = s;
        switch (s) {
            case FIRST_MENU:
                keyboardController = FirstMenuKeyboardController.getController();
                break;
            case SECOND_MENU:
                keyboardController = SecondMenuKeyboardController.getController();
                break;
            case WALKING:
                keyboardController = WalkingKeyboardController.getController(); 
                Player.resetPos();
                Play.updateKeyListener();
                break;
            case MENU:
                keyboardController = MenuKeyboardController.getController();
                Player.resetPos();
                Play.updateKeyListener();
                break;
            case FIGHTING:
                keyboardController = FightingKeyboardController.getController();
                Player.resetPos();
                Play.updateKeyListener();
                break;
        }
    }
    
    public State getState() {
        return state;
    }
    
    public boolean isKeyPressed() {
        return keyboardController.isKeyPressed();
    }

    public KeyboardController getCurrentController() {
        return keyboardController;
    }
    
    public static void main(String[] args) {
        MainController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
    }
}