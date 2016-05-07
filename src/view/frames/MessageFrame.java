package view.frames;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.parameters.State;

/* 
 * implements -> extends -> mettere i this
 */

public class MessageFrame {
	private JPanel panel;
	private final List<JLabel> labels = new ArrayList<>();
	private JButton ok;
	
    public MessageFrame(State st, String... strs) {
        JWindow window = new JWindow();
        panel = new JPanel();
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        panel.setLayout(new GridLayout(0,1));
        for (String s : strs) {
            labels.add(new JLabel(s));
        }
        for (JLabel l : labels) {
        	panel.add(l);
        }
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st != null) {
                    Controller.getController().updateStatus(st);
                }
                window.dispose();
            }
        });
        panel.add(ok);
        window.getContentPane().add(panel);
        window.setAlwaysOnTop(true);
        window.setBounds(100, 100, 450, 100 * strs.length);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

