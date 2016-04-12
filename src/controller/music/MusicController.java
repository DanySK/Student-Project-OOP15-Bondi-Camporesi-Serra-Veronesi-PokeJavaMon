package controller.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import controller.parameters.Music;

public class MusicController implements MusicControllerInterface {
    private Sound s;
    
    public void play(Music song) {        
        s = Gdx.audio.newSound(Gdx.files.classpath(song.getPath()));
        s.loop();
    }
    
    public void stop() {
        s.stop();
        s.dispose();
    }
}