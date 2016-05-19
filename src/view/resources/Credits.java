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

public class Credits extends JWindow {

    private static final long serialVersionUID = 313390316550029L;
    private List<JLabel> left;
    private List<JLabel> right;
    private JPanel panel;
    private JButton exit;
    
    public Credits() {
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();
        this.panel = new JPanel();
        this.exit = new JButton("EXIT");
        this.left.add(new JLabel("Christian Serra"));
        this.left.add(new JLabel("Daniel Veronesi"));
        this.left.add(new JLabel("Davide Bondi"));
        this.left.add(new JLabel("Michael Camporesi"));
        this.right.add(new JLabel("Model"));
        this.right.add(new JLabel("View and Beta Testing"));
        this.right.add(new JLabel("Fight and Tests"));
        this.right.add(new JLabel("Controller"));
    }
    
    public void showCredits() {
        this.setMinimumSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.panel.setLayout(new GridLayout(0, 2));
        ListIterator<JLabel> it1 = this.left.listIterator();
        ListIterator<JLabel> it2 = this.right.listIterator();
        for (int x = 0; x < 4; x++) {
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
