package view.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.keyboard.WalkingController;
import controller.load.LoadController;
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
import view.frames.FightScreen;
import view.frames.Menu;

public final class ViewController {
    
    private static WalkingController w;
    private static Player p;
    private static String name;
    
    public static void showMenu() {
        new Menu();
        w.stop();
    }
    
    public static void save() {
        SaveController sc = new SaveController();
        LoadController lc = new LoadController();
        General g;
        if (lc.saveExists()) {
            g = lc.load();
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
        g.setPosition(new Pair<Float, Float>(p.getX(),p.getY()));
        sc.save(g);
    }
    
    public static void resume() {
        w.start();
    }
    
    public static void pause() {
        w.stop();
    }

    public static void setupController(WalkingController walkingController) {
        ViewController.w = walkingController;
    }
    
    public static void setupPlayer(Player p) {
        ViewController.p = p;
    }

    public static void setName(String text) {
        ViewController.name = text;
    }
    
    public static void fightScreen() {
        new FightScreen();
    }
}
