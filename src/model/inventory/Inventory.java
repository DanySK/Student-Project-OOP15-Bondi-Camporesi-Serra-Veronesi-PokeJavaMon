package model.inventory;

import java.util.Map;

import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Potion;

public interface Inventory {
    
    public Map<Item, Integer> getSubInventory(final Item.ItemType type);
    
    public void addItem(final Item item);

    public void consumeItem(final Item item);
    
    public void setPokeballs(final Map<Pokeball, Integer> balls);
    
    public void setBoosts(final Map<Boost, Integer> boosts);
    
    public void setPotions(final Map<Potion, Integer> potions);
    
    public void initializeInventory(final Map<String, Integer> potionList, final Map<String, Integer> boostList, final Map<String, Integer> ballList);
    	
}
