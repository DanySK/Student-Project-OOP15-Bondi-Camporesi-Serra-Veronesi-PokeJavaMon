package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.parameters.State;
import model.player.PlayerImpl;
import view.View;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;

public class Menu extends JWindow implements MyFrame {

    private static final long serialVersionUID = 3868831254532069974L;
    private JPanel panel;
    private JButton box;
    private JButton team;
    private JButton bag;
    private JButton save;
    private JButton resume;
    private JButton music;
    private JLabel name;
    private JLabel money;
    private JLabel badges;
    
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true); 
        this.setBounds(100, 100, 180, 310);
        panel = new JPanel();
        this.setContentPane(panel);     
        panel.setBorder(new LineBorder(Color.GRAY, 4));
        panel.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));   
        name = new JLabel("Name: " + Controller.getController().getPlayer().getName());
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(name);
        money = new JLabel("Money: " + Controller.getController().getPlayer().getMoney());
        money.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(money);
        badges = new JLabel("Badges: " + Controller.getController().getPlayer().getLastBadge());
        badges.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(badges);
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
                    View.getView().addNew(new MessageFrame(State.WALKING, "NO PKMN IN BOX"));
                    View.getView().showCurrent();
                } else {
                    View.getView().hideCurrent();
                    BoxMenu bx = new BoxMenu();
                    View.getView().addNew(bx);
                    View.getView().showCurrent();
                }
            }
        });
        this.add(Box.createVerticalGlue());
        team = new JButton ("Team");
        team.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(team);
        team.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().hideCurrent();
                TeamMenu tm = new TeamMenu(true, false);
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
                BagMenu za = new BagMenu();
                View.getView().addNew(za);
                View.getView().showCurrent();
            }
        });             
        this.add(Box.createVerticalGlue());    
        music = new JButton("MOOOOOSECA");
        music.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(music);    
        music.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (Controller.getController().isPaused()) {
                    Controller.getController().resume();
                    Controller.getController().getStatusController().updateMusic();
                } else {
                    Controller.getController().pause();
                }
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
                View.getView().addNew(new MessageFrame(State.WALKING, "Salvataggio riuscito!"));
                View.getView().showCurrent();
            }
        });   
        this.add(Box.createVerticalGlue());
        resume = new JButton("Resume");
        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(resume);
        resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
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
    public void resumeFrame() {
        this.setVisible(true);
    }
}