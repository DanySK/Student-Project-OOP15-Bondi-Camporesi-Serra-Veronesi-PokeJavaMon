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
import model.player.PlayerImpl;
import model.pokemon.Stat;

public class PokeMarket extends NPC {
	
	private final Set<Item> tier0;
	private final Set<Item> tier1;
	private final Set<Item> tier2;
	
	
	public PokeMarket(int x, int y) {
		super("PokeMarketGuy", x, y, Direction.SOUTH, "Welcome to the PokeMarket!");
		this.tier0 = new HashSet<>();
		tier0.add(new Pokeball(PokeballType.Pokeball));
		tier0.add(new Potion(PotionType.POTION));
		tier0.add(new Potion(PotionType.SUPERPOTION));
		this.tier1 = new HashSet<>(tier0);
		tier1.add(new Boost(Stat.ATK));
		tier1.add(new Boost(Stat.DEF));
		tier1.add(new Pokeball(PokeballType.Greatball));
		this.tier2 = new HashSet<>(tier1);
		tier2.add(new Pokeball(PokeballType.Ultraball));
		tier2.add(new Potion(PotionType.HYPERPOTION));
		tier2.add(new Boost(Stat.SPD));
		
	}

	public Set<Item> getAvailableItems() {
		switch (PlayerImpl.getPlayer().getLastBadge()) {
		case -1 :
			return Collections.unmodifiableSet(this.tier0);
		case 0 :
			return Collections.unmodifiableSet(this.tier1);
		default :
			return Collections.unmodifiableSet(this.tier2);
		}
   	}
}
