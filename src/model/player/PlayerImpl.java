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
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.squad.Squad;
import model.squad.SquadImpl;
import model.trainer.TrainerDB;

public class PlayerImpl extends AbstractCharacter implements Player{
    
    private final Squad squad;
    private final Box box;
    private final Inventory inv;
    private final Set<TrainerDB> trainersBeaten;
    private int money = 500;
    
    private static Player SINGLETON;
    private static int START_X = 50;
    private static int START_Y = 50;
    
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
    public Set<TrainerDB> getEnemyBeaten() {
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
    
    public void beatenTrainer(final TrainerDB id) {
        this.money += id.getMoney();
        this.trainersBeaten.add(id);
    }
    
    @Override
    public void pokemonCenter() {
        //TODO Controllare se si trova nel rettangolo del pokemon center
        
        for (final Pokemon p : this.squad.getPokemonList()) {
            p.heal(p.getStat(Stat.HP));
        }
    }

}
