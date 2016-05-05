package view.resources;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.parameters.State;

public class MessageFrame {

    public MessageFrame(State st, String... strs) {
        JWindow fr = new JWindow();
        JPanel pa = new JPanel();
        pa.setBorder(new LineBorder(Color.GRAY, 4));
        pa.setLayout(new GridLayout(0,1));
        List<JLabel> labels = new ArrayList<>();
        for (String s : strs) {
            labels.add(new JLabel(s));
        }
        for (JLabel l : labels) {
            pa.add(l);
        }
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st != null) {
                    Controller.getController().updateStatus(st);
                }
                fr.dispose();
            }
        });
        pa.add(button);
        fr.getContentPane().add(pa);
        fr.setAlwaysOnTop(true);
        fr.setBounds(100, 100, 450, 100 * strs.length);
        fr.setLocationRelativeTo(null);
        //fr.setUndecorated(true);
        fr.setVisible(true);
    }
}

