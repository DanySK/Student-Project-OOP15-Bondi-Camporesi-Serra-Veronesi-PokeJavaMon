package controller.keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import controller.load.LoadController;
import controller.modelResources.General;
import controller.modelResources.Player;
import controller.save.SaveController;
import model.box.Box;
import model.box.BoxImpl;
import model.inventory.Inventory;
import model.inventory.InventoryImpl;
import model.items.Boost;
import model.items.Pokeball;
import model.items.Potion;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.pokemon.Pokemon;
import model.pokemon.PokemonDB;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;
import model.trainer.TrainerDB;
import model.utilities.Pair;
import view.frames.Menu;

public class WalkingController implements KeyboardControllerInterface {

    private boolean active = false;
    private Vector2 velocity = new Vector2();
    private Player pl;
    
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                if(active) {
                    velocity.y = pl.getSpeed();
                    velocity.x = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.A:
            case Keys.LEFT:
                if(active) {
                    velocity.x = -pl.getSpeed();
                    velocity.y = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.D:
            case Keys.RIGHT:
                if(active) {
                    velocity.x = pl.getSpeed();
                    velocity.y = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.S:
            case Keys.DOWN:
                if(active) {
                    velocity.y = -pl.getSpeed();
                    velocity.x = 0;
                    pl.move(velocity.x, velocity.y);
                    break;
                }
                break;
            case Keys.ENTER:
                if(active) {
                	new Menu();
                    break;
                }
                break;    
        }
        return true;
    }

    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.UP:
                if(active) {
                    pl.stop();
                    break;
                }
                break;
            case Keys.A:
            case Keys.LEFT:
                if(active) {
                    pl.stop();
                    break;
                }
                break;
            case Keys.D:
            case Keys.RIGHT:
                if(active) {
                    pl.stop();
                    break;
                }
                break;
            case Keys.S:
            case Keys.DOWN:
                if(active) {
                    pl.stop();
                    break;
                }
                break;
            case Keys.ENTER:
                if(active) {
                    active = false;
                    pl.stop();
                    break;
                } else {
                    active = true;
                }
                break;
            case Keys.SPACE:
                if(active) {
                    SaveController sc = new SaveController();
                    LoadController lc = new LoadController();
                    General g;
                    if (lc.saveExists()) {
                        g = lc.load();
                    } else {
                        List<Pokemon> team = new ArrayList<>();
                        team.add(StaticPokemonFactory.createPokemon(PokemonDB.BLASTOISE,30));
                        Box bx = BoxImpl.getBox();
                        bx.putCapturedPokemon(StaticPokemonFactory.createPokemon(PokemonDB.CHARIZARD,30));
                        bx.putCapturedPokemon(StaticPokemonFactory.createPokemon(PokemonDB.VENUSAUR,30));
                        List<Trainer> tr = new ArrayList<>();
                        tr.add(StaticTrainerFactory.createTrainer(TrainerDB.DR_GHAIN.name(), false));
                        Map<Pokeball, Integer> balls = new HashMap<>();
                        Map<Boost, Integer> boosts = new HashMap<>();
                        Map<Potion, Integer> potions = new HashMap<>();
                        balls.put(new Pokeball(PokeballType.Ultraball),10);
                        boosts.put(new Boost(Stat.ATK), 5);
                        potions.put(new Potion(PotionType.SUPERPOTION), 15);
                        Inventory i = InventoryImpl.getInventory();
                        i.setBoosts(boosts);
                        i.setPokeballs(balls);
                        i.setPotions(potions);
                        g = new General(team,bx,tr,i,500,new Pair<Float, Float>(672f, 2272f));
                    }
                    g.setPosition(new Pair<Float, Float>(pl.getX(),pl.getY()));
                    sc.save(g);
                    JOptionPane.showMessageDialog(null, "Salvataggio riuscito!");
                }
                break;
        }
        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }
    
    public void start() {
        this.active = true;
    }
    
    public void stop() {
        this.active = false;
    }
    
    public void setPlayer(Player p) {
        this.pl = p;
    }
    
    public String toString() {
        return "Active: " + active;
    }
}