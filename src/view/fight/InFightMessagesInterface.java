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
	 * 
	 * @param myMove
	 * @param myMoveEffectiveness
	 * @param enemyMove
	 * @param enemyMoveEffectiveness
	 * @param myMoveFirst
	 * @param lastPokemonKills
	 * @param nextEnemyPokemon
	 * @param optionalMessage
	 * @param moveToLearn
	 */
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage, final Move moveToLearn);
	/**
	 * 
	 * @param myPokemon
	 * @param enemyMove
	 * @param isMyPokemonDead
	 */
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove,
                                     boolean isMyPokemonDead);
	/**
	 * 
	 * @param item
	 * @param pk
	 * @param enemyMove
	 * @param isMyPokemonDead
	 */
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, boolean isMyPokemonDead);
	/**
	 * 
	 * @param success
	 * @param enemyMove
	 * @param isMyPokemonDead
	 */
    public void resolveRun(boolean success, Move enemyMove, boolean isMyPokemonDead);
}
