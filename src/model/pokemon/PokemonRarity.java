package model.pokemon;

/**
 * Enumeration that lists all the different rarities of {@link Pokemon}
 * and their capture rates. The lower the value the lower the chance of capture
 */
public enum PokemonRarity {

    COMMON(255), 
    UNCOMMON(150), 
    RARE(90), 
    VERY_RARE(60),
    STARTER(45), 
    LEGENDARY(10), 
    UNFINDABLE(0); 
    
    private final int coeff;
    
    private PokemonRarity(final int coeff) {
        this.coeff = coeff;
    }
    
    /**
     * Gives a value used to calculate the capture rate of a {@link Pokemon} with such rarity 
     * @return Capture value
     */
    public int getCoeff() {
        return this.coeff;
    }
}
