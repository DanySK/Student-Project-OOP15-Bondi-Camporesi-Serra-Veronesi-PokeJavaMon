package controller.modelResources;

import java.util.List;

import model.box.Box;
import model.inventory.Inventory;
import model.pokemon.Pokemon;
import model.trainer.Trainer;
import model.utilities.Pair;

public class General {
    private List<Pokemon> team;
    private Box box;
    private List<Trainer> trainers;
    private Inventory inv;
    private int money;  
    private Pair<Float, Float> position;
    
    public General(List<Pokemon> team, Box box, List<Trainer> trainers, Inventory inv, int money, Pair<Float, Float> position) {
        super();
        this.team = team;
        this.trainers = trainers;
        this.inv = inv;
        this.money = money;
        this.box = box;
        this.position = position;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Pair<Float, Float> getPosition() {
        return position;
    }

    public void setPosition(Pair<Float, Float> position) {
        this.position = position;
    }

    public String toString() {
        return "General [team=" + team + ", box=" + box + ", trainers=" + trainers + ", inv=" + inv + ", money=" + money
                + ", position=" + position + "]";
    }
}