package controller.parameters;

public enum Music {
    HOME("/music/home.mp3", "/home.mp3"), 
    OPENING("/music/opening.mp3", "/opening.mp3"),
    LAB("/music/lab.mp3", "/lab.mp3"),
    WILD("/music/wild.mp3", "/wild.mp3"),
    TRAINER("/music/trainer.mp3", "/trainer.mp3"),
    CENTER("/music/center.mp3", "/center.mp3"),
    MART("/music/mart.mp3", "/mart.mp3"),
    CAVE("/music/cave.mp3", "/cave.mp3"),
    TOWN("/music/town.mp3", "/town.mp3"),
    ROUTE("/music/route.mp3", "/route.mp3");

    private Music(String rp, String absp) {
        this.resPath = rp;
        this.absPath = absp;
    }
    
    final String resPath;
    final String absPath;
    
    public String getResourcePath() {
        return this.resPath;
    }
    
    public String getAbsolutePath() {
    	return this.absPath;
    }
}