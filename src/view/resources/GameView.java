package view.resources;

import com.badlogic.gdx.Game;

/**
 * This class handles the creation, the destruction and the pausing of the frame of the game.
 */
public class GameView extends Game {       
	
    private ScreenView pl;      
	
    /**
     * @param bl true if it is a new game
     */
    public GameView(boolean bl) {
        this.pl = new ScreenView(bl);
    }
    
    @Override
    public void create() {
	setScreen(this.pl); 
    }
    
    @Override
    public void dispose() {	    
	super.dispose();
    }
    
    @Override
    public void render() {	    
	super.render();
    }
    
    @Override
    public void resize(int width, int height) {	
	super.resize(width, height);
    }
    
    @Override
    public void pause() {		
	super.pause();
    }
    
    @Override
    public void resume() {	
	super.resume();
    }
}