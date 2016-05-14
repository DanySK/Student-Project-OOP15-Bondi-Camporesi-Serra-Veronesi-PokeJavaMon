package model.pokemon;

/**
 * A "database" in form of Enumeration that contains all the possible Moves
 * Each entry has a {@link PokemonType}, a {@link Stat} modifies (excluding LVL and EXP), 
 * a boolean value which indicates whether or not the Move affects the enemy or the {@link Pokemon} itself,
 * and as well as the actual value of the Move.
 * @see Pokemon
 * @see WeaknessTable
 */
public enum Move {

    NULLMOVE(PokemonType.NONE, Stat.HP, MoveType.PHYSICAL, false, 0),
    
	
//    POUND(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 30),
//    CUT(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 50),
//    SLASH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
//    STRENGTH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 80),
//    HYPERBEAM(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 150),
//    
//    HOWL(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, true, 15),
//    HARDEN(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, false, 15),
//    TAIL_WHIP(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 15),
//    SCREECH(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 30),
//    SWORD_DANCE(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, false, 30),
//    SCARY_FACE(PokemonType.NORMAL, Stat.SPD, MoveType.STATUS, true, 30),
//    AGILITY(PokemonType.NORMAL, Stat.SPD, MoveType.STATUS, false, 30),
//    
//    EMBER(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 40),
//    FLAMETHROWER(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 90),
//    FIRE_BLAST(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 110),
//    BLAST_BURN(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 150),
//    V_CREATE(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 170),

//    WATERGUN(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 40),
//    SURF(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 95),
//    HYDROPUMP(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 110),
//    HYDROCANNON(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 150),
////TODO
//    VINE_WHIP(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 45),
//    LEAFBLADE(PokemonType.GRASS ,Stat.HP, MoveType.PHYSICAL, true, 90),
//    SOLARBEAM(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 120),
//    FRENZY_PLANT(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 150),
//    
//    THUNDERSHOCK(PokemonType.ELECTR, Stat.HP, MoveType.PHYSICAL, true, 40),
//    THUNDERBOLT(PokemonType.ELECTR, Stat.HP, MoveType.PHYSICAL, true, 90),
//    THUNDER(PokemonType.ELECTR, Stat.HP, MoveType.PHYSICAL, true, 110),
//    
//    MUDSLAP(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 20),
//    MUDBOMB(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 65),
//    EARTHQUAKE(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 100),
//    
//    ACID(PokemonType.POISON, Stat.HP, MoveType.PHYSICAL, true, 40),
//    SLUDGEBOMB(PokemonType.POISON, Stat.HP, MoveType.PHYSICAL, true, 90),
//    BELCH(PokemonType.POISON, Stat.HP, MoveType.PHYSICAL, true, 120),
//    
//    PECK(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 35),
//    FLY(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 90),
//    HURRICANE(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 110),
//    DRAGON_ASCENT(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 170),
//    
//    OUTRAGE(PokemonType.DRAGON, Stat.HP, MoveType.PHYSICAL, true, 120),
//    DRACO_METEOR(PokemonType.DRAGON, Stat.HP, MoveType.PHYSICAL, true, 130),

POUND(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 40),
KARATE_CHOP(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 50),
DOUBLE_SLAP(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 50),
COMET_PUNCH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 60),
MEGA_PUNCH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 80),
PAY_DAY(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 40),
FIRE_PUNCH(PokemonType.FIRE, Stat.HP, MoveType.PHYSICAL, true, 75),
ICE_PUNCH(PokemonType.ICE, Stat.HP, MoveType.PHYSICAL, true, 75),
THUNDER_PUNCH(PokemonType.ELECTR, Stat.HP, MoveType.PHYSICAL, true, 75),
SCRATCH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 40),
VICE_GRIP(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 55),
RAZOR_WIND(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 80),
SWORDS_DANCE(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, false, 2),
CUT(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 50),
GUST(PokemonType.FLYING, Stat.HP, MoveType.SPECIAL, true, 40),
WING_ATTACK(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 60),
FLY(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 90),
BIND(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 45),
SLAM(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 80),
VINE_WHIP(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 45),
STOMP(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 65),
DOUBLE_KICK(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 60),
MEGA_KICK(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 120),
JUMP_KICK(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 100),
ROLLING_KICK(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 60),
HEADBUTT(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
HORN_ATTACK(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 65),
FURY_ATTACK(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 45),
TACKLE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 50),
BODY_SLAM(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 85),
WRAP(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 35),
TAKE_DOWN(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 90),
THRASH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 100),
DOUBLE_EDGE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 120),
TAIL_WHIP(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 1),
POISON_STING(PokemonType.POISON, Stat.HP, MoveType.PHYSICAL, true, 15),
TWINEEDLE(PokemonType.BUG, Stat.HP, MoveType.PHYSICAL, true, 50),
PIN_MISSILE(PokemonType.BUG, Stat.HP, MoveType.PHYSICAL, true, 50),
LEER(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 1),
BITE(PokemonType.DARK, Stat.HP, MoveType.PHYSICAL, true, 60),
GROWL(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, true, 1),
ACID(PokemonType.POISON, Stat.HP, MoveType.SPECIAL, true, 40),
EMBER(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 40),
FLAMETHROWER(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 90),
WATER_GUN(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 40),
HYDRO_PUMP(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 110),
SURF(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 90),
ICE_BEAM(PokemonType.ICE, Stat.HP, MoveType.SPECIAL, true, 90),
BLIZZARD(PokemonType.ICE, Stat.HP, MoveType.SPECIAL, true, 110),
PSYBEAM(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 65),
BUBBLE_BEAM(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 65),
AURORA_BEAM(PokemonType.ICE, Stat.HP, MoveType.SPECIAL, true, 65),
HYPER_BEAM(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 150),
PECK(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 35),
DRILL_PECK(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 80),
SUBMISSION(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 80),
STRENGTH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 80),
ABSORB(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 30),
MEGA_DRAIN(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 50),
GROWTH(PokemonType.NORMAL, Stat.ATK/*_SPEC*/, MoveType.STATUS, false, 1),
RAZOR_LEAF(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 55),
SOLAR_BEAM(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 130),
PETAL_DANCE(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 110),
STRING_SHOT(PokemonType.BUG, Stat.SPD, MoveType.STATUS, true, 1),
DRAGON_RAGE(PokemonType.DRAGON, Stat.HP, MoveType.SPECIAL, true, 40),
FIRE_SPIN(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 35),
THUNDER_SHOCK(PokemonType.ELECTR, Stat.HP, MoveType.SPECIAL, true, 40),
THUNDERBOLT(PokemonType.ELECTR, Stat.HP, MoveType.SPECIAL, true, 90),
THUNDER(PokemonType.ELECTR, Stat.HP, MoveType.SPECIAL, true, 110),
ROCK_THROW(PokemonType.ROCK, Stat.HP, MoveType.PHYSICAL, true, 50),
EARTHQUAKE(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 100),
DIG(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 80),
CONFUSION(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 50),
PSYCHIC(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 90),
MEDITATE(PokemonType.PSYCHIC, Stat.ATK, MoveType.STATUS, false, 1),
AGILITY(PokemonType.PSYCHIC, Stat.SPD, MoveType.STATUS, false, 2),
QUICK_ATTACK(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 40),
RAGE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 30),
SCREECH(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, true, 2),
HARDEN(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, false, 1),
WITHDRAW(PokemonType.WATER, Stat.DEF, MoveType.STATUS, false, 1),
DEFENSE_CURL(PokemonType.NORMAL, Stat.DEF, MoveType.STATUS, false, 1),
BARRIER(PokemonType.PSYCHIC, Stat.DEF, MoveType.STATUS, false, 2),
LIGHT_SCREEN(PokemonType.PSYCHIC, Stat.DEF/*_SPEC*/, MoveType.STATUS, false, 2),
EGG_BOMB(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 100),
LICK(PokemonType.GHOST, Stat.HP, MoveType.PHYSICAL, true, 30),
SMOG(PokemonType.POISON, Stat.HP, MoveType.SPECIAL, true, 30),
SLUDGE(PokemonType.POISON, Stat.HP, MoveType.SPECIAL, true, 65),
BONE_CLUB(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 65),
FIRE_BLAST(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 110),
WATERFALL(PokemonType.WATER, Stat.HP, MoveType.PHYSICAL, true, 80),
CLAMP(PokemonType.WATER, Stat.HP, MoveType.PHYSICAL, true, 35),
SWIFT(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 60),
SKULL_BASH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 130),
SPIKE_CANNON(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 45),
CONSTRICT(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 30),
AMNESIA(PokemonType.PSYCHIC, Stat.DEF/*_SPEC*/, MoveType.STATUS, false, 2),
HIGH_JUMP_KICK(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 130),
DREAM_EATER(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 100),
BARRAGE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 45),
LEECH_LIFE(PokemonType.BUG, Stat.HP, MoveType.PHYSICAL, true, 25),
SKY_ATTACK(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 140),
BUBBLE(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 40),
DIZZY_PUNCH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
SPLASH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 0),
ACID_ARMOR(PokemonType.POISON, Stat.DEF, MoveType.STATUS, false, 2),
CRABHAMMER(PokemonType.WATER, Stat.HP, MoveType.PHYSICAL, true, 100),
EXPLOSION(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 250),
FURY_SWIPES(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 55),
BONEMERANG(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 70),
ROCK_SLIDE(PokemonType.ROCK, Stat.HP, MoveType.PHYSICAL, true, 75),
HYPER_FANG(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 80),
SHARPEN(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, false, 1),
TRI_ATTACK(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 80),
SLASH(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
STRUGGLE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 50),
TRIPLE_KICK(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 30),
THIEF(PokemonType.DARK, Stat.HP, MoveType.PHYSICAL, true, 60),
FLAME_WHEEL(PokemonType.FIRE, Stat.HP, MoveType.PHYSICAL, true, 60),
SNORE(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 50),
AEROBLAST(PokemonType.FLYING, Stat.HP, MoveType.SPECIAL, true, 100),
COTTON_SPORE(PokemonType.GRASS, Stat.SPD, MoveType.STATUS, true, 2),
POWDER_SNOW(PokemonType.ICE, Stat.HP, MoveType.SPECIAL, true, 40),
MACH_PUNCH(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 40),
SCARY_FACE(PokemonType.NORMAL, Stat.SPD, MoveType.STATUS, true, 2),
FEINT_ATTACK(PokemonType.DARK, Stat.HP, MoveType.PHYSICAL, true, 60),
SLUDGE_BOMB(PokemonType.POISON, Stat.HP, MoveType.SPECIAL, true, 90),
MUD_SLAP(PokemonType.GROUND, Stat.HP, MoveType.SPECIAL, true, 30),
OCTAZOOKA(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 65),
ZAP_CANNON(PokemonType.ELECTR, Stat.HP, MoveType.SPECIAL, true, 120),
ICY_WIND(PokemonType.ICE, Stat.HP, MoveType.SPECIAL, true, 55),
BONE_RUSH(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 50),
OUTRAGE(PokemonType.DRAGON, Stat.HP, MoveType.PHYSICAL, true, 120),
GIGA_DRAIN(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 80),
ROLLOUT(PokemonType.ROCK, Stat.HP, MoveType.PHYSICAL, true, 40),
FALSE_SWIPE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 40),
SPARK(PokemonType.ELECTR, Stat.HP, MoveType.PHYSICAL, true, 65),
FURY_CUTTER(PokemonType.BUG, Stat.HP, MoveType.PHYSICAL, true, 40),
STEEL_WING(PokemonType.STEEL, Stat.HP, MoveType.PHYSICAL, true, 75),
SACRED_FIRE(PokemonType.FIRE, Stat.HP, MoveType.PHYSICAL, true, 100),
DYNAMIC_PUNCH(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 100),
MEGAHORN(PokemonType.BUG, Stat.HP, MoveType.PHYSICAL, true, 120),
DRAGON_BREATH(PokemonType.DRAGON, Stat.HP, MoveType.SPECIAL, true, 60),
PURSUIT(PokemonType.DARK, Stat.HP, MoveType.PHYSICAL, true, 40),
RAPID_SPIN(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 20),
IRON_TAIL(PokemonType.STEEL, Stat.HP, MoveType.PHYSICAL, true, 100),
METAL_CLAW(PokemonType.STEEL, Stat.HP, MoveType.PHYSICAL, true, 55),
VITAL_THROW(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 70),
HIDDEN_POWER(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 60),
CROSS_CHOP(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 100),
TWISTER(PokemonType.DRAGON, Stat.HP, MoveType.SPECIAL, true, 40),
CRUNCH(PokemonType.DARK, Stat.HP, MoveType.PHYSICAL, true, 80),
EXTREME_SPEED(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 80),
ANCIENT_POWER(PokemonType.ROCK, Stat.HP, MoveType.SPECIAL, true, 70),
SHADOW_BALL(PokemonType.GHOST, Stat.HP, MoveType.SPECIAL, true, 80),
FUTURE_SIGHT(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 120),
ROCK_SMASH(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 40),
WHIRLPOOL(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 35),
FAKE_OUT(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 40),
UPROAR(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 80),
HEAT_WAVE(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 95),
FACADE(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
FOCUS_PUNCH(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 150),
SMELLING_SALTS(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
SUPERPOWER(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 120),
REVENGE(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 60),
BRICK_BREAK(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 75),
KNOCK_OFF(PokemonType.DARK, Stat.HP, MoveType.PHYSICAL, true, 65),
SECRET_POWER(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 70),
DIVE(PokemonType.WATER, Stat.HP, MoveType.PHYSICAL, true, 80),
ARM_THRUST(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 45),
TAIL_GLOW(PokemonType.BUG, Stat.ATK/*_SPEC*/, MoveType.STATUS, false, 2),
LUSTER_PURGE(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 70),
MIST_BALL(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 70),
FEATHER_DANCE(PokemonType.FLYING, Stat.ATK, MoveType.STATUS, false, 2),
BLAZE_KICK(PokemonType.FIRE, Stat.HP, MoveType.PHYSICAL, true, 85),
ICE_BALL(PokemonType.ICE, Stat.HP, MoveType.PHYSICAL, true, 40),
NEEDLE_ARM(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 60),
HYPER_VOICE(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 90),
POISON_FANG(PokemonType.POISON, Stat.HP, MoveType.PHYSICAL, true, 50),
CRUSH_CLAW(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 75),
BLAST_BURN(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 150),
HYDRO_CANNON(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 150),
METEOR_MASH(PokemonType.STEEL, Stat.HP, MoveType.PHYSICAL, true, 90),
ASTONISH(PokemonType.GHOST, Stat.HP, MoveType.PHYSICAL, true, 30),
WEATHER_BALL(PokemonType.NORMAL, Stat.HP, MoveType.SPECIAL, true, 50),
FAKE_TEARS(PokemonType.DARK, Stat.DEF/*_SPEC*/, MoveType.STATUS, true, 2),
AIR_CUTTER(PokemonType.FLYING, Stat.HP, MoveType.SPECIAL, true, 60),
OVERHEAT(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 130),
ROCK_TOMB(PokemonType.ROCK, Stat.HP, MoveType.PHYSICAL, true, 60),
SILVER_WIND(PokemonType.BUG, Stat.HP, MoveType.SPECIAL, true, 60),
METAL_SOUND(PokemonType.STEEL, Stat.DEF/*_SPEC*/, MoveType.STATUS, true, 2),
TICKLE(PokemonType.NORMAL, Stat.ATK/*, Stat.DEF*/, MoveType.STATUS, true, 1),
COSMIC_POWER(PokemonType.PSYCHIC, Stat.ATK/*_SPEC, Stat.DEF_SPEC*/, MoveType.STATUS, false, 1),
SIGNAL_BEAM(PokemonType.BUG, Stat.HP, MoveType.SPECIAL, true, 75),
SHADOW_PUNCH(PokemonType.GHOST, Stat.HP, MoveType.PHYSICAL, true, 60),
EXTRASENSORY(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 80),
SKY_UPPERCUT(PokemonType.FIGHT, Stat.HP, MoveType.PHYSICAL, true, 85),
SAND_TOMB(PokemonType.GROUND, Stat.HP, MoveType.PHYSICAL, true, 35),
MUDDY_WATER(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 90),
BULLET_SEED(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 45),
AERIAL_ACE(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 60),
ICICLE_SPEAR(PokemonType.ICE, Stat.HP, MoveType.PHYSICAL, true, 25),
IRON_DEFENSE(PokemonType.STEEL, Stat.DEF, MoveType.STATUS, false, 2),
HOWL(PokemonType.NORMAL, Stat.ATK, MoveType.STATUS, true, 1),
DRAGON_CLAW(PokemonType.DRAGON, Stat.HP, MoveType.PHYSICAL, true, 80),
FRENZY_PLANT(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 150),
BULK_UP(PokemonType.FIGHT, Stat.ATK/*, Stat.DEF*/, MoveType.STATUS, false, 1),
BOUNCE(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 85),
MUD_SHOT(PokemonType.GROUND, Stat.HP, MoveType.SPECIAL, true, 55),
POISON_TAIL(PokemonType.POISON, Stat.HP, MoveType.PHYSICAL, true, 50),
COVET(PokemonType.NORMAL, Stat.HP, MoveType.PHYSICAL, true, 60),
VOLT_TACKLE(PokemonType.ELECTR, Stat.HP, MoveType.PHYSICAL, true, 150),
MAGICAL_LEAF(PokemonType.GRASS, Stat.HP, MoveType.SPECIAL, true, 60),
CALM_MIND(PokemonType.PSYCHIC, Stat.ATK/*_SPEC, Stat.DEF_SPEC*/, MoveType.STATUS, false, 1),
LEAF_BLADE(PokemonType.GRASS, Stat.HP, MoveType.PHYSICAL, true, 90),
DRAGON_DANCE(PokemonType.DRAGON, Stat.ATK/*, Stat.SPD*/, MoveType.STATUS, false, 1),
ROCK_BLAST(PokemonType.ROCK, Stat.HP, MoveType.PHYSICAL, true, 55),
SHOCK_WAVE(PokemonType.ELECTR, Stat.HP, MoveType.SPECIAL, true, 60),
WATER_PULSE(PokemonType.WATER, Stat.HP, MoveType.SPECIAL, true, 60),
DOOM_DESIRE(PokemonType.STEEL, Stat.HP, MoveType.SPECIAL, true, 140),
PSYCHO_BOOST(PokemonType.PSYCHIC, Stat.HP, MoveType.SPECIAL, true, 140),
V_CREATE(PokemonType.FIRE, Stat.HP, MoveType.SPECIAL, true, 170),
DRAGON_ASCENT(PokemonType.FLYING, Stat.HP, MoveType.PHYSICAL, true, 170),

    
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
        this.stat = stat;
        this.isOnEnemy = isOnEnemy;
        this.value = value;
        this.moveType = moveType;
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
    
    public MoveType getMoveType() {
    	return this.moveType;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
    
    
    
}

