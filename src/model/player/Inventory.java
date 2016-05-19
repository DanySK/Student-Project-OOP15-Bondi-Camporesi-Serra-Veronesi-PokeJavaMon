package model.player;

import java.util.Map;

import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Potion;

/**
 * Interface that describes a {@link Player}'s "bag" where he stores all the different kinds of {@link Item}s
 * Internally is divided by sub-invetories for each kind of {@link ItemType}
 * @see Item
 * @see Boost
 * @see Pokeball
 * @see Pokeball
 * @see Player
 */
public interface Inventory {
    
	/**
	 * Gives a sub-{@link Map}<{@link Item}, {@link Integer}> (where the {@link Integer} is the quantity) based on the {@link ItemType} specified in the argument
	 * @param type
	 * @return a Map filled with all the {@link Item} of a specified {@link ItemType}
	 */
    public Map<Item, Integer> getSubInventory(final Item.ItemType type);
    
    /**
     * Adds an {@link Item} to the Inventory
     * @param item
     */
    public void addItem(final Item item);

    /**
     * Uses an {@link Item} and
     * @param item
     */
    public void consumeItem(final Item item);
    
    public void setPokeballs(final Map<Pokeball, Integer> balls);
    
    public void setBoosts(final Map<Boost, Integer> boosts);
    
    public void setPotions(final Map<Potion, Integer> potions);
    
    public void initializeInventory(final Map<String, Integer> potionList, final Map<String, Integer> boostList, final Map<String, Integer> ballList);
    	
}
