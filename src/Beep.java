
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Beep extends ClipWrapper {

    public Beep(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(file);
    }

    @Override
    public void play() {
        clip.start();
        clip.setFramePosition(0);
    }
}
