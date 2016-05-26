package view.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;
/**
 * This {@link JWindow} implement the session when the map is loading.
 * 
 */
public class LoadingScreen extends JWindow {
    private static final long serialVersionUID = 7074031785079664583L;
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
	/**
	 * It is a panel that informs the user to wait because the screen is loading.
	 */
    public void showLoadingScreen() {
        this.setMinimumSize(new Dimension(300,150));
        this.setLocationRelativeTo(null);
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(2, 1));
        this.label1 = new JLabel("LOADING");
        this.label2 = new JLabel("Please wait...");
        this.panel.add(label1);
        this.panel.add(label2);
        this.panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    public void disposeWindow() {
        this.dispose();
    }
}
