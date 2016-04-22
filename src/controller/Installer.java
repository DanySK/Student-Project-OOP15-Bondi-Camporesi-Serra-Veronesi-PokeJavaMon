package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import controller.parameters.Music;

public class Installer {

    private boolean success;
    
    public void install() {
        if (!Files.exists(Paths.get(System.getProperty("user.home") + File.separator + "PokeJava"), LinkOption.NOFOLLOW_LINKS)) {
            success = (new File(System.getProperty("user.home") + File.separator + "PokeJava")).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MAIN FOLDER");
                return;
            }
            success = (new File(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Save")).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING SAVE FOLDER");
                return;
            }
            success = (new File(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Music")).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MUSIC FOLDER");
                return;
            }
            success = (new File(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps")).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING MAPS FOLDER");
                return;
            }
            success = (new File(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img")).mkdirs();
            if (!success) {
                System.out.println("FAILED CREATING IMG FOLDER");
                return;
            }
            for (Music m : Music.values()) {
                try {
                    File f = new File("resources/music/" + m.getPath());
                    Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Music" + File.separator + m.getPath()),StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            try {
                File f = new File("resources/img/player sheet.png");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "player sheet.png"),StandardCopyOption.REPLACE_EXISTING);
                f = new File("resources/img/player.pack");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "player.pack"),StandardCopyOption.REPLACE_EXISTING);
                f = new File("resources/img/player.png");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "player.png"),StandardCopyOption.REPLACE_EXISTING);
                f = new File("resources/img/POKEPALLA.png");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Img" + File.separator + "POKEPALLA.png"),StandardCopyOption.REPLACE_EXISTING);
                f = new File("resources/maps/map.tmx");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps" + File.separator + "map.tmx"),StandardCopyOption.REPLACE_EXISTING);
                f = new File("resources/maps/tileset5.png");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps" + File.separator + "tileset5.png"),StandardCopyOption.REPLACE_EXISTING);
                f = new File("resources/maps/tileset5.psd");
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(System.getProperty("user.home") + File.separator + "PokeJava" + File.separator + "Maps" + File.separator + "tileset5.psd"),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("GAME ALREADY INSTALLED");
        }
    }
}
