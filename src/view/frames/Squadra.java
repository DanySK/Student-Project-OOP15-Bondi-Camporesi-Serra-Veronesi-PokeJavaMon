package view.frames;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

import controller.ViewController;
  
public class Squadra {

public Squadra() {
	
		final JFrame f = new JFrame("Squadra");
		f.setResizable(false);
		f.setAlwaysOnTop(true);          	
		f.setUndecorated(true);
		f.setBounds(100, 100, 650, 500);
		f.getContentPane().setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   	
    	JTextArea FirstName = new JTextArea("Feraligatr");
    	FirstName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	FirstName.setOpaque(false);
    	FirstName.setBounds(75, 35, 95, 25);
    	f.getContentPane().add(FirstName);
    	
    	JTextArea FirstLv = new JTextArea("Lvl 100");
    	FirstLv.setOpaque(false);
    	FirstLv.setBounds(245, 35, 70, 20);
    	f.getContentPane().add(FirstLv);
    	
    	JProgressBar FirstHPbar = new JProgressBar();
    	FirstHPbar.setBounds(375, 35, 200, 22);
    	f.getContentPane().add(FirstHPbar);
    	
    	JButton FirstStats = new JButton("Stats");
    	FirstStats.setBounds(375, 65, 90, 25);
    	f.getContentPane().add(FirstStats);
    	FirstStats.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    					f.dispose();
ViewController.getController().stats();
    			}
    	});
    	
    	JButton FirstOrder = new JButton("Ordina");
    	FirstOrder.setBounds(485, 65, 90, 25);
    	f.getContentPane().add(FirstOrder);
    	
    	JTextArea SecondName = new JTextArea("Aerodactyl");
    	SecondName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	SecondName.setOpaque(false);
    	SecondName.setBounds(75, 110, 95, 25);
    	f.getContentPane().add(SecondName);
    	
    	JTextArea SecondLv = new JTextArea("Lvl 100");
    	SecondLv.setOpaque(false);
    	SecondLv.setBounds(245, 110, 70, 25);
    	f.getContentPane().add(SecondLv);
    	
    	JProgressBar SecondHPbar = new JProgressBar();
    	SecondHPbar.setBounds(375, 110, 200, 22);
    	f.getContentPane().add(SecondHPbar);
    	
    	JButton SecondStats = new JButton("Stats");
    	SecondStats.setBounds(375, 140, 90, 25);
    	f.getContentPane().add(SecondStats);
    	
    	JButton SecondOrder = new JButton("Ordina");
    	SecondOrder.setBounds(485, 140, 90, 25);
    	f.getContentPane().add(SecondOrder);
    	
    	JTextArea ThirdName = new JTextArea("Pikachu");
    	ThirdName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	ThirdName.setOpaque(false);
    	ThirdName.setBounds(75, 185, 95, 25);
    	f.getContentPane().add(ThirdName);
    	
    	JTextArea ThirdLv = new JTextArea("Lvl 100");
    	ThirdLv.setOpaque(false);
    	ThirdLv.setBounds(245, 185, 70, 20);
    	f.getContentPane().add(ThirdLv);
    	
    	JProgressBar ThirdHPbar = new JProgressBar();
    	ThirdHPbar.setBounds(375, 185, 200, 22);
    	f.getContentPane().add(ThirdHPbar);
    	
    	JButton ThirdStats = new JButton("Stats");
    	ThirdStats.setBounds(375, 215, 90, 25);
    	f.getContentPane().add(ThirdStats);
    	
    	JButton ThirdOrder = new JButton("Ordina");
    	ThirdOrder.setBounds(485, 215, 90, 25);
    	f.getContentPane().add(ThirdOrder);
    	
    	JTextArea FourthName = new JTextArea("Rayquaza");
    	FourthName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	FourthName.setOpaque(false);
    	FourthName.setBounds(75, 260, 95, 25);
    	f.getContentPane().add(FourthName);
    	
    	JTextArea FourthLv = new JTextArea("Lvl 100");
    	FourthLv.setOpaque(false);
    	FourthLv.setBounds(245, 260, 70, 20);
    	f.getContentPane().add(FourthLv);
    	
    	JProgressBar FourthHPbar = new JProgressBar();
    	FourthHPbar.setBounds(375, 260, 200, 22);
    	f.getContentPane().add(FourthHPbar);
    	
    	JButton FourthStats = new JButton("Stats");
    	FourthStats.setBounds(375, 290, 90, 25);
    	f.getContentPane().add(FourthStats);
    	
    	JButton FourthOrder = new JButton("Ordina");
    	FourthOrder.setBounds(485, 290, 90, 25);
    	f.getContentPane().add(FourthOrder);
    	
    	JTextArea FifthName = new JTextArea("Tentacool");
    	FifthName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	FifthName.setOpaque(false);
    	FifthName.setBounds(75, 335, 95, 25);
    	f.getContentPane().add(FifthName);
    	
    	JTextArea FifthLv = new JTextArea("Lvl 100");
    	FifthLv.setOpaque(false);
    	FifthLv.setBounds(245, 335, 70, 20);
    	f.getContentPane().add(FifthLv);
    	
    	JProgressBar FifthHPbar = new JProgressBar();
    	FifthHPbar.setBounds(375, 335, 200, 22);
    	f.getContentPane().add(FifthHPbar);
    	
    	JButton FifthStats = new JButton("Stats");
    	FifthStats.setBounds(375, 365, 90, 25);
    	f.getContentPane().add(FifthStats);
    	
    	JButton FifthOrder = new JButton("Ordina");
    	FifthOrder.setBounds(485, 365, 90, 25);
    	f.getContentPane().add(FifthOrder);
    	
    	JTextArea SixthName = new JTextArea("Magikarp");
    	SixthName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	SixthName.setOpaque(false);
    	SixthName.setBounds(75, 410, 95, 25);
    	f.getContentPane().add(SixthName);
    	
    	JTextArea SixthLv = new JTextArea("Lvl 100");
    	SixthLv.setOpaque(false);
    	SixthLv.setBounds(245, 410, 70, 20);
    	f.getContentPane().add(SixthLv);
    	
    	JProgressBar SixthHPbar = new JProgressBar();
    	SixthHPbar.setBounds(375, 410, 200, 22);
    	f.getContentPane().add(SixthHPbar);
    	
    	JButton SixthStats = new JButton("Stats");
    	SixthStats.setBounds(375, 440, 90, 25);
    	f.getContentPane().add(SixthStats);
    	
    	JButton SixthOrder = new JButton("Ordina");
    	SixthOrder.setBounds(485, 440, 90, 25);
    	f.getContentPane().add(SixthOrder);

    	JButton uscita = new JButton("Uscita");
    	uscita.setBounds(10, 465, 90, 25);
    	f.getContentPane().add(uscita);
    	uscita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				/*ViewController.getController().showMenu();*/
			}
		});
    	f.setVisible(true);
    	}
}
