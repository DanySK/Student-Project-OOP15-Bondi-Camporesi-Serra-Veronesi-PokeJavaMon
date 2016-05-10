package view.frames;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

public class Statistics extends JWindow implements MyFrame {
	
    private static final long serialVersionUID = 3339649136760979503L;
    private MyPanel2 IMMAGINEPKMNPANEL;
    private JPanel panel_1;
    private JLabel pkmnName;
    private JLabel type;
    private JLabel typeValue;
    private JLabel levelValue;
    private JLabel level;
    private JLabel exp;
    private JLabel expValue;
    private JButton exit;
    private final Pokemon pk;
    
    public Statistics(Pokemon ID) { 
        this.pk = ID;
    }
	
    @Override
    public void showFrame() {
    	
    	this.setAlwaysOnTop(true);
		this.setBounds(100, 100, 500, 550);
		this.getContentPane().setLayout(new GridLayout(2, 4));
		panel_1 = new JPanel();
		this.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(4, 0));
		panel_1.setBorder(new LineBorder(Color.GRAY, 3));
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        View.getView().disposeCurrent();
		        View.getView().removeCurrent();
		        View.getView().resumeCurrent();
		    }
		});
		panel_1.add(exit);       
		type = new JLabel("Type");
		panel_1.add(type);
		typeValue = new JLabel(pk.getPokemon().getFirstType().name());
		panel_1.add(typeValue);
		pkmnName = new JLabel("" + pk.getPokemon());
		panel_1.add(pkmnName);
		level = new JLabel("Level");
		panel_1.add(level);
		levelValue = new JLabel(""+ pk.getStat(Stat.LVL));
		panel_1.add(levelValue);
		IMMAGINEPKMNPANEL = new MyPanel2(pk);
		panel_1.add(IMMAGINEPKMNPANEL);
		exp = new JLabel("Experience");
		panel_1.add(exp);
		expValue = new JLabel (""+ pk.getStat(Stat.EXP) + "/" + (pk.getNecessaryExp()+pk.getStat(Stat.EXP)));
		//è possibile aggiungere anche exp corrente
		panel_1.add(expValue);
		this.add(new statsPanel(pk));
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

class statsPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<String> moves = new ArrayList<String>();
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> stats = new ArrayList<String>();
    private ArrayList<String> values = new ArrayList<String>();
    private int cols = 1;

    public statsPanel(Pokemon ID) {	 
    	this.setBorder(new LineBorder(Color.GRAY, 3));
        stats.add(Stat.HP.name());
        stats.add(Stat.ATK.name());
        stats.add(Stat.DEF.name());
        stats.add(Stat.SPD.name());
        if (ID.getPokemon().name() != null) {
            for (int j=0; j<4; j++){
                moves.add("Move");
            }
            if (ID.getCurrentMoves().get(0) != Move.NULLMOVE) {
                names.add("" + ID.getCurrentMoves().get(0).name());
            }
            if (ID.getCurrentMoves().get(1) != Move.NULLMOVE) {
                names.add("" + ID.getCurrentMoves().get(1).name());
            }
            if (ID.getCurrentMoves().get(2) != Move.NULLMOVE) {
                names.add("" + ID.getCurrentMoves().get(2).name());
            }        	
            if (ID.getCurrentMoves().get(3) != Move.NULLMOVE) {
                names.add("" + ID.getCurrentMoves().get(3).name());
            }
            values.add("" + ID.getCurrentHP() + "/" + ID.getStat(Stat.HP));
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

class MyPanel2 extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Pokemon pk;
	
	public MyPanel2(final Pokemon pk) {
	    this.pk = pk;
	}

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        try {                
    	    image = ImageIO.read(new File(pk.getPokemon().getFrontSprite().getAbsolutePath()));
    	} catch (Exception ex) {
    	    try {
    	        image = ImageIO.read(new File(pk.getPokemon().getFrontSprite().getResourcePath()));
            } catch (Exception e) {
                System.out.println("CANNOT LOAD SPRITE");
            }
    	}
        g.drawImage(image, 0, 0, null);           
    }
}
