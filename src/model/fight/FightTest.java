package model.fight;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.SquadFullException;
import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Potion;
import model.map.Drawable.Direction;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.StaticPokemonFactory;
import model.squad.Squad;
import model.squad.SquadImpl;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;


public class FightTest {
    private final static int FIRST_ELEM = 0;
    private final static int NUMBER_OF_PKMS_IN_SQUAD = 2;
    private final static int A_LOT_OF_DAMAGE = 999;
    private final Player player = PlayerImpl.getPlayer();
    private final ArrayList<PokemonInBattle> pkms = new ArrayList<>();
    /*for (int i = 0; i < NUMBER_OF_PKMS_IN_SQUAD; i++) {
        pkms.add(StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3));
    }
    private final Squad squad = new SquadImpl();
    private final Trainer trainer = new Trainer("Red", 0, 0, null, false, squad,
            "hello", "bye bye", "i'm poor sorry", 0, 0);*/
    
    /*public void init(){
        player.setName("Blue");
    }*/
    
    @Test
    public void testCheckLose() {
        //creo pkm selvatico
        final PokemonInBattle wildPikachu = StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3);
        //creo due pkm
        final PokemonInBattle pikachu = StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3);
        final PokemonInBattle raichu = StaticPokemonFactory.createPokemon(Pokedex.RAICHU, 30);
        //aggiungo 2 pkm
        try {
            player.getSquad().add(pikachu);
            player.getSquad().add(raichu);
        } catch (SquadFullException e) {
             fail("Squad is full...");
        }
        //metto gli hp dei pkm a zero e chiamo il metodo checkLose
        pikachu.damage(A_LOT_OF_DAMAGE);
        raichu.damage(A_LOT_OF_DAMAGE);
        //creo un fight a caso
        final Fight fight = new FightVsWildPkm(wildPikachu);
        assertTrue("Checking the method checkLose", fight.checkLose(player.getSquad()));
    }
    
    @Test
    public void testApplyRun() {
        final PokemonInBattle wildPikachu = StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3);
        final Map<String, Integer> trainerPkmList = new HashMap<>();
        trainerPkmList.put("Raichu", 20);
        final PokemonInBattle allyRaichu = StaticPokemonFactory.createPokemon(Pokedex.RAICHU, 50);
        final Trainer trainer = StaticTrainerFactory.createTrainer("Blue", Direction.SOUTH, false, 0, 0, trainerPkmList, "Hi!", "Bye bye", "Yeah", 0, -1);
        try {
            player.getSquad().add(allyRaichu);
        } catch (SquadFullException e) {
             fail("Squad is full...");
        }
        //testo con WildPkm la fuga riesce al 100% perchè raichu al 50 non può non fuggire contro un pikachu al 3
        final FightVsWildPkm fight = new FightVsWildPkm(wildPikachu);
        try {
            assertTrue("Checking the method applyRun... the run has failed!", fight.applyRun());
        } catch (CannotEscapeFromTrainerException e) {
            fail("You must be able to do the method applyRun against a wild pokemon! But instead the method throw the exception!");
        }
        //testo contro trainer e deve lanciare l'eccezione
        final FightVsTrainer fighttr = new FightVsTrainer(trainer);
        try {
            fighttr.applyRun();
            fail("The method applyRun should throw the exception when fight againt a trainer!");
        } catch (CannotEscapeFromTrainerException e) {
            //non faccio niente perchè è giusto che la lanci
        }
        
    }
    
    @Test
    public void testApplyChange() {
        final PokemonInBattle pikachu = StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3);
        final PokemonInBattle raichu = StaticPokemonFactory.createPokemon(Pokedex.RAICHU, 30);
        final PokemonInBattle squirtle = StaticPokemonFactory.createPokemon(Pokedex.SQUIRTLE, 5);
        try {
            player.getSquad().add(pikachu);
            player.getSquad().add(raichu);
            player.getSquad().add(squirtle);
        } catch (SquadFullException e) {
            fail("Squad is full...");
        }
        raichu.damage(A_LOT_OF_DAMAGE);
        final Fight fight = new FightVsWildPkm(StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3));
        //pokemon is exhausted
        try {
            fight.applyChange(raichu);
            fail("Raichu is exhaust! The method must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            //l'assertSame parte solo se ho fatto il cambio! il che sarebbe assurdo perchè ho lanciato l'eccezione, non ho fatto il cambio
            assertNotSame("Raichu can't be the first pkm in the squad! He is exhausted!", player.getSquad().getPokemonList().get(FIRST_ELEM), raichu);
        } catch (PokemonIsFightingException e) {
            fail("Pokemon should be exhausted! He should not be in fight!");
        }
        //pokemon is fighting
        try {
            fight.applyChange(pikachu);
            fail("Pikachu is fighting! The metohd must throw the exception!");
        } catch (PokemonIsExhaustedException e) {
            fail("Pokemon should be in fight! He should not be exhausted!");
        } catch (PokemonIsFightingException e) {
            assertSame("Pikachu must be the pokemon in battle! Raichu is exhausted!", player.getSquad().getPokemonList().get(FIRST_ELEM), pikachu);
        }
        //scambio effettuato
        try {
            fight.applyChange(squirtle);
            assertSame("Squirtle must be the first pokemon in squad! Something in the change procedure is wrong!", player.getSquad().getPokemonList().get(FIRST_ELEM), squirtle);
        } catch (PokemonIsExhaustedException e) {
            fail("The change must be resolved this time!");
        } catch (PokemonIsFightingException e) {
            fail("The change must be resolved this time!");
        } 
    }
    
    @Test
    public void testApplyItem() {
        final PokemonInBattle wildPikachu = StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3);
        final PokemonInBattle pikachu = StaticPokemonFactory.createPokemon(Pokedex.PIKACHU, 3);
        final PokemonInBattle raichu = StaticPokemonFactory.createPokemon(Pokedex.RAICHU, 30);
        try {
            player.getSquad().add(pikachu);
            player.getSquad().add(raichu);
        } catch (SquadFullException e) {
             fail("Squad is full...");
        }
        raichu.damage(A_LOT_OF_DAMAGE);
        final Item boost = new Boost(Stat.ATK);
        final Item potion = new Potion(Potion.PotionType.HYPERPOTION);
        final Item pokeball = new Pokeball(Pokeball.PokeballType.Ultraball);
        final Fight fight = new FightVsWildPkm(wildPikachu);
        
    }

}
