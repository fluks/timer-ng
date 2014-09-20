
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class B extends ClipWrapper {

    public B(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(file);
    }

    @Override
    public void play() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
}
