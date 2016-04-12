package model.items;

public interface Item {
    
    public static enum whenToUse {
        BATTLE, OUT_OF_BATTLE, EVERYWHERE;
    }
    
    public static enum ItemType {
        POKEBALL, BOOST, POTION;
    }
    
    public int getPrice();
    public ItemType getType();
    public boolean isOnEnemy();
    public whenToUse whenToUse();
}
