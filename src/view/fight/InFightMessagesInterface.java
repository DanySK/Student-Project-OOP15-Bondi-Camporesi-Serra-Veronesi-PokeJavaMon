package view.fight;

import model.fight.Effectiveness;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
/**
 * InFightMessagesInterface
 * 
 * @author Daniel Veronesi
 */
public interface InFightMessagesInterface {
	/**
	 * resolveMove
	 * 
	 * @param
	 */
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage, final Move moveToLearn);
	/**
	 * resolveChangePokemon
	 */
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove,
                                     boolean isMyPokemonDead);
	/**
	 * resolveUseItem
	 */
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead);
	/**
	 * resolveRun
	 */
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);
}
