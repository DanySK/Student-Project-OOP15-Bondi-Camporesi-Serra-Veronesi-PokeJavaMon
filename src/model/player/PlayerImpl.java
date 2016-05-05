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
        this.badges = 0;
        
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
    
    @Override
    public void setMoney(final int money) {
        this.money = money;
    }

    @Override
    public void buyItem(final Item item) throws NotEnoughMoneyException {
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
    	this.direction = d;
    	switch (d) {
    	case EAST :
    		newX += 1;
    		break;
    	case WEST :
    		newX -= 1;
    		break;
    	case NORTH :
    		newY -= 1;
    		break;
    	case SOUTH :
    		newY += 1;
    		break;
    	default :
    		return;
    	}
    	if (pm.isWalkable(newX, newY)) {
   			this.setPosition(newX, newY);
    	}
    }
    
    public void turn(final Direction d) {
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
	
	@Override
	public void setBadges(final int badges) {
		this.badges = badges;
	}
	
	@Override
	public void setStartingPoint(final int tileX, final int tileY) {
		PlayerImpl.START_X = tileX;
		PlayerImpl.START_Y = tileY;
		this.setPosition(tileX, tileY);
	}
	
}
