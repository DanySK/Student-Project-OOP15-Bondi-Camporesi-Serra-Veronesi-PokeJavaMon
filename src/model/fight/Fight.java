package model.fight;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;

public interface Fight {

    public boolean checkLose(final Squad pkmSquad);

    public void runTurn() throws CannotEscapeFromTrainerException;

    public void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void changeTurn(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void itemTurn(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException;

    public void moveTurn(final Move move);

    public List<PokemonInBattle> getPkmsThatMustEvolve();

    public void evolvePkms();

    public Pokemon getCurrentEnemyPokemon();

}