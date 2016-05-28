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
 * This class deals operations to manage a {@link Fight} against a trainer.
 */
public class FightVsTrainer extends AbstractFight {

    private static final int STANDARD_EFFECTIVENESS_VALUE = 1;
    private static final int BOOST_MOVE_DAMAGE = 0;
    private final Trainer trainer;
    private final Map<PokemonInBattle, Map<Stat, Double>> enemyPkmsBoosts = new HashMap<>();
    private static final String TRAINER_DEFEAT_MESS = "Money earned: ";
    private static final String GYM_LEADER_DEFEAT_MESS = "You gained a badge!";

    /**
     * Simple constructor for FightVsTrainer, it just initialize trainer parameters.
     * 
     * @param trainer   The {@link model.trainer.Trainer} challenged.
     */
    protected FightVsTrainer(final Trainer trainer) {
        super();
        this.trainer = trainer;
        this.enemyPkm = this.trainer.getSquad().getPokemonList().get(FIRST_ELEM);
        for (final PokemonInBattle pkm : trainer.getSquad().getPokemonList()) {
            this.enemyPkmsBoosts.put(pkm, createBoostsMap());
        }
    }

    /**
     * Apply the trainer change. This method search and send in battle the first 
     * pokemon which have the super effective types against the player pokemon. 
     * If method don't find any pokemon, send the first pokemon in the list which can battle.
     */
    protected void trainerChange() {
        for (final PokemonInBattle pkm : this.trainer.getSquad().getPokemonList()) {
            if (STANDARD_EFFECTIVENESS_VALUE < WeaknessTable.getWeaknessTable().getMultiplierAttack(
                    pkm.getPokedexEntry().getFirstType(), this.allyPkm.getPokedexEntry().getFirstType(), this.allyPkm.getPokedexEntry().getSecondType())
                    || STANDARD_EFFECTIVENESS_VALUE < WeaknessTable.getWeaknessTable().getMultiplierAttack(
                            pkm.getPokedexEntry().getSecondType(), this.allyPkm.getPokedexEntry().getFirstType(), this.allyPkm.getPokedexEntry().getSecondType())) {
                this.enemyPkm = pkm;
                break;
            }
        }
        if (this.enemyPkm.getCurrentHP() == 0) {
            for (final PokemonInBattle pkm : this.trainer.getSquad().getPokemonList()) {
                if (pkm.getCurrentHP() > 0) {
                    this.enemyPkm = pkm;
                    break;
                }
            }
        }
    }

    @Override
    protected boolean useBall(final Item itemToUse) throws CannotCaughtTrainerPkmException {
        throw new CannotCaughtTrainerPkmException();
    }

    /**
     * Calculate the enemy move. This method search and return the move that do more damage.
     * If method don't find any move that target HP, it returns the first move in list.
     * 
     * @return  The {@link model.pokemon.Move} used by enemy pokemon.
     */
    @Override
    protected Move calculationEnemyMove() {
        final List<Integer> movesDamages = new ArrayList<>();
        int movDam = BOOST_MOVE_DAMAGE;
        int movPos = FIRST_ELEM;
        for (final Move move : this.enemyPkm.getCurrentMoves()) {
            if (move.getStat() == Stat.MAX_HP) {
                movesDamages.add(damageCalculation(this.enemyPkm, this.allyPkm, getEnemyBoost(Stat.ATK), getAllyBoost(Stat.DEF),
                        move, stabCalculation(this.enemyPkm, move), isEffective(this.enemyPkm, this.allyPkm, move)));
            } else {
                movesDamages.add(BOOST_MOVE_DAMAGE);
            }
        }
        for (int i = FIRST_ELEM; i < this.enemyPkm.getCurrentMoves().size(); i++) {
            if (movDam < movesDamages.get(i)) {
                movDam = movesDamages.get(i);
                movPos = i;
            }
        }
        return this.enemyPkm.getCurrentMoves().get(movPos);
    }

    @Override
    public double getEnemyBoost(final Stat stat) {
        return this.enemyPkmsBoosts.get(this.enemyPkm).get(stat);
    }

    @Override
    public void setEnemyBoost(final Stat stat, final Double d) {
        this.enemyPkmsBoosts.get(this.enemyPkm).replace(stat, d);
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
    public void moveTurn(final Move move) {
        reset();
        int attacksDone = 0;
        boolean isEnd = false;
        boolean turnOrder = setIsAllyFastest();
        while (attacksDone < ATTACKS_TO_DO && !isEnd) {
            if (turnOrder) {
                allyTurn(move);
                if (this.isEnemyExhausted) {
                    final PokemonInBattle allyPkmNotUpdated = this.allyPkm;
                    final Map<Stat, Double> allyPkmBoost = this.allyPkmsBoosts.remove(allyPkmNotUpdated);
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
                    this.allyPkmsBoosts.put(allyPkm, allyPkmBoost);
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
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, null, null);
                } else {
                    //ally pokemon attacks, enemy pokemon attacks
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, false, null, null, null);
                }
            } else {
                if (this.isEnemyExhausted) {
                    //enemy pokemon attacks, ally pokemon attacks, enemy pokemon is exhausted
                    if (checkLose(this.trainer.getSquad())) {
                        //trainer is defeated
                        this.player.beatTrainer(this.trainer);
                        this.trainer.defeat();
                        if (this.trainer instanceof GymLeader) {
                            this.player.addBadge();
                            if (this.levelUp) {
                                MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, 
                                        EXP_MESSAGE + getExp() + LVL_UP_MESS + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney() + " - " + GYM_LEADER_DEFEAT_MESS, this.moveToLearn);
                            } else {
                                MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, 
                                        EXP_MESSAGE + getExp() + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney() + " - " + GYM_LEADER_DEFEAT_MESS, this.moveToLearn);
                            }
                        } else {
                            if (this.levelUp) {
                                MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, 
                                        EXP_MESSAGE + getExp() + LVL_UP_MESS + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney(), this.moveToLearn);
                            } else {
                                MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, null, 
                                        EXP_MESSAGE + getExp() + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney(), this.moveToLearn);
                            }
                        }
                    } else {
                        //trainer is not defeated
                        trainerChange();
                        if (levelUp) {
                            MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, this.enemyPkm, 
                                    EXP_MESSAGE + getExp() + LVL_UP_MESS, this.moveToLearn);
                        } else {
                            MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, true, this.enemyPkm, 
                                    EXP_MESSAGE + getExp(), this.moveToLearn);
                        }
                     }
                } else {
                    //enemy pokemon attacks, ally pokemon attacks
                    MainController.getController().getFightController().resolveAttack(move, this.allyEff, this.enemyMove, this.enemyEff, this.isAllyFastest, false, null, null, null);
                }
            }
        } else {
            if (this.isAllyFastest) {
                //ally pokemon attacks, enemy pokemon is exhausted
                if (checkLose(this.trainer.getSquad())) {
                    this.player.beatTrainer(this.trainer);
                    this.trainer.defeat();
                    if (this.trainer instanceof GymLeader) {
                        this.player.addBadge();
                        if (this.levelUp) {
                            MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, true, null, 
                                    EXP_MESSAGE + getExp() + LVL_UP_MESS + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney() + " - " + GYM_LEADER_DEFEAT_MESS, this.moveToLearn);
                        } else {
                            MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, true, null, 
                                    EXP_MESSAGE + getExp() + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney() + " - " + GYM_LEADER_DEFEAT_MESS, this.moveToLearn);
                        }
                    } else {
                        if (this.levelUp) {
                            MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, true, null, 
                                    EXP_MESSAGE + getExp() + LVL_UP_MESS + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney(), this.moveToLearn);
                        } else {
                            MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, true, null, 
                                    EXP_MESSAGE + getExp() + " - " + TRAINER_DEFEAT_MESS + this.trainer.getMoney(), this.moveToLearn);
                        }
                    }
                } else {
                    trainerChange();
                    if (this.levelUp) {
                        MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, false, this.enemyPkm, EXP_MESSAGE + getExp() + LVL_UP_MESS, this.moveToLearn);
                    } else {
                        MainController.getController().getFightController().resolveAttack(move, this.allyEff, null, null, this.isAllyFastest, false, this.enemyPkm, EXP_MESSAGE + getExp(), this.moveToLearn);
                    }
                }
            } else {
                //enemy pokemon attacks, ally pokemon is exhausted
                MainController.getController().getFightController().resolveAttack(null, null, this.enemyMove, this.enemyEff, this.isAllyFastest, false, null, null, null);
            }
        }
        reset();
    }

    @Override
    public int getExp() {
        return (int) (expBaseCalculation() * 1.5) / EXP_COEFFICIENT; 
    }
}