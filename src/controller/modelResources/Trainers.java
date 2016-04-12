package controller.modelResources;

public class Trainers {
    
    private String name;
    private boolean beaten;
    
    public Trainers(String n, boolean b) {
        
        name = n;
        beaten = b;
    }

    public String getName() {
        
        return name;
    }

    public void setName(String name) {
        
        this.name = name;
    }

    public boolean isBeaten() {
        
        return beaten;
    }

    public void setBeaten(boolean beaten) {
        
        this.beaten = beaten;
    }

    public String toString() {
        return "Trainers [name=" + name + ", beaten=" + beaten + "]";
    }
}
