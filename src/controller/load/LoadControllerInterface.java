package controller.load;

import model.map.PokeMap;

public interface LoadControllerInterface {
    
    public void load(final PokeMap map);
    
    public static boolean saveExists() {
        return false;
    }
}