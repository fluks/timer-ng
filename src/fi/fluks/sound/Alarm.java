package fi.fluks.sound;

import javax.sound.sampled.Clip;

/** Class for playing the alarm sound. When the target time is reached, the
 * @{link #play() play} method on this class' instance, is called.
 */
public class Alarm extends AbstractClipWrapper {

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
