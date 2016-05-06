package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.Box;
import controller.Controller;
import controller.parameters.State;
import model.player.PlayerImpl;
import view.View;
import view.resources.MessageFrame;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class Menu extends JWindow implements MyFrame {

    private static final long serialVersionUID = 3868831254532069974L;
    private JButton box;
    private JButton team;
    private JButton bag;
    private JButton save;
    private JButton resume;
    
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true); 
        this.setBounds(100, 100, 180, 310);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        box = new JButton("BoxMenu");
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(box);
        box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (PlayerImpl.getPlayer().getBox().getBoxSize() < 1) {
                    disposeFrame();
                    View.getView().disposeCurrent();
                    View.getView().removeCurrent();
                    new MessageFrame(State.WALKING, "NO PKMN IN BOX");
                } else {
                    View.getView().hideCurrent();
                    BoxMenu bx = new BoxMenu();
                    View.getView().addNew(bx);
                    View.getView().showCurrent();
                }
            }
        });
        this.add(Box.createVerticalGlue());
        team = new JButton  ("Team");
        team.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(team);
        team.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().hideCurrent();
                Squadra tm = new Squadra(true, false);
                View.getView().addNew(tm);
                View.getView().showCurrent();
            }
        });
        this.add(Box.createVerticalGlue());    
        bag = new JButton("Bag");
        bag.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(bag);    
        bag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().hideCurrent();
                Zaino za = new Zaino();
                View.getView().addNew(za);
                View.getView().showCurrent();
            }
        });      
        this.add(Box.createVerticalGlue());
        save = new JButton("Save");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(save);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                disposeFrame();
                Controller.getController().getViewController().save();
                new MessageFrame(State.WALKING, "Salvataggio riuscito!");
            }
        });   
        this.add(Box.createVerticalGlue());
        resume = new JButton("Resume");
        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(resume);
        resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                disposeFrame();
                Controller.getController().updateStatus(State.WALKING);
            }
        });     
        this.add(Box.createVerticalGlue());
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
    public void resume() {
        this.setVisible(true);
    }
}