package controller.music;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import controller.parameters.FilePath;
import controller.parameters.Music;

/**
 * This is the main music controller of the game
 */
public class MainMusicController implements MusicController {
    
    private Sound s;
    private Map<Music, Sound> sounds;
    private Optional<Music> m = Optional.empty();
    private boolean isInit = false;
    
    @Override
    public void initializeMusicController() {
    	if (isInit) {
    		return;
    	}
        Long start = System.currentTimeMillis();
    	this.sounds = new HashMap<>();
        for (final Music m : Music.values()) {
                Long startM = System.currentTimeMillis();
                final Sound s = Gdx.audio.newSound(Gdx.files.absolute(FilePath.SONG.getAbsolutePath() + m.getAbsolutePath()));
                this.sounds.put(m, s);
                Long endM = System.currentTimeMillis();
                System.out.println(m.name() + "took " + ((endM - startM)/1000f) + "seconds to complete");
        }
        Long end = System.currentTimeMillis();
        System.out.println("it took " + ((end - start)/1000f) + "seconds to complete this operation");
        isInit = true;
    }
    
    @Override
    public void playMusic(final Music song) {   

        this.s = this.sounds.get(song);
    	s.loop();
        m = Optional.of(song);
    }
    
    @Override
    public void stopMusic() {
        s.stop();
        m = Optional.empty();
    }
    
    @Override
    public Optional<Music> playing() {
        return m;
    }
} 