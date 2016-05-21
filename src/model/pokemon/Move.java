package model.pokemon;

/**
 * A "database" in form of Enumeration that contains all the possible Moves
 * Each entry has a {@link PokemonType}, a {@link Stat} modifies (excluding {@link Stat#EXP} and {@link Stat#LVL}), 
 * a boolean value which indicates whether or not the Move affects the enemy or the {@link Pokemon} itself,
 * and as well as the actual value of the Move.
 * @see Pokemon
 * @see WeaknessTable
 */
public enum Move {

    NULLMOVE(PokemonType.NONE, Stat.MAX_HP, MoveType.PHYSICAL, false, 0),

    ABSORB(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 30),
    ACID(PokemonType.POISON, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    ACID_ARMOR(PokemonType.POISON, Stat.DEF, MoveType.STATUS, false, 2),
    AERIAL_ACE(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    AEROBLAST(PokemonType.FLYING, Stat.MAX_HP, MoveType.SPECIAL, true, 100),
    AGILITY(PokemonType.PSYCHIC, Stat.SPD, MoveType.STATUS, false, 2),
    AIR_CUTTER(PokemonType.FLYING, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    AMNESIA(PokemonType.PSYCHIC, Stat.DEF/*_SPEC*/, MoveType.STATUS, false, 2),
    ANCIENT_POWER(PokemonType.ROCK, Stat.MAX_HP, MoveType.SPECIAL, true, 70),
    ARM_THRUST(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    ASTONISH(PokemonType.GHOST, Stat.MAX_HP, MoveType.PHYSICAL, true, 30),
    AURORA_BEAM(PokemonType.ICE, Stat.MAX_HP, MoveType.SPECIAL, true, 65),
    BARRAGE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    BARRIER(PokemonType.PSYCHIC, Stat.DEF, MoveType.STATUS, false, 2),
    BIND(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    BITE(PokemonType.DARK, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    BLAST_BURN(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 150),
    BLAZE_KICK(PokemonType.FIRE, Stat.MAX_HP, MoveType.PHYSICAL, true, 85),
    BLIZZARD(PokemonType.ICE, Stat.MAX_HP, MoveType.SPECIAL, true, 110),
    BODY_SLAM(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 85),
    BONE_CLUB(PokemonType.GROUND, Stat.MAX_HP, MoveType.PHYSICAL, true, 65),
    BONE_RUSH(PokemonType.GROUND, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    BONEMERANG(PokemonType.GROUND, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    BOUNCE(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 85),
    BRICK_BREAK(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    BUBBLE(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    BUBBLE_BEAM(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 65),
    BULK_UP(PokemonType.FIGHT, Stat.ATK/*, Stat.DEF*/, MoveType.STATUS, false, 1),
    BULLET_SEED(PokemonType.GRASS, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    CALM_MIND(PokemonType.PSYCHIC, Stat.ATK/*_SPEC, Stat.DEF_SPEC*/, MoveType.STATUS, false, 1),
    CLAMP(PokemonType.WATER, Stat.MAX_HP, MoveType.PHYSICAL, true, 35),
    COMET_PUNCH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    CONFUSION(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 50),
    CONSTRICT(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 30),
    COSMIC_POWER(PokemonType.PSYCHIC, Stat.ATK/*_SPEC, Stat.DEF_SPEC*/, MoveType.STATUS, false, 1),
    COTTON_SPORE(PokemonType.GRASS, Stat.SPD, MoveType.STATUS, true, 2),
    COVET(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    CRABHAMMER(PokemonType.WATER, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    CROSS_CHOP(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    CRUNCH(PokemonType.DARK, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    CRUSH_CLAW(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    CUT(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    DEFENSE_CURL(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, false, 1),
    DIG(PokemonType.GROUND, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    DIVE(PokemonType.WATER, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    DIZZY_PUNCH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    DOOM_DESIRE(PokemonType.STEEL, Stat.MAX_HP, MoveType.SPECIAL, true, 140),
    DOUBLE_EDGE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 120),
    DOUBLE_KICK(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    DOUBLE_SLAP(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    DRAGON_ASCENT(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 170),
    DRAGON_BREATH(PokemonType.DRAGON, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    DRAGON_CLAW(PokemonType.DRAGON, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    DRAGON_DANCE(PokemonType.DRAGON, Stat.SPD/*, Stat.ATK*/, MoveType.STATUS, false, 1),
    DRAGON_RAGE(PokemonType.DRAGON, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    DREAM_EATER(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 100),
    DRILL_PECK(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    DYNAMIC_PUNCH(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    EARTHQUAKE(PokemonType.GROUND, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    EGG_BOMB(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    EMBER(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    EXPLOSION(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 250),
    EXTRASENSORY(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 70),
    EXTREME_SPEED(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    FACADE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    FAKE_OUT(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    FAKE_TEARS(PokemonType.DARK, Stat.DEF/*_SPEC*/, MoveType.STATUS, true, 2),
    FALSE_SWIPE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    FEATHER_DANCE(PokemonType.FLYING, Stat.ATK, MoveType.STATUS, false, 2),
    FEINT_ATTACK(PokemonType.DARK, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    FIRE_PUNCH(PokemonType.FIRE, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    FIRE_BLAST(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 110),
    FIRE_SPIN(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 35),
    FLAME_WHEEL(PokemonType.FIRE, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    FLAMETHROWER(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    FLY(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    FOCUS_PUNCH(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 150),
    FRENZY_PLANT(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 150),
    FURY_ATTACK(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    FURY_CUTTER(PokemonType.BUG, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    FURY_SWIPES(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 55),
    FUTURE_SIGHT(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 120),
    GIGA_DRAIN(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 80),
    GROWL(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, true, 1),
    GROWTH(PokemonType.NORMAL, Stat.ATK/*_SPEC*/, MoveType.STATUS, false, 1),
    GUST(PokemonType.FLYING, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    HARDEN(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, false, 1),
    HEADBUTT(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    HEAT_WAVE(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 95),
    HIDDEN_POWER(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    HIGH_JUMP_KICK(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 130),
    HORN_ATTACK(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 65),
    HOWL(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, true, 1),
    HYDRO_CANNON(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 150),
    HYDRO_PUMP(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 110),
    HYPER_BEAM(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 150),
    HYPER_FANG(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    HYPER_VOICE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    ICE_BALL(PokemonType.ICE, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    ICE_BEAM(PokemonType.ICE, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    ICE_PUNCH(PokemonType.ICE, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    ICICLE_SPEAR(PokemonType.ICE, Stat.MAX_HP, MoveType.PHYSICAL, true, 25),
    ICY_WIND(PokemonType.ICE, Stat.MAX_HP, MoveType.SPECIAL, true, 55),
    IRON_DEFENSE(PokemonType.STEEL, Stat.DEF, MoveType.STATUS, false, 2),
    IRON_TAIL(PokemonType.STEEL, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    JUMP_KICK(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    KARATE_CHOP(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    KNOCK_OFF(PokemonType.DARK, Stat.MAX_HP, MoveType.PHYSICAL, true, 65),
    LEAF_BLADE(PokemonType.GRASS, Stat.MAX_HP, MoveType.PHYSICAL, true, 90),
    LEECH_LIFE(PokemonType.BUG, Stat.MAX_HP, MoveType.PHYSICAL, true, 25),
    LEER(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 1),
    LICK(PokemonType.GHOST, Stat.MAX_HP, MoveType.PHYSICAL, true, 30),
    LIGHT_SCREEN(PokemonType.PSYCHIC, Stat.DEF/*_SPEC*/, MoveType.STATUS, false, 2),
    LUSTER_PURGE(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 70),
    MACH_PUNCH(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    MAGICAL_LEAF(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    MEDITATE(PokemonType.PSYCHIC, Stat.ATK, MoveType.STATUS, false, 1),
    MEGA_DRAIN(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 50),
    MEGA_KICK(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 120),
    MEGA_PUNCH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    MEGAHORN(PokemonType.BUG, Stat.MAX_HP, MoveType.PHYSICAL, true, 120),
    METAL_CLAW(PokemonType.STEEL, Stat.MAX_HP, MoveType.PHYSICAL, true, 55),
    METAL_SOUND(PokemonType.STEEL, Stat.DEF/*_SPEC*/, MoveType.STATUS, true, 2),
    METEOR_MASH(PokemonType.STEEL, Stat.MAX_HP, MoveType.PHYSICAL, true, 90),
    MIST_BALL(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 70),
    MUD_SHOT(PokemonType.GROUND, Stat.MAX_HP, MoveType.SPECIAL, true, 55),
    MUD_SLAP(PokemonType.GROUND, Stat.MAX_HP, MoveType.SPECIAL, true, 30),
    MUDDY_WATER(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    NEEDLE_ARM(PokemonType.GRASS, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    OCTAZOOKA(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 65),
    OUTRAGE(PokemonType.DRAGON, Stat.MAX_HP, MoveType.PHYSICAL, true, 120),
    OVERHEAT(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 130),
    PAY_DAY(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    PECK(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 35),
    PETAL_DANCE(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 110),
    PIN_MISSILE(PokemonType.BUG, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    POISON_FANG(PokemonType.POISON, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    POISON_STING(PokemonType.POISON, Stat.MAX_HP, MoveType.PHYSICAL, true, 15),
    POISON_TAIL(PokemonType.POISON, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    PSYBEAM(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 75),
    PSYCHIC(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    PSYCHO_BOOST(PokemonType.PSYCHIC, Stat.MAX_HP, MoveType.SPECIAL, true, 140),
    POUND(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    POWDER_SNOW(PokemonType.ICE, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    PURSUIT(PokemonType.DARK, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    QUICK_ATTACK(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    RAGE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 30),
    RAPID_SPIN(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 20),
    RAZOR_LEAF(PokemonType.GRASS, Stat.MAX_HP, MoveType.PHYSICAL, true, 55),
    RAZOR_WIND(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 80),
    REVENGE(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    ROCK_BLAST(PokemonType.ROCK, Stat.MAX_HP, MoveType.PHYSICAL, true, 55),
    ROCK_SLIDE(PokemonType.ROCK, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    ROCK_SMASH(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    ROCK_THROW(PokemonType.ROCK, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    ROCK_TOMB(PokemonType.ROCK, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    ROLLING_KICK(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    ROLLOUT(PokemonType.ROCK, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    SACRED_FIRE(PokemonType.FIRE, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    SAND_TOMB(PokemonType.GROUND, Stat.MAX_HP, MoveType.PHYSICAL, true, 35),
    SCARY_FACE(PokemonType.NORMAL, Stat.SPD, MoveType.STATUS, true, 2),
    SCRATCH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 40),
    SCREECH(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 2),
    SECRET_POWER(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    SHADOW_BALL(PokemonType.GHOST, Stat.MAX_HP, MoveType.SPECIAL, true, 80),
    SHADOW_PUNCH(PokemonType.GHOST, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    SHARPEN(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, false, 1),
    SHOCK_WAVE(PokemonType.ELECTR, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    SIGNAL_BEAM(PokemonType.BUG, Stat.MAX_HP, MoveType.SPECIAL, true, 75),
    SILVER_WIND(PokemonType.BUG, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    SKULL_BASH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 130),
    SKY_ATTACK(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 140),
    SKY_UPPERCUT(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 85),
    SLAM(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    SLASH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    SLUDGE(PokemonType.POISON, Stat.MAX_HP, MoveType.SPECIAL, true, 65),
    SLUDGE_BOMB(PokemonType.POISON, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    SMELLING_SALTS(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    SMOG(PokemonType.POISON, Stat.MAX_HP, MoveType.SPECIAL, true, 30),
    SNORE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 50),
    SOLAR_BEAM(PokemonType.GRASS, Stat.MAX_HP, MoveType.SPECIAL, true, 130),
    SPARK(PokemonType.ELECTR, Stat.MAX_HP, MoveType.PHYSICAL, true, 65),
    SPIKE_CANNON(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    SPLASH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 0),
    STEEL_WING(PokemonType.STEEL, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    STOMP(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 65),
    STRENGTH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    STRING_SHOT(PokemonType.BUG, Stat.SPD, MoveType.STATUS, true, 1),
    STRUGGLE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    SUBMISSION(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    SUPERPOWER(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 120),
    SURF(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    SWIFT(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    SWORDS_DANCE(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, false, 2),
    TACKLE(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    TAIL_GLOW(PokemonType.BUG, Stat.ATK/*_SPEC*/, MoveType.STATUS, false, 2),
    TAIL_WHIP(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 1),
    TAKE_DOWN(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 90),
    THIEF(PokemonType.DARK, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    THRASH(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 100),
    THUNDER(PokemonType.ELECTR, Stat.MAX_HP, MoveType.SPECIAL, true, 110),
    THUNDER_PUNCH(PokemonType.ELECTR, Stat.MAX_HP, MoveType.PHYSICAL, true, 75),
    THUNDER_SHOCK(PokemonType.ELECTR, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    THUNDERBOLT(PokemonType.ELECTR, Stat.MAX_HP, MoveType.SPECIAL, true, 90),
    TICKLE(PokemonType.NORMAL, Stat.ATK/*, Stat.DEF*/, MoveType.STATUS, true, 1),
    TRI_ATTACK(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 80),
    TRIPLE_KICK(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 30),
    TWINEEDLE(PokemonType.BUG, Stat.MAX_HP, MoveType.PHYSICAL, true, 50),
    TWISTER(PokemonType.DRAGON, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    UPROAR(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 80),
    V_CREATE(PokemonType.FIRE, Stat.MAX_HP, MoveType.SPECIAL, true, 170),
    VICE_GRIP(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 55),
    VINE_WHIP(PokemonType.GRASS, Stat.MAX_HP, MoveType.PHYSICAL, true, 45),
    VITAL_THROW(PokemonType.FIGHT, Stat.MAX_HP, MoveType.PHYSICAL, true, 70),
    VOLT_TACKLE(PokemonType.ELECTR, Stat.MAX_HP, MoveType.PHYSICAL, true, 150),
    WATER_GUN(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 40),
    WATER_PULSE(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 60),
    WATERFALL(PokemonType.WATER, Stat.MAX_HP, MoveType.PHYSICAL, true, 80),
    WEATHER_BALL(PokemonType.NORMAL, Stat.MAX_HP, MoveType.SPECIAL, true, 50),
    WHIRLPOOL(PokemonType.WATER, Stat.MAX_HP, MoveType.SPECIAL, true, 35),
    WING_ATTACK(PokemonType.FLYING, Stat.MAX_HP, MoveType.PHYSICAL, true, 60),
    WITHDRAW(PokemonType.WATER, Stat.DEF, MoveType.STATUS, false, 1),
    WRAP(PokemonType.NORMAL, Stat.MAX_HP, MoveType.PHYSICAL, true, 35),
    ZAP_CANNON(PokemonType.ELECTR, Stat.MAX_HP, MoveType.SPECIAL, true, 120),
    ;
	
	
    public static enum MoveType {
    	PHYSICAL, SPECIAL, STATUS, 
    }

    
    private final PokemonType type;
    private final Stat stat;
    private final boolean isOnEnemy;
    private final int value;
    private final MoveType moveType;
    
    
    private Move(final PokemonType type, final Stat stat, final MoveType moveType, final boolean isOnEnemy, final int value) {
        this.type = type;
        if (stat == Stat.EXP || stat == Stat.LVL) {
        	throw new IllegalArgumentException("Cannot have EXP or LVL as Stat");
        }
        this.stat = stat;
        this.isOnEnemy = isOnEnemy;
        this.value = value;
        this.moveType = moveType;
    }

    /**
     * 
     * @return The {@link Stat} target of the {@link Move}
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * 
     * @return true if the {@link Move} is aimed at the enemy, false if it is aimed to the attacker itself
     */
    public boolean isOnEnemy() {
        return isOnEnemy;
    }

    /**
     * A method to obtain the base value of the {@link Move}, it's a damaging one (if it's {@link Stat#MAX_HP})
     * it returns the base damage, otherwise, returns the number of stages
     * @return the integer 
     */
    public int getValue() {
        return value;
    }
    
    /**
     * 
     * @return the {@link PokemonType}
     */
    public PokemonType getType() {
    	return this.type;
    }
    
    /**
     * Not implemented but a {@link Move} can be {@link MoveType#PHYSICAL} and it will use
     * attacker {@link Stat#ATK} and defender {@link Stat#DEF}.
     * If it's {@link MoveType#SPECIAL} it will involve instead attacker {@link Stat}.ATK_SPEC
     * and defender {@link Stat}.DEF_SPEC (To be implemented) 
     * @return the {@link MoveType} of the {@link Move}
     */
    public MoveType getMoveType() {
    	return this.moveType;
    }
    
    @Override
    public String toString() {
        return this.name();
    }  
}

