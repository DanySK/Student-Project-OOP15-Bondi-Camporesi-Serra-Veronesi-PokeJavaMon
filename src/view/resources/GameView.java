package view.resources;

import com.badlogic.gdx.Game;
/**
 * GameViewClass
 * 
 * @author Daniel Veronesi
 */
public class GameView extends Game {       
	/**
	 * MainGameView
	 */
    private MainGameView pl;      
	/**
	 * GameView
	 */
    public GameView(boolean bl) {
        this.pl = new MainGameView(bl);
    }
        
    public void create() {	        
	setScreen(this.pl); 
    }

    public void dispose() {	    
	super.dispose();
    }

    public void render() {	    
	super.render();
    }

    public void resize(int width, int height) {	
	super.resize(width, height);
    }

    public void pause() {		
	super.pause();
    }
	
    public void resume() {	
	super.resume();
    }
}