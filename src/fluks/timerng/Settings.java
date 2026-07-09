package fluks.timerng;

import static fluks.timerng.Global.RESOLUTION_NOT_SET;
import fluks.timerng.Global.Resolution;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 */
public enum Settings {
    INSTANCE;

    private static final String NODE_NAME = "/fluks/timerng";
    private final Preferences prefs = Preferences.userRoot().node(NODE_NAME);
    
    public void flush() {
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            //System.getLogger(Global.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void setNotify(boolean notify) {
        prefs.putBoolean("notify", notify);
    }

    public boolean getNotify() {
        return prefs.getBoolean("notify", false);
    }

    public void setResolution(int width, int height) {
        prefs.putInt("width", width);
        prefs.putInt("height", height);
    }

    public Resolution getResolution() {
        int width = prefs.getInt("width", RESOLUTION_NOT_SET);
        int height = prefs.getInt("height", RESOLUTION_NOT_SET);
        Resolution r = width == RESOLUTION_NOT_SET || height == RESOLUTION_NOT_SET ?
                null : new Resolution(width, height);

        return r;
    }

    public void setTimerTabVolume(int volume) {
        prefs.putInt("timertabvolume", volume);
    }

    public int getTimerTabVolume() {
        return prefs.getInt("timertabvolume", 50);
    }

    public void setTimerTabMute(boolean mute) {
        prefs.putBoolean("timertabmute", mute);
    }

    public boolean getTimerTabMute() {
        return prefs.getBoolean("timertabmute", false);
    }

    public void setAlarmTabMute(boolean mute) {
        prefs.putBoolean("alarmtabmute", mute);
    }

    public boolean getAlarmTabMute() {
        return prefs.getBoolean("alarmtabmute", false);
    }

    public void setAlarmTabVolume(int volume) {
        prefs.putInt("alarmtabvolume", volume);
    }

    public int getAlarmTabVolume() {
        return prefs.getInt("alarmtabvolume", 50);
    }
}
