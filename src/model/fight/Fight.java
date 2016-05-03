package model.fight;

import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;
import model.items.Item;
import java.util.List;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;

public interface Fight {

    //è anche da chiamare nella view per capire quando il player viene sconfitto
    public boolean checkLose(final Squad pkmSquad);

    public void runTurn() throws CannotEscapeFromTrainerException;

    //da chiamare in caso il pokemon alleato viene accoppato dal pokemon nemico
    public void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void changeTurn(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void itemTurn(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException;

    public void moveTurn(final Move move);

    //da chiamare nella view a fine combattimento
    public List<PokemonInBattle> getPkmsThatMustEvolve();

    public void evolvePkms();

    public Pokemon getCurrentEnemyPokemon();

}