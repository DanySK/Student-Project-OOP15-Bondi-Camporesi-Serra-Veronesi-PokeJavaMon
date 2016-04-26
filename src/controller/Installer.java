package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
                try {
                    File f = new File(this.getClass().getResource("/" + m.getPath()).getPath());
                    Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.SONG.getAbsolutePath() + m.getPath()),StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            for (FrontSpriteImage s : FrontSpriteImage.values()) {
                try {
                    File f = new File(this.getClass().getResource(s.getResourcePath()).getPath());
                    Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(s.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            for (BackSpriteImage s : BackSpriteImage.values()) {
                try {
                    File f = new File(this.getClass().getResource(s.getResourcePath()).getPath());
                    Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(s.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            try {
                File f = new File(this.getClass().getResource(FilePath.SHEET.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.SHEET.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                f = new File(this.getClass().getResource(FilePath.PACK.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.PACK.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                f = new File(this.getClass().getResource(FilePath.PLAYER.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.PLAYER.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                f = new File(this.getClass().getResource(FilePath.PALLA.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.PALLA.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                f = new File(this.getClass().getResource(FilePath.MAP.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.MAP.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                f = new File(this.getClass().getResource(FilePath.TILESET.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.TILESET.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
                f = new File(this.getClass().getResource(FilePath.PSD.getResourcePath()).getPath());
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(FilePath.PSD.getAbsolutePath()),StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("GAME ALREADY INSTALLED");
        }
    }
}
