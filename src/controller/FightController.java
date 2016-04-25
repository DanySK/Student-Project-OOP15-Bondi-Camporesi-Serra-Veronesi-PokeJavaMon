package controller;

import java.util.ArrayList;
import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.CannotUseItemInBattleException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.FightVsWildPkm;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.MethodsToImplement;
import model.player.*;

public class FightController {

    private static FightController SINGLETON;
    private Fight fight;
    private MethodsToImplement view;
    
    public static FightController getController() {    
        if (SINGLETON == null) {
            synchronized (FightController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new FightController();
                }
            }
        }
        return SINGLETON;
    }
    
    public void newFightWithTrainer(Trainer tr) {
        fight = new FightVsTrainer(tr);
        System.out.println("Fight with: " + tr.getID());
    }
    
    public void newFightWithPokemon(Pokemon pm) {
        fight = new FightVsWildPkm(pm);
        System.out.println("Fight with: " + pm.getPokemon().name());
    }
    
    public void attack(Move move) {
        Move enemy = fight.enemyMove();
        if (fight.isAllyFastest()) {
            view.movePlayerThenOpponent(move, enemy);
        } else {
            view.moveOpponentThenPlayer(enemy, move);
        }
    }
    
    public void run() {
        try {
            if (fight.run()) {
                view.run();
            } else {
                Move enemy = fight.enemyMove();
                view.showMessageThenOpponentMove("Cannot escape now!", enemy);
            }
        } catch (CannotEscapeFromTrainerException e) {
            Move enemy = fight.enemyMove();
            view.showMessageThenOpponentMove("Cannot escape from trainer!", enemy);
        }
    }
    
    @SuppressWarnings("unused")
    public void useItem(Item it) {
        // TODO chiedere al model se si puo usare l oggetto
        if (true) {
            Move enemy = fight.enemyMove();
            view.useItemThenOpponentMove(it, enemy);
        } else {
            Move enemy = fight.enemyMove();
            view.showMessageThenOpponentMove("Cannot use this item now!", enemy);
        }
    }
    
    public void changePokemon(Pokemon pk) {
        Move enemy = fight.enemyMove();
        view.changePokemonThenOpponentMove(pk, enemy);
    }
    
    
    
    
    
    
    
    
    
    
    public boolean applyMove(PokemonInBattle stricker, PokemonInBattle stricked){
		if(fight.getMoveUsed().getStat() == Stat.HP){
			fight.applyDamage(stricker, stricked);
			return true;
		}
		if(!fight.applyMoveOnBoost(stricker, stricked)){
			if(fight.getMoveUsed().isOnEnemy()){
				//view.decreaseNotAppliedMessage(stricked, fight.getMoveUsed().getStat());
			}
			else{
				//view.increaseNotAppliedMessage(stricker, fight.getMoveUsed().getStat());
			}
		}
		return false;
	}
	
	public void enemyTurn(){
		fight.setMoveUsed(fight.enemyMove());
		//view.attackMessage(fight.getEnemyPkm(), fight.isAllyPkm(fight.getEnemyPkm()), fight.getMoveUsed());
		if(applyMove(fight.getEnemyPkm(), fight.getAllyPkm())){
			checkEffectiveness(fight.getEnemyPkm(), fight.getAllyPkm());
		}
		if(fight.isExhausted(fight.getAllyPkm())){
			checkLose();
		}
	}
	
	public void allyTurn(){
		fight.setMoveUsed(fight.getMoveUsed());
		//view.attackMessage(fight.getAllyPkm(), fight.isAllyPkm(fight.getAllyPkm()), fight.getMoveUsed());
		checkEffectiveness(fight.getAllyPkm(), fight.getEnemyPkm());
		applyMove(fight.getAllyPkm(), fight.getEnemyPkm());
	}
	
	public void checkEffectiveness(final PokemonInBattle stricker, final PokemonInBattle stricked){
		final double effectiveness = fight.isEffective(stricker, stricked);
		if(effectiveness >= 2){
			//view.isSuperEffectiveMessage();
		}
		else if(effectiveness == 0){
			//view.isImmuneMessage(stricked);
		}
		else if(effectiveness <= 0.5){
			//view.isLessEffectiveMessage();
		}
	}
	
	public void checkLose(){
		if(fight.checkPkmSquad(PlayerImpl.getPlayer().getSquad()) == null){
			//sconfitta:
			//spostare player all'ultimo centro pkm
			//player.pokemonCenter();
		}
		else{
			//view.exhaustMessage(fight.getAllyPkm(), fight.isAllyPkm(fight.getAllyPkm()));
			//deve essere obbligato a mandare un pkm in combattimento
		}
	}
	
	public boolean checkWin(){
		FightVsTrainer fight = (FightVsTrainer) this.fight;
		if(fight.checkPkmSquad(fight.getTrainer().getSquad()) == null){
			return true;
		}
		FightVsTrainer tFight = (FightVsTrainer) fight;
		tFight.trainerChange();
		//view.trainerChangePkm(fight.getTrainer(), fight.getEnemyPkm());
		return false;
	}
	
	public void checkIfPkmsEvolve(){
		List<PokemonInBattle> pkmsThatMustEvolve = new ArrayList<>(fight.getPkmsThatMustEvolve());
		if(pkmsThatMustEvolve != null){
			for(PokemonInBattle pkm : pkmsThatMustEvolve){
				//view.evolveMessage(pkm);
				pkm.evolveUp();
				//view.evolveMessage(pkm);
			}
		}
	}
	
	public void run(){
		try{
			if(fight.run()){
				//fuga riuscita
			}
			else{
				enemyTurn();
			}
		}
		catch(CannotEscapeFromTrainerException e){
			//view.message(e.getMessage());
		}
	}

	public void change(final int pkmPos){
		try{
			fight.change(pkmPos);
			enemyTurn();
		}
		catch(PokemonIsExhaustedException e){
			//view.message(e.getMessage());
		}
		catch(PokemonIsFightingException e){
			//view.message(e.getMessage());
		}
	}

	public void useObject(final Item itemToUse){
		try{
			switch(fight.identifyItem(itemToUse)){
			case BOOST:
				fight.useBoost(itemToUse);
				enemyTurn();
				break;
			case POKEBALL:
				if(fight.usePokeball(itemToUse)){
					try{
						itemToUse.effect(PlayerImpl.getPlayer(), fight.getEnemyPkm());
					}
					//cattura riuscita = battaglia finita
					//mostrare messaggi o il pokemon viene in squadra, o va nel box
				}
				else{
					enemyTurn();
				}
				break;
			case POTION:
				//aprire schermata scelta pkm
			}
		}
		catch(CannotUseItemInBattleException e){
			//view.message(e.getMessage());
		}
		catch(CannotCaughtTrainerPkmException e){
			//view.message(e.getMessage());
		} catch (PokemonNotFoundException e) {
			//view.message(e.getMessage());
		}
	}

	public void usePotion(Item itemToUse, PokemonInBattle pkmTarget){
		try{
			fight.usePotion(itemToUse, pkmTarget);
			enemyTurn();
		}
		catch(PokemonIsExhaustedException e){
			//view.message(e.getMessage());
		}
		catch(PokemonNotFoundException e){
			//view.message(e.getMessage());
		}
	}

	public void fightTurn(Move allyMove){
		int attacksDone = 0;
		boolean isAllyFastest = fight.isAllyFastest();
		boolean isEnd = false;
		while(attacksDone < fight.getAttacksToDo() && !isEnd){
			if(isAllyFastest){
				allyTurn();
				if(fight.isExhausted(fight.getEnemyPkm())){
					//view.expMessage(fight.getExp)->il pkm guadagna n exp!
					if(fight.giveExpAndCheckLvlUp(fight.getExp())){
						//view.levelUpMessage(fight.getAllyPkm(), fight.getAllyPkm().getStat(Stat.LVL);
						//view.showNewPkmStats(fight.getAllyPkm().getAllStats());
						//checkIfLearnMove, con relativa gestione della view
						
					}
					if(fight instanceof FightVsWildPkm){
						
					}
					else{
						if(checkWin()){
							//FightVsTrainer tFight = (FightVsTrainer) fight;
							//view.takeMoneyMessage(tFight.getTrainerMoney());
							//tFight.playerTakeMoney
						}
						
					}
				isEnd = true;
				}
			}
			else{
				enemyTurn();
			}
			isAllyFastest = !isAllyFastest;
			attacksDone += 1;
		}
		checkIfPkmsEvolve();
	}
}