package model.map;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import model.map.tile.PokemonEncounterTile;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Terrain;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.map.tile.TileNotFoundException;
import model.map.tile.Wall;
import model.map.tile.Water;
import model.trainer.Trainer;
import model.trainer.TrainerDB;

public class PokeMapImpl implements PokeMap {

	
	private Tile.TileType[][] map;
	private Set<Position> collisions;
	private Set<Teleport> teleports;
	private Set<Sign> signs;
	private Set<Trainer> trainers;
	private Set<PokemonEncounterTile> pokemonEncounters;
	private Set<WalkableZone> walkableZones;
	
	private final int mapHeight;
	private final int mapWidth;
	
	public PokeMapImpl(final TiledMap map) {
		final TiledMapTileLayer background = ((TiledMapTileLayer) map.getLayers().get("background"));
		final TiledMapTileLayer foreground = ((TiledMapTileLayer) map.getLayers().get("foreground"));
		final TiledMapTileLayer doorLayer = ((TiledMapTileLayer) map.getLayers().get("doorLayer"));
		final TiledMapTileLayer signLayer = ((TiledMapTileLayer) map.getLayers().get("signLayer"));
		final TiledMapTileLayer encounterLayer = ((TiledMapTileLayer) map.getLayers().get("encounterLayer"));
		final TiledMapTileLayer zoneLayer = ((TiledMapTileLayer) map.getLayers().get("zoneLayer"));
		
		this.mapHeight = (int) background.getHeight();
		this.mapWidth = (int) background.getWidth();
		
		initMap();
		
		
		setTeleports(doorLayer);
		setSigns(signLayer);
		
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
	
	private void setBackgroundAndForeground(final TiledMapTileLayer background, final TiledMapTileLayer foreground, final TiledMapTileLayer encounterLayer) {
		for (int i = 0; i < this.mapWidth; i += TILE_WIDTH) {
			for (int j = 0; j < this.mapHeight; j += TILE_HEIGHT) {
				final Cell backCell = background.getCell(i, j);
				final Cell frontCell = foreground.getCell(i, j);
				
				addCell(backCell, i/TILE_WIDTH, j/TILE_HEIGHT);
				addCell(frontCell, i/TILE_WIDTH, j/TILE_HEIGHT);
				
			}
		}
	}
	
	private void addCell(final Cell c, final int tileX, final int tileY) {
		if (c != null) {
			String cellProperty = (String) c.getTile().getProperties().get("tileType");
			Position p = new Position(tileX, tileY);

			if (cellProperty.equals(Wall.TILE_NAME)) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.WALL;
			} else if (cellProperty.equals(Water.TILE_NAME)) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.WATER;
			} else if (cellProperty.equals(PokemonEncounterTile.TILE_NAME)) {
				
				// TODO finire Mappa e zona this.pokemonEncounters.add(new PokemonEncounterTile(zone, x, y))
				this.map[tileX / this.mapWidth][tileY / this.mapHeight] = TileType.POKEMON_ENCOUNTER;
			} else if (cellProperty.equals(Sign.TILE_NAME)) {
				this.collisions.add(p);
				this.map[tileX][tileY] = TileType.SIGN;
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
		return this.map[x][y].isWalkable();
	}
	
	
	private void setTeleports(final TiledMapTileLayer doorLayer) {
		if (doorLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : doorLayer.getObjects()) {
			if (mobj.getProperties().containsKey("DOOR_X")) {
				final int pixel_x = Integer.parseInt((String)mobj.getProperties().get("x"));
				final int pixel_y = Integer.parseInt((String)mobj.getProperties().get("y"));
				final int real_x = (int) (pixel_x / TILE_WIDTH); 
				final int real_y = (int) (pixel_y / TILE_HEIGHT);
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
		for (final Teleport t : teleports) {
			if (t.getFromX() == fromX && t.getFromY() == fromY) {
				return t;
			}
		}
		return null;
	}

	@Override
	public Teleport getTeleport(Tile t) {
		for (final Teleport telep : this.teleports) {
			if (t.getTileX() == telep.getFromX() || t.getTileY() == telep.getFromY()) {
				return telep;
			}
		}
		return null;
	}
	
	
	private void setSigns(final TiledMapTileLayer signLayer) {
		if (signLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : signLayer.getObjects()) {
			if (mobj.getProperties().containsKey("SIGN_TEXT")) {
				final int pixel_x = Integer.parseInt((String)mobj.getProperties().get("x"));
				final int pixel_y = Integer.parseInt((String)mobj.getProperties().get("y"));				
				final int real_x = (int) (pixel_x / TILE_WIDTH);
				final int real_y = (int) (pixel_y / TILE_HEIGHT);
				final Sign tmp = new Sign(real_x, real_y, (String) mobj.getProperties().get("SIGN_TEXT"));
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
		for (final Sign s : this.signs) {
			if (s.getTileX() == x & s.getTileY() == y) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Sign getSign(Tile t) throws TileNotFoundException {		
		for (final Sign s : this.signs) {
			if (t.getTileX() == s.getTileX() || t.getTileY() == s.getTileY()) {
				return s;
			}
		}
		return null;
	}

	
	public Set<TrainerDB> getTrainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile.TileType getTileType(int x, int y) {
		return this.map[x][y];
	}
	
	private void setWalkableZones(final TiledMapTileLayer zoneLayer) {
		for (final MapObject z : zoneLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) z;
			final int real_x = (int) (rect.getRectangle().x / TILE_WIDTH);
			final int real_y = (int) (rect.getRectangle().y / TILE_HEIGHT);
			final int width = (int) (rect.getRectangle().width / TILE_WIDTH);
			final int height = (int) (rect.getRectangle().height / TILE_HEIGHT);
			final String musicPath = (String) z.getProperties().get("musicPath");
			final String name = (String) z.getProperties().get("zoneType");
			this.walkableZones.add(new WalkableZone(name, real_x, real_y, width, height, musicPath));
			
		}
	}

	@Override
	public Set<WalkableZone> getZones() {
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
	public void importMap(TiledMap map) {
		// TODO Auto-generated method stub

	}

	@Override
	public TrainerDB getTrainer(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PokemonEncounterTile> getPkmnEncounterTiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PokemonEncounterTile getPokemonEncounterTile(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
