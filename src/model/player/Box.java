package model.player;

import java.util.List;

import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.pokemon.Pokemon;
import model.squad.Squad;

public interface Box {
    
    public void depositPokemon(final Pokemon pkmn, final Squad squad) throws PokemonNotFoundException, OnlyOnePokemonInSquadException;
    
    public void withdrawPokemon(final Pokemon pkmn, final Squad squad) throws PokemonNotFoundException, SquadFullException;
    
    public void putCapturedPokemon(final Pokemon pkmn);
    
    public int getBoxSize();
    
    public List<Pokemon> getPokemonList();
    
    public boolean contains(final Pokemon pkmn);
    
    public void setPokemons(List<Pokemon> pokemons);
    
    public String toString();
}
