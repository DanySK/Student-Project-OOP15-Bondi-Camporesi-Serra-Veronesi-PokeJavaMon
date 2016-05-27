package model.items;

public interface Item {
    
    public static enum whenToUse {
        BATTLE, OUT_OF_BATTLE, EVERYWHERE;
    }
    
    public static enum ItemType {
        POKEBALL, BOOST, POTION;
    }
    
    int getPrice();
    ItemType getType();
    boolean isOnEnemy();
    whenToUse whenToUse();
}
