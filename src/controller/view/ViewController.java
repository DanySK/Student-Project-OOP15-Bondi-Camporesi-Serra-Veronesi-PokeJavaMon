package controller.view;

import model.pokemon.Pokemon;

/**
 * This interface shows the methods that can be called on {@link MainViewController}
 */
public interface ViewController {

    /**
     * Shows the pokemon market menu
     */
    void market();

    /**
     * Shows the main menu
     */
    void showMenu();

    /**
     * Shows the first menu
     */
    void firstMenu();

    /**
     * Shows the second menu
     */
    void secondMenu();

    /**
     * Shows the game map
     * @param newGame if it is a new game or not
     */
    void map(boolean newGame);

    /**
     * Saves the game
     */
    void save();
    
    /**
     * Shows the box menu
     */
    void box();
    
    /**
     * Shows the team menu
     * @param canCloseMenu if player can close the menu or not
     * @param canChangePokemon true if player can change pokemon to that pokemon or 
     * false if can select that pokemon to use an item on
     */
    void team(final boolean canCloseMenu, final boolean canChangePokemon);
    
    /**
     * Shows the bag menu
     */
    void bag();

    /**
     * Shows the stat menu
     * @param pokemon the {@link Pokemon} to show stats of
     */
    void stats(Pokemon pokemon);

    /**
     * Set player's name
     * @param name the selected name
     */
    void setName(String name);
    
    /**
     * Initializes player's name
     */
    void initName();
    
    /**
     * Shows the fight menu
     */
    void fightScreen();
}