package controller.parameters;

import java.io.File;

public enum BackSpriteImage {
    MISSINGNO("/sprites/back/B000.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B000.png"),
    BULBASAUR("/sprites/back/B001.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B001.png"),
    IVYSAUR("/sprites/back/B002.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B002.png"),
    VENUSAUR("/sprites/back/B003.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B003.png"),
    CHARMANDER("/sprites/back/B004.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B004.png"),
    CHARMELEON("/sprites/back/B005.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B005.png"),
    CHARIZARD("/sprites/back/B006.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B006.png"),
    SQUIRTLE("/sprites/back/B007.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B007.png"),
    WARTORTLE("/sprites/back/B008.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B008.png"),
    BLASTOISE("/sprites/back/B009.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B009.png"),
    PIDGEY("/sprites/back/B016.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B016.png"),
    PIDGEOTTO("/sprites/back/B017.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B017.png"),
    PIDGEOT("/sprites/back/B018.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B018.png"),
    RATTATA("/sprites/back/B019.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B019.png"),
    RATICATE("/sprites/back/B020.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B020.png"),
    PIKACHU("/sprites/back/B025.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B025.png"),
    RAICHU("/sprites/back/B026.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B026.png"),
    SANDSHREW("/sprites/back/B027.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B027.png"),
    SANDSLASH("/sprites/back/B028.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B028.png"),
    ZUBAT("/sprites/back/B041.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B041.png"),
    GOLBAT("/sprites/back/B042.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B042.png"),
    TENTACOOL("/sprites/back/B072.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B072.png"),
    TENTACRUEL("/sprites/back/B073.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B073.png"),
    RAYQUAZA("/sprites/back/B384.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "back" + File.separator + "B384.png");
    
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