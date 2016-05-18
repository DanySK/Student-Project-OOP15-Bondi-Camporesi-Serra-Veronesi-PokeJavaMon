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


public class FightVsTrainer extends AbstractFight {

    private static final int STANDARD_EFFECTIVENESS_VALUE = 1;
    private static final int SUPER_EFFECTIVE_MAX_VALUE = 4;
    private final Trainer trainer;
    private final Map<PokemonInBattle, Map<Stat, Double>> enemyPkmsBoosts = new HashMap<>();
    private static final String TRAINER_DEFEAT_MESS = "Money earned: ";
    private static final String GYM_LEADER_DEFEAT_MESS = "You gained a badge!";

    /**
     * Constructor for a fight against a trainer.
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
     * Calculate the enemy move.
     * The method try to find a {@link Effectiveness#SUPEREFFECTIVE} move.
     * If it dont't found a move, it return the move which have highest value ({@link model.pokemon.Move#getValue()}).
     * If method don't found a move, it return the first move in the move list ({@link model.pokemon.Pokemon#getCurrentMoves()}).
     * 
     * @return  The {@link model.pokemon.Move} used by enemy {@link model.pokemon.Pokemon}.
     * @see     {@link BasicFight#calculationEnemyMove()}
     */
    @Override
    protected Move calculationEnemyMove() {
        Move move = enemyPkm.getCurrentMoves().get(FIRST_ELEM);
        boolean superEffective = false;
        final List<Move> moves = new ArrayList<>();
        for (final Move mov : enemyPkm.getCurrentMoves()) {
            if (mov != Move.NULLMOVE) {
                moves.add(mov);
            }
        }
        for (final Move m : moves) {
            if (m.getStat() == Stat.MAX_HP) {
                if (SUPER_EFFECTIVE_MAX_VALUE == WeaknessTable.getWeaknessTable().getMultiplierAttack(m.getType(), allyPkm.getPokedexEntry().getFirstType(), allyPkm.getPokedexEntry().getSecondType())) {
                    move = m;
                    break;
                } else if (SUPER_EFFECTIVE == WeaknessTable.getWeaknessTable().getMultiplierAttack(m.getType(), allyPkm.getPokedexEntry().getFirstType(), allyPkm.getPokedexEntry().getSecondType())) {
                    superEffective = true;
                    move = m;
                } else if (move.getValue() < m.getValue() && !superEffective) {
                    move = m;
                }
            }
        }
        return move;
    }

    /**
     * @see {@link BasicFight#getEnemyBoost(Stat)}
     */
    @Override
    public double getEnemyBoost(final Stat stat) {
        return enemyPkmsBoosts.get(enemyPkm).get(stat);
    }

    /**
     * @see {@link BasicFight#setEnemyBoost(Stat, Double)}
     */
    @Override
    public void setEnemyBoost(final Stat stat, final Double d) {
        enemyPkmsBoosts.get(enemyPkm).replace(stat, d);
    }

    /**
     * Resolve the run option.
     * 
     * @throws CannotEscapeFromTrainerException If the user fight against a {@link model.trainer.Trainer}.
     * @see                                     {@link AbstractFight#applyRun()}
     */
    @Override
    public boolean applyRun() throws CannotEscapeFromTrainerException{
        throw new CannotEscapeFromTrainerException();
    }

    /**
     * @see {@link AbstractFight#applyItem(Item, PokemonInBattle)}
     */
    @Override
    public boolean applyItem(final Item itemToUse, final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException {
        return super.applyItem(itemToUse, pkm);
    }

    /**
     * Resolve the use of a pokeball.
     * 
     * @param itemToUse                         The {@link model.items.Pokeball} to use.
     * @throws CannotCaughtTrainerPkmException  If the user fight against a {@link model.trainer.Trainer}.
     * @see                                     {@link AbstractFight#useBall(Item)}
     */
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

    /**
     * @see {@link AbstractFight#getExp()}
     */
    @Override
    public int getExp() {
        return (int) (expBaseCalculation() * 1.5) / EXP_COEFFICIENT; 
    }

    /**
     * Apply the trainer change. The method set the first pokemon which have the types 
     * ({@link model.pokemon.Pokedex#getFirstType()} {@link model.pokemon.Pokedex#getSecondType()}) super effective {@link Effectiveness#SUPEREFFECTIVE}.
     * If it don't found, set the first pokemon founded.
     * The method set {@link BasicFight#enemyPkm}.
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