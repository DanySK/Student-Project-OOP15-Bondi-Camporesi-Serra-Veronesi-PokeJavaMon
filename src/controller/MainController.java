package controller;

import java.util.Optional;

import controller.keyboard.KeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.music.MusicController;
import controller.music.MainMusicController;
import controller.parameters.Music;
import controller.parameters.State;
import model.pokemon.InitializeMoves;
import model.resources.Player;
import view.resources.Play;
import view.resources.ViewController;

/**
 * 
 */
public class MainController {
    
    private static State state;
    private static Optional<KeyboardController> keyboardController;
    private static Optional<MusicController> musicController;
    
    public static void updateStatus(State s) {
        state = s;
        switch (s) {
            case FIRST_MENU:
                keyboardController = Optional.empty();
                musicController = Optional.empty();
                break;
            case SECOND_MENU:
                keyboardController = Optional.empty();
                musicController = Optional.empty();
                break;
            case WALKING:
                keyboardController = Optional.of(new WalkingKeyboardController());
                Play.updateKeyListener();
                if (musicController.isPresent()) {
                    if (musicController.get().playing() != Music.TOWN) {
                        musicController.get().stop();
                        musicController.get().play(Music.TOWN);
                    }
                } else {
                    musicController = Optional.of(new MainMusicController());
                    musicController.get().play(Music.TOWN);
                }
                break;
            case MENU:
                keyboardController = Optional.empty();
                Player.resetPos();
                break;
            case FIGHTING:
                keyboardController = Optional.empty();
                Player.resetPos();
                if (musicController.isPresent()) {
                    if (musicController.get().playing() != Music.TRAINER) {
                        musicController.get().stop();
                        musicController.get().play(Music.TRAINER);
                    }
                } else {
                    musicController = Optional.of(new MainMusicController());
                    musicController.get().play(Music.TRAINER);
                }
                break;
        }
    }
    
    public static boolean isKeyPressed() {
        if (keyboardController.isPresent()) {
        	return keyboardController.get().isKeyPressed();
        }
        	return false;
    }
    
    public static void main(String[] args) {
        InitializeMoves.initAllPokemonsTypes();
        ViewController.firstMenu();
    }
    
    public static KeyboardController getController() {
        return keyboardController.get();
    }
}