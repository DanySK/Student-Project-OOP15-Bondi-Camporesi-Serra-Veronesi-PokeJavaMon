package controller;

import java.util.Optional;

import com.badlogic.gdx.maps.tiled.TiledMap;
import controller.fight.MainFightController;
import controller.fight.FightController;
import controller.load.MainLoadController;
import controller.load.LoadController;
import controller.music.MainMusicController;
import controller.music.MusicController;
import controller.parameters.Music;
import controller.parameters.State;
import controller.save.MainSaveController;
import controller.save.SaveController;
import controller.status.MainStatusController;
import controller.status.StatusController;
import controller.view.MainViewController;
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
public final class MainController implements Controller {

    private FightController fightController;
    private LoadController loadController;
    private MusicController musicController;
    private SaveController saveController;
    private StatusController statusController;
    private ViewController viewController;
    private TiledMap map;
    private Model model;
    private static Controller singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private MainController() {
        this.fightController = new MainFightController();
        this.loadController = new MainLoadController();
        this.musicController = new MainMusicController();
        this.saveController = new MainSaveController();
        this.statusController = new MainStatusController();
        this.viewController = new MainViewController();
    }
    
    /**
     * @return the current {@link MainController} ora a new {@link MainController} if this is the first time this method is invoked
     */
    public static Controller getController() {
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