package model.player;

import java.util.Set;

import exceptions.NotEnoughMoneyException;
import model.box.Box;
import model.inventory.Inventory;
import model.items.Item;
import model.map.Character;
import model.map.PokeMap;
import model.squad.Squad;
import model.trainer.Trainer;

public interface Player extends Character{
    
    public Squad getSquad();
    
    public Inventory getInventory();
    
    public Box getBox();
    
    public Set<Trainer> getEnemyBeaten();
    
    public void beatTrainer(final Trainer trainer);
    
    public int getMoney();
    
    public void buyItem(final Item item) throws NotEnoughMoneyException;
    
    public void move(final Direction d, final PokeMap pm);
    
    public void setName(final String name);
    
    public String getName();

    public void setMoney(final int money);

    public void setPosition(final int x, final int y);
    
    public int getLastBadge();
    
    public void addBadge();
    
    public void setStartingPoint(final int tileX, final int tileY);
}
