package fi.fluks;

/** A class to use instead of {@link fi.fluks.Alarm Alarm} or
 * {@link fi.fluks.Beep Beep}, when audio is not available. This class
 * overrides all the methods in {@link fi.fluks.ClipWrapper ClipWrapper},
 * replacing them by stubs, which do nothing. This way, a lot of null checks
 * can be avoided.
 */
public class NoSound extends ClipWrapper {

    @Override
    public void mute(boolean mute) {
    }

    @Override
    public void play() {
    }

    @Override
    public void setVolume(int volume, int range) {
    }

    @Override
    public void stopAndRewind() {
    }
}
