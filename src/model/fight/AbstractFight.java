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
 * This class is extended by {@link model.fight.FightVsWildPkm}.
 * This class is extended by {@link model.fight.FightVsTrainer}.
 */
public abstract class AbstractFight extends BasicFight implements Fight {

    /**
     * The standard number of attacks which must be resolve in a battle turn.
     */
    protected static final int ATTACKS_TO_DO = 2;
    /**
     * An experience coefficient used in the experience formula.
     */
    protected static final int EXP_COEFFICIENT = 7;
    /**
     * The string to show when an ally pokemon defeat an enemy.
     */
    protected static final String EXP_MESSAGE = "Exp gained: ";
    /**
     * The list of pokemon that must contains the ally pokemons,
     * which must evolve at the end of fight.
     */
    protected List<PokemonInBattle> pkmsThatMustEvolve = new ArrayList<>();
    /**
     * The value which indicates if the ally if escape from fight.
     */
    protected boolean runValue;
    protected boolean levelUp = false;

    /**
     * A simple constructor for AbstractFight, it just call the superclass constructor
     * because there are not yet enemies parameters to initialize.
     */
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
        return pkmsThatMustEvolve;
    }

    @Override
    public void evolvePkms() {
        int hpBeforeEvolution;
        int hpGainedByLvl;
        for (PokemonInBattle pkm : pkmsThatMustEvolve) {
            hpBeforeEvolution = pkm.getStat(Stat.MAX_HP);
            pkm.evolve();
            hpGainedByLvl = pkm.getStat(Stat.MAX_HP) - hpBeforeEvolution;
            pkm.heal(hpGainedByLvl);
        }
    }

    @Override
    public Pokemon getCurrentEnemyPokemon() {
        return enemyPkm;
    }

    @Override
    public abstract double getEnemyBoost(final Stat stat);

    @Override
    public abstract void setEnemyBoost(final Stat stat, final Double d);

    /**
     * Resolve the run option.
     * 
     * @return                                  True if escape successfully from a {@link FightVsWildPkm}.
     * @throws CannotEscapeFromTrainerException If the user is in a {@link FightVsTrainer}.
     */
    protected abstract boolean applyRun() throws CannotEscapeFromTrainerException;

    /**
     * Resolve the use of an item.
     * 
     * @param itemToUse                         The target {@link model.items.Item}.
     * @param pkm                               The target {@link model.pokemon.Pokemon}(if it is present).
     * @return                                  False if item is a {@link model.items.Pokeball} and the wild {@link model.pokemon.Pokemon} isn't captured.
     * @throws PokemonIsExhaustedException      If the parameter pkm is exhausted.
     * @throws PokemonNotFoundException         If the parameter pkm was not found.
     * @throws CannotCaughtTrainerPkmException  If itemToUse is a {@link model.items.Pokeball} and the user is fight in a {@link FightVsTrainer}.
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
     * Resolve the use of a pokeball.
     * 
     * @param itemToUse                         The {@link model.items.Pokeball} to use.
     * @return                                  True if enemy {@link model.pokemon.Pokemon} is caught.
     * @throws CannotCaughtTrainerPkmException  If the user fight in a {@link FightVsTrainer}.
     */
    protected abstract boolean useBall(Item itemToUse) throws CannotCaughtTrainerPkmException;

    /**
     * Calculate the exp gained by defeating a pokemon.
     * 
     * @return The exp gained by defeating a {@link model.pokemon.Pokemon}.
     */
    protected abstract int getExp();
}