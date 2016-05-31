package controller.load;

/**
 * This interface shows the methods that can be called on {@link MainLoadController}
 */
public interface LoadController {
    
    /**
     * Load all the requested informations from the save file
     */
    void load();
    
    /**
     * @return true if the save file exists, false otherwise
     */
    boolean saveExists();
}