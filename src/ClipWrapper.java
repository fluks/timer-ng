
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

 /** An abstract wrapper class for the {@link javax.sound.sampled.Clip Clip}
 * object. Most of the needed operations for playing the sounds needed in this
 * program, are implemented in this class already, only the {@link #play() play}
 * method needs to be implemented by the subclass.
 */
public abstract class ClipWrapper {
    protected Clip clip;

    /** Create a new instance and try to open the file to play.
     * @param file Audio file to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public ClipWrapper(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        openClip(file);
    }

     /** Sets the volume for the master gain control of the line.
     * @param volume
     * @param range The range of the volume, e.g. for range 0-100, range
     * would be 100.
     */
    public void setVolume(int volume, int range) {
        FloatControl c =
            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        Float max = c.getMaximum();
        Float min = c.getMinimum();
        Float distance = Math.abs(max - min);
        c.setValue(min + (distance / range) * volume);
    }

    /** Open the audio file for ready to play.
     * @param file Audio file to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    public final void openClip(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
    }
    
    /** Play the audio.
     */
    public abstract void play();

    /** Stop playing the clip and rewind to the beginning.
     */
    public void stopAndRewind() {
        clip.stop();
        clip.flush();
        clip.setFramePosition(0);
    }
}
