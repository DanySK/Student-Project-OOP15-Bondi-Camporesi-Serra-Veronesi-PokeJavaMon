package model.items;

import java.util.Random;

import exceptions.SquadFullException;
import model.player.Player;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.squad.SquadImpl;


public class Pokeball extends AbstractItem {

    public static enum PokeballType {
        Pokeball(1, 50), Greatball(1.5, 100), Ultraball(2, 200);
        
        private PokeballType(final double captureValue, final int cost) {
            this.captureValue = captureValue;
            this.cost = cost;
        }
        
        private final double captureValue;
        private final int cost;
        
        public double getPokeballValue() {
            return this.captureValue;
        }
        
        public int getCost(){
            return this.cost;
        }
    }
    
    private final PokeballType quality;
    

    public Pokeball(PokeballType quality) {
        super(quality.cost, Item.ItemType.POKEBALL, true);
        this.quality = quality;
    }
    
    public boolean isCaptured(final Pokemon pkmn) {
        double x = new Random().nextDouble();
        double y = (double) this.calculateProbabilityCatch(pkmn, pkmn.getCurrentHP() == pkmn.getStat(Stat.HP));
        System.out.println("Prob: " + y + ", value: " + x + ", enemy hp: " + pkmn.getCurrentHP() + ", rarity: " + pkmn.getPokemon().getRarity());
        return x <= y;
    }
    
    private double calculateProbabilityCatch(final Pokemon pkmn, final boolean isFullHP) {
        final int maxHP = pkmn.getStat(Stat.HP);
        final int currentHP = pkmn.getCurrentHP();
        final int rarity = pkmn.getPokemon().getRarity().getCoeff();
        final double pokeballRate = this.quality.getPokeballValue();
        final double prob;
        if (isFullHP) {
            prob = ((1 / maxHP * 3) + ((rarity * pokeballRate ) / 3)) / 256;
        } else {
            prob = (( 1 + ( maxHP * 3 - currentHP * 2 ) * rarity * pokeballRate) / ( maxHP * 3 )) / 256;
        }
        return prob;
    }

    @Override
    public void effect(final Player p, final PokemonInBattle pkmn) throws SquadFullException {
        if (this.isCaptured(pkmn)) {
           if (p.getSquad().getSquadSize() >= SquadImpl.MAX_SIZE) {
               p.getBox().putCapturedPokemon(pkmn);
               return;
           }
           
           p.getSquad().add(pkmn);
       }
    }

    @Override
    public whenToUse whenToUse() {
        return Item.whenToUse.BATTLE;
    }

    @Override
    public boolean equals(Object object) {
    	if (object == null) {
    		return false;
    	}
    	if (!(object instanceof Boost) ) {
    		return false;
    	}
        return this.hashCode() == ((Pokeball) object).hashCode();
    }
    
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
}
