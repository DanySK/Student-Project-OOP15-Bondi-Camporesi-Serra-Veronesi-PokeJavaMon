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
import model.squad.Squad;

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
        for (PokemonInBattle pkm : pkmsThatMustEvolve) {
            pkm.evolve();
        }
    }

    @Override
    public Pokemon getCurrentEnemyPokemon() {
        return enemyPkm;
    }

    protected abstract boolean applyRun() throws CannotEscapeFromTrainerException;

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

    protected abstract boolean useBall(Item itemToUse) throws CannotCaughtTrainerPkmException;

    protected abstract int getExp();
}