package controller.view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.Controller;
import controller.parameters.State;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import view.frames.*;
import view.resources.TiledMapGame;
import view.resources.TitleWiew;

/**
 * This class controls the menus from the view
 */
public class ViewController implements ViewControllerInterface {
    
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private String name;
    
    @Override
    public void market() {
        new Market();
    }
    
    @Override
    public void showMenu() {
        Controller.getController().updateStatus(State.MENU);
        new Menu();
    }
    
    @Override
    public void firstMenu() {
        Controller.getController().updateStatus(State.FIRST_MENU);
        new TitleWiew().title();
    }
    
    @Override
    public void secondMenu() {
        Controller.getController().updateStatus(State.SECOND_MENU);
        new InserisciNome();
    }
    
    @Override
    public void map(final boolean newGame) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL20 = true;
        cfg.width = WIDTH;
        cfg.height = HEIGHT;
        final TiledMapGame tl = new TiledMapGame(newGame); 
        final LwjglApplication app = new LwjglApplication(tl, cfg);    
        tl.setApp(app);
    }
    
    @Override
    public void save() {
        if (name != null) {
            PlayerImpl.getPlayer().setName(name);
        }
        Controller.getController().save();
    }
    
    @Override
    public void box() {
        new Box();
    }
    
    @Override
    public void team(final boolean canCloseMenu, final boolean canChangePokemon) {
        new Squadra(canCloseMenu, canChangePokemon);
    }
    
    @Override
    public void bag() {
        new Zaino();
    }
    
    @Override
    public void stats(final Pokemon pokemon) {
        new Stats(pokemon);
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public void fightScreen(final Pokemon pokemon) {
        Controller.getController().updateStatus(State.FIGHTING);
        new FightScreen(pokemon);
    }
}