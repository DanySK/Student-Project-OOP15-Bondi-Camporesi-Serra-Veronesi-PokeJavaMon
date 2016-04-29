package controller.parameters;

import java.io.File;

public enum FilePath {

    MAINFOLDER ("NONE", System.getProperty("user.home") + File.separator + "PokeJava"),
    SAVE ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Save"),
    MAPS ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Maps"),
    IMG ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Img"),
    MUSIC ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Music"),
    SPRITES("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Sprites"),
    FRONT("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Sprites" + File.separator + "Front"),
    BACK("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Sprites" + File.separator + "Back"),
    MAP ("/map.tmx", MAINFOLDER.getAbsolutePath() + File.separator + "Maps" + File.separator + "map.tmx"),
    TILESET ("/tileset5.png", MAINFOLDER.getAbsolutePath() + File.separator + "Maps" + File.separator + "tileset5.png"),
    PSD ("/tileset5.psd", MAINFOLDER.getAbsolutePath() + File.separator + "Maps" + File.separator + "tileset5.psd"),
    SHEET ("/playersheet.png", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "playersheet.png"),
    PACK ("/player.pack", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "player.pack"),
    PLAYER ("/player.png", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "player.png"),
    PALLA ("/POKEPALLA.png", MAINFOLDER.getAbsolutePath() + File.separator + "Img" + File.separator + "POKEPALLA.png"),
    SONG ("NONE", MAINFOLDER.getAbsolutePath() + File.separator + "Music" + File.separator);
    
    private FilePath(String jar, String abs) {
        this.jar = jar;
        this.abs = abs;
    }
    
    private String jar;
    private String abs;
    
    public String getResourcePath() {
        return jar;
    }
    
    public String getAbsolutePath() {
        return abs;
    }
}
