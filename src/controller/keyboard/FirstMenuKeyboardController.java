package controller.keyboard;

import com.badlogic.gdx.Input.Keys;

import controller.MainController;
import controller.parameters.State;

/**
 * The {@link KeyboardController} active for the first menu.
 */
public class FirstMenuKeyboardController extends AbstractKeyboardController {
    
    @Override
    public boolean keyUp(final int keycode) {
        switch(keycode) {
        case Keys.N:
            MainController.getController().getViewController().secondMenu();
            MainController.getController().updateStatus(State.SECOND_MENU);
            break;
        case Keys.C:
            MainController.getController().getViewController().map(false);
            MainController.getController().updateStatus(State.WALKING);
            break;
        default:
            break;
        }
        return false;
    }
}