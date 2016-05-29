package model.items;

import exceptions.PokemonNotFoundException;
import model.player.Player;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;

/**
 * Special kind of {@link Item} that, if used on battle gives a power up of 
 * a {@link Stat} to an ally {@link Pokemon}. 
 * The boost only last for the single battle.
 * If you add more {@link Boost} type remember to modify/override {@link }
 */
public class Boost extends AbstractItem {

	//price for each Boost in the Market
    private static final int PRICE = 500;
    //COEFF of variation of the stat
    private static final double COEFF = 0.30;
	
    //stat boosted by this item, it cannot be HP, LVL or EXP.
    private final Stat stat;
    private final String name;
    
    /**
     * Creates a new {@link Boost} that improves the specified {@link Stat}
     * @param stat
     * 			{@link Stat} improved by the {@link Boost} when used
     * @throws IllegalArgumentException if you try to create a {@link Boost} 
     * 			with {@link Stat#EXP}, {@link Stat#MAX_HP}, {@link Stat#LVL} 
     * 			as these {@link Stat} are not improvable in this way
     */
    public Boost(final Stat stat) {
        super(PRICE, Item.ItemType.BOOST, false);
        if (stat == Stat.EXP || stat == Stat.MAX_HP || stat == Stat.LVL) {
            throw new IllegalArgumentException();
        }
        this.stat = stat;
        this.name = stat.toString() + "X";
    }

    /**
     * Actually does nothing, the effect is implemented in Fight, needs fixing
     * @param p
     * @param pkmn
     * @throws PokemonNotFoundException
     */
    @Override  
    public void effect(final Player p, PokemonInBattle pkmn) throws PokemonNotFoundException {
        if (!p.getSquad().getPokemonList().contains(pkmn)) {
            throw new PokemonNotFoundException();
        }
    }

    @Override
    public WhenToUse whenToUse() {
        return Item.WhenToUse.BATTLE;
    }
        
    /**
     * @return the {@link Stat} affected by this {@link Item}
     */
    public Stat getStat() {
        return this.stat;
    }
    
    /**
     * @return the coefficient of variantion of a {@link Stat} after using this {@link Item}
     */
    public double getCoeff() {
        return COEFF;
    }

    /**
     * @return {@link Boost}'s name
     */
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object object) {
    	if (object == null) {
    		return false;
    	}
        return this.hashCode() == ((Boost) object).hashCode();
    }
    
    /**
     * Overrides {@link Object#hashCode()} in order to speed up searching {@link Item}s in the {@link Inventory}
     */
    @Override
    public int hashCode() {
        switch (this.stat) {
        case  ATK:
            return 9999980;
        case  DEF:
            return 9999981;
        case  SPD:
            return 9999982;
        default :
            return 0;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
