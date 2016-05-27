package view.resources;

import com.badlogic.gdx.Game;
/**
 * GameViewClass
 * 
 */
public class GameView extends Game {       
	
    private ScreenView pl;      
	/**
	 * GameView
	 */
    public GameView(boolean bl) {
        this.pl = new ScreenView(bl);
    }
    /**
     *     
     */
    public void create() {
	setScreen(this.pl); 
    }
    /**
     * 
     */
    public void dispose() {	    
	super.dispose();
    }
    /**
     * 
     */
    public void render() {	    
	super.render();
    }
    /**
     * 
     */
    public void resize(int width, int height) {	
	super.resize(width, height);
    }
    /**
     * It disabled the map and it allows to navigate through the windows.
     */
    public void pause() {		
	super.pause();
    }
    /**
     * It enables the map again after all the windows are closed.
     */
    public void resume() {	
	super.resume();
    }
}