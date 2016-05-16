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

import controller.MainController;
import controller.parameters.State;
import view.View;

public class MessageFrame extends JWindow implements MyFrame {
	
    private static final long serialVersionUID = 1370776687087493267L;
    private JPanel panel;
    private final List<JLabel> labels = new ArrayList<>();
    private JButton ok;
    private String[] msgs;
    private State st;
	
    public MessageFrame(State st, String... strs) {
        this.st = st;
        this.msgs = strs;
    }

    @Override
    public void showFrame() {
        panel = new JPanel();
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        panel.setLayout(new GridLayout(0,1));
        for (String s : msgs) {
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
                    MainController.getController().updateStatus(st);
                    disposeFrame();
                } else {
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    View.getView().resumeCurrent();
                }
            }
        });
        panel.add(ok);
        this.getContentPane().add(panel);
        this.setAlwaysOnTop(true);
        if (msgs.length > 4) {
            this.setBounds(100, 100, 450, 400);
        } else {
            this.setBounds(100, 100, 450, 100 * msgs.length);
        }
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