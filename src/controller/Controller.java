package controller;

import java.util.Optional;

import com.badlogic.gdx.maps.tiled.TiledMap;
import controller.fight.FightController;
import controller.load.LoadController;
import controller.music.MainMusicController;
import controller.music.MusicController;
import controller.parameters.Music;
import controller.parameters.State;
import controller.save.SaveController;
import controller.status.StatusController;
import controller.view.ViewController;
import model.Model;
import model.fight.Fight;
import model.map.PokeMap;
import model.player.Player;
import model.pokemon.Pokemon;

/**
 * This is the main controller of the game. It contains all the other controllers.
 * It implements the singleton programmation pattern
 */
public final class Controller implements ControllerInterface {

    private FightController fightController;
    private LoadController loadController;
    private MusicController musicController;
    private SaveController saveController;
    private StatusController statusController;
    private ViewController viewController;
    private TiledMap map;
    private Model model;
    private static ControllerInterface singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private Controller() {
        this.fightController = new FightController();
        this.loadController = new LoadController();
        this.musicController = new MainMusicController();
        this.saveController = new SaveController();
        this.statusController = new StatusController();
        this.viewController = new ViewController();
    }
    
    /**
     * @return the current {@link Controller} ora a new {@link Controller} if this is the first time this method is invoked
     */
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
    public Fight getFight() {
        return this.fightController.getFight();
    }
    
    @Override
    public Pokemon getEnemyPokemonInFight() {
        return this.fightController.getEnemyPokemon();
    }
    
    @Override
    public void load() {
        this.loadController.load();
    }
    
    @Override
    public boolean saveExists() {
        return this.loadController.saveExists();
    }
    
    @Override
    public void initializeMusicController() {
        this.musicController.initializeMusicController();
    }
    
    @Override
    public void playMusic(final Music m) {
        this.musicController.playMusic(m);
    }
    
    @Override
    public void stopMusic() {
        this.musicController.stopMusic();
    }
    
    @Override
    public void pause() {
        this.musicController.pause();
    }

    @Override
    public void resume() {
        this.musicController.resume();
    }

    @Override
    public boolean isPaused() {
        return this.musicController.isPaused();
    }
    
    @Override
    public Optional<Music> playing() {
        return this.musicController.playing();
    }
    
    @Override
    public void save() {
        this.saveController.setSave(this.model.getModelSnapshot());
        this.saveController.save();
    }
    
    @Override
    public void updateStatus(final State s) {
        this.statusController.updateStatus(s);
    }

    @Override
    public FightController getFightController() {
        return this.fightController;
    }

    @Override
    public LoadController getLoadController() {
        return this.loadController;
    }

    @Override
    public MusicController getMusicController() {
        return this.musicController;
    }

    @Override
    public SaveController getSaveController() {
        return this.saveController;
    }

    @Override
    public StatusController getStatusController() {
        return this.statusController;
    }

    @Override
    public ViewController getViewController() {
        return this.viewController;
    }

    @Override
    public TiledMap getMap() {
        return this.map;
    }

    @Override
    public PokeMap getPokeMap() {
        return this.model.getMap();
    }

    @Override
    public Player getPlayer() {
        return this.model.getPlayer();
    }
    
    @Override
    public Model getModel() {
        return this.model;
    }
    
    @Override
    public void initializeModel(final TiledMap map) {
        this.map = map;
        this.model = new Model(map);
    }
}