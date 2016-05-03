package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;

import exceptions.SquadFullException;
import model.map.NPC;
import model.map.PokeMap;
import model.map.PokeMapImpl;
import model.map.Position;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.WeaknessTable;
import model.trainer.GymLeader;
import model.trainer.Trainer;

public class Model implements ModelInterface {

	private final PokeMap map; 
	private final Player player;
	private boolean isContinued;
	
	public Model(final TiledMap tm) {
		this.map = new PokeMapImpl(tm);
		this.player = PlayerImpl.getPlayer();
		this.isContinued = false;
	}
	
	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public void setPlayerName(String name) {
		this.player.setName(name);
	}

	@Override
	public PokeMap getMap() {
		return this.map;
	}

	@Override
	public WeaknessTable getWeaknessTable() {
		return WeaknessTable.getWeaknessTable();
	}

	@Override
	public Set<GymLeader> getGymLeaders() {
		return this.map.getGymLeaders();
	}

	@Override
	public Set<Trainer> getTrainers() {
		return this.map.getTrainers();
	}

	@Override
	public Set<NPC> getNPCs() {
		return this.map.getNPCs();
	}

	@Override
	public void loadSave(int playerMoney, String name, int badges, Position playerPosition, List<Pokemon> squad,
			Map<Integer, Boolean> idTrainer_isDefeated, List<Pokemon> box) throws SquadFullException {
		
		if (isContinued) {
			return;
		}
		this.player.setMoney(playerMoney);
		this.player.setName(name);
		this.player.setBadges(badges);
		this.player.setPosition(playerPosition.getX(), playerPosition.getY());
		for (final Pokemon p : squad) {
			this.player.getSquad().add(p);
		}
		this.map.initTrainers(idTrainer_isDefeated);
		this.map.initGymLeaders(badges);
		this.player.getBox().setPokemons(box);

	}

}
