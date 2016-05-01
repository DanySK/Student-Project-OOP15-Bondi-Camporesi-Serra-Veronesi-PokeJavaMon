package controller.parameters;

import java.io.File;

public enum BackSpriteImage {
    MISSINGNO("/B000.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B000.png"),
    BULBASAUR("/B001.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B001.png"),
    IVYSAUR("/B002.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B002.png"),
    VENUSAUR("/B003.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B003.png"),
    CHARMANDER("/B004.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B004.png"),
    CHARMELEON("/B005.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B005.png"),
    CHARIZARD("/B006.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B006.png"),
    SQUIRTLE("/B007.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B007.png"),
    WARTORTLE("/B008.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B008.png"),
    BLASTOISE("/B009.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B009.png"),
    PIDGEY("/B016.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B016.png"),
    PIDGEOTTO("/B017.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B017.png"),
    PIDGEOT("/B018.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B018.png"),
    RATTATA("/B019.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B019.png"),
    RATICATE("/B020.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B020.png"),
    PIKACHU("/B025.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B025.png"),
    RAICHU("/B026.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B026.png"),
    SANDSHREW("/B027.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B027.png"),
    SANDSLASH("/B028.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B028.png"),
    ZUBAT("/B041.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B041.png"),
    GOLBAT("/B042.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B042.png"),
    TENTACOOL("/B072.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B072.png"),
    TENTACRUEL("/B073.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B073.png"),
    RAYQUAZA("/B384.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back" + File.separator + "B384.png");
    
    private String resourcePath;
    private String absolutePath;
    
    private BackSpriteImage(String path, String abs) {
        this.resourcePath = path;
        this.absolutePath = abs;
    }
    
    public String getResourcePath() {
        return this.resourcePath;
    }
    
    public String getAbsolutePath() {
        return this.absolutePath;
    }
}