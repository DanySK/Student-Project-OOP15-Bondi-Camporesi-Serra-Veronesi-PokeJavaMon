package test.load;

import controller.MainController;
import model.player.PlayerImpl;

public class LoadTest {

    public static void main(String[] args) {
        MainController.getController().load();
        System.out.println("LOADED SUCCESSFULLY");
        System.out.println(Integer.toString(PlayerImpl.getPlayer().getMoney()));
        System.out.println(Float.toString(PlayerImpl.getPlayer().getTileX()));
        System.out.println(Float.toString(PlayerImpl.getPlayer().getTileY()));
        System.out.println(PlayerImpl.getPlayer().getSquad().getPokemonList().toString());
        System.out.println(PlayerImpl.getPlayer().getInventory().toString());
        System.out.println(PlayerImpl.getPlayer().getBox().getPokemonList().toString());
    }
}
