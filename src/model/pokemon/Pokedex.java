package model.pokemon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import controller.parameters.BackSpriteImage;
import controller.parameters.FrontSpriteImage;

/**
 * A "database" of all the Pokemons in form of Enumeration.
 * Each type contains up to two {@link PokemonType}s, the base stats, the {@link PokemonRarity}
 * an {@link HashMap}<{@link Integer}, {@link Move}> of all the moves each Pokemon can learn
 * @see Pokemon
 * @see	Move
 * @see PokemonRarity
 * @see PokemonType
 */

public enum Pokedex {
                            /*	   FirstType         SecondType       PS   ATK  DEF   SPD   			   EVO_LVL       */
    MISSINGNO("MISSINGNO",   PokemonType.NORMAL, PokemonType.NONE,     0,   0,   0,    0,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.MISSINGNO, BackSpriteImage.MISSINGNO),     
    
    BULBASAUR("Bulbasaur",   PokemonType.GRASS,  PokemonType.POISON,  45,  49,  49,   45,  PokemonRarity.STARTER,    16, "IVYSAUR",    new HashMap<>(), FrontSpriteImage.BULBASAUR, BackSpriteImage.BULBASAUR),
    IVYSAUR("Ivysaur",       PokemonType.GRASS,  PokemonType.POISON,  60,  62,  63,   60,  PokemonRarity.STARTER,    32, "VENUSAUR",   new HashMap<>(), FrontSpriteImage.IVYSAUR, BackSpriteImage.IVYSAUR),
    VENUSAUR("Venusaur",     PokemonType.GRASS,  PokemonType.POISON,  80,  82,  83,   80,  PokemonRarity.STARTER,    -1, "",           new HashMap<>(), FrontSpriteImage.VENUSAUR, BackSpriteImage.VENUSAUR),
    
    CHARMANDER("Charmander", PokemonType.FIRE,   PokemonType.NONE,    39,  52,  43,   65,  PokemonRarity.STARTER,    16, "CHARMELEON", new HashMap<>(), FrontSpriteImage.CHARMANDER, BackSpriteImage.CHARMANDER),
    CHARMELEON("Charmeleon", PokemonType.FIRE,   PokemonType.NONE,    58,  64,  58,   80,  PokemonRarity.STARTER,    36, "CHARIZARD",  new HashMap<>(), FrontSpriteImage.CHARMELEON, BackSpriteImage.CHARMELEON),
    CHARIZARD("Charizard",   PokemonType.FIRE,   PokemonType.FLYING,  78,  84,  78,  100,  PokemonRarity.STARTER,    -1, "",           new HashMap<>(), FrontSpriteImage.CHARIZARD, BackSpriteImage.CHARIZARD),
    
    SQUIRTLE("Squirtle",     PokemonType.WATER,  PokemonType.NONE,    44,  48,  65,   43,  PokemonRarity.STARTER,    16, "WARTORTLE",  new HashMap<>(), FrontSpriteImage.SQUIRTLE, BackSpriteImage.SQUIRTLE),
    WARTORTLE("Wartortle",   PokemonType.WATER,  PokemonType.NONE,    59,  63,  80,   58,  PokemonRarity.STARTER,    36, "BLASTOISE",  new HashMap<>(), FrontSpriteImage.WARTORTLE, BackSpriteImage.WARTORTLE),
    BLASTOISE("Blastoise",   PokemonType.WATER,  PokemonType.NONE,    79,  83, 100,   78,  PokemonRarity.STARTER,    -1, "",           new HashMap<>(), FrontSpriteImage.BLASTOISE, BackSpriteImage.BLASTOISE),
    
    CATERPIE("Caterpie", 	 PokemonType.BUG,    PokemonType.NONE,    45,  30,  35,   45,  PokemonRarity.COMMON,      7, "METAPOD",    new HashMap<>(), FrontSpriteImage.CATERPIE, BackSpriteImage.CATERPIE),
    METAPOD("Metapod", 	     PokemonType.BUG,    PokemonType.NONE,    50,  20,  55,   30,  PokemonRarity.UNCOMMON,   10, "BUTTERFLY",  new HashMap<>(), FrontSpriteImage.METAPOD, BackSpriteImage.METAPOD),
    BUTTERFREE("Butterfree", PokemonType.BUG,    PokemonType.FLYING,  60,  45,	50,   70,  PokemonRarity.UNCOMMON,   -1, "",  		   new HashMap<>(), FrontSpriteImage.BUTTERFREE, BackSpriteImage.BUTTERFREE),
    
    WEEDLE("Weedle", 	     PokemonType.BUG,    PokemonType.POISON,  40,  35,  30,   50,  PokemonRarity.COMMON,      7, "KAKUNA",     new HashMap<>(), FrontSpriteImage.WEEDLE, BackSpriteImage.WEEDLE),
    KAKUNA("Kakuna", 	     PokemonType.BUG,    PokemonType.POISON,  45,  25,  50,   35,  PokemonRarity.UNCOMMON,   10, "BEEDRILL",   new HashMap<>(), FrontSpriteImage.KAKUNA, BackSpriteImage.KAKUNA),
    BEEDRILL("Beedrill", 	 PokemonType.BUG,    PokemonType.POISON,  65,  80,	40,   75,  PokemonRarity.UNCOMMON,   -1, "",  		   new HashMap<>(), FrontSpriteImage.BEEDRILL, BackSpriteImage.BEEDRILL),
    
    PIDGEY("Pidgey",         PokemonType.NORMAL, PokemonType.FLYING,  40,  45,  40,   56,  PokemonRarity.COMMON,     18, "PIDGEOTTO",  new HashMap<>(), FrontSpriteImage.PIDGEY, BackSpriteImage.PIDGEY),
    PIDGEOTTO("Pidgeotto",   PokemonType.NORMAL, PokemonType.FLYING,  63,  60,  55,   71,  PokemonRarity.UNCOMMON,   36, "PIDGEOT",    new HashMap<>(), FrontSpriteImage.PIDGEOTTO, BackSpriteImage.PIDGEOTTO),
    PIDGEOT("Pidgeot",       PokemonType.NORMAL, PokemonType.FLYING,  83,  80,  75,   91,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.PIDGEOT, BackSpriteImage.PIDGEOT),
    
    RATTATA("Rattata",       PokemonType.NORMAL, PokemonType.NONE,    30,  56,  35,   72,  PokemonRarity.COMMON,     20, "RATICATE",   new HashMap<>(), FrontSpriteImage.RATTATA, BackSpriteImage.RATTATA),
    RATICATE("Raticate",     PokemonType.NORMAL, PokemonType.NONE,    55,  81,  60,   97,  PokemonRarity.UNCOMMON,   -1, "",           new HashMap<>(), FrontSpriteImage.RATICATE, BackSpriteImage.RATICATE),
    
    SPEAROW("Spearow",       PokemonType.NORMAL, PokemonType.FLYING,  40,  30,  60,   70,  PokemonRarity.UNCOMMON,   36, "FEAROW",     new HashMap<>(), FrontSpriteImage.SPEAROW, BackSpriteImage.SPEAROW),
    FEAROW("Fearow",         PokemonType.NORMAL, PokemonType.FLYING,  65,  90,  65,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.FEAROW, BackSpriteImage.FEAROW),
    
    EKANS("Ekans",           PokemonType.POISON, PokemonType.NONE,    35,  60,  44,   55,  PokemonRarity.UNCOMMON,   22, "ARBOK",      new HashMap<>(), FrontSpriteImage.EKANS, BackSpriteImage.EKANS),
    ARBOK("Arbok",           PokemonType.POISON, PokemonType.NONE,    60,  85,  69,   80,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.ARBOK, BackSpriteImage.ARBOK),
  
    PIKACHU("Pikachu",       PokemonType.ELECTR, PokemonType.NONE,    35,  55,  30,   90,  PokemonRarity.RARE,       15, "RAICHU",     new HashMap<>(), FrontSpriteImage.PIKACHU, BackSpriteImage.PIKACHU),
    RAICHU("Raichu",         PokemonType.ELECTR, PokemonType.NONE,    60,  90,  55,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.RAICHU, BackSpriteImage.RAICHU),
    
    SANDSHREW("Sandshrew",   PokemonType.GROUND, PokemonType.NONE,    50,  75,  85,   40,  PokemonRarity.UNCOMMON,   22, "SANDSLASH",  new HashMap<>(), FrontSpriteImage.SANDSHREW, BackSpriteImage.SANDSHREW),
    SANDSLASH("Sandslash",   PokemonType.GROUND, PokemonType.NONE,    75, 100, 110,   65,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.SANDSLASH, BackSpriteImage.SANDSLASH),
    
    NIDORANF("NidoranF", 	 PokemonType.POISON, PokemonType.NONE,    55,  47,  52,   41,  PokemonRarity.COMMON,     16, "NIDORINA",   new HashMap<>(), FrontSpriteImage.NIDORANF, BackSpriteImage.NIDORANF),
    NIDORINA("Nidorina", 	 PokemonType.POISON, PokemonType.NONE,    70,  62,  67,   56,  PokemonRarity.UNCOMMON,   30, "NIDOQUEEN",  new HashMap<>(), FrontSpriteImage.NIDORINA, BackSpriteImage.NIDORINA),
    NIDOQUEEN("Nidoqueen", 	 PokemonType.POISON, PokemonType.GROUND,  90,  82,	87,   76,  PokemonRarity.UNFINDABLE, -1, "",  		   new HashMap<>(), FrontSpriteImage.NIDOQUEEN, BackSpriteImage.NIDOQUEEN),
    
    NIDORANM("NidoranM", 	 PokemonType.POISON, PokemonType.NONE,    46,  57,  40,   50,  PokemonRarity.COMMON,     16, "NIDORINO",   new HashMap<>(), FrontSpriteImage.NIDORANM, BackSpriteImage.NIDORANM),
    NIDORINO("Nidorino", 	 PokemonType.POISON, PokemonType.NONE,    61,  72,  57,   65,  PokemonRarity.UNCOMMON,   30, "NIDOKING",   new HashMap<>(), FrontSpriteImage.NIDORINO, BackSpriteImage.NIDORINO),
    NIDOKING("Nidoking", 	 PokemonType.POISON, PokemonType.GROUND,  81,  92,	77,   85,  PokemonRarity.UNFINDABLE, -1, "",  		   new HashMap<>(), FrontSpriteImage.NIDOKING, BackSpriteImage.NIDOKING),
    
    CLEFAIRY("Clefairy",     PokemonType.NORMAL, PokemonType.NONE,    70,  45,  48,   35,  PokemonRarity.RARE,       20, "CLEFABLE",   new HashMap<>(), FrontSpriteImage.CLEFAIRY, BackSpriteImage.CLEFAIRY),
    CLEFABLE("Clefable",     PokemonType.NORMAL, PokemonType.NONE,    90,  70,  73,   60,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.CLEFABLE, BackSpriteImage.CLEFABLE),
    
    VULPIX("Vulpix",         PokemonType.FIRE,   PokemonType.NONE,    38,  41,  40,   65,  PokemonRarity.UNCOMMON,    25, "NINETALES", new HashMap<>(), FrontSpriteImage.VULPIX, BackSpriteImage.VULPIX),
    NINETALES("Ninetales",   PokemonType.FIRE,   PokemonType.NONE,    73,  76,  75,  100,  PokemonRarity.RARE,        -1, "",          new HashMap<>(), FrontSpriteImage.NINETALES, BackSpriteImage.NINETALES),

    JIGGLYPUFF("Jigglypuff", PokemonType.NORMAL, PokemonType.NONE,   115,  45,  20,   20,  PokemonRarity.RARE,       20, "WIGGLYTUFF", new HashMap<>(), FrontSpriteImage.JIGGLYPUFF, BackSpriteImage.JIGGLYPUFF),
    WIGGLYTUFF("Wigglytuff", PokemonType.NORMAL, PokemonType.NONE,   140,  70,  45,   45,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.WIGGLYTUFF, BackSpriteImage.WIGGLYTUFF),
    
    ZUBAT("Zubat",           PokemonType.POISON, PokemonType.FLYING,  40,  45,  35,   55,  PokemonRarity.COMMON,     22, "GOLBAT",     new HashMap<>(), FrontSpriteImage.ZUBAT, BackSpriteImage.ZUBAT),            
    GOLBAT("Golbat",         PokemonType.POISON, PokemonType.FLYING,  75,  80,  70,   90,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.GOLBAT, BackSpriteImage.GOLBAT),
  
    ODDISH("Oddish",         PokemonType.GRASS,  PokemonType.POISON,  45,  50,  55,   30,  PokemonRarity.COMMON,     16, "GLOOM",      new HashMap<>(), FrontSpriteImage.ODDISH, BackSpriteImage.ODDISH),
    GLOOM("Gloom",           PokemonType.GRASS,  PokemonType.POISON,  60,  65,  70,   40,  PokemonRarity.UNCOMMON,   32, "VILEPLUME",  new HashMap<>(), FrontSpriteImage.GLOOM, BackSpriteImage.GLOOM),
    VILEPLUME("Vileplume",   PokemonType.GRASS,  PokemonType.POISON,  75,  85,  85,   50,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.VILEPLUME, BackSpriteImage.VILEPLUME),

    PARAS("Paras",           PokemonType.BUG, PokemonType.GRASS,      35,  70,  55,   25,  PokemonRarity.COMMON,     24, "PARASECT",   new HashMap<>(), FrontSpriteImage.PARAS, BackSpriteImage.PARAS),            
    PARASECT("Parasect",     PokemonType.BUG, PokemonType.GRASS,      60,  95,  80,   30,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.PARASECT, BackSpriteImage.PARASECT),
  
    VENONAT("Venonat",       PokemonType.BUG, PokemonType.POISON,     60,  55,  50,   45,  PokemonRarity.COMMON,     31, "VENOMOTH",   new HashMap<>(), FrontSpriteImage.VENONAT, BackSpriteImage.VENONAT),            
    VENOMOTH("Venomoth",     PokemonType.BUG, PokemonType.POISON,     70,  65,  60,   90,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.VENOMOTH, BackSpriteImage.VENOMOTH),
    
    DIGLETT("Diglett",       PokemonType.GROUND, PokemonType.NONE,    50,  75,  85,   40,  PokemonRarity.UNCOMMON,   26, "DUGTRIO",    new HashMap<>(), FrontSpriteImage.DIGLETT, BackSpriteImage.DIGLETT),
    DUGTRIO("Dugtrio",       PokemonType.GROUND, PokemonType.NONE,    75, 100, 110,   65,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.DUGTRIO, BackSpriteImage.DUGTRIO),

    MEOWTH("Meowth",         PokemonType.NORMAL, PokemonType.NONE,    40,  45,  35,   90,  PokemonRarity.RARE,       28, "PERSIAN",    new HashMap<>(), FrontSpriteImage.MEOWTH, BackSpriteImage.MEOWTH),
    PERSIAN("Persian",       PokemonType.NORMAL, PokemonType.NONE,    65,  70,  60,  115,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.PERSIAN, BackSpriteImage.PERSIAN),
    
    PSYDUCK("Psyduck",       PokemonType.WATER, PokemonType.NONE,     50,  52,  48,   55,  PokemonRarity.UNCOMMON,   33, "GOLDUCK",    new HashMap<>(), FrontSpriteImage.PSYDUCK, BackSpriteImage.PSYDUCK),
    GOLDUCK("Golduck",       PokemonType.WATER, PokemonType.NONE,     80,  82,  78,   85,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.GOLDUCK, BackSpriteImage.GOLDUCK),
    
    MANKEY("Mankey",         PokemonType.FIGHT, PokemonType.NONE,     40,  80,  35,   70,  PokemonRarity.UNCOMMON,   28, "PRIMEAPE",    new HashMap<>(), FrontSpriteImage.MANKEY, BackSpriteImage.MANKEY),
    PRIMEAPE("Primaepe",       PokemonType.FIGHT, PokemonType.NONE,     65, 105,  60,   95,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.PRIMEAPE, BackSpriteImage.PRIMEAPE),
    
    GROWLITHE("Growlithe",   PokemonType.FIRE,   PokemonType.NONE,    55,  70,  45,   60,  PokemonRarity.RARE,       29, "ARCANINE",   new HashMap<>(), FrontSpriteImage.GROWLITHE, BackSpriteImage.GROWLITHE),
    ARCANINE("Arcanine",     PokemonType.FIRE,   PokemonType.NONE,    90, 110,  80,   95,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.ARCANINE, BackSpriteImage.ARCANINE),
    
    POLIWAG("Poliwag",       PokemonType.WATER,  PokemonType.NONE,    40,  50,  40,   90,  PokemonRarity.COMMON,     16, "POLIWHIRL",  new HashMap<>(), FrontSpriteImage.POLIWAG, BackSpriteImage.POLIWAG),
    POLIWHIRL("Poliwhirl",   PokemonType.WATER,  PokemonType.NONE,    65,  65,  65,   90,  PokemonRarity.UNCOMMON,   36, "POLIWRATH",  new HashMap<>(), FrontSpriteImage.POLIWHIRL, BackSpriteImage.POLIWHIRL),
    POLIWRATH("Poliwrath",   PokemonType.WATER,  PokemonType.NONE,    90,  85,  95,   70,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.POLIWRATH, BackSpriteImage.POLIWRATH),

    ABRA("Abra",             PokemonType.PSYCHIC, PokemonType.NONE,   25,  20,  15,   90,  PokemonRarity.RARE,       25, "KADABRA",    new HashMap<>(), FrontSpriteImage.ABRA, BackSpriteImage.ABRA),
    KADABRA("Kadabra",       PokemonType.PSYCHIC, PokemonType.NONE,   40,  35,  30,  105,  PokemonRarity.RARE,       36, "ALAKAZAM",   new HashMap<>(), FrontSpriteImage.KADABRA, BackSpriteImage.KADABRA),
    ALAKAZAM("Alakazam",     PokemonType.PSYCHIC, PokemonType.NONE,   55,  50,  45,  120,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.ALAKAZAM, BackSpriteImage.ALAKAZAM),
 
    MACHOP("Machop",         PokemonType.FIGHT,  PokemonType.NONE,    70,  80,  50,   35,  PokemonRarity.COMMON,     28, "MACHOKE",    new HashMap<>(), FrontSpriteImage.MACHOP, BackSpriteImage.MACHOP),
    MACHOKE("Machoke",       PokemonType.FIGHT,  PokemonType.NONE,    80, 100,  70,   45,  PokemonRarity.UNCOMMON,   36, "MACHAMP",    new HashMap<>(), FrontSpriteImage.MACHOKE, BackSpriteImage.MACHOKE),
    MACHAMP("Machamp",       PokemonType.FIGHT,  PokemonType.NONE,    90, 130,  80,   55,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.MACHAMP, BackSpriteImage.MACHAMP),
    
    BELLSPROUT("Bellsprout", PokemonType.GRASS,  PokemonType.POISON,  50,  75,  35,   40,  PokemonRarity.COMMON,     21, "WEEPINBELL", new HashMap<>(), FrontSpriteImage.BELLSPROUT, BackSpriteImage.BELLSPROUT),
    WEEPINBELL("Weepinbell", PokemonType.GRASS,  PokemonType.POISON,  65,  90,  50,   55,  PokemonRarity.UNCOMMON,   32, "VICTREEBEL", new HashMap<>(), FrontSpriteImage.WEEPINBELL, BackSpriteImage.WEEPINBELL),
    VICTREEBEL("Victreebel", PokemonType.GRASS,  PokemonType.POISON,  80, 105,  65,   70,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.VICTREEBEL, BackSpriteImage.VICTREEBEL),

    TENTACOOL("Tentacool",   PokemonType.WATER,  PokemonType.POISON,  40,  40,  35,   70,  PokemonRarity.COMMON,     30, "TENTACRUEL", new HashMap<>(), FrontSpriteImage.TENTACOOL, BackSpriteImage.TENTACOOL),
    TENTACRUEL("Tentacruel", PokemonType.WATER,  PokemonType.POISON,  80,  70,  65,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.TENTACRUEL, BackSpriteImage.TENTACRUEL),
    
    GEODUDE("Geodude",       PokemonType.GROUND,  PokemonType.ROCK,   40,  80, 100,   20,  PokemonRarity.COMMON,     21, "GRAVELER",   new HashMap<>(), FrontSpriteImage.GEODUDE, BackSpriteImage.GEODUDE),
    GRAVELER("Graveler",     PokemonType.GROUND,  PokemonType.ROCK,   55,  95, 115,   35,  PokemonRarity.UNCOMMON,   32, "GOLEM",      new HashMap<>(), FrontSpriteImage.GRAVELER, BackSpriteImage.GRAVELER),
    GOLEM("Golem",           PokemonType.GROUND,  PokemonType.ROCK,   80, 110, 130,   45,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.GOLEM, BackSpriteImage.GOLEM),

    PONYTA("Ponyta",         PokemonType.FIRE,   PokemonType.NONE,    50,  85,  55,   90,  PokemonRarity.RARE,       40, "RAPIDASH",   new HashMap<>(), FrontSpriteImage.PONYTA, BackSpriteImage.PONYTA),
    RAPIDASH("Rapidash",     PokemonType.FIRE,   PokemonType.NONE,    65, 100,  70,  105,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.RAPIDASH, BackSpriteImage.RAPIDASH),

    SLOWPOKE("Slowpoke",     PokemonType.WATER,  PokemonType.PSYCHIC, 90,  65,  65,   15,  PokemonRarity.UNCOMMON,   37, "SLOWBRO",    new HashMap<>(), FrontSpriteImage.SLOWPOKE, BackSpriteImage.SLOWPOKE),
    SLOWBRO("Slowbro",       PokemonType.WATER,  PokemonType.PSYCHIC, 95,  75, 110,   30,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.SLOWBRO, BackSpriteImage.SLOWBRO),
 
    MAGNEMITE("Magnemite",   PokemonType.ELECTR,  PokemonType.STEEL,  25,  35,  70,   45,  PokemonRarity.UNCOMMON,   30, "MAGNETON",   new HashMap<>(), FrontSpriteImage.MAGNEMITE, BackSpriteImage.MAGNEMITE),
    MAGNETON("Magneton",     PokemonType.ELECTR,  PokemonType.STEEL,  50,  60,  95,   70,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.MAGNETON, BackSpriteImage.MAGNETON),
 
    FARFETCHD("Farfetchd",   PokemonType.FLYING,  PokemonType.NORMAL, 52,  65,  55,   60,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.FARFETCHD, BackSpriteImage.FARFETCHD),

    DODUO("Doduo",           PokemonType.NORMAL, PokemonType.FLYING,  35,  85,  45,   75,  PokemonRarity.COMMON,     31, "DODRIO",     new HashMap<>(), FrontSpriteImage.DODUO, BackSpriteImage.DODUO),
    DODRIO("Dodrio",         PokemonType.NORMAL, PokemonType.FLYING,  50, 110,  70,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.DODRIO, BackSpriteImage.DODRIO),
    
    SEEL("Seel",             PokemonType.WATER,  PokemonType.ICE,     65,  45,  55,   45,  PokemonRarity.COMMON,    34, "DEWGONG",     new HashMap<>(), FrontSpriteImage.SEEL, BackSpriteImage.SEEL),
    DEWGONG("Dewgong",       PokemonType.WATER,  PokemonType.ICE,     90,  70,  80,   70,  PokemonRarity.RARE,      -1, "",            new HashMap<>(), FrontSpriteImage.DEWGONG, BackSpriteImage.DEWGONG),
    
    GRIMER("Grimer",         PokemonType.POISON,   PokemonType.NONE,  80,  80,  50,   25,  PokemonRarity.COMMON,     29, "MUK",        new HashMap<>(), FrontSpriteImage.GRIMER, BackSpriteImage.GRIMER),
    MUK("Muk",               PokemonType.POISON,   PokemonType.NONE, 105, 105,  75,   50,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.MUK, BackSpriteImage.MUK),

    SHELLDER("Shellder",     PokemonType.WATER,  PokemonType.ICE,     30,  65, 100,   70,  PokemonRarity.COMMON,    25, "CLOYSTER",    new HashMap<>(), FrontSpriteImage.SHELLDER, BackSpriteImage.SHELLDER),
    CLOYSTER("Cloyster",     PokemonType.WATER,  PokemonType.ICE,     50,  95, 180,   80,  PokemonRarity.RARE,      -1, "",            new HashMap<>(), FrontSpriteImage.CLOYSTER, BackSpriteImage.CLOYSTER),

    GASTLY("Gastly",         PokemonType.GHOST,  PokemonType.POISON,  30,  35,  30,   80,  PokemonRarity.COMMON,     25, "HAUNTER",    new HashMap<>(), FrontSpriteImage.GASTLY, BackSpriteImage.GASTLY),
    HAUNTER("Haunter",       PokemonType.GHOST,  PokemonType.POISON,  45,  50,  45,   95,  PokemonRarity.UNCOMMON,   32, "GENGAR",     new HashMap<>(), FrontSpriteImage.HAUNTER, BackSpriteImage.HAUNTER),
    GENGAR("Gengar",         PokemonType.GHOST,  PokemonType.POISON,  60,  65,  60,  110,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.GENGAR, BackSpriteImage.GENGAR),

    ONIX("Onix",             PokemonType.ROCK,   PokemonType.GROUND,  35,  45, 160,   70,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.ONIX, BackSpriteImage.ONIX),

    DROWZEE("Drowzee",       PokemonType.PSYCHIC,  PokemonType.NONE,  60,  48,  45,   42,  PokemonRarity.COMMON,     26, "HYPNO",      new HashMap<>(), FrontSpriteImage.DROWZEE, BackSpriteImage.DROWZEE),
    HYPNO("Hypno",           PokemonType.PSYCHIC,  PokemonType.NONE,  85,  73,  70,   67,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.HYPNO, BackSpriteImage.HYPNO),

    KRABBY("Krabby",         PokemonType.WATER,  PokemonType.NONE,    30, 105,  90,   50,  PokemonRarity.COMMON,     28, "KINGLER",    new HashMap<>(), FrontSpriteImage.KRABBY, BackSpriteImage.KRABBY),
    KINGLER("Kingler",       PokemonType.WATER,  PokemonType.NONE,    55, 130, 115,   75,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.KINGLER, BackSpriteImage.KINGLER),

    VOLTORB("Voltorb",       PokemonType.ELECTR,  PokemonType.NONE,   40,  30,  50,  100,  PokemonRarity.UNCOMMON,   30, "ELECTRODE",  new HashMap<>(), FrontSpriteImage.VOLTORB, BackSpriteImage.VOLTORB),
    ELECTRODE("Electrode",   PokemonType.ELECTR,  PokemonType.NONE,   60,  50,  70,  140,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.ELECTRODE, BackSpriteImage.ELECTRODE),
  
    EXEGGCUTE("Exeggecute",  PokemonType.GRASS, PokemonType.PSYCHIC,  60,  40,  80,   40,  PokemonRarity.UNCOMMON,   29, "EXEGGUTOR",  new HashMap<>(), FrontSpriteImage.EXEGGCUTE, BackSpriteImage.EXEGGCUTE),            
    EXEGGUTOR("Exeggutor",   PokemonType.GRASS, PokemonType.PSYCHIC,  95,  95,  85,   55,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.EXEGGUTOR, BackSpriteImage.EXEGGUTOR),

    CUBONE("Cubone",         PokemonType.GROUND, PokemonType.NONE,    50,  50,  95,   35,  PokemonRarity.UNCOMMON,   28, "MAROWAK",    new HashMap<>(), FrontSpriteImage.CUBONE, BackSpriteImage.CUBONE),
    MAROWAK("Marowak",       PokemonType.GROUND, PokemonType.NONE,    60,  80, 110,   45,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.MAROWAK, BackSpriteImage.MAROWAK),

    HITMONLEE("Hitmonchan",  PokemonType.FIGHT,  PokemonType.NONE,    50, 120,  53,   87,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.HITMONLEE, BackSpriteImage.HITMONLEE),
    HITMONCHAN("Hitmonchan", PokemonType.FIGHT,  PokemonType.NONE,    50, 105,  79,   76,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.HITMONCHAN, BackSpriteImage.HITMONCHAN),
    
    LICKITUNG("Lickitung",   PokemonType.NORMAL,  PokemonType.NONE,   90,  55,  75,   30,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.LICKITUNG, BackSpriteImage.LICKITUNG),
    
    RHYHORN("Rhyhorn",       PokemonType.GROUND,  PokemonType.ROCK,   80,  85,  95,   25,  PokemonRarity.UNCOMMON,   42, "RHYDON",     new HashMap<>(), FrontSpriteImage.RHYHORN, BackSpriteImage.RHYHORN),
    RHYDON("Rhydon",         PokemonType.GROUND,  PokemonType.ROCK,  105, 130, 120,   40,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.RHYDON, BackSpriteImage.RHYDON),

    CHANSEY("Chansey",       PokemonType.NORMAL,  PokemonType.NONE,  250,   5,   5,   50,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.CHANSEY, BackSpriteImage.CHANSEY),

    HORSEA("Horsea",         PokemonType.WATER,  PokemonType.NONE,    30,  40,  70,   60,  PokemonRarity.COMMON,     32, "SEADRA",     new HashMap<>(), FrontSpriteImage.HORSEA, BackSpriteImage.HORSEA),
    SEADRA("Seadra",         PokemonType.WATER,  PokemonType.NONE,    55,  65,  95,   85,  PokemonRarity.UNCOMMON,   -1, "",           new HashMap<>(), FrontSpriteImage.SEADRA, BackSpriteImage.SEADRA),

    GOLDEEN("Goldeen",       PokemonType.WATER,  PokemonType.NONE,    45,  67,  60,   63,  PokemonRarity.COMMON,     33, "SEAKING",    new HashMap<>(), FrontSpriteImage.GOLDEEN, BackSpriteImage.GOLDEEN),
    SEAKING("Seaking",       PokemonType.WATER,  PokemonType.NONE,    80,  92,  65,   68,  PokemonRarity.UNCOMMON,   -1, "",           new HashMap<>(), FrontSpriteImage.SEAKING, BackSpriteImage.SEAKING),

    STARYU("Staryu",         PokemonType.WATER,  PokemonType.NONE,    30,  40,  70,   60,  PokemonRarity.COMMON,     28, "STARMIE",    new HashMap<>(), FrontSpriteImage.STARYU, BackSpriteImage.STARYU),
    STARMIE("Starmie",       PokemonType.WATER,  PokemonType.PSYCHIC, 55,  65,  95,   85,  PokemonRarity.UNCOMMON,   -1, "",           new HashMap<>(), FrontSpriteImage.STARMIE, BackSpriteImage.STARMIE),

    MRMIME("MrMime",         PokemonType.PSYCHIC,  PokemonType.NONE,  40,  45,  65,   90,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.MRMIME, BackSpriteImage.MRMIME),

    SCYTHER("Scyther",       PokemonType.BUG,  PokemonType.FLYING,    70, 110,  80,  105,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.SCYTHER, BackSpriteImage.SCYTHER),

    JYNX("Jynx",             PokemonType.ICE,  PokemonType.PSYCHIC,   60,  50,  35,   95,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.JYNX, BackSpriteImage.JYNX),

    ELECTABUZZ("Electabuzz", PokemonType.ELECTR,  PokemonType.NONE,   65,  83,  57,   93,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.ELECTABUZZ, BackSpriteImage.ELECTABUZZ),

    MAGMAR("Magmar",         PokemonType.FIRE,  PokemonType.NONE,     65, 125,  57,   93,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.MAGMAR, BackSpriteImage.MAGMAR),

    PINSIR("Pinsir",         PokemonType.BUG,  PokemonType.NONE,      65, 125, 100,   85,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.PINSIR, BackSpriteImage.PINSIR),

    TAUROS("Tauros",         PokemonType.NORMAL,  PokemonType.NONE,   75, 100,  95,  110,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.TAUROS, BackSpriteImage.TAUROS),
    
    MAGIKARP("Magikarp",     PokemonType.WATER,  PokemonType.NONE,    20,  10,  55,   80,  PokemonRarity.COMMON,     28, "GYARADOS",   new HashMap<>(), FrontSpriteImage.MAGIKARP, BackSpriteImage.MAGIKARP),
    GYARADOS("Gyarados",     PokemonType.WATER,  PokemonType.FLYING,  95, 125,  79,   81,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.GYARADOS, BackSpriteImage.GYARADOS),

    LAPRAS("Gyarados",       PokemonType.WATER,  PokemonType.ICE,    130,  85,  80,   60,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.LAPRAS, BackSpriteImage.LAPRAS),
    
    DITTO("Ditto",           PokemonType.NORMAL,  PokemonType.NONE,   48,  48,  48,   48,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.DITTO, BackSpriteImage.DITTO),

    EEVEE("Eevee",           PokemonType.NORMAL,  PokemonType.NONE,   55,  55,  50,   55,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.EEVEE, BackSpriteImage.EEVEE),
    VAPOREON("Vaporeon",     PokemonType.WATER,  PokemonType.NONE,   130,  65,  60,   65,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.VAPOREON, BackSpriteImage.VAPOREON),
    JOLTEON("Jolteon",       PokemonType.ELECTR,  PokemonType.NONE,   65,  65,  60,  130,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.JOLTEON, BackSpriteImage.JOLTEON),
    FLAREON("Flareon",       PokemonType.FIRE,  PokemonType.NONE,     65, 130,  60,   65,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.FLAREON, BackSpriteImage.FLAREON),
    
    PORYGON("Porygon",       PokemonType.NORMAL,  PokemonType.NONE,   65,  60,  70,   40,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.PORYGON, BackSpriteImage.PORYGON),

    OMANYTE("Omanyte",       PokemonType.WATER,  PokemonType.ROCK,    35,  40, 100,   35,  PokemonRarity.UNFINDABLE, 40, "OMASTAR",    new HashMap<>(), FrontSpriteImage.OMANYTE, BackSpriteImage.OMANYTE),
    OMASTAR("Omastar",       PokemonType.WATER,  PokemonType.ROCK,    70,  60, 125,   55,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.OMASTAR, BackSpriteImage.OMASTAR),

    KABUTO("Kabuto",         PokemonType.WATER,  PokemonType.ROCK,    30,  80,  90,   55,  PokemonRarity.UNFINDABLE, 40, "KABUTOPS",   new HashMap<>(), FrontSpriteImage.KABUTO, BackSpriteImage.KABUTO),
    KABUTOPS("Kabutops",     PokemonType.WATER,  PokemonType.ROCK,    60, 115, 105,   80,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.KABUTOPS, BackSpriteImage.KABUTOPS),
    
    AERODACTYL("Aerodactyl", PokemonType.FLYING,  PokemonType.ROCK,   80, 105,  65,  130,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.AERODACTYL, BackSpriteImage.AERODACTYL),

    SNORLAX("Snorlax",       PokemonType.NORMAL,  PokemonType.NONE,  160, 110,  65,   30,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.SNORLAX, BackSpriteImage.SNORLAX),

    ARTICUNO("Articuno",     PokemonType.ICE, PokemonType.FLYING,     90,  85, 100,   85,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.ARTICUNO, BackSpriteImage.ARTICUNO),
    ZAPDOS("Zapdos",         PokemonType.ELECTR, PokemonType.FLYING,  90,  90,  85,  100,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.ZAPDOS, BackSpriteImage.ZAPDOS),
    MOLTRES("Moltres",       PokemonType.FIRE, PokemonType.FLYING,    90, 100,  90,   90,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.MOLTRES, BackSpriteImage.MOLTRES),

    DRATINI("Dratini",       PokemonType.DRAGON, PokemonType.FLYING,  41,  64,  45,   50,  PokemonRarity.RARE,       25, "DRAGONAIR",  new HashMap<>(), FrontSpriteImage.DRATINI, BackSpriteImage.DRATINI),
    DRAGONAIR("Dragonair",   PokemonType.DRAGON, PokemonType.FLYING,  61,  84,  65,   70,  PokemonRarity.UNFINDABLE, 32, "DRAGONITE",  new HashMap<>(), FrontSpriteImage.DRAGONAIR, BackSpriteImage.DRAGONAIR),
    DRAGONITE("Dragonite",   PokemonType.DRAGON, PokemonType.FLYING,  91, 134,  95,   80,  PokemonRarity.UNFINDABLE, -1, "",           new HashMap<>(), FrontSpriteImage.DRAGONITE, BackSpriteImage.DRAGONITE),

    MEWTWO("Mewtwo",         PokemonType.PSYCHIC, PokemonType.NONE,  106, 110,  90,  130,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.MEWTWO, BackSpriteImage.MEWTWO),
    MEW("Mew",               PokemonType.PSYCHIC, PokemonType.NONE,  106, 100, 100,  100,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.MEW, BackSpriteImage.MEW),

    
    RAYQUAZA("Rayquaza",     PokemonType.DRAGON, PokemonType.FLYING, 105, 150,  90,   95,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.RAYQUAZA, BackSpriteImage.RAYQUAZA),
	;
	
	private String name;
    private PokemonType firstType;
    private PokemonType secondType;
    /*
     * Da Migliorare
     */
    private int baseHP;
    private int baseATK;
    private int baseDEF;
    private int baseSPD;
    private PokemonRarity rarity;
    private int evolveLevel;
    private String evolvesTo;
    private Map<Integer, Move> moveset;
    private final FrontSpriteImage frontSprite;
    private final BackSpriteImage backSprite;
    
    static {
    	for (final Entry<Pokedex, Map<Integer, Move>> e : InitializeMoves.getAllMoves().entrySet()) {
    		e.getKey().initializeMoveset(e.getValue());
    	}
    }
    
    private Pokedex(final String name, final PokemonType firstType, final PokemonType secondType, final int baseHP, final int baseATK, final int baseDEF, final int baseSPD,
            final PokemonRarity rarity, final int evolveLevel, final String evolvesTo, final Map<Integer, Move> moveset, final FrontSpriteImage frontSprite, final BackSpriteImage backSprite) {
        this.name = name;
        this.firstType = firstType;
        this.secondType = secondType;
        this.baseHP = baseHP;
        this.baseATK = baseATK;
        this.baseDEF = baseDEF;
        this.baseSPD = baseSPD;
        this.rarity = rarity;
        this.evolveLevel = evolveLevel;
        this.evolvesTo = evolvesTo;
        this.moveset = moveset;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;
    }

    /**
     * A method that returns the name with a beginning capital letter.
     * E.g. Charizard
     * @return the name of the Pokemon
     */
    public String getName() {
        return name;
    }
    
    /**
     * A method that returns the first {@link PokemonType}
     * @return the first {@link PokemonType}
     */
    public PokemonType getFirstType() {
        return this.firstType;
    }
    
    /**
     * A method that returns the second {@link PokemonType}
     * @return the second {@link PokemonType}, PokemonType.NONE if it doesn't have one
     */
    public PokemonType getSecondType() {
    	return this.secondType;
    }
    
    /**
     * @return the base value of {@link Stat} HP
     */
    public int getBaseHP() {
        return baseHP;
    }

    /**
     * @return the base value of {@link Stat} ATTACK
     */
    public int getBaseATK() {
        return baseATK;
    }

    /**
     * @return the base value of {@link Stat} DEFENSE
     */    
    public int getBaseDEF() {
        return baseDEF;
    }

    /**
     * @return the base value of {@link Stat} SPEED
     */
    public int getBaseSPD() {
        return baseSPD;
    }

    /**
     * @return the {@link PokemonRarity} of the Pokemon
     */
    public PokemonRarity getRarity() {
        return rarity;
    }    
    
    /**
     * @return the level that allows the Pokemon to evolve, -1 if the Pokemon cannot evolve
     */
    public int getEvolveLevel() {
        return evolveLevel;
    }
    
    /**
     * A method to get the {@link Pokedex} entry of the next evolved stage of the Pokemon
     * @return	the next evolved stage, {@link Pokedex}.MISSINGNO if it doesn't have one
     */
    public Pokedex getEvolvesToPokemon() {
        if (this.evolvesTo == "") {
            return Pokedex.MISSINGNO;
        }
        return Pokedex.valueOf(this.evolvesTo);
    }
    
    /**
     * A method that returns an unmodifiable {@link Map}<{@link Integer}, {@link Move}> of all the moves per level
     * @return A map with all the moves per level
     */
    public Map<Integer, Move> getMoveset() {
        return Collections.unmodifiableMap(this.moveset);
    }
    
    /**
     * A method that allows to initialize the move of the Pokemon
     * @param a {@link Map}<{@link Integer}, {@link Move}> of the moveset of the single Pokemon
     */
    public void initializeMoveset(final Map<Integer, Move> m) {
        if (m != null && !m.isEmpty()) {
        	this.moveset = m;
        } else {
        	throw new IllegalArgumentException("moveset is either null or empty");
        }
    }
    
    /**
     * @return the {@link FrontSpriteImage} of the Pokemon
     */
    public FrontSpriteImage getFrontSprite() {
    	return this.frontSprite;
    }

    /**
     * @return the {@link BackSpriteImage} of the Pokemon
     */
    public BackSpriteImage getBackSprite() {
    	return this.backSprite;
    }
    
    /**
     * @return a capitalized version of the name
     */
    @Override
    public String toString() {
    	return this.name().toUpperCase();
    }
}
