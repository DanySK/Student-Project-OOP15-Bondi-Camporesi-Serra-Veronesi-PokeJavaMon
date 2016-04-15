package controller.music;

import controller.parameters.Music;

public interface MusicController {
    
    public void play(Music m);
    public void stop();
    public Music playing();
}
