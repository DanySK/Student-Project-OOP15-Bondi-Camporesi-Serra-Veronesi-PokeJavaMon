package model.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.Controller;
import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import exceptions.SquadFullException;
import model.items.Boost;
import model.items.Item;
import model.items.Item.ItemType;
import model.items.Pokeball;
import model.items.Potion;
import model.player.Player;
import model.player.PlayerImpl;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.Pokedex;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.pokemon.WeaknessTable;
import model.squad.Squad;

public class FightVsWildPkm implements Fight {
	final private static int SUCCESS_PROBABILTY = 255;
	protected final static int FIRST_ELEM = 0;
	protected final int ATTACKS_TO_DO = 2;
	protected final int MIN_DAMAGE = 1;
	protected final double STAB_ACTIVE = 1.5;
	protected final double MIN_BOOST_VALUE = 0.25;
	protected final double MAX_BOOST_VALUE = 2.0;
	protected double stab;
	protected double effectiveValue = 1;
	
	protected Player player;
	protected PokemonInBattle allyPkm;
	protected PokemonInBattle enemyPkm;
	protected Move enemyMove;
	protected Map<PokemonInBattle, Map<Stat, Double>> allyPkmsBoosts = new HashMap<>();
	private Map<Stat, Double> enemyPkmBoosts = new HashMap<>();
	protected final WeaknessTable table = WeaknessTable.getWeaknessTable();
	
	protected Effectiveness allyEff = Effectiveness.NORMAL;
	protected Effectiveness enemyEff = Effectiveness.NORMAL;
	protected boolean isAllyExhausted = false;
	protected boolean isEnemyExhausted = false;
	protected boolean runValue;
	protected boolean isAllyFastest;
	protected  List<Pokedex> pkmsThatMustEvolve = new ArrayList<>();
	
	protected final Map<Stat, Double> createBoostsMap(){
		final Map<Stat, Double> boosts = new HashMap<>();
		boosts.put(Stat.ATK, 1.0);
		boosts.put(Stat.DEF, 1.0);
		boosts.put(Stat.SPD, 1.0);
		return boosts;
	}
	
	public FightVsWildPkm(final Pokemon pikachu){
		this.player = PlayerImpl.getPlayer();
		this.allyPkm = player.getSquad().getPokemonList().get(FIRST_ELEM);
		this.enemyPkm = (PokemonInBattle) pikachu;
		for(PokemonInBattle pkm : player.getSquad().getPokemonList()){
			this.allyPkmsBoosts.put(pkm, createBoostsMap());
		}
		this.enemyPkmBoosts = new HashMap<>(createBoostsMap());
	}
	
	protected void reset(){
		isAllyExhausted = false;
		isEnemyExhausted = false;
	}
	
	@Override
	public boolean checkLose(final Squad pkmSquad){
		final List<PokemonInBattle> pkmSquadList = pkmSquad.getPokemonList();
		for(PokemonInBattle pkm : pkmSquadList){
			if(pkm.getCurrentHP() > 0){
				return false;
			}
		}
		return true;
	}
	
	protected void enemyTurn(){
		enemyMove = calculationEnemyMove();
		if(enemyMove.getStat() == Stat.HP){
			applyDamage(enemyPkm, allyPkm, enemyMove);
			checkAndSetIsExhausted(allyPkm);
		}
		else{
			applyMoveOnBoost(enemyPkm, allyPkm, enemyMove);
		}
	}
	
	protected Move calculationEnemyMove(){
		Random numberMove = new Random();
		List<Move> moves = new ArrayList<>();
		for(Move m : enemyPkm.getCurrentMoves()){
			if(m != null){
				moves.add(m);
			}
		}
		final int movesNumber = moves.size();
		return moves.get(numberMove.nextInt(movesNumber));
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
	
	protected void isEffective(final PokemonInBattle stricker, final PokemonInBattle stricked, 
			final Move move){
		effectiveValue = table.getMultiplierAttack(move.getType(), stricked.getPokemon().getFirstType(),
				stricked.getPokemon().getSecondType());
		if(effectiveValue >= 2){
			if(stricker.equals(allyPkm)){
				this.allyEff = Effectiveness.SUPEREFFECTIVE;
			}
			else{
				this.enemyEff = Effectiveness.SUPEREFFECTIVE;
			}
		}
		else{
			if(stricker.equals(allyPkm)){
				this.allyEff = Effectiveness.LESSEFFECTIVE;
			}
			else{
				this.enemyEff = Effectiveness.LESSEFFECTIVE;
			}
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
		int damage = (int)((((2 * stricker.getStat(Stat.LVL) + 10) 
			* (stricker.getStat(Stat.ATK) * atkBoost * move.getValue())) / 
			((stricked.getStat(Stat.DEF) * defBoost) * 250 + 2)) * stab * effectiveValue);
		if(damage <= 0){
			return MIN_DAMAGE;
		}	
		return damage;
	}
		
	protected void checkAndSetIsExhausted(final PokemonInBattle pkm){
		if(pkm.equals(allyPkm)){
			isAllyExhausted = (pkm.getCurrentHP() == 0);
		}
		else{
			isEnemyExhausted = (pkm.getCurrentHP() == 0);
		}
	}

	protected void applyMoveOnBoost(final PokemonInBattle stricker, PokemonInBattle stricked, final Move move){
		Double newBoostValue;
		if(allyPkm.equals(stricker)){
			if(move.isOnEnemy()){
				newBoostValue = getEnemyBoost(move.getStat()) - move.getValue();
				if(newBoostValue < MIN_BOOST_VALUE){
					newBoostValue = MIN_BOOST_VALUE;
					allyEff = Effectiveness.CANNOTDECREASE;
				}
				setEnemyBoost(move.getStat(), newBoostValue);
			}
			else{
				newBoostValue = allyPkmsBoosts.get(stricker).get(move.getStat()) 
					+ move.getValue();
				if(newBoostValue > MAX_BOOST_VALUE){
					newBoostValue = MAX_BOOST_VALUE;
					allyEff = Effectiveness.CANNOTINCREASE;
				}
			allyPkmsBoosts.get(stricker).replace(move.getStat(), newBoostValue);
			}
		}
		else{
			if(move.isOnEnemy()){
				newBoostValue = allyPkmsBoosts.get(stricked).get(move.getStat()) 
					- move.getValue();
				if(newBoostValue < MIN_BOOST_VALUE){
					newBoostValue = MIN_BOOST_VALUE;
					this.enemyEff = Effectiveness.CANNOTDECREASE;
				}
				allyPkmsBoosts.get(stricked).replace(move.getStat(), newBoostValue);
			}
			else{
				newBoostValue = getEnemyBoost(move.getStat()) + move.getValue();
				if(newBoostValue > MAX_BOOST_VALUE){
					newBoostValue = MAX_BOOST_VALUE;
					this.enemyEff = Effectiveness.CANNOTINCREASE;
				}
				setEnemyBoost(move.getStat(), newBoostValue);
			}
		}
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
		return runValue = (escapeChance > escapeRoll.nextInt(SUCCESS_PROBABILTY));
	}
	
	@Override
	public void runTurn() throws CannotEscapeFromTrainerException{
		if(!applyRun()){
			enemyTurn();
		}
		Controller.getController().getFightController().resolveRun(runValue, enemyMove, isAllyExhausted);
		reset();
	}
	
	@Override
	public void applyChange(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException {
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
		allyPkm = pkm;
		reset();
	}
	
	@Override
	public void changeTurn(final PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonIsFightingException{
		applyChange(pkm);
		enemyTurn();
		Controller.getController().getFightController().resolvePokemon(allyPkm, enemyMove, isAllyExhausted);
		reset();
	}
	
	protected boolean applyItem(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException{
		switch(itemToUse.getType()){
		case BOOST:
			final Boost boost = (Boost) itemToUse;
			//mettere a posto il valore del coefficente, dentro alla classe Boost
			allyPkmsBoosts.get(allyPkm).replace(boost.getStat(), 
			allyPkmsBoosts.get(allyPkm).get(boost.getStat()) + boost.getCoeff());
			break;
		case POKEBALL:
			if(useBall(itemToUse)){
				try {
					player.getSquad().add(enemyPkm);
				} catch (SquadFullException e) {
					player.getBox().putCapturedPokemon(enemyPkm);
				}
				return true;
			}
			return false;
		case POTION:
			final Potion potion = (Potion) itemToUse;
			potion.effect(player, pkm);
		}
		return true;
	}
	
	protected boolean useBall(final Item itemToUse) throws CannotCaughtTrainerPkmException{
		final Pokeball ball = (Pokeball) itemToUse;
		return ball.isCaptured(enemyPkm);
	}
	
	public void itemTurn(final Item itemToUse, PokemonInBattle pkm) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException{
	        player.getInventory().consumeItem(itemToUse);
	        if(applyItem(itemToUse, pkm)){
			if(itemToUse.getType() == ItemType.POKEBALL){
				Controller.getController().getFightController().resolveItem(itemToUse, pkm, null, isAllyExhausted);
				return;
			} else {
			        enemyTurn();
	                        Controller.getController().getFightController().resolveItem(itemToUse, pkm, enemyMove, isAllyExhausted);
	                        reset();
			}
		} else {
                    enemyTurn();
                    Controller.getController().getFightController().resolveItem(itemToUse, pkm, enemyMove, isAllyExhausted);
                    reset();
		}
	}
	
	public void moveTurn(final Move move){
		int exp;
		int attacksDone = 0;
		boolean isEnd = false;
		boolean turnOrder = setIsAllyFastest();
		while(attacksDone < ATTACKS_TO_DO && !isEnd){
			if(turnOrder){
				allyTurn(move);
				if(isEnemyExhausted){
					exp = getExp();
					giveExpAndCheckLvlUp(exp);
					isEnd = true;
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
					Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null/*exp*/);
				}
				else{
					//alleato attacca, nemico attacca, pokemon alleato sopravvive
					Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null/*exp*/);
				}
			}
			else{
				if(isEnemyExhausted){
					//nemico attacca, alleato attacca, pokemon nemico esausto
					Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, true, null, null/*exp*/);
				}
				else{
					//nemico attacca, alleato attacca, pokemon nemico sopravvive
					Controller.getController().getFightController().resolveAttack(move, allyEff, enemyMove, enemyEff, isAllyFastest, false, null, null/*exp*/);
				}
			}
		}
		else{
			if(isAllyFastest){
				//alleato attacca per primo, pkm nemico esausto
				Controller.getController().getFightController().resolveAttack(move, allyEff, null, null, isAllyFastest, false, null, null/*exp*/);
			}
			else{
				//nemico attaccata per primo, pkm alleato esausto
				Controller.getController().getFightController().resolveAttack(null, null, enemyMove, enemyEff, isAllyFastest, false, null, null/*exp*/);
			}
		}
		reset();
	}
	
	protected void allyTurn(final Move move){
		if(move.getStat() == Stat.HP){
			applyDamage(allyPkm, enemyPkm, move);
			checkAndSetIsExhausted(enemyPkm);
		}
		else{
			applyMoveOnBoost(allyPkm, enemyPkm, move);
		}
	}
	
	protected boolean setIsAllyFastest(){
		return isAllyFastest = ((allyPkm.getStat(Stat.SPD) * allyPkmsBoosts.get(allyPkm).get(Stat.SPD)) 
				>= (enemyPkm.getStat(Stat.SPD) * this.enemyPkmBoosts.get(Stat.SPD)));
	}
	
	protected int getExp(){
		return (int) (expBaseCalculation() / 7);
	}
	
	protected double expBaseCalculation(){
		//TODO testare se e' bilanciata la quantita'  di baseExp
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
	
	protected void giveExpAndCheckLvlUp(final int exp){
		if(allyPkm.getNecessaryExp() <= exp){
		        allyPkm.setExp(exp - allyPkm.getNecessaryExp());
			allyPkm.levelUp();
		} else {
		    allyPkm.setExp(allyPkm.getStat(Stat.EXP) + exp);   
		}
	}
	
	public List<Pokedex> getPkmsThatMustEvolve(){
		List<Pokedex> pmksThatMustEvolve = new ArrayList<>();
		for(PokemonInBattle pkm : player.getSquad().getPokemonList()){
			if(pkm.checkIfEvolves()){
				pmksThatMustEvolve.add(pkm.getPokemon());
			}
		}
		return pmksThatMustEvolve;
	}

    @Override
    public Pokemon getCurrentEnemyPokemon() {
        return enemyPkm;
    }
	
}