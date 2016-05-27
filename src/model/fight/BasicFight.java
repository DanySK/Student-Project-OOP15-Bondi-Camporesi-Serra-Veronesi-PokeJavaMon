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
 * This abstract class provides the basic methods used to perform the operations declared in
 * {@link Fight}. Some methods implement the template method pattern.
 * 
 * This class is extended by {@link AbstractFight}.
 */
public abstract class BasicFight {
    /**
     * The value used to accede at the first element of a list.
     */
    protected static final int FIRST_ELEM = 0;
    /**
     * The modifier used in the boost formula.
     */
    protected static final double BOOST_COEFF = 0.15;
    /**
     * The multiplier used in the boost formula in case of increment.
     */
    protected static final double BOOST_COEFF_INCR = 2;
    /**
     * The value indicates the minimum value reachable by a battle boost.
     */
    protected static final double MIN_BOOST_VALUE = 0.2;
    /**
     * The value indicates the maximum value reachable by a battle boost.
     */
    protected static final double MAX_BOOST_VALUE = 2.75;
    /**
     * The multiplier used when the stab value is active.
     */
    protected static final double STAB_ACTIVE = 1.5;
    /**
     * The used when the stab value is deactivated.
     */
    protected static final double STAB_DISABLED = 1;
    /**
     * The value assumed by multiplier when a move is super effective.
     */
    protected static final int SUPER_EFFECTIVE = 2;
    /**
     * The value assumed by multiplier when a move is less effective.
     */
    protected static final double LESS_EFFECTIVE = 0.5;
    /**
     * The value assumed by multiplier when a move has no effect against the opponent pokemon.
     */
    protected static final double IMMUNE = 0;
    /**
     * The minimum damage done by a move which targets HP.
     */
    protected static final int MIN_DAMAGE = 1;
    /**
     * A reference at player, to make more easier the access.
     */
    protected Player player = PlayerImpl.getPlayer();
    /**
     * The ally front pokemon which stand the fight.
     */
    protected PokemonInBattle allyPkm;
    /**
     * The enemy front pokemon which stand the fight.
     */
    protected PokemonInBattle enemyPkm;
    /**
     * The value indicates if {@link BasicFight#allyPkm} cannot fight anymore.
     */
    protected boolean isAllyExhausted;
    /**
     * The value indicates if {@link BasicFight#enemyPkm} cannot fight anymore.
     */
    protected boolean isEnemyExhausted;
    /**
     * The value indicates the effectiveness of ally move.
     */
    protected Effectiveness allyEff;
    /**
     * The value indicates the effectiveness of {@link BasicFight#enemyMove}.
     */
    protected Effectiveness enemyEff;
    /**
     * The value indicates if the ally pokemon is more faster than enemy pokemon.
     */
    protected boolean isAllyFastest;
    /**
     * The move used by {@link BasicFight#enemyPkm}.
     */
    protected Move enemyMove;
    /**
     * The value indicates if the ally pokemon gain a level.
     */
    protected boolean levelUp = false;
    /**
     * The boosts of all pokemons in the player squad.
     */
    protected Map<PokemonInBattle, Map<Stat, Double>> allyPkmsBoosts = new HashMap<>();
    /**
     * The move that the ally pokemon should learn
     * (in case a pokemon level up and it is able to learn a new move).
     */
    protected Move moveToLearn;

    /**
     * A simple constructor for BasicFight, it just initialize some ally parameters.
     */
    protected BasicFight() {
        this.moveToLearn = Move.NULLMOVE;
        this.allyPkm = this.player.getSquad().getPokemonList().get(FIRST_ELEM);
        for (final PokemonInBattle pkm : this.player.getSquad().getPokemonList()) {
            this.allyPkmsBoosts.put(pkm, createBoostsMap());
        }
    }

    /**
     * Create a battle boosts map for a pokemon.
     * 
     * @return  A boost map that store the battle {@link model.pokemon.Stat}.
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
        this.isAllyExhausted = false;
        this.isEnemyExhausted = false;
        this.allyEff = Effectiveness.NORMAL;
        this.enemyEff = Effectiveness.NORMAL;
        this.levelUp = false;
    }

    
    /**
     * Resolve the ally turn using the chosen move by user.
     * 
     * @param move      The chosen {@link model.pokemon.Move}.
     */
    public void allyTurn(final Move move) {
        applyMove(move, allyPkm, enemyPkm);
    }

    /**
     * Resolve the enemy turn using a move. This is a template method.
     * It used the method calculationEnemyMove() specialized by subclasses.
     */
    public void enemyTurn() {
        this.enemyMove = calculationEnemyMove();
        applyMove(enemyMove, enemyPkm, allyPkm);
    }

    /**
     * Resolve the move use.
     * 
     * @param move      The {@link model.pokemon.Move} used.
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
        if (pkm.equals(this.allyPkm)) {
            this.isAllyExhausted = pkm.getCurrentHP() == 0;
        } else {
            this.isEnemyExhausted = pkm.getCurrentHP() == 0;
        }
    }

    /**
     * Calculate the enemy move to use.
     * 
     * @return          The {@link model.pokemon.Move} used by enemy {@link model.pokemon.Pokemon}.
     */
    protected abstract Move calculationEnemyMove();

    /**
     * Get the enemy battle stat value of the same type of the stat insert as argument.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to get.
     * @return          The relative battle stat value of enemy pokemon.
     */
    public abstract double getEnemyBoost(final Stat stat);

    /**
     * Replace the enemy battle stat value of the same type of the stat insert as argument.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to modified.
     * @param d         The battle stat value to replace.
     */
    public abstract void setEnemyBoost(final Stat stat, final Double d);

    /**
     * Get the ally battle stat value of the same type of the stat insert as argument.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to get.
     * @return          The relative battle stat value of ally pokemon.
     */
    public double getAllyBoost(final Stat stat) {
        return this.allyPkmsBoosts.get(this.allyPkm).get(stat);
    }

    /**
     * Replace the ally battle stat value of the same type of the stat insert as argument.
     * 
     * @param stat      The battle {@link model.pokemon.Stat} to modified.
     * @param d         The battle stat value to replace.
     */
    public void setAllyBoost(final Stat stat, final Double d) {
        this.allyPkmsBoosts.get(this.allyPkm).replace(stat, d);
    }

    /**
     * Resolve a move that target a boost stat. This is a template method.
     * It used the methods setEnemyBoost() and getEnemyBoost() specialized by subclasses.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param move      The {@link model.pokemon.Move} used.
     */
    protected void applyMoveOnBoost(final PokemonInBattle striker, final PokemonInBattle stricken,
            final Move move) {
        double newBoostValue;
        double moveValue = move.getValue() * BOOST_COEFF;
        if (this.allyPkm.equals(striker)) {
            if (move.isOnEnemy()) {
                newBoostValue = getEnemyBoost(move.getStat()) - moveValue;
                if (newBoostValue < MIN_BOOST_VALUE) {
                    newBoostValue = MIN_BOOST_VALUE;
                    this.allyEff = Effectiveness.CANNOTDECREASE;
                }
                setEnemyBoost(move.getStat(), newBoostValue);
            } else {
                newBoostValue = getAllyBoost(move.getStat()) + (moveValue * BOOST_COEFF_INCR);
                if (newBoostValue > MAX_BOOST_VALUE) {
                    newBoostValue = MAX_BOOST_VALUE;
                    this.allyEff = Effectiveness.CANNOTINCREASE;
                }
                setAllyBoost(move.getStat(), newBoostValue);
            }
        } else {
            if (move.isOnEnemy()) {
                newBoostValue = getAllyBoost(move.getStat()) - moveValue;
                if (newBoostValue < MIN_BOOST_VALUE) {
                    newBoostValue = MIN_BOOST_VALUE;
                    this.enemyEff = Effectiveness.CANNOTDECREASE;
                }
                setAllyBoost(move.getStat(), newBoostValue);
            } else {
                newBoostValue = getEnemyBoost(move.getStat()) + moveValue;
                if (newBoostValue > MAX_BOOST_VALUE) {
                    newBoostValue = MAX_BOOST_VALUE;
                    this.enemyEff = Effectiveness.CANNOTINCREASE;
                }
                setEnemyBoost(move.getStat(), newBoostValue);
            }
        }
    }

    /**
     * Resolve a move that do damage. This is a template method.
     * It used the methods getEnemyBoost() specialized by subclasses.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param move      The {@link model.pokemon.Move} used.
     */
    protected void applyDamage(final PokemonInBattle striker, final PokemonInBattle stricken,
            final Move move) {
        isEffective(striker, stricken, move);
        final double atkBoost;
        final double defBoost;
        if (striker.equals(this.allyPkm)) {
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
     * Calculate the effectiveness.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param move      The {@link model.pokemon.Move} used.
     * @return          The effective multiplier.
     */
    protected double isEffective(final PokemonInBattle striker, final PokemonInBattle stricken, 
            final Move move) {
        final double effectiveValue = WeaknessTable.getWeaknessTable().getMultiplierAttack(move.getType(), stricken.getPokedexEntry().getFirstType(),
                stricken.getPokedexEntry().getSecondType());
        if (effectiveValue >= SUPER_EFFECTIVE) {
            if (striker.equals(this.allyPkm)) {
                this.allyEff = Effectiveness.SUPEREFFECTIVE;
            } else {
                this.enemyEff = Effectiveness.SUPEREFFECTIVE;
            }
        } else if (effectiveValue <= IMMUNE) {
            if (striker.equals(this.allyPkm)) {
                this.allyEff = Effectiveness.IMMUNE;
            } else {
                enemyEff = Effectiveness.IMMUNE;
            }
        } else if (effectiveValue <= LESS_EFFECTIVE) {
            if (striker.equals(this.allyPkm)) {
                this.allyEff = Effectiveness.LESSEFFECTIVE;
            } else {
                enemyEff = Effectiveness.LESSEFFECTIVE;
            }
        } else {
            if (striker.equals(this.allyPkm)) {
                this.allyEff = Effectiveness.NORMAL;
            } else {
                this.enemyEff = Effectiveness.NORMAL;
            }
        }
        if (move.equals(Move.SPLASH)) {
            if (striker.equals(this.allyPkm)) {
                this.allyEff = Effectiveness.NONE;
            } else {
                this.enemyEff = Effectiveness.NONE;
            }
        }
        return effectiveValue;
    }

    /**
     * Calculate the stab value.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param move      The {@link model.pokemon.Move} used.      
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
     * Calculate the damage done. This is a template method.
     * It used the methods getEnemyMove() specialized by subclasses.
     * 
     * @param striker   The {@link model.pokemon.Pokemon} which use the move.
     * @param stricken  The {@link model.pokemon.Pokemon} which is stricken by the move.
     * @param atkBoost  The battle boost ATK of the striker pokemon.
     * @param defBoost  The battle boost DEF of the stricken pokemon.
     * @param move      The {@link model.pokemon.Move} used.
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
        return this.isAllyFastest = (this.allyPkm.getStat(Stat.SPD) * getAllyBoost(Stat.SPD))
                >= (this.enemyPkm.getStat(Stat.SPD) * getEnemyBoost(Stat.SPD));
    }

    /**
     * Give to ally pokemon the experience of enemy pokemon and if 
     * is enough to level up, ally pokemon levels up.
     * 
     * @param exp       The experience gained ({@link AbstractFight#getExp()}) by beating enemy pokemon.
     * @return          True, if ally {@link model.pokemon.Pokemon} levels up.
     */
    public boolean giveExpAndCheckLvlUp(final int exp) {
        if (this.allyPkm.getNecessaryExp() <= exp) {
            this.allyPkm.setExp(exp - this.allyPkm.getNecessaryExp());
            this.allyPkm.levelUp();
            return true;
        }
        this.allyPkm.setExp(this.allyPkm.getStat(Stat.EXP) + exp);
        return false;
    }

    /**
     * Return the experience gained by beating the enemy pokemon according to rarity.
     * 
     * @return          The experience gained.
     */
    protected double expBaseCalculation() {
        double baseExp;
        switch(this.enemyPkm.getPokedexEntry().getRarity()){
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
        baseExp = baseExp * this.enemyPkm.getStat(Stat.LVL);
        return baseExp;
    }

}