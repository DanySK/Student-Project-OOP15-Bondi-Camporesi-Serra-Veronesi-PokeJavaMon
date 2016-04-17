package controller.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.MainController;
import controller.parameters.State;
import view.resources.ViewController;

public class SecondMenuKeyboardController implements KeyboardController, KeyListener {

    private JFrame frame;
    private JTextField nickname;
    private JButton inizia;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
            String s = nickname.getText();
            if (s.length() < 4 || s.length() > 20) {
                    JOptionPane.showMessageDialog(inizia, "You Naive Idiot");
               }
            else {
            
            ViewController.setName(nickname.getText());
            ViewController.map(true);
            MainController.updateStatus(State.WALKING);
            frame.dispose();
            }
        }
    }

    @Override
    public boolean isKeyPressed() {
        return false;
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setText(JTextField nickname) {
        this.nickname = nickname;
    }

    public void setButton(JButton inizia) {
        this.inizia = inizia;
    }
}