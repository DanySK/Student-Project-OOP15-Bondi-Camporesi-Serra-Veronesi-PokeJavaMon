package controller.status;

import java.util.Optional;

import controller.Controller;
import controller.keyboard.FightingKeyboardController;
import controller.keyboard.FirstMenuKeyboardController;
import controller.keyboard.KeyboardController;
import controller.keyboard.MenuKeyboardController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.parameters.Music;
import controller.parameters.State;
import model.fight.FightVsTrainer;
import model.map.Drawable.Direction;
import model.player.PlayerImpl;
import model.map.WalkableZone;
import view.resources.Play;

/**
 * This is the status controller of the game
 */
public class StatusController implements StatusControllerInterface {
    
    private State state;
    private KeyboardController keyboardController;

    @Override
    public void updateStatus(final State s) {
        state = s;
        switch (s) {
            case FIRST_MENU:
                keyboardController = new FirstMenuKeyboardController();
                break;
            case SECOND_MENU:
                keyboardController = new SecondMenuKeyboardController();
                break;
            case WALKING:
                if (Controller.getController().playing().isPresent()) {
                    updateMusic();
                }
                keyboardController = new WalkingKeyboardController(); 
                Play.updateKeyListener();
                break;
            case MENU:
                keyboardController = new MenuKeyboardController();
                Play.updateKeyListener();
                break;
            case FIGHTING:
                if (Controller.getController().playing().isPresent()) {
                    if (Controller.getController().playing().get() != Music.TRAINER || Controller.getController().playing().get() != Music.WILD) {
                        Controller.getController().stopMusic();
                        if (Controller.getController().getFight() instanceof FightVsTrainer) {
                            Controller.getController().playMusic(Music.TRAINER);
                        } else {
                            Controller.getController().playMusic(Music.WILD);
                        }
                    }
                } else {
                    if (Controller.getController().getFight() instanceof FightVsTrainer) {
                        Controller.getController().playMusic(Music.TRAINER);
                    } else {
                        Controller.getController().playMusic(Music.WILD);
                    }
                }
                keyboardController = new FightingKeyboardController();
                Play.updateKeyListener();
                break;
            case READING:
                keyboardController = new MenuKeyboardController();
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
    public Direction getDirection() {
        return keyboardController.getDirection();
    }
    
    @Override
    public void checkEncounter() {
        keyboardController.checkEncounter();
    }

    @Override
    public void updateMusic() {
        final Optional<WalkableZone> zone = Play.getMapImpl().getWalkableZone(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY());
        if (zone.isPresent()) {
            for (final Music m : Music.values()) {
                if (m.getAbsolutePath().equals(zone.get().getMusicPath()) && Controller.getController().getStatusController().getState() != State.FIGHTING) {
                    if (Controller.getController().playing().isPresent()) {
                        if (Controller.getController().playing().get() != m) {
                            Controller.getController().stopMusic();
                            Controller.getController().playMusic(m);
                        }
                    } else {
                        Controller.getController().playMusic(m);
                    }               
                }
            }
        }
    }
}