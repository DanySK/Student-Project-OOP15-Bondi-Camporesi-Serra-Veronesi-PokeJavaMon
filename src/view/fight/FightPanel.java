package view.fight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.Controller;
import controller.MainController;
import controller.fight.FightController;
import exceptions.CannotEscapeFromTrainerException;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import net.miginfocom.swing.MigLayout;
import view.View;
import view.windows.BagMenu;
import view.windows.MessageFrame;
import view.windows.TeamMenu;

public class FightPanel extends JPanel {

    private static final long serialVersionUID = 849521658746630224L;
    private static final int FIGHT = 0;
    private static final int BAG = 1;
    private static final int SQUAD = 2;
    private static final int RUN = 3;
	
    private final FightController fc;
    private final Controller ctrl;
    private JLabel enemyLvl;
    private JLabel enemyName;
    private HealthBar enemyHealthBar;
    private JLabel enemyFrontSprite;
    private JLabel allyLvl;
    private JLabel allyName;
    private JLabel allyHP;
    private HealthBar allyHealthBar;
    private JProgressBar allyExpBar;
    private JLabel allyBackSprite;
    private JPanel subPanel;	
    private List<JButton> choicesList = new ArrayList<>(); 
    private JButton choice1;
    private JButton choice2;
    private JButton choice3;
    private JButton choice4;  
    private final Map<Integer, ActionListener> fightMenuListeners;
    private final ActionListener moveListener;
    
    /**
     * Create the panel.
     */
    public FightPanel(final Pokemon enemy, final Pokemon ally) {
        setLayout(new MigLayout("", "[78px,grow][][53px][10px][][][][][][][][]", "[14px][14px][10px][][][][][][][][grow][][][]"));    
        this.choicesList.add(new JButton("FIGHT"));
        this.choicesList.add(new JButton("BAG"));
        this.choicesList.add(new JButton("SQUAD"));
        this.choicesList.add(new JButton("RUN"));		
        this.fc = MainController.getController().getFightController();
        this.ctrl = MainController.getController();		
        this.fightMenuListeners = new HashMap<>();
        this.fightMenuListeners.put(FIGHT, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                set4MovesToButtons();
            }			
        });
        this.fightMenuListeners.put(BAG, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                View.getView().hideCurrent();
                BagMenu za = new BagMenu();
                View.getView().addNew(za);
                View.getView().showCurrent();       
            }			
        });
        this.fightMenuListeners.put(SQUAD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (MainController.getController().getSquad().getNextAlivePokemon().isPresent()) {
                    View.getView().hideCurrent();
                    TeamMenu tm = new TeamMenu(true, false);
                    View.getView().addNew(tm);
                    View.getView().showCurrent();
                } else {
                    View.getView().hideCurrent();
                    View.getView().addNew(new MessageFrame(null, "NO POKEMON ALIVE"));
                    View.getView().showCurrent();
                }
            }			
        });
        this.fightMenuListeners.put(RUN, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.getController().getFightController().run();
                } catch (CannotEscapeFromTrainerException e1) {
                    View.getView().hideCurrent();
                    View.getView().addNew(new MessageFrame(null, "CANNOT ESCAPE FROM TRAINER"));
                    View.getView().showCurrent();
                }
            }
        });		
        this.moveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton thisButton = (JButton) e.getSource();
                final Move m = Move.valueOf(thisButton.getName());
                if (m == null || m == Move.NULLMOVE) {
                    return;
                }
                fc.attack(m);
                refresh();
//	     	View.getView().hideCurrent();
                setMenuButtons();
            }
        };
        this.choicesList.get(FIGHT).addActionListener(this.fightMenuListeners.get(FIGHT));
        this.choicesList.get(BAG).addActionListener(this.fightMenuListeners.get(BAG));
        this.choicesList.get(SQUAD).addActionListener(this.fightMenuListeners.get(SQUAD));
        this.choicesList.get(RUN).addActionListener(this.fightMenuListeners.get(RUN));	
        this.enemyName = new JLabel(enemy.getPokedexEntry().getName());
        add(this.enemyName, "cell 0 0");
        this.enemyLvl = new JLabel("Lvl " + enemy.getStat(Stat.LVL));
        add(this.enemyLvl, "cell 1 0,alignx right");	
        this.enemyHealthBar = new HealthBar(enemy.getStat(Stat.MAX_HP),enemy.getCurrentHP());
        add(this.enemyHealthBar, "cell 0 1 2 1,growx");	
        this.enemyFrontSprite = new JLabel();
        this.enemyFrontSprite.setIcon(new ImageIcon(FightPanel.class.getResource(enemy.getPokedexEntry().getFrontSprite().getResourcePath())));
        add(this.enemyFrontSprite, "cell 8 0 2 5,alignx center,aligny center");	
        this.allyBackSprite = new JLabel();
        this.allyBackSprite.setIcon(new ImageIcon(FightPanel.class.getResource(ally.getPokedexEntry().getBackSprite().getResourcePath())));
        add(this.allyBackSprite, "cell 0 5 3 5,alignx center,growy");		
        this.allyName = new JLabel(ally.getPokedexEntry().getName());
        add(this.allyName, "cell 8 6");		
        this.allyLvl = new JLabel("Lvl " + ally.getStat(Stat.LVL));
        add(this.allyLvl, "cell 9 6,alignx right");		
        this.allyHealthBar = new HealthBar(ally.getStat(Stat.MAX_HP), ally.getCurrentHP());
        add(this.allyHealthBar, "cell 8 7 2 1,growx");		
        this.allyHP = new JLabel(ally.getCurrentHP() + "/" + ally.getStat(Stat.MAX_HP));
        add(this.allyHP, "cell 9 8,alignx right,aligny baseline");		
        this.allyExpBar = new ExpBar(ally.getLevelExp(), ally.getStat(Stat.EXP));
        this.add(this.allyExpBar, "cell 8 9 2 1,growx");		
        this.subPanel = new JPanel();
        add(this.subPanel, "cell 0 10 11 4,grow");
        this.choice1 = new JButton("Action1");
        this.choice2 = new JButton("Action2");
        this.choice3 = new JButton("Action3");
        this.choice4 = new JButton("Action4");
        this.choice1 = this.choicesList.get(0);
        this.choice2 = this.choicesList.get(1);
        this.choice3 = this.choicesList.get(2);
        this.choice4 = this.choicesList.get(3);
        for (final JButton b : this.choicesList) {
        	b.setBorderPainted(false);
        	b.setFocusable(false);
        }
        setGroupLayout4Buttons(this.subPanel, this.choice1, this.choice2, this.choice3, this.choice4);		
    }
	
    private static void setGroupLayout4Buttons(final JPanel panel, final JButton b1, final JButton b2, final JButton b3, final JButton b4) {
        final GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel.createSequentialGroup()
                .addContainerGap()
                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                    .addComponent(b3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                    .addComponent(b2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b4, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
            .addContainerGap())
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(b1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                .addComponent(b2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                        .addGap(18)
                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                                .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                                        .addComponent(b4, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE))
                                .addComponent(b3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panel.setLayout(gl_panel);
    }

    private void set4MovesToButtons() {
        final List<Move> moves = this.ctrl.getSquad().getPokemonList().get(0).getCurrentMoves();
        int count = 0;
        for (final JButton b : this.choicesList) {
            if (b.getText().equals("FIGHT")) {
                b.removeActionListener(fightMenuListeners.get(FIGHT));
            } else if (b.getText().equals("BAG")) {
                b.removeActionListener(fightMenuListeners.get(BAG));
            } else if (b.getText().equals("SQUAD")) {
                b.removeActionListener(fightMenuListeners.get(SQUAD));
            } else if (b.getText().equals("RUN")) {
                b.removeActionListener(fightMenuListeners.get(RUN));
            }
            if (moves.get(count) == null || moves.get(count) == Move.NULLMOVE) {
                b.setText("----");
                b.setEnabled(false);
            } else {
                b.setName(moves.get(count).name());
                b.setText("(" + moves.get(count).getType().name() + ") "+ moves.get(count).name());
            }
            b.addActionListener(this.moveListener);
            count++;
            if (count >= PokemonInBattle.MAX_MOVES) {
                return;
            }
        }
    }
	
    public void setMenuButtons() {
        JButton tmp = choicesList.get(FIGHT);
        tmp.setText("FIGHT");
        tmp.removeActionListener(this.moveListener);
        tmp.addActionListener(fightMenuListeners.get(FIGHT));
        tmp.setEnabled(true);
        tmp = choicesList.get(BAG);
        tmp.setText("BAG");
        tmp.removeActionListener(this.moveListener);
        tmp.addActionListener(fightMenuListeners.get(BAG));
        tmp.setEnabled(true);
        tmp = choicesList.get(SQUAD);
        tmp.setText("SQUAD");
        tmp.removeActionListener(this.moveListener);
        tmp.addActionListener(fightMenuListeners.get(SQUAD));
        tmp.setEnabled(true);
        tmp = choicesList.get(RUN);
        tmp.setText("RUN");
        tmp.removeActionListener(this.moveListener);
        tmp.addActionListener(fightMenuListeners.get(RUN));
        tmp.setEnabled(true);
    }
    
    public void refresh() {
        final Pokemon currentEnemy = this.ctrl.getEnemyPokemonInFight();
        final Pokemon currentAlly = this.ctrl.getSquad().getPokemonList().get(0);
        this.enemyLvl.setText("lvl " + currentEnemy.getStat(Stat.LVL));
        this.enemyName.setText(currentEnemy.getPokedexEntry().getName());
        this.enemyHealthBar.setMaximum(currentEnemy.getStat(Stat.MAX_HP));
        this.enemyHealthBar.setValue(currentEnemy.getCurrentHP());
        this.enemyFrontSprite.setIcon(new ImageIcon(FightPanel.class.getResource(currentEnemy.getPokedexEntry().getFrontSprite().getResourcePath())));
        this.allyLvl.setText("lvl " + currentAlly.getStat(Stat.LVL));
        this.allyName.setText(currentAlly.getPokedexEntry().getName());
        this.allyHealthBar.setMaximum(currentAlly.getStat(Stat.MAX_HP));
        this.allyHealthBar.setValue(currentAlly.getCurrentHP());
        this.allyHP.setText(currentAlly.getCurrentHP() + "/" + currentAlly.getStat(Stat.MAX_HP));
        this.allyBackSprite.setIcon(new ImageIcon(FightPanel.class.getResource(currentAlly.getPokedexEntry().getBackSprite().getResourcePath())));
        this.allyExpBar.setMaximum(currentAlly.getStat(Stat.EXP) + currentAlly.getNecessaryExp());
        this.allyExpBar.setValue(currentAlly.getStat(Stat.EXP));
        this.validate();
        this.repaint();
    }
}