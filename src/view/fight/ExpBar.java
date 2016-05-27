package view.fight;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JProgressBar;
/**
 * This class creates and paints the experience bar placed in {@link FightScreen}.
 * 
 */
public class ExpBar extends JProgressBar {
	/**
	 * It specifies the color of the experience needed to raise a pokémon level.
	 */
	private static final String BG_COLOR = "#C4C4C4"; //LIGHT_GRAY
	/**
	 * It specifies the color of the experience gained since the pokémon levelled up.
	 */
	private static final String FG_COLOR = "#006CFF"; //BLUE
	private static final long serialVersionUID = 1767168964690866299L;
	/**
	 * It creates the experience bar that is placed in {@link FightScreen}.
	 * @param lvlEXP It is the total experience needed to level up.
	 * @param currentEXP It is the experience gained in the level.
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
