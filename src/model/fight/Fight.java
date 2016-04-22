package model.fight;

import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.squad.Squad;

import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.CannotUseItemInBattleException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import model.box.PokemonNotFoundException;
import model.items.Item;
import model.items.Item.ItemType;


public interface Fight {
	
	public PokemonInBattle checkPkmSquad(final Squad pkmSquad);
	
	public boolean run() throws CannotEscapeFromTrainerException;
	
	public void change(final int pkmPos) throws PokemonIsExhaustedException, PokemonIsFightingException;
	
	public ItemType identifyItem(final Item itemToUse) throws CannotUseItemInBattleException;
	
	public void useBoost(final Item itemToUse) throws PokemonNotFoundException;
	
	public boolean usePokeball(final Item itemToUse) throws CannotCaughtTrainerPkmException;
	
	public void usePotion(final Item itemToUse, final PokemonInBattle pkmTarget) throws PokemonIsExhaustedException, PokemonNotFoundException;
	
	public boolean isAllyFastest();
	
	public int getAttacksToDo();
	
	public boolean isExhausted(final PokemonInBattle pkm);
	
	public void setMoveUsed(final Move moveUsed);
	
	public Move getMoveUsed();
	
	public PokemonInBattle getAllyPkm();
	
	public PokemonInBattle getEnemyPkm();
	
	public boolean isAllyPkm(final PokemonInBattle pkm);
	
	public double isEffective(final PokemonInBattle stricker, final PokemonInBattle stricked);

	public void applyDamage(PokemonInBattle stricker, PokemonInBattle stricked);
	
	public boolean applyMoveOnBoost(PokemonInBattle stricker, PokemonInBattle stricked);
	
	public Move enemyMove();
	
	public int getExp();
	
	public boolean giveExpAndCheckLvlUp(final int exp);
	
	public List<PokemonInBattle> getPkmsThatMustEvolve();
	
}