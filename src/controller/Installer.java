package controller;

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

public class Installer {

    private boolean success;

    public void install() {
        if (!Files.exists(Paths.get(FilePath.MAINFOLDER.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS)) {
            success = (new File(FilePath.MAINFOLDER.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MAIN FOLDER");
                return;
            }
            success = (new File(FilePath.SAVE.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING SAVE FOLDER");
                return;
            }
            success = (new File(FilePath.MUSIC.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MUSIC FOLDER");
                return;
            }
            success = (new File(FilePath.MAPS.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MAPS FOLDER");
                return;
            }
            success = (new File(FilePath.IMG.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING IMG FOLDER");
                return;
            }
            success = (new File(FilePath.SPRITES.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING SPRITES FOLDER");
                return;
            }
            success = (new File(FilePath.FRONT.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING FRONT FOLDER");
                return;
            }
            success = (new File(FilePath.BACK.getAbsolutePath())).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING BACK FOLDER");
                return;
            }
            
            for (Music m : Music.values()) {
               	try(InputStream musicStream = this.getClass().getResourceAsStream("/" + m.getPath());
               		FileOutputStream fos = new FileOutputStream(FilePath.MUSIC.getAbsolutePath() + "/" + m.getPath())) {
               	    byte[] buf = new byte[2048];
               	    int r = musicStream.read(buf);
               	    while(r != -1) {
               	        fos.write(buf, 0, r);
               	        r = musicStream.read(buf);
               	    }
               	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
                	
            }
            for (FrontSpriteImage s : FrontSpriteImage.values()) {

               	try(InputStream is = this.getClass().getResourceAsStream(s.getResourcePath());
               		FileOutputStream fos = new FileOutputStream(s.getAbsolutePath())) {
               	    byte[] buf = new byte[2048];
               	    int r = is.read(buf);
               	    while(r != -1) {
               	        fos.write(buf, 0, r);
               	        r = is.read(buf);
               	    }
               	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
                	
            
            }
            for (BackSpriteImage s : BackSpriteImage.values()) {
               	try(InputStream is = this.getClass().getResourceAsStream(s.getResourcePath());
                   		FileOutputStream fos = new FileOutputStream(s.getAbsolutePath())) {
                   	    byte[] buf = new byte[2048];
                   	    int r = is.read(buf);
                   	    while(r != -1) {
                   	        fos.write(buf, 0, r);
                   	        r = is.read(buf);
                   	    }
                   	} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    					return;
    				}
            }
            
            for (final FilePath fp : Arrays.asList(FilePath.SHEET, FilePath.PACK, FilePath.PLAYER, FilePath.PALLA, FilePath.MAP, FilePath.TILESET, FilePath.PSD)) {
               	try(InputStream is = this.getClass().getResourceAsStream(fp.getResourcePath());
                   		FileOutputStream fos = new FileOutputStream(fp.getAbsolutePath())) {
                   	    byte[] buf = new byte[2048];
                   	    int r = is.read(buf);
                   	    while(r != -1) {
                   	        fos.write(buf, 0, r);
                   	        r = is.read(buf);
                   	    }
                   	} catch (IOException e) {
    					e.printStackTrace();
    					return;
    				}
            } 
        } else {
            System.out.println("GAME ALREADY INSTALLED");
        }
    }
}
