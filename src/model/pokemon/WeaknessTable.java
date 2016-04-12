package model.pokemon;

public class WeaknessTable {
	
	public static enum WeaknessType {
		
		IMMUNE(0), OK(1), SUPEREFFECTIVE(2), NOT_SO_EFFECTIVE(0.5);
		
		private double multiplier;
		
		private WeaknessType(final double multiplier) {
			this.multiplier = multiplier;
		}
		
		public double getMultiplier() {
			return this.multiplier;
		}
	}
	
	/*
	 * Rows are attacking Types, Columns are defending types
	 */
	private double[][] table;
	private static WeaknessTable singleton = null;
	
	private WeaknessTable() {
		table = new double[PokemonType.values().length][PokemonType.values().length];
		initWeaknesses();
	}
	
	/*Singleton*/
	public static WeaknessTable getWeaknessTable() {
		if (singleton == null) {
			singleton = new WeaknessTable();
		}
		return singleton;
	}
	
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
