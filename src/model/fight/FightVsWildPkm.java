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
 * This class deals operations to manage a {@link Fight} against a wild pokemon.
 */
public class FightVsWildPkm extends AbstractFight {

    private static final int RUN_PROB_COEFF = 255;
    private final Map<Stat, Double> enemyPkmBoosts = new HashMap<>(createBoostsMap());

    /**
     * Simple constructor for FightVsWildPkm, it just initialize wild pokemon parameters.
     * 
     * @param pkm       The wild {@link model.pokemon.Pokemon} that appears.
     */
    protected FightVsWildPkm(final Pokemon pkm) {
        super();
        this.enemyPkm = (PokemonInBattle) pkm;
    }

    /**
     * Calculate the enemy move. This method returns a random move.
     * 
     * @return  The {@link model.pokemon.Move} used by enemy pokemon.
     */
    @Override
    protected Move calculationEnemyMove() {
        final Random numberMove = new Random();
        final List<Move> moves = new ArrayList<>();
        for (final Move m : this.enemyPkm.getCurrentMoves()) {
            if (m != Move.NULLMOVE) {
                moves.add(m);
            }
        }
        final int movesNumber = moves.size();
        return moves.get(numberMove.nextInt(movesNumber));
    }

    @Override
    public double getEnemyBoost(final Stat stat) {
        return this.enemyPkmBoosts.get(stat);
    }

    @Override
    public void setEnemyBoost(final Stat stat, final Double d) {
        this.enemyPkmBoosts.replace(stat, d);
    }

    @Override
    public boolean applyRun() throws CannotEscapeFromTrainerException {
        final Random escapeRoll = new Random();
        final int escapeChance = (32 * this.allyPkm.getStat(Stat.SPD)) / (this.enemyPkm.getStat(Stat.SPD) / 4) + 30;
        return this.runValue = escapeChance > escapeRoll.nextInt(RUN_PROB_COEFF);
    }

    @Override
    public boolean applyItem(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException {
        return super.applyItem(itemToUse, pkm);
    }

    @Override
    protected boolean useBall(final Item itemToUse) throws CannotCaughtTrainerPkmException {
        final Pokeball ball = (Pokeball) itemToUse;
        return ball.isCaptured(this.enemyPkm);
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
                if (this.isEnemyExhausted) {
                    final int hpBeforeLvUp = this.allyPkm.getStat(Stat.MAX_HP);
                    if (giveExpAndCheckLvlUp(getExp())) {
                        this.levelUp = true;
                        int hpAfterLvUp = this.allyPkm.getStat(Stat.MAX_HP);
                        hpAfterLvUp = hpAfterLvUp - hpBeforeLvUp;
                        this.allyPkm.heal(hpAfterLvUp);
                        if (this.allyPkm.getPokedexEntry().getMoveset().containsKey(this.allyPkm.getStat(Stat.LVL))) {
                            this.moveToLearn = this.allyPkm.getPokedexEntry().getMoveset().get(this.allyPkm.getStat(Stat.LVL));
                        }
                        else {
                            this.moveToLearn = Move.NULLMOVE;
                        }
                        if (this.allyPkm.checkIfEvolves()) {
                            this.pkmsThatMustEvolve.add(this.allyPkm);
                        }
                    } else {
                        this.moveToLearn = Move.NULLMOVE;
                    }
                    isEnd = true;
                }
            } else {
                enemyTurn();
                if (this.isAllyExhausted) {
                    isEnd = true;
                }
            }
            turnOrder = !turnOrder;
            attacksDone += 1;
        }
        if (attacksDone == ATTACKS_TO_DO) {
            if (this.isAllyFastest) {
                if (this.isAllyExhausted) {
                    //ally pokemon attacks, enemy pokemon attacks, ally pokemon is exhausted
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, null, this.moveToLearn);
                } else {
                    //ally pokemon attacks, enemy pokemon attacks
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, false, null, null, this.moveToLearn);
                }
            } else {
                if (this.isEnemyExhausted) {
                    //enemy pokemon attacks, ally pokemon attacks, enemy pokemon is exhausted
                    if (this.levelUp) {
                        MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, EXP_MESSAGE + getExp() + LVL_UP_MESS, this.moveToLearn);
                    } else {
                        MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, EXP_MESSAGE + getExp(), this.moveToLearn);
                    }
                } else {
                    //enemy pokemon attacks, ally pokemon attacks
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, false, null, null, this.moveToLearn);
                }
            }
        } else {
            if (this.isAllyFastest) {
                //ally pokemon attacks, enemy pokemon is exhausted
                if (this.levelUp) {
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, false, null, EXP_MESSAGE + getExp() + LVL_UP_MESS, this.moveToLearn);
                } else {
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, false, null, EXP_MESSAGE + getExp(), this.moveToLearn);
                }
            } else {
                //enemy pokemon attacks, ally pokemon is exhausted
                MainController.getController().getFightController().resolveAttack(null, null, this.enemyMove, this.enemyEff, this.isAllyFastest, false, null, null, this.moveToLearn);
            }
        }
    }

    @Override
    public int getExp() {
        return (int) (expBaseCalculation() / EXP_COEFFICIENT);
    }

}