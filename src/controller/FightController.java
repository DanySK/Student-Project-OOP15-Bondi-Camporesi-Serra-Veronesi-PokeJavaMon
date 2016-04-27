package controller;

import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.FightVsWildPkm;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.MethodsToImplement;

public class FightController {

    private static FightController SINGLETON;
    private Fight fight;
    private MethodsToImplement view;
    
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
        System.out.println("Fight with: " + pm.getPokemon().name());
    }
    
    // Metodi che chiama il MODEL
    
    public void resolveAttack(Move myMove, String myMoveMessage, Move enemyMove, String enemyMoveMessage, boolean myMoveFirst, boolean lastPokemonKills, Pokemon nextEnemyPokemon, String optionalMessage) {
        view.resolveMove(myMove, myMoveMessage, enemyMove, enemyMoveMessage, myMoveFirst, lastPokemonKills, nextEnemyPokemon, optionalMessage);
    }
    
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveRun(success, enemyMove, isMyPokemonDead);
    }
    
    public void resolveItem(Item item, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveUseItem(item, enemyMove, isMyPokemonDead);
    }
    
    public void resolvePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        view.resolveChangePokemon(myPokemon, enemyMove, isMyPokemonDead);
    }
    
    // Metodi che chiama la VIEW
    
    public void attack(Move mv) {
        // TODO chiamare il metodo del model passandogli la mossa
    }
    
    public void run() {
        // TODO chiamare il metodo del model per la fuga
    }
    
    public void changePokemon(Pokemon pk) {
        // TODO chiamare il metodo del model passandogli il pokemon
    }
    
    public void useItem(Item it) {
        // TODO chiamare il metodo del model passandogli l'oggetto
    }
}