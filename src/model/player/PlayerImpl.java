package model.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exceptions.NotEnoughMoneyException;
import model.items.Item;
import model.map.AbstractCharacter;
import model.map.PokeMap;
import model.map.Position;
import model.map.tile.Tile;
import model.squad.Squad;
import model.squad.SquadImpl;
import model.trainer.Trainer;

/**
 * Implements {@link Player} interface, also follows Singleton's pattern as there
 * cannot be created more than one {@link Player}
 */
public class PlayerImpl extends AbstractCharacter implements Player{
    
    private String name;
    private Squad squad;
    private Box box;
    private Inventory inv;
    private Set<Trainer> trainersBeaten;
    private int money = 500;
    private int badges;
    
    private static Player SINGLETON;

    /**
     * Actual Starting {@link Position}, if modified it uses the new values,
     * otherwise uses {@link PlayerImpl#DEFAULT_START_X}, {@link PlayerImpl#DEFAULT_START_Y}
     */
    public static int START_X = -1;
    public static int START_Y = -1;

    /**
     * In case the map.tmx does not specify a {@link Tile} which has the property tileType set to START
     * starting {@link Position} is set to (278, 71)
     */
    public static final int DEFAULT_START_X = 278;
    public static final int DEFAULT_START_Y = 71;
    
    private PlayerImpl() {
        super(START_X != -1 ? START_X : DEFAULT_START_X, START_Y != -1 ? START_Y : DEFAULT_START_Y, Direction.SOUTH);
        this.squad = new SquadImpl();
        this.box = new BoxImpl();
        this.inv = new InventoryImpl();
        this.trainersBeaten = new HashSet<>();
        this.badges = 0;    
    }
    
    /**
     * Singleton main method, to get the one and only instance of {@link Player}
     * @return the only instance of {@link Player}
     */
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
    public Set<Trainer> getDefeatedTrainers() {
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
    
    @Override
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
    
    @Override
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
