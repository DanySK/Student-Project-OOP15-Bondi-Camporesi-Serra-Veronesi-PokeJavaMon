package model.trainer;

import model.map.Drawable.Direction;

public final class StaticTrainerFactory {
	
	private StaticTrainerFactory() {}
	
	public static Trainer createTrainer(final String trainerName, final boolean isDefeated) {
		for (final TrainerDB t : TrainerDB.values()) {
			if (trainerName.toUpperCase().equals(t.name())) {
				final Trainer retTrainer = new Trainer(0,0, Direction.SOUTH, t); //TODO get X,Y from map
				retTrainer.isDefeated = isDefeated;
				return retTrainer;
			}
		}
		throw new IllegalArgumentException("Trainer name not found");
	}

	public static Trainer createTrainer(final String trainerName, final Direction d, final int x, final int y) {
		for (final TrainerDB t : TrainerDB.values()) {
			if (trainerName.toUpperCase().equals(t.name())) {
				return new Trainer(x, y, d, t);
			}
		}
		throw new IllegalArgumentException("Trainer Name not found");
	}
	
	public static Trainer createTrainer(final String trainerName, final int x, final int y) {
		for (final TrainerDB t : TrainerDB.values()) {
			if (trainerName.toUpperCase().equals(t.name())) {
				return new Trainer(x, y, Direction.SOUTH, t);
			}
		}
		throw new IllegalArgumentException("Trainer Name not found");
	}
}
