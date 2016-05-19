package model.trainer;

import model.player.PlayerImpl;
import model.squad.Squad;

/**
 * Class that extends Trainer class, that gives a badge when defeated.
 * An instance of GymLeader is created through StaticTrainerFactory.createGymLeader()
 * @see Trainer
 * @see StaticTrainerFactory
 */
public class GymLeader extends Trainer {
	
    public static final String TYPE_GYM_LEADER = "GYM_LEADER";
	
    private int badge;
	
    protected GymLeader(String name, int x, int y, Direction d, boolean isDefeated, Squad squad, String initMessage,
                        String wonMessage, String lostMessage, int money, int trainerID, int badge) {
        super(name, x, y, d, isDefeated, squad, initMessage, wonMessage, lostMessage, money, trainerID);
        this.badge = badge;
    }

    /**
     * 
     * @return the number of the badge
     */
    public int getBadge() {
        return this.badge;
    }
	
    /**
     * Sets the number of the badge
     * @param badge
     */
    public void setBadge(final int badge) {
        this.badge = badge;
    }
	
    /**
     * Checks if {@link Player}'s current badges are enough to fight against 
     * @return true if your badges are equal or higher than the badges required to fight against this gymLeader
     */
    public boolean canFight() {
        return PlayerImpl.getPlayer().getLastBadge() >= this.badge;
    }	
}
