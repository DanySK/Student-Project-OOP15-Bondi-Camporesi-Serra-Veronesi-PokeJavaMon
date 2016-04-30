package controller;

import java.util.List;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
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
import model.pokemon.PokemonDB;
import view.PlayerSprite;
import view.frames.*;
import view.resources.TiledMapGame;
import view.resources.TitleWiew;

public final class ViewController {
    private String name;
    private LwjglApplication app;
    private static ViewController SINGLETON;
    
    private ViewController() {}
    
    public static ViewController getController() {
        if (SINGLETON == null) {
            synchronized (ViewController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new ViewController();
                }
            }
        }
        return SINGLETON;
    }
    
    public void showMenu() {
        MainController.getController().updateStatus(State.MENU);
        new Menu();
    }
    
    public void firstMenu() {
        MainController.getController().updateStatus(State.FIRST_MENU);
        new TitleWiew().title();
    }
    
    public void secondMenu() {
        MainController.getController().updateStatus(State.SECOND_MENU);
        new view.frames.InserisciNome();
    }
    
    public void map(boolean b) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        TiledMapGame tl = new TiledMapGame(b);   
        app = new LwjglApplication(tl, cfg);      
        tl.setApp(app);
    }
    
    public void save() {
        PlayerImpl.getPlayer().setPosition(PlayerSprite.getSprite().getPosition().getX().intValue(), PlayerSprite.getSprite().getPosition().getY().intValue());
        if (name != null) {
            PlayerImpl.getPlayer().setName(name);
        }
        SaveController.getController().save();
    }
    
    public void box() {
        new view.frames.Box();
    }
    
    public static void team() {
        new Squadra();
    }
    
    public static void bag() {
        new Zaino();
    }
    
    public void stats(final int id) {
        new Stats(id);
    }

    public void setName(String text) {
        this.name = text;
    }
    
    public void attack(Move move) {
        FightController.getController().attack(move);
    }
    
    public void changePokemon(Pokemon poke) throws PokemonIsExhaustedException, PokemonIsFightingException {
        FightController.getController().changePokemon(poke);
    }
    
    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
	FightController.getController().useItem(it, pk);	
    }
    
    public void run() throws CannotEscapeFromTrainerException {
        FightController.getController().run();
    }
    
    public List<PokemonDB> getEvolutions() {
        return FightController.getController().resolveEvolution();
    }
    
    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException {
        FightController.getController().selectPokemon(pk);
    }
    
    public void fightScreen(Pokemon pk) {
        MainController.getController().updateStatus(State.FIGHTING);
        new FightScreen(pk);
    }
}