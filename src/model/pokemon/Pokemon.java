package model.pokemon;

import java.util.List;
import java.util.Map;

public interface Pokemon {

    public PokemonDB getPokemon();
    public int getCurrentHP();
    public int getNecessaryExp();
    public int getStat(Stat s);
    public Map<Stat, Integer> getAllStats();
    public List<Move> getCurrentMoves();
    
    public boolean levelUp();
    public void evolveUp();
    public void learnMove(Move oldMove, Move newMove);
    public void heal(int value);
    public void evolve();
    public String toString();
}
