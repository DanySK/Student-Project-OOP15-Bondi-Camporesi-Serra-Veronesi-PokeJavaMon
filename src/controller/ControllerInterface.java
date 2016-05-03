package controller;

import controller.fight.FightController;
import controller.load.LoadController;
import controller.music.MusicController;
import controller.parameters.Music;
import controller.parameters.State;
import controller.save.SaveController;
import controller.status.StatusController;
import controller.view.ViewController;
import model.fight.Fight;
import model.pokemon.Pokemon;

public interface ControllerInterface {

    void initializeMainController();

    Fight getFight();

    Pokemon getEnemyPokemonInFight();

    void load();

    boolean saveExists();
    
    void initializeMusicController();

    void playMusic(Music m);

    void stopMusic();

    Music playing();

    void save();

    void updateStatus(State s);

    FightController getFightController();

    LoadController getLoadController();

    MusicController getMusicController();

    SaveController getSaveController();

    StatusController getStatusController();

    ViewController getViewController();
}