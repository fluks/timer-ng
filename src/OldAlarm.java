
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public final class OldAlarm {
    private Clip beep;
    private static OldAlarm obj = null;

    /** Get an instance of the class and try to open the file to play.
     * @param file Audio file to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     * @return An instance of OldAlarm.
     */
    public static OldAlarm getInstance(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        if (obj == null)
            obj = new OldAlarm(file);
        return obj;
    }

    /** Create a new instance and try to open the file to play.
     * @param file Audio file to play.
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws IOException 
     */
    private OldAlarm(File file) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException {
        openClip(file);
    }

    /** Play the audio.
     */
    public void play() {
        beep.loop(Clip.LOOP_CONTINUOUSLY);
        beep.start();
    }

    public void stop() {
        beep.stop();
        beep.flush();
        beep.setFramePosition(0);
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
