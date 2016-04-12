package model.squad;

public class OnlyOnePokemonInSquadException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1775402590171934554L;

    public OnlyOnePokemonInSquadException() {
        super("You cannot deposit your last pokemon");
    }
}
