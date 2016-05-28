package model.items;

import exceptions.PokemonNotFoundException;
import model.map.PokeMarket;
import model.player.Player;
import model.pokemon.PokemonInBattle;

/**
 * Subclass of {@link AbstractItem}, if used on one of {@link Player}'s {@link Pokemon} it heals some HP
 */
public class Potion extends AbstractItem {

	/**
	 * Types of {@link Potion}s available, with healing value and pricing
	 */
    public enum PotionType {
        Potion(20, 200), Superpotion(50, 400), Hyperpotion(200, 600);
        
        private PotionType(final int heal, final int cost) {
            this.heal = heal;
            this.cost = cost;
        }
        
        private final int heal;
        private final int cost;
        
        /**
         * @return the amount of HP that recovers
         */
        public int getHeal() {
            return this.heal;
        }
        
        /**
         * @return the price to pay to {@link PokeMarket} to buy it
         */
        public int getCost() {
            return this.cost;
        }
    }
    
    private final PotionType quality;
    
    /**
     * Constructor to initialize TODO
     * @param quality
     */
    public Potion(final Potion.PotionType quality) {
        super(quality.cost, Item.ItemType.POTION, false);
        this.quality = quality;
    }

    @Override
    public void effect(final Player p, final PokemonInBattle pkmn) throws PokemonNotFoundException {
        if (!p.getSquad().getPokemonList().contains(pkmn)) {
            throw new PokemonNotFoundException();
        }
        
        pkmn.heal(this.quality.heal);
        
    }
    
    public PotionType getQuality() {
        return this.quality;
    }
    
    public WhenToUse whenToUse() {
        return Item.WhenToUse.EVERYWHERE;
    }
    
    @Override
    public boolean equals(Object object) {
    	if (object == null) {
    		return false;
    	}
        return this.hashCode() == ((Potion) object).hashCode();
    }
    
    @Override
    public int hashCode() {
        switch (this.quality) {
        case Hyperpotion :
            return 9999970;
        case  Superpotion :
            return 9999971;
        case  Potion :
            return 9999972;
        }
        return 0;
    }

    @Override
    public String toString() {
        return quality.toString();
    }
}
