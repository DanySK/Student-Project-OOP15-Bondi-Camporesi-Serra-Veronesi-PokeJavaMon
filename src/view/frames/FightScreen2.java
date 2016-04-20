package view.frames;

import java.awt.Graphics;
import java.awt.Panel;


import javax.swing.JFrame;
import javax.swing.JPanel;


import view.resources.ViewController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

public void paint(Graphics g) {
	int width = 150;
	double maxHP = 200; /*get maxHealth*/
	double HP = 100; /*get currentHealth*/
	double Scale = HP / maxHP;
    g.drawRect (10, 10, width, 10); 
    g.fillRect(10, 10, (int) (width * Scale), 10);
    g.drawRect (10, 10, width, 10);
    
    
    double maxHP2 = 200; /*get maxHealth*/
	double HP2 = 200; /*get currentHealth*/
	double Scale2 = HP2 / maxHP2;
    g.drawRect (220, 80, width, 10); 
    g.fillRect(220, 80, (int) (width * Scale2), 10);
    g.drawRect (220, 80, width, 10);
  }
}

public class FightScreen2 {
	
  public static void main(String[] a) {
	  
	  final JFrame f = new JFrame("Fight");
    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
	f.setAlwaysOnTop(true);
    f.setBounds(0, 0, 400, 300);
    f.setUndecorated(true);
    f.getContentPane().setLayout(new CardLayout(0, 0));
    
    Canvas myCanvas = new Canvas();
    f.getContentPane().add(myCanvas, "Canvas");
    myCanvas.setLayout(new CardLayout(0, 0));
    
    JPanel panel = new JPanel();
	myCanvas.add(panel, "Panel");
	panel.setLayout(null);
	
    /*myCanvas.add(panel);*/

    JButton attacca = new JButton("Attacca");
    attacca.setBounds(209, 222, 95, 40);
    panel.add(attacca);
	attacca.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			f.getContentPane().add(panel);          
		}
	});
	    
    JButton squadra = new JButton("Squadra");
    squadra.setBounds(305, 222, 95, 40);
    panel.add(squadra);
	squadra.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ViewController.team();
		}
	});
    
	JButton zaino = new JButton("Zaino");
	zaino.setBounds(209, 260, 95, 40);
	panel.add(zaino);
	zaino.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ViewController.bag();
		}
	});
    
    JButton fuga = new JButton("Fuga");
    fuga.setBounds(305, 260, 95, 40);
    panel.add(fuga);
    fuga.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
		f.dispose();
	}
});
	
    JPanel panel_1 = new JPanel();
    myCanvas.add(panel_1, "Pannello2");
    panel_1.setBounds(0, 223, 190, 77);
    panel_1.setLayout(null);
    
	JButton mossa1 = new JButton("Mossa 1");
	mossa1.setBounds(0, 220, 95, 40);
	panel_1.add(mossa1);
	mossa1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
/*errore qui??*/f.getContentPane().add(myCanvas);          
		}
	});
	
	JButton mossa2 = new JButton("Mossa 2");
	mossa2.setBounds(95, 220, 95, 40);
	panel_1.add(mossa2);
	
	JButton mossa3 = new JButton("Mossa 3");
	mossa3.setBounds(0, 260, 95, 40);
	panel_1.add(mossa3);
	
	JButton mossa4 = new JButton("Mossa 4");
	mossa4.setBounds(95, 260, 95, 40);
	panel_1.add(mossa4);
    
    f.setVisible(true);
  }
}