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
    public void playMusic(final Music m) {
        musicController.playMusic(m);
    }
    
    @Override
    public void stopMusic() {
        musicController.stopMusic();
    }
    
    @Override
    public Optional<Music> playing() {
        return musicController.playing();
    }
    
    @Override
    public void save() {
        saveController.setSave(model.getModelSnapshot());
        saveController.save();
    }
    
    @Override
    public void updateStatus(final State s) {
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

    @Override
    public TiledMap getMap() {
        return map;
    }

    @Override
    public PokeMap getPokeMap() {
        return model.getMap();
    }

    @Override
    public Player getPlayer() {
        return model.getPlayer();
    }
    
    @Override
    public Model getModel() {
        return model;
    }
    
    @Override
    public void initializeModel(final TiledMap map) {
        this.map = map;
        this.model = new Model(map);
    }
}