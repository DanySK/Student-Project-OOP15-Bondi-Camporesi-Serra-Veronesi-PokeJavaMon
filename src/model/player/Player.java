package model.player;

import java.util.Set;

import model.box.Box;
import model.inventory.Inventory;
import model.items.Item;
import model.map.Character;
import model.map.PokeMap;
import model.pokemon.PokemonInBattle;
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
    
    public void useItem(final Item item, final PokemonInBattle pkmn) throws ItemNotFoundException;

    public void pokemonCenter();
    
    public void move(final Direction d, final PokeMap pm);
}
