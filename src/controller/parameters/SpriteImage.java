package controller.parameters;

public enum SpriteImage {

    MISSINGNO("/000.png"),
    BULBASAUR("/001.png"),
    IVYSAUR("/002.png"),
    VENUSAUR("/003.png"),
    CHARMANDER("/004.png"),
    CHARMELEON("/005.png"),
    CHARIZARD("/006.png"),
    SQUIRTLE("/007.png"),
    WARTORTLE("/008.png"),
    BLASTOISE("/009.png"),
    PIDGEY("/016.png"),
    PIDGEOTTO("/017.png"),
    PIDGEOT("/018.png"),
    RATTATA("/019.png"),
    RATICATE("/020.png"),
    PIKACHU("/025.png"),
    RAICHU("/026.png"),
    SANDSHREW("/027.png"),
    SANDSLASH("/028.png"),
    ZUBAT("/041.png"),
    GOLBAT("/042.png"),
    TENTACOOL("/072.png"),
    TENTACRUEL("/073.png"),
    RAYQUAZA("/384.png");
    
    private String resourcePath;
    
    private SpriteImage(String path) {
        this.resourcePath = path;
    }
    
    public String getResourcePath() {
        return this.resourcePath;
    }
}
