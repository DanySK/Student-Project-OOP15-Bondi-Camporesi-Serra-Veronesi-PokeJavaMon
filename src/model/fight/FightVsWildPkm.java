package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.CannotUseItemInBattleException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.items.Boost;
import model.items.Item;
import model.items.Item.whenToUse;
import model.items.Pokeball;
import model.items.Potion;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;
import model.squad.Squad;



public class FightVsWildPkm implements Fight {
	final private static int SUCCESS_PROBABILTY = 255;
	protected final int ATTACKS_TO_DO = 2;
	protected final double STAB_ACTIVE = 1.5;
	protected final double MIN_BOOST_VALUE = 0.25;
	protected final double MAX_BOOST_VALUE = 2.0;
	protected double stab;
	protected double effectiveValue = 1;
	
	protected Player player;
	protected PokemonInBattle allyPkm;
	protected PokemonInBattle enemyPkm;
	protected Move allyMove;
	protected Move enemyMove;
	protected Map<PokemonInBattle, Map<Stat, Double>> allyPkmsBoosts = new HashMap<>();
	private Map<Stat, Double> enemyPkmBoosts;
	protected final WeaknessTable table = WeaknessTable.getWeaknessTable();
	//per comunicare cosa è successo alla view
	protected boolean boostChangeApplied = true;
	protected boolean boostChangeOnAlly;
	protected Effectiveness effective = Effectiveness.NORMAL;
	protected boolean isAllyExhausted = false;
	protected boolean isEnemyExhausted = false;
	protected BattleStat state = BattleStat.FIGHTING;
	protected PlayerOptions option;
	protected Item itemToUse;
	protected PokemonInBattle pkmToChange;
	protected boolean isAllyFastest;
	protected boolean isLevelUpped = false;
	protected int expGained;
	protected  List<PokemonInBattle> pkmsThatMustEvolve;
	
	protected final Map<Stat, Double> createBoostsMap(){
		final Map<Stat, Double> boosts = new HashMap<>();
		boosts.put(Stat.ATK, 1.0);
		boosts.put(Stat.DEF, 1.0);
		boosts.put(Stat.SPD, 1.0);
		return boosts;
	}
	
	public FightVsWildPkm(final Pokemon wildPokemon){
		this.player = PlayerImpl.getPlayer();
		this.allyPkm = checkPkmSquad(player.getSquad());
		this.enemyPkm = (PokemonInBattle) wildPokemon;
		for(PokemonInBattle pkm : player.getSquad().getPokemonList()){
			this.allyPkmsBoosts.put(pkm, createBoostsMap());
		}
		this.enemyPkmBoosts = new HashMap<>(createBoostsMap());
	}
	
	//usato nel costruttore e nel checkLose
	protected PokemonInBattle checkPkmSquad(final Squad pkmSquad){
		final List<PokemonInBattle> pkmSquadList = pkmSquad.getPokemonList();
		for(PokemonInBattle pkm : pkmSquadList){
			if(pkm.getCurrentHP() > 0){
				return pkm;
			}
		}
		return null;
	}
	
	public void enemyTurn(){
		this.enemyMove = calculationEnemyMove();
		if(enemyMove.getStat() == Stat.HP){
			applyDamage(enemyPkm, allyPkm, enemyMove);
			if(isExhausted(allyPkm)){
				if(checkLose()){
					//sconfitta
				}
				else{
					//esausto
				}
			}
		}
		else{
			applyMoveOnBoost(enemyPkm, allyPkm, enemyMove);
		}
	}
	
	protected Move calculationEnemyMove(){
		Random numberMove = new Random();
		final int movesNumber = enemyPkm.getCurrentMoves().size();
		return enemyPkm.getCurrentMoves().get(numberMove.nextInt(movesNumber));
	}
	
	protected void applyDamage(final PokemonInBattle stricker, PokemonInBattle stricked, final Move move){
		isEffective(stricker, stricked, move);
		stab = stabCalculation(stricker, move);
		final double atkBoost;
		final double defBoost;
		if(stricker.equals(allyPkm)){
			atkBoost = allyPkmsBoosts.get(stricker).get(Stat.ATK);
			defBoost = getEnemyBoost(Stat.DEF);
		}
		else{
			atkBoost = getEnemyBoost(Stat.ATK);
			defBoost = allyPkmsBoosts.get(stricked).get(Stat.ATK);
		}
		final int damageDone = damageCalculation(stricker, stricked, atkBoost, defBoost, move);
		stricked.damage(damageDone);
	}
	
	protected void isEffective(final PokemonInBattle stricker, 
			final PokemonInBattle stricked, final Move move){
		effectiveValue = table.getMultiplierAttack(move.getType(), stricked.getPokemon().getFirstType(),
				stricked.getPokemon().getSecondType());
		if(effectiveValue == 1){
			effective = Effectiveness.NORMAL;
		}
		else if(effectiveValue >= 2){
			effective = Effectiveness.SUPEREFFECTIVE;
		}
		else{
			effective = Effectiveness.LESSEFFECTIVE;
		}
	}
	
	protected double stabCalculation(final PokemonInBattle stricker, final Move move){
		if(stricker.getPokemon().getFirstType() == move.getType() ||
			stricker.getPokemon().getSecondType() == move.getType()){
			return STAB_ACTIVE;
		}
		return 1;
	}
		
	protected int damageCalculation(final PokemonInBattle stricker, final PokemonInBattle stricked, 
			final double atkBoost, final double defBoost, final Move move){
		return (int)((((2 * stricker.getStat(Stat.LVL) + 10) 
			* (stricker.getStat(Stat.ATK) * atkBoost * move.getValue())) / 
			((stricked.getStat(Stat.DEF) * defBoost) * 250 + 2)) * stab * effectiveValue);
	}
		
	protected boolean isExhausted(final PokemonInBattle pkm){
		if(pkm.equals(allyPkm)){
			return isAllyExhausted = (pkm.getCurrentHP() == 0);
		}
			return isEnemyExhausted = (pkm.getCurrentHP() == 0);
	}
	
	protected boolean checkLose(){
		if(checkPkmSquad(player.getSquad()) == null){
			this.state = BattleStat.LOSE;
			return true;
		}
		return false;
	}
	
	protected boolean applyMoveOnBoost(final PokemonInBattle stricker, PokemonInBattle stricked, final Move move){
		Double newBoostValue;
		if(allyPkm.equals(stricker)){
			if(move.isOnEnemy()){
				boostChangeOnAlly = false;
				newBoostValue = getEnemyBoost(move.getStat()) - move.getValue();
				if(newBoostValue < MIN_BOOST_VALUE){
					newBoostValue = MIN_BOOST_VALUE;
					boostChangeApplied = false;
				}
				setEnemyBoost(move.getStat(), newBoostValue);
			}
			else{
				boostChangeOnAlly = true;
				newBoostValue = allyPkmsBoosts.get(stricker).get(move.getStat()) 
					+ move.getValue();
				if(newBoostValue > MAX_BOOST_VALUE){
					newBoostValue = MAX_BOOST_VALUE;
					boostChangeApplied = false;
				}
			allyPkmsBoosts.get(stricked).replace(move.getStat(), newBoostValue);
			}
		}
		else{
			if(move.isOnEnemy()){
				boostChangeOnAlly = true;
				newBoostValue = allyPkmsBoosts.get(stricked).get(move.getStat()) 
					- move.getValue();
				if(newBoostValue < MIN_BOOST_VALUE){
					newBoostValue = MIN_BOOST_VALUE;
					boostChangeApplied = false;
				}
				allyPkmsBoosts.get(stricked).replace(move.getStat(), newBoostValue);
			}
			else{
				boostChangeOnAlly = false;
				newBoostValue = getEnemyBoost(move.getStat()) + move.getValue();
				if(newBoostValue > MAX_BOOST_VALUE){
					newBoostValue = MAX_BOOST_VALUE;
					boostChangeApplied = false;
				}
				setEnemyBoost(move.getStat(), newBoostValue);
			}
		}
		return boostChangeApplied;
	}
	
	protected double getEnemyBoost(final Stat stat){
		return enemyPkmBoosts.get(stat);
	}
	
	protected void setEnemyBoost(final Stat stat, final Double d){
		enemyPkmBoosts.replace(stat, d);
	}
	
	protected boolean applyRun() throws CannotEscapeFromTrainerException{
		final Random escapeRoll = new Random();
		final int escapeChance = (32 * allyPkm.getStat(Stat.SPD)) / 
				(enemyPkm.getStat(Stat.SPD) / 4) + 30;
		option = PlayerOptions.RUN;
		return (escapeChance > escapeRoll.nextInt(SUCCESS_PROBABILTY));
	}
	
	@Override
	//per controller
	public void run() throws CannotEscapeFromTrainerException{
		if(applyRun()){
			//FightController.getController().fugaRiuscita();
		}
		else{
			enemyTurn();
		}
	}
	
	protected void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException {
		pkmToChange = pkm;
		if(pkm.getCurrentHP() == 0){
			throw new PokemonIsExhaustedException();
		}
		else if(pkm.equals(allyPkm)){
			throw new PokemonIsFightingException();
		}
		allyPkm = pkm;
		int pkmPos = 0;
		for(final PokemonInBattle p : player.getSquad().getPokemonList()){
			if(pkm.equals(p)){
				break;
			}
			pkmPos += 1;
		}
		player.getSquad().switchPokemon(0, pkmPos);
	}
	
	@Override
	//per controller
	public void change(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException{
			applyChange(pkm);
			//animazione cambio
			enemyTurn();
	}
	
	@Override
	//zaino->scelgo oggetto->chiamo questo metodo, memorizzo l'item e poi lo metto dentro al metodo useItem
	public void identifyItem(final Item itemToUse) throws CannotUseItemInBattleException{
		if(itemToUse.whenToUse() == whenToUse.OUT_OF_BATTLE){
			throw new CannotUseItemInBattleException();
		}
		this.itemToUse = itemToUse;
	}
	
	public void useItem(final Item itemToUSe, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException{
		switch(itemToUse.getType()){
		case BOOST:
			final Boost boost = (Boost) itemToUse;
			//mettere a posto il valore del coefficente, dentro alla classe Boost
			allyPkmsBoosts.get(allyPkm).replace(boost.getStat(), 
					allyPkmsBoosts.get(allyPkm).get(boost.getStat()) + boost.getCoeff());
		case POKEBALL:
			final Pokeball ball = (Pokeball) itemToUse;
			ball.isCaptured(enemyPkm);
		case POTION:
			final Potion potion = (Potion) itemToUse;
			potion.effect(player, pkm);
		}
		player.getInventory().consumeItem(itemToUSe);
		//chiamo metodo che fa cose, inserendo l'oggetto itemToUse e basta
		//poi da esso ricavo il type di conseguenza stabilisco l'animazione
		//poi è uguale agli altri due, basta fare il turno nemico
	}
	
	public void useMove(final Move move){
		int attacksDone = 0;
		boolean isEnd = false;
		allyMove = move;
		boolean turnOrder = setIsAllyFastest();
		while(attacksDone < ATTACKS_TO_DO && !isEnd){
			if(turnOrder){
				allyTurn();
				if(isExhausted(enemyPkm)){
					this.expGained = getExp();
					if(giveExpAndCheckLvlUp(expGained)){
						this.isLevelUpped = true;
					}
					state = BattleStat.WIN;
					isEnd = true;
					pkmsThatMustEvolve = getPkmsThatMustEvolve();
				}
			}
			else{
				enemyTurn();
			}
			turnOrder = !turnOrder;
			attacksDone += 1;
		}
	}
	
	protected void allyTurn(){
		if(allyMove.getStat() == Stat.HP){
			applyDamage(allyPkm, enemyPkm, allyMove);
			if(isExhausted(enemyPkm)){
				//vittoria
			}
		}
		else{
			if(applyMoveOnBoost(allyPkm, enemyPkm, allyMove)){
				//ho applicato l'aumento o decremento
			}
		}
	}
	
	protected boolean setIsAllyFastest(){
		return isAllyFastest = ((allyPkm.getStat(Stat.SPD) * allyPkmsBoosts.get(allyPkm).get(Stat.SPD)) 
				> (enemyPkm.getStat(Stat.SPD) * enemyPkmBoosts.get(Stat.SPD)));
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
	
	
	
	
	
	
	protected int getExp(){
		return (int) (expBaseCalculation() / 7);
	}
	
	protected double expBaseCalculation(){
		//TODO testare se è bilanciata la quantità di baseExp
		double baseExp;
		switch(enemyPkm.getPokemon().getRarity()){
			case COMMON:
				baseExp = 60;
			case UNCOMMON:
				baseExp = 90;
				break;
			case RARE:
				baseExp = 120;
				break;
			case STARTER:
				baseExp = 150;
				break;
			case LEGENDARY:
				baseExp = 300;
				break;
			default:
				baseExp = 1;
		}
		baseExp = baseExp * enemyPkm.getStat(Stat.LVL);
		return baseExp;
	}
	
	protected boolean giveExpAndCheckLvlUp(final int exp){
		if(allyPkm.getNecessaryExp() <= exp){
			allyPkm.getAllStats().replace(Stat.EXP, (exp - allyPkm.getNecessaryExp()));
			allyPkm.levelUp();
			return true;
		}
		allyPkm.getAllStats().replace(Stat.EXP, (allyPkm.getStat(Stat.EXP) + exp));
		return false;
	}
	
	protected List<PokemonInBattle> getPkmsThatMustEvolve(){
		List<PokemonInBattle> pmksThatMustEvolve = new ArrayList<>();
		for(PokemonInBattle pkm : player.getSquad().getPokemonList()){
			if(pkm.checkIfEvolves()){
				pmksThatMustEvolve.add(pkm);
			}
		}
		if(pmksThatMustEvolve.isEmpty()){
			return null;
		}
		return pmksThatMustEvolve;
	}
	
}