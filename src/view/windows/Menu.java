package view.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.MainController;
import controller.parameters.State;
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
        this.panel = new JPanel();
        this.setContentPane(this.panel);     
        this.panel.setBorder(new LineBorder(Color.GRAY, 4));
        this.panel.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));   
        this.name = new JLabel("Name: " + MainController.getController().getPlayer().get().getName());
        this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.name);
        this.money = new JLabel("Money: " + MainController.getController().getPlayer().get().getMoney());
        this.money.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.money);
        this.badges = new JLabel("Badges: " + MainController.getController().getPlayer().get().getLastBadge());
        this.badges.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.badges);
        this.add(Box.createVerticalGlue());
        this.box = new JButton("BoxMenu");
        this.box.setBorderPainted(false);
        this.box.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.box);
        this.box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (MainController.getController().getBox().get().getBoxSize() < 1) {
                    View.getView().hideCurrent();
                    View.getView().addNew(new MessageFrame(null, "NO PKMN IN BOX"));
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
        this.team = new JButton ("Team");
        this.team.setBorderPainted(false);
        this.team.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.team);
        this.team.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().hideCurrent();
                TeamMenu tm = new TeamMenu(true, false);
                View.getView().addNew(tm);
                View.getView().showCurrent();
            }
        });
        this.add(Box.createVerticalGlue());    
        this.bag = new JButton("Bag");
        this.bag.setBorderPainted(false);
        this.bag.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.bag);    
        this.bag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().hideCurrent();
                BagMenu za = new BagMenu();
                View.getView().addNew(za);
                View.getView().showCurrent();
            }
        });             
        this.add(Box.createVerticalGlue());    
        this.music = new JButton("Music");
        this.music.setBorderPainted(false);
        this.music.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.music);    
        this.music.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (MainController.getController().isPaused()) {
                    MainController.getController().resume();
                    MainController.getController().getStatusController().updateMusic();
                } else {
                    MainController.getController().pause();
                }
            }
        });         
        this.add(Box.createVerticalGlue());
        this.save = new JButton("Save");
        this.save.setBorderPainted(false);
        this.save.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.save);
        this.save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                disposeFrame();
                MainController.getController().getViewController().save();
                View.getView().addNew(new MessageFrame(State.WALKING, "Save complete!"));
                View.getView().showCurrent();
            }
        });   
        this.add(Box.createVerticalGlue());
        this.resume = new JButton("Resume");
        this.resume.setBorderPainted(false);
        this.resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(this.resume);
        this.resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
                MainController.getController().updateStatus(State.WALKING);
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