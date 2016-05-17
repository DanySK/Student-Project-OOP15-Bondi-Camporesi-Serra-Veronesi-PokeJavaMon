package controller.fight;

import java.util.ArrayList;
import java.util.List;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Effectiveness;
import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.FightVsWildPkm;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.methods.InFightMessages;
import view.methods.InFightMessagesInterface;

/**
 * The MainController that controls the fight.
 */
public class MainFightController implements FightController {

    private Fight fight;
    private InFightMessagesInterface view;
    private Trainer trainer;
    
    @Override
    public void newFightWithTrainer(final Trainer trainer) {
        this.fight = new FightVsTrainer(trainer);
        this.trainer = trainer;
        this.view = new InFightMessages(); 
    }
    
    @Override
    public void newFightWithPokemon(final Pokemon pokemon) {
        this.fight = new FightVsWildPkm(pokemon);
        this.view = new InFightMessages();
    }
    
    @Override
    public Fight getFight() {
        return this.fight;
    }
    
    @Override
    public void resolveAttack(final Move myMove, final Effectiveness myMoveEffectiveness, final Move enemyMove, final Effectiveness enemyMoveEffectiveness, final boolean myMoveFirst, final boolean lastPokemonKills, final Pokemon nextEnemyPokemon, final String optionalMessage, final Move moveToLearn) {
        view.resolveMove(myMove, myMoveEffectiveness, enemyMove, enemyMoveEffectiveness, myMoveFirst, lastPokemonKills, nextEnemyPokemon, optionalMessage, moveToLearn);
    }
    
    @Override
    public void resolveRun(final boolean success, final Move enemyMove, final boolean isMyPokemonDead) {
        this.view.resolveRun(success, enemyMove, isMyPokemonDead);
    }
    
    @Override
    public void resolveItem(final Item item, final Pokemon pokemon, final Move enemyMove, final boolean isMyPokemonDead) {
        this.view.resolveUseItem(item, pokemon, enemyMove, isMyPokemonDead);
    }
    
    @Override
    public void resolvePokemon(final Pokemon myPokemon, final Move enemyMove, final boolean isMyPokemonDead) {
        this.view.resolveChangePokemon(myPokemon, enemyMove, isMyPokemonDead);
    }
    
    @Override
    public void attack(final Move move) {
        this.fight.moveTurn(move);
    }
    
    @Override
    public void run() throws CannotEscapeFromTrainerException {
        this.fight.runTurn();
    }
    
    @Override
    public void changePokemon(final Pokemon pokemon) throws PokemonIsExhaustedException, PokemonIsFightingException {
        this.fight.changeTurn((PokemonInBattle) pokemon);
    }
    
    @Override
    public void useItem(final Item item, final Pokemon pokemon) throws PokemonIsExhaustedException, PokemonNotFoundException, CannotCaughtTrainerPkmException, IllegalStateException {
        this.fight.itemTurn(item, (PokemonInBattle) pokemon);
    }
    
    @Override
    public List<String> resolveEvolution() {
        final List<PokemonInBattle> evolutions = this.fight.getPkmsThatMustEvolve();
        final List<String> names = new ArrayList<>();
        if (!evolutions.isEmpty()) {
            for (final PokemonInBattle p : evolutions) {
                names.add(p.getPokedexEntry().name());
            }
            this.fight.evolvePkms();
        } 
        return names;
    }
    
    @Override
    public void selectPokemon(final Pokemon pokemon) throws PokemonIsExhaustedException, PokemonIsFightingException {
        this.fight.applyChange((PokemonInBattle) pokemon);
    }

    @Override
    public Pokemon getEnemyPokemon() {
        return this.fight.getCurrentEnemyPokemon();
    }
    
    @Override
    public void healEnemy() {
        if (this.fight instanceof FightVsTrainer) {
            if (this.trainer != null) {
                this.trainer.healAllPokemons();
            }
        } else {
            this.fight.getCurrentEnemyPokemon().heal(this.fight.getCurrentEnemyPokemon().getStat(Stat.MAX_HP));
        }
    }
}