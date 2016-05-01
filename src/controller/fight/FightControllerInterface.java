package controller.fight;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Effectiveness;
import model.fight.Fight;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.trainer.Trainer;

public interface FightControllerInterface {

    public void newFightWithTrainer(Trainer tr);

    public void newFightWithPokemon(Pokemon pm);

    public Fight getFight();

    public void resolveAttack(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
                Pokemon nextEnemyPokemon, String optionalMessage);

    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);

    public void resolveItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead);

    public void resolvePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead);

    public void attack(Move mv);

    public void run() throws CannotEscapeFromTrainerException;

    public void changePokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException,
                CannotCaughtTrainerPkmException, IllegalStateException;

    public List<Pokedex> resolveEvolution();

    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException;

    public Pokemon getEnemyPokemon();
}