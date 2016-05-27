package view.fight;

import java.awt.Color;

import javax.swing.JProgressBar;
/**
 * This class creates and paints the 2 health bars placed in {@link FightScreen}.
 * 
 */
public class HealthBar extends JProgressBar {
	private static final long serialVersionUID = -1998418607308529873L;
	private static final double LOW = 0.25;
	private static final Color LOW_COLOR = Color.RED;
	private static final double LESS_HALF = 0.5;
	private static final Color LESS_HALF_COLOR = Color.YELLOW;
	private static final Color MORE_HALF_COLOR = Color.GREEN;
	private double ratio;
	/**
	 * It creates the health bar that is placed in {@link FightScreen}.
	 * 
	 * @param maxHP The maximum amount of health points the pokémon has.
	 * @param currentHP The current amount of health points the pokémon has.
	 * @throws IllegalArgumentException if the health is lower than 0.
	 */
	public HealthBar(final int maxHP, final int currentHP) {
		super(0, maxHP);
		if (maxHP <= 0 || currentHP < 0) {
			throw new IllegalArgumentException("Health values cannot be lower than 0");
		}
		this.setValue(currentHP);
		this.ratio = (double) currentHP / maxHP;
		this.setColorRatio(ratio);
	}
	
	@Override
	public void setValue(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Health values cannot be lower than 0");
		}
		super.setValue(n);
		this.ratio = (double) n / this.getMaximum();
		setColorRatio(this.ratio);
	}
	/**
	 * It paints the bars depending on the percentage of health the pokémon has.
	 * 
	 * @param r Current health points.
	 */
	private void setColorRatio(final double r) {
		if (r <= LOW) {
			this.setForeground(LOW_COLOR);
		} else if (r <= LESS_HALF) {
			this.setForeground(LESS_HALF_COLOR);
		} else {
			this.setForeground(MORE_HALF_COLOR);
		}
	}
}
