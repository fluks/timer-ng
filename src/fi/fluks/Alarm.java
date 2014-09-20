package fi.fluks;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Alarm extends ClipWrapper {

    public Alarm(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(file);
    }

    @Override
    public void play() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
}
