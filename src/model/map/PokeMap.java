package model.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.map.Drawable.Direction;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.trainer.Trainer;

public interface PokeMap {
    
    public float getMapHeight();
    public float getMapWidth();
    public int getTileWidth();
    public int getTileHeight();
    
    public boolean isOutOfBounds(final int x, final int y);
    public Set<Position> getCollisions();
    public void removeCollision(final Position p);
    public void addCollision(final Position p);
    public boolean isWalkable(final int x, final int y);
    public TileType getTileNextToPlayer(final Direction d);
    
    public Set<Teleport> getTeleports();
    public Optional<Teleport> getTeleport(final int fromX, final int fromY);
    
    public Set<Sign> getSigns();
    public Optional<Sign> getSign(final int x, final int y);
    
    public Set<Trainer> getTrainers();
    public Optional<Trainer> getTrainer(final int x, final int y);
    public void initTrainers(final Map<Integer, Boolean> trainerID_isDefeated);
    
    public Set<PokemonEncounterZone> getEncounterZones();
    public Optional<PokemonEncounterZone> getEncounterZone(final int x, final int y);

    public Tile.TileType getTileType(final int x, final int y);
    
    public Set<WalkableZone> getWalkableZones();
    public Optional<WalkableZone> getWalkableZone(final int x, final int y);
    
    public int getTileUnitX(final int cellX);
	public int getTileUnitY(final int cellY);

	public TileType[][] getMap();
	    
}
