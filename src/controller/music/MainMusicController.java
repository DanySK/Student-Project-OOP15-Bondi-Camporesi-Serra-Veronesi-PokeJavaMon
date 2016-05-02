package controller.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import controller.parameters.FilePath;
import controller.parameters.Music;

public class MainMusicController implements MusicController {
    
    private Sound s;
    private Music m = null;
    private static MainMusicController SINGLETON;
    
    private MainMusicController() {}
    
    public static MainMusicController getController() {
        if (SINGLETON == null) {
            synchronized (MainMusicController.class) {
                if (SINGLETON == null) {
                    SINGLETON = new MainMusicController();
                }
            }
        }
        return SINGLETON;
    }
    
    @Override
    public void play(Music song) {        
        try {
            s = Gdx.audio.newSound(Gdx.files.absolute(FilePath.SONG.getAbsolutePath() + song.getAbsolutePath()));
        } catch (Exception e) {
        	e.printStackTrace();
            s = Gdx.audio.newSound(Gdx.files.classpath(song.getResourcePath()));
        }
        s.loop();
        m = song;
    }
    
    @Override
    public void stop() {
        s.stop();
        s.dispose();
        m = null;
    }
    
    @Override
    public Music playing() {
        return m;
    }
} 