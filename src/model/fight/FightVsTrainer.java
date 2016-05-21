package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.MainController;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonNotFoundException;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;
import model.trainer.GymLeader;
import model.trainer.Trainer;

/**
 * A concrete class which deals operations to manage a {@link Fight} against a trainer.
 */
public class FightVsTrainer extends AbstractFight {

    private static final int STANDARD_EFFECTIVENESS_VALUE = 1;
    private static final int BOOST_MOVE_DAMAGE = 0;
    private final Trainer trainer;
    private final Map<PokemonInBattle, Map<Stat, Double>> enemyPkmsBoosts = new HashMap<>();
    private static final String TRAINER_DEFEAT_MESS = "Money earned: ";
    private static final String GYM_LEADER_DEFEAT_MESS = "You gained a badge!";

    /**
     * Constructor for a {@link FightVsTrainer}.
     * 
     * @param trainer   The {@link model.trainer.Trainer} challenged.
     */
    protected FightVsTrainer(final Trainer trainer) {
        super();
        this.trainer = trainer;
        enemyPkm = this.trainer.getSquad().getPokemonList().get(FIRST_ELEM);
        for (final PokemonInBattle pkm : trainer.getSquad().getPokemonList()) {
            enemyPkmsBoosts.put(pkm, createBoostsMap());
        }
    }

    /**
     * Calculate the enemy move. A pokemon of a trainer, search and return the move 
     * which do more damage. If method don't find any move which hit HP, it return 
     * the first move in the move list.
     * 
     * @return  The {@link model.pokemon.Move} used by enemy {@link model.pokemon.Pokemon}.
     */
    @Override
    protected Move calculationEnemyMove() {
        final List<Integer> movesDamages = new ArrayList<>();
        int movDam = BOOST_MOVE_DAMAGE;
        int movPos = FIRST_ELEM;
        for (final Move move : enemyPkm.getCurrentMoves()) {
            if (move.getStat() == Stat.MAX_HP) {
                movesDamages.add(damageCalculation(enemyPkm, allyPkm, getEnemyBoost(Stat.ATK), getAllyBoost(Stat.DEF),
                        move, stabCalculation(enemyPkm, move), isEffective(enemyPkm, allyPkm, move)));
            } else {
                movesDamages.add(BOOST_MOVE_DAMAGE);
            }
        }
        for (int i = FIRST_ELEM; i < enemyPkm.getCurrentMoves().size(); i++) {
            if (movDam < movesDamages.get(i)) {
                movDam = movesDamages.get(i);
                movPos = i;
            }
        }
        return enemyPkm.getCurrentMoves().get(movPos);
    }

    @Override
    public double getEnemyBoost(final Stat stat) {
        return enemyPkmsBoosts.get(enemyPkm).get(stat);
    }

    @Override
    public void setEnemyBoost(final Stat stat, final Double d) {
        enemyPkmsBoosts.get(enemyPkm).replace(stat, d);
    }

    @Override
    public boolean applyRun() throws CannotEscapeFromTrainerException{
        throw new CannotEscapeFromTrainerException();
    }

    @Override
    public boolean applyItem(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException {
        return super.applyItem(itemToUse, pkm);
    }

    @Override
    protected boolean useBall(final Item itemToUse) throws CannotCaughtTrainerPkmException {
        throw new CannotCaughtTrainerPkmException();
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
                    final PokemonInBattle allyPkmNotUpdated = allyPkm;
                    final Map<Stat, Double> allyPkmBoost = allyPkmsBoosts.remove(allyPkmNotUpdated);
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
                    allyPkmsBoosts.put(allyPkm, allyPkmBoost);
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
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null, null);
                } else {
                    //alleato attacca, nemico attacca, pokemon alleato sopravvive
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null, null);
                }
            } else {
                if (isEnemyExhausted) {
                    //nemico attacca, alleato attacca, pokemon nemico esausto
                    if (checkLose(trainer.getSquad())) {
                        player.beatTrainer(trainer);
                        trainer.defeat();
                        if (trainer instanceof GymLeader) {
                            player.addBadge();
                            MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, 
                                    EXP_MESSAGE + getExp() + " " + TRAINER_DEFEAT_MESS + trainer.getMoney() + " " + GYM_LEADER_DEFEAT_MESS, moveToLearn);
                        } else {
                            MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, 
                                    EXP_MESSAGE + getExp() + " " + TRAINER_DEFEAT_MESS + trainer.getMoney(), moveToLearn);
                        }
                    } else {
                        trainerChange();
                        MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, enemyPkm, 
                                EXP_MESSAGE + getExp(), moveToLearn);
                    }
                } else {
                    //nemico attacca, alleato attacca, pokemon nemico sopravvive
                    MainController.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null, null);
                }
            }
        } else {
            if (isAllyFastest) {
                //alleato attacca per primo, pkm nemico esausto
                if (checkLose(trainer.getSquad())) {
                    player.beatTrainer(trainer);
                    trainer.defeat();
                    if (trainer instanceof GymLeader) {
                        player.addBadge();
                        MainController.getController().getFightController().resolveAttack(move, allyEff, null, null, isAllyFastest, true, null, 
                                EXP_MESSAGE + getExp() + " " + TRAINER_DEFEAT_MESS + trainer.getMoney() + " " + GYM_LEADER_DEFEAT_MESS, moveToLearn);
                    } else {
                        MainController.getController().getFightController().resolveAttack(move, allyEff, null, null, isAllyFastest, true, null, 
                                EXP_MESSAGE + getExp() + " " + TRAINER_DEFEAT_MESS + trainer.getMoney(), moveToLearn);
                    }
                } else {
                    trainerChange();
                    MainController.getController().getFightController().resolveAttack(move, allyEff, null, null, isAllyFastest, false, enemyPkm, EXP_MESSAGE + getExp(), moveToLearn);
                }
            } else {
                //nemico attacca per primo, pkm alleato esausto
                MainController.getController().getFightController().resolveAttack(null, null, enemyMove, enemyEff, isAllyFastest, false, null, null, null);
            }
        }
        reset();
    }

    @Override
    public int getExp() {
        return (int) (expBaseCalculation() * 1.5) / EXP_COEFFICIENT; 
    }

    /**
     * Apply the trainer change. The method search and send in battle the first 
     * pokemon which have the super effective types against the user pokemon. 
     * If method don't find any pokemon, send the first pokemon in the list which can battle.
     */
    protected void trainerChange() {
        for (final PokemonInBattle pkm : trainer.getSquad().getPokemonList()) {
            if (STANDARD_EFFECTIVENESS_VALUE < WeaknessTable.getWeaknessTable().getMultiplierAttack(
                    pkm.getPokedexEntry().getFirstType(), allyPkm.getPokedexEntry().getFirstType(), allyPkm.getPokedexEntry().getSecondType())
                    || STANDARD_EFFECTIVENESS_VALUE < WeaknessTable.getWeaknessTable().getMultiplierAttack(
                            pkm.getPokedexEntry().getSecondType(), allyPkm.getPokedexEntry().getFirstType(), allyPkm.getPokedexEntry().getSecondType())) {
                enemyPkm = pkm;
                break;
            }
        }
        if (enemyPkm.getCurrentHP() == 0) {
            for (final PokemonInBattle pkm : trainer.getSquad().getPokemonList()) {
                if (pkm.getCurrentHP() > 0) {
                    enemyPkm = pkm;
                    break;
                }
            }
        }
    }

}