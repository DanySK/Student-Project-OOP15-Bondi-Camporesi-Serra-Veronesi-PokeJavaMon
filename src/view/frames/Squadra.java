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
   	
    	JTextArea FirstName = new JTextArea();
    	FirstName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	FirstName.setOpaque(false);
    	FirstName.setText("Feraligatr");
    	FirstName.setBounds(80, 5, 95, 25);
    	f.getContentPane().add(FirstName);
    	
    	JTextArea FirstLv = new JTextArea();
    	FirstLv.setOpaque(false);
    	FirstLv.setText("Lvl 100");
    	FirstLv.setBounds(250, 5, 70, 20);
    	f.getContentPane().add(FirstLv);
    	
    	JProgressBar FirstHPbar = new JProgressBar();
    	FirstHPbar.setBounds(380, 5, 200, 22);
    	f.getContentPane().add(FirstHPbar);
    	
    	JButton FirstStats = new JButton("Stats");
    	FirstStats.setBounds(380, 35, 90, 25);
    	f.getContentPane().add(FirstStats);
    	FirstStats.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    					f.dispose();
    					ViewController.getController().stats();
    			}
    	});
    	
    	JButton FirstOrder = new JButton("Ordina");
    	FirstOrder.setBounds(491, 35, 90, 25);
    	f.getContentPane().add(FirstOrder);
    	
    	JTextArea SecondName = new JTextArea();
    	SecondName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	SecondName.setOpaque(false);
    	SecondName.setText("Aerodactyl");
    	SecondName.setBounds(80, 80, 95, 25);
    	f.getContentPane().add(SecondName);
    	
    	JTextArea SecondLv = new JTextArea();
    	SecondLv.setOpaque(false);
    	SecondLv.setText("Lvl 100");
    	SecondLv.setBounds(250, 80, 70, 25);
    	f.getContentPane().add(SecondLv);
    	
    	JProgressBar SecondHPbar = new JProgressBar();
    	SecondHPbar.setBounds(380, 80, 200, 22);
    	f.getContentPane().add(SecondHPbar);
    	
    	JButton SecondStats = new JButton("Stats");
    	SecondStats.setBounds(380, 110, 90, 25);
    	f.getContentPane().add(SecondStats);
    	
    	JButton SecondOrder = new JButton("Ordina");
    	SecondOrder.setBounds(491, 110, 90, 25);
    	f.getContentPane().add(SecondOrder);
    	
    	JTextArea ThirdName = new JTextArea();
    	ThirdName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	ThirdName.setOpaque(false);
    	ThirdName.setText("Pikachu");
    	ThirdName.setBounds(80, 155, 95, 25);
    	f.getContentPane().add(ThirdName);
    	
    	JTextArea ThirdLv = new JTextArea();
    	ThirdLv.setOpaque(false);
    	ThirdLv.setText("Lvl 100");
    	ThirdLv.setBounds(250, 155, 70, 20);
    	f.getContentPane().add(ThirdLv);
    	
    	JProgressBar ThirdHPbar = new JProgressBar();
    	ThirdHPbar.setBounds(380, 155, 200, 22);
    	f.getContentPane().add(ThirdHPbar);
    	
    	JButton ThirdStats = new JButton("Stats");
    	ThirdStats.setBounds(380, 185, 90, 25);
    	f.getContentPane().add(ThirdStats);
    	
    	JButton ThirdOrder = new JButton("Ordina");
    	ThirdOrder.setBounds(491, 185, 90, 25);
    	f.getContentPane().add(ThirdOrder);
    	
    	JTextArea FourthName = new JTextArea();
    	FourthName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	FourthName.setOpaque(false);
    	FourthName.setText("Rayquaza");
    	FourthName.setBounds(80, 230, 95, 25);
    	f.getContentPane().add(FourthName);
    	
    	JTextArea FourthLv = new JTextArea();
    	FourthLv.setOpaque(false);
    	FourthLv.setText("Lvl 100");
    	FourthLv.setBounds(250, 230, 70, 20);
    	f.getContentPane().add(FourthLv);
    	
    	JProgressBar FourthHPbar = new JProgressBar();
    	FourthHPbar.setBounds(380, 230, 200, 22);
    	f.getContentPane().add(FourthHPbar);
    	
    	JButton FourthStats = new JButton("Stats");
    	FourthStats.setBounds(380, 260, 90, 25);
    	f.getContentPane().add(FourthStats);
    	
    	JButton FourthOrder = new JButton("Ordina");
    	FourthOrder.setBounds(491, 260, 90, 25);
    	f.getContentPane().add(FourthOrder);
    	
    	JTextArea FifthName = new JTextArea();
    	FifthName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	FifthName.setOpaque(false);
    	FifthName.setText("Tentacool");
    	FifthName.setBounds(80, 305, 95, 25);
    	f.getContentPane().add(FifthName);
    	
    	JTextArea FifthLv = new JTextArea();
    	FifthLv.setOpaque(false);
    	FifthLv.setText("Lvl 100");
    	FifthLv.setBounds(250, 305, 70, 20);
    	f.getContentPane().add(FifthLv);
    	
    	JProgressBar FifthHPbar = new JProgressBar();
    	FifthHPbar.setBounds(380, 305, 200, 22);
    	f.getContentPane().add(FifthHPbar);
    	
    	JButton FifthStats = new JButton("Stats");
    	FifthStats.setBounds(380, 335, 90, 25);
    	f.getContentPane().add(FifthStats);
    	
    	JButton FifthOrder = new JButton("Ordina");
    	FifthOrder.setBounds(491, 335, 90, 25);
    	f.getContentPane().add(FifthOrder);
    	
    	JTextArea SixthName = new JTextArea();
    	SixthName.setFont(new Font("DokChampa", Font.BOLD, 16));
    	SixthName.setOpaque(false);
    	SixthName.setText("Magikarp");
    	SixthName.setBounds(80, 380, 95, 25);
    	f.getContentPane().add(SixthName);
    	
    	JTextArea SixthLv = new JTextArea();
    	SixthLv.setOpaque(false);
    	SixthLv.setText("Lvl 100");
    	SixthLv.setBounds(250, 380, 70, 20);
    	f.getContentPane().add(SixthLv);
    	
    	JProgressBar SixthHPbar = new JProgressBar();
    	SixthHPbar.setBounds(380, 380, 200, 22);
    	f.getContentPane().add(SixthHPbar);
    	
    	JButton SixthStats = new JButton("Stats");
    	SixthStats.setBounds(380, 410, 90, 25);
    	f.getContentPane().add(SixthStats);
    	
    	JButton SixthOrder = new JButton("Ordina");
    	SixthOrder.setBounds(491, 410, 90, 25);
    	f.getContentPane().add(SixthOrder);

    	JButton uscita = new JButton("Uscita");
    	uscita.setBounds(10, 425, 90, 25);
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