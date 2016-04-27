package model.fight;

import java.util.HashMap;
import java.util.Map;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;


public class FightVsTrainer extends FightVsWildPkm{
	private final static int FIRST_ELEM = 0;
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
	
	public boolean usePokeball(final Item itemToUse) throws CannotCaughtTrainerPkmException{
		throw new CannotCaughtTrainerPkmException();
	}
	
	public boolean isAllyFastest(){
		if((allyPkm.getStat(Stat.SPD) * allyPkmsBoosts.get(allyPkm).get(Stat.SPD))
				> (enemyPkm.getStat(Stat.SPD) * enemyPkmsBoosts.get(enemyPkm).get(Stat.SPD))){
					return true;
		}
		return false;
	}
	
	@Override
	protected double getEnemyBoost(Stat stat){
		return enemyPkmsBoosts.get(enemyPkm).get(stat);
	}
	
	@Override
	public void setEnemyBoost(Stat stat, Double d){
		enemyPkmsBoosts.get(enemyPkm).replace(stat, d);
	}
	
	protected Move calculateEnemyMove(){
		Move move = enemyPkm.getCurrentMoves().get(FIRST_ELEM);
		boolean superEffective = false;
		//cerca di ritornare prima una mossa superefficace, 
		//se non la trovo, ritorna la mossa che fa più danno
		//se non ha mosse che fanno danno, usa la prima mossa
		for(Move m : enemyPkm.getCurrentMoves()){
			if(m.getStat() == Stat.HP){
				if(4 == table.getMultiplierAttack(m.getType(), allyPkm.getPokemon().getFirstType(), allyPkm.getPokemon().getSecondType())){
					move = m;
					superEffective = true;
					break;
				}
				else if(2 == table.getMultiplierAttack(m.getType(), allyPkm.getPokemon().getFirstType(), allyPkm.getPokemon().getSecondType())){
					move = m;
					superEffective = true;
				}
				else if(move.getValue() < m.getValue() && !superEffective){
					move = m;
				}
			}
		}
		return move;
	}
	
	@Override
	public int getExp(){//da sistemare
		return (int) (expBaseCalculation() * 1.5) / 7; 
	}
	
	public Trainer getTrainer(){
		return this.trainer;
	}
	
	public void trainerChange(){
		//manda un pkm che ha mosse superefficaci contro l'allyPkm
		for(PokemonInBattle pkm : this.trainer.getSquad().getPokemonList()){
			if(STANDARD_EFFECTIVENESS_VALUE < table.getMultiplierAttack(pkm.getPokemon().getFirstType(), 
				enemyPkm.getPokemon().getFirstType(), enemyPkm.getPokemon().getSecondType())
					|| STANDARD_EFFECTIVENESS_VALUE < table.getMultiplierAttack(pkm.getPokemon().getSecondType(), 
							enemyPkm.getPokemon().getFirstType(), enemyPkm.getPokemon().getSecondType())){
				enemyPkm = pkm;
			}
		}
		//altrimenti manda il primo pkm che trova nella squadra
		//enemyPkm = checkPkmSquad(this.trainer.getSquad());
	}
	
	public int getTrainerMoney(){
		return this.trainer.getMoney();
	}
	
	public void playerTakeMoney(){
		//TODO
		//player.beatenTrainer(this.trainer.getTrainerDB());
	}
	
	/*public boolean checkWin(){
	if(checkPkmSquad(fight.getTrainer().getSquad()) == null){
		return true;
	}
	FightVsTrainer tFight = (FightVsTrainer) fight;
	tFight.trainerChange();
	//view.trainerChangePkm(fight.getTrainer(), fight.getEnemyPkm());
	return false;
	}*/
	
}