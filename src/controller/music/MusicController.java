package controller.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import controller.parameters.Music;

public class MusicController {
    private static Sound s;
    
    public static void play(Music song) {        
        s = Gdx.audio.newSound(Gdx.files.classpath(song.getPath()));
        s.loop();
    }
    
    public static void stop() {
        s.stop();
        s.dispose();
    }
}