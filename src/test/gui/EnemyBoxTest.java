package test.gui;

import java.awt.Color;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.files.FileHandle;

import javax.swing.*;

public class EnemyBoxTest extends JWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631613419194945790L;
	private EnemyBoxLabel p = new EnemyBoxLabel();
	
	public EnemyBoxTest() throws IOException {
		super();
		
		JLabel lPkmn = new JLabel("Pokemon");
		lPkmn.setBounds(50, -10	, 100, 50);
		this.add(lPkmn);
		
		HealthBar hb = new HealthBar(40, 100, true);
		hb.setBounds(127, 39, 130, 5);
		this.add(hb);
		
		ExpBar bar = new ExpBar(50, 100);
		bar.setBounds(80, 74, 170, 7);
		bar.setValue(50);
		bar.setStringPainted(false);
		bar.setForeground(Color.CYAN);
		this.add(bar);
		this.add(p);
		this.setBounds(0, 0, 300, 300);
		this.setVisible(true);
		
	}
	

	
	
	public static void main(String[] args) throws IOException {
	        Gdx.files = new LwjglFiles();
		FileHandle f = Gdx.files.absolute(EnemyBoxTest.class.getResource("/gui/ExpBar.png").getFile());
		System.out.println(f.exists());
	}
}
