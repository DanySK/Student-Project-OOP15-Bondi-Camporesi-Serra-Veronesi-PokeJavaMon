package model.items;

/**
 * Interface of a usable and disposable "object" that can be used on {@link Pokemon}
 */
public interface Item {
    
	/**
	 * Describes when you can use an {@link Item}
	 */
    public enum WhenToUse {
    	BATTLE, OUT_OF_BATTLE, EVERYWHERE;
    }
    
    /**
     * All current available types of {@link Item}, 
     */
    public enum ItemType {
        POKEBALL, BOOST, POTION;
    }
    
    /**
     * @return the price of this {@link Item}
     */
    int getPrice();
    
    /**
     * @return {@link ItemType} of this {@link Item}
     */
    ItemType getType();

    /**
     * @return true if you can use it on an enemy {@link Pokemon}
     */
    boolean isOnEnemy();
    
    /**
     * @return {@link WhenToUse} 
     */
    WhenToUse whenToUse();
}
