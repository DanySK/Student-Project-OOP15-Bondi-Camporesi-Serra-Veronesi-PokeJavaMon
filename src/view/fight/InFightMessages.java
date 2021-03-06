
package view.fight;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import controller.parameters.State;
import model.fight.Effectiveness;
import model.items.Item;
import model.items.Item.ItemType;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.View;
import view.windows.MessageFrame;
import view.windows.MyFrame;
/**
 * This class handles all the messages that can appear in the game in any situation.
 */
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
        this.message = new ArrayList<>();
        this.moveToLearn = moveToLearn;
        if (myMoveFirst) {
            this.message.add(MainController.getController().getSquad().get().getPokemonList().get(0).getPokedexEntry().getName() + ": " + myMove);
            this.message.add(myMoveEffectiveness.getMessage());
            if (enemyMove == null) {
                this.message.add("Enemy pokemon is exhausted");
                this.message.add(optionalMessage);
                if (nextEnemyPokemon != null) {
                    this.message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokedexEntry().getName());                                     
                    if (moveToLearn != Move.NULLMOVE && !MainController.getController().getSquad().get().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                        if (MainController.getController().getSquad().get().getPokemonList().get(0).isCurrentMovesetFull()) {
                            this.newMoveMessage();
                        } else {
                            this.learnedMessage();
                        }
                    } else {
                        this.showMessage();
                    }
                } else {
                    MainController.getController().checkLegendaryAndDelete();
                    this.message.add("Enemy defeated");
                    if (MainController.getController().getFightController().getEnemyTrainer().isPresent()) {
                        this.message.add(MainController.getController().getFightController().getEnemyTrainer().get().getName() + ": " + MainController.getController().getFightController().getEnemyTrainer().get().getTtrainerLostMessage());
                    }
                    this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());                        
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();                   
                    if (moveToLearn != Move.NULLMOVE && !MainController.getController().getSquad().get().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                        if (MainController.getController().getSquad().get().getPokemonList().get(0).isCurrentMovesetFull()) {
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
                this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().get().getPokedexEntry().getName() + ": " + enemyMove);
                this.message.add(enemyMoveEffectiveness.getMessage());
                if (lastPokemonKills) {
                    this.message.add("Your pokemon is exhausted");
                    if (MainController.getController().getSquad().get().getNextAlivePokemon().isPresent()) {
                        showMessage();
                    } else {
                        lostMessage();
                    }
                } else {
                    showMessage();
                }
            }
        } else {
            this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().get().getPokedexEntry().getName() + ": " + enemyMove);
            this.message.add(enemyMoveEffectiveness.getMessage());
            if (myMove == null) {
                this.message.add("Your pokemon is exhausted");
                if (MainController.getController().getSquad().get().getNextAlivePokemon().isPresent()) {
                    showMessage();
                } else {
                    lostMessage();
                }
            } else {
                this.message.add(MainController.getController().getSquad().get().getPokemonList().get(0).getPokedexEntry().getName() + ": " + myMove);
                this.message.add(myMoveEffectiveness.getMessage());
                if (lastPokemonKills) {
                    this.message.add("Enemy pokemon is exhausted");
                    this.message.add(optionalMessage);
                    if (nextEnemyPokemon != null) {
                        this.message.add("Next enemy pokemon: " + nextEnemyPokemon.getPokedexEntry().getName());
                        if (moveToLearn != Move.NULLMOVE && !MainController.getController().getSquad().get().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                            if (MainController.getController().getSquad().get().getPokemonList().get(0).isCurrentMovesetFull()) {
                                newMoveMessage();
                            } else {
                                learnedMessage();
                            }
                        } else {
                            showMessage();
                        }
                    } else {
                        MainController.getController().checkLegendaryAndDelete();
                        this.message.add("Enemy defeated!");    
                        if (MainController.getController().getFightController().getEnemyTrainer().isPresent()) {
                            this.message.add(MainController.getController().getFightController().getEnemyTrainer().get().getName() + ": " + MainController.getController().getFightController().getEnemyTrainer().get().getTtrainerLostMessage());
                        }
                        this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());                        
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        if (moveToLearn != Move.NULLMOVE && !MainController.getController().getSquad().get().getPokemonList().get(0).getCurrentMoves().contains(moveToLearn)) {
                            if (MainController.getController().getSquad().get().getPokemonList().get(0).isCurrentMovesetFull()) {
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
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead) {
        this.message = new ArrayList<>();
        this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().get().getPokedexEntry().getName() + ": " + enemyMove);
        this.message.add(eff.getMessage());
        if (isMyPokemonDead) {
            this.message.add("Your pokemon is exhausted");
            if (MainController.getController().getSquad().get().getNextAlivePokemon().isPresent()) {
                showMessage();
            } else {
                lostMessage();
            }
        } else {
            showMessage(); 
        }
    }

    @Override
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead) {
        this.message = new ArrayList<>();
        if (enemyMove != null) {
            if (item.getType() == ItemType.POKEBALL) {
                this.message.add("He escaped!");
            }
            this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().get().getPokedexEntry().getName() + ": " + enemyMove);
            this.message.add(eff.getMessage());
            if (isMyPokemonDead) {
                this.message.add("Your pokemon is exhausted");
                if (MainController.getController().getSquad().get().getNextAlivePokemon().isPresent()) {
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
            MainController.getController().checkLegendaryAndDelete();
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
    public void resolveRun(boolean success, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead) {
        this.message = new ArrayList<>();
        if (success) {
            MainController.getController().checkLegendaryAndDelete();
            MainController.getController().updateStatus(State.WALKING);
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
        } else {
            this.message.add("Run failed!");
            this.message.add("Enemy " + MainController.getController().getEnemyPokemonInFight().get().getPokedexEntry().getName() + ": " + enemyMove);
            this.message.add(eff.getMessage());
            if (isMyPokemonDead) {
                this.message.add("Your pokemon is exhausted");
                if (MainController.getController().getSquad().get().getNextAlivePokemon().isPresent()) {
                    showMessage();                
                } else {
                    lostMessage();
                }
            } else {
                showMessage();
            }
        }
    }
	
    /**
     * Shows the default message
     */
    private void showMessage() {
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        MyFrame fs = View.getView().getCurrent();
        ((FightScreen) fs).showMessage(array); 
    }
	/**
	 * It is the message displayed when a pok�mon learns a new move.
	 */
    private void learnedMessage() {
        MainController.getController().getSquad().get().getPokemonList().get(0).learnMove(Move.NULLMOVE, this.moveToLearn);
        this.message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        MyFrame fs = View.getView().getCurrent();
        ((FightScreen) fs).showMessage(array);
    }
	/**
	 * It is the message displayed when the player loses a battle.
	 */
    private void lostMessage() {
        MainController.getController().getFightController().healEnemy();
        this.message.add("You lost!");
        if (MainController.getController().getFightController().getEnemyTrainer().isPresent()) {
            this.message.add(MainController.getController().getFightController().getEnemyTrainer().get().getName() + ": " + MainController.getController().getFightController().getEnemyTrainer().get().getTrainerWonMessageMessage());
        }
        this.message.add("Evolving Pokemons: " + MainController.getController().getFightController().resolveEvolution());
        View.getView().disposeCurrent();
        View.getView().removeCurrent();
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        View.getView().addNew(new MessageFrame(State.WALKING, array));
        View.getView().showCurrent();
        MainController.getController().teleportToCenter();
    }
	/**
	 * It is the message displayed when a pok�mon learns a new move and then it puts the state of the player in "WALKING".
	 */
    private void learnedMoveAndWalking() {
        MainController.getController().getSquad().get().getPokemonList().get(0).learnMove(Move.NULLMOVE, this.moveToLearn);
        this.message.add("Your pokemon learned the move: " + moveToLearn.name() + "!");  
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        View.getView().addNew(new MessageFrame(State.WALKING, array));
        View.getView().showCurrent();
    }
	/**
	 * Shows a new {@link LearnMoveFrame}
	 */
    private void newMoveMessage() {
        String[] array = new String[this.message.size()];
        this.message.toArray(array);
        MyFrame fs = View.getView().getCurrent();
        ((FightScreen) fs).showMessage(array);
        View.getView().hideCurrent();
        View.getView().addNew(new LearnMoveFrame(this.moveToLearn));
        View.getView().showCurrent();
    }
	/**
	 *  It is the message displayed when the player wins a battle.
	 */
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