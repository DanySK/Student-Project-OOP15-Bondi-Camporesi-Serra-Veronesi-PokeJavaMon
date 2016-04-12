package test.save;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import controller.modelResources.General;
import controller.save.SaveController;
import model.box.Box;
import model.box.BoxImpl;
import model.inventory.Inventory;
import model.inventory.InventoryImpl;
import model.items.Boost;
import model.items.Pokeball;
import model.items.Pokeball.PokeballType;
import model.items.Potion;
import model.items.Potion.PotionType;
import model.pokemon.InitializeMoves;
import model.pokemon.Pokemon;
import model.pokemon.PokemonDB;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;
import model.trainer.TrainerDB;
import model.utilities.Pair;

public class SaveTest {

    public static void main(String[] args) {
        SaveController sc = new SaveController();
        InitializeMoves.initAllPokemonsTypes();
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
        General g = new General(team,bx,tr,i,500,new Pair<Float, Float>(672f, 2272f));
        sc.save(g);
        System.out.println("SAVED SUCCESSFULLY");
    }
}
