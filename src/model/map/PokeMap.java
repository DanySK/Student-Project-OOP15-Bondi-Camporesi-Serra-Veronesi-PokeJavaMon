package model.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.badlogic.gdx.maps.tiled.TiledMap;

import model.map.Drawable.Direction;
import model.map.tile.EncounterTile;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.trainer.GymLeader;
import model.trainer.Trainer;

public interface PokeMap {
    
    float getMapHeight();
    float getMapWidth();
    int getTileWidth();
    int getTileHeight();
    
    boolean isOutOfBounds(final int x, final int y);
    Set<Position> getCollisions();
    void removeCollision(final Position p);
    void addCollision(final Position p);
    boolean isWalkable(final int x, final int y);
    boolean isWalkableNextToPlayer(final Direction d);
    TileType getTileNextToPlayer(final Direction d);
    
    Set<Teleport> getTeleports();
    Optional<Teleport> getTeleport(final int fromX, final int fromY);
    
    Set<Sign> getSigns();
    Optional<Sign> getSign(final int x, final int y);
    
    Set<Trainer> getTrainers();
    Optional<Trainer> getTrainer(final int x, final int y);
    void initTrainers(final Map<Integer, Boolean> trainerID_isDefeated);
    
    Set<GymLeader> getGymLeaders();
    Optional<GymLeader> getGymLeader(final int x, final int y);
    void initGymLeaders(final int badges);
   
    Set<NPC> getNPCs();
    Optional<NPC> getNPC(final int x, final int y);
    
    Set<EncounterTile> getEncounterTiles();
    Optional<EncounterTile> getEncounterTile(final int x, final int y);
    Set<EncounterTile> getRemovedEncounterTiles();
    void setDeletedEncounterTiles(final Set<String> pkmnsToBeDeleted);
    void deleteEncounterTile(final int x, final int y);
   
    PokeMarket getPokeMarket();
    
    Set<PokemonEncounterZone> getEncounterZones();
    Optional<PokemonEncounterZone> getEncounterZone(final int x, final int y);

    Tile.TileType getTileType(final int x, final int y);
    
    Set<WalkableZone> getWalkableZones();
    Optional<WalkableZone> getWalkableZone(final int x, final int y);
    
    int getTileUnitX(final int cellX);
	int getTileUnitY(final int cellY);

	TileType[][] getMap();
	
	TiledMap getTiledMap();
	
	Position getPokemonCenterSpawnPosition();
}
