package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import controller.parameters.Directions;
import controller.parameters.FilePath;
import model.utilities.Pair;
import view.resources.Play;

public class PlayerSprite extends Sprite {
    
    private static Vector2 velocity = new Vector2(), newVelocity = new Vector2();
    private boolean newPlayer = true;
    private Animation left, right, up, down, left_s, right_s, up_s, down_s;
    private TextureAtlas playerAtlas;
    private float animationTime = 0, deltaTime;
    private static Directions direction;
    private int pos = 0;
    private static Pair<Float, Float> position;
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
        direction = Directions.STILL;
        setSize(16, 16);
        this.setupAnimation();
    }
    
    public void update(SpriteBatch spriteBatch) {
        move(Gdx.graphics.getDeltaTime(), direction);
        updatePosition();
        super.draw(spriteBatch);
    }
    
    private void updatePosition() {
        PlayerSprite.position = new Pair<>(super.getX(),super.getY());
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
    
    public void setOrientation(Directions d) {
        switch (d) {
        case UP:
            direction = d;
            setRegion(up_s.getKeyFrame(animationTime));
            break;
        case DOWN:
            direction = d;
            setRegion(down_s.getKeyFrame(animationTime));
            break;
        case LEFT:
            direction = d;
            setRegion(left_s.getKeyFrame(animationTime));
            break;
        case RIGHT:
            direction = d;
            setRegion(right_s.getKeyFrame(animationTime));
            break;
        case STILL:
            break;
        }
    }
    
    public void move(float delta, Directions direction) {
        deltaTime = delta;
        switch (direction) {
        case UP:
            if (velocity.y > 0) {
                setRegion(up.getKeyFrame(animationTime));
                super.setY(super.getY() + velocity.y);
                animationTime += deltaTime;
                pos ++;
                if (pos == 8) {
                    velocity.y = newVelocity.y;
                    pos = 0;
                }
            }
            break;
        case DOWN:
            if (velocity.y < 0) {
                setRegion(down.getKeyFrame(animationTime));
                super.setY(super.getY() + velocity.y);
                animationTime += deltaTime;
                pos ++;
                if (pos == 8) {
                    velocity.y = newVelocity.y;
                    pos = 0;
                }
            }
            break;
        case LEFT:
            if (velocity.x < 0) {
                setRegion(left.getKeyFrame(animationTime));
                super.setX(super.getX() + velocity.x);
                animationTime += deltaTime;
                pos ++;
                if (pos == 8) {
                    velocity.x = newVelocity.x;
                    pos = 0;
                }
            }
            break;
        case RIGHT:
            if (velocity.x > 0) {
                setRegion(right.getKeyFrame(animationTime));
                super.setX(super.getX() + velocity.x);            
                animationTime += deltaTime;
                pos ++;
                if (pos == 8) {
                    velocity.x = newVelocity.x;
                    pos = 0;
                }
            }
            break;
        case STILL:
            if (newPlayer) {
                setOrientation(Directions.DOWN);
                newPlayer = false;
            }
            break;
        }
    }
    
    public void setDirection(Directions d) {
        switch(d) {
        case UP:
            if (velocity.x == velocity.y && velocity.x == 0) {
                velocity.y = 2;
                velocity.x = 0;
            } else {
                newVelocity.y = 2;
                newVelocity.x = 0;
            }
            direction = d;
            break;
        case DOWN:
            if (velocity.x == velocity.y && velocity.x == 0) {
                velocity.y = -2;
                velocity.x = 0;
            } else {
                newVelocity.y = -2;
                newVelocity.x = 0;
            }
            direction = d;
            break;
        case LEFT:
            if (velocity.x == velocity.y && velocity.x == 0) {
                velocity.y = 0;
                velocity.x = -2;
            } else {
                newVelocity.y = 0;
                newVelocity.x = -2;
            }
            direction = d;
            break;
        case RIGHT:
            if (velocity.x == velocity.y && velocity.x == 0) {
                velocity.y = 0;
                velocity.x = 2;
            } else {
                newVelocity.y = 0;
                newVelocity.x = 2;
            }
            direction = d;
            break;
        case STILL:
            break;
        }
    }
    
    public static Pair<Float, Float> getPosition() {
        return position;
    }

    public void setPlayerPosition(float x, float y) {
        super.setX(x);
        super.setY(y);
    }
    
    public void stop() {
        newVelocity.x = 0;
        newVelocity.y = 0;
    }
} 
