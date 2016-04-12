package controller.modelResources;

import java.util.List;

public class General {
    public List<Pokemon> team;
    public List<Trainers> trainers;
    public Inventory inv;
    public int money;
    public int time;
    public List<Pokemon> box;
    public float xPos, yPos, exitX, exitY;
    public boolean place;
    
    public General(List<Pokemon> team, List<Trainers> trainers, Inventory inv, int money, int time, List<Pokemon> box, float a, float b, float c, float d, boolean bool) {
        super();
        this.team = team;
        this.trainers = trainers;
        this.inv = inv;
        this.money = money;
        this.time = time;
        this.box = box;
        this.xPos = a;
        this.yPos = b;
        this.exitX = c;
        this.exitY = d;
        this.place = bool;
    }

    public String toString() {
        return "General [team=" + team + ", trainers=" + trainers + ", inv=" + inv + ", money=" + money + ", time="
                + time + ", box=" + box + ", xPos=" + xPos + ", yPos=" + yPos + ", exitX=" + exitX + ", exitY=" + exitY
                + ", place=" + place + "]";
    }
}