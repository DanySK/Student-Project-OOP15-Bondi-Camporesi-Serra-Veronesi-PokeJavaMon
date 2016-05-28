package controller.keyboard;

/**
 * The {@link KeyboardController} active for the menu.
 */
public class MenuKeyboardController extends AbstractKeyboardController {
    
    private final String name = "MenuKeyboardController";
    
    public MenuKeyboardController() {
        // EMPTY CONSTRUCTORs
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}