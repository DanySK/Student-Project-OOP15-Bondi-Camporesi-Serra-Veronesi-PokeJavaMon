package model;

import model.map.PokeMap;
import model.player.Player;

public interface Model {
    
    public static enum State {
        WALK, BATTLE, MENU;
    }
    
    public Player getPlayer();
    
    public PokeMap getMap();
    
    public State getState();
    
    public void setState();
}
