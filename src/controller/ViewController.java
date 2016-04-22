package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.load.LoadController;
import controller.parameters.State;
import controller.save.SaveController;
import model.box.Box;
import model.box.BoxImpl;
import model.inventory.Inventory;
import model.inventory.InventoryImpl;
import model.items.Boost;
import model.items.Pokeball;
import model.items.Potion;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.map.Drawable.Direction;
import model.map.PokeMapImpl;
import model.pokemon.Pokemon;
import model.pokemon.PokemonDB;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.resources.General;
import model.resources.Player;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;
import model.utilities.Pair;
import view.frames.*;
import view.resources.Play;
import view.resources.TiledMapGame;
import view.resources.TitleWiew;

public final class ViewController {
    
    private String name;
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
        General g;
        if (LoadController.saveExists()) {
            g = LoadController.load(new PokeMapImpl(new Play(false).getMap()));
        } else {
            List<Pokemon> team = new ArrayList<>();
            team.add(StaticPokemonFactory.createPokemon(PokemonDB.BLASTOISE,30));
            Box bx = BoxImpl.getBox();
            bx.putCapturedPokemon(StaticPokemonFactory.createPokemon(PokemonDB.CHARIZARD,30));
            bx.putCapturedPokemon(StaticPokemonFactory.createPokemon(PokemonDB.VENUSAUR,30));
            List<Trainer> tr = new ArrayList<>();
            tr.add(StaticTrainerFactory.createTrainer("DR. GHAIN", Direction.SOUTH, false, 0, 0, new ArrayList<>(), "1", "2", "3", 500, 0));
            Map<Pokeball, Integer> balls = new HashMap<>();
            Map<Boost, Integer> boosts = new HashMap<>();
            Map<Potion, Integer> potions = new HashMap<>();
            balls.put(new Pokeball(PokeballType.Ultraball),10);
            boosts.put(new Boost(Stat.ATK), 5);
            potions.put(new Potion(PotionType.SUPERPOTION), 15);
            Inventory i = InventoryImpl.getInventory();
            i.setBoosts(boosts);
            i.setPokeballs(balls);
            i.setPotions(potions);
            g = new General(team,bx,tr,i,500,new Pair<Float, Float>(672f, 2272f));
        }
        System.out.println(name);
        g.setPosition(new Pair<Float, Float>(Player.getPosition().getX(),Player.getPosition().getY()));
        SaveController.save(g);
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
        ViewController.getController().name = text;
    }
    
    public void fightScreen() {
        MainController.getController().updateStatus(State.FIGHTING);
        new FightScreen();
    }
}