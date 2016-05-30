package view.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import view.View;
import view.windows.MyFrame;
/**
 * This {@link JWindow} handles the credits of the game.
 * 
 */
public class Credits extends JWindow implements MyFrame {
    private static final long serialVersionUID = 313390316550029L;
    private final JPanel panel;
	/**
	 * It creates a border in the panel.
	 */
    public Credits() {
    	this.panel = new CreditBackground();
    	this.panel.setBorder(new LineBorder(Color.GRAY, 4));
    }
    /**
     * This {@link JPanel} contains the background image
     * the name of the authors of the game
     * the button the come back in {@link FirstMenu}
     */
    private class CreditBackground extends JPanel {
		private static final long serialVersionUID = -7371702206859570082L;
		private Image bgImage = null;
    	private final JButton back;
    	/**
    	 * Catches an exception if it fails to load the image for the background
    	 */
    	public CreditBackground() {
		    final MediaTracker mt = new MediaTracker(this);
		    try {
				bgImage = ImageIO.read(FirstMenu.class.getResourceAsStream("/gui/Credits.png"));
			} catch (IOException e1) {
				System.out.println("FAILED TO SET BACKGROUND");
			}
		    mt.addImage(bgImage, 0);
		    try {
		      mt.waitForAll();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		    this.back = new JButton("BACK");
		    this.back.setBorderPainted(false);
	        this.back.addActionListener(new ActionListener() {      
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                View.getView().disposeCurrent();
	                View.getView().removeCurrent();
	                View.getView().resumeCurrent();          
	            }
	        });
		    this.add(back);
    	}
    	
    	@Override
    	protected void paintComponent(final Graphics g) {
    		super.paintComponent(g);
    		g.drawImage(bgImage, 1, 1, 600, 400, null);	
    	}
    }

	@Override
	public void showFrame() {
        this.setMinimumSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.setContentPane(this.panel);
        this.setVisible(true);
	}

	@Override
	public void disposeFrame() {
		this.dispose();
	}

	@Override
	public void hideFrame() {
		this.setVisible(false);
	}

	@Override
	public void resumeFrame() {
		this.setVisible(true);
	}
}
