package model.fight;

import model.pokemon.Pokemon;
import model.trainer.Trainer;

/**
 * A static class for create fight instance. It implement the pattern of a simple factory.
 */
public final class StaticSimpleFightFactory {

    /**
     * Create a new Fight against a wild pokemon.
     * 
     * @param wildPkm   The wild {@link model.pokemon.Pokemon} that appear.
     * @return          A new {@link FightVsWildPkm}.
     */
    public static Fight createFight(final Pokemon wildPkm) {
        return new FightVsWildPkm(wildPkm);
    }

    /**
     * Create a new Fight against a trainer.
     * 
     * @param trainer   The {@link model.trainer.Trainer} challenged.
     * @return          A new {@link FightVsTrainer}.
     */
    public static Fight createFight(final Trainer trainer) {
        return new FightVsTrainer(trainer);
    }
}
