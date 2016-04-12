package controller.parameters;

public enum XMLParameters {

    MONEY("MONEY"), TIME("TIME"), PLACE("PLACE"), POSITION("POSITION"),
    X("X"), Y("Y"), TEAM("TEAM"), HP("HP"), EXP("EXP"), NMOVES("NMOVES"),
    MOVES_ID("M"), TRAINERS("TRAINERS"), BAG("BAG"), STRUM("STRUM"),
    BASE("BASE"), BALLS("BALLS"), BOX("BOX"), RETURNPOSITION("RETURNPOSITION"),
    RETX("RETX"), RETY("RETY"), TITLE("SAVE");
    
    private XMLParameters(String s) {
        this.value = s;
    }
    
    private String value;
    
    public String getName() {
        return this.value;
    }
}