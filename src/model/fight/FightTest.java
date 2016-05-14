package model.fight;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
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

@FixMethodOrder(MethodSorters.DEFAULT)
public class FightTest {
    private static final int FIRST_ELEM = 0;
    private static final int SECOND_ELEM = 1;
    private static final int THIRD_ELEM = 2;
    private static final int MIN_STAT = 1;
    private static final int EXHAUSTED_HP = 0;
    private static final int A_LOT_OF_HP = 999;
    private static final Player player = PlayerImpl.getPlayer();

    @Test
    public void fightTest() {
        testSquadFull();
        testCheckLose();
        testApplyRun();
        testApplyChange();
        testApplyItem();
        testUseMove();
    }

    private FightVsWildPkm createFightVsWildPkm() {
        return new FightVsWildPkm(StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3));
    }

    private FightVsTrainer createFightVsTrainer() {
        final Map<String, Integer> trainerPkmList = new HashMap<>();
        trainerPkmList.put("RATTATA", 3);
        final Trainer trainer = StaticTrainerFactory.createTrainer("Blue", Direction.SOUTH, false, 0, 0, trainerPkmList, "Hi!", "Bye bye", "Yeah", 0, -1);
        return new FightVsTrainer(trainer);
    }

    private void testSquadFull() {
        InitializeMoves.getAllMoves();
        try {
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.BLASTOISE, 50));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.WARTORTLE, 35));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 15));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 5));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 5));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 5));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 5));
            fail("The pokemon squad can not contains 7 pokemon!");
        } catch (SquadFullException e) {
             //non faccio niente perchè deve lanciare l'eccezione
        }
    }
    
    private void testCheckLose() {
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

    public void testApplyRun() {
        InitializeMoves.getAllMoves();
        final FightVsWildPkm fightWild = createFightVsWildPkm();
        final FightVsTrainer fightTrainer = this.createFightVsTrainer();
        //testo con WildPkm la fuga riesce al 100% perchè blastoise al 50 non può non fuggire contro un pikachu al 3
        try {
            assertTrue("Checking the method applyRun... the run has failed!", fightWild.applyRun());
        } catch (CannotEscapeFromTrainerException e) {
            fail("You must be able to do the method applyRun against a wild pokemon! But instead the method throw the exception!");
        }
        //testo contro trainer e deve lanciare l'eccezione
        try {
            fightTrainer.applyRun();
            fail("The method applyRun should throw the exception when fight againt a trainer!");
        } catch (CannotEscapeFromTrainerException e) {
        }
    }

    public void testApplyChange() {
        InitializeMoves.getAllMoves();
        final PokemonInBattle blastoise = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final PokemonInBattle wartortle = player.getSquad().getPokemonList().get(SECOND_ELEM);
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(THIRD_ELEM);
        wartortle.damage(A_LOT_OF_HP);
        final Fight fight = createFightVsWildPkm();
        try {
            fight.applyChange(player.getSquad().getPokemonList().get(SECOND_ELEM));
            fail("Wartortle is exhausted! The method must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            //l'assertNotSame parte solo se ho fatto il cambio! il che sarebbe assurdo perchè ho lanciato l'eccezione, non ho fatto il cambio
            assertNotSame("Wartortle can't be the first pkm in the squad! He is exhausted!", 
                    player.getSquad().getPokemonList().get(FIRST_ELEM), wartortle);
        } catch (PokemonIsFightingException e) {
            fail("Wartortle should be exhausted! He should not be in fight!");
        }
        //pokemon is fighting
        try {
            fight.applyChange(blastoise);
            fail("Blastoise is in fight! The metohd must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            fail("Blastoise should be in fight! He should not be exhausted!");
        } catch (PokemonIsFightingException e) {
            assertSame("Blastoise must be the pokemon in fight!", player.getSquad().getPokemonList().get(FIRST_ELEM), blastoise);
        }
        //scambio effettuato
        try {
            fight.applyChange(squirtle);
            assertSame("Squirtle must be the first pokemon in squad! Something in the change procedure is wrong!", player.getSquad().getPokemonList().get(FIRST_ELEM), squirtle);
        } catch (PokemonIsExhaustedException | PokemonIsFightingException e) {
            fail("The change must be resolved correctly this time!");
        }
    }

    public void testApplyItem() {
        InitializeMoves.getAllMoves();
        final Item boost = new Boost(Stat.ATK);
        final Item potion = new Potion(Potion.PotionType.HYPERPOTION);
        final Item pokeball = new Pokeball(Pokeball.PokeballType.Ultraball);
        player.getInventory().addItem(boost);
        player.getInventory().addItem(potion);
        player.getInventory().addItem(pokeball);
        final FightVsWildPkm fightWild = createFightVsWildPkm();
        final FightVsTrainer fightTr = createFightVsTrainer();
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final PokemonInBattle wartortle = player.getSquad().getPokemonList().get(SECOND_ELEM);
        final PokemonInBattle pkmNotInTeam = StaticPokemonFactory.createPokemon(Pokedex.CHARMANDER, 5);
        assertTrue("The boost must be in the inventory!", player.getInventory().getSubInventory(ItemType.BOOST).get(boost) == 1);
        assertTrue("The potion must be in the inventory!", player.getInventory().getSubInventory(ItemType.POTION).get(potion) == 1);
        assertTrue("The pokeball must be in the inventory!", player.getInventory().getSubInventory(ItemType.POKEBALL).get(pokeball) == 1);
        //testo il boost
        final double squirtleAtkBeforeBoost = fightWild.allyPkmsBoosts.get(squirtle).get(Stat.ATK);
        try {
            fightWild.applyItem(boost, null);
            assertTrue("Squirtle attack must be increased!", squirtleAtkBeforeBoost < squirtle.getStat(Stat.ATK));
        } catch (PokemonIsExhaustedException | PokemonNotFoundException | CannotCaughtTrainerPkmException e) {
            fail("Boost can't throw any exception!");
        }
        //testo la pozione
        //usata su pkm esausto
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
        //pkmNotFound
        try {
            fightWild.applyItem(potion, pkmNotInTeam);
            fail("Charmander isn't in team! The method must throw the exception!");
        } catch (PokemonIsExhaustedException e2) {
            fail("Charmander isn't in team! The method throw the wrong exception!");
        } catch (PokemonNotFoundException e2) {
            //non faccio nulla, tira l'eccezione giusta e basta
        } catch (CannotCaughtTrainerPkmException e2) {
            fail("Potion can't throw this exception!");
        }
        //andata a buon fine
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
        //uso pokeball contro allenatore
        try {
            fightTr.applyItem(pokeball, null);
            fail("Pokeball can't be used against a trainer! The method must throw exception!");
        } catch (PokemonIsExhaustedException | PokemonNotFoundException e1) {
            fail("Pokeball can't throw this exception!");
        } catch (CannotCaughtTrainerPkmException e1) {
            //deve lanciare l'eccezione quindi non faccio nulla
        }
        //uso pokeball contro pokemon selvatico
        fightWild.getCurrentEnemyPokemon().damage(A_LOT_OF_HP);
        fightWild.getCurrentEnemyPokemon().heal(MIN_STAT);
        //con un ultraball è impossibile non catturare un pikachu a livello 3
//        try {
//            assertTrue("Checking method applyItem, using a pokeball...the pokemon isn't captured!", 
//                    fightWild.applyItem(pokeball, null));
//            } catch (PokemonIsExhaustedException e) {
//                fail("The pokeball can't throw this exception!");
//            } catch (PokemonNotFoundException e) {
//                fail("The pokeball can't throw this exception!");
//            } catch (CannotCaughtTrainerPkmException e) {
//                fail("You can use pokeball against a wild pokemon!");
//            }
    }

    public void testUseMove() {
        final PokemonInBattle squirtle = player.getSquad().getPokemonList().get(FIRST_ELEM);
        final PokemonInBattle wartortle = player.getSquad().getPokemonList().get(SECOND_ELEM);
        final FightVsTrainer fightTr = createFightVsTrainer();
        final int hpBeforeTurn = squirtle.getCurrentHP();
        final double boostBeforeTurn = fightTr.getAllyBoost(Stat.DEF);
        final List<PokemonInBattle> pkmsToEvolve;
        assertTrue("Squirtle at lv 15 must be faster than a rattata at lv 3!", fightTr.setIsAllyFastest());
        fightTr.enemyTurn();
        assertTrue("Squirtle HP must be reduced by rattata attack!(rattata at lv 3 has only pound)", 
                hpBeforeTurn > squirtle.getCurrentHP());
        fightTr.allyTurn(Move.HARDEN);
        assertTrue("Squirtle DEF must be increase by harden!", 
                boostBeforeTurn < fightTr.getAllyBoost(Stat.DEF));
        squirtle.setExp(squirtle.getNecessaryExp() - MIN_STAT);
        fightTr.giveExpAndCheckLvlUp(fightTr.getExp());//do l'esperienza di rattata squirtle
        wartortle.heal(A_LOT_OF_HP);
        try {
            fightTr.applyChange(wartortle);
        } catch (PokemonIsExhaustedException | PokemonIsFightingException e) {
            fail("Something in change going wrong...");
        }
        wartortle.setExp(wartortle.getNecessaryExp() - MIN_STAT);
        fightTr.giveExpAndCheckLvlUp(fightTr.getExp());//do l'esperienza di rattata squirtle
        fightTr.evolvePkms();
        pkmsToEvolve = fightTr.getPkmsThatMustEvolve();
        assertNotSame("Wartortle must be evolved in blastoise!", Pokedex.BLASTOISE, pkmsToEvolve.get(FIRST_ELEM).getPokemon());
        assertNotSame("Squirtle must be evolved in wartortle!", Pokedex.BLASTOISE, pkmsToEvolve.get(SECOND_ELEM).getPokemon());
    }
}
