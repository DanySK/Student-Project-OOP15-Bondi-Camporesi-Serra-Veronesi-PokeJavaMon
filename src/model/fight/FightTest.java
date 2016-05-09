package model.fight;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.HashMap;
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
import model.items.Pokeball;
import model.items.Potion;
import model.map.Drawable.Direction;
import model.player.Player;
import model.player.PlayerImpl;
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
    private static final int MIN_HP = 1;
    private static final int EXHAUSTED_HP = 0;
    private static final int A_LOT_OF_HP = 999;
    private static final Player player = PlayerImpl.getPlayer();

    @Test
    public void fightTest() {
        testSquadFull();
        testCheckLose();
        testApplyRun();
        testApplyChange();
    }

    private FightVsWildPkm createFightVsWildPkm() {
        return new FightVsWildPkm(StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3));
    }

    private FightVsTrainer createFightVsTrainer() {
        final Map<String, Integer> trainerPkmList = new HashMap<>();
        trainerPkmList.put("RAICHU", 50);
        final Trainer trainer = StaticTrainerFactory.createTrainer("Blue", Direction.SOUTH, false, 0, 0, trainerPkmList, "Hi!", "Bye bye", "Yeah", 0, -1);
        return new FightVsTrainer(trainer);
    }

    private void testSquadFull() {
        try {
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.BLASTOISE, 50));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.WARTORTLE, 20));
            player.getSquad().add(StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 10));
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
            fail("Pokemon should be exhausted! He should not be in fight!");
        }
        //pokemon is fighting
        try {
            fight.applyChange(blastoise);
            fail("Blastoise is in fight! The metohd must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            fail("Pokemon should be in fight! He should not be exhausted!");
        } catch (PokemonIsFightingException e) {
            assertSame("Blastoise must be the pokemon in fight!", player.getSquad().getPokemonList().get(FIRST_ELEM), blastoise);
        }
        //scambio effettuato
        try {
            fight.applyChange(squirtle);
            assertSame("Squirtle must be the first pokemon in squad! Something in the change procedure is wrong!", player.getSquad().getPokemonList().get(FIRST_ELEM), squirtle);
        } catch (PokemonIsExhaustedException e) {
            fail("The change must be resolved correctly this time!");
        } catch (PokemonIsFightingException e) {
            fail("The change must be resolved correctly this time!");
        }
    }

    public void testEApplyItem() {
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
        //testo il boost
        final double squirtleAtkBeforeBoost = fightWild.allyPkmsBoosts.get(squirtle).get(Stat.ATK);
        try {
            fightWild.applyItem(boost, null);
            assertTrue("Squirtle attack must be increased!", squirtleAtkBeforeBoost < squirtle.getStat(Stat.ATK));
        } catch (PokemonIsExhaustedException e) {
            fail("Boost can't throw any exception!");
        } catch (PokemonNotFoundException e) {
            fail("Boost can't throw any exception!");
        } catch (CannotCaughtTrainerPkmException e) {
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
        squirtle.damage(MIN_HP);
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
        } catch (PokemonIsExhaustedException e1) {
            fail("Pokeball can't throw this exception!");
        } catch (PokemonNotFoundException e1) {
            fail("Pokeball can't throw this exception!");
        } catch (CannotCaughtTrainerPkmException e1) {
            //deve lanciare l'eccezione quindi non faccio nulla
        }
        //uso pokeball contro pokemon selvatico
        fightWild.getCurrentEnemyPokemon().damage(A_LOT_OF_HP);
        fightWild.getCurrentEnemyPokemon().heal(MIN_HP);
        //con un ultraball è impossibile non catturare un pikachu a livello 3
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
            /*
             * Controllare che gli oggetti vengano usati
             */
    }

}
