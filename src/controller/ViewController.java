package controller;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import controller.parameters.State;
import controller.save.SaveController;
import model.player.PlayerImpl;
import model.resources.Player;
import view.frames.*;
import view.resources.TiledMapGame;
import view.resources.TitleWiew;

public final class ViewController {
    private String name = null;
    private LwjglApplication app;
    private static ViewController SINGLETON;
    
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
        TitleWiew t = new TitleWiew();
        t.title();
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
        Player.resetPos();
        PlayerImpl.getPlayer().setPosition(Player.getPosition().getX().intValue(), Player.getPosition().getY().intValue());
        if (name != null) {
            PlayerImpl.getPlayer().setName(name);
        }
        SaveController.getController().save();
    }
    
    public void box() {
        new view.frames.Box();
    }
    
    public void team() {
        new Squadra();
    }
    
    public void bag() {
        new Zaino();
    }
    
    public void stats() {
        new Stats();
    }

    public void setName(String text) {
        this.name = text;
    }
    
    public void fightScreen() {
        MainController.getController().updateStatus(State.FIGHTING);
        new FightScreen();
    }
}