package model.squad;

import java.util.List;

import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;

public interface Squad {
    public int getSquadSize();
    
    public List<PokemonInBattle> getPokemonList();
    
    public void remove(final Pokemon pkmn) throws OnlyOnePokemonInSquadException;
    
    public void add(final Pokemon pkmn) throws SquadFullException;
    
    public boolean contains(final Pokemon pkmn);
    
    public PokemonInBattle getPokemon(final Pokemon pkmn);

    public void switchPokemon(final int index1, final int index2) throws IndexOutOfBoundsException;

    public String toString();
    
}
