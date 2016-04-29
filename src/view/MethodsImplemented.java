package view;

import controller.MainController;
import controller.parameters.State;
import model.fight.Effectiveness;
import model.items.Item;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.frames.FightScreen;

public class MethodsImplemented implements MethodsToImplement {

    @Override
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
            Pokemon nextEnemyPokemon, String optionalMessage) {
        System.out.println("RESOLVING MOVE");
        System.out.println(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name());
        if (myMoveFirst) {
            System.out.println("My: " + myMove);
            if (enemyMove == null) {
                System.out.println("ENEMY DEAD");
                if (nextEnemyPokemon != null) {
                    System.out.println("Next: " + nextEnemyPokemon.getPokemon().name());
                } else {
                    System.out.println("ENEMY DEFEATED");
                    MainController.getController().updateStatus(State.WALKING);
                    FightScreen.dispose();
                }
            } else {
                System.out.println("Enemy: " + enemyMove);
                System.out.println("HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
                if (lastPokemonKills) {
                    System.out.println("ALLY DEAD");
                    MainController.getController().updateStatus(State.WALKING);
                    FightScreen.dispose();
                }
            }
        } else {
            System.out.println("Enemy: " + enemyMove);
            System.out.println("HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
            if (myMove == null) {
                System.out.println("ALLY DEAD");
                MainController.getController().updateStatus(State.WALKING);
                FightScreen.dispose();
            } else {
                System.out.println("My: " + myMove);
                if (lastPokemonKills) {
                    System.out.println("ENEMY DEAD");
                    if (nextEnemyPokemon != null) {
                        System.out.println("Next: " + nextEnemyPokemon.getPokemon().name());
                    } else {
                        System.out.println("ENEMY DEFEATED");
                        MainController.getController().updateStatus(State.WALKING);
                        FightScreen.dispose();
                    }
                }
            }
        }
    }

    @Override
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        System.out.println("RESOLVING POKEMON");
        System.out.println(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name());
        System.out.println("Enemy: " + enemyMove);
        System.out.println("HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
        if (isMyPokemonDead) {
            System.out.println("ALLY DEAD");
            MainController.getController().updateStatus(State.WALKING);
            FightScreen.dispose();
        }
    }

    @Override
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        System.out.println("RESOLVING ITEM");
        System.out.println(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name());
        System.out.println("Enemy: " + enemyMove);
        System.out.println("HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
        if (isMyPokemonDead) {
            System.out.println("ALLY DEAD");
            MainController.getController().updateStatus(State.WALKING);
            FightScreen.dispose();
        }
    }

    @Override
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        System.out.println("RESOLVING RUN");
        System.out.println(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name());
        if (success) {
            MainController.getController().updateStatus(State.WALKING);
            FightScreen.dispose();
        } else {
            System.out.println("RUN FAILED!");
            System.out.println("Enemy: " + enemyMove);
            System.out.println("HP: " + PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getCurrentHP());
            if (isMyPokemonDead) {
                System.out.println("ALLY DEAD");
                MainController.getController().updateStatus(State.WALKING);
                FightScreen.dispose();
            }
        }
    }

}