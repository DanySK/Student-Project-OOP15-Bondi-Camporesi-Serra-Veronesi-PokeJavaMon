package view.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import controller.keyboard.KeyboardControllerInterface;
import controller.load.LoadController;
import controller.parameters.State;
import model.resources.General;
import model.resources.Player;

public class Play implements Screen {  
        private boolean pause = false;
        private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private int[] background = new int[] {0}, foreground = new int[] {1};
	private ShapeRenderer sr;
	private KeyboardControllerInterface k;
	private boolean newGame;
	
	public Play(boolean b) {
	    newGame = b;
	}
	
	public void render(float delta) {		
	        if (!pause) {	            
	                Gdx.gl.glClearColor(0, 0, 0, 1);
	                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);                	                
	                camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
	                camera.update();
	                renderer.setView(camera);
	                renderer.render(background);
	                renderer.getSpriteBatch().begin();	                
	                player.update(renderer.getSpriteBatch());	                
	                renderer.getSpriteBatch().end();
	                renderer.render(foreground);        
	        }
	} 
		
	public void resize(int width, int height) {		
	        camera.viewportWidth = width / 2.5f;
		camera.viewportHeight = height / 2.5f;
	}
	
	public void show() {			    
	        map = new TmxMapLoader().load(this.getClass().getResource("/map.tmx").getPath());
	        renderer = new OrthogonalTiledMapRenderer(map);                    
		sr = new ShapeRenderer();
		sr.setColor(Color.CYAN);
		Gdx.gl.glLineWidth(3);
		camera = new OrthographicCamera();	
		Texture tx = new Texture(this.getClass().getResource("/player.png").getPath());
		TextureRegion gain = new TextureRegion(tx);
		TiledMapTileLayer fg = (TiledMapTileLayer) map.getLayers().get("foreground");
		TiledMapTileLayer bg = (TiledMapTileLayer) map.getLayers().get("background");
		MapLayer obj = map.getLayers().get("obj");
		Sprite sp = new Sprite(gain);		
		player = new Player(sp, bg, fg, obj, map.getTileSets().getTile(322));
		if (newGame) {
		    player.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		} else {
		    LoadController lc = new LoadController();
		    if (lc.saveExists()) {
		        General g = lc.load();
	                player.setBounds(g.getPosition().getX(), g.getPosition().getY(), 15.9f, 15.9f);
		    } else {
		        System.out.println("SAVE DOES NOT EXIST");
		        player.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		    }
		}
		k = State.WALKING.getController();
		k.setPlayer(player);
		Gdx.input.setInputProcessor(k);
		k.start();
	}

	public void hide() {		
	    dispose();
	}

	public void pause() {	    
	    pause = true;
	}

	public void resume() {	    
	    pause = false;
	}
	
	public float getXPosition() {    
	    return player.getX();
	}
	
	public float getYPosition() {	    
	    return player.getY();
	}
	
	public void setPosition(float x, float y) {	    
	    player.setPos(x, y);
	}
	
	public void dispose() {        
	        map.dispose();
		renderer.dispose();
		sr.dispose();
	}
}