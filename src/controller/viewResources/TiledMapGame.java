package controller.viewResources;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TiledMapGame extends Game {       
        LwjglApplication app;
        boolean keepShowing = true;
        Play pl;      
        
        public TiledMapGame(boolean bl) {
            pl = new Play(bl);
        }
        
        public void create() {	        
	    setScreen(pl);
	}

	public void dispose() {	    
	    super.dispose();
	}

	public void render() {	    
	    super.render();
	    try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
