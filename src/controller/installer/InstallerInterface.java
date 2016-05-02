package controller.installer;

/**
 * This interface shows the methods {@link Installer} must implements, and the methods
 * that can be called on {@link Installer}
 */
public interface InstallerInterface {

    /**
     * Install in the user home all the necessaries file
     */
    void install();
}