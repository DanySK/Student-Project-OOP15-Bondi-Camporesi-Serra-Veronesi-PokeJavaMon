package controller.modelResources;

import java.util.Set;

public class Pokemon {
    
    private String nome;
    private int hp;
    private int exp;
    private Set<String> moves;
    
    public Pokemon(String n, int h, int e, Set<String> s) {
        
        nome = n;
        hp = h;
        exp = e;
        moves = s;
    }

    public String getNome() {
        
        return nome;
    }

    public void setNome(String nome) {
        
        this.nome = nome;
    }

    public int getHp() {
        
        return hp;
    }

    public void setHp(int hp) {
        
        this.hp = hp;
    }

    public int getExp() {
        
        return exp;
    }

    public void setExp(int exp) {
        
        this.exp = exp;
    }

    public Set<String> getMoves() {
        
        return moves;
    }

    public void setMoves(Set<String> moves) {
        
        this.moves = moves;
    }

    public String toString() {
        
        return "Pok�mon [nome=" + nome + ", hp=" + hp + ", exp=" + exp + ", moves=" + moves + "]";
    }
}
