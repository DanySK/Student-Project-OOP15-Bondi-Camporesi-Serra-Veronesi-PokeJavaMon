package controller.fight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.CannotCaughtTrainerPkmException;
import exceptions.CannotEscapeFromTrainerException;
import exceptions.PokemonIsExhaustedException;
import exceptions.PokemonIsFightingException;
import exceptions.PokemonNotFoundException;
import model.fight.Effectiveness;
import model.fight.Fight;
import model.fight.FightVsTrainer;
import model.fight.StaticSimpleFightFactory;
import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;
import model.pokemon.PokemonInBattle;
import model.pokemon.Stat;
import model.trainer.Trainer;
import view.fight.InFightMessages;
import view.fight.InFightMessagesInterface;

/**
 * The MainController that controls the fight.
 */
public class MainFightController implements FightController {

    private Fight fight;
    private InFightMessagesInterface view;
    private Optional<Trainer> trainer;
    
    public MainFightController() {
        // EMPTY CONSTRUCTOR
    }
    
    @Override
    public void newFightWithTrainer(final Trainer trainer) {
        this.fight = StaticSimpleFightFactory.createFight(trainer);
        this.trainer = Optional.of(trainer);
        this.view = new InFightMessages(); 
    }
    
    @Override
    public void newFightWithPokemon(final Pokemon pokemon) {
        this.fight = StaticSimpleFightFactory.createFight(pokemon);
        this.view = new InFightMessages();
        this.trainer = Optional.empty();
    }
    
    @Override
    public Optional<Fight> getFight() {
        if (this.fight == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.fight);
        }
    }
    
    @Override
    public void resolveAttack(final Move myMove, final Effectiveness myMoveEffectiveness, final Move enemyMove, final Effectiveness enemyMoveEffectiveness, final boolean myMoveFirst, 
            final boolean lastPokemonKills, final Pokemon nextEnemyPokemon, final String optionalMessage, final Move moveToLearn) {
        this.view.resolveMove(myMove, myMoveEffectiveness, enemyMove, enemyMoveEffectiveness, myMoveFirst, lastPokemonKills, nextEnemyPokemon, optionalMessage, moveToLearn);
    }
    
    @Override
    public void resolveRun(final boolean success, final Move enemyMove, final Effectiveness effectiveness, final boolean isMyPokemonDead) {
        this.view.resolveRun(success, enemyMove, effectiveness, isMyPokemonDead);
    }
    
    @Override
    public void resolveItem(final Item item, final Pokemon pokemon, final Move enemyMove, final Effectiveness effectiveness, final boolean isMyPokemonDead) {
        this.view.resolveUseItem(item, pokemon, enemyMove, effectiveness, isMyPokemonDead);
    }
    
    @Override
    public void resolvePokemon(final Pokemon myPokemon, final Move enemyMove, final Effectiveness effectiveness, final boolean isMyPokemonDead) {
        this.view.resolveChangePokemon(myPokemon, enemyMove, effectiveness, isMyPokemonDead);
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
    public Optional<Pokemon> getEnemyPokemon() {
        if (this.fight.getCurrentEnemyPokemon() == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.fight.getCurrentEnemyPokemon());
        }
    }
    
    @Override
    public Optional<Trainer> getEnemyTrainer() {
        if (this.trainer == null) {
            return Optional.empty();
        } else {
            return this.trainer;
        }
    }
    
    @Override
    public void healEnemy() {
        if (this.fight instanceof FightVsTrainer) {
            if (this.trainer.isPresent()) {
                this.trainer.get().healAllPokemons();
            }
        } else {
            if (this.fight.getCurrentEnemyPokemon() != null) {
                this.fight.getCurrentEnemyPokemon().heal(this.fight.getCurrentEnemyPokemon().getStat(Stat.MAX_HP));
            }
        }
    }
}