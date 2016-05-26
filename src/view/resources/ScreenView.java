package view.resources;

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
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import controller.MainController;
import controller.parameters.Img;
import controller.parameters.Maps;
import controller.parameters.State;
import exceptions.SquadFullException;
import model.map.Position;
import view.sprite.PlayerSprite;
/**
 * MainGameViewClass
 * 
 * @author Daniel Veronesi
 */
public class ScreenView implements Screen {  
	/**
	 * renderer
	 */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * camera
     */
    private OrthographicCamera camera;
    /**
     * background&foreground
     */
    private int[] background = new int[] {0}, foreground = new int[] {1};
    /**
     * sr
     */
    private ShapeRenderer sr;
    /**
     * newGame
     */
    private boolean newGame;
    /**
     * toDispose
     */
    private boolean toDispose;
    /**
     * pls
     */
    private PlayerSprite pls;
    /**
     * sp
     */
    private static Sprite sp;
    /**
     * tx
     */
    private Texture tx;
    /**
	 * ScreenView
	 */

    private static final float VIEWPORT_X = 2.5f;
    private static final float VIEWPORT_Y = 2.5f;
    
    
    public ScreenView(boolean b) {
        this.newGame = b;    
        this.toDispose = true;
    }
	
    public void render(float delta) {		
	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);                                       
        this.camera.position.set(this.pls.getX() + this.pls.getWidth() / 2, this.pls.getY() + this.pls.getHeight() / 2, 0);
        this.camera.update();
        this.renderer.setView(this.camera);
        this.renderer.render(this.background);
        this.renderer.getBatch().begin();                      
        this.pls.update((SpriteBatch)this.renderer.getBatch());
        this.renderer.getBatch().end();
        this.renderer.render(this.foreground); 
    } 
	/**
	 * resize
	 */	
    public void resize(int width, int height) {		
        this.camera.viewportWidth = width / VIEWPORT_X;
        this.camera.viewportHeight = height / VIEWPORT_Y;
    }
	
    public void show() {	
        final LoadingScreen ls = new LoadingScreen();
        ls.showLoadingScreen();
        try {
            MainController.getController().initializeModel(new TmxMapLoader().load(Maps.MAP.getAbsolutePath()));
        } catch (Exception e) {
            MainController.getController().initializeModel(new TmxMapLoader().load(MainController.class.getClass().getResource(Maps.MAP.getResourcePath()).getPath()));
        }
        MainController.getController().getViewController().initName();
	MainController.getController().initializeMusicController();
	if (toDispose) {
	    ls.disposeWindow();
	    this.toDispose = false;
	}
        MainController.getController().updateStatus(State.WALKING);
	if (this.newGame) {
	    try {
                MainController.getController().initializeStarter();
            } catch (SquadFullException e) {
                System.out.println("FAILED INITIALIZING STARTER POKEMON");
            }
	    MainController.getController().initInventory();
	}
	this.renderer = new OrthogonalTiledMapRenderer(MainController.getController().getMap().get());                    
        this.sr = new ShapeRenderer();
	this.sr.setColor(Color.CYAN);
	Gdx.gl.glLineWidth(3);
	this.camera = new OrthographicCamera();	
	try {
	    this.tx = new Texture(Img.PLAYER.getAbsolutePath());
	} catch (Exception e) {
	    this.tx = new Texture(this.getClass().getResource(Img.PLAYER.getResourcePath()).getPath());
	}
	final TextureRegion gain = new TextureRegion(tx);
	sp = new Sprite(gain);
        this.pls = PlayerSprite.getSprite();
        this.setInitialPosition(newGame);
	        
    }

    public void hide() {		
        this.dispose();
    }
	/**
	 * getXPosition
	 */
    public float getXPosition() {    
	return this.pls.getX();
    }
	/**
	 * getYPosition
	 */
    public float getYPosition() {	    
	return this.pls.getY();
    }
	/**
	 * setPosition
	 */
    public void setPosition(float x, float y) {	    
        this.pls.setPlayerPosition(x, y);
    }
	
    public void dispose() {        
	MainController.getController().getMap().get().dispose();
	this.renderer.dispose();
	this.sr.dispose();
    }
	/**
	 * updateKeyListener
	 */
    public static void updateKeyListener() {
        Gdx.input.setInputProcessor(MainController.getController().getStatusController().getCurrentController());
    }
	/**
	 * getSprite
	 */
    public static Sprite getSprite() {
        return sp;
    }

    private void setInitialPosition(final boolean isNewGame) {
        Position p;
        if (isNewGame) {
            p = MainController.getController().getDefaultInitialPosition();
        } else {
            if (MainController.getController().saveExists()) {
                MainController.getController().load();
                p = MainController.getController().getInitialPosition();
            } else {
                throw new IllegalStateException("Cannot continue without a save file");
            }
        } 
        float mapHeight = -1;
        float tileWidth = -1;
        float tileHeight = -1;
        
        if (MainController.getController().getMap().isPresent()) {
            final MapProperties prop = MainController.getController().getMap().get().getProperties();
            mapHeight = (float) prop.get("height", Integer.class);
            tileWidth = (float) prop.get("tilewidth", Integer.class);
            tileHeight = (float) prop.get("tileheight", Integer.class);
        }
        
        this.pls.setBounds(p.getX() * tileWidth, (mapHeight - 1 - p.getY()) * tileHeight, PlayerSprite.getSprite().getWidth(), PlayerSprite.getSprite().getHeight());
    }
    
    @Override
    public void pause() {
        // EMPTY METHOD
    }

    @Override
    public void resume() {
        // EMPTY METHOD
    }
}