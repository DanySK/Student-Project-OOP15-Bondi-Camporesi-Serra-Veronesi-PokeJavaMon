package model.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exceptions.NotEnoughMoneyException;
import model.box.Box;
import model.box.BoxImpl;
import model.inventory.Inventory;
import model.inventory.InventoryImpl;
import model.items.Item;
import model.map.AbstractCharacter;
import model.map.PokeMap;
import model.map.tile.Teleport;
import model.map.tile.Tile.TileType;
import model.squad.Squad;
import model.squad.SquadImpl;
import model.trainer.Trainer;

public class PlayerImpl extends AbstractCharacter implements Player{
    
    private String name;
    private Squad squad;
    private Box box;
    private Inventory inv;
    private Set<Trainer> trainersBeaten;
    private int money = 500;
    private int badges;
    
    private static Player SINGLETON;
    
    //TODO: Player position IMPORT FROM MAP
    public static int START_X = -1;
    public static int START_Y = -1;
    public static final int DEFAULT_START_X = 278;
    public static final int DEFAULT_START_Y = 71;
    
    private PlayerImpl() {
        super(START_X != -1 ? START_X : DEFAULT_START_X, START_Y != -1 ? START_Y : DEFAULT_START_Y, Direction.SOUTH);
        this.squad = new SquadImpl();
        this.box = BoxImpl.getBox();
        this.inv = InventoryImpl.getInventory();
        this.trainersBeaten = new HashSet<>();
        this.badges = -1;
        
    }
    
    public static Player getPlayer() {
        if (SINGLETON == null) {
            synchronized (PlayerImpl.class) {
                if (SINGLETON == null) {
                    SINGLETON = new PlayerImpl();
                }
            }
        }
        return SINGLETON;
    }
    
    
    @Override
    public void setName(final String name) {
    	this.name = name;
    }
    
    @Override
    public String getName() {
    	return (this.name == null || this.name.isEmpty()) ? "Player" : this.name;
    }
    
    @Override
    public Squad getSquad() {
        return this.squad;
    }

    @Override
    public Inventory getInventory() {
        return this.inv;
    }

    @Override
    public Box getBox() {
        return this.box;
    }

    @Override
    public Set<Trainer> getEnemyBeaten() {
        return Collections.unmodifiableSet(trainersBeaten);
    }

    @Override
    public int getMoney() {
        return this.money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void buyItem(Item item) throws NotEnoughMoneyException {
        if (this.money - item.getPrice() < 0 ) {
            throw new NotEnoughMoneyException();
        }
        this.inv.addItem(item);
        this.money -= item.getPrice();
    }

    @Override
    public void beatTrainer(final Trainer trainer) {
        this.money += trainer.getMoney();
        this.trainersBeaten.add(trainer);
    }
    
    public void setPosition(final int x, final int y) {
    	this.tileX = x;
    	this.tileY = y;
    }
    
    @Override
    public void move(final Direction d, final PokeMap pm) {
    	int newX = this.tileX;
    	int newY = this.tileY;
    	switch (d) {
    	case EAST :
    		newX += pm.getTileWidth();
    		break;
    	case WEST :
    		newX -= pm.getTileWidth();
    		break;
    	case NORTH :
    		newY -= pm.getTileHeight();
    		break;
    	case SOUTH :
    		newY +=  pm.getTileHeight();
    		break;
    	}
    	if (pm.isWalkable(newX, newY)) {
    		if (pm.getTileType(newX, newY) == TileType.TELEPORT) {
    			final Teleport tmpTlprt = pm.getTeleport(newX, newY).get();
    			if (tmpTlprt == null) {
    				throw new IllegalStateException("Teleport not found even if it's in the map as a TileType");
    			}
    			this.setPosition(tmpTlprt.getDestinationX(), tmpTlprt.getDestinationY());
    		} else {
    			this.setPosition(newX, newY);
    		}
    	}
    }
    
    public void turn(Direction d) {
    	this.direction = d;
    }

	@Override
	public int getLastBadge() {
		return this.badges;
	}

	@Override
	public void addBadge() {
		this.badges++;
	}
	
	//TODO Da rivedere
	public static void setStartingPoint(int tileX, int tileY) {
		PlayerImpl.START_X = tileX;
		PlayerImpl.START_Y = tileY;
	}

}
