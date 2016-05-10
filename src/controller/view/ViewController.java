package controller.view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.Controller;
import controller.parameters.State;
import model.pokemon.Pokemon;
import view.View;
import view.frames.*;
import view.resources.SecondMenu;
import view.resources.GameView;
import view.resources.FirstMenu;

/**
 * This class controls the menus from the view
 */
public class ViewController implements ViewControllerInterface {
    
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private String name;
    
    @Override
    public void market() {
        Controller.getController().updateStatus(State.READING);
        Market m = new Market();
        View.getView().addNew(m);
        View.getView().showCurrent();
    }
    
    @Override
    public void showMenu() {
        Controller.getController().updateStatus(State.MENU);
        Menu m = new Menu();
        View.getView().addNew(m);
        View.getView().showCurrent();
    }
    
    @Override
    public void firstMenu() {
        Controller.getController().updateStatus(State.FIRST_MENU);
        new FirstMenu().title();
    }
    
    @Override
    public void secondMenu() {
        Controller.getController().updateStatus(State.SECOND_MENU);
        new SecondMenu();
    }
    
    @Override
    public void map(final boolean newGame) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL30 = true;
        cfg.width = WIDTH;
        cfg.height = HEIGHT;
        final GameView tl = new GameView(newGame); 
        new LwjglApplication(tl, cfg);    
    }
    
    @Override
    public void save() {
        if (this.name != null) {
            Controller.getController().getPlayer().setName(this.name);
        }
        Controller.getController().save();
    }
    
    @Override
    public void box() {
        new BoxMenu();
    }
    
    @Override
    public void team(final boolean canCloseMenu, final boolean canChangePokemon) {
        new TeamMenu(canCloseMenu, canChangePokemon);
    }
    
    @Override
    public void bag() {
        new BagMenu();
    }
    
    @Override
    public void stats(final Pokemon pokemon) {
        new Statistics(pokemon);
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public void initName() {
        if (this.name != null) {
            Controller.getController().getPlayer().setName(this.name);
        }
    }
    
    @Override
    public void fightScreen() {
        Controller.getController().updateStatus(State.FIGHTING);
        FightScreen fs = new FightScreen();
        View.getView().addNew(fs);
        View.getView().showCurrent();
    }
}