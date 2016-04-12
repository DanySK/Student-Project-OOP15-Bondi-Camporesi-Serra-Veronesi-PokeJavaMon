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
    
        PokemonDB.CHARMANDER.init(initMap);
        PokemonDB.CHARMELEON.init(initMap);
        PokemonDB.CHARIZARD.init(initMap);
        
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
        PokemonDB.SQUIRTLE.init(initMap);
        PokemonDB.WARTORTLE.init(initMap);
        PokemonDB.BLASTOISE.init(initMap);
        
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
        PokemonDB.BULBASAUR.init(initMap);
        PokemonDB.IVYSAUR.init(initMap);
        PokemonDB.VENUSAUR.init(initMap);
        
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
        PokemonDB.PIDGEY.init(initMap);
        PokemonDB.PIDGEOTTO.init(initMap);
        PokemonDB.PIDGEOT.init(initMap);
        
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
        PokemonDB.RATTATA.init(initMap);
        PokemonDB.RATICATE.init(initMap);
        
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
        PokemonDB.PIKACHU.init(initMap);
        PokemonDB.RAICHU.init(initMap);
        
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
        PokemonDB.SANDSHREW.init(initMap);
        PokemonDB.SANDSLASH.init(initMap);
        
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
        PokemonDB.TENTACOOL.init(initMap);
        PokemonDB.TENTACRUEL.init(initMap);
        
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
        PokemonDB.ZUBAT.init(initMap);
        PokemonDB.GOLBAT.init(initMap);
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(35, Move.DRACO_METEOR)
                .put(36, Move.OUTRAGE)
                .put(37, Move.HYPERBEAM)
                .put(39, Move.FLY)
                .build();
        PokemonDB.RAYQUAZA.init(initMap);
        
        initMap = ImmutableMap.<Integer, Move>builder()
                .put(1, Move.NULLMOVE)
                .build();
        PokemonDB.MISSINGNO.init(initMap);
        

    }
}
