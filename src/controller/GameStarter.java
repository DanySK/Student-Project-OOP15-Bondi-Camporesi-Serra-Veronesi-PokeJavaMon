package controller;

import controller.installer.Installer;
import controller.parameters.State;
import controller.status.StatusController;
import controller.view.ViewController;
import model.pokemon.InitializeMoves;

/**
 * This class starts the game
 */
public class GameStarter {

    public static void main(String... varargs) {
        new Installer().install();
        StatusController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
    }
}
