package view.windows;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonType;
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
/**
 * StatisticsClass
 */
public class Statistics extends JWindow implements MyFrame {
	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 3339649136760979503L;
    /**
     * IMMAGINEPKMNPANEL
     */
    private MyPanel2 IMMAGINEPKMNPANEL;
    /**
     * panel
     */
    private JPanel panel;
    /**
     * pkmnName
     */
    private JLabel pkmnName;
    /**
     * type
     */
    private JLabel type;
    /**
     * typeValue
     */
    private JLabel typeValue;
    /**
     * levelValue
     */
    private JLabel levelValue;
    /**
     * level
     */
    private JLabel level;
    /**
     * exp
     */
    private JLabel exp;
    /**
     * expValue
     */
    private JLabel expValue;
    /**
     * exit
     */
    private JButton exit;
    /**
     * Statistics
     */
    private final Pokemon pk;
	/**
	 * Statistics
	 */
    public Statistics(Pokemon ID) { 
        this.pk = ID;
    }
	
    @Override
    public void showFrame() {
    	
    	this.setAlwaysOnTop(true);
		this.setBounds(100, 100, 500, 550);
		this.getContentPane().setLayout(new GridLayout(2, 4));
		this.panel = new JPanel();
		this.getContentPane().add(this.panel);
		this.panel.setLayout(new GridLayout(4, 0));
		this.panel.setBorder(new LineBorder(Color.GRAY, 3));
		this.exit = new JButton("Exit");
		this.exit.setBorderPainted(false);
		this.exit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        View.getView().disposeCurrent();
		        View.getView().removeCurrent();
		        View.getView().resumeCurrent();
		    }
		});
		this.panel.add(this.exit);       
		this.type = new JLabel("Type");
		this.panel.add(type);
		if (pk.getPokedexEntry().getSecondType() != PokemonType.NONE) {
			this.typeValue = new JLabel(this.pk.getPokedexEntry().getFirstType().name() + " / " + this.pk.getPokedexEntry().getSecondType());
		} else {
			this.typeValue = new JLabel(this.pk.getPokedexEntry().getFirstType().name());
		}
		this.panel.add(this.typeValue);
		this.pkmnName = new JLabel("" + this.pk.getPokedexEntry());
		this.panel.add(pkmnName);
		this.level = new JLabel("Level");
		this.panel.add(level);
		this.levelValue = new JLabel(""+ this.pk.getStat(Stat.LVL));
		this.panel.add(levelValue);
		this.IMMAGINEPKMNPANEL = new MyPanel2(this.pk);
		this.panel.add(this.IMMAGINEPKMNPANEL);
		this.exp = new JLabel("Experience");
		this.panel.add(this.exp);
		this.expValue = new JLabel (""+ this.pk.getStat(Stat.EXP) + "/" + (this.pk.getNecessaryExp()+this.pk.getStat(Stat.EXP)));
		//è possibile aggiungere anche exp corrente
		this.panel.add(expValue);
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
/**
 * statsPanel
 */
class statsPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    /**
     * moves
     */
    private ArrayList<String> moves = new ArrayList<String>();
    /**
     * names
     */
    private ArrayList<String> names = new ArrayList<String>();
    /**
     * stats
     */
    private ArrayList<String> stats = new ArrayList<String>();
    /**
     * values
     */
    private ArrayList<String> values = new ArrayList<String>();
    /**
     * cols
     */
    private int cols = 1;
	/**
	 * statsPanel
	 */
    public statsPanel(Pokemon ID) {	 
    	this.setBorder(new LineBorder(Color.GRAY, 3));
    	this.stats.add(Stat.MAX_HP.name());
        this.stats.add(Stat.ATK.name());
        this.stats.add(Stat.DEF.name());
        this.stats.add(Stat.SPD.name());
        if (ID.getPokedexEntry().name() != null) {
            for (int j=0; j<4; j++){
            	this.moves.add("Move");
            }
            if (ID.getCurrentMoves().get(0) != Move.NULLMOVE) {
            	this.names.add("" + ID.getCurrentMoves().get(0).name());
            }
            if (ID.getCurrentMoves().get(1) != Move.NULLMOVE) {
            	this.names.add("" + ID.getCurrentMoves().get(1).name());
            }
            if (ID.getCurrentMoves().get(2) != Move.NULLMOVE) {
            	this.names.add("" + ID.getCurrentMoves().get(2).name());
            }        	
            if (ID.getCurrentMoves().get(3) != Move.NULLMOVE) {
            	this.names.add("" + ID.getCurrentMoves().get(3).name());
            }
            this.values.add("" + ID.getCurrentHP() + "/" + ID.getStat(Stat.MAX_HP));
            this.values.add("" + ID.getStat(Stat.ATK));
            this.values.add("" + ID.getStat(Stat.DEF));
            this.values.add("" + ID.getStat(Stat.SPD));
        }
        for(int i = 0; i<4;i++) {
            add(new JLabel(this.moves.get(i) + "" + (i+1)));
            if (i < names.size()){
                add(new JLabel(this.names.get(i)));
            } else{
                add(new JLabel("")); 
            }
            add(new JLabel(this.stats.get(i)));
            add(new JLabel(this.values.get(i)));
        }
        setLayout(new GridLayout(4,cols));
    }
}
/**
 * MyPanel2
 */
class MyPanel2 extends JPanel{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * image
	 */
	private BufferedImage image;
	/**
	 * pk
	 */
	private Pokemon pk;
	/**
	 * MyPanel2
	 */
	public MyPanel2(final Pokemon pk) {
	    this.pk = pk;
	}

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        try {                
        	this.image = ImageIO.read(new File(this.pk.getPokedexEntry().getFrontSprite().getAbsolutePath()));
    	} catch (Exception ex) {
    	    try {
    	    	this.image = ImageIO.read(new File(this.pk.getPokedexEntry().getFrontSprite().getResourcePath()));
            } catch (Exception e) {
            }
    	}
        g.drawImage(this.image, 0, 0, null);           
    }
}