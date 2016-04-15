package model.map;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import model.map.tile.PokemonEncounterTile;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.map.tile.TileNotFoundException;
import model.map.tile.Wall;
import model.map.tile.Water;
import model.trainer.Trainer;
import model.trainer.TrainerDB;

public class PokeMapImpl implements PokeMap {

	
	private Tile.TileType[][] map;
	private Set<Tile> collisions;
	private Set<Teleport> teleports;
	private Set<Sign> signs;
	private Set<Trainer> trainers;
	private Set<PokemonEncounterTile> pokemonEncounters;
	private Set<Zone> zones;
	
	private final int tileHeight;
	private final int tileWidth;
	private final int mapHeight;
	private final int mapWidth;
	
	public PokeMapImpl(final TiledMap map) {
		final TiledMapTileLayer background = ((TiledMapTileLayer) map.getLayers().get("background"));
		final TiledMapTileLayer foreground = ((TiledMapTileLayer) map.getLayers().get("foreground"));
		final TiledMapTileLayer doorLayer = ((TiledMapTileLayer) map.getLayers().get("doorLayer"));
		final TiledMapTileLayer signLayer = ((TiledMapTileLayer) map.getLayers().get("signLayer"));
		final TiledMapTileLayer encounterLayer = ((TiledMapTileLayer) map.getLayers().get("encounterLayer"));
		
		this.tileHeight = (int) background.getTileHeight();
		this.tileWidth = (int) background.getTileWidth();
		this.mapHeight = (int) background.getHeight();
		this.mapWidth = (int) background.getWidth();
		
		initMap();
		
		
		setTeleports(doorLayer);
		setSigns(signLayer);
		setBackgroundObjects(background, encounterLayer);
		
	}
	
	private void initMap() {
		this.map = new Tile.TileType[this.mapWidth][this.mapHeight];
		for (int i = 0; i < this.mapWidth ; i++) {
			for (int j = 0; j < this.mapHeight; j++) {
				this.map[i][j] = Tile.TileType.TERRAIN;
			}
		}
	}
	
	private void setBackgroundObjects(final TiledMapTileLayer background, final TiledMapTileLayer encounterLayer) {
		for (int i = 0; i < this.mapWidth; i += this.tileWidth) {
			for (int j = 0; j < this.mapHeight; j += this.tileHeight) {
				final Cell c = background.getCell(i, j);
				if (c != null) {
					String cellProperty = (String) c.getTile().getProperties().get("tileType");
					if (cellProperty.equals(Wall.tileName)) {
						this.collisions.add(new Wall(i, j));
						this.map[i / this.mapWidth][j / this.mapHeight] = TileType.WALL;
					} else if (cellProperty.equals(Water.tileName)) {
						this.collisions.add(new Water(i, j));
						this.map[i / this.mapWidth][j / this.mapHeight] = TileType.WATER;
					} else if (cellProperty.equals(PokemonEncounterTile.tileName)) {
						
						// TODO finire Mappa e zona this.pokemonEncounters.add(new PokemonEncounterTile(zone, x, y))
						this.map[i / this.mapWidth][j / this.mapHeight] = TileType.WATER;
					}
				}
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
	
	private void setTeleports(final TiledMapTileLayer doorLayer) {
		if (doorLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : doorLayer.getObjects()) {
			if (mobj.getProperties().containsKey("DOOR_X")) {
				final int pixel_x = Integer.parseInt((String)mobj.getProperties().get("x"));
				final int pixel_y = Integer.parseInt((String)mobj.getProperties().get("y"));
				final int real_x = (int) (pixel_x / this.tileWidth); 
				final int real_y = (int) (pixel_y / this.tileHeight);
				final int to_x = Integer.parseInt((String)mobj.getProperties().get("DOOR_X"));
				final int to_y = Integer.parseInt((String)mobj.getProperties().get("DOOR_Y"));
				final Teleport tmp = new Teleport(real_x, real_y, to_x, to_y);
				this.teleports.add(tmp);
			}
		}
	}

	@Override
	public Set<Teleport> getTeleports() {
		return new HashSet<>(this.teleports);
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
				final int real_x = (int) (pixel_x / this.tileWidth); 
				final int real_y = (int) (pixel_y / this.tileHeight);
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

	@Override
	public Set<TrainerDB> getTrainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getTile(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Zone> getZones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zone getZone(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importMap(TiledMap map) {
		// TODO Auto-generated method stub

	}

}
