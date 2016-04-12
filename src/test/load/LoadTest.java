package test.load;

import controller.load.LoadController;
import controller.modelResources.General;
import model.pokemon.InitializeMoves;

public class LoadTest {

    public static void main(String[] args) {
        InitializeMoves.initAllPokemonsTypes();
        General g = new LoadController().load();
        System.out.println("LOADED SUCCESSFULLY");
        System.out.println(g.toString());
    }
}
