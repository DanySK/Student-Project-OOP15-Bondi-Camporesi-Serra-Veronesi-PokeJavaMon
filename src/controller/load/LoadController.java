package controller.load;

import model.map.PokeMap;

/**
 * This interface shows the methods that can be called on {@link MainLoadController}
 */
public interface LoadController {
    
    /**
     * Load all the requested informations from the save file
     * @param map the current {@link PokeMap} 
     */
    void load();
    
    /**
     * @return true if the save file exists, false otherwise
     */
    boolean saveExists();
}