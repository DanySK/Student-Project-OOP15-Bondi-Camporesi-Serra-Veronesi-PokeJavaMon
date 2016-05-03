package controller.view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.parameters.State;
import controller.save.SaveController;
import controller.status.StatusController;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
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
        StatusController.getController().updateStatus(State.MENU);
        new Menu();
    }
    
    @Override
    public void firstMenu() {
        StatusController.getController().updateStatus(State.FIRST_MENU);
        new TitleWiew().title();
    }
    
    @Override
    public void secondMenu() {
        StatusController.getController().updateStatus(State.SECOND_MENU);
        new InserisciNome();
    }
    
    @Override
    public void map(final boolean newGame) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        final TiledMapGame tl = new TiledMapGame(newGame); 
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
        StatusController.getController().updateStatus(State.FIGHTING);
        new FightScreen(pokemon);
    }
}