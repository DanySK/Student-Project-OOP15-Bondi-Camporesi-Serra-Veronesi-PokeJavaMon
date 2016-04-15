package model.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import controller.MainController;
import controller.parameters.Direction;
import model.utilities.Pair;

public class Player extends Sprite {
        private float oldX = super.getX(), oldY = super.getY(), deltaTime;
        private static float speed = 1;
        private float animationTime = 0;
        private static Vector2 velocity = new Vector2(), newVelocity = new Vector2();
        private TiledMapTileLayer background, foreground;
        private MapLayer objectLayer;
        private static Animation left, right, up, down, left_s, right_s, up_s, down_s;
        private TextureAtlas playerAtlas;
        private static boolean stopMove = false, collisionX = false, collisionY = false, collisionTrainerX = false, collisionTrainerY = false;
        private static int pos = 0;
        private static Pair<Float, Float> position;
	
	public Player(Sprite st, TiledMapTileLayer background, TiledMapTileLayer foreground, MapLayer obj, TiledMapTile tl) {
	    super(st);
	    this.background = background;
	    this.foreground = foreground;
            setSize(background.getTileWidth(), background.getTileHeight());
            this.objectLayer = obj;          
            this.setupAnimation();
	}

	public void update(SpriteBatch spriteBatch) {
	    updateMap(Gdx.graphics.getDeltaTime());
	    updatePosition();
	    super.draw(spriteBatch);
	}

	public void updateMap(float delta) {
		speed = background.getTileHeight() / 8;	
	    deltaTime = delta;
	    if (!stopMove) {
		controlCollision();
	    } else {
			if (pos > 0) {
			    controlCollision();
			} else {
			    if (newVelocity.x == 0 && newVelocity.y == 0) {
			        stopMove = false;
		                stopX();
		                stopY();
			    } else {
			        stopMove = false;
			        velocity.x = newVelocity.x;
			        velocity.y = newVelocity.y;
			        newVelocity.x = newVelocity.y = 0;
			    }
			}
			
			
	    }
	}
	
	private void controlCollision() {
	    if (isDoor(super.getX(),super.getY())) {
                for (final MapObject o : this.objectLayer.getObjects()) {
                	o.getProperties().getKeys().forEachRemaining(s -> System.out.println(s));
                	if (isInRectangleObject(o, super.getX(), super.getY())) {
                        final float x = Float.parseFloat((String)o.getProperties().get("DOOR_X")) * 16;
                        final float y = (300 - Float.parseFloat((String)o.getProperties().get("DOOR_Y"))-1) * 16;
                        super.setX(x);
                        super.setY(y);
                        decreasePos();
                        return;
                    }
                }
            }               
            oldX = super.getX();
            super.setX(super.getX() + velocity.x);                  
            if(velocity.x != 0) {
                collisionX = collidesY();
                if (velocity.x < 0) {
                    setRegion(left.getKeyFrame(animationTime));
                } else if (velocity.x > 0) {
                    setRegion(right.getKeyFrame(animationTime));
                }
                increasePos();
            }               
            if(collisionX || collisionTrainerX) {                   
                super.setX(oldX);
                if (velocity.x < 0) {
                    setRegion(left_s.getKeyFrame(animationTime));
                } else if (velocity.x > 0) {
                    setRegion(right_s.getKeyFrame(animationTime));
                }
                decreasePos();
                velocity.x = 0;
            }               
            oldY = super.getY();
            super.setY(super.getY() + velocity.y);                  
            if(velocity.y != 0) {
                collisionY = collidesX();
                if (velocity.y < 0) {
                    setRegion(down.getKeyFrame(animationTime));
                } else if (velocity.y > 0) {
                    setRegion(up.getKeyFrame(animationTime));
                }
                increasePos();
            }               
            if(collisionY || collisionTrainerY) {                   
                super.setY(oldY); 
                if (velocity.y < 0) {
                    setRegion(down_s.getKeyFrame(animationTime));
                } else if (velocity.y > 0) {
                    setRegion(up_s.getKeyFrame(animationTime));
                }
                decreasePos();
                velocity.y = 0;
            }       
            animationTime += deltaTime;
            updatePos();
	}
	
	private void updatePos() {
	    if (stopMove) {
	        pos ++;
	        if (pos == 8) {
	            pos = 0;
	        }
	    }
	}
	
	private void increasePos() {
	    if (!stopMove) {
	        pos ++;
                if (pos == 8) {
                    decreasePos();
                    if (!MainController.isKeyPressed()) {
                        stop();
                    }
                }
	    }
	}
	
	private void decreasePos() {
	    if (!stopMove) {
	        pos = 0;
	    }
	}
	
	public static void resetPos() {
	    pos = 0;
	}
	
	private boolean isInRectangleObject(final MapObject o, final float currentX, final float currentY) {
	    Object x = o.getProperties().get("x");
	    Object y = o.getProperties().get("y");
	    if (x instanceof Integer && y instanceof Integer) {
	        if ( ((Integer) x *1f) / 16 == (int) currentX / 16 && ((Integer) y * 1f) / 16 == (int) currentY / 16) {
	            return true;
	        }
	    } else if (x instanceof String && y instanceof String) {
	        if(Integer.parseInt((String) x) / 16 == (int) currentX / 16 && Integer.parseInt((String) y) / 16 == (int) currentY / 16) {
	            return true;
	        }
	    }
	    return false;
	}
		
	private boolean isCellBlockedX(float x, float y) {
	    final int cell_x = (int) x / (int) background.getTileWidth();
	    final int cell_y = (int) y / (int) background.getTileHeight();          
	    Cell cell1 = background.getCell(cell_x, cell_y);
	    Cell cell2 = foreground.getCell(cell_x, cell_y);	    
	    final boolean partialControl = checkCellBlocked(cell1) || checkCellBlocked(cell2);
	    return partialControl;
	}
	
        private boolean isCellBlockedY(float x, float y) {
            final int cell_x = (int) x / (int) background.getTileWidth();
            final int cell_y = (int) y / (int) background.getTileHeight();          
            Cell cell1 = background.getCell(cell_x, cell_y);
            Cell cell2 = foreground.getCell(cell_x, cell_y);                             
            final boolean partialControl = checkCellBlocked(cell1) || checkCellBlocked(cell2);
            return partialControl;        
        }	

	private boolean checkCellBlocked(Cell cell) {
	    return cell != null && cell.getTile() != null && !(Boolean.valueOf((String) cell.getTile().getProperties().get("walkable")));
	}
		
	private boolean isDoor(float x, float y) {
	    Cell cell = background.getCell((int) (x / background.getTileWidth()), (int) (y / background.getTileHeight()));
	    if (cell != null && cell.getTile() != null && ((String)cell.getTile().getProperties().get("tileType")).equals("TELEPORT")) {            
	        return true;
            }
            return false;
	}
	
	private boolean collidesX() {
	    if (velocity.y > 0) {
	        return isCellBlockedX(super.getX(), super.getY() + background.getTileHeight() - 0.1f);
	    }
	    return isCellBlockedX(super.getX(), super.getY());
	}
	
	private boolean collidesY() {
	    if (velocity.x > 0) {
	        return isCellBlockedY(super.getX() + background.getTileWidth() -0.1f, super.getY());
	    }
	    return isCellBlockedY(super.getX(), super.getY());
	}
	
	private void setupAnimation() {
	    playerAtlas = new TextureAtlas(this.getClass().getResource("/player.pack").getPath());
            Player.left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
            Player.right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
            Player.up = new Animation(1 / 6f, playerAtlas.findRegions("up"));
            Player.down = new Animation(1 / 6f, playerAtlas.findRegions("down"));
            Player.left_s = new Animation(1 / 6f, playerAtlas.findRegions("left_still"));
            Player.right_s = new Animation(1 / 6f, playerAtlas.findRegions("right_still"));
            Player.up_s = new Animation(1 / 6f, playerAtlas.findRegions("up_still"));
            Player.down_s = new Animation(1 / 6f, playerAtlas.findRegions("down_still"));
            Player.left.setPlayMode(Animation.LOOP);
            Player.right.setPlayMode(Animation.LOOP);
            Player.up.setPlayMode(Animation.LOOP);
            Player.down.setPlayMode(Animation.LOOP);
            Player.left_s.setPlayMode(Animation.LOOP);
            Player.right_s.setPlayMode(Animation.LOOP);
            Player.up_s.setPlayMode(Animation.LOOP);
            Player.down_s.setPlayMode(Animation.LOOP);
            setRegion(down_s.getKeyFrame(animationTime));
	}
	
	public static Vector2 getVelocity() {
	    return velocity;
	}

	public void setVelocity(Vector2 velocity) {
	    Player.velocity = velocity;
	}
	
	public float getSpeed() {
	    return speed;
	}

	public void setSpeed(float speed) {
	    Player.speed = speed;
	}
		
	public TiledMapTileLayer getCollisionLayer() {
	    return foreground;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
	    this.background = collisionLayer;
	}

	public static void move(Direction d) {
	    float x = 0, y = 0;
	    switch (d) {
	    case UP:
	        y = speed;
                break;
	    case LEFT:
	        x = -speed;
                break;
	    case RIGHT:
	        x = speed;
                break;
	    case DOWN:
	        y = -speed;
	    }
	    if (!stopMove && pos == 0) {
	        velocity.x = x;
	        velocity.y = y;
	    } else {
	        newVelocity.x = x;
	        newVelocity.y = y;
	    }
	}
	
	public void stopX() {
	    if (velocity.x < 0) {
                setRegion(left_s.getKeyFrame(animationTime));
            } else if (velocity.x > 0) {
                setRegion(right_s.getKeyFrame(animationTime));
            }
	    velocity.x = 0;
	}
	
	public void stopY() {
	    if (velocity.y < 0) {
                setRegion(down_s.getKeyFrame(animationTime));
            } else if (velocity.y > 0) {
                setRegion(up_s.getKeyFrame(animationTime));
            }
	    velocity.y = 0;
	}

	public static void stop() {
	    Player.stopMove = true;
	}
	
	public static boolean isMoving() {
	    final boolean b1 = (velocity.x != 0 || velocity.y != 0);
	    final boolean b2 = (newVelocity.x != 0 || newVelocity.y != 0);
	    return (b1 || b2);
	}
	
	public void setPlayerPosition(float x, float y) {
	    super.setX(x);
	    super.setY(y);
	}
	
	private void updatePosition() {
	    position = new Pair<>(super.getX(), super.getY());
	}
	
	public static Pair<Float, Float> getPosition() {
	    return position;
	}
}