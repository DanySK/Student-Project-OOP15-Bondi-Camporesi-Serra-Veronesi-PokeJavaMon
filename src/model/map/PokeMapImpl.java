package model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import model.map.Drawable.Direction;
import model.map.tile.BadgeTeleport;
import model.map.tile.Sign;
import model.map.tile.Teleport;
import model.map.tile.Tile;
import model.map.tile.Tile.TileType;
import model.player.Player;
import model.player.PlayerImpl;
import model.trainer.GymLeader;
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
	private Set<NPC> npcs;
	private Set<GymLeader> gymLeaders;
	private PokeMarket market;
	private TiledMap tiledMap;
	private final int mapHeight;
	private final int mapWidth;
	private final int tileHeight;
	private final int tileWidth;
	
	//TODO: Refactoring getTrainer/ect methods?
	public PokeMapImpl(final TiledMap map) {
		this.tiledMap = map;
		final TiledMapTileLayer background = ((TiledMapTileLayer) map.getLayers().get("background"));
		final TiledMapTileLayer foreground = ((TiledMapTileLayer) map.getLayers().get("foreground"));
		final MapLayer doorLayer = ((MapLayer) map.getLayers().get("doorLayer"));
		final MapLayer signLayer = ((MapLayer) map.getLayers().get("signLayer"));
		final MapLayer encounterLayer = ((MapLayer) map.getLayers().get("pokemonEncounterLayer"));
		final MapLayer zoneLayer = ((MapLayer) map.getLayers().get("zoneLayer"));
		
		this.mapHeight = (int) background.getHeight();
		this.mapWidth = (int) background.getWidth();
		this.tileHeight = (int) background.getTileHeight();
		this.tileWidth = (int) background.getTileWidth();
		
		this.collisions = new HashSet<>();
		this.signs = new HashSet<>();
		this.teleports = new HashSet<>();
		this.trainers = new HashSet<>();
		this.gymLeaders = new HashSet<>();
		this.npcs = new HashSet<>();
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
				
			} else if (cellProperty.equals(TileType.NPC.toString())) {
				this.map[tileX][tileY] = TileType.NPC;
				Direction d = Direction.NORTH;
				if (mp.get("FRONT_ID").equals("-1")) {
					d = Direction.SOUTH;
				} else if(mp.get("LEFT_ID").equals("-1")) {
					d = Direction.WEST;
				} else if (mp.get("RIGHT_ID").equals("-1")) {
					d = Direction.EAST;
				}
				final AbstractCharacter ac = importNPC(tileX, tileY, d);
				if (ac != null) {
					if (ac instanceof Trainer) {
						this.trainers.add((Trainer)ac);
					} else if (ac instanceof NPC) {
						this.npcs.add((NPC) ac);
					} else if (ac instanceof GymLeader) {
						this.gymLeaders.add((GymLeader) ac);
					}
				}
				this.collisions.add(p);

			} else if (cellProperty.equals(TileType.TELEPORT.toString()) || cellProperty.equals(TileType.BADGETELEPORT.toString())) {
				this.map[tileX][tileY] = TileType.TELEPORT;
			
			} else if (cellProperty.equals(TileType.MARKET.toString())) {
				this.map[tileX][tileY] = TileType.MARKET;
				this.market = new PokeMarket(tileX, tileY);
				this.collisions.add(p);
				
			} else if (cellProperty.equals(TileType.CENTER.toString())) {
				this.map[tileX][tileY] = TileType.CENTER;
				this.collisions.add(p);
			} else if (cellProperty.equals(TileType.START.toString())) {
				PlayerImpl.START_X = tileX;
				PlayerImpl.START_Y = tileY;
				this.map[tileX][tileY] = TileType.TERRAIN;
				PlayerImpl.getPlayer().setStartingPoint(tileX, tileY);
			}
		}
	}
	
	private AbstractCharacter importNPC(final int tileX, final int tileY, final Direction d) {
		final MapLayer trainerLayer = tiledMap.getLayers().get("trainerLayer");
		AbstractCharacter retCharacter = null;
		for (final MapObject mobj : trainerLayer.getObjects()) {
			final int trainerInMapX = getTileUnitX(mobj.getProperties().get("x", Integer.class) / this.tileWidth);
			final int trainerInMapY = getTileUnitY(mobj.getProperties().get("y", Integer.class) / this.tileHeight);

			if (trainerInMapX == tileX && trainerInMapY == tileY ) {
				final String npcType = mobj.getProperties().get("type", String.class);
				if (npcType != null) {
					if ((npcType.equals(Trainer.TYPE_TRAINER_NAME) || npcType.equals(GymLeader.TYPE_GYM_LEADER))) {
						final ArrayList<String>	pkmns_lvl = new ArrayList<>();
					
						for (int i = 1; i <= 6; i++) {
							if (mobj.getProperties().containsKey(i + "_POKEMON=LVL") && !mobj.getProperties().get(i + "_POKEMON=LVL", String.class).isEmpty()) {
								pkmns_lvl.add(mobj.getProperties().get(i + "_POKEMON=LVL", String.class));
							}
						}
						MapProperties mp = mobj.getProperties();
						final String trainerName = mp.get("name",String.class);
						final String initMessage = mp.get("initMessage", String.class);
						final String winMessage = mp.get("winMessage", String.class);
						final String lostMessage = mp.get("lostMessage", String.class);
						final int money = Integer.parseInt(mp.get("money", String.class));
						final int trainerID = Integer.parseInt(mp.get("trainerID", String.class));
						if (npcType.equals(Trainer.TYPE_TRAINER_NAME)) {
							retCharacter = StaticTrainerFactory.createTrainer(trainerName, d, false, tileX, tileY, pkmns_lvl, 
									initMessage, lostMessage, winMessage, money, trainerID);
						} else {
							final int badge = Integer.parseInt(mp.get("badgeID", String.class));
							retCharacter = StaticTrainerFactory.createGymLeader(trainerName, d, false, tileX, tileY, pkmns_lvl, 
									initMessage, lostMessage, winMessage, money,trainerID, badge);
						}
					} else if (npcType.equals(TileType.NPC.toString())) {
						retCharacter = new NPC(mobj.getProperties().get("name", String.class), tileX, tileY, d, mobj.getProperties().get("message", String.class));
					} 
				}
			}
		}
		if (retCharacter == null) {
			System.out.println("if charNULL postion: " + new Position(tileX, tileY));
		}
		return retCharacter;
	}
	
	@Override
	public boolean isOutOfBounds(final int x, final int y) {
		return x > this.mapWidth ||  x < 0 || y > this.mapHeight || y < 0;
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
	public int getTileWidth() {
		return this.tileWidth;
	}

	@Override
	public int getTileHeight() {
		return this.tileHeight;
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
		if (this.isOutOfBounds(x, y)) {
			return false;
		}
		
		boolean isCollision = false;
		for (final Position p : this.collisions) {
			if (p.getX() == x && p.getY() == y) {
				isCollision = true;
			}
		}
		return this.map[x][y].isWalkable() && !isCollision;
	}
	
	@Override
	public TileType getTileNextToPlayer(final Direction d) {
		Player p = PlayerImpl.getPlayer();
		final int tmpX = p.getTileX() + ((d == Direction.EAST || d == Direction.WEST) ? ((d == Direction.EAST) ? +1 : -1) : 0) ; 
		final int tmpY = p.getTileY() + ((d == Direction.SOUTH || d == Direction.NORTH) ? ((d == Direction.SOUTH) ? +1 : -1) : 0) ;

		return (this.isOutOfBounds(tmpX, tmpY)) ? TileType.WALL : this.map[tmpX][tmpY];
	}
	
	
	private void setTeleports(final MapLayer doorLayer) {
		if (doorLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : doorLayer.getObjects()) {
			if (mobj.getProperties().containsKey("DOOR_X")) {
				final int real_x = this.getTileUnitX(mobj.getProperties().get("x", Integer.class) / this.tileWidth);
				final int real_y = this.getTileUnitY(mobj.getProperties().get("y", Integer.class) / this.tileHeight);
				final int to_x = Integer.parseInt((String)mobj.getProperties().get("DOOR_X"));
				final int to_y = Integer.parseInt((String)mobj.getProperties().get("DOOR_Y"));
				Teleport tmp;
				if (mobj.getProperties().containsKey("badgesRequired")) {
					tmp = new BadgeTeleport(real_x, real_y, to_x, to_y, Integer.parseInt(mobj.getProperties().get("badgesRequired", String.class)));
				} else {
					tmp = new Teleport(real_x, real_y, to_x, to_y);
				}		
				this.teleports.add(tmp);
			}
		}
	}

	@Override
	public Set<Teleport> getTeleports() {
		return Collections.unmodifiableSet(this.teleports);
	}

	@Override
	public Optional<Teleport> getTeleport(int fromX, int fromY) {
		if (!this.isOutOfBounds(fromX, fromY) && this.map[fromX][fromY] == TileType.TELEPORT) {
			for (final Teleport t : teleports) {
				if (t.getFromX() == fromX && t.getFromY() == fromY) {
					return Optional.of(t);
				}
			}
		}
		return Optional.empty();
	}
	
	private void setSigns(final MapLayer signLayer) {
		if (signLayer.getObjects() == null) {
			throw new IllegalArgumentException("Layer does not contain objects");
		} 
		for (final MapObject mobj : signLayer.getObjects()) {
			if (mobj.getProperties().containsKey("signMessage")) {
				final int real_x = this.getTileUnitX(this.getTileUnitX(mobj.getProperties().get("x", Integer.class) / this.tileWidth));
				final int real_y = this.getTileUnitY(mobj.getProperties().get("y", Integer.class) / this.tileHeight);
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
	public Optional<Sign> getSign(int x, int y) {
		if (!this.isOutOfBounds(x, y) && this.map[x][y] == TileType.SIGN) {
			for (final Sign s : this.signs) {
				if (s.getTileX() == x & s.getTileY() == y) {
					return Optional.of(s);
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public Tile.TileType getTileType(int x, int y) {
		return this.isOutOfBounds(x, y) ? TileType.WALL : this.map[x][y];
	}

	private void setWalkableZones(final MapLayer zoneLayer) {
		for (final MapObject z : zoneLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) z;
			final int width = (int) (rect.getRectangle().width / this.tileWidth);
			final int height = (int) (rect.getRectangle().height / this.tileHeight) - 1;
			final int real_x = this.getTileUnitX((int) (rect.getRectangle().x / this.tileWidth));
			final int real_y = this.getTileUnitY((int) (rect.getRectangle().y / this.tileHeight)) - height;
			final String musicPath = (String) z.getProperties().get("music");
			final String name = (String) z.getProperties().get("zoneType");
			this.walkableZones.add(new WalkableZone(name, real_x, real_y, width, height, "/" + musicPath));
			
		}
	}

	@Override
	public Set<WalkableZone> getWalkableZones() {
		return Collections.unmodifiableSet(this.walkableZones);
	}

	@Override
	public Optional<WalkableZone> getWalkableZone(int x, int y) {
		for (final WalkableZone wz : this.walkableZones) {
			if (wz.contains(x, y)) {
				return Optional.of(wz);
			}
		}
		
		return Optional.empty();
	}
	
	@Override
	public Set<Trainer> getTrainers() {
		return Collections.unmodifiableSet(this.trainers);
	}
	@Override
	public Optional<Trainer> getTrainer(int x, int y) {
		if (!this.isOutOfBounds(x, y) && this.map[x][y] == TileType.NPC) {
			for (final Trainer t : this.trainers) {
				if (t.getTileX() == x && t.getTileY() == y) {
					return Optional.of(t);
				}
			}
		}
		return Optional.empty();
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
    
	@Override
	public Set<GymLeader> getGymLeaders() {
		return Collections.unmodifiableSet(this.gymLeaders);
	}

	@Override
	public Optional<GymLeader> getGymLeader(int x, int y) {
		if (!this.isOutOfBounds(x, y) && this.map[x][y] == TileType.NPC) {
			for (final GymLeader gl : this.gymLeaders) {
				if (gl.getTileX() == x && gl.getTileY() == y) {
					return Optional.of(gl);
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public void initGymLeaders(final int badges) {
		for (final GymLeader gl : this.gymLeaders) {
			if (gl.getBadge() <= badges) {
				gl.defeat();
			}
		}
	}

	@Override
	public Set<NPC> getNPCs() {
		return Collections.unmodifiableSet(this.npcs);
	}

	@Override
	public Optional<NPC> getNPC(int x, int y) {
		if (!this.isOutOfBounds(x, y) && this.map[x][y] == TileType.NPC) {
			for (final NPC npcs : this.npcs) {
				if (npcs.getTileX() == x && npcs.getTileY() == y) {
					return Optional.of(npcs);
				}
			}
		}
		return Optional.empty();
	}
	
	@Override
	public PokeMarket getPokeMarket() {
		return this.market;
	}
	
	private void setPokemonEncounterZones(final MapLayer encounterLayer) {
		for (final MapObject z : encounterLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) z;
			final int real_x = this.getTileUnitX((int) (rect.getRectangle().x / this.tileWidth));
			final int width = (int) (rect.getRectangle().width / this.tileWidth);
			final int height = (int) (rect.getRectangle().height / this.tileHeight) - 1;
			final int real_y = this.getTileUnitY((int) (rect.getRectangle().y / this.tileHeight)) - height;
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
	public Optional<PokemonEncounterZone> getEncounterZone(final int x, final int y) {
		if (this.map[x][y] == TileType.POKEMON_ENCOUNTER) {
			for (final PokemonEncounterZone pez : this.pokemonEncounterZones) {
				if (pez.contains(x, y)) {
					return Optional.of(pez);
				}
			}
		}
		return Optional.empty();
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

	public TiledMap getTiledMap() {
	    return this.tiledMap;
	}
}
