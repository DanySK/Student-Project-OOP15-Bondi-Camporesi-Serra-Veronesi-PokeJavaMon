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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import controller.MainController;
import controller.load.LoadController;
import controller.parameters.FilePath;
import model.map.PokeMapImpl;
import model.player.PlayerImpl;
import view.PlayerSprite;

public class Play implements Screen {  
        private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private int[] background = new int[] {0}, foreground = new int[] {1};
	private ShapeRenderer sr;
	private boolean newGame;
	private static PokeMapImpl pm;
	private PlayerSprite pls;
	private static Sprite sp;
	
	public Play(boolean b) {
        newGame = b;
	}
	
	public void render(float delta) {		
	    Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);                                       
            camera.position.set(pls.getX() + pls.getWidth() / 2, pls.getY() + pls.getHeight() / 2, 0);
            camera.update();
            renderer.setView(camera);
            renderer.render(background);
            renderer.getSpriteBatch().begin();                      
            pls.update(renderer.getSpriteBatch());
            renderer.getSpriteBatch().end();
            renderer.render(foreground); 
	} 
	
	public static PokeMapImpl getMapImpl() {
	    return pm;
	}
		
	public void resize(int width, int height) {		
	        camera.viewportWidth = width / 2.5f;
		camera.viewportHeight = height / 2.5f;
	}
	
	public void show() {			
	        try {
	            map = new TmxMapLoader().load(FilePath.MAP.getAbsolutePath());
	        } catch (Exception e) {
	            map = new TmxMapLoader().load(this.getClass().getResource(FilePath.MAP.getResourcePath()).getPath());
	        }
	        renderer = new OrthogonalTiledMapRenderer(map);                    
		sr = new ShapeRenderer();
		sr.setColor(Color.CYAN);
		Gdx.gl.glLineWidth(3);
		camera = new OrthographicCamera();	
		Texture tx;
		try {
		    tx = new Texture(FilePath.PLAYER.getAbsolutePath());
		} catch (Exception e) {
		    tx = new Texture(this.getClass().getResource(FilePath.PLAYER.getResourcePath()).getPath());
		}
		TextureRegion gain = new TextureRegion(tx);
		map.getLayers().get("foreground");
		map.getLayers().get("background");
		map.getLayers().get("doorLayer");
		sp = new Sprite(gain);		
		pls = PlayerSprite.getSprite();
		pm = new PokeMapImpl(map);
		if (newGame) {
		    pls.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		    pls.setPosition(28*16, (299 - 177) * 16);
		} else {
		    if (LoadController.getController().saveExists()) {
		        LoadController.getController().load(null);
	                pls.setBounds(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY(), 15.9f, 15.9f);
	                pls.setPosition(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY());
	            } else {
		        pls.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		        pls.setPosition(28*16, (299 - 177) * 16);
		    }
		}
				
	}

	public void hide() {		
	    dispose();
	}

	public float getXPosition() {    
	    return pls.getX();
	}
	
	public float getYPosition() {	    
	    return pls.getY();
	}
	
	public void setPosition(float x, float y) {	    
	    pls.setPlayerPosition(x, y);
	}
	
	public void dispose() {        
	        map.dispose();
		renderer.dispose();
		sr.dispose();
	}
	
	public static void updateKeyListener() {
	    Gdx.input.setInputProcessor(MainController.getController().getCurrentController());
	}
	
	public TiledMap getMap() {
	    try {
                map = new TmxMapLoader().load(FilePath.MAP.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                map = new TmxMapLoader().load(this.getClass().getResource(FilePath.MAP.getResourcePath()).getPath());
            }
            return this.map;
	}
	
	public static Sprite getSprite() {
	    return sp;
	}

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}