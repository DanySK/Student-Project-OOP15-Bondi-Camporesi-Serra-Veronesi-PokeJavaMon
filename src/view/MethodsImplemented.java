package view;

import controller.MainController;
import controller.parameters.State;
import model.fight.Effectiveness;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.frames.FightScreen;

public class MethodsImplemented implements MethodsToImplement {

    @Override
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
            Pokemon nextEnemyPokemon, String optionalMessage) {
        System.out.println("RESOLVING MOVE");
        if (myMoveFirst) {
            System.out.println("My: " + myMove);
            if (enemyMove == null) {
                System.out.println("ENEMY DEAD");
                MainController.getController().updateStatus(State.WALKING);
                FightScreen.dispose();
            } else {
                System.out.println("Enemy: " + enemyMove);
                if (lastPokemonKills) {
                    System.out.println("ALLY DEAD");
                    MainController.getController().updateStatus(State.WALKING);
                    FightScreen.dispose();
                }
            }
        } else {
            System.out.println("Enemy: " + enemyMove);
            if (myMove == null) {
                System.out.println("ALLY DEAD");
                MainController.getController().updateStatus(State.WALKING);
                FightScreen.dispose();
            } else {
                System.out.println("My: " + myMove);
                if (lastPokemonKills) {
                    System.out.println("ENEMY DEAD");
                    MainController.getController().updateStatus(State.WALKING);
                    FightScreen.dispose();
                }
            }
        }
    }

    @Override
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        System.out.println("RESOLVING POKEMON");
    }

    @Override
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        System.out.println("RESOLVING ITEM");
    }

    @Override
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        System.out.println("RESOLVING RUN");
    }

}
