package test;

import model.pokemon.PokemonType;
import model.pokemon.WeaknessTable;

public class TestWeakness {

	public static void main(String[] args) {
		for (final PokemonType p : PokemonType.values()) {
			if (p != PokemonType.NONE) {
				for (final PokemonType p2 : PokemonType.values()) {
					System.out.println("ATK_TYPE: " + p + ", DEF_TYPE: " + p2 + ", result = " + WeaknessTable.getWeaknessTable().getMultiplierAttack(p, p2, PokemonType.NONE));
				}
			}
		}
		
	}

}
