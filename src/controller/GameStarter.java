package controller;

import controller.installer.MainInstaller;
import controller.parameters.State;

/**
 * This class starts the game
 */
public final class GameStarter {

    private GameStarter() {}
    
    public static void main(final String... varargs) {
        new MainInstaller().install();
        MainController.getController().updateStatus(State.FIRST_MENU);
        MainController.getController().getViewController().firstMenu();
    }
}
