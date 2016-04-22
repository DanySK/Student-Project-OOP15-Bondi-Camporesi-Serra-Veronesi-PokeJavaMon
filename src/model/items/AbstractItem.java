package model.items;

import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.player.Player;
import model.pokemon.PokemonInBattle;

public abstract class AbstractItem implements Item {

    protected int price;
    protected Item.ItemType type;
    protected boolean isOnEnemy;
    
    
    public AbstractItem(final int price, final Item.ItemType type, final boolean isOnEnemy) {
        this.price = price;
        this.type = type;
    }
    
    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public ItemType getType() {
        return this.type;
    }
    
    public boolean isOnEnemy() {
        return this.isOnEnemy;
    }
    
    public abstract void effect(final Player p, final PokemonInBattle pkmn) throws PokemonNotFoundException, SquadFullException;
    
    public abstract whenToUse whenToUse();

}
