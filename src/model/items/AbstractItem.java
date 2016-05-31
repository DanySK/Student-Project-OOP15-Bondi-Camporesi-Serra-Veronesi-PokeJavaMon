package model.items;

import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.map.PokeMarket;
import model.map.tile.Tile.TileType;
import model.player.Inventory;
import model.player.Player;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;

/**
 * Class to factorize code from all different implementation of {@link Item} such as
 * {@link Boost}, {@link Pokeball}, {@link Potion}...
 * Contains basic fields that each {@link Item} has
 */
public abstract class AbstractItem implements Item {

	/**
	 * price you have to pay for this {@link Item} in order to buy it from a {@link PokeMarket}
	 */
    protected final int price;
    
    /**
     * type of this {@link Item}
     */
    protected final Item.ItemType type;
    
    /**
     * whether or not this {@link Item} can be used on an enemy's {@link Pokemon}
     */
    protected final boolean isOnEnemy;
    
    /**
     * Constructor to initialize all the fields
     * @param price
     * 			price in money that you will have to pay in order to buy it from a {@link PokeMarket}
     * @param type
     * 			{@link TileType} of this newly created {@link Item}
     * @param isOnEnemy
     * 			whether or not this {@link Item} can be used on enemy's {@link Pokemon}
     */
    protected AbstractItem(final int price, final Item.ItemType type, final boolean isOnEnemy) {
        this.price = price;
        this.type = type;
        this.isOnEnemy = isOnEnemy;
    }
    
    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public ItemType getType() {
        return this.type;
    }
    
    @Override
    public boolean isOnEnemy() {
        return this.isOnEnemy;
    }
    
    /**
     * Applies the effect of the specified {@link Item} on the {@link PokemonInBattle} passed as argument
     * @param p
     * 			{@link Player} in order to remove the used {@link Item} from his {@link Inventory}
     * @param pkmn
     * 			{@link PokemonInBattle} which will be applied the effect on
     * @throws PokemonNotFoundException if the {@link Pokemon} is not in the {@link Squad}
     * @throws SquadFullException if the {@link Squad} is already full
     */
    public abstract void effect(final Player p, final PokemonInBattle pkmn) throws PokemonNotFoundException, SquadFullException;
    
    /**
     * Abstract because depends on the {@link ItemType} and to be clarified by actual subclasses
     */
    public abstract WhenToUse whenToUse();

}
