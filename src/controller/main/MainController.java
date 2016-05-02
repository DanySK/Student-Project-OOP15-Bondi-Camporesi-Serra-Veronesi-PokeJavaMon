package controller.main;

import controller.fight.FightController;
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
import view.resources.Play;

/**
 * This is the main controller of the game
 */
public final class MainController implements MainControllerInterface {
    
    private State state;
    private KeyboardController keyboardController;
    private static MainControllerInterface singleton; 
    
    /**
     * Private constructor, used by the method getController
     */
    private MainController() {}
    
    /** 
     * @return the curent {@link MainController}, or a new {@link MainController}
     * if this is the first time this method is invoked
     */
    public static MainControllerInterface getController() {
        if (singleton == null) {
            synchronized (MainController.class) {
                if (singleton == null) {
                    singleton = new MainController();
                }
            }
        }
        return singleton;
    }

    @Override
    public void updateStatus(final State s) {
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
            default:
                break;
        }
    }
    
    @Override
    public State getState() {
        return state;
    }
    
    @Override
    public boolean isKeyPressed() {
        return keyboardController.isKeyPressed();
    }

    @Override
    public KeyboardController getCurrentController() {
        return keyboardController;
    }
    
    @Override
    public void updateSpeed() {
        keyboardController.updateSpeed();
    }
    
    @Override
    public Directions getDirection() {
        return keyboardController.getDirection();
    }
    
    @Override
    public void checkEncounter() {
        keyboardController.checkEncounter();
    }
}