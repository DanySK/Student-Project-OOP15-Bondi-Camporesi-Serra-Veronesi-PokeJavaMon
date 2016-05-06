package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.Controller;
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

public class FightVsWildPkm extends AbstractFight {

    private static final int COEFFICIENT_PROB = 255;
    private final Map<Stat, Double> enemyPkmBoosts;

    public FightVsWildPkm(final Pokemon pkm) {
        super();
        enemyPkm = (PokemonInBattle) pkm;
        enemyPkmBoosts = new HashMap<>(createBoostsMap());
    }

    @Override
    protected Move calculationEnemyMove() {
        final Random numberMove = new Random();
        final List<Move> moves = new ArrayList<>();
        for (final Move m : enemyPkm.getCurrentMoves()) {
            if (m != null) {
                moves.add(m);
            }
        }
        final int movesNumber = moves.size();
        return moves.get(numberMove.nextInt(movesNumber));
    }

    @Override
    protected double getEnemyBoost(final Stat stat) {
        return enemyPkmBoosts.get(stat);
    }

    @Override
    protected void setEnemyBoost(final Stat stat, final Double d) {
        enemyPkmBoosts.replace(stat, d);
    }

    @Override
    public boolean applyRun() throws CannotEscapeFromTrainerException {
        final Random escapeRoll = new Random();
        final int escapeChance = (32 * allyPkm.getStat(Stat.SPD)) / (enemyPkm.getStat(Stat.SPD) / 4) + 30;
        return runValue = escapeChance > escapeRoll.nextInt(COEFFICIENT_PROB);
    }

    @Override//fatto per il test
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
                    giveExpAndCheckLvlUp(getExp());
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
                    Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null);
                } else {
                    //alleato attacca, nemico attacca, pokemon alleato sopravvive
                    Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null);
                }
            } else {
                if (isEnemyExhausted) {
                    //nemico attacca, alleato attacca, pokemon nemico esausto
                    Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, EXP_MESSAGE + getExp());
                } else {
                    //nemico attacca, alleato attacca, pokemon nemico sopravvive
                    Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null);
                }
            }
        } else {
            if (isAllyFastest) {
                //alleato attacca per primo, pkm nemico esausto
                Controller.getController().getFightController().resolveAttack(move, allyEff, null, null, isAllyFastest, false, null, EXP_MESSAGE + getExp());
            } else {
                //nemico attaccata per primo, pkm alleato esausto
                Controller.getController().getFightController().resolveAttack(null, null, enemyMove, enemyEff, isAllyFastest, false, null, null);
            }
        }
        reset();
    }

    protected boolean setIsAllyFastest() {
        return isAllyFastest = (allyPkm.getStat(Stat.SPD) * allyPkmsBoosts.get(allyPkm).get(Stat.SPD)) 
                >= (enemyPkm.getStat(Stat.SPD) * enemyPkmBoosts.get(Stat.SPD));
    }

    protected int getExp() {
        return (int) (expBaseCalculation() / EXP_COEFFICIENT);
    }

}