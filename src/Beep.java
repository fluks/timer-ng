
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** A class to play the interval beep sound.
 * A new thread is used to play a clip, because even Clip.start creates a new
 * thread, it's is easier to control the playing of the clip this way.
 */
public final class Beep extends Thread {
    private Clip beep;
    private static Beep obj = null;

    /** Get an instance of the class and try to open the file to play.
     * @param file Audio file to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     * @return An instance of Beep.
     */
    public static Beep getInstance(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        if (obj == null)
            obj = new Beep(file);
        return obj;
    }

    /** Create a new instance and try to open the file to play.
     * @param file Audio file to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    private Beep(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        openClip(file);
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                    beep.start();
                    beep.setFramePosition(0);
                }
            }
            catch (InterruptedException e) {}
        }
    }

    /** Play the audio.
     */
    public void play() {
        synchronized (this) {
            notify();
        }
    }

    public void setVolume(int volume, int range) {
        FloatControl c =
            (FloatControl) beep.getControl(FloatControl.Type.MASTER_GAIN);
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
    public void openClip(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        beep = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        beep.open(ais);
    }
}
