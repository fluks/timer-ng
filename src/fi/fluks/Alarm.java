package fi.fluks;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Class for playing the alarm sound. When the target time is reached, the
 * @{link #play() play} method on this class' instance, is called.
 */
public class Alarm extends ClipWrapper {

    public Alarm() {}
    
    /**
     * @param file The alarm sound file.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public Alarm(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(file);
    }

    @Override
    /** Play the alarm sound continuously.
    */
    public void play() {
        if (clip == null)
            throw new NullPointerException();

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
}
