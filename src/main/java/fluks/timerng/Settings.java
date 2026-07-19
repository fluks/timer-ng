package fluks.timerng;

import static fluks.timerng.Global.RESOLUTION_NOT_SET;
import fluks.timerng.Global.Resolution;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 */
public enum Settings {
    INSTANCE;

    /**
     */
    public enum ActiveTab {
        TIMER_TAB,
        ALARM_TAB,
        STOPWATCH_TAB,
        TIMEZONES_TAB;
    }

    private static final String NODE_NAME = "/fluks/timerng";
    private final Preferences prefs = Preferences.userRoot().node(NODE_NAME);
    
    /**
     */
    public void flush() {
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            //System.getLogger(Global.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /**
     * @param notify 
     */
    public void setNotify(boolean notify) {
        prefs.putBoolean("notify", notify);
    }

    /**
     * @return 
     */
    public boolean getNotify() {
        return prefs.getBoolean("notify", false);
    }

    /**
     * @param width
     * @param height 
     */
    public void setResolution(int width, int height) {
        prefs.putInt("width", width);
        prefs.putInt("height", height);
    }

    /**
     * @return 
     */
    public Resolution getResolution() {
        int width = prefs.getInt("width", RESOLUTION_NOT_SET);
        int height = prefs.getInt("height", RESOLUTION_NOT_SET);
        Resolution r = width == RESOLUTION_NOT_SET || height == RESOLUTION_NOT_SET ?
                null : new Resolution(width, height);

        return r;
    }

    /**
     * @param volume 
     */
    public void setTimerTabVolume(int volume) {
        prefs.putInt("timertabvolume", volume);
    }

    /**
     * @return 
     */
    public int getTimerTabVolume() {
        return prefs.getInt("timertabvolume", 50);
    }

    /**
     * @param mute 
     */
    public void setTimerTabMute(boolean mute) {
        prefs.putBoolean("timertabmute", mute);
    }

    /**
     * @return 
     */
    public boolean getTimerTabMute() {
        return prefs.getBoolean("timertabmute", false);
    }

    /**
     * @param interval 
     */
    public void setTimerTabInterval(boolean interval) {
        prefs.putBoolean("timertabinterval", interval);
    }

    /**
     * @return 
     */
    public boolean getTimerTabInterval() {
        return prefs.getBoolean("timertabinterval", false);
    }

    /**
     * @param mute 
     */
    public void setAlarmTabMute(boolean mute) {
        prefs.putBoolean("alarmtabmute", mute);
    }

    /**
     * @param time 
     */
    public void setTimerTabTimer(TimeUnits time) {
        var timeString = Utils.writeObjectToString(time);
        prefs.put("timertabtimer", timeString);
    }

    /**
     * @return 
     */
    public TimeUnits getTimerTabTimer() {
        var time= prefs.get("timertabtimer", "");
        if (time.isEmpty()) {
            return new TimeUnits();
        }
        return (TimeUnits) Utils.readObjectFromString(time);
    }

    /**
     * @param time 
     */
    public void setAlarmTabTimer(TimeUnits time) {
        var timeString = Utils.writeObjectToString(time);
        prefs.put("alarmtabtimer", timeString);
    }

    /**
     * @return 
     */
    public TimeUnits getAlarmTabTimer() {
        var time= prefs.get("alarmtabtimer", "");
        if (time.isEmpty()) {
            return new TimeUnits();
        }
        return (TimeUnits) Utils.readObjectFromString(time);
    }

    /**
     * @return 
     */
    public boolean getAlarmTabMute() {
        return prefs.getBoolean("alarmtabmute", false);
    }

    /**
     * @param volume 
     */
    public void setAlarmTabVolume(int volume) {
        prefs.putInt("alarmtabvolume", volume);
    }

    /**
     * @return 
     */
    public int getAlarmTabVolume() {
        return prefs.getInt("alarmtabvolume", 50);
    }

    /**
     * @param zones 
     */
    public void setTimezones(List<ZoneId> zones) {
        var encoded = Utils.writeObjectToString((Serializable) zones);
        prefs.put("timezonestabzones", encoded);
    }

    /**
     * @return 
     */
    public List<ZoneId> getTimezones() {
        var encoded = prefs.get("timezonestabzones", "");
        if (encoded.isEmpty()) {
            return new ArrayList<>();
        }
        var decoded = Utils.readObjectFromString(encoded);
        if (decoded == null) {
            return new ArrayList<>();
        }

        return (List<ZoneId>) decoded;
    }

    /**
     * @param tab 
     */
    public void setActiveTab(ActiveTab tab) {
        prefs.putInt("activetab", tab.ordinal());
    }

    /**
     * @return 
     */
    public ActiveTab getActiveTab() {
        var i = prefs.getInt("activetab", ActiveTab.TIMER_TAB.ordinal());
        var tabs = ActiveTab.values();

        return (i >= 0 && i < tabs.length ? tabs[i] : ActiveTab.TIMER_TAB);
    }

    /**
     * @param laps 
     */
    public void setLaps(List<String> laps) {
        var s = Utils.writeObjectToString((Serializable) laps);
        prefs.put("laps", s);
    }

    /**
     * @return 
     */
    public List<String> getLaps() {
        var s = prefs.get("laps", null);
        if (s == null) {
            return new ArrayList<>();
        }
        return (List<String>) Utils.readObjectFromString(s);
    }
}
