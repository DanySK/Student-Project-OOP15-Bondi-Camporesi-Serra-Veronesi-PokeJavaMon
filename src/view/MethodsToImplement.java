package view;

import java.util.List;

import model.fight.Effectiveness;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;

public interface MethodsToImplement {
    
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage);
    
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove,
                                     boolean isMyPokemonDead);
    
    public void resolveUseItem(Item item, Move enemyMove, boolean isMyPokemonDead);
    
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);
    
    public void evolve(List<Pokemon> list);
}
