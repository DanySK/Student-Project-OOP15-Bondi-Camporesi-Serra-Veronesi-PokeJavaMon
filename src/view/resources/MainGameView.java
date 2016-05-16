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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import controller.MainController;
import controller.parameters.Img;
import controller.parameters.Maps;
import controller.parameters.State;
import exceptions.SquadFullException;
import view.sprite.PlayerSprite;

public class MainGameView implements Screen {  
	
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private int[] background = new int[] {0}, foreground = new int[] {1};
    private ShapeRenderer sr;
    private boolean newGame;
    private PlayerSprite pls;
    private static Sprite sp;
    private Texture tx;
    private static final int START_X = MainController.getController().getInitialPosition().getX();
    private static final int START_Y = MainController.getController().getInitialPosition().getY();
    private static final int DEFAULT_START_X = MainController.getController().getDefaultInitialPosition().getX();
    private static final int DEFAULT_START_Y = MainController.getController().getDefaultInitialPosition().getY();
    
    public MainGameView(boolean b) {
        newGame = b;    
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
		
    public void resize(int width, int height) {		
        this.camera.viewportWidth = width / 2.5f;
        this.camera.viewportHeight = height / 2.5f;
    }
	
    public void show() {	
        try {
            MainController.getController().initializeModel(new TmxMapLoader().load(Maps.MAP.getAbsolutePath()));
        } catch (Exception e) {
            MainController.getController().initializeModel(new TmxMapLoader().load(MainController.class.getClass().getResource(Maps.MAP.getResourcePath()).getPath()));
        }
        MainController.getController().getViewController().initName();
	MainController.getController().initializeMusicController();
        MainController.getController().updateStatus(State.WALKING);
	if (this.newGame) {
	    try {
                MainController.getController().initializeStarter();
            } catch (SquadFullException e) {
                System.out.println("FAILED INITIALIZING STARTER POKEMON");
            }
	    MainController.getController().initInventory();
	}
	this.renderer = new OrthogonalTiledMapRenderer(MainController.getController().getMap());                    
        this.sr = new ShapeRenderer();
	this.sr.setColor(Color.CYAN);
	Gdx.gl.glLineWidth(3);
	this.camera = new OrthographicCamera();	
	try {
	    this.tx = new Texture(Img.PLAYER.getAbsolutePath());
	} catch (Exception e) {
	    this.tx = new Texture(this.getClass().getResource(Img.PLAYER.getResourcePath()).getPath());
	}
	TextureRegion gain = new TextureRegion(tx);
	sp = new Sprite(gain);		
	this.pls = PlayerSprite.getSprite();
	if (newGame) {
	    //TODO Magic numbers... c'è un metodo nella mappa
	    this.pls.setBounds(START_X > 0 ? START_X * 16  : DEFAULT_START_X * 16, START_Y > 0 ? (299 - START_Y) * 16  : (299 - DEFAULT_START_Y) * 16, 15.9f, 15.9f);
	    if (START_X < 0) {
	        System.out.println("Initial Position not found");
	    } 
	} else {
	    if (MainController.getController().saveExists()) {
		MainController.getController().load();
		this.pls.setBounds(MainController.getController().getPlayer().getTileX()*16, (299 - MainController.getController().getPlayer().getTileY()) * 16, 15.9f, 15.9f);
	    } else {
	        this.pls.setBounds(28*16, (299 - 177) * 16, 15.9f, 15.9f);
		if (START_X < 0) {
		    System.out.println("Initial Position not found");
		}
		this.pls.setPosition(START_X > 0 ? START_X * 16 : DEFAULT_START_X * 16, START_Y > 0 ? (299 - START_Y) * 16 : (299 - DEFAULT_START_Y) * 16);
	    }
	}	        
    }

    public void hide() {		
        this.dispose();
    }

    public float getXPosition() {    
	return this.pls.getX();
    }
	
    public float getYPosition() {	    
	return this.pls.getY();
    }
	
    public void setPosition(float x, float y) {	    
        this.pls.setPlayerPosition(x, y);
    }
	
    public void dispose() {        
	MainController.getController().getMap().dispose();
	this.renderer.dispose();
	this.sr.dispose();
    }
	
    public static void updateKeyListener() {
        Gdx.input.setInputProcessor(MainController.getController().getStatusController().getCurrentController());
    }
	
    public static Sprite getSprite() {
        return sp;
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