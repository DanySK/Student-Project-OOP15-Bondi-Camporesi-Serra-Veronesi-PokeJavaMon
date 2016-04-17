package controller;

import java.awt.event.KeyListener;

import controller.keyboard.FirstMenuKeyboardController;
import controller.keyboard.KeyboardController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.music.MusicController;
import controller.music.MainMusicController;
import controller.parameters.Music;
import controller.parameters.State;
import model.pokemon.InitializeMoves;
import model.resources.Player;
import view.resources.Play;
import view.resources.ViewController;

public class MainController {
    
    private static State state;
    private static KeyboardController keyboardController;
    private static MusicController musicController;

    public static void updateStatus(State s) {
        state = s;
        switch (s) {
            case FIRST_MENU:
                keyboardController = new FirstMenuKeyboardController();
                musicController = null;
                break;
            case SECOND_MENU:
                keyboardController = new SecondMenuKeyboardController();
                musicController = null;
                break;
            case WALKING:
                keyboardController = new WalkingKeyboardController(); 
                Play.updateKeyListener();
                if (musicController != null) {
                    if (musicController.playing() != Music.TOWN) {
                        musicController.stop();
                        musicController.play(Music.TOWN);
                    }
                } else {
                    musicController = new MainMusicController();
                    musicController.play(Music.TOWN);
                }
                break;
            case MENU:
                keyboardController = null;
                Player.resetPos();
                break;
            case FIGHTING:
                keyboardController = null;
                Player.resetPos();
                if (musicController != null) {
                    if (musicController.playing() != Music.TRAINER) {
                        musicController.stop();
                        musicController.play(Music.TRAINER);
                    }
                } else {
                    musicController = new MainMusicController();
                    musicController.play(Music.TRAINER);
                }
                break;
        }
    }
    
    public static boolean isKeyPressed() {
        return keyboardController.isKeyPressed();
    }
    
    public static void main(String[] args) {
        updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.firstMenu((KeyListener)keyboardController);
    }
    
    public static KeyboardController getController() {
        return keyboardController;
    }
}