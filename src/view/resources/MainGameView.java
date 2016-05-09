package view.resources;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import controller.Controller;
import controller.parameters.FilePath;
import controller.parameters.State;
import model.items.Pokeball.PokeballType;
import model.items.Potion.PotionType;
import model.player.PlayerImpl;
import model.pokemon.Stat;
import view.sprite.PlayerSprite;

public class MainGameView implements Screen {  
	
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private int[] background = new int[] {0}, foreground = new int[] {1};
    private ShapeRenderer sr;
    private boolean newGame;
    private PlayerSprite pls;
    private static Sprite sp;
	
    public MainGameView(boolean b) {
        newGame = b;    
    }
	
    public void render(float delta) {		
	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);                                       
        camera.position.set(pls.getX() + pls.getWidth() / 2, pls.getY() + pls.getHeight() / 2, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render(background);
        renderer.getBatch().begin();                      
        pls.update((SpriteBatch)renderer.getBatch());
        renderer.getBatch().end();
        renderer.render(foreground); 
    } 
		
    public void resize(int width, int height) {		
        camera.viewportWidth = width / 2.5f;
        camera.viewportHeight = height / 2.5f;
    }
	
    public void show() {	
        try {
            Controller.getController().initializeModel(new TmxMapLoader().load(FilePath.MAP.getAbsolutePath()));
        } catch (Exception e) {
            Controller.getController().initializeModel(new TmxMapLoader().load(Controller.class.getClass().getResource(FilePath.MAP.getResourcePath()).getPath()));
        }
	Controller.getController().initializeMusicController();
        Controller.getController().updateStatus(State.WALKING);
	// Init the Inventory
	if (newGame) {
	    Map<String, Integer> potionList = new HashMap<>();
	        Map<String, Integer> boostList = new HashMap<>();
	        Map<String, Integer> ballList = new HashMap<>();
	        potionList.put(PotionType.POTION.name(), 10);
	        potionList.put(PotionType.SUPERPOTION.name(), 2);
	        potionList.put(PotionType.HYPERPOTION.name(), 2);
	        boostList.put(Stat.SPD.name() + "X", 2);
	        boostList.put(Stat.DEF.name() + "X", 2);
	        boostList.put(Stat.ATK.name() + "X", 2);
	        ballList.put(PokeballType.Greatball.name(), 2);
	        ballList.put(PokeballType.Ultraball.name(), 100);
	        ballList.put(PokeballType.Pokeball.name(), 10);     
	        Controller.getController().getModel().getPlayer().getInventory().initializeInventory(potionList, boostList, ballList);
	}
	// End Init
        renderer = new OrthogonalTiledMapRenderer(Controller.getController().getMap());                    
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
	sp = new Sprite(gain);		
	pls = PlayerSprite.getSprite();
	if (newGame) {
	    //TODO Magic numbers... c'è un metodo nella mappa
	    pls.setBounds(PlayerImpl.START_X > 0 ? PlayerImpl.START_X * 16  : PlayerImpl.DEFAULT_START_X * 16, PlayerImpl.START_Y > 0 ? (299 - PlayerImpl.START_Y) * 16  : (299 -PlayerImpl.DEFAULT_START_Y) * 16, 15.9f, 15.9f);
	    if (PlayerImpl.START_X < 0) {
	        System.out.println("Initial Position not found");
	    } 
	} else {
	    if (Controller.getController().saveExists()) {
		Controller.getController().load();
	        pls.setBounds(Controller.getController().getPlayer().getTileX()*16, (299 - Controller.getController().getPlayer().getTileY()) * 16, 15.9f, 15.9f);
	    } else {
		pls.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		if (PlayerImpl.START_X < 0) {
		    System.out.println("Initial Position not found");
		}
		pls.setPosition(PlayerImpl.START_X > 0 ? PlayerImpl.START_X * 16 : PlayerImpl.DEFAULT_START_X * 16, PlayerImpl.START_Y > 0 ? (299 - PlayerImpl.START_Y) * 16 : (299 - PlayerImpl.DEFAULT_START_Y) * 16);
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
	Controller.getController().getMap().dispose();
	renderer.dispose();
	sr.dispose();
    }
	
    public static void updateKeyListener() {
        Gdx.input.setInputProcessor(Controller.getController().getStatusController().getCurrentController());
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