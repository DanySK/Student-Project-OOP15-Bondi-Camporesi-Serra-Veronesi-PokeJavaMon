package controller.parameters;

import java.io.File;

/**
 * This enum contains the paths to project's installation folders
 */
public enum Folder {
    MAINFOLDER (Constants.NONE, System.getProperty("user.home") + File.separator + ".pokejava"),
    SAVEFOLDER (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "save"),
    MAPSFOLDER (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + Constants.MAPS),
    IMGFOLDER (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG),
    MUSICFOLDER (Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "music"),
    SPRITESFOLDER(Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "sprites"),
    FRONTSPRITEFOLDER(Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "sprites" + File.separator + "front"),
    BACKSPRITEFOLDER(Constants.NONE, MAINFOLDER.getAbsolutePath() + File.separator + "sprites" + File.separator + "back");
    
    private String jar;
    private String abs;
    
    private static class Constants {
        public static final String NONE = "NONE";
        public static final String MAPS = "maps";
        public static final String IMG = "img";
    }
    
    private Folder(final String jar, final String abs) {
        this.jar = jar;
        this.abs = abs;
    }
    
    /**
     * @return the resource path of the selected folder
     */
    public String getResourcePath() {
        return jar;
    }
    
    /**
     * @return the absolute path of the selected folder
     */
    public String getAbsolutePath() {
        return abs;
    }
}