package model.inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Potion;
import model.items.Item.ItemType;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.pokemon.Stat;

public class InventoryImpl implements Inventory {

    protected Map<Pokeball, Integer> pokeballs;
    protected Map<Potion, Integer> potions;
    protected Map<Boost, Integer> boosts;
    private static boolean isSet = false;
    
    
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
        isSet = false;
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

    public static Inventory initializeInventory(final Map<String, Integer> potionList, final Map<String, Integer> boostList, final Map<String, Integer> ballList) {
		final Inventory i = InventoryImpl.getInventory();
		
		if (InventoryImpl.isSet) {
			throw new IllegalStateException("Can't initialize Inventory more than once");
		}
		
		
		if (potionList != null) {
		        Map<Potion, Integer> pot = new HashMap<>();
			for (final String s : potionList.keySet()) {
				for (final PotionType pt : PotionType.values()) {
					if (s.toUpperCase().equals(pt.name().toUpperCase())) {
						pot.put(new Potion(pt), potionList.get(s));
					}
				}
			}
			i.setPotions(pot);
		}
			
		if (boostList != null) {
		        Map<Boost, Integer> boo = new HashMap<>();
			for (final String s : boostList.keySet()) {
				for (final Stat stat : Stat.values()) {
					if (stat == Stat.EXP || stat == Stat.HP || stat == Stat.LVL) {
						continue;
					}
					if (s.toUpperCase().equals(stat.name().toUpperCase()+"X")) {
					    boo.put(new Boost(stat), boostList.get(s));
					}
				}
			}
			i.setBoosts(boo);
		}
		
		if (ballList != null) {
		        Map<Pokeball, Integer> bal = new HashMap<>();
		        for (final String s : ballList.keySet()) {
				for (final PokeballType pbt : PokeballType.values()) {
					if (s.toUpperCase().equals(pbt.name().toUpperCase())) {
						bal.put(new Pokeball(pbt), ballList.get(s));
					}
				}
			}
		        i.setPokeballs(bal);
		}
		InventoryImpl.isSet = true;
		return i;
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
        System.out.println(item);
        switch (item.getType()) {
        case BOOST :
            if (this.boosts.get(item) + qty < 0) {
                throw new IllegalStateException();
            }
            this.boosts.replace((Boost) item, this.boosts.get(item) + qty);
            break;
        case POTION :
            if (this.potions.get(item) + qty < 0) {
                throw new IllegalStateException();
            }
            this.potions.replace((Potion) item, this.potions.get(item) + qty);
            break;
        case POKEBALL :
            if (this.pokeballs.get(item) + qty < 0) {
                throw new IllegalStateException();
            }
            this.pokeballs.replace((Pokeball) item, this.pokeballs.get(item) + qty);
            break;
        }
    }
    
    public void setPokeballs(final Map<Pokeball, Integer> pokeballs) {
        this.pokeballs = pokeballs;
    }
    
    public void setPotions(final Map<Potion, Integer> potions) {
        this.potions = potions;
    }
    
    public void setBoosts(final Map<Boost, Integer> boosts) {
        this.boosts = boosts;
    }
    
    public String toString() {
        return "Potions: " + potions.toString() + " Boosts: " + boosts.toString() + " Balls: " + pokeballs.toString();
     }
}
