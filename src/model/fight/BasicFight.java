package model.fight;

import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class which provides the basic method for perform operation in fight classes 
 * that implements the interface {@link Fight}.
 * 
 * This class is extended by {@link AbstractFight}.
 */
public abstract class BasicFight {
    protected static final int FIRST_ELEM = 0;
    protected static final double BOOST_COEFF = 0.15;
    protected static final double BOOST_COEFF_INCR = 2;
    protected static final double MIN_BOOST_VALUE = 0.2;
    protected static final double MAX_BOOST_VALUE = 2.75;
    protected static final double STAB_ACTIVE = 1.5;
    protected static final double STAB_DISABLED = 1;
    protected static final int SUPER_EFFECTIVE = 2;
    protected static final double LESS_EFFECTIVE = 0.5;
    protected static final double IMMUNE = 0;
    protected static final int MIN_DAMAGE = 1;

    protected Player player = PlayerImpl.getPlayer();
    protected PokemonInBattle allyPkm;
    protected PokemonInBattle enemyPkm;
    protected boolean isAllyExhausted;
    protected boolean isEnemyExhausted;
    protected Effectiveness allyEff;
    protected Effectiveness enemyEff;
    protected boolean isAllyFastest;
    protected Move enemyMove;
    protected Map<PokemonInBattle, Map<Stat, Double>> allyPkmsBoosts = new HashMap<>();
    protected Move moveToLearn;

    /**
     * A simple constructor for BasicFight, it just initialize some ally parameters.
     */
    protected BasicFight() {
        moveToLearn = Move.NULLMOVE;
        allyPkm = player.getSquad().getPokemonList().get(FIRST_ELEM);
        for (final PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            this.allyPkmsBoosts.put(pkm, createBoostsMap());
        }
    }

    /**
     * Create a battle boosts map for a pokemon.
     * 
     * @return  A boost map that store the battle {@link model.pokemon.Stat} multipliers of a pokemon.
     */
    protected Map<Stat, Double> createBoostsMap() {
        final Map<Stat, Double> boosts = new HashMap<>();
        boosts.put(Stat.ATK, 1.0);
        boosts.put(Stat.DEF, 1.0);
        boosts.put(Stat.SPD, 1.0);
        return boosts;
    }

    /**
     * Reset the previous battle turn. 
     */
    protected void reset() {
        isAllyExhausted = false;
        isEnemyExhausted = false;
        allyEff = Effectiveness.NORMAL;
        enemyEff = Effectiveness.NORMAL;
    }

    
    /**
     * Resolve an ally turn using the chosen move by user.
     * 
     * @param move      The chosen {@link model.pokemon.Move}.
     */
    public void allyTurn(final Move move) {
        applyMove(move, allyPkm, enemyPkm);
    }

    /**
     * Resolve an enemy turn using a move.
     */
    public void enemyTurn() {
        enemyMove = calculationEnemyMove();
        applyMove(enemyMove, enemyPkm, allyPkm);
    }

    /**
     * Resolve the move use.
     * 
     * @param move      The {@link model.pokemon.Move} which must be used.
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     */
    protected void applyMove(final Move move, final PokemonInBattle striker, final PokemonInBattle stricken) {
        if (move.getStat() == Stat.MAX_HP) {
            applyDamage(striker, stricken, move);
            checkAndSetIsExhausted(stricken);
        } else {
            applyMoveOnBoost(striker, stricken, move);
        }
    }

    /**
     * Check if the pokemon taken as argument is exhausted and if it is, 
     * set his exhaust variable true.
     * 
     * @param pkm       The {@link model.pokemon.Pokemon} that must be checked.
     */
    public void checkAndSetIsExhausted(final PokemonInBattle pkm) {
        if (pkm.equals(allyPkm)) {
            isAllyExhausted = pkm.getCurrentHP() == 0;
        } else {
            isEnemyExhausted = pkm.getCurrentHP() == 0;
        }
    }

    /**
     * Calculate the enemy move to use.
     * 
     * @return          The {@link model.pokemon.Move} used by enemy {@link model.pokemon.Pokemon}.
     */
    protected abstract Move calculationEnemyMove();

    /**
     * Get an enemy battle stat value of the same type of the stat insert as argument.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to get.
     * @return          The relative battle stat value.
     */
    public abstract double getEnemyBoost(final Stat stat);

    /**
     * Set a new enemy battle stat value.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to modified.
     * @param d         The new battle stat value to set.
     */
    public abstract void setEnemyBoost(final Stat stat, final Double d);

    /**
     * Get an ally battle stat value of the same type of the stat insert as argument.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to get.
     * @return          The relative battle stat value.
     */
    public double getAllyBoost(final Stat stat) {
        return allyPkmsBoosts.get(allyPkm).get(stat);
    }

    /**
     * Set a new ally battle stat value.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to modified.
     * @param d         The new battle stat value to set.
     */
    public void setAllyBoost(final Stat stat, final Double d) {
        allyPkmsBoosts.get(allyPkm).replace(stat, d);
    }

    /**
     * Resolve a move which target a boost stat.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param move      The {@link model.pokemon.Move} which must be used.
     */
    protected void applyMoveOnBoost(final PokemonInBattle striker, final PokemonInBattle stricken,
            final Move move) {
        double newBoostValue;
        double moveValue = move.getValue() * BOOST_COEFF;
        if (allyPkm.equals(striker)) {
            if (move.isOnEnemy()) {
                newBoostValue = getEnemyBoost(move.getStat()) - moveValue;
                if (newBoostValue < MIN_BOOST_VALUE) {
                    newBoostValue = MIN_BOOST_VALUE;
                    allyEff = Effectiveness.CANNOTDECREASE;
                }
                setEnemyBoost(move.getStat(), newBoostValue);
            } else {
                newBoostValue = getAllyBoost(move.getStat()) + (moveValue * BOOST_COEFF_INCR);
                if (newBoostValue > MAX_BOOST_VALUE) {
                    newBoostValue = MAX_BOOST_VALUE;
                    allyEff = Effectiveness.CANNOTINCREASE;
                }
                setAllyBoost(move.getStat(), newBoostValue);
            }
        } else {
            if (move.isOnEnemy()) {
                newBoostValue = getAllyBoost(move.getStat()) - moveValue;
                if (newBoostValue < MIN_BOOST_VALUE) {
                    newBoostValue = MIN_BOOST_VALUE;
                    enemyEff = Effectiveness.CANNOTDECREASE;
                }
                setAllyBoost(move.getStat(), newBoostValue);
            } else {
                newBoostValue = getEnemyBoost(move.getStat()) + moveValue;
                if (newBoostValue > MAX_BOOST_VALUE) {
                    newBoostValue = MAX_BOOST_VALUE;
                    enemyEff = Effectiveness.CANNOTINCREASE;
                }
                setEnemyBoost(move.getStat(), newBoostValue);
            }
        }
    }

    /**
     * Resolve a move which do damage.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param move      The {@link model.pokemon.Move} which must be used.
     */
    protected void applyDamage(final PokemonInBattle striker, final PokemonInBattle stricken,
            final Move move) {
        isEffective(striker, stricken, move);
        final double atkBoost;
        final double defBoost;
        if (striker.equals(allyPkm)) {
            atkBoost = getAllyBoost(Stat.ATK);
            defBoost = getEnemyBoost(Stat.DEF);
        } else {
            atkBoost = getEnemyBoost(Stat.ATK);
            defBoost = getAllyBoost(Stat.DEF);
        }
        final int damageDone = damageCalculation(striker, stricken, atkBoost, defBoost, move,
                stabCalculation(striker, move), isEffective(striker, stricken, move));
        stricken.damage(damageDone);
    }

    /**
     * Set the effective parameters: the multiplier and the enumeration value to pass 
     * to the view methods.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param move      The {@link model.pokemon.Move} which must be used.
     * @return 
     */
    protected double isEffective(final PokemonInBattle striker, final PokemonInBattle stricken, 
            final Move move) {
        final double effectiveValue = WeaknessTable.getWeaknessTable().getMultiplierAttack(move.getType(), stricken.getPokedexEntry().getFirstType(),
                stricken.getPokedexEntry().getSecondType());
        if (effectiveValue >= SUPER_EFFECTIVE) {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.SUPEREFFECTIVE;
            } else {
                enemyEff = Effectiveness.SUPEREFFECTIVE;
            }
        } else if (effectiveValue <= IMMUNE) {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.IMMUNE;
            } else {
                enemyEff = Effectiveness.IMMUNE;
            }
        } else if (effectiveValue <= LESS_EFFECTIVE) {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.LESSEFFECTIVE;
            } else {
                enemyEff = Effectiveness.LESSEFFECTIVE;
            }
        } else {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.NORMAL;
            } else {
                enemyEff = Effectiveness.NORMAL;
            }
        }
        if (move.equals(Move.SPLASH)) {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.NONE;
            } else {
                enemyEff = Effectiveness.NONE;
            }
        }
        return effectiveValue;
    }

    /**
     * Calculate the stab value.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param move      The {@link model.pokemon.Move} which must be used.      
     * @return          The stab value.
     */
    protected double stabCalculation(final PokemonInBattle striker, final Move move) {
        if (striker.getPokedexEntry().getFirstType() == move.getType() 
                || striker.getPokedexEntry().getSecondType() == move.getType()) {
            return STAB_ACTIVE;
        }
        return 1;
    }

    /**
     * Calculate the damage done.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param atkBoost  The battle boost ATK of the stricker.
     * @param defBoost  The battle boost DEF of the stricken.
     * @param move      The {@link model.pokemon.Move} which must be used.
     * @return          The damage.
     */
    protected int damageCalculation(final PokemonInBattle striker, final PokemonInBattle stricken, 
            final double atkBoost, final double defBoost, final Move move, final double stab, final double effectiveValue) {
        final int damage = (int) ((((2 * striker.getStat(Stat.LVL) + 10) * (striker.getStat(Stat.ATK) 
                * atkBoost * move.getValue())) / ((stricken.getStat(Stat.DEF) * defBoost) * 250 + 2)) * stab * effectiveValue);
        if (damage <= 0 && effectiveValue > 0 && !move.equals(Move.SPLASH)) {
            return MIN_DAMAGE;
        }
        return damage;
    }

    /**
     * Calculate, set and return true if ally pokemon is faster than enemy pokemon.
     * 
     * @return  The boolean variable which indicate what pokemon is faster.
     */
    public boolean setIsAllyFastest() {
        return isAllyFastest = (allyPkm.getStat(Stat.SPD) * getAllyBoost(Stat.SPD))
                >= (enemyPkm.getStat(Stat.SPD) * getEnemyBoost(Stat.SPD));
    }

    /**
     * Give to ally pokemon the experience of enemy pokemon and if 
     * is enough to level up, ally pokemon level up.
     * 
     * @param exp       The experience gained ({@link AbstractFight#getExp()}) by beating enemy pokemon.
     * @return          True, if ally {@link model.pokemon.Pokemon} level up.
     */
    public boolean giveExpAndCheckLvlUp(final int exp) {
        if (allyPkm.getNecessaryExp() <= exp) {
            allyPkm.setExp(exp - allyPkm.getNecessaryExp());
            allyPkm.levelUp();
            return true;
        }
        allyPkm.setExp(allyPkm.getStat(Stat.EXP) + exp);
        return false;
    }

    /**
     * Return the experience gained by beating an enemy pokemon according to rarity.
     * 
     * @return          The experience gained.
     * @see {@link AbstractFight#getExp()}
     */
    protected double expBaseCalculation() {
        //TODO testare se è bilanciata la quantità di baseExp
        double baseExp;
        switch(enemyPkm.getPokedexEntry().getRarity()){
        case COMMON:
            baseExp = 100;
            break;
        case UNCOMMON:
            baseExp = 130;
            break;
        case RARE:
            baseExp = 150;
            break;
        case VERY_RARE:
            baseExp = 180;
            break;
        case UNFINDABLE:
            baseExp = 200;
            break;
        case STARTER:
            baseExp = 250;
            break;
        case LEGENDARY:
            baseExp = 500;
            break;
        default:
            baseExp = 0;
        }
        baseExp = baseExp * enemyPkm.getStat(Stat.LVL);
        return baseExp;
    }

}