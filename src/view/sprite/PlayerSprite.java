package view.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import controller.Controller;
import controller.parameters.FilePath;
import model.map.Drawable.Direction;
import model.utilities.Pair;
import view.resources.Play;

public class PlayerSprite extends Sprite {
    
    private Vector2 velocity = new Vector2();
    private Animation left, right, up, down, left_s, right_s, up_s, down_s;
    private TextureAtlas playerAtlas;
    private float animationTime = 0;
    private int pos = 0;
    private Pair<Float, Float> position;
    private static PlayerSprite SINGLETON;
    
    public static PlayerSprite getSprite() {
        if (SINGLETON == null) {
            synchronized (PlayerSprite.class) {
                if (SINGLETON == null) {
                    SINGLETON = new PlayerSprite(Play.getSprite());
                }
            }
        }
        return SINGLETON;
    }

    public PlayerSprite(Sprite st) {
        super(st);
        super.setSize(16, 16);
        this.setupAnimation();
    }
    
    public void update(SpriteBatch spriteBatch) {
        if (pos == 0) {
            Controller.getController().getStatusController().updateSpeed();
            if (velocity.x == 0 && velocity.y == 0) {
                setOrientation(Controller.getController().getStatusController().getDirection());
            } else {
                move();
            }
        } else {
            move();
        }
        updatePosition();
        super.draw(spriteBatch); 
    }
    
    public void updatePosition() {
        this.position = new Pair<>(super.getX(),super.getY());
    }
    
    public boolean isMoving() {
        return (velocity.x != 0 || velocity.y != 0);
    }

    private void setupAnimation() {
        try {
            playerAtlas = new TextureAtlas(FilePath.PACK.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            playerAtlas = new TextureAtlas(this.getClass().getResource(FilePath.PACK.getResourcePath()).getPath());
        }
        this.left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
        this.right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
        this.up = new Animation(1 / 6f, playerAtlas.findRegions("up"));
        this.down = new Animation(1 / 6f, playerAtlas.findRegions("down"));
        this.left_s = new Animation(1 / 6f, playerAtlas.findRegions("left_still"));
        this.right_s = new Animation(1 / 6f, playerAtlas.findRegions("right_still"));
        this.up_s = new Animation(1 / 6f, playerAtlas.findRegions("up_still"));
        this.down_s = new Animation(1 / 6f, playerAtlas.findRegions("down_still"));
        this.left.setPlayMode(Animation.LOOP);
        this.right.setPlayMode(Animation.LOOP);
        this.up.setPlayMode(Animation.LOOP);
        this.down.setPlayMode(Animation.LOOP);
        this.left_s.setPlayMode(Animation.LOOP);
        this.right_s.setPlayMode(Animation.LOOP);
        this.up_s.setPlayMode(Animation.LOOP);
        this.down_s.setPlayMode(Animation.LOOP);
        setRegion(down_s.getKeyFrame(animationTime));
    }
    
    public Pair<Float, Float> getPosition() {
        return position;
    }

    public void setPlayerPosition(float x, float y) {
        super.setX(x * 16);
        super.setY((299 - y) * 16);
    }
    
    private void setOrientation(Direction d) {
        switch (d) {
        case NORTH:
            setRegion(up_s.getKeyFrame(animationTime));
            break;
        case SOUTH:
            setRegion(down_s.getKeyFrame(animationTime));
            break;
        case WEST:
            setRegion(left_s.getKeyFrame(animationTime));
            break;
        case EAST:
            setRegion(right_s.getKeyFrame(animationTime));
            break;
        case NONE:
            break;
        }
    }
    
    private void move() {
        if (velocity.x > 0) {
            super.setX(super.getX() + velocity.x);
            setRegion(right.getKeyFrame(animationTime));
        } else if (velocity.x < 0) {
            super.setX(super.getX() + velocity.x);
            setRegion(left.getKeyFrame(animationTime));
        } else if (velocity.y > 0) {
            super.setY(super.getY() + velocity.y);
            setRegion(up.getKeyFrame(animationTime));
        } else if (velocity.y < 0) {
            super.setY(super.getY() + velocity.y);
            setRegion(down.getKeyFrame(animationTime));
        }
        animationTime += Gdx.graphics.getDeltaTime();
        pos ++;
        if (pos == 8) {
            pos = 0;
            Controller.getController().getStatusController().checkEncounter();
            Controller.getController().getStatusController().updateMusic();
        }
    }
    
    public void setVelocity(float x, float y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }
} 
