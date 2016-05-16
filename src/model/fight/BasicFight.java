package model.fight;

import java.util.HashMap;
import java.util.Map;

import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;

/**
 * Abstract class which provides the basic method for perform operation in fight classes 
 * that implements the interface {@link model.fight.Fight}.
 * 
 * This class is extended by {@link model.fight.AbstractFight}.
 */
public abstract class BasicFight {
    protected static final int FIRST_ELEM = 0;
    protected static final double BOOST_COEFF = 0.15;
    protected static final double BOOST_COEFF_INCR = 2;
    protected static final double MIN_BOOST_VALUE = 0.25;
    protected static final double MAX_BOOST_VALUE = 2.8;
    protected static final double STAB_ACTIVE = 1.5;
    protected static final double STAB_DISABLED = 1;
    protected static final int SUPER_EFFECTIVE = 2;
    protected static final double LESS_EFFECTIVE = 0.5;
    protected static final int MIN_DAMAGE = 1;

    protected Player player = PlayerImpl.getPlayer();
    protected PokemonInBattle allyPkm;
    protected PokemonInBattle enemyPkm;
    protected boolean isAllyExhausted;
    protected boolean isEnemyExhausted;
    protected Effectiveness allyEff;
    protected Effectiveness enemyEff;
    protected double effectiveValue;
    protected double stab;
    protected boolean isAllyFastest;
    protected Move enemyMove;
    protected Map<PokemonInBattle, Map<Stat, Double>> allyPkmsBoosts = new HashMap<>();
    protected Move moveToLearn;

    protected BasicFight() {
        moveToLearn = Move.NULLMOVE;
        allyPkm = player.getSquad().getPokemonList().get(FIRST_ELEM);
        for (final PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            this.allyPkmsBoosts.put(pkm, createBoostsMap());
        }
    }

    protected Map<Stat, Double> createBoostsMap() {
        final Map<Stat, Double> boosts = new HashMap<>();
        boosts.put(Stat.ATK, 1.0);
        boosts.put(Stat.DEF, 1.0);
        boosts.put(Stat.SPD, 1.0);
        return boosts;
    }

    protected void reset() {
        isAllyExhausted = false;
        isEnemyExhausted = false;
        allyEff = Effectiveness.NORMAL;
        enemyEff = Effectiveness.NORMAL;
    }

    public void allyTurn(final Move move) {
        applyMove(move, allyPkm, enemyPkm);
    }

    public void enemyTurn() {
        enemyMove = calculationEnemyMove();
        applyMove(enemyMove, enemyPkm, allyPkm);
    }

    protected void applyMove(final Move move, final PokemonInBattle striker, final PokemonInBattle stricken) {
        if (move.getStat() == Stat.MAX_HP) {
            applyDamage(striker, stricken, move);
            checkAndSetIsExhausted(stricken);
        } else {
            applyMoveOnBoost(striker, stricken, move);
        }
    }

    public void checkAndSetIsExhausted(final PokemonInBattle pkm) {
        if (pkm.equals(allyPkm)) {
            isAllyExhausted = pkm.getCurrentHP() == 0;
        } else {
            isEnemyExhausted = pkm.getCurrentHP() == 0;
        }
    }

    protected abstract Move calculationEnemyMove();

    public abstract double getEnemyBoost(final Stat stat);

    public abstract void setEnemyBoost(final Stat stat, final Double d);

    public double getAllyBoost(final Stat stat) {
        return allyPkmsBoosts.get(allyPkm).get(stat);
    }

    public void setAllyBoost(final Stat stat, final Double d) {
        allyPkmsBoosts.get(allyPkm).replace(stat, d);
    }

    protected void applyMoveOnBoost(final PokemonInBattle striker, final PokemonInBattle stricken, final Move move) {
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

    protected void applyDamage(final PokemonInBattle striker, final PokemonInBattle stricken, final Move move) {
        isEffective(striker, stricken, move);
        stab = stabCalculation(striker, move);
        final double atkBoost;
        final double defBoost;
        if (striker.equals(allyPkm)) {
            atkBoost = getAllyBoost(Stat.ATK);
            defBoost = getEnemyBoost(Stat.DEF);
        } else {
            atkBoost = getEnemyBoost(Stat.ATK);
            defBoost = getAllyBoost(Stat.DEF);
        }
        final int damageDone = damageCalculation(striker, stricken, atkBoost, defBoost, move);
        stricken.damage(damageDone);
    }

    protected void isEffective(final PokemonInBattle striker, final PokemonInBattle stricken, 
            final Move move) {
        effectiveValue = WeaknessTable.getWeaknessTable().getMultiplierAttack(move.getType(), stricken.getPokedexEntry().getFirstType(),
                stricken.getPokedexEntry().getSecondType());
        if (effectiveValue >= SUPER_EFFECTIVE) {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.SUPEREFFECTIVE;
            } else {
                enemyEff = Effectiveness.SUPEREFFECTIVE;
            }
        } else if (effectiveValue <= LESS_EFFECTIVE) {
            if (striker.equals(allyPkm)) {
                allyEff = Effectiveness.LESSEFFECTIVE;
            } else {
                enemyEff = Effectiveness.LESSEFFECTIVE;
            }
        }
    }

    protected double stabCalculation(final PokemonInBattle striker, final Move move) {
        if (striker.getPokedexEntry().getFirstType() == move.getType() 
                || striker.getPokedexEntry().getSecondType() == move.getType()) {
            return STAB_ACTIVE;
        }
        return 1;
    }

    protected int damageCalculation(final PokemonInBattle striker, final PokemonInBattle stricken, 
            final double atkBoost, final double defBoost, final Move move) {
        final int damage = (int) ((((2 * striker.getStat(Stat.LVL) + 10) * (striker.getStat(Stat.ATK) 
                * atkBoost * move.getValue())) / ((stricken.getStat(Stat.DEF) * defBoost) * 250 + 2)) * stab * effectiveValue);
        if (damage <= 0) {
            return MIN_DAMAGE;
        }
        return damage;
    }

    public boolean setIsAllyFastest() {
        return isAllyFastest = (allyPkm.getStat(Stat.SPD) * getAllyBoost(Stat.SPD))
                >= (enemyPkm.getStat(Stat.SPD) * getEnemyBoost(Stat.SPD));
    }

    public boolean giveExpAndCheckLvlUp(final int exp) {
        if (allyPkm.getNecessaryExp() <= exp) {
            allyPkm.setExp(exp - allyPkm.getNecessaryExp());
            allyPkm.levelUp();
            return true;
        }
        allyPkm.setExp(allyPkm.getStat(Stat.EXP) + exp);
        return false;
    }

    protected double expBaseCalculation() {
        //TODO testare se è bilanciata la quantità di baseExp
        double baseExp;
        switch(enemyPkm.getPokedexEntry().getRarity()){
        case COMMON:
            baseExp = 80;
            break;
        case UNCOMMON:
            baseExp = 100;
            break;
        case RARE:
            baseExp = 120;
            break;
        case VERY_RARE:
            baseExp = 150;
            break;
        case STARTER:
            baseExp = 180;
            break;
        case LEGENDARY:
            baseExp = 300;
            break;
        //default ricopre il caso unfindable
        default:
            baseExp = 0;
        }
        baseExp = baseExp * enemyPkm.getStat(Stat.LVL);
        return baseExp;
    }

}