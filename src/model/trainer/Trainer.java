package model.trainer;

import model.map.AbstractCharacter;
import model.pokemon.PokemonDB;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;
import model.squad.SquadImpl;
import model.utilities.Pair;

public class Trainer extends AbstractCharacter {
    
    private final Squad squad;
    private final TrainerDB id;
    private boolean isDefeated;
    
    public Trainer(final int x, final int y, final Direction d, final TrainerDB id) {
        super(x,y,d);
        
        PokemonInBattle[] tmpSquad = new PokemonInBattle[id.getSquad().size()];
        int counter = 0;
        for (final Pair<PokemonDB, Integer> p : id.getSquad()) {
            tmpSquad[counter] = new PokemonInBattle(p.getX(), p.getY());
            counter++;
        }
        
        squad = new SquadImpl(tmpSquad);
        this.id = id;
        isDefeated = false;
        
    }
    
    public TrainerDB getTrainerDB() {
        return this.id;
    }
    
    public Squad getSquad() {
        return this.squad;
    }
    
    
    public String getInitialMessage() {
        return this.id.getInitialMessage();
    }
    
    public String getDefeatedMessage() {
        return this.id.getDefeatedMessage();
    }
    
    public String getWinningMessage() {
        return this.id.getWinningMessage();
    }
    
    public void defeat() {
        for (final PokemonInBattle p : this.squad.getPokemonList()) {
            if (p.getCurrentHP() > 0) {
                throw new IllegalStateException("There's still at least 1 pokemon alive");
            }
        }
        this.isDefeated = true;
    }
    
    public boolean isDefeated() {
        return this.isDefeated;
    }
}
