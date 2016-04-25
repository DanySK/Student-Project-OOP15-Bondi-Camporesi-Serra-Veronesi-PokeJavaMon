package view;

import model.items.Item;
import model.pokemon.Move;
import model.pokemon.Pokemon;

public interface MethodsToImplement {
    
    // DANI QUANDO VEDI QUESTO MESSAGGIO SCRIVIMI CHE TI SPIEGO COSA DEVONO FARE QUESTI METODI
    
    public void movePlayerThenOpponent(Move first, Move second);
    
    public void moveOpponentThenPlayer(Move first, Move second);
    
    public void run();
    
    public void showMessageThenOpponentMove(String msg, Move mv);
    
    public void useItemThenOpponentMove(Item it, Move mv);
    
    public void changePokemonThenOpponentMove(Pokemon pk, Move mv);
}
