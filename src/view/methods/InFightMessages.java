
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
import view.View;
import view.frames.FightScreen;
import view.frames.LearnMoveFrame;
import view.frames.MessageFrame;
import view.frames.MyFrame;
import view.sprite.PlayerSprite;

public class InFightMessages implements InFightMessagesInterface {

    public void teleportToCenter() {
        final int x = Controller.getController().getPokeMap().getPokemonCenterSpawnPosition().getX();
        final int y = Controller.getController().getPokeMap().getPokemonCenterSpawnPosition().getY();
        PlayerSprite.getSprite().setPlayerPosition(x, y);
        PlayerSprite.getSprite().setVelocity(0, 0);
        PlayerImpl.getPlayer().setPosition(x, y);
        PlayerImpl.getPlayer().getSquad().healAllPokemon(Controller.getController().getPokeMap());
    }
    
    @Override
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
            Pokemon nextEnemyPokemon, String optionalMessage, final Move moveToLearn) {
        List<String> message = new ArrayList<>();
        if (myMoveFirst) {
            message.add(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name() + ": " + myMove);
            message.add(myMoveEffectiveness.getMessage());
            if (enemyMove == null) {
                message.add("Enemy pokemon is exhausted");
                message.add(optionalMessage);
                if (nextEnemyPokemon != null) {
                    message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokemon().name());                                     
                    if (moveToLearn != Move.NULLMOVE) {
                        if (Controller.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                            String[] array = new String[message.size()];
                            message.toArray(array);
                            MyFrame fs = View.getView().getCurrent();
                            ((FightScreen) fs).showMessage(array);
                            View.getView().hideCurrent();
                            View.getView().addNew(new LearnMoveFrame(moveToLearn));
                            View.getView().showCurrent();
                        } else {
                            Controller.getController().getPlayer().getSquad().getPokemonList().get(0).learnMove(Move.NULLMOVE, moveToLearn);
                            message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
                            String[] array = new String[message.size()];
                            message.toArray(array);
                            MyFrame fs = View.getView().getCurrent();
                            ((FightScreen) fs).showMessage(array);
                        }
                    } else {
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        MyFrame fs = View.getView().getCurrent();
                        ((FightScreen) fs).showMessage(array);
                    }
                } else {
                    message.add("Enemy defeated");
                    message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());                        
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();                   
                    if (moveToLearn != Move.NULLMOVE) {
                        if (Controller.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                            String[] array = new String[message.size()];
                            message.toArray(array);
                            View.getView().addNew(new MessageFrame(State.WALKING, array));
                            View.getView().showCurrent();
                            View.getView().hideCurrent();
                            View.getView().addNew(new LearnMoveFrame(moveToLearn));
                            View.getView().showCurrent();
                        } else {
                            Controller.getController().getPlayer().getSquad().getPokemonList().get(0).learnMove(Move.NULLMOVE, moveToLearn);
                            message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
                            String[] array = new String[message.size()];
                            message.toArray(array);
                            View.getView().addNew(new MessageFrame(State.WALKING, array));
                            View.getView().showCurrent();
                        }
                    } else {
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        View.getView().addNew(new MessageFrame(State.WALKING, array));
                        View.getView().showCurrent();
                    }
                }
            } else {
                message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
                message.add(enemyMoveEffectiveness.getMessage());
                if (lastPokemonKills) {
                    message.add("Your pokemon is exhausted");
                    if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        MyFrame fs = View.getView().getCurrent();
                        ((FightScreen) fs).showMessage(array);
                    } else {
                        message.add("You lost!");
                        message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        View.getView().addNew(new MessageFrame(State.WALKING, array));
                        View.getView().showCurrent();
                        teleportToCenter();
                    }
                } else {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                }
            }
        } else {
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            message.add(enemyMoveEffectiveness.getMessage());
            if (myMove == null) {
                message.add("Your pokemon is exhausted");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                } else {
                    message.add("You lost!");
                    message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    View.getView().addNew(new MessageFrame(State.WALKING, array));
                    View.getView().showCurrent();
                    teleportToCenter();
                }
            } else {
                message.add(PlayerImpl.getPlayer().getSquad().getPokemonList().get(0).getPokemon().name() + ": " + myMove);
                message.add(myMoveEffectiveness.getMessage());
                if (lastPokemonKills) {
                    message.add("Enemy pokemon is exhausted");
                    message.add(optionalMessage);
                    if (nextEnemyPokemon != null) {
                        message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokemon().name());
                        if (moveToLearn != Move.NULLMOVE) {
                            if (Controller.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                                String[] array = new String[message.size()];
                                message.toArray(array);
                                MyFrame fs = View.getView().getCurrent();
                                ((FightScreen) fs).showMessage(array);
                                View.getView().hideCurrent();
                                View.getView().addNew(new LearnMoveFrame(moveToLearn));
                                View.getView().showCurrent();
                            } else {
                                Controller.getController().getPlayer().getSquad().getPokemonList().get(0).learnMove(Move.NULLMOVE, moveToLearn);
                                message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
                                String[] array = new String[message.size()];
                                message.toArray(array);
                                MyFrame fs = View.getView().getCurrent();
                                ((FightScreen) fs).showMessage(array);
                            }
                        } else {
                            String[] array = new String[message.size()];
                            message.toArray(array);
                            MyFrame fs = View.getView().getCurrent();
                            ((FightScreen) fs).showMessage(array);
                        }
                    } else {
                        message.add("Enemy defeated!");             
                        message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());                        
                        Controller.getController().updateStatus(State.WALKING);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        if (moveToLearn != Move.NULLMOVE) {
                            if (Controller.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                                String[] array = new String[message.size()];
                                message.toArray(array);
                                View.getView().addNew(new MessageFrame(State.WALKING, array));
                                View.getView().showCurrent();
                                View.getView().hideCurrent();
                                View.getView().addNew(new LearnMoveFrame(moveToLearn));
                                View.getView().showCurrent();
                            } else {
                                Controller.getController().getPlayer().getSquad().getPokemonList().get(0).learnMove(Move.NULLMOVE, moveToLearn);
                                message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
                                String[] array = new String[message.size()];
                                message.toArray(array);
                                View.getView().addNew(new MessageFrame(State.WALKING, array));
                                View.getView().showCurrent();
                            }
                        } else {
                            String[] array = new String[message.size()];
                            message.toArray(array);
                            View.getView().addNew(new MessageFrame(State.WALKING, array));
                            View.getView().showCurrent();
                        }
                    }
                } else {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                }
            }
        }
    }

    @Override
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        List<String> message = new ArrayList<>();
        message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
        if (isMyPokemonDead) {
            message.add("Your pokemon is exhausted");
            if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                String[] array = new String[message.size()];
                message.toArray(array);
                MyFrame fs = View.getView().getCurrent();
                ((FightScreen) fs).showMessage(array);
            } else {
                message.add("You lost!");
                message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
                String[] array = new String[message.size()];
                message.toArray(array);
                View.getView().addNew(new MessageFrame(State.WALKING, array));
                View.getView().showCurrent();
                teleportToCenter();
            }
        } else {
            String[] array = new String[message.size()];
            message.toArray(array);
            MyFrame fs = View.getView().getParent();
            ((FightScreen) fs).showMessage(array); 
        }
    }

    @Override
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        List<String> message = new ArrayList<>();
        if (enemyMove != null) {
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                message.add("Your pokemon is exhausted");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                } else {
                    message.add("You lost!");
                    message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    View.getView().addNew(new MessageFrame(State.WALKING, array));
                    View.getView().showCurrent();
                    teleportToCenter();
                }
            } else {
                String[] array = new String[message.size()];
                message.toArray(array);
                View.getView().resumeCurrent();
                MyFrame fs = View.getView().getCurrent();
                ((FightScreen) fs).showMessage(array);
            }
        } else {
            message.add("Pokemon caught!!");
            message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
            String[] array = new String[message.size()];
            message.toArray(array);
            View.getView().addNew(new MessageFrame(State.WALKING, array));
            View.getView().showCurrent();
        }
    }

    @Override
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        List<String> message = new ArrayList<>();
        if (success) {
            Controller.getController().updateStatus(State.WALKING);
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
        } else {
            message.add("Run failed!");
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                message.add("Your pokemon is exhausted");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);                
                } else {
                    message.add("You lost!");
                    message.add("Evolving Pokemons: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    View.getView().addNew(new MessageFrame(State.WALKING, array));
                    View.getView().showCurrent();
                    teleportToCenter();
                }
            } else {
                String[] array = new String[message.size()];
                message.toArray(array);
                MyFrame fs = View.getView().getCurrent();
                ((FightScreen) fs).showMessage(array); 
            }
        }
    }
}