package controller.view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.MainController;
import controller.parameters.State;
import model.pokemon.Pokemon;
import view.View;
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
    private String name;
    
    @Override
    public void market() {
        MainController.getController().updateStatus(State.READING);
        Market m = new Market();
        View.getView().addNew(m);
        View.getView().showCurrent();
    }
    
    @Override
    public void showMenu() {
        MainController.getController().updateStatus(State.MENU);
        Menu m = new Menu();
        View.getView().addNew(m);
        View.getView().showCurrent();
    }
    
    @Override
    public void firstMenu() {
        MainController.getController().updateStatus(State.FIRST_MENU);
        View.getView().addNew(new FirstMenu());
        View.getView().showCurrent();
    }
    
    @Override
    public void secondMenu() {
        MainController.getController().updateStatus(State.SECOND_MENU);
        View.getView().addNew(new SecondMenu());
        View.getView().showCurrent();
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
            MainController.getController().getPlayer().get().setName(this.name);
        }
        MainController.getController().save();
    }
    
    @Override
    public void box() {
        BoxMenu b = new BoxMenu();
        View.getView().addNew(b);
        View.getView().showCurrent();
    }
    
    @Override
    public void team(final boolean canCloseMenu, final boolean canChangePokemon) {
        TeamMenu tm = new TeamMenu(canCloseMenu, canChangePokemon);
        View.getView().addNew(tm);
        View.getView().showCurrent();
    }
    
    @Override
    public void bag() {
        BagMenu bm = new BagMenu();
        View.getView().addNew(bm);
        View.getView().showCurrent();
    }
    
    @Override
    public void stats(final Pokemon pokemon) {
        Statistics st = new Statistics(pokemon);
        View.getView().addNew(st);
        View.getView().showCurrent();
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
        View.getView().addNew(fs);
        View.getView().showCurrent();
    }
}