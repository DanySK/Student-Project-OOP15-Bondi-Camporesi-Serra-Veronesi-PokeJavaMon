package controller.parameters;

/**
 * This enum contains all the parameters that are saved on the xml save file
 */
public enum XMLParameters {
    MONEY("MONEY"), 
    TIME("TIME"), 
    PLACE("PLACE"), 
    POSITION("POSITION"),
    X("X"), 
    Y("Y"), 
    TEAM("TEAM"), 
    LV("LV"), 
    HP("HP"), 
    EXP("EXP"), 
    NMOVES("NMOVES"),
    MOVES_ID("M"), 
    TRAINERS("TRAINERS"), 
    BAG("BAG"), 
    POTIONS("POTIONS"),
    BOOSTS("BOOSTS"), 
    BALLS("BALLS"), 
    BOX("BOX"), 
    RETURNPOSITION("RETURNPOSITION"),
    RETX("RETX"), 
    RETY("RETY"), 
    TITLE("SAVEFOLDER"), 
    NAME("NAME"), 
    ENCOUNTER("ENCOUNTERS"), 
    BADGES("BADGES");
    
    private final String value;
    
    private XMLParameters(final String s) {
        this.value = s;
    }
    
    /**
     * @return the name of the selected parameter
     */
    public String getName() {
        return this.value;
    }
}