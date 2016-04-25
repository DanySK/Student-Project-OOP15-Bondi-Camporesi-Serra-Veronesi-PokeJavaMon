package controller;

import exceptions.CannotEscapeFromTrainerException;
import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.FightVsWildPkm;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.trainer.Trainer;
import view.MethodsToImplement;

public class FightController {

    private static FightController SINGLETON;
    private Fight fight;
    private MethodsToImplement view;
    
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
    
    public void attack(Move move) {
        Move enemy = fight.enemyMove();
        if (fight.isAllyFastest()) {
            view.movePlayerThenOpponent(move, enemy);
        } else {
            view.moveOpponentThenPlayer(enemy, move);
        }
    }
    
    public void run() {
        try {
            if (fight.run()) {
                view.run();
            } else {
                Move enemy = fight.enemyMove();
                view.showMessageThenOpponentMove("Cannot escape now!", enemy);
            }
        } catch (CannotEscapeFromTrainerException e) {
            Move enemy = fight.enemyMove();
            view.showMessageThenOpponentMove("Cannot escape from trainer!", enemy);
        }
    }
    
    @SuppressWarnings("unused")
    public void useItem(Item it) {
        // TODO chiedere al model se si puo usare l oggetto
        if (true) {
            Move enemy = fight.enemyMove();
            view.useItemThenOpponentMove(it, enemy);
        } else {
            Move enemy = fight.enemyMove();
            view.showMessageThenOpponentMove("Cannot use this item now!", enemy);
        }
    }
    
    public void changePokemon(Pokemon pk) {
        Move enemy = fight.enemyMove();
        view.changePokemonThenOpponentMove(pk, enemy);
    }
}