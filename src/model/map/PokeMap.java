package model.map;

import java.util.Map;
import java.util.Set;

import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.trainer.Trainer;

public interface PokeMap {
    public static float LIMIT_UP_X = 500;
    public static float LIMIT_UP_Y = 500;
    public static float LIMIT_DOWN_X = 0;
    public static float LIMIT_DOWN_Y = 0;
    
    public static int TILE_HEIGHT = 16;
    public static int TILE_WIDTH = 16;
    
    public static boolean isOutOfBorder(final float x, final float y) {
        return x > LIMIT_DOWN_X && x < LIMIT_UP_X && y > LIMIT_DOWN_Y && y < LIMIT_UP_X; 
    }
    
    public float getMapHeight();
    public float getMapWidth();
    
    public Set<Position> getCollisions();
    public void removeCollision(final Position p);
    public void addCollision(final Position p);
    public boolean isWalkable(final int x, final int y);
    
    public Set<Teleport> getTeleports();
    public Teleport getTeleport(final int fromX, final int fromY);
    
    public Set<Sign> getSigns();
    public Sign getSign(final int x, final int y);
    
    public Set<Trainer> getTrainers();
    public Trainer getTrainer(final int x, final int y);
    public void initTrainers(final Map<Integer, Boolean> trainerID_isDefeated);
    
    public Set<PokemonEncounterZone> getEncounterZones();
    public PokemonEncounterZone getEncounterZone(final int x, final int y);

    public Tile.TileType getTileType(final int x, final int y);
    
    public Set<WalkableZone> getWalkableZones();
    public WalkableZone getWalkableZone(final int x, final int y);
    
    public int getTileUnitX(final int cellX);
	public int getTileUnitY(final int cellY);

	public TileType[][] getMap();
	    
}
