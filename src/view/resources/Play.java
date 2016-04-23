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
import controller.MainController;
import controller.load.LoadController;
import controller.parameters.FilePath;
import model.map.PokeMapImpl;
import model.map.Position;
import model.player.PlayerImpl;
import model.resources.Player;

public class Play implements Screen {  
        private boolean pause = false;
        private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private int[] background = new int[] {0}, foreground = new int[] {1};
	private ShapeRenderer sr;
	private boolean newGame;
	private static PokeMapImpl pm;
	
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
	            e.printStackTrace();
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
		    e.printStackTrace();
		    tx = new Texture(this.getClass().getResource(FilePath.PLAYER.getResourcePath()).getPath());
		}
		TextureRegion gain = new TextureRegion(tx);
		TiledMapTileLayer fg = (TiledMapTileLayer) map.getLayers().get("foreground");
		TiledMapTileLayer bg = (TiledMapTileLayer) map.getLayers().get("background");
		MapLayer obj = map.getLayers().get("doorLayer");
		Sprite sp = new Sprite(gain);		
		player = new Player(sp, bg, fg, obj, map.getTileSets().getTile(322));
		pm = new PokeMapImpl(map);
		if (newGame) {
		    player.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		} else {
		    if (LoadController.getController().saveExists()) {
		        LoadController.getController().load(null);
	                player.setBounds(PlayerImpl.getPlayer().getTileX(), PlayerImpl.getPlayer().getTileY(), 15.9f, 15.9f);
	                System.out.println("Benvenuto " + PlayerImpl.getPlayer().getName());
		    } else {
		        System.out.println("SAVE DOES NOT EXIST");
		        player.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		    }
		}
		
		System.out.println(new Position(47,85) + " "  +  pm.getTileType(47,85) + " " + pm.getEncounterZone(47,85).get().getAvailablePokemon());
		System.out.println(new Position(31,112) + " "  +  pm.getTileType(31,112) + " "  + pm.getEncounterZone(31, 112).get().getAvailablePokemon());
		System.out.println(new Position(147,52) + " "  +  pm.getTileType(147,52) + " "  + pm.getEncounterZone(147, 52).get().getAvailablePokemon());
		System.out.println(new Position(14,24) + " "  +  pm.getTileType(14,24) + " "  + pm.getEncounterZone(14, 24).get().getAvailablePokemon());
		System.out.println(new Position(41,159) + " "  +  pm.getTileType(41,159) + " "  + pm.getEncounterZone(41, 159).get().getAvailablePokemon());
		System.out.println(new Position(15,282) + " "  +  pm.getTileType(15,282) + " "  + pm.getEncounterZone(15, 282).get().getAvailablePokemon());
		System.out.println(new Position(67,43) + " "  +  pm.getTileType(67,43) + " "  + pm.getEncounterZone(67, 43).get().getAvailablePokemon());
		System.out.println(new Position(175,21) + " "  +  pm.getTileType(175,21) + " "  + pm.getEncounterZone(175, 21).get().getAvailablePokemon());
		System.out.println(new Position(195,91) + " "  +  pm.getTileType(195,91) + " "  + pm.getEncounterZone(195, 91).get().getAvailablePokemon());
		System.out.println(new Position(58,289) + " "  +  pm.getTileType(58,289) + " "  + pm.getEncounterZone(58,289).get().getAvailablePokemon());
		System.out.println(new Position(27,173) + " "  +  pm.getTileType(27,173) + " "  + pm.getSign(27, 173).get());
		System.out.println(pm.getSigns());
 		
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
	    player.setPlayerPosition(x, y);
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
}