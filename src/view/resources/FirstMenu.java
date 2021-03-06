package view.resources;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import controller.parameters.Img;
import controller.parameters.State;
import view.View;
import view.windows.MyFrame;

/**
 * This {@link JFrame} handles the main menu of the game.
 * The user can choose between starting a new game or loading a save.
 * There are also 2 buttons that opens {@link HowToPlay} and {@link Credits}
 */

public class FirstMenu extends JFrame implements MyFrame {
	private static final long serialVersionUID = -3171512540755919384L;
	private ContentPanel contentPane;
	private JButton newGame;
	private JButton continueGame;
	private JButton howToPlay;
	private JButton credits;
	private JLabel welcomeLabel;
	private SpringLayout sl_contentPane;
	/**
	 * This {@link JPanel} is used to get the background image.
	 * It catches an exception if it fails to load the picture.
	 */
	private class ContentPanel extends JPanel {
		
		private static final long serialVersionUID = 3361495155189049313L;
		private Image bgimage = null;
		ContentPanel() {
		    final MediaTracker mt = new MediaTracker(this);
		    try {
				bgimage = ImageIO.read(FirstMenu.class.getResourceAsStream("/gui/1stMenuBG.png"));
			} catch (IOException e1) {
			        System.out.println("FAILED TO SET BACKGROUND");
			}
		    mt.addImage(bgimage, 0);
		    try {
		      mt.waitForAll();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		  }

		  @Override
		  protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(bgimage, 1, 1, 450, 300, null);
		  }
	}

	@Override
	public void showFrame() {
	    try {
		    this.setIconImage(Toolkit.getDefaultToolkit().getImage(Img.PALLA.getAbsolutePath()));
		} catch (SecurityException e) {
		    this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(Img.PALLA.getResourcePath()).getPath()));
		}
		this.setTitle("PokeJavaMon");
		this.setBounds(new Rectangle(0, 0, 300, 200));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new ContentPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		this.sl_contentPane = new SpringLayout();
		this.contentPane.setLayout(sl_contentPane);		
		this.newGame = new JButton("New Game");
		this.newGame.setIcon(new ImageIcon(FirstMenu.class.getResource("/gui/Pokeball.png")));
		this.sl_contentPane.putConstraint(SpringLayout.EAST, this.newGame, 140, SpringLayout.WEST, contentPane);
		this.newGame.setFocusable(false);
		this.newGame.setOpaque(false);
		this.newGame.setBorderPainted(false);
        this.newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
                MainController.getController().updateStatus(State.SECOND_MENU);
                MainController.getController().getViewController().secondMenu();
            }
        });
		this.newGame.setFont(new Font("Verdana", Font.PLAIN, 14));
		this.sl_contentPane.putConstraint(SpringLayout.NORTH, this.newGame, 100, SpringLayout.NORTH, contentPane);
		this.sl_contentPane.putConstraint(SpringLayout.WEST, this.newGame, 10, SpringLayout.WEST, contentPane);
		this.contentPane.add(this.newGame);		
		this.continueGame = new JButton("Continue");
		this.sl_contentPane.putConstraint(SpringLayout.NORTH, this.continueGame, 100, SpringLayout.NORTH, contentPane);
		this.sl_contentPane.putConstraint(SpringLayout.WEST, this.continueGame, -140, SpringLayout.EAST, contentPane);
		this.continueGame.setIcon(new ImageIcon(FirstMenu.class.getResource("/gui/Resume.png")));
		this.continueGame.setFocusable(false);
		this.continueGame.setOpaque(false);
		this.continueGame.setMinimumSize(new Dimension(83, 23));
		this.continueGame.setMaximumSize(new Dimension(83, 23));
        this.continueGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainController.getController().getViewController().map(false);
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
            }
        });
        this.sl_contentPane.putConstraint(SpringLayout.EAST, this.continueGame, -10, SpringLayout.EAST, contentPane);
		this.continueGame.setBorderPainted(false);
		this.continueGame.setFont(new Font("Verdana", Font.PLAIN, 14));
		if (!MainController.getController().saveExists()) {
            this.continueGame.setEnabled(false);
        }
		this.contentPane.add(this.continueGame);		
		this.credits = new JButton("Credits");
		this.sl_contentPane.putConstraint(SpringLayout.WEST, this.credits, 80, SpringLayout.WEST, contentPane);
		this.sl_contentPane.putConstraint(SpringLayout.SOUTH, this.credits, 94, SpringLayout.SOUTH, this.newGame);
		this.credits.setIcon(new ImageIcon(FirstMenu.class.getResource("/gui/Bag.png")));
		this.credits.setFocusable(false);
		this.credits.setOpaque(false);
		this.credits.setBorderPainted(false);
		this.credits.setMaximumSize(new Dimension(89, 23));
		this.credits.setMinimumSize(new Dimension(89, 23));
		this.credits.setPreferredSize(new Dimension(89, 23));
        this.credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	View.getView().hideCurrent();
            	View.getView().addNew(new Credits());
            	View.getView().showCurrent();
            }     
        });
		this.contentPane.add(this.credits);	
		this.howToPlay = new JButton("How to Play");
		this.sl_contentPane.putConstraint(SpringLayout.NORTH, this.credits, 0, SpringLayout.NORTH, this.howToPlay);
		this.sl_contentPane.putConstraint(SpringLayout.EAST, this.credits, -13, SpringLayout.WEST, this.howToPlay);
		this.sl_contentPane.putConstraint(SpringLayout.NORTH, this.howToPlay, 67, SpringLayout.SOUTH, this.continueGame);
		this.sl_contentPane.putConstraint(SpringLayout.WEST, this.howToPlay, 223, SpringLayout.WEST, contentPane);
		this.sl_contentPane.putConstraint(SpringLayout.EAST, this.howToPlay, -81, SpringLayout.EAST, contentPane);
		this.howToPlay.setIcon(new ImageIcon(FirstMenu.class.getResource("/gui/Save.png")));
		this.howToPlay.setFocusable(false);
		this.howToPlay.setOpaque(false);
		this.howToPlay.setBorderPainted(false);
        this.howToPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	View.getView().hideCurrent();
            	View.getView().addNew(new HowToPlay());
            	View.getView().showCurrent();
            }     
        });
		this.contentPane.add(this.howToPlay);	
		this.welcomeLabel = new JLabel("Welcome to PokeJava!");
		this.welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.welcomeLabel.setFocusable(false);
		this.welcomeLabel.setForeground(Color.BLACK);
		this.sl_contentPane.putConstraint(SpringLayout.NORTH, this.welcomeLabel, 28, SpringLayout.NORTH, this.contentPane);
		this.sl_contentPane.putConstraint(SpringLayout.WEST, this.welcomeLabel, 0, SpringLayout.WEST, this.credits);
		this.welcomeLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		this.contentPane.add(this.welcomeLabel);
		this.setLocationRelativeTo(null);
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
