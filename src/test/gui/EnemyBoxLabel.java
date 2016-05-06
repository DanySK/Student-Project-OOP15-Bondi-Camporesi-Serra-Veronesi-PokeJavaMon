package test.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class EnemyBoxLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4601281471781410011L;
	private Image img;
	
	public EnemyBoxLabel() {
		try {
			this.img = ImageIO.read(EnemyBoxLabel.class.getResource("/gui/AllyInBattle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(30, 30, img.getWidth(this), img.getHeight(this));
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
