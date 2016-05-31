package model.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.map.Drawable.Direction;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.player.Player;
import model.trainer.GymLeader;
import model.trainer.Trainer;

/**
 * Interface that describes all the method that a proper map in the game of Pokemon should have.
 * The map must be divided in {@link Tile}s b
 */
public interface PokeMap {

    /**
     * @return map's width in {@link Tile}'s unit
     */
    int getMapWidth();
	
    /**
	 * @return map's height in {@link Tile}'s unit
	 */
    int getMapHeight();
    
    /**
     * @return a single tile's width in Pixels
     */
    int getTileWidth();
    
    /**
     * @return a single tile's heigth in Pixels
     */
    int getTileHeight();
    
    /**
     * Checks if the coordinates belong to the map
     * @param x
     * 			x-axis coordinate in tile-units
     * @param y
     * 			y-axis coordinate in tile-units
     * @return true if the point is outside the map, false if it's inside
     */
    boolean isOutOfBounds(final int x, final int y);
    
    /**
     * @return an unmodifiable {@link Set} of {@link Position} (lightweight objects) of all current collisions
     */
    Set<Position> getCollisions();
    
    /**
     * Removes a given collision from the map and sets the {@link TileType} in the map to {@link TileType#TERRAIN}
     * @param p
     * 			{@link Position} to be removed from the collision set
     */
    void removeCollision(final Position p);
    
    /**
     * Adds a new collision to the map (the old value of {@link TileType} remains, however, the same
     * @param p
     * 			{@link Position} to be added to the collision set
     */
    void addCollision(final Position p);
    
    /**
     * Checks if a certain {@link Tile} is not a collision and is not a unwalkable {@link Tile}
     * @param x
     * 			x-axis coordinate in tile-units
     * @param y
     * 			y-axis coordinate in tile-units
     * @return	true if you can walk on it, false if it is unwalkable
     */
    boolean isWalkable(final int x, final int y);
    
    /**
     * Checks if the {@link Tile} in {@link Direction} to the {@link Player} is walkable
     * @param d
     * 			{@link Direction} next to the {@link Player}
     * @return true if the {@link Tile} is walkable, false otherwise
     */
    boolean isWalkableNextToPlayer(final Direction d);
    
    /**
     * @param d
     * 			{@link Direction} next to the {@link Player}
     * @return	the {@link TileType} in {@link Direction} to the {@link Player}
     */
    TileType getTileNextToPlayer(final Direction d);
    
    /**
     * @return an unmodifiable {@link Set}({@link Teleport}) of all available {@link Teleport}s in the map
     */
    Set<Teleport> getTeleports();
    
    /**
     * @param fromX
     * 			x-axis coordinate of the {@link Teleport} starting point in tile units
     * @param fromY
     * 			y-axis coordinate of the {@link Teleport} starting point in tile units
     * @return an {@link Optional}({@link Teleport}) that may or may not be there
     */
    Optional<Teleport> getTeleport(final int fromX, final int fromY);
    
    /**
     * @return an unmodifiable {@link Set}({@link Sign}) of all available {@link Sign}s in the map
     */
    Set<Sign> getSigns();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link Sign} in tile units
     * @param y
     * 			y-axis coordinate of the {@link Sign} in tile units
     * @return an {@link Optional}({@link Sign}) that may or may not be there
     */
    Optional<Sign> getSign(final int x, final int y);
    
    /**
     * @return an unmodifiable {@link Set}({@link Trainer}) of all available {@link Trainer}s in the map
     */
    Set<Trainer> getTrainers();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link Trainer} in tile units
     * @param y
     * 			y-axis coordinate of the {@link Trainer} in tile units
     * @return an {@link Optional}({@link Trainer}) that may or may not be there
     */
    Optional<Trainer> getTrainer(final int x, final int y);
    
    /**
     * initialize all defeated {@link Trainer}s from the save
     * @param trainerID_isDefeated
     * 			a {@link Map}({@link Integer}, {@link Boolean}) with {@link Trainer}'id as keys a if he is defeated as values
     */
    void initTrainers(final Map<Integer, Boolean> trainerID_isDefeated);
    
    /**
     * @return an unmodifiable {@link Set}({@link GymLeader}) of all available {@link GymLeader}s in the map
     */
    Set<GymLeader> getGymLeaders();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link GymLeader} in tile units
     * @param y
     * 			y-axis coordinate of the {@link GymLeader} in tile units
     * @return an {@link Optional}({@link GymLeader}) that may or may not be there
     */
    Optional<GymLeader> getGymLeader(final int x, final int y);
    
    /**
     * Initialize defeated {@link GymLeader}s based on the number of badges aquired.
     * E.g. if you have a save with 3 badges, when you load the first 3 {@link GymLeader}s appear as defeated
     * @param badges
     * 			number of {@link Player}'s badges
     */
    void initGymLeaders(final int badges);
    
    /**
     * Switches the {@link Direction} of a given {@link Character}, used when interacting with him from a different {@link Direction}
     * @param c
     * 			{@link Character} to be turned
     * @param d
     * 			{@link Direction} to turn towards
     */
    void turnCharacter(final Character c, final Direction d) throws IllegalArgumentException;
    
    /**
     * @return an unmodifiable {@link Set}({@link NPC}) of all available {@link NPC}s in the map
     */
    Set<NPC> getNPCs();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link NPC} in tile units
     * @param y
     * 			y-axis coordinate of the {@link NPC} in tile units
     * @return an {@link Optional}({@link NPC}) that may or may not be there
     */
    Optional<NPC> getNPC(final int x, final int y);
    
    /**
     * @return an unmodifiable {@link Set}({@link SpecialEncounterTile}) of all available {@link SpecialEncounterTile}s in the map
     */
    Set<SpecialEncounterTile> getEncounterTiles();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link SpecialEncounterTile} in tile units
     * @param y
     * 			y-axis coordinate of the {@link SpecialEncounterTile} in tile units
     * @return an {@link Optional}({@link SpecialEncounterTile}) that may or may not be there
     */
    Optional<SpecialEncounterTile> getEncounterTile(final int x, final int y);
    
    /**
     * @return an unmodifiable {@link Set}({@link SpecialEncounterTile}) of all removed {@link SpecialEncounterTile}s that no longer belong to the map
     */
    Set<SpecialEncounterTile> getRemovedEncounterTiles();
    
    /**
     * 
     * @param pkmnsToBeDeleted
     */
    void initDeletedEncounterTiles(final Set<String> pkmnsToBeDeleted);
    
    /**
     * Removes a {@link SpecialEncounterTile} from the map. It's used when you defeat or run away from a Legendary
     * or with loading a save
     * @param x
     * 			x-axis coordinate in tile-units
     * @param y
     * 			y-axis coordinate in tile-units
     */
    void deleteEncounterTile(final int x, final int y);
   
    /**
     * @return the only {@link PokeMarket} vendor available in the map
     */
    PokeMarket getPokeMarket();
    
    /**
     * @return an unmodifiable {@link Set}({@link PokemonEncounterZone}) of all available {@link PokemonEncounterZone}s in the map
     */
    Set<PokemonEncounterZone> getEncounterZones();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link PokemonEncounterZone} in tile units
     * @param y
     * 			y-axis coordinate of the {@link PokemonEncounterZone} in tile units
     * @return an {@link Optional}({@link PokemonEncounterZone}) that may or may not be there
     */
    Optional<PokemonEncounterZone> getEncounterZone(final int x, final int y);

    /**
     * @param x
     * 			x-axis coordinate of a given {@link Tile} in tile-units
     * @param y
     * 			y-axis coordinate of a given {@link Tile} in tile-units
     * @return  the {@link TileType} of the searched {@link Tile}
     */
    Tile.TileType getTileType(final int x, final int y);
    
    /**
     * @return an unmodifiable {@link Set}({@link WalkableZone}) of all available {@link WalkableZone}s in the map
     */
    Set<WalkableZone> getWalkableZones();
    
    /**
     * @param x
     * 			x-axis coordinate of the {@link WalkableZone} in tile units
     * @param y
     * 			y-axis coordinate of the {@link WalkableZone} in tile units
     * @return an {@link Optional}({@link WalkableZone}) that may or may not be there
     */
    Optional<WalkableZone> getWalkableZone(final int x, final int y);
    
    /**
     * Converts a x-coordinate of a carthesian plane (where the origin is in the botttom left corner)
     * to a more intuitive x-cordinate of an inverted-y-axis-carthesian plane (like the disposition of a matrix)
     * @param cellX
     * 			x-coordinate in a carthesian plane in tile unit to be converted
     * @return  x-coordinate in a inverted-y-axis-carthesian-plane
     */
    int getCorrectedCoordinateX(final int cellX);
    
    /**
     * Converts a y-coordinate of a carthesian plane (where the origin is in the botttom left corner)
     * to a more intuitive y-cordinate of an inverted-y-axis-carthesian plane (like the disposition of a matrix)
     * @param cellY
     * 			y-coordinate in a carthesian plane in tile unit to be converted
     * @return  y-coordinate in a inverted-y-axis-carthesian-plane
     */
	int getCorrectedCoordinateY(final int cellY);

	/**
	 * @return a copy of the matrix that includes all the different {@link TileType}s of each {@link Tile}
	 */
	TileType[][] getMap();
	
	/**
	 * @return a {@link Position} where you go whenever your all {@link Pokemon} get defeated.
	 * Typically it is inside a PokemonCenter
	 */
	Position getPokemonCenterSpawnPosition();
}
