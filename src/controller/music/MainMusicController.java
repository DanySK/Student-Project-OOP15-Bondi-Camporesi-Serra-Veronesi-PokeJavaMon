package controller.music;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import controller.parameters.Folder;
import controller.parameters.Music;

/**
 * This is the main music controller of the game
 */
public class MainMusicController implements MusicController {
    
    private static final String SONG = Folder.MAINFOLDER.getAbsolutePath() + File.separator + "music" + File.separator;
    private Sound s;
    private Map<Music, Sound> sounds;
    private Optional<Music> m;
    private boolean isInit;
    private boolean isPaused;
    
    public MainMusicController() {
        this.m = Optional.empty();
        this.isInit = false;
        this.isPaused = false;
    }
    
    @Override
    public void initializeMusicController() {
    	if (this.isInit) {
    	    return;
    	}
    	this.sounds = new HashMap<>();
        for (final Music m : Music.values()) {
            final Sound s = Gdx.audio.newSound(Gdx.files.absolute(SONG + m.getAbsolutePath()));
            this.sounds.put(m, s);
        }
        this.isInit = true;
    }
    
    @Override
    public void playMusic(final Music song) {   
        this.s = this.sounds.get(song);
        this.s.loop();
        this.m = Optional.of(song);
    }
    
    @Override
    public void stopMusic() {
        this.s.stop();
        this.m = Optional.empty();
    }
    
    @Override
    public void pause() {
        this.s.pause();
        this.isPaused = true;
    }
    
    @Override
    public void resume() {
        this.s.resume();
        this.isPaused = false;
    }
    
    @Override
    public Optional<Music> playing() {
        return this.m;
    }
    
    @Override
    public boolean isPaused() {
        return this.isPaused;
    }
} 