package controller.installer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import controller.parameters.Folder;
import controller.parameters.Music;
import controller.parameters.ResourcesGetter;
import model.utilities.Pair;

/**
 * This class installs all the requested files into the disk
 */
public class MainInstaller implements Installer {

    private static final int OFFSET = 0;
    private static final int NODATA = -1;
    private static final int SIZE = 2048;
    private boolean success;

    @Override
    public void install() {
        installFolders();
        installMusic();
        installResources();
    }
    
    /**
     * Installs the required folders
     */
    private void installFolders() {
        for (final Folder f : Folder.values()) {
            if(!Files.exists(Paths.get(f.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                this.success = new File(f.getAbsolutePath()).mkdirs();
                if (!this.success) {
                    System.out.println("FAILED INSTALLING FOLDER");
                    return;
                }
            }
        }
    }
    
    /**
     * Installs the required songs
     */
    private void installMusic() {
        for (final Music m : Music.values()) {
            if (!Files.exists(Paths.get(Folder.MUSICFOLDER.getAbsolutePath() + m.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream musicStream = this.getClass().getResourceAsStream(m.getResourcePath());
                        FileOutputStream fos = new FileOutputStream(Folder.MUSICFOLDER.getAbsolutePath() + m.getAbsolutePath())) {
                    final byte[] buf = new byte[SIZE];
                    int r = musicStream.read(buf);
                    while(r != NODATA) {
                        fos.write(buf, OFFSET, r);
                        r = musicStream.read(buf);
                    }
                } catch (IOException e) {
                    System.out.println("FAILED INSTALLING MUSIC");
                    return;
                }
            }
        }
    }
    
    /**
     * Installs the required resources
     */
    private void installResources() {
        for (final Pair<String, String> p : new ResourcesGetter().getResources()) {
            if (!Files.exists(Paths.get(p.getX()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream is = this.getClass().getResourceAsStream(p.getY());
                        FileOutputStream fos = new FileOutputStream(p.getX())) {
                    final byte[] buf = new byte[SIZE];
                    int r = is.read(buf);
                    while(r != NODATA) {
                        fos.write(buf, OFFSET, r);
                        r = is.read(buf);
                    }
                } catch (IOException e) {
                    System.out.println("FAILED INSTALLING RESOURCE");
                    return;
                }
            }
        }
    }
}