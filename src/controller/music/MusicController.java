package controller.music;

import java.util.Optional;

import controller.parameters.Music;

/**
 * This is the interface that all music controllers for this game must implement
 */
public interface MusicController {
    
    /**
     * MainGameView the selected {@link Music}
     * @param m the selected {@link Music}
     */
    void playMusic(Music m);
    
    /**
     * Stop playing a song
     */
    void stopMusic();
    
    /**
     * @return the {@link Music} that the controller is playing
     */
    Optional<Music> playing();

    /**
     * Initializes the music controller
     */
    void initializeMusicController();
}
