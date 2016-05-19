package view.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

public class HowToPlay extends JWindow {

    private static final long serialVersionUID = -8605835642465055987L;
    private List<JLabel> left;
    private List<JLabel> right;
    private JPanel panel;
    private JButton exit;
    
    public HowToPlay() {
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();
        this.panel = new JPanel();
        this.exit = new JButton("EXIT");
        this.left.add(new JLabel("KEY"));
        this.left.add(new JLabel("W - UP"));
        this.left.add(new JLabel("A - LEFT"));
        this.left.add(new JLabel("S - DOWN"));
        this.left.add(new JLabel("D - RIGHT"));
        this.left.add(new JLabel("ENTER"));
        this.left.add(new JLabel("ESC"));
        this.right.add(new JLabel("ACTION"));
        this.right.add(new JLabel("MOVE UP"));
        this.right.add(new JLabel("MOVE LEFT"));
        this.right.add(new JLabel("MOVE DOWN"));
        this.right.add(new JLabel("MOVE RIGHT"));
        this.right.add(new JLabel("INTERACT"));
        this.right.add(new JLabel("OPEN MENU"));
    }
    
    public void showHowToPlay() {
        this.setMinimumSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.panel.setLayout(new GridLayout(0, 2));
        ListIterator<JLabel> it1 = this.left.listIterator();
        ListIterator<JLabel> it2 = this.right.listIterator();
        for (int x = 0; x < 7; x++) {
            this.panel.add(it1.next());
            this.panel.add(it2.next());
        }
        this.exit.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();            
            }
        });
        this.panel.add(exit);
        this.panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.getContentPane().add(panel);
        this.setVisible(true);
    }
}
