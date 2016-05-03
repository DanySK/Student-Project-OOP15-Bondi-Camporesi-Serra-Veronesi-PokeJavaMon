package controller;

import controller.fight.FightController;
import controller.load.LoadController;
import controller.music.MainMusicController;
import controller.music.MusicController;
import controller.parameters.Music;
import controller.parameters.State;
import controller.save.SaveController;
import controller.status.StatusController;
import controller.view.ViewController;
import model.fight.Fight;
import model.pokemon.Pokemon;

public class Controller implements ControllerInterface {

    private FightController fightController;
    private LoadController loadController;
    private MusicController musicController;
    private SaveController saveController;
    private StatusController statusController;
    private ViewController viewController;
    private static ControllerInterface singleton;
    
    private Controller() {}
    
    public static ControllerInterface getController() {
        if (singleton == null) {
            synchronized (Controller.class) {
                if (singleton == null) {
                    singleton = new Controller();
                }
            }
        }
        return singleton;
    }
    
    @Override
    public void initializeMainController() {
        this.fightController = new FightController();
        this.loadController = new LoadController();
        this.musicController = new MainMusicController();
        this.saveController = new SaveController();
        this.statusController = new StatusController();
        this.viewController = new ViewController();
    }
    
    @Override
    public Fight getFight() {
        return fightController.getFight();
    }
    
    @Override
    public Pokemon getEnemyPokemonInFight() {
        return fightController.getEnemyPokemon();
    }
    
    @Override
    public void load() {
        loadController.load();
    }
    
    @Override
    public boolean saveExists() {
        return loadController.saveExists();
    }
    
    @Override
    public void initializeMusicController() {
        musicController.initializeMusicController();
    }
    
    @Override
    public void playMusic(Music m) {
        musicController.playMusic(m);
    }
    
    @Override
    public void stopMusic() {
        musicController.stopMusic();
    }
    
    @Override
    public Music playing() {
        return musicController.playing();
    }
    
    @Override
    public void save() {
        saveController.save();
    }
    
    @Override
    public void updateStatus(State s) {
        statusController.updateStatus(s);
    }

    @Override
    public FightController getFightController() {
        return fightController;
    }

    @Override
    public LoadController getLoadController() {
        return loadController;
    }

    @Override
    public MusicController getMusicController() {
        return musicController;
    }

    @Override
    public SaveController getSaveController() {
        return saveController;
    }

    @Override
    public StatusController getStatusController() {
        return statusController;
    }

    @Override
    public ViewController getViewController() {
        return viewController;
    }
}
