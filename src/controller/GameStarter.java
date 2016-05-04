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
        Controller.getController().initializeMainController();
        Controller.getController().updateStatus(State.FIRST_MENU);
        InitializeMoves.initAllPokemonsTypes();
        Controller.getController().getViewController().firstMenu();
    }
}
