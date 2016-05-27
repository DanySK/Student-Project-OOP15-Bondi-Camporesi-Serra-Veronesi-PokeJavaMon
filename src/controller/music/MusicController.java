package controller.music;

import java.util.Optional;

import controller.parameters.Music;

/**
 * This is the interface that all music controllers for this game must implement
 */
public interface MusicController {
    
    /**
     * ScreenView the selected {@link Music}
     * @param m the selected {@link Music}
     */
    void playMusic(Music m);
    
    /**
     * Stop playing a song
     */
    void stopMusic();
    
    /**
     * Pauses the music
     */
    void pause();
    
    /**
     * Resumes the music
     */
    void resume();
    
    /**
     * @return the {@link Music} that the controller is playing
     */
    Optional<Music> playing();
    
    /**
     * @return true if music is paused, false otherwise
     */
    boolean isPaused();

    /**
     * Initializes the music controller
     */
    void initializeMusicController();
}
