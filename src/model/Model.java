package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;

import exceptions.SquadFullException;
import model.box.Box;
import model.inventory.Inventory;
import model.map.NPC;
import model.map.PokeMap;
import model.map.PokeMapImpl;
import model.map.Position;
import model.map.tile.EncounterTile;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
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
    public void loadSave(final int playerMoney, final String name, final int badges, final Position playerPosition, 
			 final List<Pokemon> squad, final Map<Integer, Boolean> idTrainer_isDefeated, final List<Pokemon> box, 
			 final Map<String, Integer> pokeballs, final Map<String, Integer> boosts, final Map<String, Integer> potions, final Set<String> deletedEncounterTiles) throws SquadFullException {
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
        this.player.getInventory().initializeInventory(potions, boosts, pokeballs);
        this.map.setDeletedEncounterTiles(deletedEncounterTiles);
    }

    @Override
    public Save getModelSnapshot() {
        return new Save() {

            @Override
            public int getPlayerMoney() {
                return player.getMoney();
            }

            @Override
            public String getPlayerName() {
                return player.getName();
            }
	    	
            @Override
            public int getPlayerBadges() {
                return player.getLastBadge();
            }
	    	
            @Override
            public Position getPlayerPosition() {
                return new Position(player.getTileX(), player.getTileY());
            }
	    	
            @Override
            public List<PokemonInBattle> getPokemonSquad() {
                return player.getSquad().getPokemonList();
            }

            @Override
            public Set<Trainer> getTrainers() {
                return map.getTrainers();
            }

            @Override
            public Inventory getInventory() {
                return player.getInventory();
            }

            @Override
            public Box getBox() {
                return player.getBox();
            }
            
            @Override
            public Set<EncounterTile> getEncounterTilesToBeRemoved() {
            	final Set<EncounterTile> toBeRemoved = new HashSet<>();
            	for (final EncounterTile et : map.getEncounterTiles()) {
            		if (!et.isEncounterable()) {
            			toBeRemoved.add(et);
            		}
            	}
            	return Collections.unmodifiableSet(toBeRemoved);
            }
        };
    }
}
