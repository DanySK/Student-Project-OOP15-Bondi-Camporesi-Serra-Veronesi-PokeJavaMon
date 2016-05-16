package model.pokemon;

public enum PokemonRarity {

    COMMON(255), 
    UNCOMMON(150), 
    RARE(90), 
    VERY_RARE(60),
    STARTER(45), 
    LEGENDARY(3), 
    UNFINDABLE(0); 
    
    private final int coeff;
    
    private PokemonRarity(final int coeff) {
        this.coeff = coeff;
    }
    
    public int getCoeff() {
        return this.coeff;
    }
}
