package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.items.Boost;
import model.items.Item;
import model.items.Potion;
import model.items.Item.ItemType;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;
import model.squad.Squad;

public abstract class AbstractFight implements Fight {
    protected static final int FIRST_ELEM = 0;
    protected static final double STAB_ACTIVE = 1.5;
    protected static final int SUPER_EFFECTIVE = 2;
    protected static final double LESS_EFFECTIVE = 0.5;
    protected static final int MIN_DAMAGE = 1;
    protected static final double MIN_BOOST_VALUE = 0.25;
    protected static final double MAX_BOOST_VALUE = 1.75;
    protected static final int BALANCE_BOOST_MOVE = 100;
    protected static final int ATTACKS_TO_DO = 2;
    protected static final int EXP_COEFFICIENT = 7;
    protected static final String EXP_MESSAGE = "You defeated a pokemon, your pokemon get experience: ";

    protected Player player = PlayerImpl.getPlayer();
    protected PokemonInBattle allyPkm;
    protected PokemonInBattle enemyPkm;
    protected boolean isAllyExhausted;
    protected boolean isEnemyExhausted;
    protected double effectiveValue;
    protected Effectiveness allyEff;
    protected Effectiveness enemyEff;
    protected double stab;
    protected boolean isAllyFastest;
    protected Move enemyMove;
    protected Map<PokemonInBattle, Map<Stat, Double>> allyPkmsBoosts = new HashMap<>();
    protected final WeaknessTable table = WeaknessTable.getWeaknessTable();
    protected List<PokemonInBattle> pkmsThatMustEvolve = new ArrayList<>();
    protected boolean runValue;

    public AbstractFight() {
        allyPkm = player.getSquad().getPokemonList().get(FIRST_ELEM);
        for (final PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            this.allyPkmsBoosts.put(pkm, createBoostsMap());
        }
    }

    @Override
    public boolean checkLose(final Squad pkmSquad) {
        final List<PokemonInBattle> pkmSquadList = pkmSquad.getPokemonList();
        for (final PokemonInBattle pkm : pkmSquadList) {
            if (pkm.getCurrentHP() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void runTurn() throws CannotEscapeFromTrainerException {
        reset();
        if (!applyRun()) {
            enemyTurn();
        }
        Controller.getController().getFightController().resolveRun(runValue, enemyMove, isAllyExhausted);
    }

    @Override
    public void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException {
        if (pkm.getCurrentHP() == 0) {
            throw new PokemonIsExhaustedException();
        } else if (pkm.equals(allyPkm)) {
            throw new PokemonIsFightingException();
        }
        allyPkm = pkm;
        int pkmPos = 0;
        for (final PokemonInBattle p : player.getSquad().getPokemonList()) {
            if (pkm.equals(p)) {
                break;
            }
            pkmPos += 1;
        }
        player.getSquad().switchPokemon(0, pkmPos);
        allyPkm = pkm;
    }

    @Override
    public void changeTurn(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException {
        reset();
        applyChange(pkm);
        enemyTurn();
        Controller.getController().getFightController().resolvePokemon(allyPkm, enemyMove, isAllyExhausted);
    }

    @Override
    public void itemTurn(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
        reset();
        player.getInventory().consumeItem(itemToUse);
        if (applyItem(itemToUse, pkm)) { 
            if (itemToUse.getType() == ItemType.POKEBALL) {
                Controller.getController().getFightController().resolveItem(itemToUse, pkm, null, isAllyExhausted);
                return;
            } else {
                enemyTurn();
                Controller.getController().getFightController().resolveItem(itemToUse, pkm, enemyMove, isAllyExhausted);
            }
        } else {
            enemyTurn();
            Controller.getController().getFightController().resolveItem(itemToUse, pkm, enemyMove, isAllyExhausted);
        }
    }

    @Override
    public List<PokemonInBattle> getPkmsThatMustEvolve() {
        pkmsThatMustEvolve = new ArrayList<>();
        for (final PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            System.out.println(pkm.checkIfEvolves());
            if (pkm.checkIfEvolves()) {
                pkmsThatMustEvolve.add(pkm);
            }
        }
        return pkmsThatMustEvolve;
    }

    @Override
    public void evolvePkms() {
        for (PokemonInBattle pkm : pkmsThatMustEvolve) {
            pkm.evolve();
        }
    }

    @Override
    public Pokemon getCurrentEnemyPokemon() {
        return enemyPkm;
    }

    protected Map<Stat, Double> createBoostsMap() {
        final Map<Stat, Double> boosts = new HashMap<>();
        boosts.put(Stat.ATK, 1.0);
        boosts.put(Stat.DEF, 1.0);
        boosts.put(Stat.SPD, 1.0);
        return boosts;
    }

    protected abstract boolean applyRun() throws CannotEscapeFromTrainerException;

    protected void reset() {
        isAllyExhausted = false;
        isEnemyExhausted = false;
        allyEff = Effectiveness.NORMAL;
        enemyEff = Effectiveness.NORMAL;
    }

    protected void enemyTurn() {
        enemyMove = calculationEnemyMove();
        if (enemyMove.getStat() == Stat.HP) {
            applyDamage(enemyPkm, allyPkm, enemyMove);
            checkAndSetIsExhausted(allyPkm);
        } else {
            applyMoveOnBoost(enemyPkm, allyPkm, enemyMove);
        }
    }

    protected boolean applyItem(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException {
        switch(itemToUse.getType()) {
        case BOOST:
            final Boost boost = (Boost) itemToUse;
            allyPkmsBoosts.get(allyPkm).replace(boost.getStat(), 
                    allyPkmsBoosts.get(allyPkm).get(boost.getStat()) + boost.getCoeff());
            break;
        case POKEBALL:
            if (useBall(itemToUse)) {
                try {
                    player.getSquad().add(enemyPkm);
                } catch (SquadFullException e) {
                    player.getBox().putCapturedPokemon(enemyPkm);
                }
                return true;
            }
            return false;
        case POTION:
            final Potion potion = (Potion) itemToUse;
            potion.effect(player, pkm);
        }
        return true;
    }

    protected abstract Move calculationEnemyMove();

    protected void applyDamage(final PokemonInBattle stricker, final PokemonInBattle stricked, final Move move) {
        isEffective(stricker, stricked, move);
        stab = stabCalculation(stricker, move);
        final double atkBoost;
        final double defBoost;
        if (stricker.equals(allyPkm)) {
            atkBoost = allyPkmsBoosts.get(stricker).get(Stat.ATK);
            defBoost = getEnemyBoost(Stat.DEF);
        } else {
            atkBoost = getEnemyBoost(Stat.ATK);
            defBoost = allyPkmsBoosts.get(stricked).get(Stat.ATK);
        }
        final int damageDone = damageCalculation(stricker, stricked, atkBoost, defBoost, move);
        stricked.damage(damageDone);
    }

    protected abstract double getEnemyBoost(final Stat stat);

    protected abstract void setEnemyBoost(final Stat stat, final Double d);

    protected void isEffective(final PokemonInBattle stricker, final PokemonInBattle stricked, 
            final Move move) {
        effectiveValue = table.getMultiplierAttack(move.getType(), stricked.getPokemon().getFirstType(),
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

    protected void checkAndSetIsExhausted(final PokemonInBattle pkm) {
        if (pkm.equals(allyPkm)) {
            isAllyExhausted = pkm.getCurrentHP() == 0;
        } else {
            isEnemyExhausted = pkm.getCurrentHP() == 0;
        }
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
                newBoostValue = allyPkmsBoosts.get(stricker).get(move.getStat()) + moveValue;
                if (newBoostValue > MAX_BOOST_VALUE) {
                    newBoostValue = MAX_BOOST_VALUE;
                    allyEff = Effectiveness.CANNOTINCREASE;
                }
                allyPkmsBoosts.get(stricker).replace(move.getStat(), newBoostValue);
            }
        } else {
            if (move.isOnEnemy()) {
                newBoostValue = allyPkmsBoosts.get(stricked).get(move.getStat()) - moveValue;
                if (newBoostValue < MIN_BOOST_VALUE) {
                    newBoostValue = MIN_BOOST_VALUE;
                    enemyEff = Effectiveness.CANNOTDECREASE;
                }
                allyPkmsBoosts.get(stricked).replace(move.getStat(), newBoostValue);
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

    protected abstract boolean useBall(Item itemToUse) throws CannotCaughtTrainerPkmException;

    protected void allyTurn(final Move move) {
        if (move.getStat() == Stat.HP) {
            applyDamage(allyPkm, enemyPkm, move);
            checkAndSetIsExhausted(enemyPkm);
        } else {
            applyMoveOnBoost(allyPkm, enemyPkm, move);
        }
    }

    protected double expBaseCalculation() {
        //TODO testare se è bilanciata la quantità di baseExp
        double baseExp;
        switch(enemyPkm.getPokemon().getRarity()){
        case COMMON:
            baseExp = 60;
            break;
        case UNCOMMON:
            baseExp = 90;
            break;
        case RARE:
            baseExp = 120;
            break;
        case STARTER:
            baseExp = 150;
            break;
        case LEGENDARY:
            baseExp = 300;
            break;
        default:
            baseExp = 1;
        }
        baseExp = baseExp * enemyPkm.getStat(Stat.LVL);
        return baseExp;
    }

    protected void giveExpAndCheckLvlUp(final int exp) {
        if (allyPkm.getNecessaryExp() <= exp) {
            allyPkm.setExp(exp - allyPkm.getNecessaryExp());
            allyPkm.levelUp();
        } else {
            allyPkm.setExp(allyPkm.getStat(Stat.EXP) + exp);
        }
    }

}