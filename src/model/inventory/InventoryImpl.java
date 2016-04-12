package model.inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Potion;
import model.items.Item.ItemType;
import model.pokemon.Stat;

public class InventoryImpl implements Inventory {

    private final Map<Pokeball, Integer> pokeballs;
    private final Map<Potion, Integer> potions;
    private final Map<Boost, Integer> boosts;
    
    private static Inventory SINGLETON;
    
    private InventoryImpl() {
        this.pokeballs = new HashMap<>();
        for (final Pokeball.PokeballType p : Pokeball.PokeballType.values()) {
            pokeballs.put(new Pokeball(p), 0);
        }
        
        this.potions = new HashMap<>();
        for (final Potion.PotionType p : Potion.PotionType.values()) {
            potions.put(new Potion(p), 0);
        }
        
        this.boosts = new HashMap<>();
        for (final Stat s : Stat.values()) {
            if (s != Stat.HP && s != Stat.EXP && s != Stat.LVL) {
                boosts.put(new Boost(s), 0);
            }
        }
        
    }
    
    
    
    public static Inventory getInventory() {
        if (SINGLETON == null) {
            synchronized (InventoryImpl.class) {
                if (SINGLETON == null) {
                    SINGLETON = new InventoryImpl();
                }
            }
        }
        return SINGLETON;
    }
    
    @Override
    public Map<Item, Integer> getSubInventory(ItemType type) {
        switch (type) {
        case BOOST :
            return Collections.unmodifiableMap(this.boosts);
        case POKEBALL :
            return Collections.unmodifiableMap(this.pokeballs);
        case POTION :
            return Collections.unmodifiableMap(this.potions);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void addItem(Item item) {
        this.changeQty(item, +1);
    }

    @Override
    public void consumeItem(Item item) throws IllegalStateException {
        this.changeQty(item, -1);
    }

    private void changeQty(Item item, int qty) {
        switch (item.getType()) {
        case BOOST :
            if (this.boosts.get(item) + qty < 0) {
                throw new IllegalStateException();
            }
            this.boosts.replace((Boost) item, this.boosts.get(item) + qty);
        case POTION :
            if (this.potions.get(item) + qty < 0) {
                throw new IllegalStateException();
            }
            this.potions.replace((Potion) item, this.potions.get(item) + qty);
        case POKEBALL :
            if (this.pokeballs.get(item) + qty < 0) {
                throw new IllegalStateException();
            }
            this.pokeballs.replace((Pokeball) item, this.pokeballs.get(item) + qty);
        }
    }
}
