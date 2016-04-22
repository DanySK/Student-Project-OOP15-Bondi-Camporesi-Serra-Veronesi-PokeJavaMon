package model.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.box.Box;
import model.box.BoxImpl;
import model.inventory.Inventory;
import model.inventory.InventoryImpl;
import model.items.Item;
import model.map.AbstractCharacter;
import model.map.PokeMap;
import model.map.tile.Teleport;
import model.map.tile.Tile.TileType;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.squad.Squad;
import model.squad.SquadImpl;
import model.trainer.Trainer;

public class PlayerImpl extends AbstractCharacter implements Player{
    
	private String name;
    private final Squad squad;
    private final Box box;
    private final Inventory inv;
    private final Set<Trainer> trainersBeaten;
    private int money = 500;
    
    private static Player SINGLETON;
    private static int START_X = 278;
    private static int START_Y = 71;
    
    private PlayerImpl() {
        super(START_X, START_Y, Direction.SOUTH);
        this.squad = new SquadImpl();
        this.box = BoxImpl.getBox();
        this.inv = InventoryImpl.getInventory();
        this.trainersBeaten = new HashSet<>();
        
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
    public void buyItem(Item item) throws NotEnoughMoneyException {
        if (this.money - item.getPrice() < 0 ) {
            throw new NotEnoughMoneyException();
        }
        this.inv.addItem(item);
        this.money -= item.getPrice();
    }

    @Override
    public void useItem(Item item, PokemonInBattle pkmn) throws ItemNotFoundException {
        //TODO CONTROLLARE STATO (BATTAGLIA) e se l'oggetto può essere usato... Exceptions..
    }
    
    @Override
    public void beatTrainer(final Trainer trainer) {
        this.money += trainer.getMoney();
        this.trainersBeaten.add(trainer);
    }
    
    @Override
    public void pokemonCenter() {
        //TODO Controllare se si trova nel rettangolo del pokemon center
        
        for (final Pokemon p : this.squad.getPokemonList()) {
            p.heal(p.getStat(Stat.HP));
        }
    }
    
    private void setPosition(final int x, final int y) {
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
    			final Teleport tmpTlprt = pm.getTeleport(newX, newY);
    			if (tmpTlprt == null) {
    				throw new IllegalStateException("Teleport not found even if it's in the map as a TileType");
    			}
    			this.setPosition(tmpTlprt.getDestinationX(), tmpTlprt.getDestinationY());
    		} else {
    			this.setPosition(newX, newY);
    		}
    	}
    }

}
