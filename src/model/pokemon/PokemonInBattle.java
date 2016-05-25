package model.pokemon;

/**
 * A class that extends the abstract class {@link AbstractPokemon} implementing 
 * {@link model.pokemon.AbstractPokemon#levelUp()}, {@link model.pokemon.AbstractPokemon#damage(int)} and {@link model.pokemon.AbstractPokemon#evolve()}.
 * It also contains overrides {@link Object#equals(Object)} and {@link Object#hashCode()} to improve comparison.
 * You can create instances of this class through {@link StaticPokemonFactory}
 * @see Pokemon
 * @see AbstractPokemon
 * @see StaticPokemonFactory
 */
public class PokemonInBattle extends AbstractPokemon{

    private boolean canEvolve;
    private Pokedex evolvesTo;

    protected PokemonInBattle(Pokedex pokemon, int lvl) {   
        super(pokemon, lvl);
        if (pokemon.getEvolvesToPokemon() != Pokedex.MISSINGNO) {
            canEvolve = true;
            evolvesTo = pokemon.getEvolvesToPokemon();
        }
    }
	
    @Override
    public void levelUp() {
        if (this.getStat(Stat.LVL) == MAX_LEVEL) {
            return;
        }
        changeStat(Stat.LVL, this.mapStat.get(Stat.LVL) + 1);
        updateStats();
        if (this.getStat(Stat.LVL) == MAX_LEVEL) {
            this.changeStat(Stat.EXP, 0);
        }
    }
	
	/**
	 * A method that tells if this Pokemon is ready to evolve, judgying by its level.
	 * @return	true if it's ready to evolve, false if it isn't
	 */
    public boolean checkIfEvolves() {
        if (this.getStat(Stat.LVL) >= this.pokemon.getEvolveLevel() && this.pokemon.getEvolveLevel() > 0) {
            return true;
    }
	    return false;
    }
	
    @Override
    public void evolve() throws IllegalStateException {
        if (!canEvolve || !this.checkIfEvolves()) {
            throw new IllegalStateException();
        }
        this.pokemon = this.evolvesTo;
        if (this.evolvesTo.getEvolveLevel() > 0) {
            this.canEvolve = true;
            this.evolvesTo = this.evolvesTo.getEvolvesToPokemon();
        } else {
            this.canEvolve = false;
            this.evolvesTo = Pokedex.MISSINGNO;
        }
        this.updateStats();
    }
	
	/**
	 * A method that tells if a Pokemon will be able to evolve or if it has reached its final form
	 * @return true if it still can evolve, false if not
	 */
    public boolean canEvolve() {
        return this.canEvolve;
    }
	
	/**
	 * A method that returns the {@link Pokedex} value of the Pokemon which it will evolve to
	 * @return		The {@link Pokedex} value of its next evolved form
	 * @see	Pokedex
	 */
    public Pokedex evolvesTo() {
        return this.evolvesTo;
    }
	
    @Override
    public void damage(final int dmg) {
        this.currentHP -= dmg;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    
    /**
     * Overriding {@link Object#hashCode()} to make Pokemon comparison faster
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mapStat == null) ? 0 : mapStat.hashCode() * pokemon.hashCode() * this.getStat(Stat.LVL));
        return result;
    }

    /**
     * Overriding {@link Object#equals(Object)} to make Pokemon comparison faster
     * @param obj	
     * 			the other {@link Pokemon} to make a comparison with	
     * @return 	true if the this Pokemon and obj are the same
     */
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
