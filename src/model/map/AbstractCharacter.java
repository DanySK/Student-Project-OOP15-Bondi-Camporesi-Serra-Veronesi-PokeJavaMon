package model.map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import controller.Controller;

public abstract class AbstractCharacter implements Character {
    
    protected int tileX;
    protected int tileY;
    protected Direction direction;
    
    
    public AbstractCharacter(final int x, final int y, final Direction d) {
        this.tileX = x;
        this.tileY = y;
        this.direction = d;
    }
    
    public abstract void move(final Direction d, final PokeMap pm);
    
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getTileX() {
        return this.tileX;
    }

    @Override
    public int getTileY() {
        return this.tileY;
    }
    
    @Override
    public Position getPosition() {
    	return new Position(this.tileX, this.tileY);
    }
    
	@Override
	public void turn(final Direction d) {
		if (this.direction == d) {
			return;
		}
	    PokeMap pm = Controller.getController().getPokeMap();
	    TiledMapTileLayer bg = (TiledMapTileLayer) pm.getTiledMap().getLayers().get("foreground");
	    Cell tr = bg.getCell(pm.getTileUnitX(tileX), pm.getTileUnitY(tileY));
	    int val = -1;
	    switch (d) {
	    case WEST:
	        val = Integer.parseInt(tr.getTile().getProperties().get("LEFT_ID", String.class));
	        val++;
	        if (val > 0) {
	        	tr.setTile(pm.getTiledMap().getTileSets().getTile(val));
	        }
	        break;
	    case NORTH:
	        val = Integer.parseInt(tr.getTile().getProperties().get("REAR_ID", String.class));
            val++;
	        if (val > 0) {
            	tr.setTile(pm.getTiledMap().getTileSets().getTile(val));
            }
            break;
	    case EAST:
	        val = Integer.parseInt(tr.getTile().getProperties().get("RIGHT_ID", String.class));
            val++;
	        if (val > 0) {
            	tr.setTile(pm.getTiledMap().getTileSets().getTile(val));
            }
            break;
	    case SOUTH:
            val = Integer.parseInt(tr.getTile().getProperties().get("FRONT_ID", String.class));
            val++;
            if(val > 0) {
            	tr.setTile(pm.getTiledMap().getTileSets().getTile(val));
            }
            break;
        default :
        	return;
	    }
	    this.direction = d;
	}
	
}
