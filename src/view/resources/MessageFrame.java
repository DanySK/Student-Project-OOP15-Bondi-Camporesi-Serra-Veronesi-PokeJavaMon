package view.resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainController;
import controller.parameters.State;

public class MessageFrame {

    public MessageFrame(String s, State st) {
        JFrame fr = new JFrame();
        JPanel pa = new JPanel();
        JLabel tx;
        tx = new JLabel(s);
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st != null) {
                    MainController.getController().updateStatus(st);
                }
                fr.dispose();
            }
        });
        pa.add(tx);
        pa.add(button);
        fr.add(pa);
        fr.setAlwaysOnTop(true);
        fr.setBounds(100, 100, 450, 100);
        fr.setUndecorated(true);
        fr.setVisible(true);
    }
}
