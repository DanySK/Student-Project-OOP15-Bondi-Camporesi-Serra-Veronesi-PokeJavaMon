package controller.installer;

/**
 * This interface shows the methods {@link MainInstaller} must implements, and the methods
 * that can be called on {@link MainInstaller}
 */
public interface Installer {

    /**
     * Install in the user home all the necessaries file
     */
    void install();
}