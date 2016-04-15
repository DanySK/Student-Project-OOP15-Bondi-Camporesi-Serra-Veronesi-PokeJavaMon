package view.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import controller.MainController;
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
import model.pokemon.Pokemon;
import model.pokemon.PokemonDB;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.resources.General;
import model.resources.Player;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;
import model.trainer.TrainerDB;
import model.utilities.Pair;
import view.frames.*;

public final class ViewController {
    
    private static String name;
    static LwjglApplication app;
    
    public static void showMenu() {
        MainController.updateStatus(State.MENU);
        new Menu();
    }
    
    public static void firstMenu() {
        MainController.updateStatus(State.FIRST_MENU);
        new TitleWiew();
        TitleWiew.title();
    }
    
    public static void secondMenu() {
        MainController.updateStatus(State.SECOND_MENU);
        new view.frames.InserisciNome();
    }
    
    public static void map(boolean b) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PokeJavaMon";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        
        TiledMapGame tl = new TiledMapGame(b);
        
        app = new LwjglApplication(tl, cfg);
        
        tl.setApp(app);
    }
    
    public static void save() {
        General g;
        if (LoadController.saveExists()) {
            g = LoadController.load();
        } else {
            List<Pokemon> team = new ArrayList<>();
            team.add(StaticPokemonFactory.createPokemon(PokemonDB.BLASTOISE,30));
            Box bx = BoxImpl.getBox();
            bx.putCapturedPokemon(StaticPokemonFactory.createPokemon(PokemonDB.CHARIZARD,30));
            bx.putCapturedPokemon(StaticPokemonFactory.createPokemon(PokemonDB.VENUSAUR,30));
            List<Trainer> tr = new ArrayList<>();
            tr.add(StaticTrainerFactory.createTrainer(TrainerDB.DR_GHAIN.name(), false));
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
    
    public static void box() {
        new view.frames.Box();
    }
    
    public static void team() {
        new Squadra();
    }
    
    public static void bag() {
        new Zaino();
    }
    
    public static void stats() {
        new Stats();
    }

    public static void setName(String text) {
        ViewController.name = text;
    }
    
    public static void fightScreen() {
        MainController.updateStatus(State.FIGHTING);
        new FightScreen();
    }
}
