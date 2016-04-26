package model.trainer;

import model.player.PlayerImpl;
import model.squad.Squad;

public class GymLeader extends Trainer {
	
	public static final String TYPE_GYM_LEADER = "GYM_LEADER";
	
	private int badge;
	
	protected GymLeader(String name, int x, int y, Direction d, boolean isDefeated, Squad squad, String initMessage,
			String wonMessage, String lostMessage, int money, int trainerID, int badge) {
		super(name, x, y, d, isDefeated, squad, initMessage, wonMessage, lostMessage, money, trainerID);
		this.badge = badge;
	}

	public int getBadge() {
		return this.badge;
	}
	
	public void setBadge(final int badge) {
		this.badge = badge;
	}
	
	public boolean canFight() {
		return PlayerImpl.getPlayer().getLastBadge() >= this.badge;
	}
	
	
}
