package model.fight;

/**
 * Enumeration used for communicate moves effectiveness.
 */
public enum Effectiveness {
    SUPEREFFECTIVE("Super effective!!"), 
    LESSEFFECTIVE("Not very effective..."), 
    IMMUNE("Enemy is immune!"),
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
