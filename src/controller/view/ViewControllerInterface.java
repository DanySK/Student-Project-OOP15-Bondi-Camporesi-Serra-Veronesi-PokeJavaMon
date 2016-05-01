package controller.view;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;

public interface ViewControllerInterface {

    public void market();

    public void showMenu();

    public void firstMenu();

    public void secondMenu();

    public void map(boolean b);

    public void save();

    public void box();
    
    public void team(final boolean bl, final boolean bl2);
    
    public void bag();

    public void stats(Pokemon iD);

    public void setName(String text);

    public void attack(Move move);

    public void changePokemon(Pokemon poke) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException,
                CannotCaughtTrainerPkmException, IllegalStateException;

    public void run() throws CannotEscapeFromTrainerException;

    public List<Pokedex> getEvolutions();

    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void fightScreen(Pokemon pk);
}