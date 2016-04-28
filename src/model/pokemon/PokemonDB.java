package model.pokemon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import controller.parameters.BackSpriteImage;
import controller.parameters.FrontSpriteImage;

/*
 * Dunno if sprite should go there
 */

public enum PokemonDB {
                       /*	   FirstType         SecondType       PS   ATK  DEF   SPD   			   EVO_LVL       */
    MISSINGNO("MISSINGNO",   PokemonType.NORMAL, PokemonType.NONE,     0,   0,   0,    0,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), null, null),     
    
    BULBASAUR("Bulbasaur",   PokemonType.GRASS,  PokemonType.POISON,  45,  49,  49,   45,  PokemonRarity.STARTER,    16, "IVYSAUR",    new HashMap<>(), FrontSpriteImage.BULBASAUR, BackSpriteImage.BULBASAUR),
    IVYSAUR("Ivysaur",       PokemonType.GRASS,  PokemonType.POISON,  60,  62,  63,   60,  PokemonRarity.STARTER,    32, "VENUSAUR",   new HashMap<>(), FrontSpriteImage.IVYSAUR, BackSpriteImage.IVYSAUR),
    VENUSAUR("Venusaur",     PokemonType.GRASS,  PokemonType.POISON,  80,  82,  83,   80,  PokemonRarity.STARTER,    -1, "",           new HashMap<>(), FrontSpriteImage.VENUSAUR, BackSpriteImage.VENUSAUR),
    
    CHARMANDER("Charmander", PokemonType.FIRE,   PokemonType.NONE,    39,  52,  43,   65,  PokemonRarity.STARTER,    16, "CHARMELEON", new HashMap<>(), FrontSpriteImage.CHARMANDER, BackSpriteImage.CHARMANDER),
    CHARMELEON("Charmeleon", PokemonType.FIRE,   PokemonType.NONE,    58,  64,  58,   80,  PokemonRarity.STARTER,    36, "CHARIZARD",  new HashMap<>(), FrontSpriteImage.CHARMELEON, BackSpriteImage.CHARMELEON),
    CHARIZARD("Charizard",   PokemonType.FIRE,   PokemonType.FLYING,  78,  84,  78,  100,  PokemonRarity.STARTER,    -1, "",           new HashMap<>(), FrontSpriteImage.CHARIZARD, BackSpriteImage.CHARIZARD),
    
    SQUIRTLE("Squirtle",     PokemonType.WATER,  PokemonType.NONE,    44,  48,  65,   43,  PokemonRarity.STARTER,    16, "WARTORTLE",  new HashMap<>(), FrontSpriteImage.SQUIRTLE, BackSpriteImage.SQUIRTLE),
    WARTORTLE("Wartortle",   PokemonType.WATER,  PokemonType.NONE,    59,  63,  80,   58,  PokemonRarity.STARTER,    36, "BLASTOISE",  new HashMap<>(), FrontSpriteImage.WARTORTLE, BackSpriteImage.WARTORTLE),
    BLASTOISE("Blastoise",   PokemonType.WATER,  PokemonType.NONE,    79,  83, 100,   78,  PokemonRarity.STARTER,    -1, "",           new HashMap<>(), FrontSpriteImage.BLASTOISE, BackSpriteImage.BLASTOISE),
    
    PIDGEY("Pidgey",         PokemonType.NORMAL, PokemonType.FLYING,  40,  45,  40,   56,  PokemonRarity.COMMON,     18, "PIDGEOTTO",  new HashMap<>(), FrontSpriteImage.PIDGEY, BackSpriteImage.PIDGEY),
    PIDGEOTTO("Pidgeotto",   PokemonType.NORMAL, PokemonType.FLYING,  63,  60,  55,   71,  PokemonRarity.UNCOMMON,   36, "PIDGEOT",    new HashMap<>(), FrontSpriteImage.PIDGEOTTO, BackSpriteImage.PIDGEOTTO),
    PIDGEOT("Pidgeot",       PokemonType.NORMAL, PokemonType.FLYING,  83,  80,  75,   91,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.PIDGEOT, BackSpriteImage.PIDGEOT),
    
    RATTATA("Rattata",       PokemonType.NORMAL, PokemonType.NONE,    30,  56,  35,   72,  PokemonRarity.COMMON,     20, "RATICATE",   new HashMap<>(), FrontSpriteImage.RATTATA, BackSpriteImage.RATTATA),
    RATICATE("Raticate",     PokemonType.NORMAL, PokemonType.NONE,    55,  81,  60,   97,  PokemonRarity.UNCOMMON,   -1, "",           new HashMap<>(), FrontSpriteImage.RATICATE, BackSpriteImage.RATICATE),
    
    PIKACHU("Pikachu",       PokemonType.ELECTR, PokemonType.NONE,    35,  55,  30,   90,  PokemonRarity.RARE,       15, "RAICHU",     new HashMap<>(), FrontSpriteImage.PIKACHU, BackSpriteImage.PIKACHU),
    RAICHU("Raichu",         PokemonType.ELECTR, PokemonType.NONE,    60,  90,  55,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.RAICHU, BackSpriteImage.RAICHU),
    
    SANDSHREW("Sandshrew",   PokemonType.GROUND, PokemonType.NONE,    50,  75,  85,   40,  PokemonRarity.UNCOMMON,   22, "SANDSLASH",  new HashMap<>(), FrontSpriteImage.SANDSHREW, BackSpriteImage.SANDSHREW),
    SANDSLASH("Sandslash",   PokemonType.GROUND, PokemonType.NONE,    75, 100, 110,   65,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.SANDSLASH, BackSpriteImage.SANDSLASH),
    
    ZUBAT("Zubat",           PokemonType.POISON, PokemonType.FLYING,  40,  45,  35,   55,  PokemonRarity.COMMON,     22, "GOLBAT",     new HashMap<>(), FrontSpriteImage.ZUBAT, BackSpriteImage.ZUBAT),            
    GOLBAT("Golbat",         PokemonType.POISON, PokemonType.FLYING,  75,  80,  70,   90,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.GOLBAT, BackSpriteImage.GOLBAT),
      
    TENTACOOL("Tentacool",   PokemonType.WATER,  PokemonType.POISON,  40,  40,  35,   70,  PokemonRarity.COMMON,     30, "TENTACRUEL", new HashMap<>(), FrontSpriteImage.TENTACOOL, BackSpriteImage.TENTACOOL),
    TENTACRUEL("Tentacruel", PokemonType.WATER,  PokemonType.POISON,  80,  70,  65,  100,  PokemonRarity.RARE,       -1, "",           new HashMap<>(), FrontSpriteImage.TENTACRUEL, BackSpriteImage.TENTACRUEL),

    RAYQUAZA("Rayquaza",     PokemonType.DRAGON, PokemonType.FLYING, 105, 150,  90,   95,  PokemonRarity.LEGENDARY,  -1, "",           new HashMap<>(), FrontSpriteImage.RAYQUAZA, BackSpriteImage.RAYQUAZA);

    
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
    
    private PokemonDB(final String name, final PokemonType firstType, final PokemonType secondType, final int baseHP, final int baseATK, final int baseDEF, final int baseSPD,
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
    
    public FrontSpriteImage getFrontSprite() {
    	return this.frontSprite;
    }
    
    public BackSpriteImage getBackSprite() {
    	return this.backSprite;
    }
    
    @Override
    public String toString() {
    	return this.name().toUpperCase();
    }
}
