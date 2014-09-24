package fi.fluks;

import fluks.timerng.sound.Beep;
import fluks.timerng.sound.AbstractClipWrapper;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BeepTest {
    private AbstractClipWrapper beep;
    private static final int DELAY = 1500;
    private static final int DELAY_BETWEEN_PLAYING = 500;
    private static final String BEEP_FILE = "/resources/beep.wav";

    @Before
    public void setUp() throws LineUnavailableException, IOException,
        UnsupportedAudioFileException {
        try (InputStream is = LoadResource.loadResource(BEEP_FILE)) {
            beep = new Beep();
            beep.openClip(is);
        }
        catch (Exception e) {
            beep.closeClip();
            throw e;
        }
    }

    @Test
    public void initialize() {
        assertTrue(new Beep() instanceof Beep);
    }
    
    @Test
    public void objectIsClipWrapper() {
        assertTrue(beep instanceof AbstractClipWrapper);
    }

    /* The rest of the tests doesn't assert anything. To verify that these
     * succeed, tester needs to use his senses. */
    @Test
    public void play() {
        try {
            Thread.sleep(DELAY_BETWEEN_PLAYING);
            System.out.println("Beep once.");
            beep.play();
            Thread.sleep(DELAY);

            Thread.sleep(DELAY_BETWEEN_PLAYING);
            System.out.println("Beep twice.");
            beep.play();
            Thread.sleep(DELAY);
            Thread.sleep(DELAY_BETWEEN_PLAYING);
            beep.play();
            Thread.sleep(DELAY);

            Thread.sleep(DELAY_BETWEEN_PLAYING);
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted, " + e.getMessage());
        }
    }
    
    @Test
    public void mute() {
        try {
            System.out.println("Beep once mute.");
            beep.mute(true);
            beep.play();
            Thread.sleep(DELAY);

            Thread.sleep(DELAY_BETWEEN_PLAYING);
            beep.mute(false);
            System.out.println("Beep once not mute.");
            beep.play();
            Thread.sleep(DELAY);

            Thread.sleep(DELAY_BETWEEN_PLAYING);
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted, " + e.getMessage());
        }
    }

    @Test
    public void volume() {
         try {
            System.out.println("Beep once volume set to minimum.");
            beep.setVolume(0, 100);
            beep.play();
            Thread.sleep(DELAY);

            Thread.sleep(DELAY_BETWEEN_PLAYING);
            System.out.println("Beep once volume set to max.");
            beep.setVolume(100, 100);
            beep.play();
            Thread.sleep(DELAY);

            Thread.sleep(DELAY_BETWEEN_PLAYING);
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted, " + e.getMessage());
        }
    }

    @Test
    public void closeClip() {
        beep.closeClip();
        System.out.println("Play on closed clip.");
        beep.play();
    }
}
