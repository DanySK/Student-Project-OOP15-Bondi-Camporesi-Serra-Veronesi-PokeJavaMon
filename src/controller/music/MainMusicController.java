package controller.music;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import controller.parameters.FilePath;
import controller.parameters.Music;

/**
 * This is the main music controller of the game
 */
public final class MainMusicController implements MusicController {
    
    private Sound s;
    private Map<Music, Sound> sounds;
    private Music m;
    private static MainMusicController singleton;
    
    /**
     * Private constructor, used by the method getController
     */
    private MainMusicController() {
    	this.sounds = new HashMap<>();
    	for (final Music m : Music.values()) {
    		final Sound s = Gdx.audio.newSound(Gdx.files.absolute(FilePath.SONG.getAbsolutePath() + m.getAbsolutePath()));
    		this.sounds.put(m, s);
    	}
    }
    
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
    public void playMusic(final Music song) {   

        this.s = this.sounds.get(song);
    	s.loop();
        m = song;
    }
    
    @Override
    public void stopMusic() {
        s.stop();
        m = null;
    }
    
    @Override
    public Music playing() {
        return m;
    }
} 