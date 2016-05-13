package model.pokemon;


import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public final class InitializeMoves {
	
	private static Map<Integer, Move> bulbasaurMap;
	private static Map<Integer, Move> ivysaurMap;
	private static Map<Integer, Move> venusaurMap;
	private static Map<Integer, Move> charmanderMap;
	private static Map<Integer, Move> charmeleonMap;
	private static Map<Integer, Move> charizardMap;
	private static Map<Integer, Move> squirtleMap;
	private static Map<Integer, Move> wartortleMap;
	private static Map<Integer, Move> blastoiseMap;
	private static Map<Integer, Move> caterpieMap;
	private static Map<Integer, Move> metapodMap;
	private static Map<Integer, Move> butterfreeMap;
	private static Map<Integer, Move> weedleMap;
	private static Map<Integer, Move> kakunaMap;
	private static Map<Integer, Move> beedrillMap;
	private static Map<Integer, Move> pidgeyMap;
	private static Map<Integer, Move> pidgeottoMap;
	private static Map<Integer, Move> pidgeotMap;
	private static Map<Integer, Move> rattataMap;
	private static Map<Integer, Move> raticateMap;
	private static Map<Integer, Move> spearowMap;
	private static Map<Integer, Move> fearowMap;
	private static Map<Integer, Move> ekansMap;
	private static Map<Integer, Move> arbokMap;
	private static Map<Integer, Move> pikachuMap;
	private static Map<Integer, Move> raichuMap;
	private static Map<Integer, Move> sandshrewMap;
	private static Map<Integer, Move> sandslashMap;
	private static Map<Integer, Move> nidoranfMap;
	private static Map<Integer, Move> nidorinaMap;
	private static Map<Integer, Move> nidoqueenMap;
	private static Map<Integer, Move> nidoranmMap;
	private static Map<Integer, Move> nidorinoMap;
	private static Map<Integer, Move> nidokingMap;
	private static Map<Integer, Move> clefairyMap;
	private static Map<Integer, Move> clefableMap;
	private static Map<Integer, Move> vulpixMap;
	private static Map<Integer, Move> ninetalesMap;
	private static Map<Integer, Move> jigglypuffMap;
	private static Map<Integer, Move> wigglytuffMap;
	private static Map<Integer, Move> zubatMap;
	private static Map<Integer, Move> golbatMap;
	private static Map<Integer, Move> oddishMap;
	private static Map<Integer, Move> gloomMap;
	private static Map<Integer, Move> vileplumeMap;
	private static Map<Integer, Move> parasMap;
	private static Map<Integer, Move> parasectMap;
	private static Map<Integer, Move> venonatMap;
	private static Map<Integer, Move> venomothMap;
	private static Map<Integer, Move> diglettMap;
	private static Map<Integer, Move> dugtrioMap;
	private static Map<Integer, Move> meowthMap;
	private static Map<Integer, Move> persianMap;
	private static Map<Integer, Move> psyduckMap;
	private static Map<Integer, Move> golduckMap;
	private static Map<Integer, Move> mankeyMap;
	private static Map<Integer, Move> primeapeMap;
	private static Map<Integer, Move> growlitheMap;
	private static Map<Integer, Move> arcanineMap;
	private static Map<Integer, Move> poliwagMap;
	private static Map<Integer, Move> poliwhirlMap;
	private static Map<Integer, Move> poliwrathMap;
	private static Map<Integer, Move> abraMap;
	private static Map<Integer, Move> kadabraMap;
	private static Map<Integer, Move> alakazamMap;
	private static Map<Integer, Move> machopMap;
	private static Map<Integer, Move> machokeMap;
	private static Map<Integer, Move> machampMap;
	private static Map<Integer, Move> bellsproutMap;
	private static Map<Integer, Move> weepinbellMap;
	private static Map<Integer, Move> victreebelMap;
	private static Map<Integer, Move> tentacoolMap;
	private static Map<Integer, Move> tentacruelMap;
	private static Map<Integer, Move> geodudeMap;
	private static Map<Integer, Move> gravelerMap;
	private static Map<Integer, Move> golemMap;
	private static Map<Integer, Move> ponytaMap;
	private static Map<Integer, Move> rapidashMap;
	private static Map<Integer, Move> slowpokeMap;
	private static Map<Integer, Move> slowbroMap;
	private static Map<Integer, Move> magnemiteMap;
	private static Map<Integer, Move> magnetonMap;
	private static Map<Integer, Move> farfetchdMap;
	private static Map<Integer, Move> doduoMap;
	private static Map<Integer, Move> dodrioMap;
	private static Map<Integer, Move> seelMap;
	private static Map<Integer, Move> dewgongMap;
	private static Map<Integer, Move> grimerMap;
	private static Map<Integer, Move> mukMap;
	private static Map<Integer, Move> shellderMap;
	private static Map<Integer, Move> cloysterMap;
	private static Map<Integer, Move> gastlyMap;
	private static Map<Integer, Move> haunterMap;
	private static Map<Integer, Move> gengarMap;
	private static Map<Integer, Move> onixMap;
	private static Map<Integer, Move> drowzeeMap;
	private static Map<Integer, Move> hypnoMap;
	private static Map<Integer, Move> krabbyMap;
	private static Map<Integer, Move> kinglerMap;
	private static Map<Integer, Move> voltorbMap;
	private static Map<Integer, Move> electrodeMap;
	private static Map<Integer, Move> exeggcuteMap;
	private static Map<Integer, Move> exeggutorMap;
	private static Map<Integer, Move> cuboneMap;
	private static Map<Integer, Move> marowakMap;
	private static Map<Integer, Move> hitmonleeMap;
	private static Map<Integer, Move> hitmonchanMap;
	private static Map<Integer, Move> lickitungMap;
	private static Map<Integer, Move> koffingMap;
	private static Map<Integer, Move> weezingMap;
	private static Map<Integer, Move> rhyhornMap;
	private static Map<Integer, Move> rhydonMap;
	private static Map<Integer, Move> chanseyMap;
	private static Map<Integer, Move> tangelaMap;
	private static Map<Integer, Move> kangaskhanMap;
	private static Map<Integer, Move> horseaMap;
	private static Map<Integer, Move> seadraMap;
	private static Map<Integer, Move> goldeenMap;
	private static Map<Integer, Move> seakingMap;
	private static Map<Integer, Move> staryuMap;
	private static Map<Integer, Move> starmieMap;
	private static Map<Integer, Move> mrmimeMap;
	private static Map<Integer, Move> scytherMap;
	private static Map<Integer, Move> jynxMap;
	private static Map<Integer, Move> electabuzzMap;
	private static Map<Integer, Move> magmarMap;
	private static Map<Integer, Move> pinsirMap;
	private static Map<Integer, Move> taurosMap;
	private static Map<Integer, Move> magikarpMap;
	private static Map<Integer, Move> gyaradosMap;
	private static Map<Integer, Move> laprasMap;
	private static Map<Integer, Move> dittoMap;
	private static Map<Integer, Move> eeveeMap;
	private static Map<Integer, Move> vaporeonMap;
	private static Map<Integer, Move> jolteonMap;
	private static Map<Integer, Move> flareonMap;
	private static Map<Integer, Move> porygonMap;
	private static Map<Integer, Move> omanyteMap;
	private static Map<Integer, Move> omastarMap;
	private static Map<Integer, Move> kabutoMap;
	private static Map<Integer, Move> kabutopsMap;
	private static Map<Integer, Move> aerodactylMap;
	private static Map<Integer, Move> snorlaxMap;
	private static Map<Integer, Move> articunoMap;
	private static Map<Integer, Move> zapdosMap;
	private static Map<Integer, Move> moltresMap;
	private static Map<Integer, Move> dratiniMap;
	private static Map<Integer, Move> dragonairMap;
	private static Map<Integer, Move> dragoniteMap;
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
        
            bulbasaurMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.TACKLE)
                    .put(4,Move.GROWL)
                    .put(10,Move.VINE_WHIP)
                    .put(20,Move.RAZOR_LEAF)
                    .put(32,Move.GROWTH)
                    .put(46,Move.SOLAR_BEAM)
            .build();
            
            ivysaurMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.TACKLE)
                    .put(4,Move.GROWL)
                    .put(10,Move.VINE_WHIP)
                    .put(22,Move.RAZOR_LEAF)
                    .put(38,Move.GROWTH)
                    .put(56,Move.SOLAR_BEAM)
            .build();
            
            venusaurMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.TACKLE)
                    .put(4,Move.GROWL)
                    .put(10,Move.VINE_WHIP)
                    .put(22,Move.RAZOR_LEAF)
                    .put(41,Move.GROWTH)
                    .put(65,Move.SOLAR_BEAM)
            .build();            
            
            charmanderMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.SCRATCH)
                    .put(7,Move.EMBER)
                    .put(13,Move.METAL_CLAW)
                    .put(19,Move.RAGE)
                    .put(25,Move.SCARY_FACE)
                    .put(31,Move.FLAMETHROWER)
                    .put(37,Move.SLASH)
                    .put(49,Move.FIRE_SPIN)
            .build();
            
            charmeleonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.SCRATCH)
                    .put(2,Move.GROWL)
                    .put(7,Move.EMBER)
                    .put(13,Move.METAL_CLAW)
                    .put(20,Move.RAGE)
                    .put(27,Move.SCARY_FACE)
                    .put(34,Move.FLAMETHROWER)
                    .put(41,Move.SLASH)
                    .put(55,Move.FIRE_SPIN)
            .build();
            
            charizardMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.HEAT_WAVE)
                    .put(2,Move.METAL_CLAW)
                    .put(3,Move.SCRATCH)
                    .put(4,Move.GROWL)
                    .put(5,Move.EMBER)
                    .put(20,Move.RAGE)
                    .put(27,Move.SCARY_FACE)
                    .put(34,Move.FLAMETHROWER)
                    .put(44,Move.SLASH)
                    .put(64,Move.FIRE_SPIN)
            .build();
            
            squirtleMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(4, Move.TAIL_WHIP)
                    .put(7, Move.BUBBLE)
                    .put(10, Move.WITHDRAW)
                    .put(13, Move.WATER_GUN)
                    .put(18, Move.BITE)
                    .put(23, Move.RAPID_SPIN)
                    .put(40, Move.SKULL_BASH)
                    .put(47, Move.HYDRO_PUMP)
            .build();
            
            wartortleMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(3, Move.BUBBLE)
                    .put(10, Move.WITHDRAW)
                    .put(13, Move.WATER_GUN)
                    .put(19, Move.BITE)
                    .put(25, Move.RAPID_SPIN)
                    .put(45, Move.SKULL_BASH)
                    .put(53, Move.HYDRO_PUMP)
            .build();
            
            blastoiseMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(4, Move.TAIL_WHIP)
                    .put(7, Move.BUBBLE)
                    .put(10, Move.WITHDRAW)
                    .put(13, Move.WATER_GUN)
                    .put(19, Move.BITE)
                    .put(25, Move.RAPID_SPIN)
                    .put(55, Move.SKULL_BASH)
                    .put(68, Move.HYDRO_PUMP)
            .build();
       
            caterpieMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.STRING_SHOT)
            .build();
            
            metapodMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.HARDEN)
            .build();
            
            butterfreeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONFUSION)
                    .put(28, Move.GUST)
                    .put(34, Move.PSYBEAM)
                    .put(47, Move.SILVER_WIND)
            .build();
            
            weedleMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POISON_STING)
                    .put(2, Move.STRING_SHOT)
            .build();
            
            kakunaMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.HARDEN)
            .build();
            
            beedrillMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.FURY_ATTACK)
                    .put(20, Move.TWINEEDLE)
                    .put(25, Move.RAGE)
                    .put(30, Move.PURSUIT)
                    .put(35, Move.PIN_MISSILE)
                    .put(40, Move.AGILITY)
            .build();
            
            pidgeyMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(9, Move.GUST)
                    .put(13, Move.QUICK_ATTACK)
                    .put(25, Move.WING_ATTACK)
                    .put(31, Move.FEATHER_DANCE)
                    .put(39, Move.AGILITY)
            .build();
            
            pidgeottoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.GUST)
                    .put(13, Move.QUICK_ATTACK)
                    .put(27, Move.WING_ATTACK)
                    .put(34, Move.FEATHER_DANCE)
                    .put(43, Move.AGILITY)
            .build();
            
            pidgeotMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.GUST)
                    .put(3, Move.QUICK_ATTACK)
                    .put(27, Move.WING_ATTACK)
                    .put(34, Move.FEATHER_DANCE)
                    .put(48, Move.AGILITY)
            .build();
            
            rattataMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(7, Move.QUICK_ATTACK)
                    .put(15, Move.HYPER_FANG)
                    .put(27, Move.PURSUIT)
            .build();
            
            raticateMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(3, Move.QUICK_ATTACK)
                    .put(13, Move.HYPER_FANG)
                    .put(30, Move.PURSUIT)
            .build();
            
            spearowMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(2, Move.GROWL)
                    .put(7, Move.LEER)
                    .put(13, Move.FURY_ATTACK)
                    .put(19, Move.PURSUIT)
                    .put(25, Move.AERIAL_ACE)
                    .put(37, Move.DRILL_PECK)
                    .put(43, Move.AGILITY)
            .build();
            
            fearowMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(2, Move.GROWL)
                    .put(7, Move.LEER)
                    .put(13, Move.FURY_ATTACK)
                    .put(26, Move.PURSUIT)
                    .put(40, Move.DRILL_PECK)
                    .put(47, Move.AGILITY)
            .build();
    
            ekansMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WRAP)
                    .put(2, Move.LEER)
                    .put(8, Move.POISON_STING)
                    .put(13, Move.BITE)
                    .put(25, Move.SCREECH)
                    .put(32, Move.ACID)
            .build();
    
            arbokMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WRAP)
                    .put(2, Move.LEER)
                    .put(8, Move.POISON_STING)
                    .put(13, Move.BITE)
                    .put(28, Move.SCREECH)
                    .put(38, Move.ACID)
            .build();
            
            pikachuMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.GROWL)
                    .put(2, Move.THUNDER_SHOCK)
                    .put(6, Move.TAIL_WHIP)
                    .put(11, Move.QUICK_ATTACK)
                    .put(20, Move.STRENGTH)
                    .put(26, Move.THUNDERBOLT)
                    .put(41, Move.AGILITY)
                    .put(42, Move.THUNDER)
            .build();
            
            raichuMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.GROWL)
                    .put(2, Move.THUNDER_SHOCK)
                    .put(6, Move.TAIL_WHIP)
                    .put(11, Move.QUICK_ATTACK)
                    .put(20, Move.STRENGTH)
                    .put(26, Move.THUNDERBOLT)
                    .put(41, Move.AGILITY)
                    .put(42, Move.THUNDER)
            .build();
            
            sandshrewMap = ImmutableMap.<Integer, Move>builder()
                    .put(3, Move.SCRATCH)
                    .put(5, Move.DEFENSE_CURL)
                    .put(7, Move.POISON_STING)
                    .put(15, Move.SLASH)
                    .put(25, Move.SWIFT)
                    .put(36, Move.FURY_SWIPES)
                    .put(42, Move.SAND_TOMB)
            .build();
            
            sandslashMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.DEFENSE_CURL)
                    .put(17, Move.POISON_STING)
                    .put(24, Move.SLASH)
                    .put(33, Move.SWIFT)
                    .put(40, Move.EARTHQUAKE)
                    .put(42, Move.FURY_SWIPES)
            .build();
            
            nidoranfMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.GROWL)
                    .put(8, Move.TAIL_WHIP)
                    .put(12, Move.DOUBLE_KICK)
                    .put(17, Move.POISON_STING)
                    .put(20, Move.BITE)
                    .put(30, Move.FURY_SWIPES)
                    .put(47, Move.CRUNCH)
            .build();
    
            nidorinaMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.GROWL)
                    .put(8, Move.TAIL_WHIP)
                    .put(12, Move.DOUBLE_KICK)
                    .put(18, Move.POISON_STING)
                    .put(22, Move.BITE)
                    .put(34, Move.FURY_SWIPES)
                    .put(53, Move.CRUNCH)
            .build();
    
            nidoqueenMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.GROWL)
                    .put(8, Move.TAIL_WHIP)
                    .put(12, Move.DOUBLE_KICK)
                    .put(18, Move.POISON_STING)
                    .put(22, Move.BODY_SLAM)
                    .put(43, Move.SUPERPOWER)
                    .put(46, Move.CRUNCH)
            .build();
    
            nidoranmMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEER)
                    .put(2, Move.PECK)
                    .put(12, Move.DOUBLE_KICK)
                    .put(17, Move.POISON_STING)
                    .put(20, Move.HORN_ATTACK)
                    .put(30, Move.FURY_ATTACK)
            .build();
    
            nidorinoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEER)
                    .put(2, Move.PECK)
                    .put(12, Move.DOUBLE_KICK)
                    .put(17, Move.POISON_STING)
                    .put(22, Move.HORN_ATTACK)
                    .put(34, Move.FURY_ATTACK)
            .build();
            
            nidokingMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEER)
                    .put(2, Move.PECK)
                    .put(12, Move.DOUBLE_KICK)
                    .put(17, Move.POISON_STING)
                    .put(22, Move.HORN_ATTACK)
                    .put(26, Move.THRASH)
                    .put(34, Move.FURY_ATTACK)
                    .put(43, Move.MEGAHORN)
            .build();
            
            clefairyMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(2, Move.GROWL)
                    .put(12, Move.DOUBLE_SLAP)
                    .put(25, Move.DEFENSE_CURL)
                    .put(33, Move.COSMIC_POWER)
                    .put(45, Move.METEOR_MASH)
            .build();
            
            clefableMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(2, Move.GROWL)
                    .put(12, Move.DOUBLE_SLAP)
                    .put(25, Move.DEFENSE_CURL)
                    .put(30, Move.COSMIC_POWER)
                    .put(40, Move.METEOR_MASH)
            .build();
            
            vulpixMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.EMBER)
                    .put(5, Move.TAIL_WHIP)
                    .put(13, Move.QUICK_ATTACK)
                    .put(25, Move.FLAMETHROWER)
                    .put(30, Move.FIRE_SPIN)
            .build();
            
            ninetalesMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.EMBER)
                    .put(5, Move.TAIL_WHIP)
                    .put(13, Move.QUICK_ATTACK)
                    .put(25, Move.FLAMETHROWER)
                    .put(36, Move.FIRE_BLAST)
            .build();
            
            jigglypuffMap = ImmutableMap.<Integer, Move>builder()
                    .put(4, Move.DEFENSE_CURL)
                    .put(9, Move.POUND)
                    .put(19, Move.ROLLOUT)
                    .put(25, Move.DOUBLE_SLAP)
                    .put(34, Move.BODY_SLAM)
                    .put(44, Move.HYPER_VOICE)
                    .put(50, Move.DOUBLE_EDGE)
                    .put(60, Move.HYPER_BEAM)
            .build();
            
            wigglytuffMap = ImmutableMap.<Integer, Move>builder()
                    .put(4, Move.DEFENSE_CURL)
                    .put(9, Move.POUND)
                    .put(19, Move.ROLLOUT)
                    .put(25, Move.DOUBLE_SLAP)
                    .put(34, Move.BODY_SLAM)
                    .put(44, Move.HYPER_VOICE)
                    .put(50, Move.DOUBLE_EDGE)
                    .put(60, Move.HYPER_BEAM)
            .build();
            
            zubatMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEECH_LIFE)
                    .put(5, Move.ASTONISH)
                    .put(7, Move.BITE)
                    .put(15, Move.WING_ATTACK)
                    .put(20, Move.AIR_CUTTER)
                    .put(25, Move.POISON_FANG)
                    .put(29, Move.SLUDGE_BOMB)
            .build();
            
            golbatMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEECH_LIFE)
                    .put(5, Move.ASTONISH)
                    .put(16, Move.BITE)
                    .put(21, Move.WING_ATTACK)
                    .put(23, Move.SCREECH)
                    .put(35, Move.AIR_CUTTER)
                    .put(49, Move.POISON_FANG)
            .build();
            
            oddishMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.ABSORB)
                    .put(10, Move.ACID)
                    .put(39, Move.PETAL_DANCE)
            .build();
            
            gloomMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.ABSORB)
                    .put(19, Move.ACID)
                    .put(36, Move.PETAL_DANCE)
            .build();
            
            vileplumeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.ABSORB)
                    .put(19, Move.ACID)
                    .put(40, Move.PETAL_DANCE)
                    .put(45, Move.SLUDGE_BOMB)
                    .put(50, Move.SOLAR_BEAM)
            .build();
            
            parasMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(12, Move.LEECH_LIFE)
                    .put(20, Move.SLASH)
                    .put(29, Move.GROWTH)
                    .put(34, Move.GIGA_DRAIN)
            .build();
            
            parasectMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(12, Move.LEECH_LIFE)
                    .put(30, Move.SLASH)
                    .put(33, Move.GROWTH)
                    .put(37, Move.GIGA_DRAIN)
                    .put(40, Move.SLUDGE_BOMB)
                    .put(45, Move.SWORDS_DANCE)
            .build();
            
            venonatMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(17, Move.CONFUSION)
                    .put(25, Move.LEECH_LIFE)
                    .put(33, Move.PSYBEAM)
                    .put(41, Move.PSYCHIC)
            .build();
            
            venomothMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(17, Move.CONFUSION)
                    .put(25, Move.LEECH_LIFE)
                    .put(29, Move.GUST)
                    .put(32, Move.SILVER_WIND)
                    .put(36, Move.PSYBEAM)
                    .put(45, Move.PSYCHIC)
            .build();
            
            diglettMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(5, Move.GROWL)
                    .put(17, Move.DIG)
                    .put(21, Move.FURY_SWIPES)
                    .put(25, Move.MUD_SLAP)
                    .put(33, Move.SLASH)
                    .put(41, Move.EARTHQUAKE)
            .build();
            
            dugtrioMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(5, Move.GROWL)
                    .put(17, Move.DIG)
                    .put(21, Move.FURY_SWIPES)
                    .put(25, Move.MUD_SLAP)
                    .put(30, Move.TRI_ATTACK)
                    .put(38, Move.SLASH)
                    .put(51, Move.EARTHQUAKE)
            .build();
            
            meowthMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(5, Move.GROWL)
                    .put(11, Move.BITE)
                    .put(18, Move.PAY_DAY)
                    .put(25, Move.SCREECH)
                    .put(30, Move.FURY_SWIPES)
                    .put(37, Move.SLASH)
                    .put(42, Move.FAKE_OUT)
            .build();
            
            persianMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(5, Move.GROWL)
                    .put(10, Move.BITE)
                    .put(18, Move.PAY_DAY)
                    .put(25, Move.SCREECH)
                    .put(30, Move.FURY_SWIPES)
                    .put(37, Move.SLASH)
                    .put(42, Move.FAKE_OUT)
                    .put(50, Move.HYPER_BEAM)
            .build();
            
            psyduckMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(5, Move.TAIL_WHIP)
                    .put(10, Move.CONFUSION)
                    .put(23, Move.SCREECH)
                    .put(31, Move.FURY_SWIPES)
                    .put(40, Move.HYDRO_PUMP)
            .build();
            
            golduckMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(5, Move.TAIL_WHIP)
                    .put(10, Move.CONFUSION)
                    .put(23, Move.SCREECH)
                    .put(27, Move.FURY_SWIPES)
                    .put(31, Move.SURF)
                    .put(35, Move.CROSS_CHOP)
                    .put(40, Move.HYDRO_PUMP)
            .build();
            
            mankeyMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.LEER)
                    .put(8, Move.KARATE_CHOP)
                    .put(16, Move.FURY_SWIPES)
                    .put(31, Move.CROSS_CHOP)
                    .put(37, Move.THRASH)
                    .put(42, Move.DYNAMIC_PUNCH)
            .build();
            
            primeapeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.LEER)
                    .put(8, Move.KARATE_CHOP)
                    .put(16, Move.FURY_SWIPES)
                    .put(22, Move.RAGE)
                    .put(26, Move.CROSS_CHOP)
                    .put(30, Move.SCREECH)
                    .put(34, Move.EARTHQUAKE)
                    .put(41, Move.THRASH)
                    .put(50, Move.FOCUS_PUNCH)
            .build();
            
            growlitheMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BITE)
                    .put(7, Move.EMBER)
                    .put(13, Move.LEER)
                    .put(20, Move.TAKE_DOWN)
                    .put(25, Move.FLAME_WHEEL)
                    .put(30, Move.AGILITY)
                    .put(40, Move.FLAMETHROWER)
            .build();
            
            arcanineMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BITE)
                    .put(7, Move.EMBER)
                    .put(13, Move.LEER)
                    .put(20, Move.TAKE_DOWN)
                    .put(25, Move.FLAME_WHEEL)
                    .put(30, Move.AGILITY)
                    .put(38, Move.FLAMETHROWER)
                    .put(43, Move.EXTREME_SPEED)
                    .put(50, Move.FIRE_BLAST)
            .build();
            
            poliwagMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BUBBLE)
                    .put(7, Move.WATER_GUN)
                    .put(15, Move.DOUBLE_SLAP)
                    .put(22, Move.BODY_SLAM)
                    .put(30, Move.BULK_UP)
                    .put(36, Move.SURF)
                    .put(43, Move.HYDRO_PUMP)
            .build();
            
            poliwhirlMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BUBBLE)
                    .put(13, Move.WATER_GUN)
                    .put(19, Move.DOUBLE_SLAP)
                    .put(25, Move.BODY_SLAM)
                    .put(30, Move.BULK_UP)
                    .put(38, Move.SURF)
                    .put(46, Move.HYDRO_PUMP)
            .build();
            
            poliwrathMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BUBBLE)
                    .put(2, Move.WATER_GUN)
                    .put(19, Move.DOUBLE_SLAP)
                    .put(30, Move.BODY_SLAM)
                    .put(35, Move.BULK_UP)
                    .put(39, Move.SURF)
                    .put(42, Move.DOUBLE_EDGE)
                    .put(46, Move.ICE_BEAM)
                    .put(50, Move.FOCUS_PUNCH)
                    .put(55, Move.HYDRO_PUMP)
            .build();
            
            abraMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONFUSION)
                    .put(18, Move.ICE_PUNCH)
                    .put(19, Move.THUNDER_PUNCH)
                    .put(20, Move.FIRE_PUNCH)
                    .put(34, Move.PSYCHIC)
            .build();
            
            kadabraMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONFUSION)
                    .put(21, Move.PSYBEAM)
                    .put(28, Move.ICE_PUNCH)
                    .put(29, Move.THUNDER_PUNCH)
                    .put(30, Move.FIRE_PUNCH)
                    .put(38, Move.PSYCHIC)
            .build();
            
            alakazamMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONFUSION)
                    .put(21, Move.PSYBEAM)
                    .put(31, Move.ICE_PUNCH)
                    .put(32, Move.THUNDER_PUNCH)
                    .put(33, Move.FIRE_PUNCH)
                    .put(43, Move.PSYCHIC)
            .build();
            
            machopMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEER)
                    .put(2, Move.KARATE_CHOP)
                    .put(15, Move.REVENGE)
                    .put(22, Move.VITAL_THROW)
                    .put(26, Move.SUBMISSION)
                    .put(31, Move.CROSS_CHOP)
                    .put(43, Move.FOCUS_PUNCH)
            .build();
            
            machokeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEER)
                    .put(2, Move.KARATE_CHOP)
                    .put(25, Move.REVENGE)
                    .put(32, Move.VITAL_THROW)
                    .put(40, Move.SUBMISSION)
                    .put(46, Move.CROSS_CHOP)
                    .put(59, Move.FOCUS_PUNCH)
            .build();
            
            machampMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LEER)
                    .put(2, Move.KARATE_CHOP)
                    .put(25, Move.REVENGE)
                    .put(33, Move.VITAL_THROW)
                    .put(41, Move.SUBMISSION)
                    .put(46, Move.CROSS_CHOP)
                    .put(50, Move.EARTHQUAKE)
                    .put(53, Move.BULK_UP)
                    .put(56, Move.DOUBLE_EDGE)
                    .put(59, Move.FOCUS_PUNCH)
            .build();
            
            bellsproutMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.VINE_WHIP)
                    .put(5, Move.GROWTH)
                    .put(11, Move.WRAP)
                    .put(20, Move.ACID)
                    .put(31, Move.RAZOR_LEAF)
                    .put(43, Move.SLAM)
            .build();
            
            weepinbellMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.VINE_WHIP)
                    .put(5, Move.GROWTH)
                    .put(11, Move.WRAP)
                    .put(24, Move.ACID)
                    .put(30, Move.RAZOR_LEAF)
                    .put(40, Move.SLAM)
            .build();
            
            victreebelMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.VINE_WHIP)
                    .put(5, Move.GROWTH)
                    .put(11, Move.WRAP)
                    .put(24, Move.ACID)
                    .put(30, Move.RAZOR_LEAF)
                    .put(33, Move.SLUDGE_BOMB)
                    .put(38, Move.SWORDS_DANCE)
                    .put(40, Move.SLAM)
                    .put(50, Move.SOLAR_BEAM)
            .build();
            
            tentacoolMap = ImmutableMap.<Integer, Move>builder()
                    .put(3, Move.POISON_STING)
                    .put(5, Move.CONSTRICT)
                    .put(7, Move.ACID)
                    .put(15, Move.BUBBLE_BEAM)
                    .put(20, Move.WRAP)
                    .put(26, Move.SCREECH)
                    .put(30, Move.SURF)
                    .put(35, Move.SLUDGE_BOMB)
                    .put(42, Move.HYDRO_PUMP)
            .build();
            
            tentacruelMap = ImmutableMap.<Integer, Move>builder()
                    .put(3, Move.POISON_STING)
                    .put(5, Move.CONSTRICT)
                    .put(7, Move.ACID)
                    .put(15, Move.BUBBLE_BEAM)
                    .put(20, Move.WRAP)
                    .put(26, Move.SCREECH)
                    .put(30, Move.SURF)
                    .put(35, Move.SLUDGE_BOMB)
                    .put(42, Move.SWORDS_DANCE)
                    .put(50, Move.HYDRO_PUMP)
                    .put(60, Move.HYPER_BEAM)
            .build();
            
            geodudeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(5, Move.DEFENSE_CURL)
                    .put(11, Move.ROCK_THROW)
                    .put(22, Move.ROLLOUT)
                    .put(29, Move.ROCK_BLAST)
                    .put(36, Move.EARTHQUAKE)
                    .put(41, Move.EXPLOSION)
                    .put(46, Move.DOUBLE_EDGE)
            .build();
            
            gravelerMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(5, Move.DEFENSE_CURL)
                    .put(11, Move.ROCK_THROW)
                    .put(22, Move.ROLLOUT)
                    .put(29, Move.ROCK_BLAST)
                    .put(39, Move.EARTHQUAKE)
                    .put(45, Move.EXPLOSION)
                    .put(50, Move.DOUBLE_EDGE)
            .build();
            
            golemMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(5, Move.DEFENSE_CURL)
                    .put(11, Move.ROCK_THROW)
                    .put(22, Move.ROLLOUT)
                    .put(32, Move.ROCK_BLAST)
                    .put(45, Move.EARTHQUAKE)
                    .put(48, Move.EXPLOSION)
                    .put(53, Move.DOUBLE_EDGE)
                    .put(60, Move.HYPER_BEAM)
            .build();
            
            ponytaMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(4, Move.QUICK_ATTACK)
                    .put(7, Move.GROWL)
                    .put(10, Move.TAIL_WHIP)
                    .put(16, Move.EMBER)
                    .put(20, Move.STOMP)
                    .put(26, Move.FIRE_SPIN)
                    .put(31, Move.TAKE_DOWN)
                    .put(35, Move.AGILITY)
                    .put(40, Move.BOUNCE)
                    .put(46, Move.FLAMETHROWER)
                    .put(51, Move.FIRE_BLAST)
            .build();
            
            rapidashMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(4, Move.QUICK_ATTACK)
                    .put(7, Move.GROWL)
                    .put(10, Move.TAIL_WHIP)
                    .put(16, Move.EMBER)
                    .put(20, Move.STOMP)
                    .put(26, Move.FIRE_SPIN)
                    .put(32, Move.TAKE_DOWN)
                    .put(35, Move.AGILITY)
                    .put(40, Move.FURY_ATTACK)
                    .put(46, Move.BOUNCE)
                    .put(50, Move.FLAMETHROWER)
                    .put(60, Move.FIRE_BLAST)
            .build();
            
            slowpokeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(7, Move.GROWL)
                    .put(10, Move.WATER_GUN)
                    .put(16, Move.CONFUSION)
                    .put(20, Move.HEADBUTT)
                    .put(26, Move.AMNESIA)
                    .put(32, Move.PSYCHIC)
                    .put(35, Move.CALM_MIND)
                    .put(40, Move.ICE_BEAM)
                    .put(48, Move.SURF)
            .build();
            
            slowbroMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(7, Move.GROWL)
                    .put(10, Move.WATER_GUN)
                    .put(16, Move.CONFUSION)
                    .put(20, Move.HEADBUTT)
                    .put(26, Move.AMNESIA)
                    .put(32, Move.PSYCHIC)
                    .put(35, Move.CALM_MIND)
                    .put(40, Move.SURF)
                    .put(45, Move.ICE_BEAM)
                    .put(50, Move.HYDRO_PUMP)
                    .put(60, Move.FIRE_BLAST)
            .build();
            
            magnemiteMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(3, Move.METAL_SOUND)
                    .put(8, Move.THUNDER_SHOCK)
                    .put(16, Move.SPARK)
                    .put(21, Move.SWIFT)
                    .put(26, Move.SCREECH)
                    .put(31, Move.ZAP_CANNON)
                    .put(36, Move.THUNDERBOLT)
                    .put(40, Move.EXPLOSION)
                    .put(50, Move.THUNDER)
            .build();
            
            magnetonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(3, Move.METAL_SOUND)
                    .put(8, Move.THUNDER_SHOCK)
                    .put(16, Move.SPARK)
                    .put(21, Move.SWIFT)
                    .put(26, Move.SCREECH)
                    .put(31, Move.ZAP_CANNON)
                    .put(40, Move.THUNDERBOLT)
                    .put(45, Move.EXPLOSION)
                    .put(55, Move.THUNDER)
            .build();
            
            farfetchdMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(3, Move.LEER)
                    .put(8, Move.KNOCK_OFF)
                    .put(12, Move.SLAM)
                    .put(16, Move.FURY_CUTTER)
                    .put(20, Move.SLASH)
                    .put(24, Move.SWORDS_DANCE)
                    .put(28, Move.AGILITY)
                    .put(33, Move.DOUBLE_EDGE)
                    .put(38, Move.HYPER_BEAM)
            .build();
            
            doduoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(3, Move.GROWL)
                    .put(8, Move.PURSUIT)
                    .put(12, Move.FURY_ATTACK)
                    .put(19, Move.TRI_ATTACK)
                    .put(24, Move.RAGE)
                    .put(27, Move.UPROAR)
                    .put(30, Move.AGILITY)
                    .put(36, Move.DRILL_PECK)
                    .put(42, Move.DOUBLE_EDGE)
                    .put(50, Move.HYPER_BEAM)
            .build();
            
            dodrioMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(3, Move.GROWL)
                    .put(8, Move.PURSUIT)
                    .put(12, Move.FURY_ATTACK)
                    .put(19, Move.TRI_ATTACK)
                    .put(24, Move.RAGE)
                    .put(27, Move.UPROAR)
                    .put(30, Move.AGILITY)
                    .put(36, Move.DRILL_PECK)
                    .put(42, Move.STEEL_WING)
                    .put(48, Move.DOUBLE_EDGE)
                    .put(55, Move.HYPER_BEAM)
            .build();
            
            seelMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.HEADBUTT)
                    .put(5, Move.BUBBLE)
                    .put(10, Move.ICY_WIND)
                    .put(18, Move.AURORA_BEAM)
                    .put(24, Move.TAKE_DOWN)
                    .put(30, Move.ICE_BEAM)
                    .put(35, Move.SURF)
            .build();
            
            dewgongMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.HEADBUTT)
                    .put(3, Move.GROWL)
                    .put(8, Move.ICY_WIND)
                    .put(12, Move.AURORA_BEAM)
                    .put(19, Move.TAKE_DOWN)
                    .put(35, Move.ICE_BEAM)
                    .put(37, Move.SURF)
                    .put(40, Move.BLIZZARD)
                    .put(45, Move.HYDRO_PUMP)
            .build();
            
            grimerMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(4, Move.HARDEN)
                    .put(13, Move.SLUDGE)
                    .put(26, Move.SCREECH)
                    .put(34, Move.ACID_ARMOR)
                    .put(43, Move.SLUDGE_BOMB)
            .build();
    
            mukMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(2, Move.HARDEN)
                    .put(13, Move.SLUDGE)
                    .put(26, Move.SCREECH)
                    .put(34, Move.ACID_ARMOR)
                    .put(47, Move.SLUDGE_BOMB)
            .build();
    
            shellderMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.WITHDRAW)
                    .put(8, Move.ICICLE_SPEAR)
                    .put(17, Move.AURORA_BEAM)
                    .put(33, Move.LEER)
                    .put(41, Move.CLAMP)
                    .put(49, Move.ICE_BEAM)
            .build();
    
            cloysterMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WITHDRAW)
                    .put(2, Move.AURORA_BEAM)
                    .put(41, Move.SPIKE_CANNON)
            .build();
    
            gastlyMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LICK)
                    .put(25, Move.SHADOW_PUNCH)
                    .put(31, Move.DREAM_EATER)
                    .put(45, Move.SHADOW_BALL)
            .build();
    
            haunterMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LICK)
                    .put(25, Move.SHADOW_PUNCH)
                    .put(31, Move.DREAM_EATER)
                    .put(45, Move.SHADOW_BALL)
            .build();
    
            gengarMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LICK)
                    .put(25, Move.SHADOW_PUNCH)
                    .put(31, Move.DREAM_EATER)
                    .put(45, Move.SHADOW_BALL)
            .build();
    
            onixMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.SCREECH)
                    .put(10, Move.BIND)
                    .put(14, Move.ROCK_THROW)
                    .put(23, Move.HARDEN)
                    .put(27, Move.RAGE)
                    .put(30, Move.DRAGON_BREATH)
                    .put(36, Move.SAND_TOMB)
                    .put(40, Move.SLAM)
                    .put(45, Move.IRON_TAIL)
                    .put(56, Move.DOUBLE_EDGE)
            .build();
            
            drowzeeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(11, Move.CONFUSION)
                    .put(17, Move.HEADBUTT)
                    .put(27, Move.MEDITATE)
                    .put(31, Move.PSYCHIC)
                    .put(45, Move.FUTURE_SIGHT)
            .build();
    
            hypnoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(11, Move.CONFUSION)
                    .put(17, Move.HEADBUTT)
                    .put(29, Move.MEDITATE)
                    .put(35, Move.PSYCHIC)
                    .put(60, Move.FUTURE_SIGHT)
            .build();
    
            krabbyMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BUBBLE)
                    .put(5, Move.LEER)
                    .put(12, Move.VICE_GRIP)
                    .put(16, Move.HARDEN)
                    .put(23, Move.MUD_SHOT)
                    .put(27, Move.STOMP)
                    .put(45, Move.CRABHAMMER)
            .build();
    
            kinglerMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.METAL_CLAW)
                    .put(2, Move.BUBBLE)
                    .put(3, Move.HARDEN)
                    .put(5, Move.LEER)
                    .put(12, Move.VICE_GRIP)
                    .put(23, Move.MUD_SHOT)
                    .put(27, Move.STOMP)
                    .put(57, Move.CRABHAMMER)
            .build();
    
            voltorbMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(8, Move.SCREECH)
                    .put(21, Move.SPARK)
                    .put(32, Move.ROLLOUT)
                    .put(37, Move.LIGHT_SCREEN)
                    .put(42, Move.SWIFT)
                    .put(46, Move.EXPLOSION)
            .build();
    
            electrodeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.SCREECH)
                    .put(21, Move.SPARK)
                    .put(34, Move.ROLLOUT)
                    .put(41, Move.LIGHT_SCREEN)
                    .put(48, Move.SWIFT)
                    .put(54, Move.EXPLOSION)
            .build();
    
            exeggcuteMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.UPROAR)
                    .put(2, Move.BARRAGE)
                    .put(19, Move.CONFUSION)
                    .put(43, Move.SOLAR_BEAM)
            .build();
    
            exeggutorMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONFUSION)
                    .put(2, Move.BARRAGE)
                    .put(19, Move.STOMP)
                    .put(31, Move.EGG_BOMB)
            .build();
    
            cuboneMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.GROWL)
                    .put(5, Move.TAIL_WHIP)
                    .put(9, Move.BONE_CLUB)
                    .put(13, Move.HEADBUTT)
                    .put(17, Move.LEER)
                    .put(25, Move.BONEMERANG)
                    .put(29, Move.RAGE)
                    .put(33, Move.FALSE_SWIPE)
                    .put(37, Move.THRASH)
                    .put(41, Move.BONE_RUSH)
                    .put(45, Move.DOUBLE_EDGE)
            .build();
    
            marowakMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.GROWL)
                    .put(2, Move.TAIL_WHIP)
                    .put(3, Move.BONE_CLUB)
                    .put(4, Move.HEADBUTT)
                    .put(17, Move.LEER)
                    .put(25, Move.BONEMERANG)
                    .put(32, Move.RAGE)
                    .put(39, Move.FALSE_SWIPE)
                    .put(46, Move.THRASH)
                    .put(53, Move.BONE_RUSH)
                    .put(61, Move.DOUBLE_EDGE)
            .build();
    
            hitmonleeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.DOUBLE_KICK)
                    .put(2, Move.REVENGE)
                    .put(6, Move.MEDITATE)
                    .put(11, Move.ROLLING_KICK)
                    .put(16, Move.JUMP_KICK)
                    .put(20, Move.BRICK_BREAK)
                    .put(26, Move.HIGH_JUMP_KICK)
                    .put(46, Move.MEGA_KICK)
            .build();
    
            hitmonchanMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.COMET_PUNCH)
                    .put(2, Move.REVENGE)
                    .put(7, Move.AGILITY)
                    .put(13, Move.PURSUIT)
                    .put(20, Move.MACH_PUNCH)
                    .put(26, Move.THUNDER_PUNCH)
                    .put(27, Move.ICE_PUNCH)
                    .put(28, Move.FIRE_PUNCH)
                    .put(32, Move.SKY_UPPERCUT)
                    .put(38, Move.MEGA_PUNCH)
            .build();
    
            lickitungMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.LICK)
                    .put(12, Move.DEFENSE_CURL)
                    .put(18, Move.KNOCK_OFF)
                    .put(23, Move.STOMP)
                    .put(29, Move.WRAP)
                    .put(40, Move.SLAM)
            .build();
    
            koffingMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(9, Move.SMOG)
                    .put(21, Move.SLUDGE)
                    .put(41, Move.EXPLOSION)
            .build();
    
            weezingMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.SMOG)
                    .put(21, Move.SLUDGE)
                    .put(44, Move.EXPLOSION)
            .build();
    
            rhyhornMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.HORN_ATTACK)
                    .put(2, Move.TAIL_WHIP)
                    .put(10, Move.STOMP)
                    .put(15, Move.FURY_ATTACK)
                    .put(24, Move.SCARY_FACE)
                    .put(29, Move.ROCK_BLAST)
                    .put(43, Move.TAKE_DOWN)
                    .put(52, Move.EARTHQUAKE)
                    .put(57, Move.MEGAHORN)
            .build();
    
            rhydonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.HORN_ATTACK)
                    .put(2, Move.TAIL_WHIP)
                    .put(3, Move.STOMP)
                    .put(4, Move.FURY_ATTACK)
                    .put(10, Move.STOMP)
                    .put(24, Move.SCARY_FACE)
                    .put(29, Move.ROCK_BLAST)
                    .put(46, Move.TAKE_DOWN)
                    .put(58, Move.EARTHQUAKE)
                    .put(66, Move.MEGAHORN)
            .build();
    
            chanseyMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(2, Move.GROWL)
                    .put(5, Move.TAIL_WHIP)
                    .put(17, Move.DOUBLE_SLAP)
                    .put(35, Move.EGG_BOMB)
                    .put(41, Move.DEFENSE_CURL)
                    .put(49, Move.LIGHT_SCREEN)
                    .put(57, Move.DOUBLE_EDGE)
            .build();
    
            tangelaMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONSTRICT)
                    .put(10, Move.ABSORB)
                    .put(13, Move.GROWTH)
                    .put(22, Move.VINE_WHIP)
                    .put(28, Move.BIND)
                    .put(31, Move.MEGA_DRAIN)
                    .put(40, Move.SLAM)
                    .put(46, Move.TICKLE)
            .build();
    
            kangaskhanMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.COMET_PUNCH)
                    .put(2, Move.LEER)
                    .put(7, Move.BITE)
                    .put(13, Move.TAIL_WHIP)
                    .put(19, Move.FAKE_OUT)
                    .put(25, Move.MEGA_PUNCH)
                    .put(31, Move.RAGE)
                    .put(43, Move.DIZZY_PUNCH)
            .build();
    
            horseaMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BUBBLE)
                    .put(15, Move.LEER)
                    .put(22, Move.WATER_GUN)
                    .put(29, Move.TWISTER)
                    .put(36, Move.AGILITY)
                    .put(43, Move.HYDRO_PUMP)
                    .put(50, Move.DRAGON_DANCE)
            .build();
    
            seadraMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BUBBLE)
                    .put(2, Move.LEER)
                    .put(3, Move.WATER_GUN)
                    .put(29, Move.TWISTER)
                    .put(40, Move.AGILITY)
                    .put(51, Move.HYDRO_PUMP)
                    .put(62, Move.DRAGON_DANCE)
            .build();
    
            goldeenMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(2, Move.TAIL_WHIP)
                    .put(15, Move.HORN_ATTACK)
                    .put(29, Move.FURY_ATTACK)
                    .put(38, Move.WATERFALL)
                    .put(52, Move.AGILITY)
                    .put(57, Move.MEGAHORN)
            .build();
    
            seakingMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(2, Move.TAIL_WHIP)
                    .put(15, Move.HORN_ATTACK)
                    .put(29, Move.FURY_ATTACK)
                    .put(41, Move.WATERFALL)
                    .put(61, Move.AGILITY)
                    .put(69, Move.MEGAHORN)
            .build();
    
            staryuMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.HARDEN)
                    .put(6, Move.WATER_GUN)
                    .put(10, Move.RAPID_SPIN)
                    .put(24, Move.SWIFT)
                    .put(28, Move.BUBBLE_BEAM)
                    .put(37, Move.LIGHT_SCREEN)
                    .put(42, Move.COSMIC_POWER)
                    .put(46, Move.HYDRO_PUMP)
            .build();
    
            starmieMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WATER_GUN)
                    .put(2, Move.RAPID_SPIN)
                    .put(3, Move.SWIFT)
            .build();
    
            mrmimeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.BARRIER)
                    .put(2, Move.CONFUSION)
                    .put(12, Move.MEDITATE)
                    .put(15, Move.DOUBLE_SLAP)
                    .put(21, Move.LIGHT_SCREEN)
                    .put(22, Move.MAGICAL_LEAF)
                    .put(29, Move.PSYBEAM)
                    .put(43, Move.PSYCHIC)
            .build();
    
            scytherMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.QUICK_ATTACK)
                    .put(2, Move.LEER)
                    .put(11, Move.PURSUIT)
                    .put(16, Move.FALSE_SWIPE)
                    .put(21, Move.AGILITY)
                    .put(26, Move.WING_ATTACK)
                    .put(31, Move.SLASH)
                    .put(36, Move.SWORDS_DANCE)
                    .put(46, Move.FURY_CUTTER)
            .build();
    
            jynxMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(2, Move.LICK)
                    .put(3, Move.POWDER_SNOW)
                    .put(21, Move.DOUBLE_SLAP)
                    .put(25, Move.ICE_PUNCH)
                    .put(41, Move.FAKE_TEARS)
                    .put(51, Move.BODY_SLAM)
                    .put(67, Move.BLIZZARD)
            .build();
    
            electabuzzMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.QUICK_ATTACK)
                    .put(2, Move.LEER)
                    .put(9, Move.THUNDER_PUNCH)
                    .put(17, Move.LIGHT_SCREEN)
                    .put(25, Move.SWIFT)
                    .put(36, Move.SCREECH)
                    .put(47, Move.THUNDERBOLT)
                    .put(58, Move.THUNDER)
            .build();
    
            magmarMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.EMBER)
                    .put(2, Move.LEER)
                    .put(3, Move.SMOG)
                    .put(4, Move.FIRE_PUNCH)
                    .put(7, Move.LEER)
                    .put(13, Move.SMOG)
                    .put(41, Move.FLAMETHROWER)
                    .put(57, Move.FIRE_BLAST)
            .build();
    
            pinsirMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.VICE_GRIP)
                    .put(7, Move.BIND)
                    .put(19, Move.HARDEN)
                    .put(25, Move.REVENGE)
                    .put(31, Move.BRICK_BREAK)
                    .put(43, Move.SUBMISSION)
                    .put(49, Move.SWORDS_DANCE)
            .build();
            
            taurosMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(4, Move.RAGE)
                    .put(13, Move.HORN_ATTACK)
                    .put(14, Move.SCARY_FACE)
                    .put(19, Move.PURSUIT)
                    .put(43, Move.THRASH)
                    .put(53, Move.TAKE_DOWN)
            .build();
    
            magikarpMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SPLASH)
                    .put(15, Move.TACKLE)
            .build();
    
            gyaradosMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.THRASH)
                    .put(20, Move.BITE)
                    .put(25, Move.DRAGON_RAGE)
                    .put(30, Move.LEER)
                    .put(35, Move.TWISTER)
                    .put(40, Move.HYDRO_PUMP)
                    .put(50, Move.DRAGON_DANCE)
                    .put(55, Move.HYPER_BEAM)
            .build();
    
            laprasMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WATER_GUN)
                    .put(2, Move.GROWL)
                    .put(13, Move.BODY_SLAM)
                    .put(31, Move.ICE_BEAM)
                    .put(49, Move.HYDRO_PUMP)
            .build();
    
            dittoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .build();
    
            eeveeMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(16, Move.GROWL)
                    .put(23, Move.QUICK_ATTACK)
                    .put(30, Move.BITE)
                    .put(42, Move.TAKE_DOWN)
            .build();
            
            vaporeonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(16, Move.WATER_GUN)
                    .put(23, Move.QUICK_ATTACK)
                    .put(30, Move.BITE)
                    .put(36, Move.AURORA_BEAM)
                    .put(47, Move.ACID_ARMOR)
                    .put(52, Move.HYDRO_PUMP)
            .build();
    
            jolteonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(16, Move.THUNDER_SHOCK)
                    .put(23, Move.QUICK_ATTACK)
                    .put(30, Move.DOUBLE_KICK)
                    .put(36, Move.PIN_MISSILE)
                    .put(47, Move.AGILITY)
                    .put(52, Move.THUNDER)
            .build();
    
            flareonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(2, Move.TAIL_WHIP)
                    .put(16, Move.EMBER)
                    .put(23, Move.QUICK_ATTACK)
                    .put(30, Move.BITE)
                    .put(36, Move.FIRE_SPIN)
                    .put(42, Move.SMOG)
                    .put(47, Move.LEER)
                    .put(52, Move.FLAMETHROWER)
            .build();
    
            porygonMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(9, Move.AGILITY)
                    .put(12, Move.PSYBEAM)
                    .put(24, Move.SHARPEN)
                    .put(36, Move.TRI_ATTACK)
                    .put(48, Move.ZAP_CANNON)
            .build();
    
            omanyteMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONSTRICT)
                    .put(2, Move.WITHDRAW)
                    .put(13, Move.BITE)
                    .put(19, Move.WATER_GUN)
                    .put(25, Move.MUD_SHOT)
                    .put(31, Move.LEER)
                    .put(43, Move.TICKLE)
                    .put(49, Move.ANCIENT_POWER)
                    .put(55, Move.HYDRO_PUMP)
            .build();
    
            omastarMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONSTRICT)
                    .put(2, Move.WITHDRAW)
                    .put(3, Move.BITE)
                    .put(4, Move.WATER_GUN)
                    .put(25, Move.MUD_SHOT)
                    .put(31, Move.LEER)
                    .put(40, Move.SPIKE_CANNON)
                    .put(46, Move.TICKLE)
                    .put(55, Move.ANCIENT_POWER)
                    .put(65, Move.HYDRO_PUMP)
            .build();
    
            kabutoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.SCRATCH)
                    .put(2, Move.HARDEN)
                    .put(13, Move.ABSORB)
                    .put(19, Move.LEER)
                    .put(25, Move.MUD_SHOT)
                    .put(43, Move.METAL_SOUND)
                    .put(49, Move.MEGA_DRAIN)
                    .put(55, Move.ANCIENT_POWER)
            .build();
    
            kabutopsMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.FURY_CUTTER)
                    .put(2, Move.SCRATCH)
                    .put(3, Move.HARDEN)
                    .put(4, Move.ABSORB)
                    .put(19, Move.LEER)
                    .put(25, Move.MUD_SHOT)
                    .put(40, Move.SLASH)
                    .put(46, Move.METAL_SOUND)
                    .put(55, Move.MEGA_DRAIN)
                    .put(65, Move.ANCIENT_POWER)
            .build();
    
            aerodactylMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WING_ATTACK)
                    .put(2, Move.BITE)
                    .put(3, Move.SCARY_FACE)
                    .put(17, Move.AGILITY)
                    .put(25, Move.ANCIENT_POWER)
                    .put(33, Move.CRUNCH)
                    .put(41, Move.TAKE_DOWN)
                    .put(57, Move.HYPER_BEAM)
                    .put(65, Move.ROCK_SLIDE)
            .build();
    
            snorlaxMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.TACKLE)
                    .put(4, Move.DEFENSE_CURL)
                    .put(9, Move.AMNESIA)
                    .put(12, Move.LICK)
                    .put(28, Move.SNORE)
                    .put(33, Move.BODY_SLAM)
                    .put(41, Move.ROLLOUT)
                    .put(44, Move.CRUNCH)
            .build();
    
            articunoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.GUST)
                    .put(2, Move.POWDER_SNOW)
                    .put(29, Move.ANCIENT_POWER)
                    .put(36, Move.AGILITY)
                    .put(43, Move.ICE_BEAM)
                    .put(71, Move.BLIZZARD)
            .build();
    
            zapdosMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.PECK)
                    .put(2, Move.THUNDER_SHOCK)
                    .put(29, Move.ANCIENT_POWER)
                    .put(43, Move.AGILITY)
                    .put(64, Move.LIGHT_SCREEN)
                    .put(71, Move.DRILL_PECK)
                    .put(78, Move.THUNDER)
            .build();
    
            moltresMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WING_ATTACK)
                    .put(2, Move.EMBER)
                    .put(8, Move.FIRE_SPIN)
                    .put(15, Move.AGILITY)
                    .put(29, Move.ANCIENT_POWER)
                    .put(36, Move.FLAMETHROWER)
                    .put(64, Move.HEAT_WAVE)
                    .put(71, Move.SOLAR_BEAM)
                    .put(78, Move.SKY_ATTACK)
            .build();
    
            dratiniMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WRAP)
                    .put(2, Move.LEER)
                    .put(11, Move.TWISTER)
                    .put(15, Move.DRAGON_RAGE)
                    .put(21, Move.SLAM)
                    .put(25, Move.AGILITY)
                    .put(53, Move.DRAGON_DANCE)
                    .put(61, Move.OUTRAGE)
                    .put(67, Move.HYPER_BEAM)
            .build();
    
            dragonairMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.WRAP)
                    .put(2, Move.LEER)
                    .put(11, Move.TWISTER)
                    .put(15, Move.DRAGON_RAGE)
                    .put(21, Move.SLAM)
                    .put(25, Move.AGILITY)
                    .put(53, Move.DRAGON_DANCE)
                    .put(61, Move.OUTRAGE)
                    .put(67, Move.HYPER_BEAM)
            .build();
    
            dragoniteMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.FIRE_PUNCH)
                    .put(2, Move.THUNDER_PUNCH)
                    .put(3, Move.WRAP)
                    .put(4, Move.LEER)
                    .put(11, Move.TWISTER)
                    .put(15, Move.DRAGON_RAGE)
                    .put(21, Move.SLAM)
                    .put(25, Move.AGILITY)
                    .put(53, Move.DRAGON_DANCE)
                    .put(55, Move.WING_ATTACK)
                    .put(64, Move.OUTRAGE)
                    .put(73, Move.HYPER_BEAM)
            .build();
    
            mewtwoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.CONFUSION)
                    .put(8, Move.BARRIER)
                    .put(15, Move.SWIFT)
                    .put(22, Move.FUTURE_SIGHT)
                    .put(57, Move.AMNESIA)
                    .put(71, Move.PSYCHIC)
            .build();
    
            mewMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.POUND)
                    .put(10, Move.MEGA_PUNCH)
                    .put(30, Move.PSYCHIC)
                    .put(40, Move.BARRIER)
                    .put(50, Move.ANCIENT_POWER)
                    .put(60, Move.AMNESIA)
            .build();
    
            rayquazaMap = ImmutableMap.<Integer, Move>builder()
                    .put(1,Move.TWISTER)
                    .put(5,Move.SCARY_FACE)
                    .put(15,Move.ANCIENT_POWER)
                    .put(20,Move.DRAGON_CLAW)
                    .put(30,Move.DRAGON_DANCE)
                    .put(35,Move.CRUNCH)
                    .put(45,Move.FLY)
                    .put(60,Move.EXTREME_SPEED)
                    .put(65,Move.OUTRAGE)
                    .put(75,Move.HYPER_BEAM)
            .build();
            
            missignoMap = ImmutableMap.<Integer, Move>builder()
                    .put(1, Move.NULLMOVE)
            .build();
            isInit = true;  
        }
        
        private static Map<Integer, Move> getPokedexMove(final Pokedex p) {
            switch(p) {
            case BULBASAUR : 
                    return bulbasaurMap;
            case IVYSAUR : 
                    return ivysaurMap;
            case VENUSAUR : 
                    return venusaurMap;
            case CHARMANDER : 
                    return charmanderMap;
            case CHARMELEON : 
                    return charmeleonMap;
            case CHARIZARD : 
                    return charizardMap;
            case SQUIRTLE : 
                    return squirtleMap;
            case WARTORTLE : 
                    return wartortleMap;
            case BLASTOISE : 
                    return blastoiseMap;
            case CATERPIE : 
                    return caterpieMap;
            case METAPOD : 
                    return metapodMap;
            case BUTTERFREE : 
                    return butterfreeMap;
            case WEEDLE : 
                    return weedleMap;
            case KAKUNA : 
                    return kakunaMap;
            case BEEDRILL : 
                    return beedrillMap;
            case PIDGEY : 
                    return pidgeyMap;
            case PIDGEOTTO : 
                    return pidgeottoMap;
            case PIDGEOT : 
                    return pidgeotMap;
            case RATTATA : 
                    return rattataMap;
            case RATICATE : 
                    return raticateMap;
            case SPEAROW : 
                    return spearowMap;
            case FEAROW : 
                    return fearowMap;
            case EKANS : 
                    return ekansMap;
            case ARBOK : 
                    return arbokMap;
            case PIKACHU : 
                    return pikachuMap;
            case RAICHU : 
                    return raichuMap;
            case SANDSHREW : 
                    return sandshrewMap;
            case SANDSLASH : 
                    return sandslashMap;
            case NIDORANF : 
                    return nidoranfMap;
            case NIDORINA : 
                    return nidorinaMap;
            case NIDOQUEEN : 
                    return nidoqueenMap;
            case NIDORANM : 
                    return nidoranmMap;
            case NIDORINO : 
                    return nidorinoMap;
            case NIDOKING : 
                    return nidokingMap;
            case CLEFAIRY : 
                    return clefairyMap;
            case CLEFABLE : 
                    return clefableMap;
            case VULPIX : 
                    return vulpixMap;
            case NINETALES : 
                    return ninetalesMap;
            case JIGGLYPUFF : 
                    return jigglypuffMap;
            case WIGGLYTUFF : 
                    return wigglytuffMap;
            case ZUBAT : 
                    return zubatMap;
            case GOLBAT : 
                    return golbatMap;
            case ODDISH : 
                    return oddishMap;
            case GLOOM : 
                    return gloomMap;
            case VILEPLUME : 
                    return vileplumeMap;
            case PARAS : 
                    return parasMap;
            case PARASECT : 
                    return parasectMap;
            case VENONAT : 
                    return venonatMap;
            case VENOMOTH : 
                    return venomothMap;
            case DIGLETT : 
                    return diglettMap;
            case DUGTRIO : 
                    return dugtrioMap;
            case MEOWTH : 
                    return meowthMap;
            case PERSIAN : 
                    return persianMap;
            case PSYDUCK : 
                    return psyduckMap;
            case GOLDUCK : 
                    return golduckMap;
            case MANKEY : 
                    return mankeyMap;
            case PRIMEAPE : 
                    return primeapeMap;
            case GROWLITHE : 
                    return growlitheMap;
            case ARCANINE : 
                    return arcanineMap;
            case POLIWAG : 
                    return poliwagMap;
            case POLIWHIRL : 
                    return poliwhirlMap;
            case POLIWRATH : 
                    return poliwrathMap;
            case ABRA : 
                    return abraMap;
            case KADABRA : 
                    return kadabraMap;  
            case ALAKAZAM : 
                    return alakazamMap;
            case MACHOP : 
                    return machopMap;
            case MACHOKE : 
                    return machokeMap;
            case MACHAMP : 
                    return machampMap;
            case BELLSPROUT : 
                    return bellsproutMap;
            case WEEPINBELL : 
                    return weepinbellMap;
            case VICTREEBEL : 
                    return victreebelMap;
            case TENTACOOL : 
                    return tentacoolMap;
            case TENTACRUEL : 
                    return tentacruelMap;
            case GEODUDE : 
                    return geodudeMap;
            case GRAVELER : 
                    return gravelerMap;
            case GOLEM : 
                    return golemMap;
            case PONYTA : 
                    return ponytaMap;
            case RAPIDASH : 
                    return rapidashMap;
            case SLOWPOKE : 
                    return slowpokeMap;
            case SLOWBRO : 
                    return slowbroMap;
            case MAGNEMITE : 
                    return magnemiteMap;
            case MAGNETON : 
                    return magnetonMap;
            case FARFETCHD : 
                    return farfetchdMap;
            case DODUO : 
                    return doduoMap;
            case DODRIO : 
                    return dodrioMap;
            case SEEL : 
                    return seelMap;
            case DEWGONG : 
                    return dewgongMap;
            case GRIMER : 
                    return grimerMap;
            case MUK : 
                    return mukMap;
            case SHELLDER : 
                    return shellderMap;
            case CLOYSTER : 
                    return cloysterMap;
            case GASTLY : 
                    return gastlyMap;
            case HAUNTER : 
                    return haunterMap;
            case GENGAR : 
                    return gengarMap;
            case ONIX : 
                    return onixMap;
            case DROWZEE : 
                    return drowzeeMap;
            case HYPNO : 
                    return hypnoMap;
            case KRABBY : 
                    return krabbyMap;
            case KINGLER : 
                    return kinglerMap;
            case VOLTORB : 
                    return voltorbMap;
            case ELECTRODE : 
                    return electrodeMap;
            case EXEGGCUTE : 
                    return exeggcuteMap;
            case EXEGGUTOR : 
                    return exeggutorMap;
            case CUBONE : 
                    return cuboneMap;
            case MAROWAK : 
                    return marowakMap;
            case HITMONLEE : 
                    return hitmonleeMap;
            case HITMONCHAN : 
                    return hitmonchanMap;
            case LICKITUNG : 
                    return lickitungMap;
            case KOFFING : 
                    return koffingMap;
            case WEEZING : 
                    return weezingMap;
            case RHYHORN : 
                    return rhyhornMap;
            case RHYDON : 
                    return rhydonMap;
            case CHANSEY : 
                    return chanseyMap;
            case TANGELA : 
                    return tangelaMap;
            case KANGASKHAN : 
                    return kangaskhanMap;
            case HORSEA : 
                    return horseaMap;
            case SEADRA : 
                    return seadraMap;
            case GOLDEEN : 
                    return goldeenMap;
            case SEAKING : 
                    return seakingMap;
            case STARYU : 
                    return staryuMap;
            case STARMIE : 
                    return starmieMap;
            case MRMIME : 
                    return mrmimeMap;
            case SCYTHER : 
                    return scytherMap;
            case JYNX : 
                    return jynxMap;
            case ELECTABUZZ : 
                    return electabuzzMap;
            case MAGMAR : 
                    return magmarMap;
            case PINSIR : 
                    return pinsirMap;
            case TAUROS : 
                    return taurosMap;
            case MAGIKARP : 
                    return magikarpMap;
            case GYARADOS : 
                    return gyaradosMap;
            case LAPRAS : 
                    return laprasMap;
            case DITTO : 
                    return dittoMap;
            case EEVEE : 
                    return eeveeMap;
            case VAPOREON : 
                    return vaporeonMap;
            case JOLTEON : 
                    return jolteonMap;
            case FLAREON : 
                    return flareonMap;
            case PORYGON : 
                    return porygonMap;
            case OMANYTE : 
                    return omanyteMap;
            case OMASTAR : 
                    return omastarMap;
            case KABUTO : 
                    return kabutoMap;
            case KABUTOPS : 
                    return kabutopsMap;
            case AERODACTYL : 
                    return aerodactylMap;
            case SNORLAX : 
                    return snorlaxMap;
            case ARTICUNO : 
                    return articunoMap;
            case ZAPDOS : 
                    return zapdosMap;
            case MOLTRES : 
                    return moltresMap;
            case DRATINI : 
                    return dratiniMap;
            case DRAGONAIR : 
                    return dragonairMap;
            case DRAGONITE : 
                    return dragoniteMap;
            case MEWTWO : 
                    return mewtwoMap;
            case MEW : 
                    return mewMap;
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
