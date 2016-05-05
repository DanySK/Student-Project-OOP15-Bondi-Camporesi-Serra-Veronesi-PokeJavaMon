package view.methods;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import controller.parameters.State;
import model.fight.Effectiveness;
import model.items.Item;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.frames.FightScreen;
import view.resources.MessageFrame;

public class MethodsImplemented implements MethodsToImplement {

    @Override
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
            Pokemon nextEnemyPokemon, String optionalMessage) {
        List<String> message = new ArrayList<>();
        if (myMoveFirst) {
            message.add(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name() + ": " + myMove);
            message.add(myMoveEffectiveness.name());
            if (enemyMove == null) {
                message.add("ENEMY DEAD");
                if (nextEnemyPokemon != null) {
                    message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokemon().name());
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    FightScreen.showMessage(array);
                } else {
                    message.add("ENEMY DEFEATED");
                    message.add(" Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    FightScreen.dispose();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
                }
            } else {
                message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
                message.add(enemyMoveEffectiveness.name());
                if (lastPokemonKills) {
                    message.add("ALLY DEAD");
                    if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                        Controller.getController().getViewController().team(false, false);
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        FightScreen.showMessage(array);
                    } else {
                        message.add("ALLY DEFEATED");
                        message.add(" Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                        FightScreen.dispose();
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        new MessageFrame(State.WALKING, array);
                    }
                } else {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    FightScreen.showMessage(array);
                }
            }
        } else {
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            message.add(enemyMoveEffectiveness.name());
            if (myMove == null) {
                message.add("ALLY DEAD");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    Controller.getController().getViewController().team(false, false);
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    FightScreen.showMessage(array);
                } else {
                    message.add("ALLY DEFEATED");
                    message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    FightScreen.dispose();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
                }
            } else {
                message.add(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name() + ": " + myMove);
                message.add(myMoveEffectiveness.name());
                if (lastPokemonKills) {
                    message.add("ENEMY DEAD");
                    if (nextEnemyPokemon != null) {
                        message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokemon().name());
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        FightScreen.showMessage(array);
                    } else {
                        message.add("ENEMY DEFEATED");             
                        message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());                        
                        Controller.getController().updateStatus(State.WALKING);
                        FightScreen.dispose();
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        new MessageFrame(State.WALKING, array);
                    }
                } else {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    FightScreen.showMessage(array);
                }
            }
        }
    }

    @Override
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        List<String> message = new ArrayList<>();
        message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
        if (isMyPokemonDead) {
            message.add("ALLY DEAD");
            if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                Controller.getController().getViewController().team(false, false);
                String[] array = new String[message.size()];
                message.toArray(array);
                FightScreen.showMessage(array);
            } else {
                message.add("ALLY DEFEATED");
                message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                FightScreen.dispose();
                String[] array = new String[message.size()];
                message.toArray(array);
                new MessageFrame(State.WALKING, array);
            }
        } else {
            String[] array = new String[message.size()];
            message.toArray(array);
            FightScreen.showMessage(array); 
        }
    }

    @Override
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        List<String> message = new ArrayList<>();
        if (enemyMove != null) {
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                message.add("ALLY DEAD");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    Controller.getController().getViewController().team(false, false);
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    FightScreen.showMessage(array);
                } else {
                    message.add("ALLY DEFEATED");
                    message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    FightScreen.dispose();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
                }
            } else {
                String[] array = new String[message.size()];
                message.toArray(array);
                FightScreen.showMessage(array);
            }
        } else {
            message.add("ENEMY CAUGHT!");
            message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
            FightScreen.dispose();
            String[] array = new String[message.size()];
            message.toArray(array);
            new MessageFrame(State.WALKING, array);
        }
    }

    @Override
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        List<String> message = new ArrayList<>();
        if (success) {
            Controller.getController().updateStatus(State.WALKING);
            FightScreen.dispose();
        } else {
            message.add("RUN FAILED!");
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                message.add("ALLY DEAD");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    Controller.getController().getViewController().team(false, false);
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    FightScreen.showMessage(array);                
                } else {
                    message.add("ALLY DEFEATED");
                    message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    FightScreen.dispose();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
                }
            } else {
                String[] array = new String[message.size()];
                message.toArray(array);
                FightScreen.showMessage(array); 
            }
        }
    }
}