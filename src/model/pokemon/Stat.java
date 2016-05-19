package model.pokemon;

/**
 * All different stats that varies with levels.
 * Does not include currentHP which is obtained through {@link Pokemon#getCurrentHP()}
 * There will be implemented ATK_SPEC and DEF_SPEC
 */
public enum Stat {
    ATK, DEF, SPD, MAX_HP, EXP, LVL;
}
