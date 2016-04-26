package controller.parameters;

import java.io.File;

public enum FrontSpriteImage {

    MISSINGNO("/F000.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F000.png"),
    BULBASAUR("/F001.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F001.png"),
    IVYSAUR("/F002.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F002.png"),
    VENUSAUR("/F003.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F003.png"),
    CHARMANDER("/F004.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F004.png"),
    CHARMELEON("/F005.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F005.png"),
    CHARIZARD("/F006.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F006.png"),
    SQUIRTLE("/F007.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F007.png"),
    WARTORTLE("/F008.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F008.png"),
    BLASTOISE("/F009.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F009.png"),
    PIDGEY("/F016.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F016.png"),
    PIDGEOTTO("/F017.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F017.png"),
    PIDGEOT("/F018.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F018.png"),
    RATTATA("/F019.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F019.png"),
    RATICATE("/F020.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F020.png"),
    PIKACHU("/F025.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F025.png"),
    RAICHU("/F026.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F026.png"),
    SANDSHREW("/F027.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F027.png"),
    SANDSLASH("/F028.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F028.png"),
    ZUBAT("/F041.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F041.png"),
    GOLBAT("/F042.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F042.png"),
    TENTACOOL("/F072.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F072.png"),
    TENTACRUEL("/F073.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F073.png"),
    RAYQUAZA("/F384.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front" + File.separator + "F384.png");
    
    private String resourcePath;
    private String absolutePath;
    
    private FrontSpriteImage(String path, String abs) {
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
