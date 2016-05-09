package controller.parameters;

import java.io.File;

/**
 * This enum contains the paths to the resources of the project in Maps folder
 */
public enum Maps {

    MAP ("/maps/map.tmx", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS + File.separator + "map.tmx"),
    TILESET ("/maps/tileset5.png", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS + File.separator + "tileset5.png"),
    PSD ("/maps/tileset5.psd", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS + File.separator + "tileset5.psd");
    
    private String jar;
    private String abs;
    
    private static class Constants {
        public static final String MAPS = "maps";
    }
    
    private Maps(final String jar, final String abs) {
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
