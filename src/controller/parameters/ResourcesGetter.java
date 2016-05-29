package controller.parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.utilities.Pair;

/**
 * This class is created to return the {@link List}<{@link Pair}<{@link String}, {@link String}>>
 * where the first {@link String} is the absolute path of a resource, the second is the
 * relative path
 */
public class ResourcesGetter {

    private final List<Pair<String, String>> resources;
    
    /**
     * Public constructor that initializes the {@link List}
     */
    public ResourcesGetter() {
        this.resources = new ArrayList<>();
        for (FrontSpriteImage f : FrontSpriteImage.values()) {
            this.resources.add(new Pair<String, String>(f.getAbsolutePath(), f.getResourcePath()));
        }
        for (BackSpriteImage b : BackSpriteImage.values()) {
            this.resources.add(new Pair<String, String>(b.getAbsolutePath(), b.getResourcePath()));
        }
        for (Maps m : Maps.values()) {
            this.resources.add(new Pair<String, String>(m.getAbsolutePath(), m.getResourcePath()));
        }
        for (Img i : Img.values()) {
            this.resources.add(new Pair<String, String>(i.getAbsolutePath(), i.getResourcePath()));
        }
    }
    
    /**
     * @return the {@link List} of resource's paths
     */
    public List<Pair<String, String>> getResources() {
        return Collections.unmodifiableList(this.resources);
    }
}