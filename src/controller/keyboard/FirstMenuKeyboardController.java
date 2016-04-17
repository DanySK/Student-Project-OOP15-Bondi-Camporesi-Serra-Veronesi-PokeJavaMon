package controller.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.MainController;
import controller.parameters.State;
import view.resources.ViewController;

public class FirstMenuKeyboardController implements KeyboardController, KeyListener {

    JFrame frame;
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_N) {
            frame.dispose();
            MainController.updateStatus(State.SECOND_MENU);
            ViewController.secondMenu((KeyListener) MainController.getController());
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            frame.dispose();
            ViewController.map(false);
            MainController.updateStatus(State.WALKING);
        }
    }

    @Override
    public boolean isKeyPressed() {
        return false;
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}