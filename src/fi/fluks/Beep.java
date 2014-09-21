package fi.fluks;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Class for playing the beep sound. When interval is selected and a time
 * set, the @{link #play() play} method on this class' instance, is called,
 * whenever current time modulo interval target time is zero.
 */
public class Beep extends ClipWrapper {

    public Beep() {}

    /**
     * @param is The beep sound stream.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public Beep(InputStream is) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        super(is);
    }

    @Override
    /** Play the beep sound once and rewind to the beginning.
    */
    public void play() {
        if (clip == null)
            throw new NullPointerException();

        clip.start();
        /* TimerTask's run() is now run in Swing's event dispatch thread,
         * without this listener(if we would only call setFramePosition), beeps
         * would not be complete beeps, I don't know why. */
        clip.addLineListener((LineEvent e) -> {
            if (e.getType() == LineEvent.Type.STOP && clip != null)
                clip.setFramePosition(0);
        });
    }
}
