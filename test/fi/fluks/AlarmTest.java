package fi.fluks;

import java.io.InputStream;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class AlarmTest {
    private ClipWrapper alarm;
    private InputStream is;
    private static final int DELAY = 1500;
    private static final int DELAY_BETWEEN_PLAYING = 500;
    private static final String ALARM_FILE = "/resources/alarm.wav";
    
    @Before
    public void setUp() {
        is = LoadResource.loadResource(ALARM_FILE);
        try {
            alarm = new Alarm(is);
        }
        catch (Exception e) {
            System.err.println("Can't setup Alarm with stream, " +
                e.getMessage());
        }
    }

    @Test
    public void initialize() {
        assertTrue(new Alarm() instanceof Alarm);
    }
    
    @Test
    public void objectIsClipWrapper() {
        assertTrue(alarm instanceof ClipWrapper);
    }

    @Test
    public void initializeWithStreamParam() {
        try {
            is = LoadResource.loadResource(ALARM_FILE);
            alarm = new Alarm(is);
        }
        catch (Exception e) {
            fail("Can't initialize with stream, " + e.getMessage());
        }
    }
    
    /* The rest of the tests doesn't assert anything. To verify that these
     * succeed, tester needs to use his senses. */
    @Test
    public void play() {
        try {
            System.out.println("Play alarm for " + DELAY + "ms.");
            alarm.play();
            Thread.sleep(DELAY);
            System.out.println("Stop and rewind.");
            alarm.stopAndRewind();

            Thread.sleep(DELAY_BETWEEN_PLAYING);
            System.out.println("Play again.");
            alarm.play();
            Thread.sleep(DELAY);
            alarm.stopAndRewind();
            
            Thread.sleep(DELAY_BETWEEN_PLAYING);
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted, " + e.getMessage());
        }
    }
    
    @Test
    public void mute() {
        try {
            System.out.println("Play alarm mute.");
            alarm.mute(true);
            alarm.play();
            Thread.sleep(DELAY);
            alarm.stopAndRewind();

            Thread.sleep(DELAY_BETWEEN_PLAYING);
            alarm.mute(false);
            System.out.println("Play alarm not mute.");
            alarm.play();
            Thread.sleep(DELAY);
            alarm.stopAndRewind();
            
            Thread.sleep(DELAY_BETWEEN_PLAYING);
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted, " + e.getMessage());
        }
    }

    @Test
    public void volume() {
         try {
            System.out.println("Play alarm volume set to minimum.");
            alarm.setVolume(0, 100);
            alarm.play();
            Thread.sleep(DELAY);
            alarm.stopAndRewind();

            Thread.sleep(DELAY_BETWEEN_PLAYING);
            System.out.println("Play alarm volume set to maximum.");
            alarm.setVolume(100, 100);
            alarm.play();
            Thread.sleep(DELAY);
            alarm.stopAndRewind();

            Thread.sleep(DELAY_BETWEEN_PLAYING);
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted, " + e.getMessage());
        }
    }
}
