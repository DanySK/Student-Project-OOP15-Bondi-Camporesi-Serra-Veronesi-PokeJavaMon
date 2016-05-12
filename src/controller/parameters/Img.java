package controller.parameters;

import java.io.File;

/**
 * This enum contains the paths to the resources of the project in Img folder
 */
public enum Img {  
    SHEET ("/img/playersheet.png", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "playersheet.png"),
    PACK ("/img/player.pack", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "player.pack"),
    PLAYER ("/img/player.png", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "player.png"),
    PALLA ("/img/POKEPALLA.png", Folder.MAINFOLDER.getAbsolutePath() + File.separator + Constants.IMG + File.separator + "POKEPALLA.png");
	
    private String jar;
    private String abs;
    
    private static class Constants {
        public static final String IMG = "img";
    }
    
    private Img(final String jar, final String abs) {
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