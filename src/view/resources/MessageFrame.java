package view.resources;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.parameters.State;

public class MessageFrame {

    public MessageFrame(String s, State st) {
        JFrame fr = new JFrame();
        JPanel pa = new JPanel();
        pa.setBorder(new LineBorder(Color.GRAY, 4));
        JLabel tx;
        tx = new JLabel(s);
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
        pa.add(tx);
        pa.add(button);
        fr.getContentPane().add(pa);
        fr.setAlwaysOnTop(true);
        fr.setBounds(100, 100, 450, 100);
        fr.setUndecorated(true);
        fr.setVisible(true);
    }
}

