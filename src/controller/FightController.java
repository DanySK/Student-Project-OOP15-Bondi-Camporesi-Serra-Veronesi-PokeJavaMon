package controller;

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
import model.pokemon.PokemonDB;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.MethodsImplemented;
import view.MethodsToImplement;

public class FightController {

    private static FightController SINGLETON;
    private Fight fight;
    private MethodsToImplement view = new MethodsImplemented();
    
    private FightController() {}
    
    public static FightController getController() {    
        if (SINGLETON == null) {
            synchronized (FightController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new FightController();
                }
            }
        }
        return SINGLETON;
    }
    
    public void newFightWithTrainer(Trainer tr) {
        fight = new FightVsTrainer(tr);
        System.out.println("Fight with: " + tr.getID());
    }
    
    public void newFightWithPokemon(Pokemon pm) {
        fight = new FightVsWildPkm(pm);
        System.out.println("Fight with: " + pm.getPokemon().name() + " LVL: " + pm.getStat(Stat.LVL));
        System.out.println("My Pkmn HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
    }
    
    public Fight getFight() {
        return this.fight;
    }
    
    // Metodi che chiama il MODEL
    
    public void resolveAttack(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove, Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills, Pokemon nextEnemyPokemon, String optionalMessage) {
        view.resolveMove(myMove, myMoveEffectiveness, enemyMove, enemyMoveEffectiveness, myMoveFirst, lastPokemonKills, nextEnemyPokemon, optionalMessage);
    }
    
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveRun(success, enemyMove, isMyPokemonDead);
    }
    
    public void resolveItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveUseItem(item, pk, enemyMove, isMyPokemonDead);
    }
    
    public void resolvePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveChangePokemon(myPokemon, enemyMove, isMyPokemonDead);
    }
    
    // Metodi che chiama la VIEW
    
    public void attack(Move mv) {
        fight.moveTurn(mv);
    }
    
    public void run() throws CannotEscapeFromTrainerException {
        fight.runTurn();
    }
    
    public void changePokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException {
        fight.changeTurn((PokemonInBattle) pk);
    }
    
    public void useItem(Item it, Pokemon pk) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
        fight.itemTurn(it, (PokemonInBattle) pk);
    }
    
    public List<PokemonDB> resolveEvolution() {
        return fight.getPkmsThatMustEvolve();
    }
    
    public void selectPokemon(Pokemon pk) throws PokemonIsExhaustedException, PokemonIsFightingException {
        fight.applyChange((PokemonInBattle) pk);
    }

    public Pokemon getEnemyPokemon() {
        return fight.getCurrentEnemyPokemon();
    }
}