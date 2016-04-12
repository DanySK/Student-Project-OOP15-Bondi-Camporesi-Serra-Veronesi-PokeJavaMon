package model.box;

public class PokemonNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -163106485399215182L;
    
    public PokemonNotFoundException() {
        super("Pokemon not found");
    }
}
