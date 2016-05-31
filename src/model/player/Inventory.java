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
	 * Gives a sub-{@link Map}({@link Item}, {@link Integer}) (where the {@link Integer} is the quantity) based on the {@link ItemType} specified in the argument
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
     * removes the {@link Item} from itself
     * @param item {@link Item} to be used
     * @throws IllegalStateException if there are no more {@link Item}s to be removed 
     */
    public void consumeItem(final Item item) throws IllegalStateException;

    /**
     * (Only to be used once)
     * Initialize inventory with {@link Potion}, {@link Pokeball} and {@link Boost} .
     * @param potionList
     * 			{@link Map}({@link String}, {@link Integer}) with potion names as keys and quantities as values 
     * @param boostList
     * 			{@link Map}({@link String}, {@link Integer}) with stat names as keys and quantities as values 
     * @param ballList
     * 			{@link Map}({@link String}, {@link Integer}) with pokeball names as keys and quantities as values 
     * 
     * @throws IllegalStateException if it has already been initialized before
     */
    public void initializeInventory(final Map<String, Integer> potionList, final Map<String, Integer> boostList, final Map<String, Integer> ballList) throws IllegalStateException;
    	
}
