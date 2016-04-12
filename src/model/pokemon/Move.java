package model.pokemon;

public enum Move {

    NULLMOVE(PokemonType.NONE, Stat.HP, false, 0),
    
	
    POUND(PokemonType.NORMAL, Stat.HP, true, 30),
    CUT(PokemonType.NORMAL, Stat.HP, true, 50),
    SLASH(PokemonType.NORMAL, Stat.HP, true, 70),
    STRENGTH(PokemonType.NORMAL, Stat.HP, true, 80),
    HYPERBEAM(PokemonType.NORMAL, Stat.HP, true, 150),
    
    HOWL(PokemonType.NORMAL, Stat.ATK, true, 15),
    HARDEN(PokemonType.NORMAL, Stat.DEF, false, 15),
    TAIL_WHIP(PokemonType.NORMAL, Stat.DEF, true, 15),
    SCREECH(PokemonType.NORMAL, Stat.DEF, true, 30),
    SWORD_DANCE(PokemonType.NORMAL, Stat.ATK, false, 30),
    SCARY_FACE(PokemonType.NORMAL, Stat.SPD, true, 30),
    AGILITY(PokemonType.NORMAL, Stat.SPD, false, 30),
    
    EMBER(PokemonType.FIRE, Stat.HP, true, 40),
    FLAMETHROWER(PokemonType.FIRE, Stat.HP, true, 90),
    FIRE_BLAST(PokemonType.FIRE, Stat.HP, true, 110),
    BLAST_BURN(PokemonType.FIRE, Stat.HP, true, 150),
    
    WATERGUN(PokemonType.WATER, Stat.HP, true, 40),
    SURF(PokemonType.WATER, Stat.HP, true, 95),
    HYDROPUMP(PokemonType.WATER, Stat.HP, true, 110),
    HYDROCANNON(PokemonType.WATER, Stat.HP, true, 150),

    VINE_WHIP(PokemonType.GRASS, Stat.HP, true, 45),
    LEAFBLADE(PokemonType.GRASS ,Stat.HP, true, 90),
    SOLARBEAM(PokemonType.GRASS, Stat.HP, true, 120),
    FRENZY_PLANT(PokemonType.GRASS, Stat.HP, true, 150),
    
    THUNDERSHOCK(PokemonType.ELECTR, Stat.HP, true, 40),
    THUNDERBOLT(PokemonType.ELECTR, Stat.HP, true, 90),
    THUNDER(PokemonType.ELECTR, Stat.HP, true, 110),
    
    MUDSLAP(PokemonType.GROUND, Stat.HP, true, 20),
    MUDBOMB(PokemonType.GROUND, Stat.HP, true, 65),
    EARTHQUAKE(PokemonType.GROUND, Stat.HP, true, 100),
    
    ACID(PokemonType.POISON, Stat.HP, true, 40),
    SLUDGEBOMB(PokemonType.POISON, Stat.HP, true, 90),
    BELCH(PokemonType.POISON, Stat.HP, true, 120),
    
    PECK(PokemonType.FLYING, Stat.HP, true, 35),
    FLY(PokemonType.FLYING, Stat.HP, true, 90),
    HURRICANE(PokemonType.FLYING, Stat.HP, true, 110),
    
    OUTRAGE(PokemonType.DRAGON, Stat.HP, true, 120),
    DRACO_METEOR(PokemonType.DRAGON, Stat.HP, true, 130),
    
    ;
    
    private PokemonType type;
    private Stat stat;
    private boolean isOnEnemy;
    private int value;
    
    
    private Move(final PokemonType type, final Stat stat, final boolean isOnEnemy, final int value) {
        this.type = type;
        this.stat = stat;
        this.isOnEnemy = isOnEnemy;
        this.value = value;
    }

    public Stat getStat() {
        return stat;
    }

    public boolean isOnEnemy() {
        return isOnEnemy;
    }

    public int getValue() {
        return value;
    }
    
    public PokemonType getType() {
    	return this.type;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
    
    
    
}
