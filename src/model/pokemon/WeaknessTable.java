package model.pokemon;

/**
 *  A class that implements the pattern Singleton that computes a {@link Move} effect
 *  according to the enemy {@link Pokemon} {@link PokemonType}s
 *  @see Pokemon
 *  @see PokemonType
 *  @see WeaknessType
 */
public class WeaknessTable {
	
    /**
     * All four of possible damage multipliers per single {@link PokemonType} comparison.
     * They stack up multiplicatively when considering more {@link PokemonType}s
     * @see WeaknessTable
     * @see Pokemon
     * @see PokemonType
     */
    public static enum WeaknessType {
		
        IMMUNE(0), OK(1), SUPEREFFECTIVE(2), NOT_SO_EFFECTIVE(0.5);
		
        private double multiplier;
		
        private WeaknessType(final double multiplier) {
            this.multiplier = multiplier;
            }
		
        /**
         * 
         * @return the double value of the WeaknessType
         */
        public double getMultiplier() {
            return this.multiplier;
        }
    }
	
    /**
     * Rows are attacking Types, Columns are defending types
     */
    private double[][] table;
    private static WeaknessTable singleton = null;
	
    private static int NORMAL = PokemonType.NORMAL.ordinal();
    private static int FLYING = PokemonType.FLYING.ordinal();
    private static int BUG = PokemonType.BUG.ordinal();
    private static int FIRE = PokemonType.FIRE.ordinal();
    private static int WATER = PokemonType.WATER.ordinal();
    private static int GRASS = PokemonType.GRASS.ordinal();
    private static int POISON = PokemonType.POISON.ordinal();
    private static int GROUND = PokemonType.GROUND.ordinal();
    private static int ELECTR = PokemonType.ELECTR.ordinal();
    private static int DRAGON = PokemonType.DRAGON.ordinal();
    private static int PSYCHIC = PokemonType.PSYCHIC.ordinal();
    private static int FIGHT = PokemonType.FIGHT.ordinal();
    private static int STEEL = PokemonType.STEEL.ordinal();
    private static int ROCK = PokemonType.ROCK.ordinal();
    private static int GHOST = PokemonType.GHOST.ordinal();
    private static int DARK = PokemonType.DARK.ordinal();
    private static int ICE = PokemonType.ICE.ordinal();

    private WeaknessTable() {
        table = new double[PokemonType.values().length][PokemonType.values().length];
        initWeaknesses();
    }
	
    /**
     * A method that implements the Singleton Pattern, thread-safe version
     * @return the only object of {@link WeaknessTable} that can be generated
     */
    public static WeaknessTable getWeaknessTable() {
        if (singleton == null) {
            singleton = new WeaknessTable();
        }
        return singleton;
    }
	
    /**
     * A method that calculates the damage multiplier for a move against a {@link Pokemon}
     * Because of the dual {@link PokemonType} all the different {@link WeaknessType}s stack multiplicatively <br>
     * For example a <i>GRASS</i> {@link Move}, against a {@link Pokemon} with <i>GROUND</i> and <i>WATER</i> types, will do <b>4x</b> damage
     * <i>GRASS</i> vs <i>GROUND</i> is <b>2x</b> and <i>GRASS</i> vs <i>WATER</i> is <b>2x</b>
     * In the same way a <i>GROUND</i> {@link Move} against a {@link Pokemon} with <i>FIRE</i> and <i>FLYING</i> types will do <b>0x</b> damage
     * because <i>FLYING</i> is immune to <i>GROUND</i> and even if <i>GROUND</i> is super-effective against <i>FIRE</i> the {@link Move} will do <b>0</b> damage.
     * @param move 			attacking {@link Move}
     * @param enemyType1	defending first {@link PokemonType}
     * @param enemyType2 	defending second {@link PokemonType}, can be {@link PokemonType}.NONE
     * @return				damage multiplier 
     * @see	PokemonType
     * @see Pokemon
     */
    public double getMultiplierAttack(final PokemonType move, final PokemonType enemyType1, final PokemonType enemyType2) {
        return this.table[move.ordinal()][enemyType1.ordinal()] * this.table[move.ordinal()][enemyType2.ordinal()];
    }
	
    private void initWeaknesses() {
        for (final PokemonType p : PokemonType.values()) {
            switch (p) {
			
            case NONE :
                /*Ok*/
                for (int i = 0; i < PokemonType.values().length; i++) {
                    table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                }
                break;
			
            case NORMAL :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == ROCK || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                        /*Immune*/
                    } else if (i == GHOST){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
			
            case FIGHT :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == FLYING || i == POISON || i == BUG || i == PSYCHIC) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == NORMAL || i == ROCK || i == STEEL || i == ICE || i == DARK) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Immune*/
                    } else if (i == GHOST){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();	
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
				
            case FLYING :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == ELECTR || i == ROCK || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == GRASS || i == BUG || i == FIGHT) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
				
            case POISON :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == GROUND || i == POISON || i == ROCK || i == GHOST) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == GRASS) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Immune*/
                    } else if (i == STEEL){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
				
            case GROUND :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == GRASS || i == BUG) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == FIRE || i == ELECTR || i == POISON || i == ROCK || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Immune*/
                    } else if (i == FLYING){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
	
            case ROCK :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == FIGHT || i == GROUND || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == FIRE || i == FLYING || i == BUG || i == ICE) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;	
				
            case BUG :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                        if (i == FLYING || i == POISON || i == FIRE || i == FIGHT || i == GHOST || i == STEEL) {
                            table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
			/*Supereffective*/
                        } else if (i == GRASS || i == PSYCHIC || i == DARK) {
                            table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
			/*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
				
            case GHOST :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == DARK || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == PSYCHIC || i == GHOST) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Immune*/
                    } else if (i == NORMAL){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;		
				
            case STEEL :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == FIRE || i == STEEL || i == WATER || i == ELECTR) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == ROCK || i == ICE) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
		
            case FIRE :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == FIRE || i == WATER || i == DRAGON || i == ROCK) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == GRASS || i == BUG || i == STEEL || i == ICE) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
			/*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
			
            case WATER :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == GRASS || i == WATER || i == DRAGON) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == FIRE || i == GROUND || i == ROCK) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
			
            case GRASS :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == GRASS || i == FIRE || i == POISON || i == FLYING || i == DRAGON || i == BUG || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == WATER || i == GROUND || i == ROCK) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
		    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
			
            case ELECTR :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == GRASS || i == ELECTR || i == DRAGON) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == WATER || i == FLYING) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Immune*/
                    } else if (i == GROUND){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;		
				
            case PSYCHIC :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == PSYCHIC || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == FIGHT || i == POISON) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Immune*/
                    } else if (i == DARK){
                        table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;			
				
            case ICE :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == STEEL || i == FIRE || i == WATER || i == ICE) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == FLYING || i == GROUND || i == GRASS || i == DRAGON) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;

            case DRAGON :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == DRAGON) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                    	table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;				
                        
            case DARK :
                for (int i = 0; i < PokemonType.values().length; i++) {
                    /*NotSoEffective*/
                    if (i == FIGHT || i == DARK || i == STEEL) {
                        table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
                    /*Supereffective*/
                    } else if (i == GHOST || i == PSYCHIC) {
                        table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
                    /*Ok*/
                    } else {
                        table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
                    }
                }
                break;
            }
        }
    }
}
