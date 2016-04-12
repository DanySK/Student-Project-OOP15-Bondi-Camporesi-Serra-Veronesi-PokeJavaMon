package controller.modelResources;

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

import controller.music.MusicController;
import controller.parameters.Music;

public class Player extends Sprite {
        float oldX = super.getX(), oldY = super.getY();
        float exitX = 0, exitY = 0;
        boolean toModifyExit = true, inside;
        Cell door = null;
        TiledMapTile tile;
	private Vector2 velocity = new Vector2();
	private float speed = 1;
        private TiledMapTileLayer background;
        private TiledMapTileLayer foreground;
        private MapLayer objectLayer;
        private Animation left, right, up, down;
        private Animation left_s, right_s, up_s, down_s;
        private float animationTime = 0;
        private TextureAtlas playerAtlas;
        private MusicController mc = new MusicController();
	
	public Player(Sprite st, TiledMapTileLayer background, TiledMapTileLayer foreground, MapLayer obj, TiledMapTile tl) {	    
	    super(st);
	    this.background = background;
	    this.foreground = foreground;
            setSize(background.getTileWidth(), background.getTileHeight());
            tile = tl;
            this.objectLayer = obj;          
            this.setupAnimation();
            mc.play(Music.TOWN);
	}

	public void update(SpriteBatch spriteBatch) {
	        updateMap(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	public void updateMap(float delta) {	    		
	        speed = background.getTileHeight();		        
		boolean collisionX = false, collisionY = false;
		boolean collisionTrainerX = false, collisionTrainerY = false;		
		if (isDoor(super.getX(),super.getY())) {
                    for (final MapObject o : this.objectLayer.getObjects()) {
                        if (isInRectangleObject(o, super.getX(), super.getY())) {
                            final float x = Float.parseFloat((String)o.getProperties().get("DOOR_X")) * 16;
                            final float y = (300 - Float.parseFloat((String)o.getProperties().get("DOOR_Y"))-1) * 16;
                            super.setX(x);
                            super.setY(y);
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
                }               
                if(collisionX || collisionTrainerX) {                   
                    super.setX(oldX);
                    if (velocity.x < 0) {
                        setRegion(left_s.getKeyFrame(animationTime));
                    } else if (velocity.x > 0) {
                        setRegion(right_s.getKeyFrame(animationTime));
                    }
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
                }               
                if(collisionY || collisionTrainerY) {                   
                    super.setY(oldY); 
                    if (velocity.y < 0) {
                        setRegion(down_s.getKeyFrame(animationTime));
                    } else if (velocity.y > 0) {
                        setRegion(up_s.getKeyFrame(animationTime));
                    }
                    velocity.y = 0;
                }       
                animationTime += delta;
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
            final int remaining_x = (int) x % (int) background.getTileWidth();           
	    Cell cell1 = background.getCell(cell_x, cell_y);
	    Cell cell2 = foreground.getCell(cell_x, cell_y);
	    Cell cell1_2 = null;
	    Cell cell2_2 = null;	    
	    if (remaining_x != 0) {
	        cell1_2 = background.getCell(cell_x + 1, cell_y);
	        cell2_2 = foreground.getCell(cell_x + 1, cell_y);
	    } 	    
	    final boolean partialControl = checkCellBlocked(cell1) || checkCellBlocked(cell2);
	    if (cell1_2 == null && cell2_2 == null) {
	        return partialControl;
	    }           
	    final boolean partialControl2 = checkCellBlocked(cell1_2) || checkCellBlocked(cell2_2);
	    return partialControl || partialControl2;
	}
	
        private boolean isCellBlockedY(float x, float y) {
            final int cell_x = (int) x / (int) background.getTileWidth();
            final int cell_y = (int) y / (int) background.getTileHeight();
            final int remaining_y = (int) y % (int) background.getTileHeight();          
            Cell cell1 = background.getCell(cell_x, cell_y);
            Cell cell2 = foreground.getCell(cell_x, cell_y);
            Cell cell1_2 = null;
            Cell cell2_2 = null;                   
            if (remaining_y != 0) {
                cell1_2 = background.getCell(cell_x, cell_y + 1);
                cell2_2 = foreground.getCell(cell_x, cell_y + 1);
            }            
            final boolean partialControl = checkCellBlocked(cell1) || checkCellBlocked(cell2);
            if (cell1_2 == null && cell2_2 == null) {
                return partialControl;
            }           
            final boolean partialControl2 = checkCellBlocked(cell1_2) || checkCellBlocked(cell2_2);
            return partialControl || partialControl2;
        }	

	private boolean checkCellBlocked(Cell cell) {
	    return cell != null && cell.getTile() != null && !(Boolean.valueOf((String) cell.getTile().getProperties().get("walkable")));
	}
		
	public boolean isDoor(float x, float y) {
	    Cell cell = background.getCell((int) (x / background.getTileWidth()), (int) (y / background.getTileHeight()));
	    if (cell != null && cell.getTile() != null && ((String)cell.getTile().getProperties().get("tileType")).equals("TELEPORT")) {            
	        return true;
            }
            return false;
	}
	
	public boolean collidesX() {
	    if (velocity.y > 0) {
	        return isCellBlockedX(super.getX(), super.getY() + background.getTileHeight() - 0.1f);
	    }
	    return isCellBlockedX(super.getX(), super.getY());
	}
	
	public boolean collidesY() {
	    if (velocity.x > 0) {
	        return isCellBlockedY(super.getX() + background.getTileWidth() -0.1f, super.getY());
	    }
	    return isCellBlockedY(super.getX(), super.getY());
	}
	
	private void setupAnimation() {
	    playerAtlas = new TextureAtlas("resources/img/player.pack");
            left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
            right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
            up = new Animation(1 / 6f, playerAtlas.findRegions("up"));
            down = new Animation(1 / 6f, playerAtlas.findRegions("down"));
            left_s = new Animation(1 / 6f, playerAtlas.findRegions("left_still"));
            right_s = new Animation(1 / 6f, playerAtlas.findRegions("right_still"));
            up_s = new Animation(1 / 6f, playerAtlas.findRegions("up_still"));
            down_s = new Animation(1 / 6f, playerAtlas.findRegions("down_still"));
            left.setPlayMode(Animation.LOOP);
            right.setPlayMode(Animation.LOOP);
            up.setPlayMode(Animation.LOOP);
            down.setPlayMode(Animation.LOOP);
            left_s.setPlayMode(Animation.LOOP);
            right_s.setPlayMode(Animation.LOOP);
            up_s.setPlayMode(Animation.LOOP);
            down_s.setPlayMode(Animation.LOOP);
            setRegion(down_s.getKeyFrame(animationTime));
	}
	
	public Vector2 getVelocity() {
	    return velocity;
	}

	public void setVelocity(Vector2 velocity) {
	    this.velocity = velocity;
	}
	
	public float getSpeed() {
	    return speed;
	}

	public void setSpeed(float speed) {
	    this.speed = speed;
	}
		
	public TiledMapTileLayer getCollisionLayer() {
	    return foreground;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
	    this.background = collisionLayer;
	}

	public void move(float x, float y) {
	    velocity.x = x;
	    velocity.y = y;
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

	public void stop() {
	    this.stopY();
	    this.stopX();
	}
	
	public void setPos(float x, float y) {
	    super.setX(x);
	    super.setY(y);
	}
}