
package view.methods;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import controller.parameters.State;
import model.fight.Effectiveness;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.View;
import view.frames.FightScreen;
import view.frames.LearnMoveFrame;
import view.frames.MessageFrame;
import view.frames.MyFrame;

public class InFightMessages implements InFightMessagesInterface {
    
    private List<String> message;
    private Move moveToLearn;
    
    public InFightMessages() {
        this.message = new ArrayList<>();
    }
    
    @Override
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
            Pokemon nextEnemyPokemon, String optionalMessage, final Move moveToLearn) {
        this.moveToLearn = moveToLearn;
        if (myMoveFirst) {
            this.message.add(MainController.getController().getSquad().getPokemonList().get(0).getPokemon().name() + ": " + myMove);
            this.message.add(myMoveEffectiveness.getMessage());
            if (enemyMove == null) {
                this.message.add("Enemy pokemon is exhausted");
                this.message.add(optionalMessage);
                if (nextEnemyPokemon != null) {
                    this.message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokemon().name());                                     
                    if (moveToLearn != Move.NULLMOVE && !MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                        if (MainController.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                            this.newMoveMessage();
                        } else {
                            this.learnedMessage();
                        }
                    } else {
                        this.showMessage();
                    }
                } else {
                    this.message.add("Enemy defeated");
                    this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());                        
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();                   
                    if (moveToLearn != Move.NULLMOVE && !MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                        if (MainController.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                            this.winningMessage();
                        } else {
                            this.learnedMoveAndWalking();
                        }
                    } else {
                        String[] array = new String[this.message.size()];
                        this.message.toArray(array);
                        View.getView().addNew(new MessageFrame(State.WALKING, array));
                        View.getView().showCurrent();
                    }
                }
            } else {
                this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
                this.message.add(enemyMoveEffectiveness.getMessage());
                if (lastPokemonKills) {
                    this.message.add("Your pokemon is exhausted");
                    if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                        showMessage();
                    } else {
                        lostMessage();
                    }
                } else {
                    showMessage();
                }
            }
        } else {
            this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            this.message.add(enemyMoveEffectiveness.getMessage());
            if (myMove == null) {
                this.message.add("Your pokemon is exhausted");
                if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                    showMessage();
                } else {
                    lostMessage();
                }
            } else {
                this.message.add(MainController.getController().getSquad().getPokemonList().get(0).getPokemon().name() + ": " + myMove);
                this.message.add(myMoveEffectiveness.getMessage());
                if (lastPokemonKills) {
                    this.message.add("Enemy pokemon is exhausted");
                    this.message.add(optionalMessage);
                    if (nextEnemyPokemon != null) {
                        this.message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokemon().name());
                        if (moveToLearn != Move.NULLMOVE && !MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                            if (MainController.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                                newMoveMessage();
                            } else {
                                learnedMessage();
                            }
                        } else {
                            showMessage();
                        }
                    } else {
                        this.message.add("Enemy defeated!");             
                        this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());                        
                        MainController.getController().updateStatus(State.WALKING);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        if (moveToLearn != Move.NULLMOVE && !MainController.getController().getPlayer().getSquad().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                            if (MainController.getController().getPlayer().getSquad().getPokemonList().get(0).isCurrentMovesetFull()) {
                                winningMessage();
                            } else {
                                learnedMoveAndWalking();
                            }
                        } else {
                            String[] array = new String[this.message.size()];
                            this.message.toArray(array);
                            View.getView().addNew(new MessageFrame(State.WALKING, array));
                            View.getView().showCurrent();
                        }
                    }
                } else {
                    showMessage();
                }
            }
        }
    }

    @Override
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, boolean isMyPokemonDead) {
        this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
        if (isMyPokemonDead) {
            this.message.add("Your pokemon is exhausted");
            if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                showMessage();
            } else {
                lostMessage();
            }
        } else {
            showMessage(); 
        }
    }

    @Override
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead) {
        if (enemyMove != null) {
            this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                this.message.add("Your pokemon is exhausted");
                if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                    showMessage();
                } else {
                    lostMessage();
                }
            } else {
                String[] array = new String[this.message.size()];
                this.message.toArray(array);
                View.getView().resumeCurrent();
                MyFrame fs = View.getView().getCurrent();
                ((FightScreen) fs).showMessage(array);
            }
        } else {
            this.message.add("Pokemon caught!!");
            this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
            String[] array = new String[this.message.size()];
            this.message.toArray(array);
            View.getView().addNew(new MessageFrame(State.WALKING, array));
            View.getView().showCurrent();
        }
    }

    @Override
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead) {
        if (success) {
            MainController.getController().updateStatus(State.WALKING);
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
        } else {
            this.message.add("Run failed!");
            this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                this.message.add("Your pokemon is exhausted");
                if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                    showMessage();                
                } else {
                    lostMessage();
                }
            } else {
                showMessage();
            }
        }
    }
    
    // PRIVATE METHODS
    
    private void showMessage() {
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        MyFrame fs = View.getView().getCurrent();
        ((FightScreen) fs).showMessage(array); 
    }
    
    private void learnedMessage() {
        MainController.getController().getPlayer().getSquad().getPokemonList().get(0).learnMove(Move.NULLMOVE, this.moveToLearn);
        this.message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        MyFrame fs = View.getView().getCurrent();
        ((FightScreen) fs).showMessage(array);
    }
    
    private void lostMessage() {
        this.message.add("You lost!");
        this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());
        View.getView().disposeCurrent();
        View.getView().removeCurrent();
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        View.getView().addNew(new MessageFrame(State.WALKING, array));
        View.getView().showCurrent();
        MainController.getController().teleportToCenter();
    }
    
    private void learnedMoveAndWalking() {
        MainController.getController().getPlayer().getSquad().getPokemonList().get(0).learnMove(Move.NULLMOVE, this.moveToLearn);
        this.message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        View.getView().addNew(new MessageFrame(State.WALKING, array));
        View.getView().showCurrent();
    }
    
    private void newMoveMessage() {
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        MyFrame fs = View.getView().getCurrent();
        ((FightScreen) fs).showMessage(array);
        View.getView().hideCurrent();
        View.getView().addNew(new LearnMoveFrame(this.moveToLearn));
        View.getView().showCurrent();
    }
    
    private void winningMessage() {
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        View.getView().addNew(new MessageFrame(State.WALKING, array));
        View.getView().showCurrent();
        View.getView().hideCurrent();
        View.getView().addNew(new LearnMoveFrame(this.moveToLearn));
        View.getView().showCurrent();
    }
}