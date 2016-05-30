package view.fight;

import model.fight.Effectiveness;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
/**
 * InFightMessagesInterface
 * 
 */
public interface InFightMessagesInterface {
	/**
	 * @param myMove The move used by the ally pok�mon.
	 * @param myMoveEffectiveness The effectiveness of the move used by the ally pok�mon against the enemy.
	 * @param enemyMove The move used by the enemy pok�mon.
	 * @param enemyMoveEffectiveness The effectiveness of the move used by the enemy against the ally pok�mon.
	 * @param myMoveFirst Checks whether or not the ally pok�mon is faster than the enemy.
	 * @param lastPokemonKills Checks if the pok�mon killed is the last of that trainer.
	 * @param nextEnemyPokemon The pok�mon the trainer is going to put in battle.
	 * @param optionalMessage The message is displaed during the fight.
	 * @param moveToLearn The move the pok�mon might learn when he levels up.
	 */
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage, final Move moveToLearn);
	/**
	 * @param myPokemon The pok�mon is going to replace the one is in battle.
	 * @param enemyMove The move used by the enemy pok�mon.
	 * @param eff The effectiveness of the move used against the pok�mon.
	 * @param isMyPokemonDead Checks whether or not the ally pok�mon is dead at the end of the turn.
	 */
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, Effectiveness eff,
                                     boolean isMyPokemonDead);
	/**
	 * @param item It is the items used by the player.
	 * @param pk The pok�mon the trainer uses an item on.
	 * @param enemyMove The move used by the enemy pok�mon.
	 * @param eff The effectiveness of the move used against the pok�mon.
	 * @param isMyPokemonDead Checks whether or not the ally pok�mon is dead at the end of the turn.
	 */
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead);
	/**
	 * @param success Checks if the trainer can run away from the fight.
	 * @param enemyMove The move used by the enemy pok�mon.
	 * @param eff The effectiveness of the move used against the pok�mon.
	 * @param isMyPokemonDead Checks whether or not the ally pok�mon is dead at the end of the turn.
	 */
    public void resolveRun(boolean success, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead);
}
