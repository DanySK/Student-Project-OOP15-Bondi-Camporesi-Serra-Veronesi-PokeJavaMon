package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.FightController;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;


public class FightVsTrainer extends FightVsWildPkm{
	private final static int STANDARD_EFFECTIVENESS_VALUE = 1;
	private Trainer trainer;
	private Map<PokemonInBattle, Map<Stat, Double>> enemyPkmsBoosts = new HashMap<>();
	
	public FightVsTrainer(Trainer trainer) {
		super(trainer.getSquad().getPokemonList().get(FIRST_ELEM));
		this.trainer = trainer;
		for(PokemonInBattle pkm : trainer.getSquad().getPokemonList()){
			this.enemyPkmsBoosts.put(pkm, createBoostsMap());
		}
	}
	
	@Override
	protected boolean applyRun() throws CannotEscapeFromTrainerException{
		throw new CannotEscapeFromTrainerException();
	}
	
	@Override
	protected boolean useBall(final Item itemToUse) throws CannotCaughtTrainerPkmException{
		throw new CannotCaughtTrainerPkmException();
	}
	
	@Override
	protected boolean setIsAllyFastest(){
		return isAllyFastest = ((allyPkm.getStat(Stat.SPD) * allyPkmsBoosts.get(allyPkm).get(Stat.SPD))
				>= (enemyPkm.getStat(Stat.SPD) * this.enemyPkmsBoosts.get(enemyPkm).get(Stat.SPD)));
	}
	
	@Override
	protected double getEnemyBoost(Stat stat){
		return enemyPkmsBoosts.get(enemyPkm).get(stat);
	}
	
	@Override
	public void setEnemyBoost(Stat stat, Double d){
		enemyPkmsBoosts.get(enemyPkm).replace(stat, d);
	}
	
	@Override
	protected Move calculationEnemyMove(){
		Move move = enemyPkm.getCurrentMoves().get(FIRST_ELEM);
		boolean superEffective = false;
		//cerca di ritornare prima una mossa superefficace, 
		//se non la trovo, ritorna la mossa che fa più danno
		//se non ha mosse che fanno danno, usa la prima mossa
		List<Move> moves = new ArrayList<>();
		for(Move mov : enemyPkm.getCurrentMoves()){
			if(mov != null){
				moves.add(mov);
			}
		}
		for(Move m : moves){
			if(m.getStat() == Stat.HP){
				if(4 == table.getMultiplierAttack(m.getType(), allyPkm.getPokemon().getFirstType(), allyPkm.getPokemon().getSecondType())){
					move = m;
					break;
				}
				else if(2 == table.getMultiplierAttack(m.getType(), allyPkm.getPokemon().getFirstType(), allyPkm.getPokemon().getSecondType())){
					superEffective = true;
					move = m;
				}
				else if(move.getValue() < m.getValue() && !superEffective){
					move = m;
				}
			}
		}
		return move;
	}
	
	@Override
	public int getExp(){
		return (int) (expBaseCalculation() * 1.5) / 7; 
	}
	
	@Override
	public void moveTurn(final Move move){
		int exp;
		int attacksDone = 0;
		boolean isEnd = false;
		boolean turnOrder = setIsAllyFastest();
		while(attacksDone < ATTACKS_TO_DO && !isEnd){
			if(turnOrder){
				allyTurn(move);
				if(isEnemyExhausted){
					final PokemonInBattle allyPkmNotUpdated = allyPkm;
					final Map<Stat, Double> allyPkmBoost = allyPkmsBoosts.remove(allyPkmNotUpdated);
					exp = getExp();
					giveExpAndCheckLvlUp(exp);
					isEnd = true;
					allyPkmsBoosts.put(allyPkm, allyPkmBoost);
				}
			}
			else{
				enemyTurn();
				if(isAllyExhausted){
					isEnd = true;
				}
			}
			turnOrder = !turnOrder;
			attacksDone += 1;
		}
		if(attacksDone == 2){
			if(isAllyFastest){
				if(isAllyExhausted){
					//alleato attacca, nemico attacca, pokemon alleato esausto
					FightController.getController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null/*exp*/);
				}
				else{
					//alleato attacca, nemico attacca, pokemon alleato sopravvive
					FightController.getController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null/*exp*/);
				}
			}
			else{
				if(isEnemyExhausted){
					//nemico attacca, alleato attacca, pokemon nemico esausto
					if(checkLose(trainer.getSquad())){
						player.beatTrainer(trainer);
						FightController.getController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null/*exp*/);
					}
					else{
						trainerChange();
						FightController.getController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, enemyPkm, null/*exp*/);
					}
				}
				else{
					//nemico attacca, alleato attacca, pokemon nemico sopravvive
					FightController.getController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null/*exp*/);
				}
			}
		}
		else{
			if(isAllyFastest){
				//alleato attacca per primo, pkm nemico esausto
				if(checkLose(trainer.getSquad())){
					player.beatTrainer(trainer);
					FightController.getController().resolveAttack(move, allyEff, null, null, isAllyFastest, false, null, null/*exp*/);
				}
				else{
					trainerChange();
					FightController.getController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, enemyPkm, null/*exp*/);
				}
			}
			else{
				//nemico attaccata per primo, pkm alleato esausto
				FightController.getController().resolveAttack(null, null, enemyMove, enemyEff, isAllyFastest, false, null, null/*exp*/);
			}
		}
		reset();
	}
	
	protected void trainerChange(){
		//manda il primo pkm che trova e che ha un tipo superefficace contro l'allyPkm
		for(PokemonInBattle pkm : this.trainer.getSquad().getPokemonList()){
			if(STANDARD_EFFECTIVENESS_VALUE < table.getMultiplierAttack(pkm.getPokemon().getFirstType(), 
				allyPkm.getPokemon().getFirstType(), allyPkm.getPokemon().getSecondType())
					|| STANDARD_EFFECTIVENESS_VALUE < table.getMultiplierAttack(pkm.getPokemon().getSecondType(), 
							allyPkm.getPokemon().getFirstType(), allyPkm.getPokemon().getSecondType())){
				enemyPkm = pkm;
				break;
			}
		}
		//se non ne trova nessuno manda il primo pokemon che trova
		if(enemyPkm.getCurrentHP() == 0){
			for(PokemonInBattle pkm : this.trainer.getSquad().getPokemonList()){
				if(pkm.getCurrentHP() > 0){
					enemyPkm = pkm;
					break;
				}
			}
		}
	}
	
}