package controller.keyboard;

/**
 * The {@link KeyboardController} active during a fight.
 */
public class FightingKeyboardController extends AbstractKeyboardController {
    
    private final String name = "FightKeyboardController";
    
    @Override
    public String toString() {
        return this.name;
    }
}