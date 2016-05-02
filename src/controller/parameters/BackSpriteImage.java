package controller.parameters;

import java.io.File;

/**
 * This enum contains the paths to the back pokemon's Constants.SPRITES
 */
public enum BackSpriteImage {
    MISSINGNO("/Constants.SPRITES/back/B000.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B000.png"),
    BULBASAUR("/Constants.SPRITES/back/B001.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B001.png"),
    IVYSAUR("/Constants.SPRITES/back/B002.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B002.png"),
    VENUSAUR("/Constants.SPRITES/back/B003.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B003.png"),
    CHARMANDER("/Constants.SPRITES/back/B004.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B004.png"),
    CHARMELEON("/Constants.SPRITES/back/B005.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B005.png"),
    CHARIZARD("/Constants.SPRITES/back/B006.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B006.png"),
    SQUIRTLE("/Constants.SPRITES/back/B007.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B007.png"),
    WARTORTLE("/Constants.SPRITES/back/B008.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B008.png"),
    BLASTOISE("/Constants.SPRITES/back/B009.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B009.png"),
    PIDGEY("/Constants.SPRITES/back/B016.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B016.png"),
    PIDGEOTTO("/Constants.SPRITES/back/B017.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B017.png"),
    PIDGEOT("/Constants.SPRITES/back/B018.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B018.png"),
    RATTATA("/Constants.SPRITES/back/B019.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B019.png"),
    RATICATE("/Constants.SPRITES/back/B020.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B020.png"),
    PIKACHU("/Constants.SPRITES/back/B025.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B025.png"),
    RAICHU("/Constants.SPRITES/back/B026.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B026.png"),
    SANDSHREW("/Constants.SPRITES/back/B027.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B027.png"),
    SANDSLASH("/Constants.SPRITES/back/B028.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B028.png"),
    ZUBAT("/Constants.SPRITES/back/B041.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B041.png"),
    GOLBAT("/Constants.SPRITES/back/B042.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B042.png"),
    TENTACOOL("/Constants.SPRITES/back/B072.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B072.png"),
    TENTACRUEL("/Constants.SPRITES/back/B073.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B073.png"),
    RAYQUAZA("/Constants.SPRITES/back/B384.png", System.getProperty(Constants.HOME) + File.separator + Constants.MAINFOLDER + File.separator + Constants.SPRITES + File.separator + Constants.BACK + File.separator + "B384.png");
    
    private String resourcePath;
    private String absolutePath;
    
    private static class Constants {
        private static final String BACK = "back";
        private static final String HOME = "user.home";
        private static final String MAINFOLDER = ".pokejava";
        public  static final String SPRITES = "sprites";
    }
    
    private BackSpriteImage(final String path, final String abs) {
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