package controller.view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.MainController;
import controller.parameters.State;
import model.pokemon.Pokemon;
import view.View;
import view.ViewInterface;
import view.fight.FightScreen;
import view.resources.SecondMenu;
import view.windows.*;
import view.resources.GameView;
import view.resources.FirstMenu;

/**
 * This class controls the menus from the view 
 */
public class MainViewController implements ViewController {
    
    private static final int WIDTH = 1280 / 2;
    private static final int HEIGHT = 720 / 2;
    private ViewInterface view;
    private String name;
    
    public MainViewController() {
        this.view = View.getView();
    }
    
    @Override
    public void market() {
        MainController.getController().updateStatus(State.READING);
        Market m = new Market();
        this.view.addNew(m);
        this.view.showCurrent();
    }
    
    @Override
    public void showMenu() {
        MainController.getController().updateStatus(State.MENU);
        Menu m = new Menu();
        this.view.addNew(m);
        this.view.showCurrent();
    }
    
    @Override
    public void firstMenu() {
        MainController.getController().updateStatus(State.FIRST_MENU);
        this.view.addNew(new FirstMenu());
        this.view.showCurrent();
    }
    
    @Override
    public void secondMenu() {
        MainController.getController().updateStatus(State.SECOND_MENU);
        this.view.addNew(new SecondMenu());
        this.view.showCurrent();
    }
    
    @Override
    public void map(final boolean newGame) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL30 = false;
        cfg.width = WIDTH;
        cfg.height = HEIGHT;
        final GameView tl = new GameView(newGame); 
        new LwjglApplication(tl, cfg);    
    }
    
    @Override
    public void save() {
        if (this.name != null) {
            MainController.getController().getPlayer().get().setName(this.name);
        }
        MainController.getController().save();
    }
    
    @Override
    public void box() {
        BoxMenu b = new BoxMenu();
        this.view.addNew(b);
        this.view.showCurrent();
    }
    
    @Override
    public void team(final boolean canCloseMenu, final boolean canChangePokemon) {
        TeamMenu tm = new TeamMenu(canCloseMenu, canChangePokemon);
        this.view.addNew(tm);
        this.view.showCurrent();
    }
    
    @Override
    public void bag() {
        BagMenu bm = new BagMenu();
        this.view.addNew(bm);
        this.view.showCurrent();
    }
    
    @Override
    public void stats(final Pokemon pokemon) {
        Statistics st = new Statistics(pokemon);
        this.view.addNew(st);
        this.view.showCurrent();
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public void initName() {
        if (this.name != null) {
            MainController.getController().getPlayer().get().setName(this.name);
        }
    }
    
    @Override
    public void fightScreen() {
        MainController.getController().updateStatus(State.FIGHTING);
        FightScreen fs = new FightScreen();
        this.view.addNew(fs);
        this.view.showCurrent();
    }
}