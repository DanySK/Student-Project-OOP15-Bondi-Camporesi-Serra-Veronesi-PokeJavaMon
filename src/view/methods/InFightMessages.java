package view.methods;

import java.util.ArrayList;
import java.util.List;import com.badlogic.gdx.maps.tiled.TiledMap;

import controller.Controller;
import controller.parameters.State;
import model.fight.Effectiveness;
import model.items.Item;
import model.map.Position;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import view.View;
import view.frames.FightScreen;
import view.frames.MessageFrame;
import view.frames.MyFrame;
import view.resources.MainGameView;
import view.sprite.PlayerSprite;

public class InFightMessages implements InFightMessagesInterface {

    
    public void teleportToCenter() {
        final int x = MainGameView.getMapImpl().getPokemonCenterSpawnPosition().getX();
        final int y = MainGameView.getMapImpl().getPokemonCenterSpawnPosition().getY();
        PlayerSprite.getSprite().setPlayerPosition(x, y);
        PlayerSprite.getSprite().setVelocity(0, 0);
        PlayerImpl.getPlayer().setPosition(x, y);
        PlayerImpl.getPlayer().getSquad().healAllPokemon(MainGameView.getMapImpl());
    }
    
    @Override
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst, boolean lastPokemonKills,
            Pokemon nextEnemyPokemon, String optionalMessage, final Move moveToLearn) {
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
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                } else {
                    message.add("ENEMY DEFEATED");
                    message.add(" Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
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
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        MyFrame fs = View.getView().getCurrent();
                        ((FightScreen) fs).showMessage(array);
                    } else {
                        message.add("ALLY DEFEATED");
                        message.add(" Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        new MessageFrame(State.WALKING, array);
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
            message.add(enemyMoveEffectiveness.name());
            if (myMove == null) {
                message.add("ALLY DEAD");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                } else {
                    message.add("ALLY DEFEATED");
                    message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
                    teleportToCenter();
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
                        MyFrame fs = View.getView().getCurrent();
                        ((FightScreen) fs).showMessage(array);
                    } else {
                        message.add("ENEMY DEFEATED");             
                        message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());                        
                        Controller.getController().updateStatus(State.WALKING);
                        View.getView().disposeCurrent();
                        View.getView().removeCurrent();
                        String[] array = new String[message.size()];
                        message.toArray(array);
                        new MessageFrame(State.WALKING, array);
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
            message.add("ALLY DEAD");
            if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                String[] array = new String[message.size()];
                message.toArray(array);
                MyFrame fs = View.getView().getCurrent();
                ((FightScreen) fs).showMessage(array);
            } else {
                message.add("ALLY DEFEATED");
                message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
                String[] array = new String[message.size()];
                message.toArray(array);
                new MessageFrame(State.WALKING, array);
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
                message.add("ALLY DEAD");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);
                } else {
                    message.add("ALLY DEFEATED");
                    message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
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
            message.add("ENEMY CAUGHT!");
            message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
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
            View.getView().disposeCurrent();
            View.getView().removeCurrent();
        } else {
            message.add("RUN FAILED!");
            message.add("Enemy " + Controller.getController().getEnemyPokemonInFight().getPokemon().name() + ": " + enemyMove);
            if (isMyPokemonDead) {
                message.add("ALLY DEAD");
                if (PlayerImpl.getPlayer().getSquad().getNextAlivePokemon().isPresent()) {
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    MyFrame fs = View.getView().getCurrent();
                    ((FightScreen) fs).showMessage(array);                
                } else {
                    message.add("ALLY DEFEATED");
                    message.add("Evolving Pokemon: " + Controller.getController().getFightController().resolveEvolution());
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    String[] array = new String[message.size()];
                    message.toArray(array);
                    new MessageFrame(State.WALKING, array);
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