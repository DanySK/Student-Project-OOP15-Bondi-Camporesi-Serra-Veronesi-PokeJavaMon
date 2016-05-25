package test.model;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.OnlyOnePokemonInSquadException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.player.Box;
import model.player.PlayerImpl;
import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.StaticPokemonFactory;
import model.squad.Squad;

/**
 * The class that perform the operation with box.
 */
public class TestBox {
    private static final Box box = PlayerImpl.getPlayer().getBox();
    private static final Squad squad = PlayerImpl.getPlayer().getSquad();
    private static final int MAX_PKM_SQUAD = 6;
    private static final int MIN_PKM_SQUAD = 1;
    private static final PokemonInBattle squirtle = StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 5);

    /**
     * Test {@link model.player.Box} methods. Tests need an order because they need to manage different 
     * situations of squad status, which is changed by the execution of the methods.
     */
    @Test
    public void testBox(){
        testWithdraw();
        testDeposit();
    }
    
    /**
     * Test withdraw.
     */
    private void testWithdraw() {
        final PokemonInBattle raticate = StaticPokemonFactory.createPokemon(Pokedex.RATICATE, 25);
        final PokemonInBattle randomRattata = StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 5);
        box.putCapturedPokemon(raticate);
        /*
         * try to add 7 pokemon in a squad, method must throw the exception!
         * */
        try {
            squad.add(randomRattata);
            for (int i = MIN_PKM_SQUAD; i < MAX_PKM_SQUAD; i++) {
                squad.add(StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 3));
            }
            squad.add((StaticPokemonFactory.createPokemon(Pokedex.RATTATA, 3)));
            fail("The last pokemon addition should throws the exception!");
        } catch (SquadFullException e) {}
        /*
         * test method withdrawPokemon when the squad have 6 pokemons, method must throw exception!
         * */
        try {
            box.withdrawPokemon(raticate, squad);
        } catch (PokemonNotFoundException e) {
            fail("Raticate is in the box and the squad have 6 pokemons! The method should throw SquadIsFullException!");
        } catch (SquadFullException e) {}
        /*
         * test method withdrawPokemon on a pokemon which isn't in the box, method must throw exception!
         * */
        try {
            box.depositPokemon(randomRattata, squad);
        } catch (PokemonNotFoundException e2) {
            fail("Pokemon is in the squad!");
        } catch (OnlyOnePokemonInSquadException e2) {
            fail("Pokemon isn't the only one in the squad!");
        }
        try {
            box.withdrawPokemon(squirtle, squad);
        } catch (PokemonNotFoundException e1) {
        } catch (SquadFullException e1) {
            fail("The squad isn't full and squirtle isn't in the box!");
        }
        /*
         * test method withdrawPokemon on a pokemon which can be withdrawn and check 
         * if pokemon withdrawed is in the squad.
         * */
        try {
            box.withdrawPokemon(raticate, squad);
        } catch (PokemonNotFoundException | SquadFullException e) {
           fail("The method should not throws any exception!");
        }
        assertTrue("Raticate should be in the squad!", squad.contains(raticate));
    }

    /**
     * Test deposit.
     */
    private void testDeposit() {
        /*
         * test method depositPokemon on all pokemons in squad, method must throw the exception!
         * */
        try {
            for (int i = MAX_PKM_SQUAD; i < MIN_PKM_SQUAD; i--) {
                box.depositPokemon(squad.getPokemonList().get(i), squad);
            }
        } catch (PokemonNotFoundException e) {
            fail("PokemonNotFoundException is throwed! Something in the procedure is wrong!");
        } catch (OnlyOnePokemonInSquadException e) {}
        /*
         * test method depositPokemon on a pokemon which is not in the squad, method must throw exception!
         * */
        try {
            box.depositPokemon(squirtle, squad);
        } catch (PokemonNotFoundException e) {
        } catch (OnlyOnePokemonInSquadException e) {
            fail("The pokemon isn't in the squad! The method should throw PokemonNotFoundException!");
        }
    }

}
