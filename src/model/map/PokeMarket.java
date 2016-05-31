package model.map;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.items.Boost;
import model.items.Item;
import model.items.Pokeball;
import model.items.Pokeball.PokeballType;
import model.items.Potion;
import model.items.Potion.PotionType;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Stat;
import model.trainer.GymLeader;

/**
 * Special type of {@link NPC} who sells {@link Item}s.
 * New {@link Item}s may appear as soon as the {@link Player} gets more badges by defeating {@link GymLeader}s
 */
public class PokeMarket extends NPC {
	
	//different tiers for different number of badges
	private final Set<Item> tier0;
	private final Set<Item> tier1;
	private final Set<Item> tier2;
	
	/**
	 * Creates a new {@link PokeMarket} seller on the specified {@link Position}
	 * @param x
	 * 			x-axis coordinate in tile-units
	 * @param y
	 * 			y-axis coordinate in tile-units
	 */
	public PokeMarket(final int x, final int y) {
		super("PokeMarketGuy", x, y, Direction.SOUTH, "Welcome to the PokeMarket!");
		this.tier0 = new HashSet<>();
		tier0.add(new Pokeball(PokeballType.Pokeball));
		tier0.add(new Potion(PotionType.Potion));
		tier0.add(new Potion(PotionType.Superpotion));
		this.tier1 = new HashSet<>(tier0);
		tier1.add(new Boost(Stat.ATK));
		tier1.add(new Boost(Stat.DEF));
		tier1.add(new Pokeball(PokeballType.Greatball));
		this.tier2 = new HashSet<>(tier1);
		tier2.add(new Pokeball(PokeballType.Ultraball));
		tier2.add(new Potion(PotionType.Hyperpotion));
		tier2.add(new Boost(Stat.SPD));
	}

	/**
	 * @return the available {@link Item}s to be bought, according to the {@link Player}'s badges
	 */
	public Set<Item> getAvailableItems() {
		switch (PlayerImpl.getPlayer().getLastBadge()) {
		case 0 :
			return Collections.unmodifiableSet(this.tier0);
		case 1 :
			return Collections.unmodifiableSet(this.tier1);
		default :
			return Collections.unmodifiableSet(this.tier2);
		}
   	}
}
