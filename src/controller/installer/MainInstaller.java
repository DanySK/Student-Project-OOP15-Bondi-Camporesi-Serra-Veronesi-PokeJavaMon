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
import controller.parameters.Folder;
import controller.parameters.Img;
import controller.parameters.Maps;
import controller.parameters.Music;
import controller.parameters.FrontSpriteImage;

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
        installFrontSprites();
        installBackSprites();      
        installMaps();
        installImg();
    }
    
    /**
     * Install the required folders
     */
    private void installFolders() {
        for (Folder f : Folder.values()) {
            if(!Files.exists(Paths.get(f.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                this.success = new File(f.getAbsolutePath()).mkdirs();
                if (!this.success) {
                    return;
                }
            }
        }
    }
    
    /**
     * Install the required songs
     */
    private void installMusic() {
        for (final Music m : Music.values()) {
            if (m != Music.SONG && !Files.exists(Paths.get(Folder.MUSICFOLDER.getAbsolutePath() + m.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
                try(InputStream musicStream = this.getClass().getResourceAsStream(m.getResourcePath());
                        FileOutputStream fos = new FileOutputStream(Folder.MUSICFOLDER.getAbsolutePath() + m.getAbsolutePath())) {
                    final byte[] buf = new byte[SIZE];
                    int r = musicStream.read(buf);
                    while(r != NODATA) {
                        fos.write(buf, OFFSET, r);
                        r = musicStream.read(buf);
                    }
                } catch (IOException e) {
                    System.out.println("FAILED INSTALLING MUSICFOLDER");
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
                    System.out.println("FAILED INSTALLING FRONTSPRITEFOLDER SPRITE");
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
                    System.out.println("FAILED INSTALLING BACKSPRITEFOLDER SPRITE");
                    return;
                }
            }
        }
    }
    
    /**
     * Install the required files in Maps folder
     */
    private void installMaps() {
        for (final Maps fp : Arrays.asList(Maps.MAP, Maps.TILESET, Maps.PSD)) {
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
                    System.out.println("FAILED INSTALLING MAPS");
                    return;
                }
            }
        }
    }
    
    /**
     * Install the required files in Img folder
     */
    private void installImg() {
        for (final Img fp : Arrays.asList(Img.SHEET, Img.PACK, Img.PLAYER, Img.PALLA)) {
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
                    System.out.println("FAILED INSTALLING IMG");
                    return;
                }
            }
        }
    }
}