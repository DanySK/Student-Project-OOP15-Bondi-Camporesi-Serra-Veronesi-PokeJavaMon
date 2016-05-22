package model.fight;

/**
 * Enumeration used for communicate moves effectiveness.
 * 
 * @see BasicFight#isEffective(model.pokemon.PokemonInBattle, model.pokemon.PokemonInBattle, model.pokemon.Move)
 */
public enum Effectiveness {
    SUPEREFFECTIVE("Super effective!!"), 
    LESSEFFECTIVE("Not very effective..."), 
    IMMUNE("He is immune!"),
    NONE("But nothing happens..."),
    NORMAL("That was effective"), 
    CANNOTINCREASE("His stat cannot increase"), 
    CANNOTDECREASE("His stat cannot decrease");
    
    private String msg;
    
    private Effectiveness(final String s) {
        this.msg = s;
    }
    
    public String getMessage() {
        return this.msg;
    }
}
