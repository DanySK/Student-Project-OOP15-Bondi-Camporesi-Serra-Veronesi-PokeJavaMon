package view.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import view.View;
import view.windows.MyFrame;
/**
 * HowToPlayClass
 * 
 * @author Daniel Veronesi
 */
public class HowToPlay extends JWindow implements MyFrame {

    private static final long serialVersionUID = -8605835642465055987L;
	/**
	 * mainPanel
	 */
    private JPanel mainPanel;
	/**
	 * HowToPlay
	 */
    public HowToPlay() {
    	this.mainPanel = new HowToPlayPanel();
    }
	/**
	 * HowToPlayPanelClass
	 */
    public class HowToPlayPanel extends JPanel {
    	/**
    	 * serialVersionUID
    	 */
		private static final long serialVersionUID = 2304161775966955920L;
		/**
		 * moveUp
		 */
		private final JLabel moveUp;
		/**
		 * up
		 */
		private final JLabel up;
		/**
		 * moveLeft
		 */
		private final JLabel moveLeft;
		/**
		 * left
		 */
		private final JLabel left;
		/**
		 * moveDown
		 */
		private final JLabel moveDown;
		/**
		 * down
		 */
		private final JLabel down;
		/**
		 * moveRight
		 */
		private final JLabel moveRight;
		/**
		 * right
		 */
		private final JLabel right;
		/**
		 * interact
		 */
		private final JLabel interact;
		/**
		 * enter
		 */
		private final JLabel enter;
		/**
		 * menu
		 */
		private final JLabel menu;
		/**
		 * esc
		 */
		private final JLabel esc;
		/**
		 * back
		 */
    	private final JButton back;
    	/**
    	 * bgImage
    	 */
    	private Image bgImage = null;
    	/**
    	 * Create the HowToPlayPanel.
    	 */
    	public HowToPlayPanel() {
    		setMinimumSize(new Dimension(600, 400));
    		setPreferredSize(new Dimension(600, 400));
    		setBorder(new LineBorder(Color.GRAY, 4, true));
    		
    		this.moveUp = this.createLabel("MOVE UP");
    		this.up = this.createLabel("W - UP");
    		this.moveLeft = this.createLabel("MOVE LEFT");
    		this.left = this.createLabel("A - LEFT");
    		this.moveDown = this.createLabel("MOVE DOWN");
    		this.down = this.createLabel("S - DOWN");
    		this.moveRight = this.createLabel("MOVE RIGHT");
    		this.right = this.createLabel("D - RIGHT");
    		this.interact = this.createLabel("INTERACT");
    		this.enter = this.createLabel("ENTER");
    		this.menu = this.createLabel("MENU");
    		this.esc = this.createLabel("ESC");

    		this.back = new JButton("BACK");
    		this.back.setBorderPainted(false);
    		this.back.setFocusable(false);
    		this.back.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                View.getView().disposeCurrent();
	                View.getView().removeCurrent();
	                View.getView().resumeCurrent();
	            }
    		});
    		final GroupLayout groupLayout = new GroupLayout(this);
    		groupLayout.setHorizontalGroup(
    			groupLayout.createParallelGroup(Alignment.LEADING)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(94)
    					.addComponent(moveUp)
    					.addGap(276)
    					.addComponent(up))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(85)
    					.addComponent(moveLeft)
    					.addGap(262)
    					.addComponent(left))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(79)
    					.addComponent(moveDown)
    					.addGap(251)
    					.addComponent(down))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(79)
    					.addComponent(moveRight)
    					.addGap(249)
    					.addComponent(right))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(89)
    					.addComponent(interact)
    					.addGap(272)
    					.addComponent(enter))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(107)
    					.addComponent(menu)
    					.addGap(302)
    					.addComponent(esc))
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(276)
    					.addComponent(back))
    		);
    		groupLayout.setVerticalGroup(
    			groupLayout.createParallelGroup(Alignment.LEADING)
    				.addGroup(groupLayout.createSequentialGroup()
    					.addGap(30)
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    						.addComponent(moveUp)
    						.addComponent(up))
    					.addGap(35)
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    						.addComponent(moveLeft)
    						.addComponent(left))
    					.addGap(35)
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    						.addComponent(moveDown)
    						.addComponent(down))
    					.addGap(35)
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    							.addComponent(moveRight)
    						.addComponent(right))
    					.addGap(35)
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    						.addComponent(interact)
    						.addComponent(enter))
    					.addGap(35)
    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    						.addComponent(menu)
    						.addComponent(esc))
    					.addGap(35)
    					.addComponent(back))
    		);
    		this.setLayout(groupLayout);
    		
		    final MediaTracker mt = new MediaTracker(this);
		    try {
				bgImage = ImageIO.read(FirstMenu.class.getResourceAsStream("/gui/HowToPlay.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    mt.addImage(bgImage, 0);
		    try {
		      mt.waitForAll();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }

    	}
    	/**
    	 * createLabel
    	 */
    	private JLabel createLabel(final String text) {
    		final JLabel l = new JLabel(text);
    		l.setFont(new Font("Verdana", Font.BOLD, 14));
    		return l;
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
        this.mainPanel.setPreferredSize(new Dimension(600, 400));
        this.setContentPane(this.mainPanel);
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