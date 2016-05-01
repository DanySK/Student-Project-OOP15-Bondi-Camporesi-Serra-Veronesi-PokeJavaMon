package controller.fight;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Effectiveness;
import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.FightVsWildPkm;
import model.items.Item;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.methods.MethodsImplemented;
import view.methods.MethodsToImplement;

public class FightController implements FightControllerInterface {

    private static FightControllerInterface SINGLETON;
    private Fight fight;
    private MethodsToImplement view = new MethodsImplemented();
    
    private FightController() {}
    
    public static FightControllerInterface getController() {    
        if (SINGLETON == null) {
            synchronized (FightController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new FightController();
                }
            }
        }
        return SINGLETON;
    }
    
    @Override
    public void newFightWithTrainer(Trainer tr) {
        fight = new FightVsTrainer(tr);
        System.out.println("Fight with: " + tr.getID());
    }
    
    @Override
    public void newFightWithPokemon(Pokemon pm) {
        fight = new FightVsWildPkm(pm);
        System.out.println("Fight with: " + pm.getPokemon().name() + " LVL: " + pm.getStat(Stat.LVL));
        System.out.println("My Pkmn HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
    }
    
    @Override
    public Fight getFight() {
        return this.fight;
    }
    
    // Metodi che chiama il MODEL
    
    @Override
    public void resolveAttack(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove, Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills, Pokemon nextEnemyPokemon, String optionalMessage) {
        view.resolveMove(myMove, myMoveEffectiveness, enemyMove, enemyMoveEffectiveness, myMoveFirst, lastPokemonKills, nextEnemyPokemon, optionalMessage);
    }
    
    @Override
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveRun(success, enemyMove, isMyPokemonDead);
    }
    
    @Override
    public void resolveItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveUseItem(item, pk, enemyMove, isMyPokemonDead);
    }
    
    @Override
    public void resolvePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveChangePokemon(myPokemon, enemyMove, isMyPokemonDead);
    }
    
    // Metodi che chiama la VIEW
    
    @Override
    public void attack(Move mv) {
        fight.moveTurn(mv);
    }
    
    @Override
    public void run() throws CannotEscapeFromTrainerException {
        fight.runTurn();
    }
    
    @Override
    public void changePokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException {
        fight.changeTurn((PokemonInBattle) pk);
    }
    
    @Override
    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
        fight.itemTurn(it, (PokemonInBattle) pk);
    }
    
    @Override
    public List<Pokedex> resolveEvolution() {
        return fight.getPkmsThatMustEvolve();
    }
    
    @Override
    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException {
        fight.applyChange((PokemonInBattle) pk);
    }

    @Override
    public Pokemon getEnemyPokemon() {
        return fight.getCurrentEnemyPokemon();
    }
}