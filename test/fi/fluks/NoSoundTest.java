package fi.fluks;

import fi.fluks.sound.NoSound;
import fi.fluks.sound.AbstractClipWrapper;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class NoSoundTest {
    private AbstractClipWrapper noSound;
    
    @Before
    public void setUp() {
        noSound = new NoSound();
    }

    @Test
    public void initialize() {
        assertTrue(new NoSound() instanceof NoSound);
    }

    @Test
    public void objectIsClipWrapper() {
        assertTrue(noSound instanceof AbstractClipWrapper);
    }

    /* Can't really(at least easily) test that these won't do anything. */
    @Test
    public void testMute() {
        noSound.mute(true);
    }

    @Test
    public void testPlay() {
        noSound.play();
    }

    @Test
    public void testSetVolume() {
        noSound.setVolume(1, 1);
    }

    @Test
    public void testStopAndRewind() {
        noSound.stopAndRewind();
    }

    @Test
    public void testCloseClip() {
        noSound.closeClip();
    }
}
