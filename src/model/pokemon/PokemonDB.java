package model.pokemon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Nome, Numero, Tipo, Mappa(Mosse:Livello), 
 * Ps base, Attacco base, Difesa Base, Velocita' Base
 * rarita', livello evoluzione(-1 se non si evolve piu')) //XML?!
 */

public enum PokemonDB {
                       /*	   FirstType         SecondType       PS   ATK  DEF   SPD   			   EVO_LVL       */
    MISSINGNO("MISSINGNO",   PokemonType.NORMAL, PokemonType.NONE,     0,   0,   0,    0,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>()),     
    
    BULBASAUR("Bulbasaur",   PokemonType.GRASS,  PokemonType.POISON,  45,  49,  49,   45,  PokemonRarity.STARTER,    16, "IVYSAUR",    new HashMap<>()),
    IVYSAUR("Ivysaur",       PokemonType.GRASS,  PokemonType.POISON,  60,  62,  63,   60,  PokemonRarity.STARTER,    32, "VENUSAUR",   new HashMap<>()),
    VENUSAUR("Venusaur",     PokemonType.GRASS,  PokemonType.POISON,  80,  82,  83,   80,  PokemonRarity.STARTER,    -1, "",           new HashMap<>()),
    
    CHARMANDER("Charmander", PokemonType.FIRE,   PokemonType.NONE,    39,  52,  43,   65,  PokemonRarity.STARTER,    16, "CHARMELEON", new HashMap<>()),
    CHARMELEON("Charmeleon", PokemonType.FIRE,   PokemonType.NONE,    58,  64,  58,   80,  PokemonRarity.STARTER,    36, "CHARIZARD",  new HashMap<>()),
    CHARIZARD("Charizard",   PokemonType.FIRE,   PokemonType.FLYING,  78,  84,  78,  100,  PokemonRarity.STARTER,    -1, "",           new HashMap<>()),
    
    SQUIRTLE("Squirtle",     PokemonType.WATER,  PokemonType.NONE,    44,  48,  65,   43,  PokemonRarity.STARTER,    16, "WARTORTLE",  new HashMap<>()),
    WARTORTLE("Wartortle",   PokemonType.WATER,  PokemonType.NONE,    59,  63,  80,   58,  PokemonRarity.STARTER,    36, "BLASTOISE",  new HashMap<>()),
    BLASTOISE("Blastoise",   PokemonType.WATER,  PokemonType.NONE,    79,  83, 100,   78,  PokemonRarity.STARTER,    -1, "",           new HashMap<>()),
    
    PIDGEY("Pidgey",         PokemonType.NORMAL, PokemonType.FLYING,  40,  45,  40,   56,  PokemonRarity.COMMON,     18, "PIDGEOTTO",  new HashMap<>()),
    PIDGEOTTO("Pidgeotto",   PokemonType.NORMAL, PokemonType.FLYING,  63,  60,  55,   71,  PokemonRarity.UNCOMMON,   36, "PIDGEOT",    new HashMap<>()),
    PIDGEOT("Pidgeot",       PokemonType.NORMAL, PokemonType.FLYING,  83,  80,  75,   91,  PokemonRarity.RARE,       -1, "",           new HashMap<>()),
    
    RATTATA("Rattata",       PokemonType.NORMAL, PokemonType.NONE,    30,  56,  35,   72,  PokemonRarity.COMMON,     20, "RATICATE",   new HashMap<>()),
    RATICATE("Raticate",     PokemonType.NORMAL, PokemonType.NONE,    55,  81,  60,   97,  PokemonRarity.UNCOMMON,   -1, "",           new HashMap<>()),
    
    PIKACHU("Pikachu",       PokemonType.ELECTR, PokemonType.NONE,    35,  55,  30,   90,  PokemonRarity.RARE,       15, "RAICHU",     new HashMap<>()),
    RAICHU("Raichu",         PokemonType.ELECTR, PokemonType.NONE,    60,  90,  55,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>()),
    
    SANDSHREW("Sandshrew",   PokemonType.GROUND, PokemonType.NONE,    50,  75,  85,   40,  PokemonRarity.UNCOMMON,   22, "SANDSLASH",  new HashMap<>()),
    SANDSLASH("Sandslash",   PokemonType.GROUND, PokemonType.NONE,    75, 100, 110,   65,  PokemonRarity.RARE,       -1, "",           new HashMap<>()),
    
    TENTACOOL("Tentacool",   PokemonType.WATER,  PokemonType.POISON,  40,  40,  35,   70,  PokemonRarity.COMMON,     30, "TENTACRUEL", new HashMap<>()),
    TENTACRUEL("Tentacruel", PokemonType.WATER,  PokemonType.POISON,  80,  70,  65,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>()),
    
    ZUBAT("Zubat",           PokemonType.POISON, PokemonType.FLYING,  40,  45,  35,   55,  PokemonRarity.COMMON,     22, "GOLBAT",     new HashMap<>()),            
    GOLBAT("Golbat",         PokemonType.POISON, PokemonType.FLYING,  75,  80,  70,   90,  PokemonRarity.RARE,       -1, "",           new HashMap<>()),
    
    RAYQUAZA("Rayquaza",     PokemonType.DRAGON, PokemonType.FLYING, 105, 150,  90,   95,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>()),
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
   
    private PokemonDB(final String name, final PokemonType firstType, final PokemonType secondType, final int baseHP, final int baseATK, final int baseDEF, final int baseSPD,
            final PokemonRarity rarity, final int evolveLevel, final String evolvesTo, final Map<Integer, Move> moveset) {
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
    }

    public String getName() {
        return name;
    }

    public PokemonType getFirstType() {
        return this.firstType;
    }
    
    public PokemonType getSecondType() {
    	return this.secondType;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseATK() {
        return baseATK;
    }

    public int getBaseDEF() {
        return baseDEF;
    }

    public int getBaseSPD() {
        return baseSPD;
    }

    public PokemonRarity getRarity() {
        return rarity;
    }    
    
    public int getEvolveLevel() {
        return evolveLevel;
    }
    
    public PokemonDB getEvolvesToPokemon() {
        if (this.evolvesTo == "") {
            return PokemonDB.MISSINGNO;
        }
        return PokemonDB.valueOf(this.evolvesTo);
    }
    
    public Map<Integer, Move> getMoveset() {
        return Collections.unmodifiableMap(this.moveset);
    }
    
    public void init(final Map<Integer, Move> m) {
        this.moveset = m;
    }
}
