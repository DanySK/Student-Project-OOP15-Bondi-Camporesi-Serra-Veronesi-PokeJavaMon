package view.methods;

import model.fight.Effectiveness;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;

public interface InFightMessagesInterface {
    
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage);
    
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove,
                                     boolean isMyPokemonDead);
    
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead);
    
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);
}
