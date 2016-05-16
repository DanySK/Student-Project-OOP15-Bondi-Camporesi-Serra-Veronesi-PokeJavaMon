package model.items;

import exceptions.PokemonNotFoundException;
import model.player.Player;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;

public class Boost extends AbstractItem {

    final Stat stat;
    final double coeff;
    final String name;
    
    public Boost(final Stat stat) {
        super(500, Item.ItemType.BOOST, false);
        if (stat == Stat.EXP || stat == Stat.MAX_HP || stat == Stat.LVL) {
            throw new IllegalArgumentException();
        }
        this.stat = stat;
        this.coeff = 0.30;
        this.name = stat.toString() + "X";
    }

    @Override  
    public void effect(final Player p, PokemonInBattle pkmn) throws PokemonNotFoundException {
        if (!p.getSquad().getPokemonList().contains(pkmn)) {
            throw new PokemonNotFoundException();
        }
    }

    @Override
    public whenToUse whenToUse() {
        return Item.whenToUse.BATTLE;
    }
        
    public Stat getStat() {
        return this.stat;
    }
    
    public double getCoeff() {
        return this.coeff;
    }

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
