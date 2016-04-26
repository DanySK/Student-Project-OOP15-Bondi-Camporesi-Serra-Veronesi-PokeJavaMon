package controller.parameters;

import java.io.File;

public enum FilePath {

    MAINFOLDER ("NONE", System.getProperty("user.home") + File.separator + "PokeJava"),
    SAVE ("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Save"),
    MAPS ("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps"),
    IMG ("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img"),
    MUSIC ("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Music"),
    SPRITES("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites"),
    FRONT("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Front"),
    BACK("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Sprites" + File.separator + "Back"),
    MAP ("/map.tmx", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps" + File.separator + "map.tmx"),
    TILESET ("/tileset5.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps" + File.separator + "tileset5.png"),
    PSD ("/tileset5.psd", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps" + File.separator + "tileset5.psd"),
    SHEET ("/playersheet.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "playersheet.png"),
    PACK ("/player.pack", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "player.pack"),
    PLAYER ("/player.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "player.png"),
    PALLA ("/POKEPALLA.png", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "POKEPALLA.png"),
    SONG ("NONE", System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Music" + File.separator);
    
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
