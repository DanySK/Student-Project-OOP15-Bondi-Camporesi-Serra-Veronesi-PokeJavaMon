package test.load;

import controller.load.LoadController;
import model.player.PlayerImpl;
import model.pokemon.InitializeMoves;

public class LoadTest {

    public static void main(String[] args) {
        InitializeMoves.initAllPokemonsTypes();
        LoadController.getController().load(null);
        System.out.println("LOADED SUCCESSFULLY");
        System.out.println(Integer.toString(PlayerImpl.getPlayer().getMoney()));
        System.out.println(Float.toString(PlayerImpl.getPlayer().getTileX()));
        System.out.println(Float.toString(PlayerImpl.getPlayer().getTileY()));
        System.out.println(PlayerImpl.getPlayer().getSquad().getPokemonList().toString());
        System.out.println(PlayerImpl.getPlayer().getInventory().toString());
        System.out.println(PlayerImpl.getPlayer().getBox().getPokemonList().toString());
    }
}
