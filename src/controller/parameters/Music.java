package controller.parameters;

public enum Music {
    HOME("music/home.mp3"), 
    OPENING("music/opening.mp3"),
    LAB("music/lab.mp3"),
    WILD("music/wild.mp3"),
    TRAINER("music/trainer.mp3"),
    CENTER("music/center.mp3"),
    MART("music/mart.mp3"),
    CAVE("music/cave.mp3"),
    TOWN("music/town.mp3"),
    ROUTE("music/route.mp3");
    
    private Music(String s) {
        this.path = s;
    }
    
    String path;
    
    public String getPath() {
        return this.path;
    }
}