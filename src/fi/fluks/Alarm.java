package fi.fluks;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Class for playing the alarm sound. When the target time is reached, the
 * @{link #play() play} method on this class' instance, is called.
 */
public class Alarm extends ClipWrapper {

    /** Initialize new Alarm object without opening a clip.
     */
    public Alarm() {}
    
    /**
     * @param is The alarm sound stream.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public Alarm(InputStream is) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(is);
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
