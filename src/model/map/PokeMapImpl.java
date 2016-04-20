package model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import model.map.Drawable.Direction;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.trainer.StaticTrainerFactory;
import model.trainer.Trainer;

public class PokeMapImpl implements PokeMap {

	
	private Tile.TileType[][] map;
	private Set<Position> collisions;
	private Set<Teleport> teleports;
	private Set<Sign> signs;
	private Set<Trainer> trainers;
	private Set<PokemonEncounterZone> pokemonEncounterZones;
	private Set<WalkableZone> walkableZones;
	
	private final int mapHeight;
	private final int mapWidth;
	
	//Testato EncounterZones, Trainers, 
	public PokeMapImpl(final TiledMap map) {
		//TODO: cambiare il modo in cui assegna i teleport perché nella mappa di tiled su e giu sono invertiti
		final TiledMapTileLayer background = ((TiledMapTileLayer) map.getLayers().get("background"));
		final TiledMapTileLayer foreground = ((TiledMapTileLayer) map.getLayers().get("foreground"));
		final MapLayer doorLayer = ((MapLayer) map.getLayers().get("doorLayer"));
		final MapLayer signLayer = ((MapLayer) map.getLayers().get("signLayer"));
		final MapLayer encounterLayer = ((MapLayer) map.getLayers().get("pokemonEncounterLayer"));
		final MapLayer zoneLayer = ((MapLayer) map.getLayers().get("zoneLayer"));
		
		this.mapHeight = (int) background.getHeight();
		this.mapWidth = (int) background.getWidth();
		
		this.collisions = new HashSet<>();
		this.signs = new HashSet<>();
		this.teleports = new HashSet<>();
		this.trainers = new HashSet<>();
		this.pokemonEncounterZones = new HashSet<>();
		this.walkableZones = new HashSet<>();
				
				
		
		initMap();
		
		
		setTeleports(doorLayer);
		setSigns(signLayer);
		
		setPokemonEncounterZones(encounterLayer);
		setWalkableZones(zoneLayer);
		setBackgroundAndForeground(background, foreground, encounterLayer);
		
	}
	
	private void initMap() {
		this.map = new Tile.TileType[this.mapWidth][this.mapHeight];
		for (int i = 0; i < this.mapWidth ; i++) {
			for (int j = 0; j < this.mapHeight; j++) {
				this.map[i][j] = Tile.TileType.TERRAIN;
			}
		}
	}
	
	private void setBackgroundAndForeground(final TiledMapTileLayer background, final TiledMapTileLayer foreground, final MapLayer encounterLayer) {
		for (int i = 0; i < this.mapWidth; i += 1) {
			for (int j = 0; j < this.mapHeight; j += 1) {
				
				final Cell backCell = background.getCell(getTileUnitX(i), getTileUnitY(j));
				final Cell frontCell = foreground.getCell(getTileUnitX(i), getTileUnitY(j));
				
				if (backCell != null) {
					addCell(backCell, i, j, encounterLayer);
				}
				if (frontCell != null) {
					addCell(frontCell, i, j, encounterLayer);
				}
				
			}
		}
	}
	
	private void addCell(final Cell c, final int tileX, final int tileY, final MapLayer encounterLayer) {
		if (c != null) {
			MapProperties mp = c.getTile().getProperties();
			String cellProperty = (mp.get("tileType", String.class)).toUpperCase();
			Position p = new Position(tileX, tileY);
//			System.out.println("CellPROPR=" + cellProperty);
//			System.out.println();
			
			if (cellProperty.equals(TileType.WALL.toString())) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.WALL;
			
			} else if (cellProperty.equals(TileType.WATER.toString())) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.WATER;
				
			} else if (cellProperty.equals(TileType.TREE.toString())) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.TREE;
				
			} else if (cellProperty.equals(TileType.POKEMON_ENCOUNTER.toString())) {
				this.map[tileX][tileY] = TileType.POKEMON_ENCOUNTER;
				
			} else if (cellProperty.equals(TileType.SIGN.toString())) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.SIGN;
				
			} else if (cellProperty.equals(TileType.TRAINER.toString())) {
				this.map[tileX][tileY] = TileType.TRAINER;
				Direction d = Direction.NORTH;
				if (mp.get("FRONT_ID").equals("-1")) {
					d = Direction.SOUTH;
				} else if(mp.get("LEFT_ID").equals("-1")) {
					d = Direction.WEST;
				} else if (mp.get("RIGHT_ID").equals("-1")) {
					d = Direction.EAST;
				}
				final ArrayList<String>	pkmns_lvl = new ArrayList<>();
				for (int i = 1; i <= 6; i++) {
					if (mp.containsKey(i + "_POKEMON=LVL") && !mp.get(i + "_POKEMON=LVL", String.class).isEmpty()) {
						pkmns_lvl.add(mp.get(i + "_POKEMON=LVL", String.class));
					}
				}
				this.map[tileX][tileY] = TileType.TRAINER;
				this.trainers.add(StaticTrainerFactory.createTrainer(mp.get("name", String.class), d, false, tileX, tileY, pkmns_lvl , mp.get("initMessage", String.class), mp.get("lostMessage", String.class), mp.get("wonMessage", String.class), Integer.parseInt(mp.get("money", String.class)), Integer.parseInt(mp.get("trainerID", String.class))));

			} else if (cellProperty.equals(TileType.TELEPORT.toString())) {
				this.map[tileX][tileY] = TileType.TELEPORT;
			}
		}
	}
	
	
	
	@Override
	public float getMapHeight() {
		return this.mapHeight;
	}

	@Override
	public float getMapWidth() {
		return this.mapWidth;
	}
	
	@Override
	public Set<Position> getCollisions() {
		return Collections.unmodifiableSet(this.collisions);
	}

	@Override
	public void removeCollision(Position p) {
		if (this.collisions.contains(p)) {
			this.collisions.remove(p);
			return;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void addCollision(Position p) {
		this.collisions.add(p);
	}

	@Override
	public boolean isWalkable(int x, int y) {
		boolean isCollision = false;
		for (final Position p : this.collisions) {
			if (p.getX() == x && p.getY() == y) {
				isCollision = true;
			}
		}
		return this.map[x][y].isWalkable() && !isCollision;
	}
	
	
	private void setTeleports(final MapLayer doorLayer) {
		if (doorLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : doorLayer.getObjects()) {
			if (mobj.getProperties().containsKey("DOOR_X")) {
				final int real_x = this.getTileUnitX(mobj.getProperties().get("x", Integer.class) / TILE_WIDTH);
				final int real_y = this.getTileUnitY(mobj.getProperties().get("y", Integer.class) / TILE_HEIGHT);
				final int to_x = Integer.parseInt((String)mobj.getProperties().get("DOOR_X"));
				final int to_y = Integer.parseInt((String)mobj.getProperties().get("DOOR_Y"));
				final Teleport tmp = new Teleport(real_x, real_y, to_x, to_y);
				this.teleports.add(tmp);
			}
		}
	}

	@Override
	public Set<Teleport> getTeleports() {
		return Collections.unmodifiableSet(this.teleports);
	}

	@Override
	public Teleport getTeleport(int fromX, int fromY) {
		if (this.map[fromX][fromY] == TileType.TELEPORT) {
			for (final Teleport t : teleports) {
				if (t.getFromX() == fromX && t.getFromY() == fromY) {
					return t;
				}
			}
		}
		return null;
	}
	
	private void setSigns(final MapLayer signLayer) {
		if (signLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : signLayer.getObjects()) {
			if (mobj.getProperties().containsKey("signMessage")) {
				final int real_x = this.getTileUnitX(this.getTileUnitX(mobj.getProperties().get("x", Integer.class) / TILE_WIDTH));
				final int real_y = this.getTileUnitY(mobj.getProperties().get("y", Integer.class) / TILE_HEIGHT);
				final Sign tmp = new Sign(real_x, real_y, (String) mobj.getProperties().get("signMessage"));
				this.signs.add(tmp);
			}
		}
	}
	
	@Override
	public Set<Sign> getSigns() {
		return new HashSet<Sign>(this.signs);
	}

	@Override
	public Sign getSign(int x, int y) {
		if (this.map[x][y] == TileType.SIGN) {
			for (final Sign s : this.signs) {
				if (s.getTileX() == x & s.getTileY() == y) {
					return s;
				}
			}
		}
		return null;
	}

	@Override
	public Tile.TileType getTileType(int x, int y) {
		return this.map[x][y];
	}
	//TODO: REWORKARE LE POSIZIONI PERCHE X = MAP_HEIGHT-X
	private void setWalkableZones(final MapLayer zoneLayer) {
		for (final MapObject z : zoneLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) z;
			final int width = (int) (rect.getRectangle().width / TILE_WIDTH);
			final int height = (int) (rect.getRectangle().height / TILE_HEIGHT) - 1;
			final int real_x = this.getTileUnitX((int) (rect.getRectangle().x / TILE_WIDTH));
			final int real_y = this.getTileUnitY((int) (rect.getRectangle().y / TILE_HEIGHT)) - height;
			final String musicPath = (String) z.getProperties().get("musicPath");
			final String name = (String) z.getProperties().get("zoneType");
			this.walkableZones.add(new WalkableZone(name, real_x, real_y, width, height, musicPath));
			
		}
	}

	@Override
	public Set<WalkableZone> getWalkableZones() {
		return Collections.unmodifiableSet(this.walkableZones);
	}

	@Override
	public WalkableZone getWalkableZone(int x, int y) {
		for (final WalkableZone wz : this.walkableZones) {
			if (wz.contains(x, y)) {
				return wz;
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	@Override
	public Set<Trainer> getTrainers() {
		return Collections.unmodifiableSet(this.trainers);
	}
	@Override
	public Trainer getTrainer(int x, int y) {
		if (this.map[x][y] == TileType.TRAINER) {
			for (final Trainer t : this.trainers) {
				if (t.getTileX() == x && t.getTileY() == y) {
					return t;
				}
			}
		}
		return null;
	}
	
	public void initTrainers(final Map<Integer, Boolean> trainerID_isDefeated) {
		for (final Entry<Integer, Boolean> e : trainerID_isDefeated.entrySet()) {
			if (!e.getValue()) {
				continue;
			}
			for (final Trainer t : this.trainers) {
				if (e.getKey() == t.getID()) {
					t.defeat();
				}
			}
		}
	}
    
	
	private void setPokemonEncounterZones(final MapLayer encounterLayer) {
		for (final MapObject z : encounterLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) z;
			final int real_x = this.getTileUnitX((int) (rect.getRectangle().x / TILE_WIDTH));
			final int width = (int) (rect.getRectangle().width / TILE_WIDTH);
			final int height = (int) (rect.getRectangle().height / TILE_HEIGHT) - 1;
			final int real_y = this.getTileUnitY((int) (rect.getRectangle().y / TILE_HEIGHT)) - height;
			final int id = Integer.parseInt(rect.getProperties().get("zoneID", String.class));
			final int avgLvl = Integer.parseInt(rect.getProperties().get("avgLvl", String.class));
			final String pkmnList = rect.getProperties().get("pokemonList", String.class);
			this.pokemonEncounterZones.add(new PokemonEncounterZone(id, pkmnList, avgLvl, real_x, real_y, width, height));
		}
	}

	@Override
	public Set<PokemonEncounterZone> getEncounterZones() {
		return Collections.unmodifiableSet(this.pokemonEncounterZones);
	}
	
	@Override
	public PokemonEncounterZone getEncounterZone(final int x, final int y) {
		if (this.map[x][y] == TileType.POKEMON_ENCOUNTER) {
			for (final PokemonEncounterZone pez : this.pokemonEncounterZones) {
				if (pez.contains(x, y)) {
					return pez;
				}
			}
		}
		return null;
	}
	
	
	@Override
	public int getTileUnitX(final int cellX) {
		return cellX;
	}
	
	@Override
	public int getTileUnitY(final int cellY) {
		return this.mapHeight - cellY - 1;
	}
	
	@Override
	public Tile.TileType[][] getMap() {
		return Arrays.copyOf(this.map, this.map.length);
	}

}
