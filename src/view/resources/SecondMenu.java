package view.resources;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import view.windows.MyFrame;

import javax.swing.SwingConstants;

import controller.MainController;
import model.pokemon.Pokedex;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This {@link JWindow} handles the menu in which the user chooses his name
 * and with what starter he wants to start the game.
 */
public class SecondMenu extends JFrame implements MyFrame {
    public SecondMenu() {
    }

    private static final long serialVersionUID = -6893200324739176114L;
    private static final int COORDINATES_OFFSET = 20;
    private static final int DIM_OFFSET = 35;
    private static String s;
    private JPanel contentPane;
    private JLabel name;
    private JLabel bulba_label;
    private JLabel charm_label;
    private JLabel squi_label;
    private JButton bulba_button;
    private JButton charm_button;
    private JButton squi_button;
    private static JTextField textField;
    /**
     * It handles the whole layout of the {@link JWindow}.
     */
    public void showFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(SecondMenu.class.getResource("/img/POKEPALLA.png")));
        setResizable(false);
        setTitle("PokeJavaMon");
        setMinimumSize(new Dimension(450, 300));
        setMaximumSize(new Dimension(450, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        this.contentPane = new ImagePanel();
        this.contentPane.setPreferredSize(new Dimension(450, 300));
        this.contentPane.setMinimumSize(new Dimension(450, 300));
        this.contentPane.setMaximumSize(new Dimension(450, 300));
        this.contentPane.setBorder(null);
        setContentPane(this.contentPane);
        this.contentPane.setLayout(new MigLayout("", "[89px][50.00px][133px][50.00px][77px]", "[26px][20px][23px][][][][][][][][]"));
        this.name = new JLabel("INSERT NAME ( 4 - 15 CHAR )");
        this.name.setFont(new Font("Verdana", Font.BOLD, 20));
        this.name.setForeground(Color.WHITE);
        this.contentPane.add(name, "cell 0 0 5 1,alignx center,aligny center");
        SecondMenu.textField = new JTextField();
        this.contentPane.add(textField, "cell 2 3,growx,aligny center");
        SecondMenu.textField.setColumns(10);
        this.bulba_label = new JLabel("");
        this.bulba_label.setHorizontalTextPosition(SwingConstants.CENTER);
        this.bulba_label.setIcon(new ImageIcon(SecondMenu.class.getResource("/sprites/front/F001.png")));
        this.bulba_label.setHorizontalAlignment(SwingConstants.CENTER);
        this.contentPane.add(this.bulba_label, "cell 0 6 1 4,alignx center");
        this.charm_label = new JLabel("");
        this.charm_label.setIcon(new ImageIcon(SecondMenu.class.getResource("/sprites/front/F004.png")));
        this.contentPane.add(charm_label, "cell 2 7 1 3,alignx center");
        this.squi_label = new JLabel("");
        this.squi_label.setIcon(new ImageIcon(SecondMenu.class.getResource("/sprites/front/F007.png")));
        this.contentPane.add(this.squi_label, "cell 4 7 1 3,alignx center");
        this.bulba_button = new JButton("Bulbasaur");
        this.bulba_button.setFont(new Font("Verdana", Font.BOLD, 10));
        this.bulba_button.setBorderPainted(false);
        this.bulba_button.setFocusable(false);
        this.contentPane.add(this.bulba_button, "cell 0 10,alignx right,aligny bottom");
        addListener(this.bulba_button, Pokedex.BULBASAUR);
        this.charm_button = new JButton("Charmander");
        this.charm_button.setFont(new Font("Verdana", Font.BOLD, 10));
        this.charm_button.setFocusable(false);
        this.charm_button.setBorderPainted(false);
        this.contentPane.add(this.charm_button, "cell 2 10,alignx center,aligny center");
        addListener(this.charm_button, Pokedex.CHARMANDER);
        this.squi_button = new JButton("Squirtle");
        this.squi_button.setFont(new Font("Verdana", Font.BOLD, 10));
        this.squi_button.setFocusable(false);
        this.squi_button.setBorderPainted(false);
        this.contentPane.add(this.squi_button, "cell 4 10,alignx center,aligny center");
        addListener(this.squi_button, Pokedex.SQUIRTLE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * It gets the text written in the textfield.
     * 
     * @return a string with the name written.
     */
    public static String getPlayerName() {
        s = textField.getText();
        return s;      
    }
    /**
     * It adds a listener to the buttons of the game that checks
     * if the nickname chosen has the right length
     * 
     * @param b The button with the name of the starter pokémon.
     * @param p The starter pokémon it will be put in the party.
     */
    private void addListener(final JButton b, final Pokedex p) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s = textField.getText();
                if (s.length() < 4 || s.length() > 15) {
                    JOptionPane.showMessageDialog(b, "Insert a valid NAME");
                } else {
                    MainController.getController().selectStarter(p);
                    MainController.getController().getViewController().setName(textField.getText());
                    MainController.getController().getViewController().map(true);
                    disposeFrame();
                }
            }
        });
    }
    /**
     * The {@link JPanel} where the background image is created
     */
    public class ImagePanel extends JPanel {
        private static final long serialVersionUID = 3361495155189049313L;
        private Image bgimage = null;
        /**
         * It sets an image as background.
         * It catches an exception if it fails to load the picture.
         */
        ImagePanel() {
            final MediaTracker mt = new MediaTracker(this);
            try {
            		this.bgimage = ImageIO.read(FirstMenu.class.getResourceAsStream("/gui/pokemon_pokeball.png"));
                } catch (IOException e1) {
                        e1.printStackTrace();
                }
            mt.addImage(this.bgimage, 0);
            try {
              mt.waitForAll();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }

          @Override
          protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.bgimage, 0 - COORDINATES_OFFSET, 0 - COORDINATES_OFFSET, 450 + DIM_OFFSET, 300 + DIM_OFFSET, null);
          }
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
