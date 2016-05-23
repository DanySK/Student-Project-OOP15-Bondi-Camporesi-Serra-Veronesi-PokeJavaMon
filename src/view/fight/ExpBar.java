package view.fight;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JProgressBar;
/**
 * ExpBar
 */
public class ExpBar extends JProgressBar {
	/**
	 * BG_COLOR
	 */
	private static final String BG_COLOR = "#C4C4C4"; //LIGHT_GRAY
	/**
	 * FG_COLOR
	 */
	private static final String FG_COLOR = "#006CFF"; //BLUE
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1767168964690866299L;
	/**
	 * ExpBar
	 */
	public ExpBar(final int lvlEXP, final int currentEXP) {
		super(0, lvlEXP);
		this.setBackground(Color.decode(BG_COLOR));
		this.setForeground(Color.decode(FG_COLOR));
		this.setValue(currentEXP);
	}
	
	@Override
	protected void paintComponent(final Graphics g) {
		  final Graphics2D g2d = (Graphics2D) g;
		  g2d.scale(-1, 1);
		  g2d.translate(-getWidth(), 0);
		  super.paintComponent(g2d);
		}
	
}
