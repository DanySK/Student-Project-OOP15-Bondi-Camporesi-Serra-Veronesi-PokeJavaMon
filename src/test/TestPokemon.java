package test;

import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.StaticPokemonFactory;

public class TestPokemon {

    public static void main(String[] args) {
        int x = 0;
        for (final Pokedex p : Pokedex.values()) {
            Pokemon pk = StaticPokemonFactory.createPokemon(p, 50);
            x++;
            System.out.println(p + ", Moves: " + pk.getCurrentMoves());
        }
        System.out.println(x);
    }
}
