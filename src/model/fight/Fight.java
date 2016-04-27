package model.fight;

import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.items.Item;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.CannotUseItemInBattleException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;



public interface Fight {
	
	public void run() throws CannotEscapeFromTrainerException;
	
	public void change(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException;
	
	//zaino->scelgo oggetto da usare->chiamo questo metodo
	public void identifyItem(final Item itemToUse) throws CannotUseItemInBattleException;
	
	//dopo aver chiamato il metodo sopra, il controller, controlla: se l'oggetto è 
	//una ball, chiama useItem; un boost, chiama useItem;
	//una potion, apre schermata squadra, dopo aver scelto pkm, chiama useItem
	public void useItem(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException;
	
	public void useMove(final Move move);
	
}