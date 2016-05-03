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
				/*Ok*/
				for (int i = 0; i < PokemonType.values().length; i++) {
					table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
				}
				break;
			
			case FIRE :
				for (int i = 0; i < PokemonType.values().length; i++) {
					/*NotSoEffective*/
					if (i == PokemonType.FIRE.ordinal() || i == PokemonType.WATER.ordinal() || i == PokemonType.DRAGON.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.GRASS.ordinal()) {
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
					if (i == PokemonType.GRASS.ordinal() || i == PokemonType.WATER.ordinal() || i == PokemonType.ELECTR.ordinal() || i == PokemonType.DRAGON.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.FIRE.ordinal() || i == PokemonType.GROUND.ordinal() ) {
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
					if (i == PokemonType.GRASS.ordinal() || i == PokemonType.FIRE.ordinal() || i == PokemonType.POISON.ordinal() || i == PokemonType.FLYING.ordinal() || i == PokemonType.DRAGON.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.WATER.ordinal() || i == PokemonType.GROUND.ordinal()) {
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
					if (i == PokemonType.GRASS.ordinal() || i == PokemonType.ELECTR.ordinal() || i == PokemonType.DRAGON.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.WATER.ordinal() || i == PokemonType.FLYING.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
					
					/*Ok*/
					} else {
						table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
					}
				}
				break;
				
			case GROUND :
				for (int i = 0; i < PokemonType.values().length; i++) {
					/*NotSoEffective*/
					if (i == PokemonType.GRASS.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.FIRE.ordinal() || i == PokemonType.ELECTR.ordinal() || i == PokemonType.POISON.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
					
					/*Immune*/
					} else if (i == PokemonType.FLYING.ordinal()){
						table[p.ordinal()][i] = WeaknessType.IMMUNE.getMultiplier();
					
					/*Ok*/
					} else {
						table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
					}
				}
				break;
				
			case POISON :
				for (int i = 0; i < PokemonType.values().length; i++) {
					/*NotSoEffective*/
					if (i == PokemonType.GROUND.ordinal() || i == PokemonType.POISON.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.GRASS.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
					
					/*Ok*/
					} else {
						table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
					}
				}
				break;
				
			case FLYING :
				for (int i = 0; i < PokemonType.values().length; i++) {
					/*NotSoEffective*/
					if (i == PokemonType.ELECTR.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.NOT_SO_EFFECTIVE.getMultiplier();
					
					/*Supereffective*/
					} else if (i == PokemonType.GRASS.ordinal()) {
						table[p.ordinal()][i] = WeaknessType.SUPEREFFECTIVE.getMultiplier();
					
					/*Ok*/
					} else {
						table[p.ordinal()][i] = WeaknessType.OK.getMultiplier();
					}
				}
				break;
				
			case DRAGON :
                            for (int i = 0; i < PokemonType.values().length; i++) {
                                /*Supereffective*/
                                if (i == PokemonType.DRAGON.ordinal()) {
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
