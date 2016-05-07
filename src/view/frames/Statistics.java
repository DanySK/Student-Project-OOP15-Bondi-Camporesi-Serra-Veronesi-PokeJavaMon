package view.frames;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.GridLayout;

public class Statistics extends JWindow implements MyFrame {
	
    private static final long serialVersionUID = 3339649136760979503L;
    private final Pokemon pk;
    private JPanel contain;
    private JButton but;
    
    public Statistics(Pokemon ID) { 
        this.pk = ID;
    }
	
    @Override
    public void showFrame() {
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setSize(400, 500);
        contain = new JPanel();
        this.setContentPane(contain);
        contain.setLayout(new GridLayout(2,2));
        but = new JButton("Exit");
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View.getView().disposeCurrent();
                View.getView().removeCurrent();
                View.getView().resumeCurrent();
            }
        });
        contain.add(but);       
        contain.add(new NamePanel3(pk));
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

class NamePanel3 extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<String> moves = new ArrayList<String>();
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> stats = new ArrayList<String>();
    private ArrayList<String> values = new ArrayList<String>();
    private int cols = 1;

    public NamePanel3(Pokemon ID) {	    
        stats.add("MAX " + Stat.HP.name());
        stats.add(Stat.ATK.name());
        stats.add(Stat.DEF.name());
        stats.add(Stat.SPD.name());
        if (ID.getPokemon().name() != null) {
            for (int j=0; j<4; j++){
                moves.add("Move");
            }
            if (ID.getCurrentMoves().get(0) != null) {
                names.add("" + ID.getCurrentMoves().get(0).name());
            }
            if (ID.getCurrentMoves().get(1) != null) {
                names.add("" + ID.getCurrentMoves().get(1).name());
            }
            if (ID.getCurrentMoves().get(2) != null) {
                names.add("" + ID.getCurrentMoves().get(2).name());
            }        	
            if (ID.getCurrentMoves().get(3) != null) {
                names.add("" + ID.getCurrentMoves().get(3).name());
            }
            values.add("" + ID.getStat(Stat.HP));
            values.add("" + ID.getStat(Stat.ATK));
            values.add("" + ID.getStat(Stat.DEF));
            values.add("" + ID.getStat(Stat.SPD));
        }
        for(int i = 0; i<4;i++) {
            add(new JLabel(moves.get(i) + "" + (i+1)));
            if (i < names.size()){
                add(new JLabel(names.get(i)));
            } else{
                add(new JLabel("")); 
            }
            add(new JLabel(stats.get(i)));
            add(new JLabel(values.get(i)));
        }
        setLayout(new GridLayout(4,cols));
    }
}