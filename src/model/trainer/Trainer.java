package model.trainer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import model.map.AbstractCharacter;
import model.map.PokeMap;
import model.map.PokeMapImpl;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;
import view.resources.Play;

public class Trainer extends AbstractCharacter {
    
    public final static String TYPE_TRAINER_NAME = "TRAINER";
    private final Squad squad;
    private final String name;
    protected boolean isDefeated;
    private final String initialMessage;
    private final String trainerWonMessage;
    private final String trainerLostMessage;
    private final int money;
    private final int trainerID;
    
    
    protected Trainer(final String name, final int x, final int y, final Direction d, final boolean isDefeated, final Squad squad,
    				  final String initMessage, final String wonMessage, final String lostMessage, final int money, final int trainerID) {
        super(x,y,d);
        this.name = name;
        this.squad = squad;
        this.isDefeated = isDefeated;
        this.initialMessage = initMessage;
        this.trainerWonMessage = wonMessage;
        this.trainerLostMessage = lostMessage;
        this.money = money;
        this.trainerID = trainerID;
    }

    public Squad getSquad() {
        return this.squad;
    }
    
    
    public String getInitialMessage() {
        return this.initialMessage;
    }
    
    public String getTtrainerLostMessage() {
        return this.trainerLostMessage;
    }
    
    public String getTrainerWonMessageMessage() {
        return this.trainerWonMessage;
    }
    
    public void defeat() {
        for (final PokemonInBattle p : this.squad.getPokemonList()) {
            if (p.getCurrentHP() > 0) {
                throw new IllegalStateException("There's still at least 1 pokemon alive");
            }
        }
        this.isDefeated = true;
    }
    
    public boolean isDefeated() {
        return this.isDefeated;
    }
    
    public int getMoney() {
    	return this.money;
    }
    
    public int getID() {
    	return this.trainerID;
    }
    
    public String toString() {
        return "Name= " + this.name + " Defeated= " + this.isDefeated + ", Squad: " + this.squad + ", D: " + this.direction.name();
    }

	@Override
	public void move(final Direction d, final PokeMap pm) {
	
	}
	
	public void turn(final Direction d) {
	    PokeMapImpl pm = Play.getMapImpl();
	    TiledMapTileLayer bg = (TiledMapTileLayer) pm.getTiledMap().getLayers().get("foreground");
	    Cell tr = bg.getCell(pm.getTileUnitX(tileX), pm.getTileUnitY(tileY));
	    System.out.println(tr.getTile().getId());
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
	    }
	    
        System.out.println("Valore TileID: " + val);
	    this.direction = d;
	}
}
