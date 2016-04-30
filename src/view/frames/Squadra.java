package view.frames;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.Stat;

public class Squadra {

	public Squadra() {
		
        JFrame frame = new JFrame("Squadra");
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setSize(500,600);
        frame.setUndecorated(true);
        
        JPanel contain = new JPanel();
        System.out.println(contain.getLayout());
        frame.setContentPane(contain);
        contain.setLayout(new GridLayout(1,1));

        final ArrayList<String>names = new ArrayList<String>();
        final ArrayList<String>lvl = new ArrayList<String>();
        final ArrayList<String>cHP = new ArrayList<String>();
        final ArrayList<String>mHP = new ArrayList<String>();
        
        for (Pokemon p : PlayerImpl.getPlayer().getSquad().getPokemonList()) {
        	names.add(p.getPokemon().name()); // Nome Pkmn
        	lvl.add("" + p.getStat(Stat.LVL)); // Livello
        	cHP.add("" + p.getCurrentHP());
        	mHP.add("" + p.getStat(Stat.HP));
        }
        System.out.println(names);
        System.out.println(lvl);
        System.out.println(cHP);
        System.out.println(mHP);
        
        contain.add(new Panel2(names, lvl, cHP, mHP, 1));
        
        frame.setVisible(true);

    }
}

class Panel2 extends JPanel
{
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> lvl = new ArrayList<String>();
    ArrayList<String> cHP = new ArrayList<String>();
    ArrayList<String> mHP = new ArrayList<String>();
    int cols;

    public Panel2(ArrayList<String> nam,ArrayList<String> lv,ArrayList<String> current, ArrayList<String> max, int c) 
    {
        this.names = nam;
        this.lvl =lv;
        this.cHP = current;
        this.mHP = max;
        this.cols = c;

        setLayout(new GridLayout(names.size(),cols));

        for(int i = 0; i<names.size();i++)
        {
            add(new JLabel(names.get(i)));
            add(new JTextField(lvl.get(i)));
            add(new JTextField(cHP.get(i)));
            add(new JTextField(mHP.get(i)));
            JButton but = new JButton("ESCI");
            add(but);
            JButton but2 = new JButton("CIAO");
            add(but2);
           }

    }}

