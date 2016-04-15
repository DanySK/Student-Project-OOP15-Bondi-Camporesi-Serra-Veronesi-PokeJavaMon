package controller.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import controller.parameters.Music;

public class MainMusicController implements MusicController {
    private static Sound s;
    private Music m;
    
    public void play(Music song) {        
        s = Gdx.audio.newSound(Gdx.files.classpath(song.getPath()));
        s.loop();
        m = song;
    }
    
    public void stop() {
        s.stop();
        s.dispose();
    }
    
    public Music playing() {
        return m;
    }
}