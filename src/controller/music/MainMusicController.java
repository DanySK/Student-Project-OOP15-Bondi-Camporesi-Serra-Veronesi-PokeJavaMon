package controller.music;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import controller.parameters.Folder;
import controller.parameters.MusicPath;

/**
 * This is the main music controller of the game
 */
public class MainMusicController implements MusicController {
    
    private static final String SONG = Folder.MAINFOLDER.getAbsolutePath() + File.separator + "music" + File.separator;
    private Music m;
    private Map<MusicPath, Music> musics;
    private Optional<MusicPath> mp;
    private boolean isInit;
    private boolean isPaused;
    
    public MainMusicController() {
        this.mp = Optional.empty();
        this.isInit = false;
        this.isPaused = false;
    }
    
    @Override
    public void initializeMusicController() {
    	if (this.isInit) {
    	    return;
    	}
    	this.musics = new HashMap<>();
        for (final MusicPath m : MusicPath.values()) {
            final Music s = Gdx.audio.newMusic(Gdx.files.absolute(SONG + m.getAbsolutePath()));
            this.musics.put(m, s);
        }
        this.isInit = true;
    }
    
    @Override
    public void playMusic(final MusicPath song) {   
        this.m = this.musics.get(song);
        this.m.play();
        this.m.setLooping(true);
        this.mp = Optional.of(song);
    }
    
    @Override
    public void stopMusic() {
        this.m.stop();
        this.mp = Optional.empty();
    }
    
    @Override
    public void pause() {
        this.m.pause();
        this.isPaused = true;
    }
    
    @Override
    public void resume() {
        this.m.play();
        this.m.setLooping(true);
        this.isPaused = false;
    }
    
    @Override
    public Optional<MusicPath> playing() {
        return this.mp;
    }
    
    @Override
    public boolean isPaused() {
        return this.isPaused;
    }
} 