package controller;

import controller.keyboard.FightingKeyboardController;
import controller.keyboard.FirstMenuKeyboardController;
import controller.keyboard.KeyboardController;
import controller.keyboard.MenuKeyboardController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.music.MainMusicController;
import controller.parameters.Music;
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
                if (MainMusicController.getController().playing() == null) {
                    MainMusicController.getController().play(Music.TOWN);
                } else {
                    if (MainMusicController.getController().playing() != Music.TOWN) {
                        MainMusicController.getController().stop();
                        MainMusicController.getController().play(Music.TOWN);
                    }
                }
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
                if (MainMusicController.getController().playing() == null) {
                    MainMusicController.getController().play(Music.TRAINER);
                } else {
                    if (MainMusicController.getController().playing() != Music.TRAINER) {
                        MainMusicController.getController().stop();
                        MainMusicController.getController().play(Music.TRAINER);
                    }
                }
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
        new Installer().install();
        MainController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
    }
}