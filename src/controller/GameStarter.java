package controller;
import controller.viewResources.TitleWiew;
import model.pokemon.InitializeMoves;

public class GameStarter {

    public static void main(String[] args) {

        InitializeMoves.initAllPokemonsTypes();
        new TitleWiew();
        TitleWiew.title();
    }
}