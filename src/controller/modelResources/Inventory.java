package controller.modelResources;

import java.util.Map;
import java.util.Set;

public class Inventory {

    Map<String, Integer> oggetti;
    Set<String> base;
    Map<String, Integer> balls;
    
    public Inventory(Map<String, Integer> oggetti, Set<String> base, Map<String, Integer> balls) {
        
        super();
        this.oggetti = oggetti;
        this.base = base;
        this.balls = balls;
    }

    public Map<String, Integer> getOggetti() {
        
        return oggetti;
    }

    public void setOggetti(Map<String, Integer> oggetti) {
        
        this.oggetti = oggetti;
    }

    public Set<String> getBase() {
        
        return base;
    }

    public void setBase(Set<String> base) {
        
        this.base = base;
    }

    public Map<String, Integer> getBalls() {
        
        return balls;
    }

    public void setBalls(Map<String, Integer> balls) {
        
        this.balls = balls;
    }

    public String toString() {
        
        return "Inventory [oggetti=" + oggetti + ", base=" + base + ", balls=" + balls + "]";
    }
}
