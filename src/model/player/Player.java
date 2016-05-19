package model.player;

import java.util.Set;

import exceptions.NotEnoughMoneyException;
import model.items.Item;
import model.map.Character;
import model.map.PokeMap;
import model.squad.Squad;
import model.trainer.Trainer;

/**
 * Class that shapes the main character of the game.
 * @see Squad
 * @see Character
 * @see PokeMap
 */
public interface Player extends Character{
    
	/**
	 * 
	 * @return Player's {@link Squad} filled with {@link Pokemon}
	 */
    public Squad getSquad();
    
    /**
     * 
     * @return Player's {@link Inventory}: a "bag" filled with {@link Item}s
     */
    public Inventory getInventory();
    
    /**
     * 
     * @return Player's {@link Box}: a storage of all {@link Pokemon} that are not in the {@link Squad}
     */
    public Box getBox();
    
    /**
     * 
     * @return a {@link Set} of all the {@link Trainer}s defeated
     */
    public Set<Trainer> getDefeatedTrainers();
    
    /**
     * Method that retrieve winning money from the {@link Trainer} and 
     * adds him to the {@link Set} of defeated {@link Trainer}s
     * @param trainer The {@link Trainer} the Player has just defeated 
     */
    public void beatTrainer(final Trainer trainer);
    
    /**
     * 
     * @return Current Player's money
     */
    public int getMoney();
    
    /**
     * Adds an {@link Item} to the {@link Inventory} only if the Player has 
     * sufficient money
     * @param item	{@link Item} bought
     * @throws NotEnoughMoneyException
     */
    public void buyItem(final Item item) throws NotEnoughMoneyException;
    
    /**
     * The Player moves to the next {@link Tile} in {@link Direction}.
     * Won't do anything if he encounters a collision {@link Tile}
     * Won't check for {@link Teleport}s
     * @param d {@link Direction} to walk to
     * @param pm {@link PokeMap} to check if there are any collisions or {@link Teleport}s
     * @see PokeMap
     * @see Tile
     */
    public void move(final Direction d, final PokeMap pm);
    
    /**
     * Sets the new name of the Player
     * @param name Player new name
     */
    public void setName(final String name);
    
    /**
     * 
     * @return Player's name
     */
    public String getName();

    /**
     * Set the new ammount of money
     * @param money the new ammount
     */
    public void setMoney(final int money);

    /**
     * Sets the Player's {@link Position} in the {@link PokeMap}
     * Does not check if it is out of {@link PokeMap} bounds
     * @param x	x-axis Coordinate
     * @param y y-axis Coordinate
     */
    public void setPosition(final int x, final int y);
    
    /**
 	 * Note: badges have to go incrementally, 1st GymLeader gives 1st badge,
 	 * 3rd gives 3rd etc...
     * @return the last badge obtained from a {@link GymLeader}
     */
    public int getLastBadge();
    
    /**
     * Adds a badge to the badge count
     */
    public void addBadge();
    
    /**
     * Sets the ammount of badges to a new ammount
     * @param badges new ammount of badges
     */
    public void setBadges(final int badges);
    
    /**
     * Sets Player's first {@link Position} in the {@link PokeMap}
     * @param tileX x-axis Coordinate
     * @param tileY y-axis Coordinate
     */
    public void setStartingPoint(final int tileX, final int tileY);
}
