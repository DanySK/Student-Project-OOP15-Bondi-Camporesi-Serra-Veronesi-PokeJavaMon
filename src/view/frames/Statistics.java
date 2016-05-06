package view.frames;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import model.pokemon.Pokemon;
import model.pokemon.Stat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.GridLayout;

public class Statistics {
	
	private static JWindow w;
	
	public Statistics(Pokemon ID) { 
		
		w = new JWindow ();
        w.setAlwaysOnTop(true);
        w.setVisible(true);
        w.setSize(400, 500);

        JPanel contain = new JPanel();
        w.setContentPane(contain);

        contain.setLayout(new GridLayout(2,2));
        
        //JLabel Hp = new JLabel("HP:");
        //contain.add(Hp);
        
        JButton but = new JButton("Exit");
        but.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                Statistics.dispose();
            }
        });
        contain.add(but);
        
        contain.add(new NamePanel3(ID));
        w.setVisible(true);
	}
	public static void dispose() {
    	w.dispose();
    }
		
}

class NamePanel3 extends JPanel
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NamePanel3(Pokemon ID)
    {
	    
	//TODO ArrayList<moves> moves = new ArrayList<>();
	//TODO ArrayList<stats> stats = new ArrayList<>();
    	ArrayList<String> moves = new ArrayList<String>();
    	ArrayList<String> names = new ArrayList<String>();
    	ArrayList<String> stats = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        int cols = 1;

        if (ID.getPokemon().name() != null) {
        	System.out.println(ID);
        	for (int j=0; j<4; j++){
                moves.add("Move");
                stats.add("Stat");
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
        	
        /*System.out.println(moves);
            System.out.println(stats);
        	System.out.println(names);
        	System.out.println(values);
        	System.out.println(ID);*/
        }

        for(int i = 0; i<4;i++)
        {
            add(new JLabel(moves.get(i) + "" + (i+1)));
            if (i < names.size()){
                add(new JLabel(names.get(i)));
            }
            else{
                add(new JLabel("")); 
            }
            add(new JLabel(stats.get(i)));
            add(new JLabel(values.get(i)));
            }
        setLayout(new GridLayout(4,cols));
    	}
	}
