package controller.parameters;

import java.io.File;

/**
 * This enum contains the paths to the resources of the project
 */
public enum FilePath {
    MAINFOLDER (Constants.NONE, System.getProperty("user.home") + File.separator + ".pokejava"),
    SAVE (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "save"),
    MAPS (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS),
    IMG (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG),
    MUSIC (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "music"),
    SPRITES(Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "sprites"),
    FRONT(Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "sprites" + File.separator + "front"),
    BACK(Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "sprites" + File.separator + "back"),
    MAP ("/maps/map.tmx", MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS + File.separator + "map.tmx"),
    TILESET ("/maps/tileset5.png", MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS + File.separator + "tileset5.png"),
    PSD ("/maps/tileset5.psd", MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS + File.separator + "tileset5.psd"),
    SHEET ("/img/playersheet.png", MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "playersheet.png"),
    PACK ("/img/player.pack", MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "player.pack"),
    PLAYER ("/img/player.png", MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "player.png"),
    PALLA ("/img/POKEPALLA.png", MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "POKEPALLA.png"),
    SONG (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "music" + File.separator);
	
    private String jar;
    private String abs;
    
    private static class Constants {
        public static final String NONE = "NONE";
        public static final String MAPS = "maps";
        public static final String IMG = "img";
    }
    
    private FilePath(final String jar, final String abs) {
        this.jar = jar;
        this.abs = abs;
    }
    
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