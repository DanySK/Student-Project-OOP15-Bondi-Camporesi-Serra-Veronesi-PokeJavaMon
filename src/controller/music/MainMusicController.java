package controller.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import controller.parameters.FilePath;
import controller.parameters.Music;

/**
 * This is the main music controller of the game
 */
public final class MainMusicController implements MusicController {
    
    private Sound s;
    private Music m;
    private static MainMusicController singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private MainMusicController() {}
    
    /** 
     * @return the curent {@link MainMusicController}, or a new {@link MainMusicController}
     * if this is the first time this method is invoked
     */
    public static MainMusicController getController() {
        if (singleton == null) {
            synchronized (MainMusicController.class) {
                if (singleton == null) {
                    singleton = new MainMusicController();
                }
            }
        }
        return singleton;
    }
    
    @Override
    public void play(final Music song) {        
        try {
            s = Gdx.audio.newSound(Gdx.files.absolute(FilePath.SONG.getAbsolutePath() + song.getAbsolutePath()));
        } catch (Exception e) {
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