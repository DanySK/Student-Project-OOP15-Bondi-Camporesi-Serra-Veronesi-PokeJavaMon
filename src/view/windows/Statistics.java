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
 * This {@link JWindow} handles the statistics menu with the pokémon selected and his relative informations.
 * 
 * @author Daniel Veronesi
 */
public class Statistics extends JWindow implements MyFrame {
	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 3339649136760979503L;
    /**
     * It is a panel used to get the pokémon's sprite.
     */
    private MyPanel2 IMAGINEPKMNPANEL;
    /**
     * It creates the panel with pokémon's informations like moves and stats.
     */
    private JPanel panel;
    /**
     * A label that gets the name of the pokémon selected previously.
     */
    private JLabel pkmnName;
    /**
     * A label that says "Type".
     */
    private JLabel type;
    /**
     * A label that gets the type (or types) of the pokémon.
     */
    private JLabel typeValue;
    /**
     * A label that gets the level of the pokémon.
     */
    private JLabel levelValue;
    /**
     * A label that says "Level".
     */
    private JLabel level;
    /**
     * A label that says "Experience".
     */
    private JLabel exp;
    /**
     * A label that gets the current experience and the needed experience of the pokémon.
     */
    private JLabel expValue;
    /**
     * A button that closes Statistics.
     */
    private JButton exit;
    /**
     * The pokémon selected.
     */
    private final Pokemon pk;
	/**
	 * A function that get the pokémon from his ID.
	 * @param ID The identity number of the pokémon.
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
		this.IMAGINEPKMNPANEL = new MyPanel2(this.pk);
		this.panel.add(this.IMAGINEPKMNPANEL);
		this.exp = new JLabel("Experience");
		this.panel.add(this.exp);
		this.expValue = new JLabel (""+ this.pk.getStat(Stat.EXP) + "/" + (this.pk.getNecessaryExp()+this.pk.getStat(Stat.EXP)));
		//è possibile aggiungere anche exp corrente
		this.panel.add(expValue);
		this.add(new statsPanel(pk));
		this.setLocationRelativeTo(null);
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
 * This {@link JPanel} handles the moves and the 4 stats of the pokémon.
 */
class statsPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    /**
     * An array filled with the number of the moves.
     */
    private ArrayList<String> moves = new ArrayList<String>();
    /**
     * An array filled with the name of the moves.
     */
    private ArrayList<String> names = new ArrayList<String>();
    /**
     * An array filled with the number of the statistics.
     */
    private ArrayList<String> stats = new ArrayList<String>();
    /**
     * An array filled with the value of the statistics.
     */
    private ArrayList<String> values = new ArrayList<String>();
    /**
     * The number of columns.
     */
    private int cols = 1;
	/**
	 * The panel that gets the statistic of the pokémon.
	 * @param ID The pokémon selected.
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
 * This {@link JPanel} is used to get the image of the pokémon.
 */
class MyPanel2 extends JPanel{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The image of the pokémon.
	 */
	private BufferedImage image;
	/**
	 * The pokémon selected.
	 */
	private Pokemon pk;
	/**
	 * The panel for the image
	 * @param pk The pokémon.
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