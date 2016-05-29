package model.items;

import java.util.Random;

import exceptions.SquadFullException;
import model.map.PokeMarket;
import model.player.Box;
import model.player.Player;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.squad.SquadImpl;

/**
 * A special kind of {@link Item} used in battle to catch wild {@link Pokemon}
 */
public class Pokeball extends AbstractItem {

    private boolean capture;
	/**
	 * Enum used to describe all the different kinds of {@link Pokeball},
	 * each one with different prices and capture chance
	 */
    public enum PokeballType {
        Pokeball(1, 50), Greatball(1.5, 100), Ultraball(2, 200);
        
        private PokeballType(final double captureValue, final int cost) {
            this.captureValue = captureValue;
            this.cost = cost;
        }
        
        //values of each type of {@link Pokeball}
        private final double captureValue;
        private final int cost;
        
        /**
         * @return the base ratio of capture of a {@link Pokeball}, the higher the easier to catch
         */
        public double getPokeballValue() {
            return this.captureValue;
        }
        
        /**
         * @return the price you have to pay to buy it from a {@link PokeMarket}
         */
        public int getCost(){
            return this.cost;
        }
    }
    
    private final PokeballType quality;
    
    /**
     * Creates a kind of {@link Pokeball} based on its type
     * @param quality 
     * 				{@link PokeballType} of the {@link Item}
     */
    public Pokeball(PokeballType quality) {
        super(quality.cost, Item.ItemType.POKEBALL, true);
        this.quality = quality;
    }
    
    /**
     * Calculates the probability of catching and checks if, after having thrown this {@link Pokeball}, the {@link Pokemon} is captured
     * @param pkmn
     * 			the {@link Pokemon} to be caught
     * @return
     * 			true if, after calculations, the {@link Pokemon} is caught, false otherwise
     */
    public boolean isCaptured(final Pokemon pkmn) {
        double x = new Random().nextDouble();
        double y = (double) this.calculateProbabilityCatch(pkmn, pkmn.getCurrentHP() == pkmn.getStat(Stat.MAX_HP));
        return this.capture = x <= y;
    }
    
    /**
     * Calculates the probability of catching a given {@link Pokemon}, based on its {@link Stat}s and on this {@link Pokeball} capture value
     * specified on {@link PokeballType}
     * @param pkmn
     * 			{@link Pokemon} to be checked
     * @param isFullHP
     * 			whether or not the {@link Pokemon} has all the healh points
     * @return	a value in range [0,1] which is the probability in decimals of catching the {@link Pokemon}
     */
    public double calculateProbabilityCatch(final Pokemon pkmn, final boolean isFullHP) {
        final int maxHP = pkmn.getStat(Stat.MAX_HP);
        final int currentHP = pkmn.getCurrentHP();
        final int rarity = pkmn.getPokedexEntry().getRarity().getCoeff();
        final double pokeballRate = this.quality.getPokeballValue();
        double prob;
        if (isFullHP) {
        	prob = ((1 / maxHP * 3) + ((rarity * pokeballRate ) / 3)) / 256;
        } else {
        	System.out.println(this.quality + " " + pkmn.getPokedexEntry() + ", MaxHP/cHP= " + pkmn.getStat(Stat.MAX_HP) + "/" + pkmn.getCurrentHP() + ", (1+catchRate) / (maxHP*3) / 256 = (" + 1 + "+" + ((maxHP * 3 - currentHP * 2 ) * rarity * pokeballRate) + ") / " + (maxHP*3) + " / 256" );
        	prob = (( 1 + ( maxHP * 3 - currentHP * 2 ) * rarity * pokeballRate) / ( maxHP * 3 )) / 256;
        }
        return prob <= 1 ? prob : 1;
    }

    /**
     * If the odds are in your favor and you capture a {@link Pokemon} 
     * it gets sent either to the {@link Player}'s {@link Squad} or, if this is full,
     * to the {@link Box}
     * @param p
     * @param pkmn
     */
    @Override
    public void effect(final Player p, final PokemonInBattle pkmn) {
        if (this.isCaptured(pkmn)) {
           if (p.getSquad().getSquadSize() >= SquadImpl.MAX_SIZE) {
               p.getBox().putCapturedPokemon(pkmn);
               return;
           }
           
           try {
			p.getSquad().add(pkmn);
		} catch (SquadFullException e) {
			e.printStackTrace();
		}
       }
    }

    @Override
    public WhenToUse whenToUse() {
        return Item.WhenToUse.BATTLE;
    }

    /**
     * Overrides {@link Object#equals(Object)} in order to speed up searching {@link Item}s in the {@link Inventory}
     */
    @Override
    public boolean equals(Object object) {
    	if (object == null) {
    		return false;
    	}	
        return this.hashCode() == ((Pokeball) object).hashCode();
    }
    
    /**
     * Overrides {@link Object#hashCode()} in order to speed up searching {@link Item}s in the {@link Inventory}
     */
    @Override
    public int hashCode() {
        switch (this.quality) {
        case Ultraball :
            return 9999990;
        case Greatball :
            return 9999991;
        case Pokeball :
            return 9999992;
        }
        return 0;
    }

    @Override
    public String toString() {
        return quality.toString();
    }

    public boolean getCapture() {
        return this.capture;
    }
}
