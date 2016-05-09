package controller;

import controller.installer.Installer;
import controller.parameters.State;
import model.pokemon.InitializeMoves;

/**
 * This class starts the game
 */
public final class GameStarter {

    private GameStarter() {}
    
    public static void main(final String... varargs) {
        new Installer().install();
        InitializeMoves.initAllPokemonsTypes();
        Controller.getController().updateStatus(State.FIRST_MENU);
        Controller.getController().getViewController().firstMenu();
    }
}
