package fi.fluks.sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

 /** An abstract wrapper class for the {@link javax.sound.sampled.Clip Clip}
 * object. Most of the needed operations for playing the sounds needed in this
 * program, are implemented in this class already, only the {@link #play() play}
 * method needs to be implemented by the subclass.
 */
public abstract class AbstractClipWrapper {
    protected Clip clip;

     /** Sets the volume for the master gain control of the line.
     * @param volume
     * @param range The range of the volume, e.g. for range 0-100, range
     * would be 100.
     */
    public void setVolume(int volume, int range) {
        if (clip == null)
            throw new NullPointerException();

        FloatControl c =
            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        Float max = c.getMaximum();
        Float min = c.getMinimum();
        Float distance = Math.abs(max - min);
        c.setValue(min + (distance / range) * volume);
    }

    /** Set the line mute. Doesn't affect the volume of the line.
     * @param mute True to mute the line, false to change it not be mute.
     */ 
    public void mute(boolean mute) {
        if (clip == null)
            throw new NullPointerException();

        BooleanControl c = (BooleanControl) clip.getControl(
            BooleanControl.Type.MUTE);
        c.setValue(mute);
    }

    /** Open the audio file for ready to play.
     * @param is Audio stream to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public void openClip(InputStream is) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        clip = AudioSystem.getClip();
        /* Without BufferedInputStream throws(mark/reset not supported)
         * running jar. */
        AudioInputStream ais = AudioSystem.getAudioInputStream(
            new BufferedInputStream(is));
        clip.open(ais);
    }
    
    /** Play the audio.
     */
    public abstract void play();

    /** Stop playing the clip and rewind to the beginning.
     */
    public void stopAndRewind() {
        if (clip == null)
            throw new NullPointerException();

        clip.stop();
        clip.flush();
        clip.setFramePosition(0);
    }

    /** Close clip.
     */
    public void closeClip() {
        if (clip != null)
            clip.close();
    }
}
