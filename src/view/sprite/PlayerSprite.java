package view.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import controller.MainController;
import controller.parameters.Img;
import model.map.Drawable.Direction;
import model.utilities.Pair;
import view.resources.ScreenView;
/**
 * PlayerSpriteClass
 * 
 */
public class PlayerSprite extends Sprite {
    private Vector2 velocity;
    private Animation left, right, up, down, left_s, right_s, up_s, down_s;
    private TextureAtlas playerAtlas;
    private float animationTime;
    private int pos;
    private Pair<Float, Float> position;
    private boolean update;
    private static PlayerSprite SINGLETON;
	/**
	 * getSprite
	 */
    public static PlayerSprite getSprite() {
        if (SINGLETON == null) {
            synchronized (PlayerSprite.class) {
                if (SINGLETON == null) {
                    SINGLETON = new PlayerSprite(ScreenView.getSprite());
                }
            }
        }
        return SINGLETON;
    }
    private PlayerSprite(final Sprite st) {
        super(st);
        super.setSize(16, 16);
        this.setupAnimation();
        this.velocity = new Vector2();
        this.animationTime = 0;
        this.pos = 0;
        this.update = true;
    }
	/**
	 * update
	 */
    public void update(final SpriteBatch spriteBatch) {
        if (this.pos == 0) {
            MainController.getController().getStatusController().updateSpeed();
            if (this.velocity.x == 0 && this.velocity.y == 0) {
                setOrientation(MainController.getController().getStatusController().getDirection());
            } else {
                move();
            }
        } else {
            move();
        }
        updatePosition();
        super.draw(spriteBatch); 
        if (this.update) {
            MainController.getController().getStatusController().updateMusic();
            this.update = false;
        }
    }
	/**
	 * updatePosition
	 */
    public void updatePosition() {
        this.position = new Pair<>(super.getX(),super.getY());
    }
	/**
	 * isMoving
	 */
    public boolean isMoving() {
        return (this.velocity.x != 0 || this.velocity.y != 0);
    }
    private void setupAnimation() {
        try {
            this.playerAtlas = new TextureAtlas(Img.PACK.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            this.playerAtlas = new TextureAtlas(this.getClass().getResource(Img.PACK.getResourcePath()).getPath());
        }
        this.left = new Animation(1 / 6f, this.playerAtlas.findRegions("left"));
        this.right = new Animation(1 / 6f, this.playerAtlas.findRegions("right"));
        this.up = new Animation(1 / 6f, this.playerAtlas.findRegions("up"));
        this.down = new Animation(1 / 6f, this.playerAtlas.findRegions("down"));
        this.left_s = new Animation(1 / 6f, this.playerAtlas.findRegions("left_still"));
        this.right_s = new Animation(1 / 6f, this.playerAtlas.findRegions("right_still"));
        this.up_s = new Animation(1 / 6f, this.playerAtlas.findRegions("up_still"));
        this.down_s = new Animation(1 / 6f, this.playerAtlas.findRegions("down_still"));
        this.left.setPlayMode(Animation.PlayMode.LOOP);
        this.right.setPlayMode(Animation.PlayMode.LOOP);
        this.up.setPlayMode(Animation.PlayMode.LOOP);
        this.down.setPlayMode(Animation.PlayMode.LOOP);
        this.left_s.setPlayMode(Animation.PlayMode.LOOP);
        this.right_s.setPlayMode(Animation.PlayMode.LOOP);
        this.up_s.setPlayMode(Animation.PlayMode.LOOP);
        this.down_s.setPlayMode(Animation.PlayMode.LOOP);
        setRegion(down_s.getKeyFrame(animationTime));
    }
	/**
	 * getPosition
	 */
    public Pair<Float, Float> getPosition() {
        return this.position;
    }
	/**
	 * setPlayerPosition
	 * TODO MAGIC NUMBERS, DIPENDONO PER FORZA DALLA MAPPA SE CAMBIO LA MAPPA SI ****...............
	 */
    public void setPlayerPosition(final float x, final float y) {
        super.setX(MainController.getController().getPokeMap().get().getCorrectedCoordinateX((int) x) * MainController.getController().getPokeMap().get().getTileWidth());
        super.setY(MainController.getController().getPokeMap().get().getCorrectedCoordinateY((int) y) * MainController.getController().getPokeMap().get().getTileHeight());
    }
    private void setOrientation(final Direction d) {
        switch (d) {
        case NORTH:
            setRegion(up_s.getKeyFrame(this.animationTime));
            break;
        case SOUTH:
            setRegion(down_s.getKeyFrame(this.animationTime));
            break;
        case WEST:
            setRegion(left_s.getKeyFrame(this.animationTime));
            break;
        case EAST:
            setRegion(right_s.getKeyFrame(this.animationTime));
            break;
        case NONE:
            break;
        }
    }
    private void move() {
        if (this.velocity.x > 0) {
            super.setX(super.getX() + this.velocity.x);
            setRegion(this.right.getKeyFrame(this.animationTime));
        } else if (this.velocity.x < 0) {
            super.setX(super.getX() + this.velocity.x);
            setRegion(this.left.getKeyFrame(this.animationTime));
        } else if (this.velocity.y > 0) {
            super.setY(super.getY() + this.velocity.y);
            setRegion(this.up.getKeyFrame(this.animationTime));
        } else if (this.velocity.y < 0) {
            super.setY(super.getY() + this.velocity.y);
            setRegion(this.down.getKeyFrame(this.animationTime));
        }
        this.animationTime += Gdx.graphics.getDeltaTime();
        this.pos ++;
        if (this.pos == 8) {
            this.pos = 0;
            MainController.getController().getStatusController().checkEncounter();
            MainController.getController().getStatusController().updateMusic();
        }
    }
	/**
	 * setVelocity
	 */
    public void setVelocity(final float x, final float y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }
} 