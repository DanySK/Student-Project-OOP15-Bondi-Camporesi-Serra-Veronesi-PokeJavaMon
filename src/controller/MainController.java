package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;

import controller.fight.FightController;
import controller.fight.MainFightController;
import controller.load.LoadController;
import controller.load.MainLoadController;
import controller.music.MainMusicController;
import controller.music.MusicController;
import controller.parameters.MusicPath;
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
import model.ModelInterface;
import model.fight.Fight;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Pokeball.PokeballType;
import model.items.Potion;
import model.items.Potion.PotionType;
import model.map.PokeMap;
import model.map.Position;
import model.map.tile.Tile.TileType;
import model.player.Box;
import model.player.Inventory;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.squad.Squad;
import view.sprite.PlayerSprite;

/**
 * This is the main controller of the game. It contains all the other controllers.
 * It implements the singleton programmation pattern
 */
public final class MainController implements Controller {

    private static final int DEFAULT_LVL = 5;
    private static final int DEFAULT_QUANTITY = 10;
    private static final int NO_ITEM = 0;
    private static final int NO_SPEED = 0;
    private final FightController fightController;
    private final LoadController loadController;
    private final MusicController musicController;
    private final SaveController saveController;
    private final StatusController statusController;
    private final ViewController viewController;
    private TiledMap map;
    private ModelInterface model;
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
    public Optional<Fight> getFight() {
        return this.fightController.getFight();
    }
    
    @Override
    public Optional<Pokemon> getEnemyPokemonInFight() {
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
    public void playMusic(final MusicPath m) {
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
    public Optional<MusicPath> playing() {
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
    public Optional<TiledMap> getMap() {
        if (this.map == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.map);
        }
    }

    @Override
    public Optional<PokeMap> getPokeMap() {
        if (modelNotInitialized()) {
            return Optional.empty();
        } else {
            return Optional.of(this.model.getMap());
        }
    }

    /**
     * @return true if {@link Model} is initialized, false otherwise
     */
    private boolean modelNotInitialized() {
        return this.model == null;
    }

    @Override
    public Optional<Player> getPlayer() {
        if(modelNotInitialized()) {
            return Optional.empty();
        } else {
            return Optional.of(this.model.getPlayer());
        }
    }
    
    @Override
    public Optional<Inventory> getInventory() {
        if (modelNotInitialized()) {
            return Optional.empty();
        } else {
            return Optional.of(this.model.getPlayer().getInventory());
        }
    }
    
    @Override
    public void initializeModel(final TiledMap map) {
        this.map = map;
        this.model = new Model(map);
    }

    @Override
    public void effectItem(final Item i, final Pokemon p) throws PokemonNotFoundException {
        if (!modelNotInitialized()) {
            if (i.getType() == ItemType.POTION) {
                ((Potion) i).effect(this.model.getPlayer(), (PokemonInBattle) p);
                this.model.getPlayer().getInventory().consumeItem(i);
            }
        }
    }

    @Override
    public Optional<Box> getBox() {
        if (modelNotInitialized()) {
            return Optional.empty();
        } else {
            return Optional.of(this.model.getPlayer().getBox());
        }
    }

    @Override
    public void withdrawPokemon(final Pokemon p) throws PokemonNotFoundException, SquadFullException {
        if (!modelNotInitialized()) {
            this.model.getPlayer().getBox().withdrawPokemon(p, this.model.getPlayer().getSquad());
        }
    }

    @Override
    public void learnMove(final Pokemon p, final Move oldMove, final Move newMove) {
        p.learnMove(oldMove, newMove);
    }
    
    @Override
    public void buyItem(final Item i) throws NotEnoughMoneyException {
        if (!modelNotInitialized()) {
            this.model.getPlayer().buyItem(i);
        }
    }

    @Override
    public Optional<Squad> getSquad() {
        if (modelNotInitialized()) {
            return Optional.empty();
        } else {
            return Optional.of(this.model.getPlayer().getSquad());
        }
    }
    
    @Override
    public void switchPokemon(final int index1, final int index2) {
        if (!modelNotInitialized()) {
            this.model.getPlayer().getSquad().switchPokemon(index1, index2);
        }
    }

    @Override
    public void depositPokemon(final Pokemon p) throws PokemonNotFoundException, OnlyOnePokemonInSquadException {
        if (!modelNotInitialized()) {
            this.model.getPlayer().getBox().depositPokemon(p, this.model.getPlayer().getSquad());
        }
    }
    
    @Override
    public void teleportToCenter() {
        if (!modelNotInitialized()) {
            final int x = this.model.getMap().getPokemonCenterSpawnPosition().getX();
            final int y = this.model.getMap().getPokemonCenterSpawnPosition().getY();
            PlayerSprite.getSprite().setPlayerPosition(x, y);
            PlayerSprite.getSprite().setVelocity(NO_SPEED, NO_SPEED);
            this.model.getPlayer().setPosition(x, y);
            this.model.getPlayer().getSquad().healAllPokemon(this.model.getMap());
        }
    }

    @Override
    public void initInventory() {
        if (!modelNotInitialized()) {
            Map<String, Integer> potionList = new HashMap<>();
            Map<String, Integer> boostList = new HashMap<>();
            Map<String, Integer> ballList = new HashMap<>();
            potionList.put(PotionType.Potion.name(), DEFAULT_QUANTITY);
            potionList.put(PotionType.Superpotion.name(), NO_ITEM);
            potionList.put(PotionType.Hyperpotion.name(), NO_ITEM);
            boostList.put(Stat.SPD.name() + "X", NO_ITEM);
            boostList.put(Stat.DEF.name() + "X", NO_ITEM);
            boostList.put(Stat.ATK.name() + "X", NO_ITEM);
            ballList.put(PokeballType.Greatball.name(), NO_ITEM);
            ballList.put(PokeballType.Ultraball.name(), NO_ITEM);
            ballList.put(PokeballType.Pokeball.name(), DEFAULT_QUANTITY);     
            this.model.getPlayer().getInventory().initializeInventory(potionList, boostList, ballList);
        }
    }

    @Override
    public Position getInitialPosition() {
        Position retPosition = new Position(PlayerImpl.START_X, PlayerImpl.START_Y);
        if (!modelNotInitialized()) {
            retPosition = this.model.getPlayer().getPosition();
        } 
        return retPosition;
    }
    
    @Override
    public Position getDefaultInitialPosition() {
        return new Position(PlayerImpl.DEFAULT_START_X, PlayerImpl.DEFAULT_START_Y);
    }

    @Override
    public void addPokemonToSquad(final Pokedex p) throws SquadFullException {
        if (!modelNotInitialized()) {
            this.model.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(p, DEFAULT_LVL));
        }
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
        if (!modelNotInitialized()) {
            final PokeMap map = this.model.getMap();
            if (map.getTileNextToPlayer(PlayerImpl.getPlayer().getDirection()) == TileType.ENCOUNTER) {
            	int offsetX = 0;
            	int offsetY = 0; 
            	switch (PlayerImpl.getPlayer().getDirection()) {
            	case NORTH :
            		offsetY = -1;
            		break;
            	case SOUTH :
            		offsetY = 1;
            		break;
            	case WEST :
            		offsetX = -1;
            		break;
            	case EAST :
            		offsetX = 1;
            		break;
            	default :
            		throw new IllegalStateException("Player position is " + PlayerImpl.getPlayer().getDirection());
            		
            	}
                map.deleteEncounterTile(this.model.getPlayer().getTileX() + offsetX, this.model.getPlayer().getTileY() + offsetY);
            }
        }
    }

    @Override
    public void loadSave(final int money, final String name, final int badges, final Position position, final List<Pokemon> team,
            final Map<Integer, Boolean> trainers, final List<Pokemon> box, final Map<String, Integer> pokeballs,
            final Map<String, Integer> boosts, final Map<String, Integer> potions, final Set<String> defeatedEncounterTiles)
                    throws SquadFullException {
        if (!modelNotInitialized()) {
            this.model.loadSave(money, name, badges, position, team, trainers, box, pokeballs, boosts, potions, defeatedEncounterTiles);
        }
    }
}