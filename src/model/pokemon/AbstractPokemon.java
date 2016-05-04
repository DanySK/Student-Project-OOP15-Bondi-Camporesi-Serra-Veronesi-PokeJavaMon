package model.pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


/**
 * Abstract class that implements {@link Pokemon} interface
 * leaving {@link model.pokemon.Pokemon#levelUp()}, {@link model.pokemon.Pokemon#damage(int)} and {@link model.pokemon.Pokemon#evolve()} as abstract methods
 * It is extended by {@link PokemonInBattle}
 * @see Pokemon
 * @see PokemonInBattle
 */
public abstract class AbstractPokemon implements Pokemon {
    

    public static final int MAX_MOVES = 4;
    
    protected Pokedex pokemon;
    protected Map<Stat, Integer> mapStat;
    protected int currentHP;
    protected Move[] currentMoves = new Move[MAX_MOVES];
    protected double randID;
    public static int MAX_LEVEL = 50;
    public static int MIN_LEVEL = 3;

    public AbstractPokemon(final Pokedex pokemon,int lvl) {
        if (lvl > MAX_LEVEL) {
        	lvl = MAX_LEVEL;
        }
        if (lvl < MIN_LEVEL) {
        	lvl = MIN_LEVEL;
        }
        
    	this.pokemon = pokemon;
        mapStat = new HashMap<>();

        for (final Stat s : Stat.values()) {
            mapStat.put(s, s == Stat.LVL ? lvl : 0);
        }
        updateStats();
        this.currentHP = mapStat.get(Stat.HP);
        
        
        List<Move> last4Moves = getLast4Moves(lvl);
        for (int i = 0 ; i < last4Moves.size() ; i ++) {
            currentMoves[i] = last4Moves.get(i);
        }
        
        final Random r = new Random();
        randID = r.nextInt();
        
    }
    
    /**
     * Method that finds the last 4 {@link Move}s (or less if there are not enough moves) the Pokemon can learn
     * starting from a given level.
     * The Moves are taken from the {@link Pokedex} value of the Pokemon.
     * @param 	lvl		the level
     * @return 	A 		{@link List} of the last <= 4 {@link Move}s the {@link Pokemon} can learn
     * @see 		Pokedex
     */
    protected List<Move> getLast4Moves(final int lvl) {
        final List<Move> retList = new ArrayList<>();
        final Map<Integer, Move> totalMoveset = this.pokemon.getMoveset();
        
        if (totalMoveset == null || totalMoveset.isEmpty()) {
            System.out.println(pokemon);
            throw new IllegalStateException("Moves not initialized");
        }
        
        for (int i = lvl; i > 0; i--) {
            if (totalMoveset.containsKey(i)) {
                retList.add(totalMoveset.get(i));
                if (retList.size() == MAX_MOVES) {
                    break;
                }
            }
        }
        
        for (int i = MAX_MOVES; i < retList.size(); i--) {
            retList.add(Move.NULLMOVE);
        }
        
        return retList;
        
    }

    /**
     * Method that calculates how much a {@link Stat} will be with a certain level
     * @param s		the {@link Stat}
     * @param lvl	the level
     * @return		the value of the {@link Stat} at the selected value
     */
    protected int getStatFormula(final Stat s, final int lvl) {
        switch (s) {
        case ATK :
            return this.pokemon.getBaseATK() * 2 * lvl / 100 + 5;
        case DEF :
            return this.pokemon.getBaseDEF() * 2 * lvl / 100 + 5;
        case SPD :
            return this.pokemon.getBaseSPD() * 2 * lvl / 100 + 5;
        case EXP :
            return (int) Math.pow(lvl , 3) * 4 / 5;
        case HP :
            return this.pokemon.getBaseHP() * 2 * lvl / 100 + lvl;
        case LVL :
            return this.mapStat.get(Stat.LVL);
        default :
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Method that calculates the necessary exp to jump from this current level
     * to the next one, starting with 0 exp.
     * @return 		the necessary exp to level up
     */
    protected int getLevelExp() {
        return (int) Math.pow(mapStat.get(Stat.LVL) , 3) * 4 / 5;
    }
    
    /**
     * Method that changes a {@link Stat}
     * @param s			indicates which {@link Stat}
     * @param newValue	the new value that will substitute the current one
     */
    protected void changeStat(final Stat s, final int newValue) {
        this.mapStat.replace(s, newValue);
        
    }
    
    @Override
    public Pokedex getPokemon() {
        return this.pokemon;
    }

    @Override
    public int getCurrentHP() {
        return currentHP;
    }

    @Override
    public int getNecessaryExp() {
        return this.getLevelExp() - mapStat.get(Stat.EXP);
    }

    @Override
    public int getStat(Stat s) {
        return this.mapStat.get(s);
    }
    
    @Override
    public void setExp(final int exp) {
    	this.changeStat(Stat.EXP, exp);
    }
    
    @Override
    public Map<Stat, Integer> getAllStats() {
        return Collections.unmodifiableMap(this.mapStat);
    }

    @Override
    public List<Move> getCurrentMoves() {
        return Arrays.asList(this.currentMoves);
    }
    
    /**
     * Method that updates {@link Stat}s accordingly to the level
     */
    protected void updateStats() {
        for (final Entry<Stat, Integer> e : mapStat.entrySet()) {
            if (e.getKey() == Stat.LVL || e.getKey() == Stat.EXP) {
                continue;
            } else {
                changeStat((Stat) e.getKey(), getStatFormula(e.getKey(), this.mapStat.get(Stat.LVL)));
            }
            
        }
    }
    
    @Override
    public abstract void levelUp();
    

    @Override
    public void learnMove(Move oldMove, Move newMove) throws IllegalArgumentException {
            if (!containsThisMove(Arrays.asList(this.currentMoves), oldMove)) {
                throw new IllegalArgumentException("oldMove cannot be found in the current moveset");
            }
        
            for (int i = 0; i < MAX_MOVES; i++) {
                if (this.currentMoves[i] == oldMove) {
                    this.currentMoves[i] = newMove;
                }
            }
    }
    
    private static boolean containsThisMove(Iterable<Move> it, Move move) {
        boolean flag = false;
        for (final Move m : it) {
            if (m == move) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void heal(int value) throws IllegalArgumentException {
    	if (value < 0) {
    		throw new IllegalArgumentException("Heal value cannot be negative");
    	}
        this.currentHP += value;
        if (this.currentHP > this.mapStat.get(Stat.HP)) {
            this.currentHP = this.mapStat.get(Stat.HP);
        }
    }

    @Override
    public abstract void evolve();
    
    @Override
    public abstract void damage(final int dmg);
    
    @Override
    public String toString() {
    	return "Name: " + pokemon.getName() + " , Index: " + pokemon.ordinal() + " , Stats: " + mapStat + " , HP: " + currentHP + " , Moveset: " + Arrays.asList(currentMoves) ;
    }

}
