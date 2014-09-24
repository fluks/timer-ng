package fi.fluks.sound;

import javax.sound.sampled.LineEvent;

/** Class for playing the beep sound. When interval is selected and a time
 * set, the @{link #play() play} method on this class' instance, is called,
 * whenever current time modulo interval target time is zero.
 */
public class Beep extends AbstractClipWrapper {

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
