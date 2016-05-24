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

public class SecondMenu extends JFrame implements MyFrame {
    public SecondMenu() {
    }

    private static final long serialVersionUID = -6893200324739176114L;
    private static final int COORDINATES_OFFSET = 20;
    private static final int DIM_OFFSET = 35;
    private static String s;
    private JPanel contentPane;
    private static JTextField textField;

    public void showFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(SecondMenu.class.getResource("/img/POKEPALLA.png")));
        setResizable(false);
        setTitle("PokeJavaMon");
        setMinimumSize(new Dimension(450, 300));
        setMaximumSize(new Dimension(450, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new ImagePanel();
        contentPane.setPreferredSize(new Dimension(450, 300));
        contentPane.setMinimumSize(new Dimension(450, 300));
        contentPane.setMaximumSize(new Dimension(450, 300));
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[89px][50.00px][133px][50.00px][77px]", "[26px][20px][23px][][][][][][][][]"));
        
        JLabel name = new JLabel("INSERT NAME ( 4 - 15 CHAR )");
        name.setFont(new Font("Verdana", Font.BOLD, 20));
        name.setForeground(Color.WHITE);
        contentPane.add(name, "cell 0 0 5 1,alignx center,aligny center");
        
        textField = new JTextField();
        contentPane.add(textField, "cell 2 3,growx,aligny center");
        textField.setColumns(10);
        
        JLabel bulba_label = new JLabel("");
        bulba_label.setHorizontalTextPosition(SwingConstants.CENTER);
        bulba_label.setIcon(new ImageIcon(SecondMenu.class.getResource("/sprites/front/F001.png")));
        bulba_label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(bulba_label, "cell 0 6 1 4,alignx center");
        
        JLabel charm_label = new JLabel("");
        charm_label.setIcon(new ImageIcon(SecondMenu.class.getResource("/sprites/front/F004.png")));
        contentPane.add(charm_label, "cell 2 7 1 3,alignx center");
        
        JLabel squi_label = new JLabel("");
        squi_label.setIcon(new ImageIcon(SecondMenu.class.getResource("/sprites/front/F007.png")));
        contentPane.add(squi_label, "cell 4 7 1 3,alignx center");
        
        JButton bulba_button = new JButton("Bulbasaur");
        bulba_button.setFont(new Font("Verdana", Font.BOLD, 10));
        bulba_button.setBorderPainted(false);
        bulba_button.setFocusable(false);
        contentPane.add(bulba_button, "cell 0 10,alignx right,aligny bottom");
        addListener(bulba_button, Pokedex.BULBASAUR);
        JButton charm_button = new JButton("Charmander");
        charm_button.setFont(new Font("Verdana", Font.BOLD, 10));
        charm_button.setFocusable(false);
        charm_button.setBorderPainted(false);
        contentPane.add(charm_button, "cell 2 10,alignx center,aligny center");
        addListener(charm_button, Pokedex.CHARMANDER);
        JButton squi_button = new JButton("Squirtle");
        squi_button.setFont(new Font("Verdana", Font.BOLD, 10));
        squi_button.setFocusable(false);
        squi_button.setBorderPainted(false);
        contentPane.add(squi_button, "cell 4 10,alignx center,aligny center");
        addListener(squi_button, Pokedex.SQUIRTLE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static String getPlayerName() {
        s = textField.getText();
        return s;      
    }
    
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

    public class ImagePanel extends JPanel {
        
        private static final long serialVersionUID = 3361495155189049313L;
        private Image bgimage = null;

        ImagePanel() {
            final MediaTracker mt = new MediaTracker(this);
            try {
                        bgimage = ImageIO.read(FirstMenu.class.getResourceAsStream("/gui/pokemon_pokeball.png"));
                } catch (IOException e1) {
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
            g.drawImage(bgimage, 0 - COORDINATES_OFFSET, 0 - COORDINATES_OFFSET, 450 + DIM_OFFSET, 300 + DIM_OFFSET, null);
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
