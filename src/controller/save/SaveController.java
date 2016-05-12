package controller.save;

import model.ModelInterface.Save;

/**
 * This interface shows the methods that can be called on {@link MainSaveController}
 */
public interface SaveController {
    
    /**
     * Initializes the {@link Save}
     * @param save the current {@link Save}
     */
    void setSave(Save s);
    
    /**
     * Saves all the necessaries information on a file on disk
     */
    void save();
}