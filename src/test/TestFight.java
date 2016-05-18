package test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.FightVsWildPkm;
import model.fight.StaticSimpleFightFactory;
import model.items.Boost;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Pokeball;
import model.items.Potion;
import model.map.Drawable.Direction;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.InitializeMoves;
import model.pokemon.Move;
import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFight {
    private static final int FIRST_ELEM = 0;
    private static final int SECOND_ELEM = 1;
    private static final int THIRD_ELEM = 2;
    private static final int MIN_STAT = 1;
    private static final int EXHAUSTED_HP = 0;
    private static final int A_LOT_OF_HP = 999;
    private static final Player player = PlayerImpl.getPlayer();

    /**
     * Test all methods. Tests need an order because many methods need to manage different 
     * situations of the squad status, which is changed by the execution of the methods.
     */
    @Test
    public void fightTest() {
        testCheckLose();
        testRun();
        testChange();
        testUseItem();
        testUseMove();
        testEvolve();
    }

    /**
     * 
     * @return An instance of FightVsWildPkm {@link model.fight.FightVsWildPkm}
     */
    private Fight createFightVsWildPkm() {
        return StaticSimpleFightFactory.createFight(StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3));
    }

    /**
     * 
     * @return An instance of FightVsTrainer {@link model.fight.FightVsTrainer}
     */
    private Fight createFightVsTrainer() {
        final Map<String, Integer> trainerPkmList = new HashMap<>();
        trainerPkmList.put("RATTATA", 3);
        final Trainer trainer = StaticTrainerFactory.createTrainer("Blue", Direction.SOUTH, false, 0, 0, trainerPkmList, "Hi!", "Bye bye", "Yeah", 0, -1);
        return StaticSimpleFightFactory.createFight(trainer);
    }

    /**
     * Test the method checkLose. {@link}
     */
    private void testCheckLose() {
        try {
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.BLASTOISE, 50));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.WARTORTLE, 35));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 15));
        } catch (SquadFullException e) {
            fail("The squad is full...");
        }
        InitializeMoves.getAllMoves();
        final Fight fight = createFightVsWildPkm();
        for (PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            pkm.damage(A_LOT_OF_HP);
        }
        assertTrue("Checking the method checkLose", fight.checkLose(player.getSquad()));
        for (PokemonInBattle pkm : player.getSquad().getPokemonList()) {
            pkm.heal(A_LOT_OF_HP);
        }
    }

    /**
     * Test for escape from a battle. {@link}
     */
    public void testRun() {
        InitializeMoves.getAllMoves();
        final FightVsWildPkm fightWild = (FightVsWildPkm) createFightVsWildPkm();
        final FightVsTrainer fightTrainer = (FightVsTrainer) createFightVsTrainer();
        /*
         * test run against a wild pokemon at level 3, the ally front pokemon is at level 50,
         * the method should return true!
         * */
        try {
            assertTrue("Checking the method applyRun... the run has failed!", fightWild.applyRun());
        } catch (CannotEscapeFromTrainerException e) {
            fail("You must be able to do the method applyRun against a wild pokemon! But instead the method throw the exception!");
        }
        /*
         * test run against a trainer
         * */
        try {
            fightTrainer.applyRun();
            fail("The method applyRun should throw the exception when fight againt a trainer!");
        } catch (CannotEscapeFromTrainerException e) {}
    }

    /**
     * Test the pokemon change during a battle. {@link}
     */
    public void testChange() {
        InitializeMoves.getAllMoves();
        final PokemonInBattle blastoise = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final PokemonInBattle wartortle = player.getSquad().getPokemonList().get(SECOND_ELEM);
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(THIRD_ELEM);
        wartortle.damage(A_LOT_OF_HP);
        final Fight fight = createFightVsWildPkm();
        /*
         * test change on a exhausted pokemon, code must throw the exception
         * */
        try {
            fight.applyChange(wartortle);
            fail("Wartortle is exhausted! The method must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            assertNotSame("Wartortle can't be the first pkm in the squad! He is exhausted!", 
                    player.getSquad().getPokemonList().get(FIRST_ELEM), wartortle);
        } catch (PokemonIsFightingException e) {
            fail("Wartortle should be exhausted! He should not be in fight!");
        }
        /*
         * test change on a pokemon in fighting, code must throw the exception
         * */
        try {
            fight.applyChange(blastoise);
            fail("Blastoise is in fight! The metohd must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            fail("Blastoise should be in fight! He should not be exhausted!");
        } catch (PokemonIsFightingException e) {
            assertSame("Blastoise must be the pokemon in fight!", player.getSquad().getPokemonList().get(FIRST_ELEM), blastoise);
        }
        /*
         * test change on a pokemon which can be sent in battle
         * */
        try {
            fight.applyChange(squirtle);
            assertSame("Squirtle must be the first pokemon in squad! Something in the change procedure is wrong!", player.getSquad().getPokemonList().get(FIRST_ELEM), squirtle);
        } catch (PokemonIsExhaustedException | PokemonIsFightingException e) {
            fail("The change must be resolved correctly this time!");
        }
    }

    /**
     * Test the use of an item during a battle. {@link}
     */
    public void testUseItem() {
        InitializeMoves.getAllMoves();
        final Item boost = new Boost(Stat.ATK);
        final Item potion = new Potion(Potion.PotionType.Hyperpotion);
        final Item pokeball = new Pokeball(Pokeball.PokeballType.Ultraball);
        player.getInventory().addItem(boost);
        player.getInventory().addItem(potion);
        player.getInventory().addItem(pokeball);
        final FightVsWildPkm fightWild = (FightVsWildPkm) createFightVsWildPkm();
        final FightVsTrainer fightTr = (FightVsTrainer) createFightVsTrainer();
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final PokemonInBattle wartortle = player.getSquad().getPokemonList().get(SECOND_ELEM);
        final PokemonInBattle pkmNotInTeam = StaticPokemonFactory.createPokemon(Pokedex.CHARMANDER, 5);
        assertTrue("The boost must be in the inventory!", player.getInventory().getSubInventory(ItemType.BOOST).get(boost) == 1);
        assertTrue("The potion must be in the inventory!", player.getInventory().getSubInventory(ItemType.POTION).get(potion) == 1);
        assertTrue("The pokeball must be in the inventory!", player.getInventory().getSubInventory(ItemType.POKEBALL).get(pokeball) == 1);
        /*
         * test the use of a boost
         * */
        final double squirtleAtkBeforeBoost = fightWild.getAllyBoost(Stat.ATK);
        try {
            fightWild.applyItem(boost, null);
            assertTrue("Squirtle attack must be increased!", squirtleAtkBeforeBoost < fightWild.getAllyBoost(Stat.ATK));
        } catch (PokemonIsExhaustedException | PokemonNotFoundException | CannotCaughtTrainerPkmException e) {
            fail("Boost can't throw any exception!");
        }
        /*
         * test the use of a potion on an exhausted pokemon
         * (wartortle hp were set at 0, in the change test)
         * */
        try {
            fightWild.applyItem(potion, wartortle);
            fail("Wartortle is exhausted! The method must throw the exception!");
        } catch (PokemonIsExhaustedException e1) {
            assertTrue("Wartortle must have 0 HP! He must be exhausted!", wartortle.getCurrentHP() == EXHAUSTED_HP);
        } catch (PokemonNotFoundException e1) {
            fail("Wartortle is in the squad!");
        } catch (CannotCaughtTrainerPkmException e1) {
            fail("Potion can't throw this exception!");
        }
        /*
         * test the use of a potion on a pokemon which is not in the squad
         * */
        try {
            fightWild.applyItem(potion, pkmNotInTeam);
            fail("Charmander isn't in team! The method must throw the exception!");
        } catch (PokemonIsExhaustedException e2) {
            fail("Charmander isn't in team! The method throw the wrong exception!");
        } catch (PokemonNotFoundException e2) {
        } catch (CannotCaughtTrainerPkmException e2) {
            fail("Potion can't throw this exception!");
        }
        /*
         * test the use of a potion on a pokemon which can be healed
         * */
        squirtle.damage(MIN_STAT);
        final int hpBeforeUsePotion = squirtle.getCurrentHP();
        try {
            fightWild.applyItem(potion, squirtle);
            assertTrue("Squirtle HP must be increased by potion!", hpBeforeUsePotion < squirtle.getCurrentHP());
        } catch (PokemonIsExhaustedException e3) {
            fail("Squirtle isn't exhausted!");
        } catch (PokemonNotFoundException e3) {
            fail("Squirtle must be in the squad!");
        } catch (CannotCaughtTrainerPkmException e3) {
            fail("Potion can't throw this exception!");
        }
        /*
         * test the use of a pokeball against a trainer
         * */
        try {
            fightTr.applyItem(pokeball, null);
            fail("Pokeball can't be used against a trainer! The method must throw exception!");
        } catch (PokemonIsExhaustedException | PokemonNotFoundException e1) {
            fail("Pokeball can't throw this exception!");
        } catch (CannotCaughtTrainerPkmException e1) {}
        /*
         * test the use of a pokeball against a wild pokemon
         * */
        fightWild.getCurrentEnemyPokemon().damage(A_LOT_OF_HP);
        fightWild.getCurrentEnemyPokemon().heal(MIN_STAT);
        /*
         * test the use of an ultraball against a wild pikachu at level 3,
         * the method must return true
         * */
        try {
            assertTrue("Checking method applyItem, using a pokeball...the pokemon isn't captured!", 
                    fightWild.applyItem(pokeball, null));
            } catch (PokemonIsExhaustedException e) {
                fail("The pokeball can't throw this exception!");
            } catch (PokemonNotFoundException e) {
                fail("The pokeball can't throw this exception!");
            } catch (CannotCaughtTrainerPkmException e) {
                fail("You can use pokeball against a wild pokemon!");
            }
    }

    /**
     * Test the use of moves in battle. Using methods that resolve ally and enemy turn and
     * applying moves that hit boosts and hp.
     */
    public void testUseMove() {
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final FightVsTrainer fightTr = (FightVsTrainer) createFightVsTrainer();
        final int hpBeforeTurn = squirtle.getCurrentHP();
        final double boostBeforeTurn = fightTr.getAllyBoost(Stat.DEF);
        assertTrue("Squirtle at lv 15 must be faster than a rattata at lv 3!", fightTr.setIsAllyFastest());
        fightTr.enemyTurn();
        assertTrue("Squirtle HP must be reduced by rattata attack!(rattata at lv 3 has only pound)", 
                hpBeforeTurn > squirtle.getCurrentHP());
        fightTr.allyTurn(Move.HARDEN);
        assertTrue("Squirtle DEF must be increase by harden!", 
                boostBeforeTurn < fightTr.getAllyBoost(Stat.DEF));
    }

    /**
     * Test the evolution.
     */
    public void testEvolve(){
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final PokemonInBattle wartortle = player.getSquad().getPokemonList().get(SECOND_ELEM);
        final FightVsTrainer fightTr = (FightVsTrainer) createFightVsTrainer();
        List<PokemonInBattle> pkmsToEvolve = new ArrayList<>();
        squirtle.setExp(squirtle.getNecessaryExp() - MIN_STAT);
        fightTr.giveExpAndCheckLvlUp(fightTr.getExp());
        wartortle.heal(A_LOT_OF_HP);
        try {
            fightTr.applyChange(wartortle);
        } catch (PokemonIsExhaustedException | PokemonIsFightingException e) {
            fail("Something in change going wrong...");
        }
        wartortle.setExp(wartortle.getNecessaryExp() - MIN_STAT);
        fightTr.giveExpAndCheckLvlUp(fightTr.getExp());
        pkmsToEvolve = fightTr.getPkmsThatMustEvolve();
        fightTr.evolvePkms();
        assertSame("Wartortle must be evolved in blastoise!", Pokedex.BLASTOISE, pkmsToEvolve.get(FIRST_ELEM).getPokedexEntry());
        assertSame("Squirtle must be evolved in wartortle!", Pokedex.WARTORTLE, pkmsToEvolve.get(SECOND_ELEM).getPokedexEntry());
    }

}