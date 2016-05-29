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
     * Constructor to initialize a {@link Potion} based on its quality
     * @param quality
     * 			{@link PotionType} quality
     */
    public Potion(final Potion.PotionType quality) {
        super(quality.cost, Item.ItemType.POTION, false);
        this.quality = quality;
    }

    /**
     * Heals a {@link Pokemon} in {@link Player}'s {@link Squad} of the ammount specified 
     * by its {@link PotionType} quality
     * @param p 
     * 			{@link Player}, to check if the {@link Pokemon} belongs to his {@link Squad}
     * @param pkmn
     * 			{@link PokemonInBattle} that will be healed
     * @throws PokemonNotFoundException if the {@link PokemonInBattle} does not appear in {@link Player}'s {@link Squad}
     */
    @Override
    public void effect(final Player p, final PokemonInBattle pkmn) throws PokemonNotFoundException {
        if (!p.getSquad().getPokemonList().contains(pkmn)) {
            throw new PokemonNotFoundException();
        }
        
        pkmn.heal(this.quality.heal);
        
    }
    
    /**
     * @return {@link PotionType} quality of this {@link Item}
     */
    public PotionType getQuality() {
        return this.quality;
    }

    @Override
    public WhenToUse whenToUse() {
        return Item.WhenToUse.EVERYWHERE;
    }
    
    /**
     * Overrides {@link Object#equals(Object)} in order to speed up searching {@link Item}s in the {@link Inventory}
     */
    @Override
    public boolean equals(Object object) {
    	if (object == null) {
    		return false;
    	}
        return this.hashCode() == ((Potion) object).hashCode();
    }
    
    /**
     * Overrides {@link Object#hashCode()} in order to speed up searching {@link Item}s in the {@link Inventory}
     */
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
