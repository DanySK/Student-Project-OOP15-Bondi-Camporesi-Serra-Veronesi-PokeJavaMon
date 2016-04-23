package controller;

import model.fight.Fight;

public class FightController {

    private static FightController SINGLETON;
    private Fight fight;
    
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
    
    public void newFightWithTrainer() {
        //fight = new FightVsTrainer();
    }
    
    public void newFightWithPokemon() {
        //fight = new FightVsWildPkm();
    }
}