package model.pokemon;


import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public final class InitializeMoves {
	
	private static Map<Integer, Move> bulbasaurMap;
	private static Map<Integer, Move> charmanderMap;
	private static Map<Integer, Move> squirtleMap;
	private static Map<Integer, Move> caterpieMap;
	private static Map<Integer, Move> weedleMap;
	private static Map<Integer, Move> pidgeyMap;
	private static Map<Integer, Move> rattataMap;
	private static Map<Integer, Move> spearowMap;
	private static Map<Integer, Move> ekansMap;
	private static Map<Integer, Move> pikachuMap;
	private static Map<Integer, Move> sandshrewMap;
	private static Map<Integer, Move> nidoranfMap;
	private static Map<Integer, Move> nidoranmMap;
	private static Map<Integer, Move> clefairyMap;
	private static Map<Integer, Move> vulpixMap;
	private static Map<Integer, Move> jigglypuffMap;
	private static Map<Integer, Move> zubatMap;
	private static Map<Integer, Move> oddishMap;
	private static Map<Integer, Move> parasMap;
	private static Map<Integer, Move> venonatMap;
	private static Map<Integer, Move> diglettMap;
	private static Map<Integer, Move> meowthMap;
	private static Map<Integer, Move> psyduckMap;
	private static Map<Integer, Move> mankeyMap;
	private static Map<Integer, Move> growlitheMap;
	private static Map<Integer, Move> poliwagMap;
	private static Map<Integer, Move> abraMap;
	private static Map<Integer, Move> machopMap;
	private static Map<Integer, Move> bellsproutMap;
	private static Map<Integer, Move> tentacoolMap;
	private static Map<Integer, Move> geodudeMap;
	private static Map<Integer, Move> ponytaMap;
	private static Map<Integer, Move> slowpokeMap;
	private static Map<Integer, Move> magnemiteMap;
	private static Map<Integer, Move> farfetchdMap;
	private static Map<Integer, Move> doduoMap;
	private static Map<Integer, Move> seelMap;
	private static Map<Integer, Move> grimerMap;
	private static Map<Integer, Move> shellderMap;
	private static Map<Integer, Move> gastlyMap;
	private static Map<Integer, Move> onixMap;
	private static Map<Integer, Move> drowzeeMap;
	private static Map<Integer, Move> krabbyMap;
	private static Map<Integer, Move> voltorbMap;
	private static Map<Integer, Move> exeggcuteMap;
	private static Map<Integer, Move> cuboneMap;
	private static Map<Integer, Move> hitmonleeMap;
	private static Map<Integer, Move> hitmonchanMap;
	private static Map<Integer, Move> lickitungMap;
	private static Map<Integer, Move> koffingMap;
	private static Map<Integer, Move> rhyhornMap;
	private static Map<Integer, Move> chanseyMap;
	private static Map<Integer, Move> tangelaMap;
	private static Map<Integer, Move> kangaskhanMap;
	private static Map<Integer, Move> horseaMap;
	private static Map<Integer, Move> goldeenMap;
	private static Map<Integer, Move> staryuMap;
	private static Map<Integer, Move> mrmimeMap;
	private static Map<Integer, Move> scytherMap;
	private static Map<Integer, Move> jynxMap;
	private static Map<Integer, Move> electabuzzMap;
	private static Map<Integer, Move> magmarMap;
	private static Map<Integer, Move> pinsirMap;
	private static Map<Integer, Move> taurosMap;
	private static Map<Integer, Move> magikarpMap;
	private static Map<Integer, Move> laprasMap;
	private static Map<Integer, Move> dittoMap;
	private static Map<Integer, Move> eeveeMap;
	private static Map<Integer, Move> vaporeonMap;
	private static Map<Integer, Move> jolteonMap;
	private static Map<Integer, Move> flareonMap;
	private static Map<Integer, Move> porygonMap;
	private static Map<Integer, Move> omanyteMap;
	private static Map<Integer, Move> kabutoMap;
	private static Map<Integer, Move> aerodactylMap;
	private static Map<Integer, Move> snorlaxMap;
	private static Map<Integer, Move> articunoMap;
	private static Map<Integer, Move> zapdosMap;
	private static Map<Integer, Move> moltresMap;
	private static Map<Integer, Move> dratiniMap;
	private static Map<Integer, Move> mewtwoMap;
	private static Map<Integer, Move> mewMap;
	private static Map<Integer, Move> rayquazaMap;
	private static Map<Integer, Move> missignoMap;
	private static boolean isInit = false;
	

	private InitializeMoves() {}
	
    private static void initializeMoves() {
        if (isInit) {
        	return;
        }
        
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
        
        /*Initialization of fireStarters using Guava ImmutableMap*/
        charmanderMap = ImmutableMap.<Integer, Move>builder()
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
    
        Pokedex.CHARMANDER.initializeMoveset(charmanderMap);
        Pokedex.CHARMELEON.initializeMoveset(charmanderMap);
        Pokedex.CHARIZARD.initializeMoveset(charmanderMap);
        
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
    		return charmanderMap;
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
