package model.fight;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
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
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.squad.Squad;

/**
 * Abstract class which must be extended for create a concrete fight class.
 * 
 * This class is extended by {@link model.fight.FightVsWildPkm}
 * This class is extended by {@link model.fight.FightVsTrainer}
 */
public abstract class AbstractFight extends BasicFight implements Fight {

    protected static final int ATTACKS_TO_DO = 2;
    protected static final int EXP_COEFFICIENT = 7;
    protected static final String EXP_MESSAGE = "Exp gained: ";

    protected List<PokemonInBattle> pkmsThatMustEvolve = new ArrayList<>();
    protected boolean runValue;

    protected AbstractFight() {
        super();
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
        MainController.getController().getFightController().resolveRun(runValue, enemyMove, isAllyExhausted);
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
        MainController.getController().getFightController().resolvePokemon(allyPkm, enemyMove, isAllyExhausted);
    }

    @Override
    public void itemTurn(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
        reset();
        player.getInventory().consumeItem(itemToUse);
        if (applyItem(itemToUse, pkm)) { 
            if (itemToUse.getType() == ItemType.POKEBALL) {
                MainController.getController().getFightController().resolveItem(itemToUse, pkm, null, isAllyExhausted);
                return;
            } else {
                enemyTurn();
                MainController.getController().getFightController().resolveItem(itemToUse, pkm, enemyMove, isAllyExhausted);
            }
        } else {
            enemyTurn();
            MainController.getController().getFightController().resolveItem(itemToUse, pkm, enemyMove, isAllyExhausted);
        }
    }

    @Override
    public List<PokemonInBattle> getPkmsThatMustEvolve() {
        pkmsThatMustEvolve = new ArrayList<>();
        for (final PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            if (pkm.checkIfEvolves()) {
                pkmsThatMustEvolve.add(pkm);
            }
        }
        return pkmsThatMustEvolve;
    }

    @Override
    public void evolvePkms() {
        int hpBeforeEvolution;
        int hpGainedByLvl;
        for (PokemonInBattle pkm : pkmsThatMustEvolve) {
            hpBeforeEvolution = pkm.getStat(Stat.MAX_HP);
            pkm.evolve();
            pkm.levelUp();
            hpGainedByLvl = pkm.getStat(Stat.MAX_HP) - hpBeforeEvolution;
            pkm.heal(hpGainedByLvl);
        }
    }

    /**
     * Get the enemy pokemon which is in battle.
     * @return          The current enemy {@link model.pokemon.Pokemon}.
     */
    @Override
    public Pokemon getCurrentEnemyPokemon() {
        return enemyPkm;
    }

    /**
     * @see {@link BasicFight#getEnemyBoost(Stat)}
     */
    public abstract double getEnemyBoost(final Stat stat);

    /**
     * @see {@link BasicFight#setEnemyBoost(Stat, Double)}
     */
    public abstract void setEnemyBoost(final Stat stat, final Double d);

    /**
     * Resolve the run option.
     * 
     * @return                                  True if escape from {@link Fight}.
     * @throws CannotEscapeFromTrainerException If the user fight against a {@link model.trainer.Trainer}.
     */
    protected abstract boolean applyRun() throws CannotEscapeFromTrainerException;

    /**
     * Resolve the use of an {@link model.items.Item}.
     * 
     * @param itemToUse                         The target {@link model.items.Item}.
     * @param pkm                               The target {@link model.pokemon.Pokemon}.
     * @return                                  False if item is a {@link model.items.Pokeball} and the {@link model.pokemon.Pokemon} isn't captured.
     * @throws PokemonIsExhaustedException      If target {@link model.pokemon.Pokemon} is exhausted.
     * @throws PokemonNotFoundException         If target {@link model.pokemon.Pokemon} was not found.
     * @throws CannotCaughtTrainerPkmException  If itemToUse is a {@link model.items.Pokeball} and the user is fight against {@link model.trainer.Trainer}.
    */
    protected boolean applyItem(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException {
        switch(itemToUse.getType()) {
        case BOOST:
            final Boost boost = (Boost) itemToUse;
            final Double newBoostValue = getAllyBoost(boost.getStat()) + boost.getCoeff();
            setAllyBoost(boost.getStat(), newBoostValue);
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
            if(pkm.getCurrentHP() == 0) {
                throw new PokemonIsExhaustedException();
            }
            final Potion potion = (Potion) itemToUse;
            potion.effect(player, pkm);
        }
        return true;
    }

    /**
     * Resolve the use of a {@link model.items.Pokeball}.
     * 
     * @param itemToUse                         The {@link model.items.Pokeball} to use.
     * @return                                  True if enemy {@link model.pokemon.Pokemon} is caught.
     * @throws CannotCaughtTrainerPkmException  If the user fight against a {@link model.trainer.Trainer}.
     */
    protected abstract boolean useBall(Item itemToUse) throws CannotCaughtTrainerPkmException;

    /**
     * Calculate the exp gained by defeating a {@link model.pokemon.Pokemon}.
     * 
     * @return The exp gained by beating a {@link model.pokemon.Pokemon}.
     */
    protected abstract int getExp();
}