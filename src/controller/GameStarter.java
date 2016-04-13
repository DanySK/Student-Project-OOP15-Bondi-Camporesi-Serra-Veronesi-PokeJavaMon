package controller;
import model.pokemon.InitializeMoves;
import view.resources.TitleWiew;

public class GameStarter {

    public static void main(String[] args) {

        InitializeMoves.initAllPokemonsTypes();
        new TitleWiew();
        TitleWiew.title();
    }
}