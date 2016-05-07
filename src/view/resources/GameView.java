package view.resources;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class GameView extends Game {       
        LwjglApplication app;
        boolean keepShowing = true;
        MainGameView pl;      
        
        public GameView(boolean bl) {
            pl = new MainGameView(bl);
        }
        
        public void create() {	        
	    setScreen(pl);
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
	
	public void setApp(LwjglApplication l) {	    
	    app = l;
	}
}
