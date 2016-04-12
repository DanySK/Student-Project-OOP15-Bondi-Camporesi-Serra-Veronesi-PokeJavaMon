package model.pokemon;

import java.util.HashMap;
import java.util.Map;

import model.items.Boost;

public class PokemonInBattle extends AbstractPokemon{

	private boolean canEvolve;
	private PokemonDB evolvesTo;
	private final Map<Stat, Double> boosts;
	public static int MAX_LEVEL = 50;

	protected PokemonInBattle(PokemonDB pokemon, int lvl) {   
		super(pokemon, lvl);
		if (lvl > MAX_LEVEL) {
		    throw new IllegalArgumentException("Level too high");
		}
		if (pokemon.getEvolvesToPokemon() != PokemonDB.MISSINGNO) {
			canEvolve = true;
			evolvesTo = pokemon.getEvolvesToPokemon();
		}
		boosts = new HashMap<>();
		boosts.put(Stat.ATK, 1.0);
		boosts.put(Stat.DEF, 1.0);
		boosts.put(Stat.SPD, 1.0);
	}

        
	public void applyBoost(final Boost b) {
	    boosts.replace(b.getStat(), b.getCoeff() * boosts.get(b.getStat()));
	}
	
	public double getBoost(final Stat s) {
	    return this.boosts.get(s);
	}
	
	public void clearBoosts() {
	    boosts.replace(Stat.ATK, 1.0);
            boosts.replace(Stat.DEF, 1.0);
            boosts.replace(Stat.SPD, 1.0);
	}
	
	public void levelUp() {
	    if (this.getStat(Stat.LVL) == MAX_LEVEL) {
	        return;
	    }
	    changeStat(Stat.LVL, this.mapStat.get(Stat.LVL) + 1);
            if (checkIfEvolves() && canEvolve) {
                evolve();
            }
            updateStats();
        }
        
	public boolean checkIfEvolves() {
	    if (this.getStat(Stat.LVL) >= this.pokemon.getEvolveLevel()) {
	        return true;
	    }
	    return false;
	}
	
	public void evolve() {
	    if (!canEvolve || !this.checkIfEvolves()) {
	        throw new IllegalStateException();
	    }
	    this.pokemon = this.evolvesTo;
	    if (this.evolvesTo.getEvolveLevel() > 0) {
	        this.canEvolve = true;
	        this.evolvesTo = this.evolvesTo.getEvolvesToPokemon();
	    } else {
	        this.canEvolve = false;
	        this.evolvesTo = PokemonDB.MISSINGNO;
	    }
	}

	
	public boolean canEvolve() {
	    return this.canEvolve;
	}
	
	public PokemonDB evolvesTo() {
	    return this.evolvesTo;
	}
	
	public void damage(final int dmg) {
	    this.currentHP -= dmg;
	    if (this.currentHP < 0) {
	        this.currentHP = 0;
	    }
	}


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((boosts == null) ? 0 : boosts.hashCode() * pokemon.hashCode() * this.getStat(Stat.LVL));
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PokemonInBattle other = (PokemonInBattle) obj;
        if (other.pokemon == this.pokemon 
                && other.boosts.equals(this.boosts) 
                && other.getStat(Stat.LVL) == this.getStat(Stat.LVL)
                && other.currentHP == this.currentHP 
                && other.getNecessaryExp() == this.getNecessaryExp()
                && other.randID == this.randID) {
            return true;
        }
        return false;
    }
	
		
	

}
