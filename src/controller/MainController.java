package controller;

import java.util.HashMap;
import java.util.Map;
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
import exceptions.NotEnoughMoneyException;
import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.Model;
import model.box.Box;
import model.fight.Fight;
import model.inventory.Inventory;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Pokeball.PokeballType;
import model.items.Potion;
import model.items.Potion.PotionType;
import model.map.Drawable.Direction;
import model.map.PokeMap;
import model.map.tile.Tile.TileType;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.squad.Squad;
import model.utilities.Pair;
import view.sprite.PlayerSprite;

/**
 * This is the main controller of the game. It contains all the other controllers.
 * It implements the singleton programmation pattern
 */
public final class MainController implements Controller {

    private static final int OFFSET = 1;
    private static final int DEFAULT_LVL = 45;
    private static final int DEFAULT_QUANTITY = 10;
    private static final int NO_ITEM = 0;
    private static final int NO_SPEED = 0;
    private FightController fightController;
    private LoadController loadController;
    private MusicController musicController;
    private SaveController saveController;
    private StatusController statusController;
    private ViewController viewController;
    private TiledMap map;
    private Model model;
    private Pokedex starter;
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
    public Inventory getInventory() {
        return this.model.getPlayer().getInventory();
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

    @Override
    public void effectItem(final Item i, final Pokemon p) throws PokemonNotFoundException {
        if (i.getType() == ItemType.POTION) {
            ((Potion) i).effect(this.model.getPlayer(), (PokemonInBattle) p);
            this.model.getPlayer().getInventory().consumeItem(i);
        }
    }

    @Override
    public Box getBox() {
        return this.model.getPlayer().getBox();
    }

    @Override
    public void withdrawPokemon(final Pokemon p) throws PokemonNotFoundException, SquadFullException {
        this.model.getPlayer().getBox().withdrawPokemon(p, this.model.getPlayer().getSquad());
    }

    @Override
    public void learnMove(final Pokemon p, final Move oldMove, final Move newMove) {
        p.learnMove(oldMove, newMove);
    }
    
    @Override
    public void buyItem(final Item i) throws NotEnoughMoneyException {
        this.model.getPlayer().buyItem(i);
    }

    @Override
    public Squad getSquad() {
        return this.model.getPlayer().getSquad();
    }
    
    @Override
    public void switchPokemon(final int index1, final int index2) {
        this.model.getPlayer().getSquad().switchPokemon(index1, index2);
    }

    @Override
    public void depositPokemon(final Pokemon p) throws PokemonNotFoundException, OnlyOnePokemonInSquadException {
        this.model.getPlayer().getBox().depositPokemon(p, this.model.getPlayer().getSquad());
    }
    
    @Override
    public void teleportToCenter() {
        final int x = MainController.getController().getPokeMap().getPokemonCenterSpawnPosition().getX();
        final int y = MainController.getController().getPokeMap().getPokemonCenterSpawnPosition().getY();
        PlayerSprite.getSprite().setPlayerPosition(x, y);
        PlayerSprite.getSprite().setVelocity(NO_SPEED, NO_SPEED);
        this.model.getPlayer().setPosition(x, y);
        this.model.getPlayer().getSquad().healAllPokemon(MainController.getController().getPokeMap());
    }

    @Override
    public void initInventory() {
        Map<String, Integer> potionList = new HashMap<>();
        Map<String, Integer> boostList = new HashMap<>();
        Map<String, Integer> ballList = new HashMap<>();
        potionList.put(PotionType.POTION.name(), DEFAULT_QUANTITY);
        potionList.put(PotionType.SUPERPOTION.name(), NO_ITEM);
        potionList.put(PotionType.HYPERPOTION.name(), NO_ITEM);
        boostList.put(Stat.SPD.name() + "X", NO_ITEM);
        boostList.put(Stat.DEF.name() + "X", NO_ITEM);
        boostList.put(Stat.ATK.name() + "X", NO_ITEM);
        ballList.put(PokeballType.Greatball.name(), NO_ITEM);
        ballList.put(PokeballType.Ultraball.name(), NO_ITEM);
        ballList.put(PokeballType.Pokeball.name(), DEFAULT_QUANTITY);     
        this.model.getPlayer().getInventory().initializeInventory(potionList, boostList, ballList);
    }

    @Override
    public Pair<Integer, Integer> getInitialPosition() {
        return new Pair<Integer, Integer>(PlayerImpl.START_X, PlayerImpl.START_Y);
    }
    
    @Override
    public Pair<Integer, Integer> getDefaultInitialPosition() {
        return new Pair<Integer, Integer>(PlayerImpl.DEFAULT_START_X, PlayerImpl.DEFAULT_START_Y);
    }

    @Override
    public void addPokemonToSquad(final Pokedex p) throws SquadFullException {
        this.model.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(p, DEFAULT_LVL));
    }

    @Override
    public void selectStarter(final Pokedex p) {
        this.starter = p;
    }

    @Override
    public void initializeStarter() throws SquadFullException {
        addPokemonToSquad(starter);
    }
    
    @Override
    public void checkLegendaryAndDelete() {
        final PokeMap map = this.model.getMap();
        if (map.getTileNextToPlayer(Direction.NORTH) == TileType.ENCOUNTER) {
            map.deleteEncounterTile(this.model.getPlayer().getTileX(), this.model.getPlayer().getTileY() - OFFSET);
        }
    }
}