package controller.load;

import model.map.PokeMap;

/**
 * This interface shows the methods that can be called on {@link LoadController}
 */
public interface LoadControllerInterface {
    
    /**
     * Load all the requested informations from the save file
     * @param map the current {@link PokeMap} 
     */
    public void load(final PokeMap map);
    
    /**
     * @return true if the save file exists, false otherwise
     */
    public boolean saveExists();
}