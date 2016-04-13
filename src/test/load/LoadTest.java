package test.load;

import controller.load.LoadController;
import model.pokemon.InitializeMoves;
import model.resources.General;

public class LoadTest {

    public static void main(String[] args) {
        InitializeMoves.initAllPokemonsTypes();
        General g = new LoadController().load();
        System.out.println("LOADED SUCCESSFULLY");
        System.out.println(g.toString());
    }
}
