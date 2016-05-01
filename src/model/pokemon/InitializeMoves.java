package model.pokemon;


import java.util.Map;
import com.google.common.collect.ImmutableMap;

public final class InitializeMoves {

    public static void initAllPokemonsTypes() {
        
        ;
            
        /*Initialization of fireStarters using Guava ImmutableMap*/
        Map<Integer, Move> initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.HOWL)
                .put(7, Move.EMBER)
                .put(15, Move.SLASH)
                .put(20, Move.SWORD_DANCE)
                .put(25, Move.FLAMETHROWER)
                .put(36, Move.FIRE_BLAST)
                .put(40, Move.FLY)
                .put(45, Move.HURRICANE)
                .put(49, Move.BLAST_BURN)
                .put(50, Move.OUTRAGE)
                .build();
    
        Pokedex.CHARMANDER.initializeMoveset(initMap);
        Pokedex.CHARMELEON.initializeMoveset(initMap);
        Pokedex.CHARIZARD.initializeMoveset(initMap);
        
        /*Initialization of waterStarters using Guava immutableMap*/
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.HARDEN)
                .put(7, Move.WATERGUN)
                .put(15, Move.CUT)
                .put(20, Move.AGILITY)
                .put(25, Move.SURF)
                .put(36, Move.HYDROPUMP)
                .put(45, Move.HYPERBEAM)
                .put(50, Move.HYDROCANNON)
                .build();
        Pokedex.SQUIRTLE.initializeMoveset(initMap);
        Pokedex.WARTORTLE.initializeMoveset(initMap);
        Pokedex.BLASTOISE.initializeMoveset(initMap);
        
        /*Initialization of grassStarters with Guava ImmutableMap*/
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.HOWL)
                .put(7, Move.VINE_WHIP)
                .put(15, Move.CUT)
                .put(20, Move.HARDEN)
                .put(25, Move.LEAFBLADE)
                .put(32, Move.SOLARBEAM)
                .put(36, Move.SLUDGEBOMB)
                .put(42, Move.BELCH)
                .put(50, Move.FRENZY_PLANT)
                .build();
        Pokedex.BULBASAUR.initializeMoveset(initMap);
        Pokedex.IVYSAUR.initializeMoveset(initMap);
        Pokedex.VENUSAUR.initializeMoveset(initMap);
        
        /*Initialization of Pidgey Evo */
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.PECK)
                .put(7, Move.AGILITY)
                .put(15, Move.SLASH)
                .put(20, Move.FLY)
                .put(25, Move.STRENGTH)
                .put(36, Move.HURRICANE)
                .put(42, Move.SCREECH)
                .put(50, Move.HYPERBEAM)
                .build();
        Pokedex.PIDGEY.initializeMoveset(initMap);
        Pokedex.PIDGEOTTO.initializeMoveset(initMap);
        Pokedex.PIDGEOT.initializeMoveset(initMap);
        
        /*Initialization of Rattata Evo */
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.PECK)
                .put(7, Move.SCARY_FACE)
                .put(15, Move.CUT)
                .put(20, Move.SLASH)
                .put(25, Move.STRENGTH)
                .put(42, Move.SCREECH)
                .put(50, Move.HYPERBEAM)
                .build();
        Pokedex.RATTATA.initializeMoveset(initMap);
        Pokedex.RATICATE.initializeMoveset(initMap);
        
        /*Initialization of Pikachu Evo*/
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.THUNDERSHOCK)
                .put(7, Move.AGILITY)
                .put(15, Move.CUT)
                .put(20, Move.THUNDERBOLT)
                .put(25, Move.STRENGTH)
                .put(36, Move.THUNDER)
                .put(42, Move.SWORD_DANCE)
                .put(50, Move.OUTRAGE)
                .build();
        Pokedex.PIKACHU.initializeMoveset(initMap);
        Pokedex.RAICHU.initializeMoveset(initMap);
        
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.MUDSLAP)
                .put(7, Move.PECK)
                .put(15, Move.CUT)
                .put(20, Move.MUDBOMB)
                .put(25, Move.STRENGTH)
                .put(36, Move.EARTHQUAKE)
                .put(42, Move.SWORD_DANCE)
                .put(50, Move.HYPERBEAM)
                .build();
        Pokedex.SANDSHREW.initializeMoveset(initMap);
        Pokedex.SANDSLASH.initializeMoveset(initMap);
        
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.WATERGUN)
                .put(7, Move.AGILITY)
                .put(15, Move.CUT)
                .put(20, Move.STRENGTH)
                .put(25, Move.SURF)
                .put(29, Move.SLUDGEBOMB)
                .put(42, Move.HYDROPUMP)
                .put(50, Move.BELCH)
                .build();
        Pokedex.TENTACOOL.initializeMoveset(initMap);
        Pokedex.TENTACRUEL.initializeMoveset(initMap);
        
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.PECK)
                .put(5, Move.AGILITY)
                .put(7, Move.CUT)
                .put(15, Move.ACID)
                .put(20, Move.FLY)
                .put(25, Move.SWORD_DANCE)
                .put(29, Move.SLUDGEBOMB)
                .put(35, Move.HURRICANE)
                .put(42, Move.BELCH)
                .put(50, Move.HYPERBEAM)
                .build();
        Pokedex.ZUBAT.initializeMoveset(initMap);
        Pokedex.GOLBAT.initializeMoveset(initMap);
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(35, Move.DRACO_METEOR)
                .put(36, Move.OUTRAGE)
                .put(37, Move.HYPERBEAM)
                .put(39, Move.FLY)
                .build();
        Pokedex.RAYQUAZA.initializeMoveset(initMap);
        
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(1, Move.NULLMOVE)
                .build();
        Pokedex.MISSINGNO.initializeMoveset(initMap);
        

    }
}
