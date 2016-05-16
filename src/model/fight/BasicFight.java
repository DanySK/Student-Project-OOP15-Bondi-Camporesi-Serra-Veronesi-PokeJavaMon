package model.fight;

import java.util.HashMap;
import java.util.Map;

import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;

public abstract class BasicFight {
    protected static final int FIRST_ELEM = 0;
    protected static final int BALANCE_BOOST_MOVE = 100;
    protected static final double MIN_BOOST_VALUE = 0.25;
    protected static final double MAX_BOOST_VALUE = 1.75;
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

    protected BasicFight(){
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
        if (move.getStat() == Stat.MAX_HP) {
            applyDamage(allyPkm, enemyPkm, move);
            checkAndSetIsExhausted(enemyPkm);
        } else {
            applyMoveOnBoost(allyPkm, enemyPkm, move);
        }
    }

    public void enemyTurn() {
        enemyMove = calculationEnemyMove();
        if (enemyMove.getStat() == Stat.MAX_HP) {
            applyDamage(enemyPkm, allyPkm, enemyMove);
            checkAndSetIsExhausted(allyPkm);
        } else {
            applyMoveOnBoost(enemyPkm, allyPkm, enemyMove);
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

    public double getAllyBoost(final Stat stat){
        return allyPkmsBoosts.get(allyPkm).get(stat);
    }

    public void setAllyBoost(final Stat stat, final Double d){
        allyPkmsBoosts.get(allyPkm).replace(stat, d);
    }

    protected void applyMoveOnBoost(final PokemonInBattle stricker, final PokemonInBattle stricked, final Move move) {
        double newBoostValue;
        double moveValue = (double) move.getValue() / BALANCE_BOOST_MOVE;
        if (allyPkm.equals(stricker)) {
            if (move.isOnEnemy()) {
                newBoostValue = getEnemyBoost(move.getStat()) - moveValue;
                if (newBoostValue < MIN_BOOST_VALUE) {
                    newBoostValue = MIN_BOOST_VALUE;
                    allyEff = Effectiveness.CANNOTDECREASE;
                }
                setEnemyBoost(move.getStat(), newBoostValue);
            } else {
                newBoostValue = getAllyBoost(move.getStat()) + moveValue;
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
        //DA ELIMINAREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        if (allyPkm.equals(stricker)) {
            if (move.isOnEnemy()) {
                System.out.println(this.getEnemyBoost(move.getStat()));
            } else {
                System.out.println(this.getAllyBoost(move.getStat()));
            }
        } else {
            if (move.isOnEnemy()) {
                System.out.println(this.getAllyBoost(move.getStat()));
            } else {
                System.out.println(this.getEnemyBoost(move.getStat()));
            }
        }
    }

    protected void applyDamage(final PokemonInBattle stricker, final PokemonInBattle stricked, final Move move) {
        isEffective(stricker, stricked, move);
        stab = stabCalculation(stricker, move);
        final double atkBoost;
        final double defBoost;
        if (stricker.equals(allyPkm)) {
            atkBoost = getAllyBoost(Stat.ATK);
            defBoost = getEnemyBoost(Stat.DEF);
        } else {
            atkBoost = getEnemyBoost(Stat.ATK);
            defBoost = getAllyBoost(Stat.DEF);
        }
        final int damageDone = damageCalculation(stricker, stricked, atkBoost, defBoost, move);
        stricked.damage(damageDone);
    }

    protected void isEffective(final PokemonInBattle stricker, final PokemonInBattle stricked, 
            final Move move) {
        effectiveValue = WeaknessTable.getWeaknessTable().getMultiplierAttack(move.getType(), stricked.getPokemon().getFirstType(),
                stricked.getPokemon().getSecondType());
        if (effectiveValue >= SUPER_EFFECTIVE) {
            if (stricker.equals(allyPkm)) {
                allyEff = Effectiveness.SUPEREFFECTIVE;
            } else {
                enemyEff = Effectiveness.SUPEREFFECTIVE;
            }
        } else if (effectiveValue <= LESS_EFFECTIVE) {
            if (stricker.equals(allyPkm)) {
                allyEff = Effectiveness.LESSEFFECTIVE;
            } else {
                enemyEff = Effectiveness.LESSEFFECTIVE;
            }
        }
    }

    protected double stabCalculation(final PokemonInBattle stricker, final Move move) {
        if (stricker.getPokemon().getFirstType() == move.getType() 
                || stricker.getPokemon().getSecondType() == move.getType()) {
            return STAB_ACTIVE;
        }
        return 1;
    }

    protected int damageCalculation(final PokemonInBattle stricker, final PokemonInBattle stricked, 
            final double atkBoost, final double defBoost, final Move move) {
        final int damage = (int) ((((2 * stricker.getStat(Stat.LVL) + 10) * (stricker.getStat(Stat.ATK) 
                * atkBoost * move.getValue())) / ((stricked.getStat(Stat.DEF) * defBoost) * 250 + 2)) * stab * effectiveValue);
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
        switch(enemyPkm.getPokemon().getRarity()){
        case COMMON:
            baseExp = 120;
            break;
        case UNCOMMON:
            baseExp = 150;
            break;
        case RARE:
            baseExp = 180;
            break;
        case VERY_RARE:
            baseExp = 230;
            break;
        case STARTER:
            baseExp = 280;
            break;
        case LEGENDARY:
            baseExp = 450;
            break;
        case UNFINDABLE:
        	baseExp = 350;
        default:
            baseExp = 0;
        }
        baseExp = baseExp * enemyPkm.getStat(Stat.LVL);
        return baseExp;
    }

}