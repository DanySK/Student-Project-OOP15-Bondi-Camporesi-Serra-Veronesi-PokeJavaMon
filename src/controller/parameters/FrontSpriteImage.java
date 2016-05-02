package controller.parameters;

import java.io.File;

/**
 * This enum contains the paths to the back pokemon's sprites
 */
public enum FrontSpriteImage {
    MISSINGNO("/sprites/front/F000.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F000.png"),
    BULBASAUR("/sprites/front/F001.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F001.png"),
    IVYSAUR("/sprites/front/F002.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F002.png"),
    VENUSAUR("/sprites/front/F003.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F003.png"),
    CHARMANDER("/sprites/front/F004.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F004.png"),
    CHARMELEON("/sprites/front/F005.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F005.png"),
    CHARIZARD("/sprites/front/F006.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F006.png"),
    SQUIRTLE("/sprites/front/F007.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F007.png"),
    WARTORTLE("/sprites/front/F008.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F008.png"),
    BLASTOISE("/sprites/front/F009.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F009.png"),
    PIDGEY("/sprites/front/F016.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F016.png"),
    PIDGEOTTO("/sprites/front/F017.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F017.png"),
    PIDGEOT("/sprites/front/F018.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F018.png"),
    RATTATA("/sprites/front/F019.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F019.png"),
    RATICATE("/sprites/front/F020.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F020.png"),
    PIKACHU("/sprites/front/F025.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F025.png"),
    RAICHU("/sprites/front/F026.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F026.png"),
    SANDSHREW("/sprites/front/F027.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F027.png"),
    SANDSLASH("/sprites/front/F028.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F028.png"),
    ZUBAT("/sprites/front/F041.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F041.png"),
    GOLBAT("/sprites/front/F042.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F042.png"),
    TENTACOOL("/sprites/front/F072.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F072.png"),
    TENTACRUEL("/sprites/front/F073.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F073.png"),
    RAYQUAZA("/sprites/front/F384.png", System.getProperty("user.home") + File.separator + ".pokejava" + File.separator + "sprites" + File.separator + "front" + File.separator + "F384.png");
    
    private String resourcePath;
    private String absolutePath;
    
    private FrontSpriteImage(String path, String abs) {
        this.resourcePath = path;
        this.absolutePath = abs;
    }
    
    /**
     * @return the resource path of the selected sprite
     */
    public String getResourcePath() {
        return this.resourcePath;
    }
    
    /**
     * @return the absolute path of the selected sprite
     */
    public String getAbsolutePath() {
        return this.absolutePath;
    }
}