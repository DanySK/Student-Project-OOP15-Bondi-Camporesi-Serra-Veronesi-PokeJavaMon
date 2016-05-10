package test;

import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;

public class TestPokemon {

    public static void main(String[] args) {
        for (final Pokedex p : Pokedex.values()) {
            Pokemon pk = StaticPokemonFactory.createPokemon(p, 50);
            System.out.println(p + ", stats: " + pk.getAllStats());
        }
    }
}
