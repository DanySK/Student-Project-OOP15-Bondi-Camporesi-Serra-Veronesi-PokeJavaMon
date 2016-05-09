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
        installMaps();
        installImg();
    }
    
    /**
     * Install the required folders
     */
    private void installFolders() {
        if (!Files.exists(Paths.get(Folder.MAINFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.MAINFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING MAIN FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.SAVEFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.SAVEFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING SAVEFOLDER FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.MUSICFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.MUSICFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING MUSICFOLDER FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.MAPSFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.MAPSFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING MAPSFOLDER FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.IMGFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.IMGFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING IMG FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.SPRITESFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.SPRITESFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING SPRITESFOLDER FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.FRONTSPRITEFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.FRONTSPRITEFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING FRONTSPRITEFOLDER FOLDER");
                return;
            }
        }
        if (!Files.exists(Paths.get(Folder.BACKSPRITEFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            this.success = new File(Folder.BACKSPRITEFOLDER.getAbsolutePath()).mkdirs();
            if (!this.success) {
                System.out.println("FAILED CREATING BACKSPRITEFOLDER FOLDER");
                return;
            }
        }
    }
    
    /**
     * Install the required songs
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