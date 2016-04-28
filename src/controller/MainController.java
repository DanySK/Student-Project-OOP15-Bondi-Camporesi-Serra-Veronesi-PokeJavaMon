package controller;

import java.util.HashMap;
import java.util.Map;

import controller.keyboard.FightingKeyboardController;
import controller.keyboard.FirstMenuKeyboardController;
import controller.keyboard.KeyboardController;
import controller.keyboard.MenuKeyboardController;
import controller.keyboard.SecondMenuKeyboardController;
import controller.keyboard.WalkingKeyboardController;
import controller.music.MainMusicController;
import controller.parameters.Directions;
import controller.parameters.Music;
import controller.parameters.State;
import exceptions.SquadFullException;
import model.inventory.InventoryImpl;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.player.PlayerImpl;
import model.pokemon.InitializeMoves;
import model.pokemon.PokemonDB;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import view.resources.Play;

public class MainController {
    
    private State state;
    private KeyboardController keyboardController;
    private static MainController SINGLETON; 
    
    private MainController() {}
    
    public static MainController getController() {
        if (SINGLETON == null) {
            synchronized (MainController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new MainController();
                }
            }
        }
        return SINGLETON;
    }

    public void updateStatus(State s) {
        state = s;
        switch (s) {
            case FIRST_MENU:
                keyboardController = FirstMenuKeyboardController.getController();
                break;
            case SECOND_MENU:
                keyboardController = SecondMenuKeyboardController.getController();
                break;
            case WALKING:
                if (MainMusicController.getController().playing() == null) {
                    MainMusicController.getController().play(Music.TOWN);
                } else {
                    if (MainMusicController.getController().playing() != Music.TOWN) {
                        MainMusicController.getController().stop();
                        MainMusicController.getController().play(Music.TOWN);
                    }
                }
                keyboardController = WalkingKeyboardController.getController(); 
                Play.updateKeyListener();
                break;
            case MENU:
                keyboardController = MenuKeyboardController.getController();
                Play.updateKeyListener();
                break;
            case FIGHTING:
                if (MainMusicController.getController().playing() == null) {
                    MainMusicController.getController().play(Music.TRAINER);
                } else {
                    if (MainMusicController.getController().playing() != Music.TRAINER) {
                        MainMusicController.getController().stop();
                        MainMusicController.getController().play(Music.TRAINER);
                    }
                }
                keyboardController = FightingKeyboardController.getController();
                Play.updateKeyListener();
                break;
            case READING:
                keyboardController = MenuKeyboardController.getController();
                Play.updateKeyListener();
                break;
        }
    }
    
    public State getState() {
        return state;
    }
    
    public boolean isKeyPressed() {
        return keyboardController.isKeyPressed();
    }

    public KeyboardController getCurrentController() {
        return keyboardController;
    }
    
    public void updateSpeed() {
        keyboardController.updateSpeed();
    }
    
    public Directions getDirection() {
        return keyboardController.getDirection();
    }
    
    public static void main(String[] args) {
        new Installer().install();
        MainController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
        try {
            PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(PokemonDB.SQUIRTLE, 5));
            PlayerImpl.getPlayer().getSquad().add(StaticPokemonFactory.createPokemon(PokemonDB.CHARMANDER, 5));
        } catch (SquadFullException e) {
            e.printStackTrace();
        }
        Map<String, Integer> potionList = new HashMap<>();
        Map<String, Integer> boostList = new HashMap<>();
        Map<String, Integer> ballList = new HashMap<>();
        potionList.put(PotionType.POTION.name(), 5);
        boostList.put(Stat.ATK.name() + "X", 5);
        ballList.put(PokeballType.Pokeball.name(), 5);
        InventoryImpl.initializeInventory(potionList, boostList, ballList);
    }
}