package controller.view;

import java.util.List;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.fight.FightController;
import controller.main.MainController;
import controller.parameters.State;
import controller.save.SaveController;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.Pokedex;
import view.frames.*;
import view.resources.TiledMapGame;
import view.resources.TitleWiew;
import view.sprite.PlayerSprite;

/**
 * This class controls the menus from the view
 */
public final class ViewController implements ViewControllerInterface {
    private String name;
    private static ViewControllerInterface singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private ViewController() {}
    
    /** 
     * @return the curent {@link ViewController}, or a new {@link ViewController}
     * if this is the first time this method is invoked
     */
    public static ViewControllerInterface getController() {
        if (singleton == null) {
            synchronized (ViewController.class) {
                if (singleton == null) {
                    singleton = new ViewController();
                }
            }
        }
        return singleton;
    }
    
    @Override
    public void market() {
        new Market();
    }
    
    @Override
    public void showMenu() {
        MainController.getController().updateStatus(State.MENU);
        new Menu();
    }
    
    @Override
    public void firstMenu() {
        MainController.getController().updateStatus(State.FIRST_MENU);
        new TitleWiew().title();
    }
    
    @Override
    public void secondMenu() {
        MainController.getController().updateStatus(State.SECOND_MENU);
        new InserisciNome();
    }
    
    @Override
    public void map(final boolean b) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        final TiledMapGame tl = new TiledMapGame(b); 
        final LwjglApplication app = new LwjglApplication(tl, cfg);    
        tl.setApp(app);
    }
    
    @Override
    public void save() {
        PlayerImpl.getPlayer().setPosition(PlayerSprite.getSprite().getPosition().getX().intValue(), PlayerSprite.getSprite().getPosition().getY().intValue());
        if (name != null) {
            PlayerImpl.getPlayer().setName(name);
        }
        SaveController.getController().save();
    }
    
    @Override
    public void box() {
        new Box();
    }
    
    @Override
    public void team(final boolean bl, final boolean bl2) {
        new Squadra(bl, bl2);
    }
    
    @Override
    public void bag() {
        new Zaino();
    }
    
    @Override
    public void stats(final Pokemon iD) {
        new Stats(iD);
    }

    @Override
    public void setName(final String text) {
        this.name = text;
    }
    
    @Override
    public void attack(final Move move) {
        FightController.getController().attack(move);
    }
    
    @Override
    public void changePokemon(final Pokemon poke) throws PokemonIsExhaustedException, PokemonIsFightingException {
        FightController.getController().changePokemon(poke);
    }
    
    @Override
    public void useItem(final Item it, final Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
	FightController.getController().useItem(it, pk);	
    }
    
    @Override
    public void run() throws CannotEscapeFromTrainerException {
        FightController.getController().run();
    }
    
    @Override
    public List<Pokedex> getEvolutions() {
        return FightController.getController().resolveEvolution();
    }
    
    @Override
    public void selectPokemon(final Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException {
        FightController.getController().selectPokemon(pk);
    }
    
    @Override
    public void fightScreen(final Pokemon pk) {
        MainController.getController().updateStatus(State.FIGHTING);
        new FightScreen(pk);
    }
}