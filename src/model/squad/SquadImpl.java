package model.squad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.OnlyOnePokemonInSquadException;
import exceptions.SquadFullException;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;

public class SquadImpl implements Squad {
    
    public static int MAX_SIZE = 6;

    private final List<PokemonInBattle> pokemonInSquad;
    
    public SquadImpl(final PokemonInBattle ...pkmns) {
        pokemonInSquad = new ArrayList<>(MAX_SIZE);
        if (pkmns.length > 6) {
            throw new IllegalArgumentException("Too many pokemons in squad, Max = " + MAX_SIZE);
        }
        if (pkmns.length > 0) {
            for (final PokemonInBattle pkmn : pkmns) {
                pokemonInSquad.add(pkmn);
            }
        }
    }

    @Override
    public int getSquadSize() {
        return this.pokemonInSquad.size();
    }

    @Override
    public List<PokemonInBattle> getPokemonList() {
        return Collections.unmodifiableList(pokemonInSquad);
    }

    @Override
    public void remove(Pokemon pkmn) throws OnlyOnePokemonInSquadException {
        if (this.pokemonInSquad.size() == 1) {
            throw new OnlyOnePokemonInSquadException();
        }
        
        pokemonInSquad.remove(pkmn);
    }

    @Override
    public void add(Pokemon pkmn) throws SquadFullException {
        if (this.pokemonInSquad.size() == MAX_SIZE) {
            throw new SquadFullException();
        }
        
        pokemonInSquad.add((PokemonInBattle) pkmn);
    }

    @Override
    public boolean contains(Pokemon pkmn) {
        for (final PokemonInBattle p : pokemonInSquad) {
            if (((PokemonInBattle)pkmn).equals(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PokemonInBattle getPokemon(Pokemon pkmn) {
        if (!this.contains(pkmn)) {
            throw new IllegalArgumentException();
        }
        for (final PokemonInBattle p : pokemonInSquad) {
            if (((PokemonInBattle) pkmn).equals(p)) {
                return p;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        String retString = "";
        for (final PokemonInBattle p : this.pokemonInSquad) {
            retString = retString + " " + p.getPokemon().getName() + ",lvl= " + p.getStat(Stat.LVL) + " /";
        }
        return retString;
    }

    @Override
    public void switchPokemon(int index1, int index2) throws IndexOutOfBoundsException {
        if (index1 >= MAX_SIZE || index1 < 0 || index2 >= MAX_SIZE || index2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        final PokemonInBattle tmpPokemon = this.pokemonInSquad.set(index1, pokemonInSquad.get(index2));
        this.pokemonInSquad.set(index2, tmpPokemon);
        
    }

}
