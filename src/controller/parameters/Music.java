package controller.parameters;

public enum Music {
    HOME("home.mp3"), 
    OPENING("opening.mp3"),
    LAB("lab.mp3"),
    WILD("wild.mp3"),
    TRAINER("trainer.mp3"),
    CENTER("center.mp3"),
    MART("mart.mp3"),
    CAVE("cave.mp3"),
    TOWN("town.mp3"),
    ROUTE("route.mp3");
    
    private Music(String s) {
        this.path = s;
    }
    
    String path;
    
    public String getPath() {
        return this.path;
    }
}