package test.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.MainController;
import exceptions.SquadFullException;
import model.items.Potion;
import model.items.Pokeball;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Pokedex;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;

public class SaveTest {

    public static void main(String[] args) {
        PlayerImpl.getPlayer().setMoney(750);
        PlayerImpl.getPlayer().setPosition(28*16, (299 - 177) * 16);
        PlayerImpl.getPlayer().setName("DEFAULT_NAME");
        try {
            PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.BLASTOISE, 30));
        } catch (SquadFullException e) {
            e.printStackTrace();
        }
        Map<String, Integer> potions = new HashMap<String, Integer>();
        potions.put(Potion.PotionType.Superpotion.name(), 15);
        Map<String, Integer> boosts = new HashMap<String, Integer>();
        boosts.put(Stat.ATK.name()+"X", 20);
        Map<String, Integer> balls = new HashMap<String, Integer>();
        balls.put(Pokeball.PokeballType.Ultraball.name(), 35);
        MainController.getController().getInventory().get().initializeInventory(potions, boosts, balls);
        List<Pokemon> box = new ArrayList<Pokemon>();
        box.add(StaticPokemonFactory.createPokemon(Pokedex.CHARIZARD, 30));
        box.add(StaticPokemonFactory.createPokemon(Pokedex.VENUSAUR, 30));
        PlayerImpl.getPlayer().getBox().initializePokemons(box);
        MainController.getController().save();
    }
}
