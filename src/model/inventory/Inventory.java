package model.inventory;

import java.util.Map;

import model.items.Item;

public interface Inventory {
    
    public Map<Item, Integer> getSubInventory(final Item.ItemType type);
    
    public void addItem(final Item item);

    public void consumeItem(final Item item);
}
