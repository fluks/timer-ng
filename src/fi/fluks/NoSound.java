package fi.fluks;

/** A class to use instead of {@link fi.fluks.Alarm Alarm} or
 * {@link fi.fluks.Beep Beep}, when audio is not available. This class
 * overrides all the methods in {@link fi.fluks.ClipWrapper ClipWrapper},
 * replacing them by stubs, which do nothing. This way, a lot of null checks
 * can be avoided.
 */
public class NoSound extends ClipWrapper {

    /** Nop.
     * @param mute 
     */
    @Override
    public void mute(boolean mute) { }

    /** Nop.
     */
    @Override
    public void play() { }

    /** Nop.
     * @param volume
     * @param range 
     */
    @Override
    public void setVolume(int volume, int range) { }

    /** Nop.
     */
    @Override
    public void stopAndRewind() { }
}
