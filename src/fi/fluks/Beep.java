package fi.fluks;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Class for playing the beep sound. When interval is selected and a time
 * set, the @{link #play() play} method on this class' instance, is called,
 * whenever current time modulo interval target time is zero.
 */
public class Beep extends ClipWrapper {

    /**
     * @param file The beep sound file.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public Beep(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(file);
    }

    @Override
    /** Play the beep sound once and rewind to the beginning.
    */
    public void play() {
        clip.start();
        clip.setFramePosition(0);
    }
}
