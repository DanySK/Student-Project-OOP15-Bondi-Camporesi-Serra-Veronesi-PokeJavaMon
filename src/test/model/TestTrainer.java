package test.model;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

import model.map.Drawable.Direction;
import model.pokemon.Pokedex;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TestTrainer {

    private static final int money = 100;
    private static final int tileX = 0;
    private static final int tileY = 1;
    private static final int id = 0;
    private static final String initMessage = "Hi";
    private static final String trainerDefeatedMessage = "Bye, bye";
    private static final String trainerWonMessage = "Yeah";
    private static final Map<String, Integer> pkmnList = new TreeMap<>();
    private static final ArrayList<String> pkmns_lvl = new ArrayList<>();

    /**
     * Test the creation of a trainer. 
     */
    @Test
    public void testStaticTrainerFactory() {
        /*
         * Test first version of method createTrainer
         * */
        pkmnList.put("RATICATE", 25);
        pkmnList.put("RATTATA", 5);
        final Trainer blue = 
                StaticTrainerFactory.createTrainer("Blue", Direction.SOUTH, false, tileX, tileY, pkmnList, initMessage, trainerDefeatedMessage, trainerWonMessage, money, id);
        assertSame("Parameter Direction is wrong!", Direction.SOUTH, blue.getDirection());
        assertEquals("Parameter isDefeated is wrong!", false, blue.isDefeated());
        assertSame("Parameter TileX is wrong!", tileX, blue.getTileX());
        assertSame("Parameter TileY is wrong!", tileY, blue.getTileY());
        assertSame("A pokemon in the squad is wrong!", Pokedex.RATICATE, blue.getSquad().getPokemonList().get(0).getPokedexEntry());
        assertSame("A pokemon in the squad is wrong!", Pokedex.RATTATA, blue.getSquad().getPokemonList().get(1).getPokedexEntry());
        assertEquals("Parameter initialMessage is wrong!", initMessage, blue.getInitialMessage());
        assertEquals("Parameter trainerLostMessage is wrong!", trainerDefeatedMessage , blue.getTtrainerLostMessage());
        assertEquals("Parameter trainerWonMessage is wrong!", trainerWonMessage, blue.getTrainerWonMessageMessage());
        assertEquals("Parameter money is wrong!", money, blue.getMoney());
        assertEquals("Parameter ID is wrong!", id, blue.getID());
        /*
         * Test second version of method createTrainer
         * */
        pkmns_lvl.add("raticate=25");
        pkmns_lvl.add("rattata=5");
        final Trainer blue2 = 
                StaticTrainerFactory.createTrainer("Blue2", Direction.NORTH, true, tileX, tileY, pkmns_lvl, initMessage, trainerDefeatedMessage, trainerWonMessage, money, id);
        assertSame("Parameter Direction is wrong!", Direction.NORTH, blue2.getDirection());
        assertEquals("Parameter isDefeated is wrong!", true, blue2.isDefeated());
        assertSame("Parameter TileX is wrong!", tileX, blue2.getTileX());
        assertSame("Parameter TileY is wrong!", tileY, blue2.getTileY());
        assertSame("A pokemon in the squad is wrong!", Pokedex.RATICATE, blue2.getSquad().getPokemonList().get(0).getPokedexEntry());
        assertSame("A pokemon in the squad is wrong!", Pokedex.RATTATA, blue2.getSquad().getPokemonList().get(1).getPokedexEntry());
        assertEquals("Parameter initialMessage is wrong!", initMessage, blue2.getInitialMessage());
        assertEquals("Parameter trainerLostMessage is wrong!", trainerDefeatedMessage , blue2.getTtrainerLostMessage());
        assertEquals("Parameter trainerWonMessage is wrong!", trainerWonMessage, blue2.getTrainerWonMessageMessage());
        assertEquals("Parameter money is wrong!", money, blue2.getMoney());
        assertEquals("Parameter ID is wrong!", id, blue2.getID());
    }
    
}
