package controller;

import controller.installer.Installer;
import controller.parameters.State;

/**
 * This class starts the game
 */
public final class GameStarter {

    private GameStarter() {}
    
    public static void main(final String... varargs) {
        new Installer();
        MainController.getController().updateStatus(State.FIRST_MENU);
        MainController.getController().getViewController().firstMenu();
    }
}
