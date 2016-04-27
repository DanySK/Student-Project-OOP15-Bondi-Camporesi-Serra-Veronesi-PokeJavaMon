package view;

import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;

public interface MethodsToImplement {
    
    public void resolveMove(Move myMove, String myMoveMessage, Move enemyMove,
                            String enemyMoveMessage, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage);
    
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove,
                                     boolean isMyPokemonDead);
    
    public void resolveUseItem(Item item, Move enemyMove, boolean isMyPokemonDead);
    
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);
}
