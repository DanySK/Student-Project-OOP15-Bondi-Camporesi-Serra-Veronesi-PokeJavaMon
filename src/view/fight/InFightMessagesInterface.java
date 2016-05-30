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
	 * @param myMove The move used by the ally pokémon.
	 * @param myMoveEffectiveness The effectiveness of the move used by the ally pokémon against the enemy.
	 * @param enemyMove The move used by the enemy pokémon.
	 * @param enemyMoveEffectiveness The effectiveness of the move used by the enemy against the ally pokémon.
	 * @param myMoveFirst Checks whether or not the ally pokémon is faster than the enemy.
	 * @param lastPokemonKills Checks if the pokémon killed is the last of that trainer.
	 * @param nextEnemyPokemon The pokémon the trainer is going to put in battle.
	 * @param optionalMessage The message is displaed during the fight.
	 * @param moveToLearn The move the pokémon might learn when he levels up.
	 */
    public void resolveMove(Move myMove, Effectiveness myMoveEffectiveness, Move enemyMove,
                            Effectiveness enemyMoveEffectiveness, boolean myMoveFirst,
                            boolean lastPokemonKills, Pokemon nextEnemyPokemon,
                            String optionalMessage, final Move moveToLearn);
	/**
	 * @param myPokemon The pokémon is going to replace the one is in battle.
	 * @param enemyMove The move used by the enemy pokémon.
	 * @param eff The effectiveness of the move used against the pokémon.
	 * @param isMyPokemonDead Checks whether or not the ally pokémon is dead at the end of the turn.
	 */
    public void resolveChangePokemon(Pokemon myPokemon, Move enemyMove, Effectiveness eff,
                                     boolean isMyPokemonDead);
	/**
	 * @param item It is the items used by the player.
	 * @param pk The pokémon the trainer uses an item on.
	 * @param enemyMove The move used by the enemy pokémon.
	 * @param eff The effectiveness of the move used against the pokémon.
	 * @param isMyPokemonDead Checks whether or not the ally pokémon is dead at the end of the turn.
	 */
    public void resolveUseItem(Item item, Pokemon pk, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead);
	/**
	 * @param success Checks if the trainer can run away from the fight.
	 * @param enemyMove The move used by the enemy pokémon.
	 * @param eff The effectiveness of the move used against the pokémon.
	 * @param isMyPokemonDead Checks whether or not the ally pokémon is dead at the end of the turn.
	 */
    public void resolveRun(boolean success, Move enemyMove, Effectiveness eff, boolean isMyPokemonDead);
}
