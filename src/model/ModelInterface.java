package model;

import model.map.NPC;
import model.map.PokeMap;
import model.map.Position;
import model.player.Player;
import model.pokemon.Pokemon;
import model.pokemon.WeaknessTable;
import model.trainer.GymLeader;
import model.trainer.Trainer;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.SquadFullException;

public interface ModelInterface {

    public Player getPlayer();
    
    public void setPlayerName(String name);
    
    public PokeMap getMap();

    public WeaknessTable getWeaknessTable();
    
    public Set<GymLeader> getGymLeaders();
    
    public Set<Trainer> getTrainers();
    
    public Set<NPC> getNPCs();
    
    public void loadSave(final int playerMoney, final String name, final int badges, final Position playerPosition, final List<Pokemon> squad,
    					 final Map<Integer, Boolean> idTrainer_isDefeated, final List<Pokemon> box) throws SquadFullException;
}
