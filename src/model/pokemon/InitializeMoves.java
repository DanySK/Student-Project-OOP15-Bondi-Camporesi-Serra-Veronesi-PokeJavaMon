package model.pokemon;


import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public final class InitializeMoves {
	
	private static Map<Integer, Move> charmenderMap;
	private static Map<Integer, Move> squirtleMap;
	private static Map<Integer, Move> bulbasaurMap;
	private static Map<Integer, Move> pidgeyMap;
	private static Map<Integer, Move> rattataMap;
	private static Map<Integer, Move> pikachuMap;
	private static Map<Integer, Move> sandshrewMap;
	private static Map<Integer, Move> tentacoolMap;
	private static Map<Integer, Move> zubatMap;
	private static Map<Integer, Move> rayquazaMap;
	private static Map<Integer, Move> missignoMap;
	private static boolean isInit = false;
	

	private InitializeMoves() {}
	
    private static void initializeMoves() {
        if (isInit) {
        	return;
        }
    	
        /*Initialization of fireStarters using Guava ImmutableMap*/
        charmenderMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.HOWL)
                .put(7, Move.EMBER)
                .put(15, Move.SLASH)
                .put(20, Move.SWORDS_DANCE)
                .put(25, Move.FLAMETHROWER)
                .put(36, Move.FIRE_BLAST)
                .put(40, Move.FLY)
//                .put(45, Move.HURRICANE)
                .put(49, Move.BLAST_BURN)
                .put(50, Move.OUTRAGE)
                .build();
    
        Pokedex.CHARMANDER.initializeMoveset(charmenderMap);
        Pokedex.CHARMELEON.initializeMoveset(charmenderMap);
        Pokedex.CHARIZARD.initializeMoveset(charmenderMap);
        
        /*Initialization of waterStarters using Guava immutableMap*/
        squirtleMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.HARDEN)
                .put(7, Move.WATER_GUN)
                .put(15, Move.CUT)
                .put(20, Move.AGILITY)
                .put(25, Move.SURF)
                .put(36, Move.HYDRO_PUMP)
                .put(45, Move.HYPER_BEAM)
                .put(50, Move.HYDRO_CANNON)
                .build();
        Pokedex.SQUIRTLE.initializeMoveset(squirtleMap);
        Pokedex.WARTORTLE.initializeMoveset(squirtleMap);
        Pokedex.BLASTOISE.initializeMoveset(squirtleMap);
        
        /*Initialization of grassStarters with Guava ImmutableMap*/
        bulbasaurMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.HOWL)
                .put(7, Move.VINE_WHIP)
                .put(15, Move.CUT)
                .put(20, Move.HARDEN)
                .put(25, Move.LEAF_BLADE)
                .put(32, Move.SOLAR_BEAM)
                .put(36, Move.SLUDGE_BOMB)
                .put(50, Move.FRENZY_PLANT)
                .build();
        Pokedex.BULBASAUR.initializeMoveset(bulbasaurMap);
        Pokedex.IVYSAUR.initializeMoveset(bulbasaurMap);
        Pokedex.VENUSAUR.initializeMoveset(bulbasaurMap);
        
        /*Initialization of Pidgey Evo */
        pidgeyMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.PECK)
                .put(7, Move.AGILITY)
                .put(15, Move.SLASH)
                .put(20, Move.FLY)
                .put(25, Move.STRENGTH)
//                .put(36, Move.HURRICANE)
                .put(42, Move.SCREECH)
                .put(50, Move.HYPER_BEAM)
                .build();
        Pokedex.PIDGEY.initializeMoveset(pidgeyMap);
        Pokedex.PIDGEOTTO.initializeMoveset(pidgeyMap);
        Pokedex.PIDGEOT.initializeMoveset(pidgeyMap);
        
        /*Initialization of Rattata Evo */
        rattataMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.PECK)
                .put(7, Move.SCARY_FACE)
                .put(15, Move.CUT)
                .put(20, Move.SLASH)
                .put(25, Move.STRENGTH)
                .put(42, Move.SCREECH)
                .put(50, Move.HYPER_BEAM)
                .build();
        Pokedex.RATTATA.initializeMoveset(rattataMap);
        Pokedex.RATICATE.initializeMoveset(rattataMap);
        
        /*Initialization of Pikachu Evo*/
        pikachuMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.THUNDER_SHOCK)
                .put(7, Move.AGILITY)
                .put(15, Move.CUT)
                .put(20, Move.THUNDERBOLT)
                .put(25, Move.STRENGTH)
                .put(36, Move.THUNDER)
                .put(42, Move.SWORDS_DANCE)
                .put(50, Move.OUTRAGE)
                .build();
        Pokedex.PIKACHU.initializeMoveset(pikachuMap);
        Pokedex.RAICHU.initializeMoveset(pikachuMap);
        
        sandshrewMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.MUD_SLAP)
                .put(7, Move.PECK)
                .put(15, Move.CUT)
                .put(25, Move.STRENGTH)
                .put(36, Move.EARTHQUAKE)
                .put(42, Move.SWORDS_DANCE)
                .put(50, Move.HYPER_BEAM)
                .build();
        Pokedex.SANDSHREW.initializeMoveset(sandshrewMap);
        Pokedex.SANDSLASH.initializeMoveset(sandshrewMap);
        
        tentacoolMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.POUND)
                .put(5, Move.WATER_GUN)
                .put(7, Move.AGILITY)
                .put(15, Move.CUT)
                .put(20, Move.STRENGTH)
                .put(25, Move.SURF)
                .put(29, Move.SLUDGE_BOMB)
                .put(42, Move.HYDRO_PUMP)
//                .put(50, Move.BELCH)
                .build();
        Pokedex.TENTACOOL.initializeMoveset(tentacoolMap);
        Pokedex.TENTACRUEL.initializeMoveset(tentacoolMap);
        
        zubatMap = ImmutableMap.<Integer, Move>builder()
                .put(3, Move.PECK)
                .put(5, Move.AGILITY)
                .put(7, Move.CUT)
                .put(15, Move.ACID)
                .put(20, Move.FLY)
                .put(25, Move.SWORDS_DANCE)
                .put(29, Move.SLUDGE_BOMB)
//                .put(35, Move.HURRICANE)
//                .put(42, Move.BELCH)
                .put(50, Move.HYPER_BEAM)
                .build();
        Pokedex.ZUBAT.initializeMoveset(zubatMap);
        Pokedex.GOLBAT.initializeMoveset(zubatMap);
        
        rayquazaMap = ImmutableMap.<Integer, Move>builder()
                .put(35, Move.SWORDS_DANCE)
                .put(36, Move.OUTRAGE)
                .put(37, Move.HYPER_BEAM)
                .put(39, Move.FLY)
                .build();
        Pokedex.RAYQUAZA.initializeMoveset(rayquazaMap);
        
        missignoMap = ImmutableMap.<Integer, Move>builder()
                .put(1, Move.NULLMOVE)
                .build();
        Pokedex.MISSINGNO.initializeMoveset(missignoMap);
        isInit = true;
        

    }
    private static Map<Integer, Move> getPokedexMove(final Pokedex p) {
    	switch(p) {
    	case CHARMANDER :
    	case CHARMELEON :
    	case CHARIZARD :
    		return charmenderMap;
    	case SQUIRTLE :
    	case WARTORTLE :
    	case BLASTOISE :
    		return squirtleMap;
    	case BULBASAUR :
    	case IVYSAUR :
    	case VENUSAUR :
    		return bulbasaurMap;
    	case PIDGEY :
    	case PIDGEOTTO :
    	case PIDGEOT :
    		return pidgeyMap;
    	case RATTATA :
    	case RATICATE :
    		return rattataMap;
    	case PIKACHU :
    	case RAICHU :
    		return pikachuMap;
    	case SANDSHREW :
    	case SANDSLASH :
    		return sandshrewMap;
    	case ZUBAT :
    	case GOLBAT :
    		return zubatMap;
    	case TENTACOOL :
    	case TENTACRUEL :
    		return tentacoolMap;
    	case RAYQUAZA :
    		return rayquazaMap;
    	default :
    		return missignoMap;
    	}
    }
    
    public static Map<Pokedex, Map<Integer, Move>> getAllMoves() {
    	final Map<Pokedex, Map<Integer, Move>> retMap = new HashMap<>();
    	initializeMoves();
    	for (final Pokedex p : Pokedex.values()) {
    		retMap.put(p, getPokedexMove(p));
    	}
    	
    	return retMap;
    }
}
