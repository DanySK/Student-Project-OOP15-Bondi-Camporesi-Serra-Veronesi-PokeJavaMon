package model.pokemon;

public class PokemonInBattle extends AbstractPokemon{

	private boolean canEvolve;
	private PokemonDB evolvesTo;
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
	}
	
	public void levelUp() {
	    if (this.getStat(Stat.LVL) == MAX_LEVEL) {
	        return;
	    }
	    changeStat(Stat.LVL, this.mapStat.get(Stat.LVL) + 1);
	    updateStats();
    }
	
	public void evolveUp(){
		if (checkIfEvolves() && canEvolve) {
            evolve();
            updateStats();
        }
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
        result = prime * result + ((mapStat == null) ? 0 : mapStat.hashCode() * pokemon.hashCode() * this.getStat(Stat.LVL));
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
                && other.getStat(Stat.LVL) == this.getStat(Stat.LVL)
                && other.currentHP == this.currentHP 
                && other.getNecessaryExp() == this.getNecessaryExp()
                && other.randID == this.randID) {
            return true;
        }
        return false;
    }
}
