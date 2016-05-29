package controller.music;

import java.util.Optional;

import controller.parameters.MusicPath;

/**
 * This is the interface that all music controllers for this game must implement
 */
public interface MusicController {
    
    /**
     * ScreenView the selected {@link MusicPath}
     * @param m the selected {@link MusicPath}
     */
    void playMusic(MusicPath m);
    
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
     * @return the {@link MusicPath} that the controller is playing
     */
    Optional<MusicPath> playing();
    
    /**
     * @return true if music is paused, false otherwise
     */
    boolean isPaused();

    /**
     * Initializes the music controller
     */
    void initializeMusicController();
}
