package controller;

import controller.installer.Installer;
import controller.main.MainController;
import controller.parameters.State;
import controller.view.ViewController;
import model.pokemon.InitializeMoves;

public class GameStarter {

    /**
     * @author Michael Camporesi
     * @version 1.0
     * Creates a new instance of the class Installer, then calls its install method to
     * install in the user home folder all the necessaries file.
     * Then updates the status of the MainController to FIRST_MENU.
     * Finally initializes all pokemon moves then calls firstMenu method of ViewController
     * to show the first menu of the game.
     * @see Installer
     * @see MainController
     * @see InitializeMoves
     * @see ViewController
     */
    public static void main(String[] args) {
        new Installer().install();
        MainController.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        ViewController.getController().firstMenu();
    }
}
