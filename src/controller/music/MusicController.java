package controller.music;

import controller.parameters.Music;

/**
 * This is the interface that all music controllers for this game must implement
 */
public interface MusicController {
    
    /**
     * Play the selected {@link Music}
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
    Music playing();
}
