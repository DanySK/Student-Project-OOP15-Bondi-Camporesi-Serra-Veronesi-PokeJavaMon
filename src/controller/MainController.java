package controller;

import controller.keyboard.FightingKeyboardController;
import controller.keyboard.FirstMenuKeyboardController;
import controller.keyboard.KeyboardController;
import controller.keyboard.MenuKeyboardController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.music.MainMusicController;
import controller.parameters.Directions;
import controller.parameters.Music;
import controller.parameters.State;
import model.fight.FightVsTrainer;
import model.pokemon.InitializeMoves;
import view.resources.Play;

public class MainController {
    
    private State state;
    private KeyboardController keyboardController;
    private static MainController SINGLETON; 
    
    private MainController() {}
    
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
                if (MainMusicController.getController().playing() == null) {
                    MainMusicController.getController().play(Music.TOWN);
                } else {
                    if (MainMusicController.getController().playing() != Music.TOWN) {
                        MainMusicController.getController().stop();
                        MainMusicController.getController().play(Music.TOWN);
                    }
                }
                keyboardController = WalkingKeyboardController.getController(); 
                Play.updateKeyListener();
                break;
            case MENU:
                keyboardController = MenuKeyboardController.getController();
                Play.updateKeyListener();
                break;
            case FIGHTING:
                if (MainMusicController.getController().playing() == null) {
                    if (FightController.getController().getFight() instanceof FightVsTrainer) {
                        MainMusicController.getController().play(Music.TRAINER);
                    } else {
                        MainMusicController.getController().play(Music.WILD);
                    }
                } else {
                    if (MainMusicController.getController().playing() != Music.TRAINER || MainMusicController.getController().playing() != Music.WILD) {
                        MainMusicController.getController().stop();
                        if (FightController.getController().getFight() instanceof FightVsTrainer) {
                            MainMusicController.getController().play(Music.TRAINER);
                        } else {
                            MainMusicController.getController().play(Music.WILD);
                        }
                    }
                }
                keyboardController = FightingKeyboardController.getController();
                Play.updateKeyListener();
                break;
            case READING:
                keyboardController = MenuKeyboardController.getController();
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
    
    public void updateSpeed() {
        keyboardController.updateSpeed();
    }
    
    public Directions getDirection() {
        return keyboardController.getDirection();
    }
    
    public void checkEncounter() {
        keyboardController.checkEncounter();
    }
    
    public static void main(String[] args) {
        new Installer().install();
        MainController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
    }
}