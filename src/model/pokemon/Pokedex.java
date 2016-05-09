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
