package test.gui;

import java.awt.Color;

public class HealthBar extends AbstractBar {
	
	private static final double LESS_HALF_HP = 0.5;
	private static final Color LESS_HALF_HP_COLOR = Color.YELLOW;
	
	private static final double CRITIC_HP = 0.25;
	private static final Color CRITIC_HP_COLOR = Color.RED;
	
	private static final Color MORE_HALF_HP_COLOR = Color.GREEN;

	protected HealthBar(final int value, final int maxValue, final boolean isEnemy) {
		super(value, maxValue, true);
		this.setHPColor();
		if (isEnemy) {
			this.setStringPainted(false);
		}
		this.setBackground(Color.BLACK);
		this.setBorderPainted(false);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501828465737646653L;

	@Override
	public void setValue(final int n) {
		super.setValue(n);
		this.setHPColor();
		this.repaint();
	}
	
	
	private void setHPColor() {
		final double ratio = ((Integer) this.getValue()).doubleValue() / ((Integer) this.getMaximum()).doubleValue();
		if (ratio < LESS_HALF_HP) {
			if (ratio < CRITIC_HP) {
				this.setForeground(CRITIC_HP_COLOR);
			} else {
				this.setForeground(LESS_HALF_HP_COLOR);
			}
		} else {
			this.setForeground(MORE_HALF_HP_COLOR);
		}
	}
}
