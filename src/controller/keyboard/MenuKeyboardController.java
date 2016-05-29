package controller.keyboard;

/**
 * The {@link KeyboardController} active for the menu.
 */
public class MenuKeyboardController extends AbstractKeyboardController {
    
    private final String name;
    
    public MenuKeyboardController() {
        this.name = "MenuKeyboardController";
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}