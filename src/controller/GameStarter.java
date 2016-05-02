package controller;

import controller.installer.Installer;
import controller.main.MainController;
import controller.parameters.State;
import controller.view.ViewController;
import model.pokemon.InitializeMoves;

/**
 * This class starts the game
 */
public class GameStarter {

    public static void main(String[] args) {
        new Installer().install();
        MainController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
    }
}
