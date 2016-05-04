package controller.installer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Arrays;

import controller.parameters.BackSpriteImage;
import controller.parameters.FilePath;
import controller.parameters.Music;
import controller.parameters.FrontSpriteImage;

/**
 * This class installs all the requested files into the disk
 */
public class Installer implements InstallerInterface {

    private static final int OFFSET = 0;
    private static final int NODATA = -1;
    private static final int SIZE = 2048;
    private boolean success;

    @Override
    public void install() {
        installFolders();
        installMusic();
        installFrontSprites();
        installBackSprites();      
        installResources();
    }
    
    /**
     * Install the required folders
     */
    private void installFolders() {
        if (!Files.exists(Paths.get(FilePath.MAINFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.MAINFOLDER.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MAIN FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.SAVE.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.SAVE.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING SAVE FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.MUSIC.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.MUSIC.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MUSIC FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.MAPS.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.MAPS.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MAPS FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.IMG.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.IMG.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING IMG FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.SPRITES.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.SPRITES.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING SPRITES FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.FRONT.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.FRONT.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING FRONT FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(FilePath.BACK.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = new File(FilePath.BACK.getAbsolutePath()).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING BACK FOLDER");
                return;
            }
        }
    }
    
    /**
     * Install the required songs
     */
    private void installMusic() {
        for (final Music m : Music.values()) {
            if (!Files.exists(Paths.get(FilePath.MUSIC.getAbsolutePath() + m.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream musicStream = this.getClass().getResourceAsStream(m.getResourcePath());
                        FileOutputStream fos = new FileOutputStream(FilePath.MUSIC.getAbsolutePath() + m.getAbsolutePath())) {
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
     * Install the required front sprites
     */
    private void installFrontSprites() {
        for (final FrontSpriteImage s : FrontSpriteImage.values()) {
            if (!Files.exists(Paths.get(s.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream is = this.getClass().getResourceAsStream(s.getResourcePath());
                        FileOutputStream fos = new FileOutputStream(s.getAbsolutePath())) {
                    final byte[] buf = new byte[SIZE];
                    int r = is.read(buf);
                    while(r != NODATA) {
                        fos.write(buf, OFFSET, r);
                        r = is.read(buf);
                    }
                } catch (IOException e) {
                    System.out.println("FAILED INSTALLING FRONT SPRITE");
                    return;
                }
            }
        }
    }
    
    /**
     * Install the required back sprites
     */
    private void installBackSprites() {
        for (final BackSpriteImage s : BackSpriteImage.values()) {
            if (!Files.exists(Paths.get(s.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream is = this.getClass().getResourceAsStream(s.getResourcePath());
                        FileOutputStream fos = new FileOutputStream(s.getAbsolutePath())) {
                    final byte[] buf = new byte[SIZE];
                    int r = is.read(buf);
                    while(r != NODATA) {
                        fos.write(buf, OFFSET, r);
                        r = is.read(buf);
                    }
                } catch (IOException e) {
                    System.out.println("FAILED INSTALLING BACK SPRITE");
                    return;
                }
            }
        }
    }
    
    /**
     * Install the required resources
     */
    private void installResources() {
        for (final FilePath fp : Arrays.asList(FilePath.SHEET, FilePath.PACK, FilePath.PLAYER, FilePath.PALLA, FilePath.MAP, FilePath.TILESET, FilePath.PSD)) {
            if (!Files.exists(Paths.get(fp.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream is = this.getClass().getResourceAsStream(fp.getResourcePath());
                        FileOutputStream fos = new FileOutputStream(fp.getAbsolutePath())) {
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