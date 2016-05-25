package model.trainer;

import model.map.AbstractCharacter;
import model.map.PokeMap;
import model.pokemon.Pokemon;
import model.pokemon.Stat;
import model.squad.Squad;

/**
 * Represent a fightable Character who contains a {@link Squad}, has 3 different messages
 * and gives money in case of win. Each one is identified with a trainerID.
 * To create an instance of Trainer use {@link StaticTrainerFactory}.createTrainer()
 * @see StaticTrainerFactory
 * @see AbstractCharacter
 */
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
      
    /**
     * Constructor to create trainers, please refer to {@link StaticTrainerFactory} to
     * create Trainer's instances
     * 
     * @param name
     * 			His name
     * @param x
     * 			x-axis coordinate
     * @param y
     * 			y-axis coordinaate
     * @param d
     * 			His {@link Direction}
     * @param isDefeated
     * 			Whether or not he's defeated
     * @param squad
     * 			His {@link Squad}
     * @param initMessage
     * 			Message he says when first met
     * @param wonMessage
     * 			Message he says if he defeated you
     * @param lostMessage
     * 			Message he says if you defeated him
     * @param money
     * 			Money he gives if defeated
     * @param trainerID
     * 			His identifier
     * 			
     * 
     * @see StaticTrainerFactory
     */
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

    /**
     * 
     * @return Trainer's {@link Squad}
     */
    public Squad getSquad() {
        return this.squad;
    }
    
    
    /**
     * 
     * @return First message he says when you interact with him
     */
    public String getInitialMessage() {
        return this.initialMessage;
    }
    
    /**
     * 
     * @return Message he says when you defeat him
     */
    public String getTtrainerLostMessage() {
        return this.trainerLostMessage;
    }
    
    /**
     * 
     * @return Message he says when he defeates you
     */
    public String getTrainerWonMessageMessage() {
        return this.trainerWonMessage;
    }
    
    /**
     * Sets isDefeated to true, after that he cannot be fought again
     */
    public void defeat() {
        this.isDefeated = true;
    }
    
    /**
     * 
     * @return true if is already defeated, false otherwise
     */
    public boolean isDefeated() {
        return this.isDefeated;
    }
    
    /**
     * 
     * @return the money you get if you defeat him
     */
    public int getMoney() {
    	return this.money;
    }
    
    /**
     * 
     * @return identifier of the trainer
     */
    public int getID() {
    	return this.trainerID;
    }
    
    /**
     * 
     * @return {@link Trainer}'s name
     */
    public String getName() {
    	return this.name;
    }

    /**
     * Heals the whole {@link Squad} to full HP
     */
    public void healAllPokemons() {
    	for (final Pokemon p : this.squad.getPokemonList()) {
    		p.heal(p.getStat(Stat.MAX_HP));
    	}
    }

    /**
     * Overriden to do nothing as a {@link Trainer} cannot move
     * @param d
     * @param pm
     */
    @Override
    public void move(final Direction d, final PokeMap pm) {}
    
    
    public String toString() {
        return "Name= " + this.name + " Defeated= " + this.isDefeated + ", Squad: " + this.squad + ", D: " + this.direction.name();
    }
    
}
