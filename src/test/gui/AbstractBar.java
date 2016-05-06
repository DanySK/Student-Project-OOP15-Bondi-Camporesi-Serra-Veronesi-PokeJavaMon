package test.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JProgressBar;

public class AbstractBar extends JProgressBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5461882713601025333L;
	private boolean isIncreasingFromLeft = true;
	
	protected AbstractBar(final int value, final int maxValue, final boolean isIncreasingFromLeft ) {
		this.setMaximum(maxValue);
		if (value > maxValue) {
			this.setValue(maxValue);
		} else if (value < 0) {
			this.setValue(0);
		} else {
			this.setValue(value);
		}
		this.setMinimum(0);
		this.isIncreasingFromLeft = isIncreasingFromLeft;
	}
	
	protected AbstractBar(final int value, final int maxValue, final boolean isIncreasingFromLeft, final Color c) {
		this(value, maxValue, isIncreasingFromLeft);
		this.setForeground(c);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	  Graphics2D g2d = (Graphics2D) g;
	  if(!this.isIncreasingFromLeft) {
		  g2d.scale(-1, 1); //Flips over y-axis
		  g2d.translate(-getWidth(), 0); //Moves back to old position.
	  }
	  super.paintComponent(g2d);
	}
	
}
