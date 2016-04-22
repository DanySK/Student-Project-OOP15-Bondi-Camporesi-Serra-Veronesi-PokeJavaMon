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
        Pokeball(255, 50), Greatball(200, 100), Ultraball(150, 200);
        
        private PokeballType(final int captureValue, final int cost) {
            this.captureValue = captureValue;
            this.cost = cost;
        }
        
        private final int captureValue;
        private final int cost;
        
        public int getPokeballValue() {
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
        return new Random().nextDouble() <= (double) this.calculateProbabilityCatch(pkmn) / 100;
    }
    
    private double calculateProbabilityCatch(Pokemon pkmn) {
        return (double) (3 * pkmn.getStat(Stat.HP) - 2 * pkmn.getCurrentHP()) * pkmn.getPokemon().getRarity().getCoeff() * this.quality.getPokeballValue() / (3 * pkmn.getStat(Stat.HP)) / 255;
    }

    @Override
    public void effect(final Player p, PokemonInBattle pkmn) throws SquadFullException {
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
