package controller.status;

import java.util.Optional;

import controller.MainController;
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
import model.map.WalkableZone;
import view.resources.MainGameView;

/**
 * This is the status controller of the game
 */
public class MainStatusController implements StatusController {
    
    private State state;
    private KeyboardController keyboardController;

    @Override
    public void updateStatus(final State s) {
        switch (s) {
            case FIRST_MENU:
                this.state = s;
                this.keyboardController = new FirstMenuKeyboardController();
                break;
            case SECOND_MENU:
                this.state = s;
                this.keyboardController = new SecondMenuKeyboardController();
                break;
            case WALKING:
                this.state = s;
                if (MainController.getController().playing().isPresent()) {
                    updateMusic();
                }
                this.keyboardController = new WalkingKeyboardController(); 
                MainGameView.updateKeyListener();
                break;
            case MENU:
                this.state = s;
                this.keyboardController = new MenuKeyboardController();
                MainGameView.updateKeyListener();
                break;
            case FIGHTING:
                if (this.state != State.FIGHTING) {
                    this.state = s;
                    if (!MainController.getController().isPaused()) {
                        if (MainController.getController().playing().isPresent()) {
                            if (MainController.getController().playing().get() != Music.TRAINER || MainController.getController().playing().get() != Music.WILD) {
                                MainController.getController().stopMusic();
                                if (MainController.getController().getFight().get() instanceof FightVsTrainer) {
                                    MainController.getController().playMusic(Music.TRAINER);
                                } else {
                                    MainController.getController().playMusic(Music.WILD);
                                }
                            }
                        } else {
                            if (MainController.getController().getFight().get() instanceof FightVsTrainer) {
                                MainController.getController().playMusic(Music.TRAINER);
                            } else {
                                MainController.getController().playMusic(Music.WILD);
                            }
                        }
                    }
                    this.keyboardController = new FightingKeyboardController();
                    MainGameView.updateKeyListener();
                }
                break;
            case READING:
                this.state = s;
                this.keyboardController = new MenuKeyboardController();
                MainGameView.updateKeyListener();
                break;
            default:
                break;
        }
    }
    
    @Override
    public State getState() {
        return this.state;
    }
    
    @Override
    public boolean isKeyPressed() {
        return this.keyboardController.isKeyPressed();
    }

    @Override
    public KeyboardController getCurrentController() {
        return this.keyboardController;
    }
    
    @Override
    public void updateSpeed() {
        this.keyboardController.updateSpeed();
    }
    
    @Override
    public Direction getDirection() {
        return this.keyboardController.getDirection();
    }
    
    @Override
    public void checkEncounter() {
        this.keyboardController.checkEncounter();
    }

    @Override
    public void updateMusic() {
        final Optional<WalkableZone> zone = MainController.getController().getPokeMap().get().getWalkableZone(MainController.getController().getPlayer().get().getTileX(), 
                MainController.getController().getPlayer().get().getTileY());
        if (!MainController.getController().isPaused())
            if (zone.isPresent())
                for (final Music m : Music.values())
                    if (m.getAbsolutePath().equals(zone.get().getMusicPath()) && MainController.getController().getStatusController().getState() != State.FIGHTING)
                        if (MainController.getController().playing().isPresent()) {
                            if (MainController.getController().playing().get() != m) {
                                MainController.getController().stopMusic();
                                MainController.getController().playMusic(m);
                            }
                        } else {
                            MainController.getController().playMusic(m);
                        }               
    }
}