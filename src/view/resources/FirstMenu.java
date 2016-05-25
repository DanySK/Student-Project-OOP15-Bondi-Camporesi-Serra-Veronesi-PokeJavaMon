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
 * It is the main menu of the game. There are new game, continue, credits and controls.
 * 
 * @author Daniel Veronesi
 */

public class FirstMenu extends JFrame implements MyFrame {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3171512540755919384L;
	/**
	 * The panel where the image is put.
	 */
	private ContentPanel contentPane;
	/**
	 * It opens {@link SecondMenu}
	 */
	private JButton newGame;
	/**
	 * It loads the save game, if it exists, and continues the game from where it was interrupted
	 * It opens {@link GameView}.
	 */
	private JButton continueGame;
	/**
	 * It opens {@link HowToPlay}
	 */
	private JButton howToPlay;
	/**
	 * It opens {@link Credits}
	 */
	private JButton credits;
	/**
	 * The title of the main menu.
	 */
	private JLabel welcomeLabel;
	/**
	 * The panel where the buttons are put.
	 */
	private SpringLayout sl_contentPane;
	
	private class ContentPanel extends JPanel {

		private static final long serialVersionUID = 3361495155189049313L;
		private Image bgimage = null;

		ContentPanel() {
		    final MediaTracker mt = new MediaTracker(this);
		    try {
				bgimage = ImageIO.read(FirstMenu.class.getResourceAsStream("/gui/1stMenuBG.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		} catch (Exception e) {
		    //TODO: Fare catch di una semplice Exception e' sbagliato
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
		sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);		
		this.newGame = new JButton("New Game");
		this.newGame.setIcon(new ImageIcon(FirstMenu.class.getResource("/gui/Pokeball.png")));
		sl_contentPane.putConstraint(SpringLayout.EAST, this.newGame, 140, SpringLayout.WEST, contentPane);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.newGame, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.newGame, 10, SpringLayout.WEST, contentPane);
		this.contentPane.add(this.newGame);		
		this.continueGame = new JButton("Continue");
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.continueGame, 100, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.continueGame, -140, SpringLayout.EAST, contentPane);
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
		sl_contentPane.putConstraint(SpringLayout.EAST, this.continueGame, -10, SpringLayout.EAST, contentPane);
		this.continueGame.setBorderPainted(false);
		this.continueGame.setFont(new Font("Verdana", Font.PLAIN, 14));
		if (!MainController.getController().saveExists()) {
            this.continueGame.setEnabled(false);
        }
		this.contentPane.add(this.continueGame);		
		this.credits = new JButton("Credits");
		sl_contentPane.putConstraint(SpringLayout.WEST, this.credits, 80, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.credits, 94, SpringLayout.SOUTH, this.newGame);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.credits, 0, SpringLayout.NORTH, this.howToPlay);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.credits, -13, SpringLayout.WEST, this.howToPlay);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.howToPlay, 67, SpringLayout.SOUTH, this.continueGame);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.howToPlay, 223, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.howToPlay, -81, SpringLayout.EAST, contentPane);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.welcomeLabel, 28, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.welcomeLabel, 0, SpringLayout.WEST, this.credits);
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
