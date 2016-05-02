package controller.parameters;

import java.io.File;

/**
 * This enum contains various paths
 */
public enum FilePath {
    MAINFOLDER ("NONE", System.getProperty("user.home") + File.separator + ".pokejava"),
    SAVE ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "save"),
    MAPS ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "maps"),
    IMG ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "img"),
    MUSIC ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "music"),
    SPRITES("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "sprites"),
    FRONT("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "sprites" + File.separator + "front"),
    BACK("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "sprites" + File.separator + "back"),
    MAP ("/maps/map.tmx", MAINFOLDER.getAbsolutePath() + File.separator + "maps" + File.separator + "map.tmx"),
    TILESET ("/maps/tileset5.png", MAINFOLDER.getAbsolutePath() + File.separator + "maps" + File.separator + "tileset5.png"),
    PSD ("/maps/tileset5.psd", MAINFOLDER.getAbsolutePath() + File.separator + "maps" + File.separator + "tileset5.psd"),
    SHEET ("/img/playersheet.png", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "playersheet.png"),
    PACK ("/img/player.pack", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "player.pack"),
    PLAYER ("/img/player.png", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "player.png"),
    PALLA ("/img/POKEPALLA.png", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "POKEPALLA.png"),
    SONG ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "music" + File.separator);
	
    private FilePath(String jar, String abs) {
        this.jar = jar;
        this.abs = abs;
    }
    
    private String jar;
    private String abs;
    
    /**
     * @return the resource path of the selected file
     */
    public String getResourcePath() {
        return jar;
    }
    
    /**
     * @return the absolute path of the selected file
     */
    public String getAbsolutePath() {
        return abs;
    }
}