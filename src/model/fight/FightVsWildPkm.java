package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.MainController;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.items.Pokeball;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;

/**
 * A concrete class which deals operations to manage a {@link Fight} against a wild pokemon.
 */
public class FightVsWildPkm extends AbstractFight {

    private static final int RUN_PROB_COEFF = 255;
    private final Map<Stat, Double> enemyPkmBoosts = new HashMap<>(createBoostsMap());

    /**
     * A constructor for {@link FightVsWildPkm}.
     * 
     * @param pkm       The wild {@link model.pokemon.Pokemon} that appears.
     */
    protected FightVsWildPkm(final Pokemon pkm) {
        super();
        enemyPkm = (PokemonInBattle) pkm;
    }

    /**
     * Calculate the enemy move. A wild pokemon do a random move.
     * 
     * @return  The {@link model.pokemon.Move} used by enemy {@link model.pokemon.Pokemon}.
     */
    @Override
    protected Move calculationEnemyMove() {
        final Random numberMove = new Random();
        final List<Move> moves = new ArrayList<>();
        for (final Move m : enemyPkm.getCurrentMoves()) {
            if (m != Move.NULLMOVE) {
                moves.add(m);
            }
        }
        final int movesNumber = moves.size();
        return moves.get(numberMove.nextInt(movesNumber));
    }

    @Override
    public double getEnemyBoost(final Stat stat) {
        return enemyPkmBoosts.get(stat);
    }

    @Override
    public void setEnemyBoost(final Stat stat, final Double d) {
        enemyPkmBoosts.replace(stat, d);
    }

    @Override
    public boolean applyRun() throws CannotEscapeFromTrainerException {
        final Random escapeRoll = new Random();
        final int escapeChance = (32 * allyPkm.getStat(Stat.SPD)) / (enemyPkm.getStat(Stat.SPD) / 4) + 30;
        return runValue = escapeChance > escapeRoll.nextInt(RUN_PROB_COEFF);
    }

    @Override
    public boolean applyItem(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException {
        return super.applyItem(itemToUse, pkm);
    }

    @Override
    protected boolean useBall(final Item itemToUse) throws CannotCaughtTrainerPkmException {
        final Pokeball ball = (Pokeball) itemToUse;
        return ball.isCaptured(enemyPkm);
    }

    @Override
    public void moveTurn(final Move move) {
        reset();
        int attacksDone = 0;
        boolean isEnd = false;
        boolean turnOrder = setIsAllyFastest();
        while (attacksDone < ATTACKS_TO_DO && !isEnd) {
            if (turnOrder) {
                allyTurn(move);
                if (isEnemyExhausted) {
                    final int hpBeforeLvUp = allyPkm.getStat(Stat.MAX_HP);
                    if (giveExpAndCheckLvlUp(getExp())) {
                        int hpAfterLvUp = allyPkm.getStat(Stat.MAX_HP);
                        hpAfterLvUp = hpAfterLvUp - hpBeforeLvUp;
                        allyPkm.heal(hpAfterLvUp);
                        if (allyPkm.getPokedexEntry().getMoveset().containsKey(allyPkm.getStat(Stat.LVL))) {
                            moveToLearn = allyPkm.getPokedexEntry().getMoveset().get(allyPkm.getStat(Stat.LVL));
                        }
                        else {
                            moveToLearn = Move.NULLMOVE;
                        }
                        if (allyPkm.checkIfEvolves()) {
                            pkmsThatMustEvolve.add(allyPkm);
                        }
                    } else {
                        moveToLearn = Move.NULLMOVE;
                    }
                    isEnd = true;
                }
            } else {
                enemyTurn();
                if (isAllyExhausted) {
                    isEnd = true;
                }
            }
            turnOrder = !turnOrder;
            attacksDone += 1;
        }
        if (attacksDone == ATTACKS_TO_DO) {
            if (isAllyFastest) {
                if (isAllyExhausted) {
                    //alleato attacca, nemico attacca, pokemon alleato esausto
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null, moveToLearn);
                } else {
                    //alleato attacca, nemico attacca, pokemon alleato sopravvive
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null, moveToLearn);
                }
            } else {
                if (isEnemyExhausted) {
                    //nemico attacca, alleato attacca, pokemon nemico esausto
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, EXP_MESSAGE + getExp(), moveToLearn);
                } else {
                    //nemico attacca, alleato attacca, pokemon nemico sopravvive
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null, moveToLearn);
                }
            }
        } else {
            if (isAllyFastest) {
                //alleato attacca per primo, pkm nemico esausto
                MainController.getController().getFightController().resolveAttack(move, allyEff, null, null, isAllyFastest, false, null, EXP_MESSAGE + getExp(), moveToLearn);
            } else {
                //nemico attaccata per primo, pkm alleato esausto
                MainController.getController().getFightController().resolveAttack(null, null, enemyMove, enemyEff, isAllyFastest, false, null, null, moveToLearn);
            }
        }
    }

    @Override
    public int getExp() {
        return (int) (expBaseCalculation() / EXP_COEFFICIENT);
    }

}